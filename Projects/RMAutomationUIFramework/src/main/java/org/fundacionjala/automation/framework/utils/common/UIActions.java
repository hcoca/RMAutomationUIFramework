package org.fundacionjala.automation.framework.utils.common;

import java.awt.AWTException;
import java.awt.Robot;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
/**
 * Common actions for using in selenium steps
 */
public class UIActions {

    /**
     * Wait for a xpath locator for 60 seconds
     * @param locatorString Xpath string
     */
    public static void waitFor(String locatorString) {
	By locator = By.xpath(locatorString);
	(new WebDriverWait(BrowserManager.getDriver(), 60))
		.until(ExpectedConditions
			.presenceOfAllElementsLocatedBy(locator));
    }

    /**
     * Wait for a xpath locator for a time in seconds
     * @param locatorString Xpath string
     * @param timeSeconds time
     */
    public static void waitFor(String locatorString, int timeSeconds) {
	By locator = By.xpath(locatorString);
	(new WebDriverWait(BrowserManager.getDriver(), timeSeconds))
		.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
    
    /**
     * Wait for an element, exec a click and log the step click
     * @param element To be clicked
     */
    public static void clickAt(WebElement element) {
	element.click();
	LogManager.info("Button has been clicked");
    }
    
    /**
     * Clear and Type on an input element
     * @param element To be clicked
     * @param message String text to be written
     */
    public static void typeOn(WebElement element, String message) {
	if (element != null) {
	    element.clear();
	    element.sendKeys(message);
	    LogManager.info("'" + message + "' has been set up in input text");
	}
    }
    
    /**
     * Double click by Java Script code raising an event
     * @param element to be clicked
     */
    public static void doubleClickJS(WebElement element) {
	JavascriptExecutor js = (JavascriptExecutor) BrowserManager.getDriver();
	js.executeScript(
		"var evt = document.createEvent('MouseEvents');"
			+ "evt.initMouseEvent('dblclick',true, true, window, 0,"
			+ " 0, 0, 0, 0, false, false, false, false, 0,null);"
			+ "arguments[0].dispatchEvent(evt);", element);
	LogManager
		.info("Element of the list has been selected for edit changes.");
    }
    
    /**
     * Double click by Selenium Actions
     * @param element to be clicked
     */
    public static void doubleClick(WebElement element) {
	Actions action = new Actions(BrowserManager.getDriver());
	action.doubleClick(element).build().perform();
	LogManager
		.info("Element of the list has been selected for edit changes.");
    }
    
    /**
     * Wait for element to be clickable for a time and click it
     * @param locator By object
     * @param timeout seconds
     */
    public static void clickWhenReady(By locator, int timeout) {
	WebDriverWait wait = new WebDriverWait(BrowserManager.getDriver(),
		timeout);

	try {
	    WebElement element = wait.until(ExpectedConditions
		    .elementToBeClickable(locator));
	    element.click();

	} catch (TimeoutException e) {
	    System.out.println("element to be clickable was not found");
	}
    }

    /**
     * This method is to expand and minimize time line.
     * first of all this go over time line then this expand or minimize
     * @param timelineCenter WebElement time line
     * @param size positive number to minimize and negative to maximize
     * @throws AWTException
     */
    public static void scrollTimeline(WebElement timelineCenter, int size) throws AWTException {
	Actions actions = new Actions(BrowserManager.getDriver());
	actions.moveToElement(timelineCenter);
	Robot robot = new Robot();
	robot.mouseMove(timelineCenter.getLocation().x,
	timelineCenter.getLocation().y);
	robot.mouseWheel(size);
    }
}