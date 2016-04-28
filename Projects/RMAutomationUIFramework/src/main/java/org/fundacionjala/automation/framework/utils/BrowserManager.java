package org.fundacionjala.automation.framework.utils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

public class BrowserManager {

	private WebDriver driver;
	private String browserName;
	
	private static final String FireFox = "firefox";
	private static final String Chrome = "chrome";
	private static final String Explorer = "explorer";
	private static final String Safari = "safari";
	private static final String UrlAdmin = "http://172.20.208.84:4040/admin/#/login";
	

	public BrowserManager(String browserName) {
       this.browserName = browserName;
	}

	public void openBrowser() {
       loadDriver();
       configureWindow();
       openRoomManager();
	}
    
    private void loadDriver() {
    	
    	switch (browserName) {
		case FireFox:
			driver = new FirefoxDriver();
			break;
        case Safari:
        	driver = new SafariDriver();
        	break;
        case Chrome:
        	System.setProperty("webdriver.chrome.driver", "./lib/chromedriver.exe");
			driver = new ChromeDriver();
			break;
        case Explorer:
        	System.setProperty("webdriver.ie.driver", "./lib/IEDriverServer.exe"); 	
			driver = new InternetExplorerDriver();
			break;
		default:
			driver = new FirefoxDriver();
			break;
		}
		
	}
	
	private void configureWindow() {
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
	    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	    
	}

	private void openRoomManager(){
       
		if (browserName.equalsIgnoreCase(Explorer)) {
			driver.get(UrlAdmin);
			clickOnAcceptRisks();
		} else {
			driver.get(UrlAdmin);
		}
		
	}
   
	private void clickOnAcceptRisks()
	{
		if(driver.getTitle().contains("Certificate")){
			  driver.findElement(By.id("overridelink")).click();
		}
	}
	
	

}
