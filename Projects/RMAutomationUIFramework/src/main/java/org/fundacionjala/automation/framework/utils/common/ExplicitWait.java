package org.fundacionjala.automation.framework.utils.common;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
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
	} catch (TimeoutException e) {
	    LogManager.error("Error when get an element" + e.getMessage());
	}
	return element;
    }

    public static WebElement getWhenVisible(By locator, int timeout,
	    boolean loggeable) {

	WebElement element = null;
	WebDriverWait wait = new WebDriverWait(BrowserManager.getDriver(),
		timeout);

	try {
	    element = wait.until(ExpectedConditions
		    .visibilityOfElementLocated(locator));
	} catch (TimeoutException e) {
	    if (loggeable) {
		LogManager.error("Error when get an element" + e.getMessage());
	    }
	}
	return element;
    }
    
    public static boolean waitForElement(String locator, int timeout) {
	WebDriverWait wait = new WebDriverWait(BrowserManager.getDriver(),
		timeout);
	try{
		wait.until(ExpectedConditions.presenceOfElementLocated(
			By.xpath(locator)));
		return true;
	}catch (Exception e){
		return false;
	}
}

    public static void waitElementVisible(WebElement element, int timeout) {

	WebDriverWait wait = new WebDriverWait(BrowserManager.getDriver(),
		timeout);

	try {
	    wait.until(ExpectedConditions.visibilityOf(element));
	} catch (TimeoutException e) {
	    LogManager.error("Error when get an element" + e.getMessage());
	}

    }

    public static List<WebElement> getElementsWhenVisible(By locator,
	    int timeout) {
	List<WebElement> elements = null;
	WebDriverWait wait = new WebDriverWait(BrowserManager.getDriver(),
		timeout);

	try {
	    elements = wait.until(ExpectedConditions
		    .visibilityOfAllElementsLocatedBy(locator));
	} catch (TimeoutException e) {
	    LogManager.error("Elements could not be found " + e.getMessage());
	}

	return elements;
    }

    public static void waitFluentElement(final By locator,
	    final int timeoutSeconds) {
	final WebDriver driver = BrowserManager.getDriver();

	FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
		.withTimeout(timeoutSeconds, TimeUnit.SECONDS)
		.pollingEvery(1, TimeUnit.SECONDS)
		.ignoring(NoSuchElementException.class);

	wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public static boolean isElementInvisible(By locator, int timeout) {

	WebDriverWait wait = new WebDriverWait(BrowserManager.getDriver(),
		timeout);

	return wait.until(ExpectedConditions
		.invisibilityOfElementLocated(locator));

    }
    
   
     
     
    
     
    


}
