package org.fundacionjala.automation.framework.pages.admin.locations;

import org.fundacionjala.automation.framework.maps.admin.locations.AddLocationMap;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.LogManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddLocationPage {
	
	@FindBy (xpath = AddLocationMap.LOCATION_NAME_FIELD) WebElement nameField;
	@FindBy (xpath = AddLocationMap.LOCATION_DISPLAY_NAME_FIELD) WebElement displayNameField;
	@FindBy (xpath = AddLocationMap.LOCATION_DESCRIPTION_AREA) WebElement descriptionArea;
	@FindBy (xpath = AddLocationMap.SAVE_BUTTON) WebElement saveButton;
	public AddLocationPage(){
		PageFactory.initElements(BrowserManager.getDriver(), this);
	}

	public AddLocationPage setNameField(String name) {
		nameField.sendKeys(name);	
		LogManager.info("The value of name text field was set to :" + name);
		return this;
	}
	
	public AddLocationPage setDisplayNameField(String displayName) {
		displayNameField.sendKeys(displayName);	
		LogManager.info("The value of display name text field was set to :" + displayName);
		return this;
	}
	
	public AddLocationPage setDescriptionArea(String description) {
		descriptionArea.sendKeys(description);	
		LogManager.info("The value of description text area was set to :" + description);
		return this;
	}
	
	public LocationPage clickOnSaveButton(){
		saveButton.click();
		LogManager.info("Save button has been clicked");
		return new LocationPage();
	}
}
