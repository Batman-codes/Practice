package simpleObjectpoolPattern;

import net.bytebuddy.build.Plugin;
import org.openqa.selenium.WebDriver;

public class ChromeTest {

    static SimpleObjectPool drivers;
    public static void main(String[] args) {
        drivers = new SimpleObjectPool();

        test1();
        test2();
    }

    private static void test2() {

        WebDriver driver = drivers.getDriver();

        driver.get("https://www.google.com");

        drivers.releaseDriver(driver);
    }

    private static void test1() {

        WebDriver driver = drivers.getDriver();

        driver.get("https://www.google.com");

        drivers.releaseDriver(driver);
    }
}
