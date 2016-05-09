package org.fundacionjala.automation.framework.pages.admin.emailserver;

import org.fundacionjala.automation.framework.maps.admin.emailserver.DeleteEmailServerMap;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class DeleteEmailServerPage {
	
	@FindBy (xpath = DeleteEmailServerMap.YES_BUTTON) WebElement yesButton;
	@FindBy (xpath = DeleteEmailServerMap.NO_BUTTON) WebElement noButton;
	
	public DeleteEmailServerPage() {
		PageFactory.initElements(BrowserManager.getDriver(), this);
	}
	
	public EmailServerPage clickOnYesButton() {
		(new WebDriverWait(BrowserManager.getDriver(), 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath(DeleteEmailServerMap.YES_BUTTON)));
		yesButton.click();
		(new WebDriverWait(BrowserManager.getDriver(), 30)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(DeleteEmailServerMap.YES_BUTTON)));
		
		LogManager.info("Remove Email Server Yes Button has been clicked");
		
		return new EmailServerPage();
	}
	
	public EmailServerPage clickNoButton() {
		(new WebDriverWait(BrowserManager.getDriver(), 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath(DeleteEmailServerMap.NO_BUTTON)));
		noButton.click();
		(new WebDriverWait(BrowserManager.getDriver(), 30)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(DeleteEmailServerMap.NO_BUTTON)));
		
		LogManager.info("Remove Email Server No Button has been clicked");
		
		return new EmailServerPage();
	}
}
