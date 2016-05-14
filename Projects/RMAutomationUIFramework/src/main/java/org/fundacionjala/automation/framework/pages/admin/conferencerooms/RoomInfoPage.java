package org.fundacionjala.automation.framework.pages.admin.conferencerooms;

import java.util.List;

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

public class RoomInfoPage {

    public RoomInfoPage() {
	
	PageFactory.initElements(BrowserManager.getDriver(), this);
    }

    @FindBy(linkText = RoomInfoMap.RESOURCES_ASSOCIATIONS_LINK)
    WebElement resourcesAssociations;

    /**
     * This method perform a click on the resource association tab
     * 
     * @return a new instance of RoomsResourceAssociationsPage
     */
    public RoomsResourceAssociationsPage clickOnResourceAssociations() {
	ExplicitWait.getWhenVisible(By.linkText(RoomInfoMap.RESOURCES_ASSOCIATIONS_LINK), 30);
	resourcesAssociations.click();
	return new RoomsResourceAssociationsPage();
    }

    @FindBy(linkText = RoomInfoMap.OUT_OF_ORDER_PLANNING_LINK)
    WebElement outOfOrder;

    /**
     * this method perform a click on out of order button
     * @return OutOfOrderPage instance
     */
    public OutOfOrderPage clickOnOutOfOrder() {
	
	(new WebDriverWait(BrowserManager.getDriver(), 30))
		.until(ExpectedConditions.visibilityOf(outOfOrder));
	outOfOrder.click();
	LogManager.info("OutOfOrder-ConferenceRooms Button has been clicked");
	return new OutOfOrderPage();
    }

    @FindBy(xpath = RoomInfoMap.OUT_OF_ORDER_LINK)
    WebElement outOfOrderOption;

    /**
     *  this method select a click on out of order button
     * @return OutOfOrderPage instance
     */
    public OutOfOrderPage SelectOutOfOrder() {
	
	UIActions.waitFor(RoomInfoMap.OUT_OF_ORDER_LINK);
	UIActions.clickAt(outOfOrderOption);
	LogManager.info("Click on Out of order Planning");
	return new OutOfOrderPage();
    }

    @FindBy(xpath = RoomInfoMap.DISPLAY_NAME_TEXTBOX)
    WebElement displayNameTextbox;

    /**
     * This Method type a word on display name text box
     * @param displayName
     * @return RoomInfoPage instance
     */
    public RoomInfoPage typeOnDisplayName(String displayName) {
	
	UIActions.waitFor(RoomInfoMap.DISPLAY_NAME_TEXTBOX);
	UIActions.typeOn(displayNameTextbox, displayName);
	return this;
    }

    @FindBy(xpath = RoomInfoMap.CODE_TEXTBOX)
    WebElement codeTextbox;

    /**
     * This Method type a word on code text box
     * @param code
     * @return RoomInfoPage instance
     */
    public RoomInfoPage typeOnCode(String code) {
	
	UIActions.typeOn(codeTextbox, code);
	return this;
    }

    @FindBy(xpath = RoomInfoMap.CAPACITY_TEXTBOX)
    WebElement capacityTextbox;

    /**
     * This method type a word on capacity text box
     * @param capacity
     * @return RoomInfoPage instance
     */
    public RoomInfoPage typeOnCapacity(String capacity) {
	
	UIActions.typeOn(capacityTextbox, capacity);
	return this;
    }

    @FindBy(xpath = RoomInfoMap.SAVE_BUTTON)
    WebElement saveButton;

    /**
     * This method perform click on save button
     * @return ConferenceRoomsPage instance
     */
    public ConferenceRoomsPage clickOnSave() {
	
	UIActions.clickAt(saveButton);
	return new ConferenceRoomsPage();
    }

    /**
     * This method return a boolean value when a code is the same than the value
     * on room info page.
     * @param expectedResult
     * @return true if code on info page is the same that a criteria.
     */
    public boolean VerifyIfCodeUpdate(String expectedResult) {
	
	String code = codeTextbox.getAttribute("value");
	return expectedResult.equalsIgnoreCase(code) ? true : false;
    }

    /**
     * This method return a boolean value when a capacity is the same than the
     * value on room info page.
     * @param expectedResult
     * @return true if capacity is the same that a criteria.
     */
    public boolean VerifyIfCapacityUpdate(String expectedResult) {
	String code = capacityTextbox.getAttribute("value");
	return expectedResult.equalsIgnoreCase(code) ? true : false;
    }

    @FindBy(xpath = RoomInfoMap.OPEN_LOCATION_LIST_BUTTON)
    WebElement locationListButton;

    /**
     * This method perform click on plus icon for display list of locations
     * @return RoomInfoPage instance.
     */
    public RoomInfoPage clickOnLocationIconPlus() {
	UIActions.clickAt(locationListButton);
	return this;
    }

    @FindBy(xpath = RoomInfoMap.LOCATION_NAME_LIST)
    List<WebElement> locationList;

    /**
     * this method return a location according a criteria.
     * @param findLocation
     * @return web element if found a location else null
     */
    private WebElement getLocation(String findLocation) {
	for (WebElement location : locationList) {
	    if (findLocation.equalsIgnoreCase(location.getText().trim())) {
		return location;
	    }
	}
	return null;
    }

    @FindBy(xpath = RoomInfoMap.DISPLAY_LOCATIONS_BUTTON)
    WebElement displayLocationsButton;

    /**
     * This method select a location by a criteria.
     * @param locationName
     * @return RoomInfoPage instance
     */
    public RoomInfoPage selectLocation(String locationName) {
	UIActions.waitFor(RoomInfoMap.DISPLAY_LOCATIONS_BUTTON);
	UIActions.clickAt(displayLocationsButton);
	UIActions.clickAt(getLocation(locationName));
	return this;
    }

    @FindBy(xpath = RoomInfoMap.LOCATION_TEXTBOX)
    WebElement locationTextbox;

    /**
     * This method is to verify if a location change according a criteria.
     * @param expectedResult
     * @return true if location change else false.
     */
    public boolean VerifyIfLocationUpdate(String expectedResult) {
	return locationTextbox.getText().trim()
		.equalsIgnoreCase("Organization/" + expectedResult) ?
			true : false;
    }

    @FindBy(xpath = RoomInfoMap.CANCEL_BUTTON) WebElement cancelButton;
    
    /**
     * This method perform click on cancel button
     * @return Conference Room Page instance.
     */
    public ConferenceRoomsPage clickOnCancelButton() {
	UIActions.clickAt(cancelButton);
	return new ConferenceRoomsPage();	
    }

}
