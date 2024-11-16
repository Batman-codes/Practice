package simpleObjectpoolPattern;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.LinkedList;
import java.util.Queue;

public class SimpleObjectPool {

    protected Queue<WebDriver> driverQueue;
    public SimpleObjectPool(){
        driverQueue = new LinkedList<>();
    }

    public WebDriver getDriver(){

        if(!driverQueue.isEmpty()){
            return driverQueue.poll();
        }else{
            return new ChromeDriver();
        }
    }

    public void releaseDriver(WebDriver driver){

        if(driver != null){
            driverQueue.offer(driver);
            System.out.println(driver.hashCode());
        }
    }
}
