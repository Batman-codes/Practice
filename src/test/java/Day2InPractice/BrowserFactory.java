package Day2InPractice;

import org.openqa.selenium.WebDriver;

public class BrowserFactory {
	
	public static WebDriver driver;
	
	public static WebDriver createInstance(BrowserTypes type) {
		
		if(type != null) {
			
			switch(type) {
				
			case chrome:
				return new ChromeBrowser().launchBrowser();
			case firefox:
				return new FirefoxBrowser().launchBrowser();
			default:
				throw new IllegalArgumentException("Please selct a valid browser");
			}
				
		}
		
		throw new IllegalArgumentException("Invalid Argument");
		
	}
	
	public static WebDriver launchURL(WebDriver driver, String url) {
		
		BrowserHelper helper = new BrowserHelper(driver);
		helper.maximiseBrowser();
		helper.useImpicitWait();
		helper.loadURL(url);
		
		return driver;
	
	}
	
	

}
