package Day2InPractice;

import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeBrowser implements Browser{
	
	private static Logger logger = Logger.getLogger(ChromeBrowser.class.getName());
	
	@Override
	public WebDriver launchBrowser() {
		
		logger.info("launching chrome broswer");
		return new ChromeDriver();
		
	}
	
	

}
