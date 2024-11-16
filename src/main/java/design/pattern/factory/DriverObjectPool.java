package design.pattern.factory;

import org.openqa.selenium.WebDriver;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.logging.Logger;

public class DriverObjectPool {

    private final Logger logger = Logger.getLogger(DriverObjectPool.class.getName());
    private final ConcurrentHashMap<BrowserType, BlockingDeque<WebDriver>> browserDriverMap = new ConcurrentHashMap<>();

    public WebDriver getDriver(BrowserType browserType, Browsers browserImplementation){

        logger.info("Getting the Driver from the Driver Pool");
        BlockingDeque<WebDriver> driverQueue = browserDriverMap.computeIfAbsent(browserType, b -> new LinkedBlockingDeque<>());

        WebDriver driver = driverQueue.poll();

        if(driver == null){
            driver = browserImplementation.launchBrowser();
        }
        return driver;
    }

    public void releaseDriver(BrowserType browserType, WebDriver driver){

        if(driver != null){
            browserDriverMap.computeIfAbsent(browserType, k-> new LinkedBlockingDeque<>()).offer(driver);
            System.out.println(browserType +  " -> " + driver.hashCode());
        }else{
            logger.info("Warning: Attempted to return a null driver for browser -> " + browserType);
        }
    }
}
