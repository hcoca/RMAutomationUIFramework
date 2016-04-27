package org.fundacionjala.automation.actions;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import config.ConfigurationAdmin;

public class CommonAction {
	public static WebDriver driver;
	private static String baseUrl;
	 
    public static void openBrowser(String browser){
    	if(browser.equalsIgnoreCase(ConfigurationAdmin.firefox)){
    		driver = new FirefoxDriver();
    	}else{
    		System.setProperty(ConfigurationAdmin.driverChrome,ConfigurationAdmin.pathChrome);
    		driver = new ChromeDriver();
    	}
		baseUrl = ConfigurationAdmin.baseUrl;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
 
	public static void navigate(){
		driver.get(baseUrl);
		}
	
	public static void closeBrowser(){
		driver.quit();
	}
}
