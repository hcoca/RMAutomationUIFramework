package org.fundacionjala.automation.framework.pages.admin.locations;

import org.fundacionjala.automation.framework.maps.admin.locations.LocationAssociationMap;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.ExplicitWait;
import org.fundacionjala.automation.framework.utils.common.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * This class represents an "Location Association" page.
 * @author ArielYanarico
 *
 */
public class LocationAssociationPage {

    @FindBy(xpath = LocationAssociationMap.AVAILABLE_ROOMS_GRID)
    WebElement availableRoomsGrid;
    @FindBy(xpath = LocationAssociationMap.ASSOCIATED_ROOMS_GRID)
    WebElement associatedRoomsGrid;
    @FindBy(xpath = LocationAssociationMap.SAVE_BUTTON)
    WebElement saveButton;

    /**
     * The constructor initializes web factory.
     */
    public LocationAssociationPage() {
	PageFactory.initElements(BrowserManager.getDriver(), this);
    }

    /**
     * Clicks on "(+)Add" button of a room which is available.
     * @param roomName name of the available room.
     * @return this "Location Association" page.
     */
    public LocationAssociationPage clickOnAddAvailableRoom(String roomName) {
	ExplicitWait.getWhenVisible(By.xpath("//div[text()='" + roomName
		+ "']/following::button[1]"), 30);
	availableRoomsGrid.findElement(
		By.xpath("//div[text()='" + roomName
			+ "']/following::button[1]")).click();
	LogManager.info("An available room has been added");
	return this;
    }

    /**
     * Clicks on "(-)Add" button of a room which is associated.
     * @param roomName name of the associated room.
     * @return this "Location Association" page.
     */
    public LocationAssociationPage clickOnRemoveAssociatedRoom(String roomName) {
	ExplicitWait.getWhenVisible(By.xpath("//div[text()='" + roomName
			+ "']/following::button[1]"), 30);
	associatedRoomsGrid.findElement(
		By.xpath("//div[text()='" + roomName
			+ "']/following::button[1]")).click();
	LogManager.info("An available room has been removed");
	return this;
    }

    /**
     * Clicks on "Save" Button.
     * @return a new "Locations" page.
     */
    public LocationPage clickOnSaveButton() {
	ExplicitWait.clickWhenReady(
		By.xpath(LocationAssociationMap.SAVE_BUTTON), 30);
	LogManager.info("Save button has been clicked");
	return new LocationPage();
    }

    /**
     * Verifies if an available room is displayed on "Location Associations"
     * page.
     * @param roomName name of the room which is under verification.
     * @return true if is displayed else false
     */
    public boolean verifyAvailableRoomDisplayed(String roomName) {
	try {
	    ExplicitWait.getWhenVisible(By.xpath("//div[text()='" + roomName
		    + "']"), 30);
	    availableRoomsGrid.findElement(By.xpath("//div[text()='" + roomName
		    + "']"));
	    LogManager.info("Test Passed");
	    return true;
	} catch (NoSuchElementException e) {
	    LogManager.warning("Test Failed");
	    LogManager.error("Element not found (Exception)");
	    return false;
	}
    }

    /**
     * Verifies if an associated room is displayed. on "Location Associations"
     * page.
     * @param roomName name of the room which is under verification.
     * @return true if is displayed else false
     */
    public boolean verifyAssociatedRoomDisplayed(String roomName) {
	try {
	    ExplicitWait.getWhenVisible(By.xpath("//div[text()='"
		    + roomName + "']"), 30);
	    associatedRoomsGrid.findElement(By.xpath("//div[text()='"
		    + roomName + "']"));
	    LogManager.info("Test Passed");
	    return true;
	} catch (NoSuchElementException e) {
	    LogManager.warning("Test Failed");
	    LogManager.error("Element not found (Exception)");
	    return false;
	}
    }
}
