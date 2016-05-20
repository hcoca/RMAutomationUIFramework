package org.fundacionjala.automation.framework.pages.admin.conferencerooms;

import org.fundacionjala.automation.framework.maps.admin.conferencerooms.OutOfOrderMap;
import org.fundacionjala.automation.framework.maps.admin.conferencerooms.RoomInfoMap;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.ExplicitWait;
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
    public static String timeBeginComplex;
    public static String timeEndComplex;

    public OutOfOrderPage() {
	PageFactory.initElements(BrowserManager.getDriver(), this);
    }

    @FindBy(xpath = OutOfOrderMap.FROM_FIELD)
    WebElement fromField;

    /**
     * This method stores the From time.
     * 
     * @return OutOfOrderPage
     */
    public OutOfOrderPage storeFromTime() {
	timeBegin = fromField.getAttribute("value");
	return this;
    }

    @FindBy(xpath = OutOfOrderMap.TO_FIELD)
    WebElement toField;

    /**
     * This method stores the To time.
     * 
     * @return OutOfOrderPage
     */
    public OutOfOrderPage storeToTime() {
	timeEnd = toField.getAttribute("value");
	return this;
    }

    @FindBy(xpath = OutOfOrderMap.FROM_FIELD_COMPLEX)
    WebElement fromFieldComplex;

    /**
     * This method stores the From complex time e.g. (Sat May 14 2016 13:00:00
     * GMT-0400 (SA Western Standard Time)).
     * 
     * @return OutOfOrderPage
     */
    public OutOfOrderPage storeFromTimeComplex() {
	timeBeginComplex = fromFieldComplex.getText();
	return this;
    }

    @FindBy(xpath = OutOfOrderMap.TO_FIELD_COMPLEX)
    WebElement toFieldComplex;

    /**
     * This method stores the To complex time e.g. (Sat May 14 2016 13:00:00
     * GMT-0400 (SA Western Standard Time)).
     * 
     * @return OutOfOrderPage
     */
    public OutOfOrderPage storeToTimeComplex() {
	timeEndComplex = toField.getText();
	return this;
    }

    @FindBy(xpath = "//table[@ng-focus]/tbody/tr[3]/td[1]/a")
    WebElement downTimeBeginButton;

    /**
     * This method performs a click on down arrow of From-Time.
     * 
     * @return OutOfOrderPage
     */
    public OutOfOrderPage setTimeBeginDown() {
	downTimeBeginButton.click();
	downTimeBeginButton.click();
	LogManager.info("The BeginTime has been updated");
	return this;
    }

    @FindBy(xpath = "//table[@ng-model='form.to.value']/tbody/tr[3]/td[1]/a")
    WebElement downTimeEndButton;

    /**
     * This method performs a click on down arrow of To-Time.
     * 
     * @return OutOfOrderPage
     */
    public OutOfOrderPage setTimeEndDown() {
	downTimeEndButton.click();
	downTimeEndButton.click();
	LogManager.info("The EndTime has been updated");
	return this;
    }

    @FindBy(xpath = "//table[@ng-focus]/tbody/tr[1]/td[1]/a")
    WebElement upTimeBeginButton;

    /**
     * This method performs a click on up arrow of From-Time.
     * 
     * @return OutOfOrderPage
     */
    public OutOfOrderPage setTimeBeginUp() {
	upTimeBeginButton.click();
	LogManager.info("The BeginTime has been updated");
	return this;
    }

    @FindBy(xpath = "//table[@ng-model='form.to.value']/tbody/tr[1]/td[1]/a")
    WebElement upTimeEndButton;

    /**
     * This method performs a click on down arrow of To-Time.
     * 
     * @return OutOfOrderPage
     */
    public OutOfOrderPage setTimeEndUp() {
	upTimeEndButton.click();
	LogManager.info("The EndTime has been updated");
	return this;
    }

    @FindBy(xpath = OutOfOrderMap.ACTIVE_BUTTON)
    WebElement activeButton;

    /**
     * This method performs a click on active OutOfOrder button.
     * 
     * @return OutOfOrderPage
     */
    public OutOfOrderPage activeOutOfOrder() {
	LogManager.info("The OufOfOrder has been activated - ActiveButton");
	By locator = By.xpath(OutOfOrderMap.ACTIVE_BUTTON);
	(new WebDriverWait(BrowserManager.getDriver(), 120))
		.until(ExpectedConditions.presenceOfElementLocated(locator));
	activeButton.click();
	return this;
    }

    @FindBy(css = OutOfOrderMap.BOX_BUTTON)
    WebElement buttonBox;

    /**
     * This method performs a click on arrow box button.
     * 
     * @return OutOfOrderPage
     */
    public OutOfOrderPage clickOnBoxButon() {
	ExplicitWait.clickWhenReady(By.cssSelector(OutOfOrderMap.BOX_BUTTON),
		30);
	LogManager.info("The BoxButton has been clicked for select the Title");
	return this;
    }

    @FindBy(linkText = OutOfOrderMap.CLOSED_FOR_MAINTENANCE_LINK)
    WebElement ClosedForMaintenanceLink;

    /**
     * This method performs a click on Closed For Maintenance Link.
     * 
     * @return OutOfOrderPage
     */
    public OutOfOrderPage ClickOnClosedForMaintenanceLink() {
	ExplicitWait.waitElementVisible(ClosedForMaintenanceLink, 30);
	ClosedForMaintenanceLink.click();
	LogManager.info("Has been selected a Title: Closed For Maintenance");
	return this;
    }

    @FindBy(xpath = OutOfOrderMap.SAVE_BUTTON)
    WebElement saveButton;

    /**
     * This method performs a click on save button.
     * 
     * @return ConferenceRoomsPage
     */
    public ConferenceRoomsPage clickOnSave() {
	int count = 0;
	do {
	    if (ExplicitWait.waitForElement(OutOfOrderMap.SAVE_BUTTON, 10)) {
		saveButton.click();
		LogManager
			.info("The changes on the OutOfOrder has been saved - SaveButton");
		count++;
		if (count == 3) {
		    UIActions.clickAt(BrowserManager.getDriver().findElement(
			    By.xpath(RoomInfoMap.CANCEL_BUTTON)));
		}
	    } else {
		count = 4;
	    }
	} while (count < 3);
	return new ConferenceRoomsPage();
    }

    @FindBy(xpath = OutOfOrderMap.SAVE_BUTTON)
    WebElement save;

    /**
     * This method performs a click on save button.
     * @return OutOfOrderPage
     * @throws InterruptedException 
     */
    public OutOfOrderPage clickOnSaveButton() throws InterruptedException {
	ExplicitWait.clickWhenReady(By.xpath(OutOfOrderMap.SAVE_BUTTON), 30);
	LogManager
		.info("The changes on the OutOfOrder has been saved - SaveButton");
	Thread.sleep(3000);
	WebElement cancel_button =ExplicitWait.getWhenVisible(
		By.xpath(RoomInfoMap.CANCEL_BUTTON), 10, false);
	if (cancel_button != null) {
	    LogManager
		.error("Out of order couldn't  be created. An error ocurred");
	    ExplicitWait.clickWhenReady(By.xpath(RoomInfoMap.CANCEL_BUTTON), 10);
	}
	return this;
    }

    @FindBy(xpath = RoomInfoMap.CANCEL_BUTTON)
    WebElement cancelButton;

    /**
     * This method perform click on cancel button
     * @return Conference Room Page instance.
     */
    public ConferenceRoomsPage clickOnCancelButton() {
	ExplicitWait.clickWhenReady(By.xpath(RoomInfoMap.CANCEL_BUTTON), 30);
	LogManager
		.info("The changes on the OutOfOrder has been canceled - CancelButton");
	return new ConferenceRoomsPage();
    }
    /**
     * This method performs a click on save button in order to verify error
     *  messages. Don't close the window.
     * @return OutOfOrderPage
     * @throws InterruptedException 
     */
    public OutOfOrderPage clickOnSaveButton(boolean state) throws InterruptedException {
	if (!state) {
	    ExplicitWait.clickWhenReady(By.xpath(OutOfOrderMap.SAVE_BUTTON), 30);
	    LogManager.info("Save button has been pressed");
	} else {
	   return clickOnSaveButton();
	}
	return this;
    }

}
