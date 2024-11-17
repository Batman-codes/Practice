package design.pattern.factory;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class FirefoxBrowser implements Browsers{

    private static Logger logger = Logger.getLogger(ChromeBrowser.class.getName());

    @Override
    public WebDriver launchBrowser(Capabilities capabilities) {

        logger.info("Adding capabilities to FirefoxOptions");

        FirefoxOptions firefoxOptions;
        if(capabilities instanceof FirefoxOptions){
            firefoxOptions = (FirefoxOptions) capabilities;
        }else{
            firefoxOptions = new FirefoxOptions();
            logger.info("Warning: Wrong capabilities given for firefox : " + capabilities.toString());
        }
        logger.info("Launching new FireFox Browser");
        return new FirefoxDriver();
    }

}
