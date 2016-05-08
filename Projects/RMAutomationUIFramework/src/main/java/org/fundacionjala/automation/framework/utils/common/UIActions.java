package org.fundacionjala.automation.framework.utils.common;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UIActions {
	
	public static void waitFor(String locatorString ) {
		By locator = By.xpath(locatorString);
		(new WebDriverWait(BrowserManager.getDriver(),20))
		.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	public static void waitFor(String locatorString, int timeSeconds) {
		By locator = By.xpath(locatorString);
		(new WebDriverWait(BrowserManager.getDriver(),timeSeconds))
		.until(ExpectedConditions.presenceOfElementLocated(locator));
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
	public static void doubleClickJS(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) BrowserManager.getDriver();
		  js.executeScript("var evt = document.createEvent('MouseEvents');" +
	 		        "evt.initMouseEvent('dblclick',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null);" +
	 		        "arguments[0].dispatchEvent(evt);", element);
		 LogManager.info("Element of the list has been selected for edit changes.");
	}
	public static void doubleClick(WebElement element) {
		Actions action = new Actions(BrowserManager.getDriver());
		 action.doubleClick(element).build().perform();
		 LogManager.info("Element of the list has been selected for edit changes.");
	}
}