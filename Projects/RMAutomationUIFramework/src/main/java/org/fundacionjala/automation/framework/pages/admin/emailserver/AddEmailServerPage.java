package org.fundacionjala.automation.framework.pages.admin.emailserver;

import org.fundacionjala.automation.framework.maps.admin.emailserver.AddEmailServerMap;
import org.fundacionjala.automation.framework.maps.admin.resource.AddResourceMap;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.LogManager;
import org.fundacionjala.automation.framework.utils.common.UIActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class AddEmailServerPage {
	@FindBy (xpath = AddEmailServerMap.TITLE) WebElement title;
	@FindBy (xpath = AddEmailServerMap.HOSTNAME_INPUT) WebElement hostname_input;
	@FindBy (xpath = AddEmailServerMap.USERNAME_INPUT) WebElement username_input;
	@FindBy (xpath = AddEmailServerMap.PASSWORD_INPUT) WebElement password_input;
	@FindBy (xpath = AddEmailServerMap.DESCRIPTION_INPUT) WebElement description_txt;
	@FindBy (xpath = AddEmailServerMap.SAVE_BUTTON) WebElement save_button;
	
	public AddEmailServerPage()
	{
		PageFactory.initElements(BrowserManager.getDriver(), this);
	}

	public AddEmailServerPage setHostName(String hostname) {
		By locator = By.xpath(AddEmailServerMap.HOSTNAME_INPUT);
		(new WebDriverWait(BrowserManager.getDriver(), 10))
		.until(ExpectedConditions.presenceOfElementLocated(locator));
		hostname_input.clear();
		hostname_input.sendKeys(hostname);
		LogManager.info("'" + hostname + " has been set up");
		return this;
	}

	public AddEmailServerPage setUserName(String username) {
		By locator = By.xpath(AddEmailServerMap.USERNAME_INPUT);
		(new WebDriverWait(BrowserManager.getDriver(), 10))
		.until(ExpectedConditions.presenceOfElementLocated(locator));
		username_input.clear();
		username_input.sendKeys(username);
		LogManager.info("'" + username + " has been set up");
		return this;
	}

	public AddEmailServerPage setPassword(String password) {
		UIActions.waitFor(AddEmailServerMap.PASSWORD_INPUT);
		UIActions.typeOn(password_input, password);
		return this;
	}

	public EmailServerPage clickSaveButton() {
		UIActions.waitFor(AddEmailServerMap.SAVE_BUTTON);
		UIActions.clickAt(save_button);
		return new EmailServerPage();
	}

}
