package design.pattern.factory;

import org.openqa.selenium.WebDriver;

public class BrowserFactory {

    public WebDriver driver;
    private final DriverObjectPool driverPool;

    public BrowserFactory(DriverObjectPool driverPool){
        this.driverPool = driverPool;
    }
    public WebDriver getInstance(BrowserType type) {

        if(type != null) {

            switch(type) {

                case chrome:
                    return driverPool.getDriver(BrowserType.chrome, new ChromeBrowser());
                case firefox:
                    return driverPool.getDriver(BrowserType.firefox, new FirefoxBrowser());
                default:
                    throw new IllegalArgumentException("Please select a valid browser");
            }

        }

        throw new IllegalArgumentException("Invalid Argument");

    }

    public WebDriver launchURL(WebDriver driver, String url) {

        BrowserHelper helper = new BrowserHelper(driver);
        helper.maximiseBrowser();
        helper.useImpicitWait();
        helper.loadURL(url);

        return driver;

    }

    public void releaseInstance(BrowserType browserType, WebDriver driver ){
        driverPool.releaseDriver(browserType,driver);
    }

}
