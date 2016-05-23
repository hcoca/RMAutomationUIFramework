package org.fundacionjala.automation.framework.pages.tablet.scheduler;

import org.fundacionjala.automation.framework.maps.tablet.scheduler.CredentialsMap;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.ExplicitWait;
import org.fundacionjala.automation.framework.utils.common.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CredentialsPage {

	@FindBy(xpath = CredentialsMap.CREATE_IN_BEHALF_OF_TEXT_FIELD)
	WebElement createInBehalfOfTextField;
	@FindBy(xpath = CredentialsMap.USER_NAME_TEXT_FIELD)
	WebElement userNameTextField;
	@FindBy(xpath = CredentialsMap.PASSWORD_TEXT_FIELD)
	WebElement passwordTextField;
	@FindBy(xpath = CredentialsMap.CREATE_AS_CHECKBOX)
	WebElement createAsCheckBox;
	@FindBy(xpath = CredentialsMap.OK_BUTTON)
	WebElement okButton;
	@FindBy(xpath = CredentialsMap.CANCEL_BUTTON)
	WebElement cancelButton;
	@FindBy(xpath = CredentialsMap.CANCEL_AS_CHECKBOX)
	WebElement cancelAsCheckBox;

	@FindBy(xpath = CredentialsMap.UPDATE_AS_CHECKBOX)
	WebElement updateAsCheckBox;

	public CredentialsPage() {
		PageFactory.initElements(BrowserManager.getDriver(), this);
	}

	public CredentialsPage setUserName(String userName) {
		(new WebDriverWait(BrowserManager.getDriver(), 30))
				.until(ExpectedConditions.visibilityOf(userNameTextField));
		userNameTextField.clear();
		userNameTextField.sendKeys(userName);

		LogManager.info("User Name " + userName + " has been set up");

		return this;
	}

	public CredentialsPage setPassword(String password) {
		(new WebDriverWait(BrowserManager.getDriver(), 30))
				.until(ExpectedConditions.visibilityOf(passwordTextField));
		passwordTextField.clear();
		passwordTextField.sendKeys(password);

		LogManager.info("Password " + password + " has been set up");

		return this;
	}

	public SchedulerPage clickOkButton() {
		(new WebDriverWait(BrowserManager.getDriver(), 30))
				.until(ExpectedConditions.elementToBeClickable(okButton));
		okButton.click();
		(new WebDriverWait(BrowserManager.getDriver(), 30))
				.until(ExpectedConditions.invisibilityOfElementLocated(By
						.xpath(CredentialsMap.OK_BUTTON)));

		LogManager.info("Ok Button has been clicked");

		return new SchedulerPage();
	}

	/**
	 * This method is to perform click on ok button for creating meeting
	 * 
	 * @return SchedulerPage instance
	 */
	public SchedulerPage clickOnOkButton() {
		(new WebDriverWait(BrowserManager.getDriver(), 30))
				.until(ExpectedConditions.elementToBeClickable(okButton));
		okButton.click();
		LogManager.info("Ok Button has been clicked");
		return new SchedulerPage();
	}

	public SchedulerPage clickCancelButton() {
		(new WebDriverWait(BrowserManager.getDriver(), 30))
				.until(ExpectedConditions.elementToBeClickable(cancelButton));
		cancelButton.click();
		(new WebDriverWait(BrowserManager.getDriver(), 30))
				.until(ExpectedConditions.invisibilityOfElementLocated(By
						.xpath(CredentialsMap.CANCEL_BUTTON)));

		LogManager.info("Cancel Button has been clicked");

		return new SchedulerPage();
	}

	public CredentialsPage clickCreateAsCheckBox() {
		(new WebDriverWait(BrowserManager.getDriver(), 30))
				.until(ExpectedConditions
						.elementToBeClickable(createAsCheckBox));
		createAsCheckBox.click();

		LogManager.info("Create As CheckBox has been clicked");

		return this;
	}

	public CredentialsPage clickCancelAsCheckBox() {
		(new WebDriverWait(BrowserManager.getDriver(), 30))
				.until(ExpectedConditions
						.elementToBeClickable(cancelAsCheckBox));
		cancelAsCheckBox.click();

		LogManager.info("Cancel As CheckBox has been clicked");

		return this;
	}

	public boolean isCreateInBehalfOfTextFieldPresent() {
		try {
			(new WebDriverWait(BrowserManager.getDriver(), 15))
					.until(ExpectedConditions
							.visibilityOf(createInBehalfOfTextField));
			LogManager.info("Create in Behalf of Text Field has been found");
			return true;

		} catch (Exception e) {
			LogManager
					.info("Create in Behalf of Text Field has not been found");
			return false;
		}
	}

	public boolean isCancelInBehalfOfMessagePresent() {
		try {
			(new WebDriverWait(BrowserManager.getDriver(), 15))
					.until(ExpectedConditions.presenceOfElementLocated(By
							.xpath(CredentialsMap.CANCEL_IN_BEHALF_OF_MESSAGE)));
			LogManager.info("Cancel in Behalf of Message has been found");
			return true;

		} catch (Exception e) {
			LogManager.info("Cancel in Behalf of Message has not been found");
			return false;
		}
	}

	public boolean isNoFunctionalityProvidedMessagePresent() {
		try {
			(new WebDriverWait(BrowserManager.getDriver(), 15))
					.until(ExpectedConditions.presenceOfElementLocated(By
							.xpath(CredentialsMap.NO_FUNCTIONALITY_PROVIDED_MESSAGE)));
			LogManager.info("No functionality provided Message has been found");
			return true;

		} catch (Exception e) {
			LogManager
					.info("No functionality provided Message has not been found");
			return false;
		}
	}

	public boolean isCreateAsCheckBoxPresent() {
		try {
			(new WebDriverWait(BrowserManager.getDriver(), 15))
					.until(ExpectedConditions.visibilityOf(createAsCheckBox));
			LogManager.info("Create As CheckBox has been found");
			return true;

		} catch (Exception e) {
			LogManager.info("Create As CheckBox has not been found");
			return false;
		}
	}

	public boolean isCancelAsCheckBoxPresent() {
		try {
			(new WebDriverWait(BrowserManager.getDriver(), 15))
					.until(ExpectedConditions.visibilityOf(cancelAsCheckBox));
			LogManager.info("Cancel As CheckBox has been found");
			return true;

		} catch (Exception e) {
			LogManager.info("Cancel As CheckBox has not been found");
			return false;
		}
	}

	public boolean isUserNameTextFieldPresent() {
		try {
			(new WebDriverWait(BrowserManager.getDriver(), 15))
					.until(ExpectedConditions.visibilityOf(userNameTextField));
			LogManager.info("User Name Text Field has been found");
			return true;

		} catch (Exception e) {
			LogManager.info("User Name Text Field has not been found");
			return false;
		}
	}

	public boolean isPasswordTextFieldPresent() {
		try {
			(new WebDriverWait(BrowserManager.getDriver(), 15))
					.until(ExpectedConditions.visibilityOf(userNameTextField));
			LogManager.info("Password Text Field has been found");
			return true;

		} catch (Exception e) {
			LogManager.info("Password Text Field has not been found");
			return false;
		}
	}

	/**
	 * this method is to verify if an error message is displayed.
	 * 
	 * @return true if message is displayed else false
	 */
	public boolean verifyErrorMessageOfCredentialsIsDisplayed() {
		return ExplicitWait.waitForElement(
				CredentialsMap.INVALID_CREDENTIALS_ERROR, 30) ? true : false;
	}

	/**
	 * This method is to uncheck the option update as
	 * 
	 * @return CredentialsPage
	 */
	public CredentialsPage clickUpdateAsCheckBox() {

		(new WebDriverWait(BrowserManager.getDriver(), 30))
				.until(ExpectedConditions
						.elementToBeClickable(updateAsCheckBox));
		updateAsCheckBox.click();

		LogManager.info("Update As CheckBox has been clicked");

		return this;
	}

	/**
	 * This function is to click on OK in credentials page and wait a message
	 * 
	 * @return Scheduler Page
	 */
	public SchedulerPage tryToClickOnOkButton() {

		int count = 1;
		WebElement element = null;
		(new WebDriverWait(BrowserManager.getDriver(), 30))
				.until(ExpectedConditions.elementToBeClickable(okButton));
		while (count <= 2) {
			okButton.click();
			LogManager.info("Ok Button has been clicked");
			element = ExplicitWait.getWhenVisible(
					By.xpath(CredentialsMap.MESSAGE_OF_UPDATE), 30);
			if (element != null) {
				count = 4;
			} else {
				count = count + 1;
			}
		}
		if (element != null) {
			LogManager.info("Ok Button has been clicked");
		} else {
			(new WebDriverWait(BrowserManager.getDriver(), 30))
					.until(ExpectedConditions
							.elementToBeClickable(cancelButton));
			cancelButton.click();
			LogManager.info("Cancel Button has been clicked");
		}
		return new SchedulerPage();
	}

	/**
	 * this method is to verify if an error message is displayed.
	 * 
	 * @return true if message is displayed else false
	 */
	public boolean verifyErrorMessageOfMeetingConflictIsDisplayed() {
		return ExplicitWait.waitForElement(
				CredentialsMap.MEETING_CONFLICT_ERROR, 30) ? true : false;
	}

}