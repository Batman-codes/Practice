package Day2InPractice;

import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class LaunchGoogle {
	
	public static WebDriver driver;
	
	@Test
	public void launchGoogle() {
		
		driver = BrowserFactory.createInstance(BrowserTypes.chrome);
		BrowserFactory.launchURL(driver, "https://google.com");
		
	}
	

}
