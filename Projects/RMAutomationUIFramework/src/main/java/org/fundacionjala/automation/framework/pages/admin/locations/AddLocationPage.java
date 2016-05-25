package org.fundacionjala.automation.framework.pages.admin.locations;

import org.fundacionjala.automation.framework.maps.admin.locations.AddLocationMap;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * This class represents an "Add Location" page.
 * @author ArielYanarico
 *
 */
public class AddLocationPage {

    @FindBy(xpath = AddLocationMap.LOCATION_NAME_FIELD)
    WebElement nameField;
    @FindBy(xpath = AddLocationMap.LOCATION_DISPLAY_NAME_FIELD)
    WebElement displayNameField;
    @FindBy(xpath = AddLocationMap.LOCATION_DESCRIPTION_AREA)
    WebElement descriptionArea;
    @FindBy(xpath = AddLocationMap.ADD_PARENT_LOCATION_BUTTON)
    WebElement addParentButton;
    @FindBy(xpath = AddLocationMap.SAVE_BUTTON)
    WebElement saveButton;
    @FindBy(xpath = AddLocationMap.CANCEL_BUTTON)
    WebElement cancelButton;
    @FindBy(xpath = AddLocationMap.LOCATION_ASSOCIATION_LINK)
    WebElement associationLink;

    /**
     * The constructor initializes web factory.
     */
    public AddLocationPage() {
	PageFactory.initElements(BrowserManager.getDriver(), this);
    }

    /**
     * Fills "Name" text field with a given name.
     * @param name the new value of "Name" text field.
     * @return this "AddLocationPage".
     */
    public AddLocationPage setNameField(String name) {
	nameField.sendKeys(name);
	LogManager.info("The value of name text field was set to :" + name);
	return this;
    }

    /**
     * Fills "Display Name" text field with a given name.
     * @param name the new value of "Display Name" text field.
     * @return this "AddLocationPage".
     */
    public AddLocationPage setDisplayNameField(String displayName) {
	displayNameField.sendKeys(displayName);
	LogManager.info("The value of display name text field was set to :"
		+ displayName);
	return this;
    }

    /**
     * Fills "Description" text area with a given name.
     * @param name the new value of "Description" text area.
     * @return this "AddLocationPage".
     */
    public AddLocationPage setDescriptionArea(String description) {
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
	saveButton.click();
	LogManager.info("Save button has been clicked");
	return new LocationPage();
    }
    
    /**
     * Clicks on "Cancel" Button.
     * @return a new "Locations" page.
     */
    public LocationPage clickOnCancelButton() {
    	
    	if (cancelButton != null) {
		   cancelButton.click();
		   LogManager.info("Cancel button has been clicked");
		}
     	return new LocationPage();
    }
    

    /**
     * Clicks on "(+)Add Parent" button.
     * @return this "AddLocationPage".
     */
    public AddLocationPage clickOnAddParentButton() {
	addParentButton.click();
	LogManager.info("Add-Parent button has been clicked");
	return this;
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
     * Picks other location to be the parent of this location
     * @param parentName the name of location which will be parent.
     * @return this "AddLocationPage".
     */
    public AddLocationPage selectAParentLocation(String parentName) {
	BrowserManager.getDriver()
		.findElement(By.xpath("//span[@ng-if='tree.branches.length']"))
		.click();
	BrowserManager.getDriver()
		.findElement(By.xpath("//div[text()='" + parentName + "']"))
		.click();
	LogManager.info("A parent location has been selected");
	return this;
    }
}
