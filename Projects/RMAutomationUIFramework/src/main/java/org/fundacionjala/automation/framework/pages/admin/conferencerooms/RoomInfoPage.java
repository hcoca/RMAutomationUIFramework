package org.fundacionjala.automation.framework.pages.admin.conferencerooms;

import java.util.List;

import org.fundacionjala.automation.framework.maps.admin.conferencerooms.RoomInfoMap;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.LogManager;
import org.fundacionjala.automation.framework.utils.common.UIActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RoomInfoPage {

	public RoomInfoPage() {
		PageFactory.initElements(BrowserManager.getDriver(), this);
	}

	@FindBy (linkText = RoomInfoMap.RESOURCES_ASSOCIATIONS_LINK) WebElement resourcesAssociations;
	public ResourceAssociationsPage clickOnResourceAssociations() {
		resourcesAssociations.click();
		return new ResourceAssociationsPage();
	}
	
	@FindBy (linkText = RoomInfoMap.OUT_OF_ORDER_PLANNING_LINK) WebElement outOfOrder;
	public OutOfOrderPage clickOnOutOfOrder() {
		outOfOrder.click();
		LogManager.info("OutOfOrder-ConferenceRooms Button has been clicked");
		return new OutOfOrderPage();
	}
	
	@FindBy (xpath = RoomInfoMap.DISPLAY_NAME_TEXTBOX) WebElement displayNameTextbox;
	public RoomInfoPage typeOnDisplayName(String displayName) {
		UIActions.waitFor(RoomInfoMap.DISPLAY_NAME_TEXTBOX);
		UIActions.typeOn(displayNameTextbox, displayName);
		return this;
	}
	
	@FindBy (xpath = RoomInfoMap.CODE_TEXTBOX) WebElement codeTextbox;
	public RoomInfoPage typeOnCode(String code) {
		UIActions.typeOn(codeTextbox, code);
		return this;
	}

	@FindBy (xpath = RoomInfoMap.CAPACITY_TEXTBOX) WebElement capacityTextbox;
	public RoomInfoPage typeOnCapacity(String capacity) {
		UIActions.typeOn(capacityTextbox, capacity);
		return this;
	}
	
	@FindBy (xpath = RoomInfoMap.SAVE_BUTTON) WebElement saveButton;
	public ConferenceRoomsPage clickOnSave() {
		UIActions.clickAt(saveButton);
		return new ConferenceRoomsPage();
	}

	public boolean VerifyIfCodeUpdate(String expectedResult) {
		String code = codeTextbox.getAttribute("value");
		if(expectedResult.equalsIgnoreCase(code)){
			return true;
		}else{
			return false;
		}
	}

	public boolean VerifyIfCapacityUpdate(String expectedResult) {
		String code = capacityTextbox.getAttribute("value");
		if(expectedResult.equalsIgnoreCase(code)){
			return true;
		}else{
			return false;
		}
	}
	
	@FindBy (xpath = RoomInfoMap.OPEN_LOCATION_LIST_BUTTON) WebElement locationListButton;
	public RoomInfoPage clickOnLocationIconPlus() {
		UIActions.clickAt(locationListButton);
		return this;
	}
	
	@FindBy (xpath = RoomInfoMap.LOCATION_NAME_LIST) List<WebElement> locationList;
	private WebElement getLocation(String findLocation){
		for (WebElement location : locationList) {
			if(findLocation.equalsIgnoreCase(location.getText().trim())){
				return location;
			}	
		}
		return null;
	}
	
	@FindBy (xpath = RoomInfoMap.DISPLAY_LOCATIONS_BUTTON) WebElement displayLocationsButton;	
	public RoomInfoPage selectLocation(String locationName) {
		UIActions.waitFor(RoomInfoMap.DISPLAY_LOCATIONS_BUTTON);
		UIActions.clickAt(displayLocationsButton);
		UIActions.clickAt(getLocation(locationName));
		return this;
	}

	@FindBy (xpath = RoomInfoMap.LOCATION_TEXTBOX) WebElement locationTextbox;	
	public boolean VerifyIfLocationUpdate(String expectedResult) {
		if(locationTextbox.getText().trim().equalsIgnoreCase("Organization/"+expectedResult)){
			return true;
		}
		return false;
	}

}
