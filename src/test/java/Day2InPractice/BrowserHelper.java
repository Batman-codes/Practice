package Day2InPractice;

import java.time.Duration;

import org.openqa.selenium.WebDriver;

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
