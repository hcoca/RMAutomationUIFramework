package org.fundacionjala.automation.framework.pages.admin.emailserver;

import java.util.List;
import org.fundacionjala.automation.framework.maps.admin.conferencerooms.ConferenceRoomsMap;
import org.fundacionjala.automation.framework.maps.admin.emailserver.DeleteEmailServerMap;
import org.fundacionjala.automation.framework.maps.admin.emailserver.EmailServerMap;
import org.fundacionjala.automation.framework.pages.admin.home.AdminPage;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.LogManager;
import org.fundacionjala.automation.framework.utils.common.UIActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EmailServerPage extends AdminPage {
	@FindBy (xpath = EmailServerMap.ADD_BUTTON) WebElement addButton;
	@FindBy (xpath = EmailServerMap.REMOVE_BUTTON) WebElement removeButton;
	@FindBy (xpath = EmailServerMap.EMAILSERVER_ITEM) WebElement emailserver_item;
	@FindBy (xpath = EmailServerMap.EDIT_BUTTON) WebElement edit_button;
	@FindBy (xpath = EmailServerMap.USERNAME_CRED_INPUT) WebElement username_cred_input;
	@FindBy (xpath = EmailServerMap.PASSWORD_CRED_INPUT) WebElement pwd_cred_input;
	@FindBy (xpath = EmailServerMap.ACCEPT_BUTTON) WebElement accept_button;
	
	public EmailServerPage()
	{
		PageFactory.initElements(BrowserManager.getDriver(), this);
	}
	public AddEmailServerPage clickOnAddButton() {
		UIActions.waitFor(EmailServerMap.ADD_BUTTON);
		UIActions.clickAt(addButton);
		LogManager.info("Click on Add button");
		return new AddEmailServerPage();
	}

	public void waitAddWindowInvisible()
	{
		By locator = By.xpath(EmailServerMap.EMAILSERVER_ITEM);
		(new WebDriverWait(BrowserManager.getDriver(), 60))
		.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	public EmailServerPage clickOnServerItemButton() {
		UIActions.waitFor(EmailServerMap.EMAILSERVER_ITEM);
		UIActions.clickAt(emailserver_item);
		LogManager.info("Click on Email Server item");
		return this;
	}
	public EmailServerPage clickOnEditCredentialButton() {
		UIActions.waitFor(EmailServerMap.EDIT_BUTTON);
		UIActions.clickAt(edit_button);
		LogManager.info("Click on Edit Credential button");
		return this;
	}
	public EmailServerPage setUserName(String username) {
		UIActions.waitFor(EmailServerMap.USERNAME_CRED_INPUT);
		UIActions.typeOn(username_cred_input, username);
		return this;
	}
	public EmailServerPage setPassword(String password) {
		UIActions.waitFor(EmailServerMap.PASSWORD_CRED_INPUT);
		UIActions.typeOn(pwd_cred_input, password);
		return this;
	}
	public EmailServerPage clickOnAcceptButton() {
		UIActions.waitFor(EmailServerMap.ACCEPT_BUTTON);
		accept_button.click();
		LogManager.info("Click on Accept button");
		return this;
	}
	public void waitProcessing() {
		LogManager.info("Waiting up to save");
		(new WebDriverWait(BrowserManager.getDriver(), 60))
		.until(new ExpectedCondition() {
			@Override
			public Boolean apply(Object obj) {
				WebDriver driver = (WebDriver)obj;
				List<WebElement> elementsHide = (List<WebElement>)driver.findElements(By.xpath(EmailServerMap.END_PROCESSING));
				return (elementsHide.size()>0);
			}
		});
		
		
	}

	public boolean verifyCredential(String current_credential, String expected_credential) {
		boolean state = (current_credential.compareTo(expected_credential) == 0);
		if (state)
			LogManager.info("[PASSED]");
		else
			LogManager.error("[FAILED] - Expected credential:" + expected_credential + ", but current credential is :" + current_credential);
		return state;
	}

	
	public boolean findEmailServer() {
		try{
			(new WebDriverWait(BrowserManager.getDriver(), 30)).until(ExpectedConditions.visibilityOf(addButton));
			LogManager.info("Email Server has been found");
			
			return true;
			
		}catch(Exception e){
			
			LogManager.info("Email Server has not been found");
			return false;
		}
	}

	public DeleteEmailServerPage clickOnRemoveButton() {
		UIActions.waitFor(EmailServerMap.REMOVE_BUTTON);
		removeButton.click();
		LogManager.info("Click on Remove button");
		return new DeleteEmailServerPage();
	}
	
	public void waitItemDeleted() {
		(new WebDriverWait(BrowserManager.getDriver(), 30))
		.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(DeleteEmailServerMap.YES_BUTTON)));
		
	}
	
	public boolean verifyIfThereAreRooms() {
		List<WebElement> roomsList = BrowserManager.getDriver().findElements(By.xpath(ConferenceRoomsMap.ROOMS_ROWS));
		return (roomsList.size() > 0);

	}

}
