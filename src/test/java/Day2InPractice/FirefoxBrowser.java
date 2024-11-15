package Day2InPractice;

import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxBrowser implements Browser{

	private static Logger logger = Logger.getLogger(ChromeBrowser.class.getName());

	@Override
	public WebDriver launchBrowser() {
		
		logger.info("launching firefox broswer");
		return new FirefoxDriver();
		
	}
	
	

}
