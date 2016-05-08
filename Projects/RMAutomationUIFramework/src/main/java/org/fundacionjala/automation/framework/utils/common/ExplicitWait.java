package org.fundacionjala.automation.framework.utils.common;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExplicitWait {

	public static void clickWhenReady(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(BrowserManager.getDriver(),
				timeout);

		try {
			WebElement element = wait.until(ExpectedConditions
					.elementToBeClickable(locator));
			element.click();
			LogManager.info("Element was clicked");

		} catch (TimeoutException e) {
			LogManager.info("Element waiting for clickable was not found");
		}
	}

	public static void waitForUrl(String url, int timeout) {

		
		WebDriverWait wait = new WebDriverWait(BrowserManager.getDriver(),
				timeout);
		try {
			wait.until(ExpectedConditions.urlToBe(url));	
		} catch (TimeoutException e) {
			LogManager.error("Error when get the url: " + e.getMessage());
		}
		
		
	}

	public static WebElement getWhenVisible(By locator, int timeout) {

		WebElement element = null;
		WebDriverWait wait = new WebDriverWait(BrowserManager.getDriver(),
				timeout);

		try {
			element = wait.until(ExpectedConditions
					.visibilityOfElementLocated(locator));
		} catch (NoSuchElementException e) {
			LogManager.error("Error when get an element" + e.getMessage());
		}
		return element;
	}

	public static List<WebElement> getElementsWhenVisible(By locator, int timeout) {
		List<WebElement> elements = null;
		WebDriverWait wait = new WebDriverWait(BrowserManager.getDriver(),
				timeout);

		try {
			elements = wait.until(ExpectedConditions
					.visibilityOfAllElementsLocatedBy(locator));
		} catch (NoSuchElementException e) {
			LogManager.error("Elements could not be found " + e.getMessage());
		}

		return elements;
	}

}
