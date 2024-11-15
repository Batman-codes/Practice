package Day2OutPractice;

import java.util.Map;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Day2InPractice.BrowserFactory;
import Day2InPractice.BrowserTypes;

public class LoginFacbook {
	
	private Map<String, Object> myTestData;
	private static WebDriver driver;
	@Test
	public void facebookLogin() {
		
		myTestData = new DataProviderFactory().getTestData(DataSources.json).getTestData("JsonTestData");
		
		driver = BrowserFactory.createInstance(BrowserTypes.chrome);
		
		//Launch Facebook URL
		BrowserFactory.
			launchURL(driver, "https://www.facebook.com/").
			findElement(By.id("email")).
			sendKeys(myTestData.get("username").toString());
		
	}

}
