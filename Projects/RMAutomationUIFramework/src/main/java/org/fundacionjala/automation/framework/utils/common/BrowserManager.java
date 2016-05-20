package org.fundacionjala.automation.framework.utils.common;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class BrowserManager extends EventFiringWebDriver {

	public BrowserManager(WebDriver driverInput) {
			super(driver);
	}

	private static WebDriver driver;
	
	private static final String FireFox = "firefox";
	private static final String Chrome = "chrome";
	private static final String Explorer = "explorer";
	private static final String Safari = "safari";
	
	private static String Browser;
	
	public static void openBrowser() {
	   Browser = PropertiesReader.getBrowser();
       loadDriver(PropertiesReader.getBrowser());
       configureWindow();
	}
    
    private static void loadDriver(String browserName) {
    	if(driver == null) {
	    	switch (browserName) {
			case FireFox:
				driver = new FirefoxDriver();
				break;
	        case Safari:
	        	driver = new SafariDriver();
	        	break;
	        case Chrome:
	        	System.setProperty("webdriver.chrome.driver", PropertiesReader.getChromeDriverPath());
				driver = new ChromeDriver();
				break;
	        case Explorer:
	        	System.setProperty("webdriver.ie.driver", PropertiesReader.getIexploreDriverPath()); 	
				driver = new InternetExplorerDriver();
				break;
			default:
				driver = new FirefoxDriver();
				break;
			}
    	}
	}
	
	private static void configureWindow() {
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
	    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	    
	}

	public static void setUrl(String URL){
		driver.get(URL);
		if(Browser.equalsIgnoreCase(Explorer)){
			clickOnAcceptRisks();
		}
		
	}
   
	private static void clickOnAcceptRisks()
	{
		if(driver.getTitle().contains("Certificate")){
			  driver.findElement(By.id("overridelink")).click();
		}
	}
	
	public static WebDriver getDriver() {
		return driver;
	} 
	 
    private static final Thread CLOSE_THREAD = new Thread() {
        @Override
        public void run() {
            driver.close();
        }
    };
    
    static{
    Runtime.getRuntime().addShutdownHook(CLOSE_THREAD);
    }
    
   @Override
    public void close() {
        if (Thread.currentThread() != CLOSE_THREAD) {
            throw new UnsupportedOperationException("You shouldn't close this WebDriver. It's shared and will close after all scenarios.");
        }
   }

public static void normalResize() {
    WebElement html = driver
		.findElement(By.tagName("html"));
    html.sendKeys(Keys.chord(Keys.CONTROL, "0"));
    
}
	
}