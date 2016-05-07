package org.fundacionjala.automation.framework.pages.admin.conferencerooms;

import org.fundacionjala.automation.framework.maps.admin.conferencerooms.OutOfOrderMap;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OutOfOrderPage {
	
	public OutOfOrderPage() {
		PageFactory.initElements(BrowserManager.getDriver(), this);
	}
	
	@FindBy (xpath = OutOfOrderMap.FROM_FIELD) WebElement fromField;
	public OutOfOrderPage insertFromValue(String from) {
		BrowserManager.getDriver().findElement(By.xpath(OutOfOrderMap.FROM_FIELD)).click();
		BrowserManager.getDriver().findElement(By.xpath(OutOfOrderMap.FROM_FIELD)).clear();
		BrowserManager.getDriver().findElement(By.xpath(OutOfOrderMap.FROM_FIELD)).sendKeys(from);
		return this;
	}
	 
	@FindBy (xpath = OutOfOrderMap.TO_FIELD) WebElement toField;
	public OutOfOrderPage insertToValue(String to) {
		BrowserManager.getDriver().findElement(By.xpath(OutOfOrderMap.TO_FIELD)).click();
		BrowserManager.getDriver().findElement(By.xpath(OutOfOrderMap.TO_FIELD)).clear();
		BrowserManager.getDriver().findElement(By.xpath(OutOfOrderMap.TO_FIELD)).sendKeys(to);
		return this;
	}

	@FindBy (xpath = "//table[@ng-focus]/tbody/tr[3]/td[1]/a") WebElement downTimeBeginButton;
	public OutOfOrderPage setTimeBeginDown() {
		downTimeBeginButton.click();
		downTimeBeginButton.click();
		return this;
	}	
	
	@FindBy (xpath = "//table[@ng-model='form.to.value']/tbody/tr[3]/td[1]/a") WebElement downTimeEndButton;
	public OutOfOrderPage setTimeEndDown() {
		downTimeEndButton.click();
		downTimeEndButton.click();
		return this;
	}
	
	@FindBy (xpath = OutOfOrderMap.ACTIVE_BUTTON) WebElement activeButton;
	public OutOfOrderPage activeOutOfOrder() {
		BrowserManager.getDriver().findElement(By.xpath(OutOfOrderMap.ACTIVE_BUTTON)).click();
		return this;
	}
	
	@FindBy (css = OutOfOrderMap.BOX_BUTTON) WebElement buttonBox;
	public OutOfOrderPage clickOnBoxButon() {
		buttonBox.click();
		return this;
	}
	
	@FindBy (linkText = OutOfOrderMap.CLOSED_FOR_MAINTENANCE_LINK) WebElement ClosedForMaintenanceLink;
	public OutOfOrderPage ClickOnClosedForMaintenanceLink() {
		ClosedForMaintenanceLink.click();
		return this;
	}
	
	@FindBy (xpath = OutOfOrderMap.SAVE_BUTTON) WebElement saveButton;
	public ConferenceRoomsPage clickOnSave() {
		(new WebDriverWait(BrowserManager.getDriver(), 30)).until(ExpectedConditions.visibilityOf(saveButton));
		saveButton.click();
		return new ConferenceRoomsPage();
	}
}
