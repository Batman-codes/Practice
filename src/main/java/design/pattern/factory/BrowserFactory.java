package design.pattern.factory;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.logging.Logger;

public class BrowserFactory implements BrowserFactoryInterface{

    public WebDriver driver;
    private static Logger logger = Logger.getLogger(BrowserFactory.class.getName());

    public BrowserFactory(){

    }
    public WebDriver getInstance(BrowserType type, String url, Capabilities capabilities) {

        if(type != null) {

            switch(type) {

                case CHROME:
                    driver =  new ChromeBrowser().launchBrowser(capabilities);
                    logger.info("Got the chrome driver -> " + driver.hashCode());
                    break;
                case FIREFOX:
                    driver = new FirefoxBrowser().launchBrowser(capabilities);
                    logger.info("Got the firefox driver -> " + driver.hashCode());
                    break;
                default:
                    throw new IllegalArgumentException("Please select a valid browser");
            }
        }
        launchURL(url);
        return driver;
    }

    public void launchURL(String url) {
        logger.info("Setting up the Browser and Launching the URL");
        BrowserHelper helper = new BrowserHelper(driver);
        helper.maximiseBrowser();
        helper.useImpicitWait();
        helper.loadURL(url);
        logger.info("URL is launched. Title is -> " + driver.getTitle());
    }

}
