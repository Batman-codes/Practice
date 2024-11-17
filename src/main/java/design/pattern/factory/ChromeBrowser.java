package design.pattern.factory;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class ChromeBrowser implements Browsers{

    private static Logger logger = Logger.getLogger(ChromeBrowser.class.getName());

    @Override
    public WebDriver launchBrowser(Capabilities capabilities) {

        logger.info("Adding capabilities to chromeOptions");

        ChromeOptions chromeOptions;
        if(capabilities instanceof ChromeOptions){
            chromeOptions = (ChromeOptions) capabilities;
        }else{
            chromeOptions = new ChromeOptions();
            logger.warning("Warning: Wrong Capabilities are given for chrome" + capabilities.toString());
        }

        logger.info("Launching Chrome Browser");

        return new ChromeDriver(chromeOptions);
    }



}
