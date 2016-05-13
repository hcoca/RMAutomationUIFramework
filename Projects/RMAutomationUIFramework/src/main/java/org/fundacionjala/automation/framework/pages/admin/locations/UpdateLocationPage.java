package org.fundacionjala.automation.framework.pages.admin.locations;

import org.fundacionjala.automation.framework.maps.admin.locations.UpdateLocationMap;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * This class represents an "Update Location" page.
 * @author ArielYanarico
 *
 */
public class UpdateLocationPage {
    @FindBy(xpath = UpdateLocationMap.LOCATION_NAME_FIELD)
    WebElement nameField;
    @FindBy(xpath = UpdateLocationMap.LOCATION_DISPLAY_NAME_FIELD)
    WebElement displayNameField;
    @FindBy(xpath = UpdateLocationMap.LOCATION_DESCRIPTION_AREA)
    WebElement descriptionArea;
    @FindBy(xpath = UpdateLocationMap.ADD_PARENT_LOCATION_BUTTON)
    WebElement addParentButton;
    @FindBy(xpath = UpdateLocationMap.PARENT_LOCATION_FIELD)
    WebElement parentField;
    @FindBy(xpath = UpdateLocationMap.SAVE_BUTTON)
    WebElement saveButton;
    @FindBy(xpath = UpdateLocationMap.LOCATION_ASSOCIATION_LINK)
    WebElement associationLink;

    /**
     * The constructor initializes web factory.
     */
    public UpdateLocationPage() {
	PageFactory.initElements(BrowserManager.getDriver(), this);
    }

    /**
     * Fills "Name" text field with a given name.
     * @param name the new value of "Name" text field.
     * @return this "UpdateLocationPage".
     */
    public UpdateLocationPage setNameField(String name) {
	nameField.clear();
	nameField.sendKeys(name);
	LogManager.info("The value of name text field was set to :" + name);
	return this;
    }

    /**
     * Fills "Display Name" text field with a given name.
     * @param name the new value of "Display Name" text field.
     * @return this "UpdateLocationPage".
     */
    public UpdateLocationPage setDisplayNameField(String displayName) {
	displayNameField.clear();
	displayNameField.sendKeys(displayName);
	LogManager.info("The value of display name text field was set to :"
		+ displayName);
	return this;
    }

    /**
     * Fills "Description" text area with a given name.
     * @param name the new value of "Description" text area.
     * @return this "UpdateLocationPage".
     */
    public UpdateLocationPage setDescriptionArea(String description) {
	descriptionArea.clear();
	descriptionArea.sendKeys(description);
	LogManager.info("The value of description text area was set to :"
		+ description);
	return this;
    }

    /**
     * Clicks on "Save" Button.
     * @return a new "Locations" page.
     */
    public LocationPage clickOnSaveButton() {
	WebElement html = BrowserManager.getDriver()
		.findElement(By.tagName("html"));
	
	if (saveButton.isDisplayed()){
	    saveButton.click();
	}else{
	    html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
	    saveButton.click();
	    html.sendKeys(Keys.chord(Keys.CONTROL, "0"));
	}
	
	LogManager.info("Save button has been clicked");
	return new LocationPage();
    }

    /**
     * Clicks on "(+)Add Parent" button.
     * @return this "UpdateLocationPage".
     */
    public UpdateLocationPage clickOnAddParentButton() {
	addParentButton.click();
	LogManager.info("Add-Parent button has been clicked");
	return this;
    }

    /**
     * Picks other location to be the parent of this location
     * @param parentName the name of location which will be parent.
     * @return this "UpdateLocationPage".
     */
    public UpdateLocationPage selectAParentLocation(String parentName) {
	BrowserManager.getDriver()
		.findElement(By.xpath("//span[@ng-if='tree.branches.length']"))
		.click();
	BrowserManager.getDriver()
		.findElement(By.xpath("//div[text()='" + parentName + "']"))
		.click();
	LogManager.info("A parent location has been selected");
	return this;
    }

    /**
     * Verifies if a location description is displayed on "Update Locations"
     * page.
     * @param description description of the location under verification.
     * @return true if is displayed else false
     */
    public boolean verifyDescriptionIsDisplayed(String description) {
	if (description.contentEquals(descriptionArea.getAttribute("value"))) {
	    LogManager.info("Test Passed");
	    return true;
	}
	LogManager.warning("Test Failed");
	return false;
    }

    /**
     * Clicks on "Location Association" link.
     * @return a new "Location Association" page.
     */
    public LocationAssociationPage clickOnLocationAssociationLink() {
	associationLink.click();
	LogManager.info("Location associations link has been clicked");
	return new LocationAssociationPage();
    }

    /**
     * Verifies if a location description is displayed on "Update Locations"
     * page.
     * @param parentName parent name of the location under verification.
     * @return true if is displayed else false
     */
    public boolean verifyParentIsDisplayed(String parentName) {
	if (parentField.getText().contains(parentName)) {
	    LogManager.info("Test Passed");
	    return true;
	}
	LogManager.warning("Test Failed");
	return false;
    }
}
