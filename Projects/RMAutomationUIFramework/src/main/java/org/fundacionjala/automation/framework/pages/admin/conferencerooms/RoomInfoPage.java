package org.fundacionjala.automation.framework.pages.admin.conferencerooms;

import org.fundacionjala.automation.framework.maps.admin.conferencerooms.RoomInfoMap;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
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

	public Boolean VerifyIfCodeUpdate(String expectedResult) {
		String code = codeTextbox.getAttribute("value");
		if(expectedResult.equalsIgnoreCase(code)){
			return true;
		}else{
			return false;
		}
	}

}
