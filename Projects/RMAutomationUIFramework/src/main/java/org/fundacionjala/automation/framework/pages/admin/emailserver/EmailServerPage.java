package org.fundacionjala.automation.framework.pages.admin.emailserver;

import java.util.List;

import org.fundacionjala.automation.framework.maps.admin.conferencerooms.ConferenceRoomsMap;
import org.fundacionjala.automation.framework.maps.admin.emailserver.DeleteEmailServerMap;
import org.fundacionjala.automation.framework.maps.admin.emailserver.EmailServerMap;
import org.fundacionjala.automation.framework.pages.admin.home.AdminPage;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EmailServerPage extends AdminPage {
	@FindBy (xpath = EmailServerMap.ADD_BUTTON) WebElement addButton;
	@FindBy (xpath = EmailServerMap.REMOVE_BUTTON) WebElement removeButton;
	@FindBy (xpath = EmailServerMap.EMAIL_SERVER_BUTTON) WebElement emailServerButton;
	@FindBy (xpath = EmailServerMap.EDIT_BUTTON) WebElement editButton;
	@FindBy (xpath = EmailServerMap.USERNAME_TEXT_FIELD) WebElement userNameTextField;
	@FindBy (xpath = EmailServerMap.PASSWORD_TEXT_FIELD) WebElement passwordTextField;
	@FindBy (xpath = EmailServerMap.ACCEPT_BUTTON) WebElement acceptButton;
	@FindBy (xpath = EmailServerMap.DESCRIPTION_TEXT_FIELD) WebElement descriptionTextField;
	
	public EmailServerPage()
	{
		PageFactory.initElements(BrowserManager.getDriver(), this);
	}
	
	public AddEmailServerPage clickOnAddButton() {
		(new WebDriverWait(BrowserManager.getDriver(), 30)).until(ExpectedConditions.visibilityOf(addButton));
		addButton.click();
		
		LogManager.info("Add Email Server Button has been clicked");
		
		return new AddEmailServerPage();
	}
	
	public DeleteEmailServerPage clickOnRemoveButton() {
		(new WebDriverWait(BrowserManager.getDriver(), 30)).until(ExpectedConditions.elementToBeClickable(removeButton));
		removeButton.click();
		
		LogManager.info("Remove Email Server Button has been clicked");
		
		return new DeleteEmailServerPage();
	}
	
	public EmailServerPage clickOnServerItemButton() {
		(new WebDriverWait(BrowserManager.getDriver(), 30)).until(ExpectedConditions.elementToBeClickable(emailServerButton));
		emailServerButton.click();
		
		LogManager.info("Email Server Button has been clicked");
		
		return this;
	}
	
	public EmailServerPage clickOnEditCredentialButton() {
		(new WebDriverWait(BrowserManager.getDriver(), 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath(EmailServerMap.EDIT_BUTTON)));
		editButton.click();
		(new WebDriverWait(BrowserManager.getDriver(), 30)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(EmailServerMap.EDIT_BUTTON)));
		
		LogManager.info("Edit Email Server Credential Button has been clicked");
		
		return this;
	}
	
	public EmailServerPage setUserName(String userName) {
		(new WebDriverWait(BrowserManager.getDriver(), 30)).until(ExpectedConditions.visibilityOf(userNameTextField));
		userNameTextField.clear();
		userNameTextField.sendKeys(userName);
		
		LogManager.info("Domain User Name " + userName +  " has been set up");
		
		return this;
	}
	
	public EmailServerPage setPassword(String password) {
		(new WebDriverWait(BrowserManager.getDriver(), 30)).until(ExpectedConditions.visibilityOf(passwordTextField));
		passwordTextField.clear();
		passwordTextField.sendKeys(password);
		
		LogManager.info("Domain Password " + password + " has been set up");
		
		return this;
	}
	
	public EmailServerPage clickOnAcceptButton() {
		(new WebDriverWait(BrowserManager.getDriver(), 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath(EmailServerMap.ACCEPT_BUTTON)));
		acceptButton.click();
		(new WebDriverWait(BrowserManager.getDriver(), 30)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(EmailServerMap.ACCEPT_BUTTON)));
		
		LogManager.info("Edit Email Server Credential Accept Button has been clicked");
		
		return this;
	}

	public boolean verifyCredential(String current_credential, String expected_credential) {
		boolean state = (current_credential.compareTo(expected_credential) == 0);
		if (state)
			LogManager.info("[PASSED]");
		else
			LogManager.error("[FAILED] - Expected credential:" + expected_credential + ", but current credential is :" + current_credential);
		return state;
	}

	
	public boolean findAddButton() {
		try{
			(new WebDriverWait(BrowserManager.getDriver(), 30)).until(ExpectedConditions.visibilityOf(addButton));
			LogManager.info("Add Button has been found");
			
			return true;
			
		}catch(Exception e){
			
			LogManager.info("Add Button has not been found");
			return false;
		}
	}

	public void waitItemDeleted() {
		(new WebDriverWait(BrowserManager.getDriver(), 30))
		.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(DeleteEmailServerMap.YES_BUTTON)));
		
	}
	
	public boolean verifyIfThereAreRooms() {
		List<WebElement> roomsList = BrowserManager.getDriver().findElements(By.xpath(ConferenceRoomsMap.ROOMS_ROWS));
		return (roomsList.size() > 0);

	}
	
	public void waitAddWindowInvisible()
	{
		By locator = By.xpath(EmailServerMap.EMAIL_SERVER_BUTTON);
		(new WebDriverWait(BrowserManager.getDriver(), 60))
		.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	public String getUserName() {
		
		String userName = userNameTextField.getAttribute("value");
		
		LogManager.info("User Name " + userName + " has been obtained");
		
		return userName;
	}
	
	public String getEmailServerDescription() {
		String description = descriptionTextField.getAttribute("value");
		
		LogManager.info("Email Server Description " + description + " has been obtained");
		
		return description;
	}
}
