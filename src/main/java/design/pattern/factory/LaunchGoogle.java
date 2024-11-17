package design.pattern.factory;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class LaunchGoogle {
    private static WebDriver driver;

    public static void main(String[] args) {
        BrowserFactory browserFactory = new BrowserFactory();
        DriverObjectPool driverPool = new DriverObjectPool(browserFactory);

        String url1 = "https://www.google.com";
        String url2 = "https://www.facebook.com";

        ChromeOptions  chromeOption1 = new ChromeOptions();
        chromeOption1.addArguments("--headless");

        ChromeOptions chromeOption2 = new ChromeOptions();

        FirefoxOptions firefoxOptions1 = new FirefoxOptions();
        firefoxOptions1.setCapability("--headless", true);

        FirefoxOptions firefoxOptions2 = new FirefoxOptions();

        testChrome1(driverPool, url1, chromeOption1);
        testChrome2(driverPool, url2, chromeOption2);
        testFirefox1(driverPool, url1, firefoxOptions1);
        testFirefox2(driverPool, url2, firefoxOptions2);

        driverPool.quitAllDrivers();

    }

    private static void testChrome1(DriverObjectPool driverPool, String url1, ChromeOptions chromeOption1) {
        WebDriver driver = driverPool.getDriver(BrowserType.CHROME, url1, chromeOption1);
        driverPool.releaseDriver(driver);
    }

    private static void testChrome2(DriverObjectPool driverPool, String url2, ChromeOptions chromeOption2) {
        WebDriver driver = driverPool.getDriver(BrowserType.CHROME, url2, chromeOption2);
        driverPool.releaseDriver(driver);
    }

    private static void testFirefox1(DriverObjectPool driverPool, String url1, FirefoxOptions firefoxOptions1) {
        WebDriver driver = driverPool.getDriver(BrowserType.FIREFOX, url1, firefoxOptions1);
        driverPool.releaseDriver(driver);
    }

    private static void testFirefox2(DriverObjectPool driverPool, String url2, FirefoxOptions firefoxOptions2) {
        WebDriver driver = driverPool.getDriver(BrowserType.FIREFOX, url2, firefoxOptions2);
        driverPool.releaseDriver(driver);
    }

}
