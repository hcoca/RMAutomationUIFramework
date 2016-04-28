package org.fundacionjala.automation.framework.utils.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UIActions {
	
	public static void waitFor(String locatorString ) {
		By locator = By.xpath(locatorString);
		(new WebDriverWait(BrowserManager.getDriver(), 10))
		.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	public static void clickAt(WebElement element) {
		element.click();
		LogManager.info("'" + element.getText() + "' button has been clicked");
	}
}
