package org.fundacionjala.automation.framework.pages.admin.conferencerooms;

import org.fundacionjala.automation.framework.maps.admin.conferencerooms.OutOfOrderMap;
import org.fundacionjala.automation.framework.maps.admin.emailserver.EmailServerMap;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.LogManager;

import org.fundacionjala.automation.framework.utils.common.UIActions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OutOfOrderPage {
	public static String timeBegin;
	public static String timeEnd;
	
	public OutOfOrderPage() {
		PageFactory.initElements(BrowserManager.getDriver(), this);
	}
	
	@FindBy (xpath = OutOfOrderMap.FROM_FIELD) WebElement fromField;
	public OutOfOrderPage storeFromTime() {
		timeBegin = fromField.getAttribute("value");
		return this;
	}
	 
	@FindBy (xpath = OutOfOrderMap.TO_FIELD) WebElement toField;
	public OutOfOrderPage storeToTime() {
		timeEnd = toField.getAttribute("value"); 
		return this;
	}

	@FindBy (xpath = "//table[@ng-focus]/tbody/tr[3]/td[1]/a") WebElement downTimeBeginButton;
	public OutOfOrderPage setTimeBeginDown() {
		downTimeBeginButton.click();
		downTimeBeginButton.click();
		LogManager.info("The BeginTime has been updated");
		return this;
	}	
	
	@FindBy (xpath = "//table[@ng-model='form.to.value']/tbody/tr[3]/td[1]/a") WebElement downTimeEndButton;
	public OutOfOrderPage setTimeEndDown() {
		downTimeEndButton.click();
		downTimeEndButton.click();
		LogManager.info("The EndTime has been updated");
		return this;
	}
	
	@FindBy (xpath = "//table[@ng-focus]/tbody/tr[1]/td[1]/a") WebElement upTimeBeginButton;
	public OutOfOrderPage setTimeBeginUp() {
		upTimeBeginButton.click();
		upTimeBeginButton.click();
		LogManager.info("The BeginTime has been updated");
		return this;
	}	
	
	@FindBy (xpath = "//table[@ng-model='form.to.value']/tbody/tr[1]/td[1]/a") WebElement upTimeEndButton;
	public OutOfOrderPage setTimeEndUp() {
		upTimeEndButton.click();
		upTimeEndButton.click();
		LogManager.info("The EndTime has been updated");
		return this;
	}
	
	@FindBy (xpath = OutOfOrderMap.ACTIVE_BUTTON) WebElement activeButton;
	public OutOfOrderPage activeOutOfOrder() {
		LogManager.info("The OufOfOrder has been activated - ActiveButton");
		By locator = By.xpath(OutOfOrderMap.ACTIVE_BUTTON);
		(new WebDriverWait(BrowserManager.getDriver(), 120))
		.until(ExpectedConditions.presenceOfElementLocated(locator));
		activeButton.click();
		return this;
	}
	
	@FindBy (css = OutOfOrderMap.BOX_BUTTON) WebElement buttonBox;
	public OutOfOrderPage clickOnBoxButon() {
		buttonBox.click();
		LogManager.info("The BoxButton has been clicked for select the Title");
		return this;
	}
	
	@FindBy (linkText = OutOfOrderMap.CLOSED_FOR_MAINTENANCE_LINK) WebElement ClosedForMaintenanceLink;
	public OutOfOrderPage ClickOnClosedForMaintenanceLink() {
		ClosedForMaintenanceLink.click();
		LogManager.info("Has been selected a Title: Closed For Maintenance");
		return this;
	}
	
	@FindBy (xpath = OutOfOrderMap.SAVE_BUTTON) WebElement saveButton;
	public ConferenceRoomsPage clickOnSave() {
		(new WebDriverWait(BrowserManager.getDriver(), 30)).until(ExpectedConditions.elementToBeClickable(saveButton));
		saveButton.click();
		LogManager.info("The changes on the OutOfOrder has been saved - SaveButton");
		return new ConferenceRoomsPage();
	}
}
