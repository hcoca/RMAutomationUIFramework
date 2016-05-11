package org.fundacionjala.automation.framework.pages.tablet.scheduler;

import org.fundacionjala.automation.framework.maps.tablet.scheduler.CredentialsMap;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.LogManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CredentialsPage {

	@FindBy (xpath = CredentialsMap.CREATE_IN_BEHALF_OF_TEXT_FIELD) WebElement createInBehalfOfTextField;
	@FindBy (xpath = CredentialsMap.USER_NAME_TEXT_FIELD) WebElement userNameTextField;
	@FindBy (xpath = CredentialsMap.PASSWORD_TEXT_FIELD) WebElement passwordTextField;
	@FindBy (xpath = CredentialsMap.CREATE_AS_CHECKBOX) WebElement createAsCheckBox;
	@FindBy (xpath = CredentialsMap.OK_BUTTON) WebElement okButton;
	
	public CredentialsPage() {
		PageFactory.initElements(BrowserManager.getDriver(), this);
	}
	
	public boolean isCreateInBehalfOfTextFieldPresent() {
		try{
			(new WebDriverWait(BrowserManager.getDriver(), 15)).until(ExpectedConditions.visibilityOf(createInBehalfOfTextField));
			LogManager.info("Create in Behalf of Text Field has been found");
			return true;
			
		}catch(Exception e) {
			LogManager.info("Create in Behalf of Text Field has not been found");
			return false;
		}
	}
	
	public boolean isCreateAsCheckBoxPresent() {
		try{
			(new WebDriverWait(BrowserManager.getDriver(), 15)).until(ExpectedConditions.visibilityOf(createAsCheckBox));
			LogManager.info("Create As CheckBox has been found");
			return true;
			
		}catch(Exception e) {
			LogManager.info("Create As CheckBox has not been found");
			return false;
		}
	}
}