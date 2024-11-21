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

        ChromeOptions chromeOption2 = new ChromeOptions();
        chromeOption2.addArguments("--start-maximized");
        chromeOption2.addArguments("--disable-notifications");
        chromeOption2.addArguments("--headless");

        FirefoxOptions firefoxOptions1 = new FirefoxOptions();
        firefoxOptions1.addArguments("--start-maximized");
        firefoxOptions1.addArguments("--disable-notifications");
        firefoxOptions1.addArguments("--headless");

        FirefoxOptions firefoxOptions2 = new FirefoxOptions();
        firefoxOptions1.addArguments("--start-maximized");

        testChrome1(driverPool, url1, chromeOption1);
        testChrome2(driverPool, url2, chromeOption2);

        testFirefox1(driverPool, url1, firefoxOptions1);
        testFirefox2(driverPool, url2, firefoxOptions1);
        testFirefox2(driverPool, "https://www.youtube.com/", firefoxOptions2);

        driverPool.quitAllDrivers();

    }

    private static void myTest1(FirefoxOptions firefoxOptions1) {
        new BrowserCapabilitiesKey(BrowserType.FIREFOX, firefoxOptions1);
    }

    private static void myTest(ChromeOptions chromeOptions){
        new BrowserCapabilitiesKey(BrowserType.CHROME, chromeOptions);
    }

    private static void testChrome1(DriverObjectPool driverPool, String url, ChromeOptions chromeOption) {
        WebDriver driver = driverPool.getDriver(BrowserType.CHROME, url, chromeOption);
        driverPool.releaseDriver(driver);
    }

    private static void testChrome2(DriverObjectPool driverPool, String url, ChromeOptions chromeOption) {
        WebDriver driver = driverPool.getDriver(BrowserType.CHROME, url, chromeOption);
        System.out.println(driver.getTitle());
        driverPool.releaseDriver(driver);
    }

    private static void testFirefox1(DriverObjectPool driverPool, String url, FirefoxOptions firefoxOptions) {
        WebDriver driver = driverPool.getDriver(BrowserType.FIREFOX, url, firefoxOptions);
        driverPool.releaseDriver(driver);
    }

    private static void testFirefox2(DriverObjectPool driverPool, String url, FirefoxOptions firefoxOptions) {
        WebDriver driver = driverPool.getDriver(BrowserType.FIREFOX, url, firefoxOptions);
        System.out.println(driver.getTitle());
        driverPool.releaseDriver(driver);
    }

}
