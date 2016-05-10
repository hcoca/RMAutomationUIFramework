package org.fundacionjala.automation.framework.pages.admin.emailserver;

import org.fundacionjala.automation.framework.maps.admin.emailserver.AddEmailServerMap;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class AddEmailServerPage {
	
	@FindBy (xpath = AddEmailServerMap.DOMAIN_SERVER_TEXT_FIELD) WebElement domainServerTextField;
	@FindBy (xpath = AddEmailServerMap.USERNAME_TEXT_FIELD) WebElement userNameTextField;
	@FindBy (xpath = AddEmailServerMap.PASSWORD_TEXT_FIELD) WebElement passwordTextField;
	@FindBy (xpath = AddEmailServerMap.DESCRIPTION_TEXT_FIELD) WebElement descriptionTextField;
	@FindBy (xpath = AddEmailServerMap.SAVE_BUTTON) WebElement saveButton;
	
	public AddEmailServerPage()	{
		PageFactory.initElements(BrowserManager.getDriver(), this);
	}

	public AddEmailServerPage setDomainServer(String domainServer) {
		(new WebDriverWait(BrowserManager.getDriver(), 30)).until(ExpectedConditions.visibilityOf(domainServerTextField));
		domainServerTextField.clear();
		domainServerTextField.sendKeys(domainServer);
		
		LogManager.info("Domain Server " + domainServer + " has been set up");
		
		return this;
	}

	public AddEmailServerPage setUserName(String userName) {
		(new WebDriverWait(BrowserManager.getDriver(), 30)).until(ExpectedConditions.visibilityOf(userNameTextField));
		userNameTextField.clear();
		userNameTextField.sendKeys(userName);
		
		LogManager.info("Domain User Name " + userName + " has been set up");
		
		return this;
	}

	public AddEmailServerPage setPassword(String password) {
		(new WebDriverWait(BrowserManager.getDriver(), 30)).until(ExpectedConditions.visibilityOf(passwordTextField));
		passwordTextField.clear();
		passwordTextField.sendKeys(password);
		
		LogManager.info("Domain Password " + password + "has been set up");
		
		return this;
	}
	
	public AddEmailServerPage setDescription(String description) {
		(new WebDriverWait(BrowserManager.getDriver(), 30)).until(ExpectedConditions.visibilityOf(descriptionTextField));
		descriptionTextField.clear();
		descriptionTextField.sendKeys(description);
		
		LogManager.info("Email Server Description " + description + "has been set up");
		
		return this;
	}

	public EmailServerPage clickSaveButton() {
		(new WebDriverWait(BrowserManager.getDriver(), 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath(AddEmailServerMap.SAVE_BUTTON)));
		saveButton.click();
		(new WebDriverWait(BrowserManager.getDriver(), 30)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(AddEmailServerMap.SAVE_BUTTON)));
		
		LogManager.info("Add Email Server Save Button has been clicked");
		
		return new EmailServerPage();
	}
}
