package org.fundacionjala.automation.framework.pages.admin.impersonation;

import org.fundacionjala.automation.framework.maps.admin.impersonation.ImpersonationMap;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.LogManager;
import org.fundacionjala.automation.framework.utils.common.UIActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ImpersonationPage {
	
	@FindBy (xpath = ImpersonationMap.ACCOUNT_TEXT_FIELD) WebElement accountTextField;
	@FindBy (xpath = ImpersonationMap.USE_IMPERSONATION_CHECK_BOX) WebElement useImpersonationCheckBox;
	@FindBy (xpath = ImpersonationMap.USER_AND_PASSWORD_RADIO_BUTTON) WebElement userAndPasswordRadioButton;
	@FindBy (xpath = ImpersonationMap.RFID_RADIO_BUTTON) WebElement RFIDRadioButton;
	@FindBy (xpath = ImpersonationMap.SAVE_BUTTON) WebElement saveButton;
	
	public ImpersonationPage() {
		PageFactory.initElements(BrowserManager.getDriver(), this);
	}
	
	public ImpersonationPage clickOnUseImpersonationCheckBox() {
		UIActions.waitFor(ImpersonationMap.USE_IMPERSONATION_CHECK_BOX);
		useImpersonationCheckBox.click();
		
		LogManager.info("Impersonation CheckBox has been clicked");
		
		return this;
	}
	
	public ImpersonationPage clickOnUserAndPasswordRadioButton() {
		UIActions.waitFor(ImpersonationMap.USER_AND_PASSWORD_RADIO_BUTTON);
		userAndPasswordRadioButton.click();
		
		LogManager.info("User and Password Radio Button has been clicked");
		
		return this;
	}
	
	public ImpersonationPage clickOnRFIDRadioButton() {
		UIActions.waitFor(ImpersonationMap.RFID_RADIO_BUTTON);
		RFIDRadioButton.click();
		
		LogManager.info("RFID Radio Button has been clicked");
		
		return this;
	}
	
	public ImpersonationPage clickOnSaveButton() {
		UIActions.waitFor(ImpersonationMap.SAVE_BUTTON);
		saveButton.click();
		
		LogManager.info("Save Button has been clicked");
		
		return this;
	}
	
	public String getAccountUserName() {
		UIActions.waitFor(ImpersonationMap.ACCOUNT_TEXT_FIELD);
		String userName = accountTextField.getAttribute("value");
		
		LogManager.info("User Name " + userName + " has been obtained");
		
		return userName;
	}
}
