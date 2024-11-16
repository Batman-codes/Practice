package design.pattern.factory;

import org.openqa.selenium.WebDriver;

public class LaunchGoogle {
    private static WebDriver driver;
    private static BrowserFactory browserFactory;
    public static void main(String[] args) {
        DriverObjectPool driverPool = new DriverObjectPool();
        browserFactory = new BrowserFactory(driverPool);

        testChome1();
        testChome2();
        testFF1();
        testFF2();

    }


    public static void testChome1(){
        driver = browserFactory.getInstance(BrowserType.chrome);
        browserFactory.launchURL(driver, "https://www.google.com/");
        browserFactory.releaseInstance(BrowserType.chrome, driver);
    }
    public static void testChome2(){
        driver = browserFactory.getInstance(BrowserType.chrome);
        browserFactory.launchURL(driver, "https://www.google.com/");
        browserFactory.releaseInstance(BrowserType.chrome, driver);
    }

    public static void testFF1(){
        driver = browserFactory.getInstance(BrowserType.firefox);
        browserFactory.launchURL(driver, "https://www.google.com/");
        browserFactory.releaseInstance(BrowserType.firefox, driver);
    }
    public static void testFF2(){
        driver = browserFactory.getInstance(BrowserType.firefox);
        browserFactory.launchURL(driver, "https://www.google.com/");
        browserFactory.releaseInstance(BrowserType.firefox, driver);
    }
}
