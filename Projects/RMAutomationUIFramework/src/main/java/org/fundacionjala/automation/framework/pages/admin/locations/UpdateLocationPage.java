package org.fundacionjala.automation.framework.pages.admin.locations;

import org.fundacionjala.automation.framework.maps.admin.locations.UpdateLocationMap;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UpdateLocationPage {
	@FindBy (xpath = UpdateLocationMap.LOCATION_NAME_FIELD) WebElement nameField;
	@FindBy (xpath = UpdateLocationMap.LOCATION_DISPLAY_NAME_FIELD) WebElement displayNameField;
	@FindBy (xpath = UpdateLocationMap.LOCATION_DESCRIPTION_AREA) WebElement descriptionArea;
	@FindBy (xpath = UpdateLocationMap.ADD_PARENT_LOCATION_BUTTON) WebElement addParentButton;
	@FindBy (xpath = UpdateLocationMap.PARENT_LOCATION_FIELD) WebElement parentField;
	@FindBy (xpath = UpdateLocationMap.SAVE_BUTTON) WebElement saveButton;
	@FindBy (xpath = UpdateLocationMap.LOCATION_ASSOCIATION_LINK) WebElement associationLink;
	public UpdateLocationPage(){
		PageFactory.initElements(BrowserManager.getDriver(), this);
	}

	public UpdateLocationPage setNameField(String name) {
		nameField.clear();	
		nameField.sendKeys(name);	
		LogManager.info("The value of name text field was set to :" + name);
		return this;
	}
	
	public UpdateLocationPage setDisplayNameField(String displayName) {
		displayNameField.clear();	
		displayNameField.sendKeys(displayName);	
		LogManager.info("The value of display name text field was set to :" + displayName);
		return this;
	}
	
	public UpdateLocationPage setDescriptionArea(String description) {
		descriptionArea.clear();	
		descriptionArea.sendKeys(description);	
		LogManager.info("The value of description text area was set to :" + description);
		return this;
	}
	
	public LocationPage clickOnSaveButton(){
		saveButton.click();
		LogManager.info("Save button has been clicked");
		return new LocationPage();
	}
	
	public UpdateLocationPage clickOnAddParentButton(){
		addParentButton.click();
		LogManager.info("Add-Parent button has been clicked");
		return this;
	}
	
	public UpdateLocationPage selectAParentLocation(String parentName){
		BrowserManager.getDriver().findElement(By.xpath("//span[@ng-if='tree.branches.length']")).click();
		BrowserManager.getDriver().findElement(By.xpath("//div[text()='"+parentName+"']")).click();
		LogManager.info("A parent location has been selected");
		return this;
	}
	
	public boolean verifyDescriptionIsDisplayed(String description){
		if (description.contentEquals(descriptionArea.getAttribute("value"))) {
			LogManager.info("Test Passed");
			return true;
		}
		LogManager.warning("Test Failed");
		return false;
	}
	
	public LocationAssociationPage clickOnLocationAssociationLink(){
		associationLink.click();
		LogManager.info("Location associations link has been clicked");
		return new LocationAssociationPage();
	}
	
	public boolean verifyParentIsDisplayed(String parentName){
		if (parentField.getText().contains(parentName)) {
			LogManager.info("Test Passed");
			return true;
		}
		LogManager.warning("Test Failed");
		return false;
	}
}
