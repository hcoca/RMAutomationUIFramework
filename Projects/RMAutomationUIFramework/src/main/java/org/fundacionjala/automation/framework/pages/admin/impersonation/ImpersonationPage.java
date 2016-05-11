package org.fundacionjala.automation.framework.pages.admin.impersonation;

import org.fundacionjala.automation.framework.maps.admin.impersonation.ImpersonationMap;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ImpersonationPage {
	
	@FindBy (xpath = ImpersonationMap.ACCOUNT_TEXT_FIELD) WebElement accountTextField;
	@FindBy (xpath = ImpersonationMap.USE_IMPERSONATION_CHECK_BOX) WebElement useImpersonationCheckBox;
	@FindBy (xpath = ImpersonationMap.USER_AND_PASSWORD_RADIO_BUTTON) WebElement userAndPasswordRadioButton;
	@FindBy (xpath = ImpersonationMap.RFID_RADIO_BUTTON) WebElement RFIDRadioButton;
	@FindBy (xpath = ImpersonationMap.SAVE_BUTTON) WebElement saveButton;
	@FindBy (xpath = ImpersonationMap.IMPERSONATION_MESSAGE) WebElement impersonationMessage;
	
	public ImpersonationPage() {
		PageFactory.initElements(BrowserManager.getDriver(), this);
	}
	
	public ImpersonationPage clickOnUseImpersonationCheckBox() {
		(new WebDriverWait(BrowserManager.getDriver(), 30)).until(ExpectedConditions.elementToBeClickable(useImpersonationCheckBox));
		Actions action = new Actions(BrowserManager.getDriver());
		action.click(useImpersonationCheckBox).perform();
		
		LogManager.info("Impersonation CheckBox has been clicked");
		
		return this;
	}
	
	public ImpersonationPage clickOnUserAndPasswordRadioButton() {
		(new WebDriverWait(BrowserManager.getDriver(), 30)).until(ExpectedConditions.elementToBeClickable(userAndPasswordRadioButton));
		Actions action = new Actions(BrowserManager.getDriver());
		action.click(userAndPasswordRadioButton).perform();
		
		LogManager.info("User and Password Radio Button has been clicked");
		
		return this;
	}
	
	public ImpersonationPage clickOnRFIDRadioButton() {
		(new WebDriverWait(BrowserManager.getDriver(), 30)).until(ExpectedConditions.elementToBeClickable(RFIDRadioButton));
		Actions action = new Actions(BrowserManager.getDriver());
		action.click(RFIDRadioButton).perform();
		
		LogManager.info("RFID Radio Button has been clicked");
		
		return this;
	}
	
	public ImpersonationPage clickOnSaveButton() {
		(new WebDriverWait(BrowserManager.getDriver(), 30)).until(ExpectedConditions.elementToBeClickable(saveButton));
		saveButton.click();

		LogManager.info("Save Button has been clicked");
		
		return this;
	}
	
	public String getAccountUserName() {
		String userName = accountTextField.getAttribute("value");
		
		LogManager.info("User Name " + userName + " has been obtained");
		
		return userName;
	}
	
	public ImpersonationPage waitForImpersonationMessageDisappear() {
		(new WebDriverWait(BrowserManager.getDriver(), 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath(ImpersonationMap.IMPERSONATION_MESSAGE)));
		String message = impersonationMessage.getText();
		
		LogManager.info("<Impersonation Message:> " + message + " <has disappeared>");
		
		return this;
	}
	
	public boolean findSaveButton() {
		try{
			(new WebDriverWait(BrowserManager.getDriver(), 15)).until(ExpectedConditions.visibilityOf(saveButton));
			LogManager.info("Save Button has been found");
			return true;
			
		}catch(Exception e){
			LogManager.info("Save Button has not been found");
			return false;
		}
	}
}
