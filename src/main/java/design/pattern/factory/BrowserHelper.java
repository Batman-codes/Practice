package design.pattern.factory;

import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class BrowserHelper {

    public WebDriver driver;

    public BrowserHelper(WebDriver driver){
        this.driver = driver;
    }

    public void maximiseBrowser() {

        driver.manage().window().maximize();
    }

    public void useImpicitWait() {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

    }

    public void loadURL(String url) {

        driver.get(url);
    }
}
