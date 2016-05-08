package org.fundacionjala.automation.framework.utils.common;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UIActions {
	
	public static void waitFor(String locatorString ) {
		By locator = By.xpath(locatorString);
		(new WebDriverWait(BrowserManager.getDriver(),20))
		.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}
	
	public static void clickAt(WebElement element) {
		element.click();
		LogManager.info("Button has been clicked");
	}
	
	public static void typeOn(WebElement element, String message) {
		element.clear();
		element.sendKeys(message);
		LogManager.info("'" + message + "' has been set up in input text");
	}

	public static void doubleClick(WebElement element) {
		Actions action = new Actions(BrowserManager.getDriver());
		 action.doubleClick(element).build().perform();
		 LogManager.info("Element of the list has been selected for edit changes.");
	}

	public static void clickWhenReady(By locator, int timeout) {
      WebDriverWait wait = new WebDriverWait(BrowserManager.getDriver(), timeout);
	    
	    try {
	    	WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		    element.click();

			System.out.println("the button has been clicked");
			System.out.println("the button has been clicked");
			
		} catch (TimeoutException e) {
			System.out.println("element to be clickable was not found");
		}
	}
}