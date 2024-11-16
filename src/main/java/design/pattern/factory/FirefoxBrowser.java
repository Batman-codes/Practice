package design.pattern.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.logging.Logger;

public class FirefoxBrowser implements Browsers{

    private static Logger logger = Logger.getLogger(ChromeBrowser.class.getName());
    @Override
    public WebDriver launchBrowser() {
        logger.info("Launching new FireFox Browser");
        return new FirefoxDriver();
    }
}
