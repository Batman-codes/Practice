package design.pattern.factory;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class DriverObjectPool {

    private final Logger logger = Logger.getLogger(DriverObjectPool.class.getName());

    //A Hashmap to handle multiple threads. Making it final because it is initialized once.
    private final ConcurrentHashMap<BrowserType, BlockingQueue<WebDriver>> browserDriverMap = new ConcurrentHashMap<>();

    //A Hashmap to store all the capabilities as key and respective WebDriver objects
    private final ConcurrentHashMap<BrowserCapabilitiesKey, BlockingQueue<WebDriver>> driverPool = new ConcurrentHashMap<>();

    //To track all the drivers created. So that it is easy to release or quit the drivers
    private final ConcurrentHashMap<WebDriver, BrowserType> driverKeyMap = new ConcurrentHashMap<>();

    //To track all the drivers created with certain capabilities
    private final ConcurrentHashMap<WebDriver, BrowserCapabilitiesKey> driverKeyMapForCapabilities = new ConcurrentHashMap<>();

    private final BrowserFactory browserFactory;

    private BrowserCapabilitiesKey browserCapabilitiesKey;

    public DriverObjectPool(BrowserFactory browserFactory){
        this.browserFactory = browserFactory;
    }

    public WebDriver getDriver(BrowserType browserType, String url, Capabilities capabilities){

        logger.info("Getting the serialized output of the given capabilities");
        try{
            browserCapabilitiesKey = new BrowserCapabilitiesKey(browserType, capabilities);
            logger.info("Capabilities for browser type " + browserType + " are " + browserCapabilitiesKey);
        }catch (Exception e){
            logger.log(Level.WARNING, "Did not WebDriver using capabilityKey. Falling back to generic capabilities" + e.getMessage(), e);
            browserCapabilitiesKey = new BrowserCapabilitiesKey(browserType, null);
        }

        BlockingQueue<WebDriver> driverQueue = driverPool.computeIfAbsent(browserCapabilitiesKey, b -> new LinkedBlockingQueue<>());

        logger.info("Getting the Driver from the Driver Pool");

        WebDriver driver = driverQueue.poll();

        if(driver == null){
            logger.info("Driver does not exist so creating a new Driver for -> " + browserType);
            driver = browserFactory.getInstance(browserType, url, capabilities);
            logger.info("Created a new driver -> " + driver.hashCode() + " For " + browserType);
            logger.info("Adding the driver to driverKeyMap");
            driverKeyMapForCapabilities.put(driver, browserCapabilitiesKey);
        }else {
            logger.info("Re-using the driver instance -> " + driver.hashCode());
        }

        return driver;
    }

    public void releaseDriver(WebDriver driver){

        BrowserCapabilitiesKey browserCapabilitiesKey = driverKeyMapForCapabilities.get(driver);

        if(browserCapabilitiesKey != null){
            logger.info("Releasing a  $" + browserCapabilitiesKey +"$ driver -> " + driver.hashCode());
            driverPool.computeIfAbsent(browserCapabilitiesKey, k-> new LinkedBlockingQueue<>()).offer(driver);
            logger.info("Driver " + driver.hashCode() + "Released and added to the queue");
        }else{
            logger.warning("Warning: Attempted to return a driver of browserCapabilitiesKey null so quitting it now.");
            driver.quit();
            logger.info("Successfully quit the driver with null browserCapabilitiesKey");
        }
    }

    public void quitAllDrivers(){
        logger.info("Quitting all the drivers");
        for( WebDriver driver : driverKeyMapForCapabilities.keySet()){
            if(driver != null){
                logger.info("Qutting driver -> " + driver.hashCode());
                driver.quit();
            }
        }

    }
}