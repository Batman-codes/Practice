package design.pattern.factory;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.logging.Logger;

public class DriverObjectPool {

    private final Logger logger = Logger.getLogger(DriverObjectPool.class.getName());

    //Making it final because it is initialized once.
    private final ConcurrentHashMap<BrowserType, BlockingDeque<WebDriver>> browserDriverMap = new ConcurrentHashMap<>();

    //To track all the drivers created. So that it is easy to release or quit the drivers
    private final ConcurrentHashMap<WebDriver, BrowserType> driverKeyMap = new ConcurrentHashMap<>();

    private BrowserFactory browserFactory;

    public DriverObjectPool(BrowserFactory browserFactory){
        this.browserFactory = browserFactory;
    }

    public WebDriver getDriver(BrowserType browserType, String url, Capabilities capabilities){

        logger.info("Getting the Driver from the Driver Pool");

        BlockingDeque<WebDriver> driverQueue = browserDriverMap.computeIfAbsent(browserType, b -> new LinkedBlockingDeque<>());
        WebDriver driver = driverQueue.poll();

        if(driver == null){
            logger.info("Driver does not exist so creating a new Driver for -> " + browserType);
            driver = browserFactory.getInstance(browserType,url, capabilities);
            logger.info("Created a new driver -> " + driver.hashCode() + " For " + browserType);
            logger.info("Adding the driver to driverKeyMap");
            driverKeyMap.put(driver, browserType);
        }else{
            logger.info("Re-using the driver instance -> " + driver.hashCode());
        }
        return driver;
    }

    public void releaseDriver(WebDriver driver){
        BrowserType browserType = driverKeyMap.get(driver);
        if(browserType != null){
            logger.info("Releasing a  $" + browserType +"$ driver -> " + driver.hashCode());
            browserDriverMap.computeIfAbsent(browserType, k-> new LinkedBlockingDeque<>()).offer(driver);
            logger.info("Driver " + driver.hashCode() + "Released and added to the queue");
        }else{
            logger.warning("Warning: Attempted to return a driver or browserType null so quitting it now.");
            driver.quit();
            logger.info("Successfully quit the driver with null BrowserType");
        }
    }

    public void quitAllDrivers(){
        logger.info("Quitting all the drivers");
        for( WebDriver driver : driverKeyMap.keySet()){
            if(driver != null){
                logger.info("Qutting driver -> " + driver.hashCode());
                driver.quit();
            }
        }

    }
}
