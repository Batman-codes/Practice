package design.pattern.factory;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

public interface BrowserFactoryInterface {

    /**
     * Method to get the Driver from Browser Factory
     * @param browserType -> BrowserType like chrome, firefox, safari, edge etc
     * @param url -> url of the application
     * @param capabilities -> capabilities of the browsers
     * @return returns Webdriver of the browser with capabilities
     */
    public WebDriver getInstance(BrowserType browserType, String url, Capabilities capabilities);
}
