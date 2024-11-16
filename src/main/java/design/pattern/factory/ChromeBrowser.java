package design.pattern.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.logging.Logger;

public class ChromeBrowser implements Browsers{

    private static Logger logger = Logger.getLogger(ChromeBrowser.class.getName());

    @Override
    public WebDriver launchBrowser() {
        logger.info("Launching Chrome Browser");
        return new ChromeDriver();
    }
}
