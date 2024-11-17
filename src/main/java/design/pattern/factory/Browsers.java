package design.pattern.factory;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Map;

public interface Browsers {
    /**
     * Method to launch the Browser with given capabilities
     * @param capabilities -> The Desired Capabilities of the Browser
     * @return -> Returns the instance of Browser as WebDriver
     * @since v1.1
     */
    public WebDriver launchBrowser(Capabilities capabilities);
}
