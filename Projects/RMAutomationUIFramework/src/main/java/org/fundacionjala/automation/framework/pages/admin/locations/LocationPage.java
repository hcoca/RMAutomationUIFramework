package org.fundacionjala.automation.framework.pages.admin.locations;

import java.util.ArrayList;
import java.util.List;

import org.fundacionjala.automation.framework.maps.admin.conferencerooms.ConferenceRoomsMap;
import org.fundacionjala.automation.framework.maps.admin.locations.LocationMap;
import org.fundacionjala.automation.framework.maps.admin.resource.ResourceMap;
import org.fundacionjala.automation.framework.pages.admin.conferencerooms.ConferenceRoomsPage;
import org.fundacionjala.automation.framework.pages.admin.home.AdminPage;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.ExplicitWait;
import org.fundacionjala.automation.framework.utils.common.LogManager;
import org.fundacionjala.automation.framework.utils.common.UIActions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * This class represents an "Location" page.
 * @author ArielYanarico
 *
 */
public class LocationPage extends AdminPage {

    @FindBy(xpath = LocationMap.LOCATION_TABLE)
    WebElement locationTable;
    @FindBy(xpath = LocationMap.ADD_LOCATION_BUTTON)
    WebElement addButton;
    @FindBy(xpath = LocationMap.REMOVE_LOCATION_BUTTON)
    WebElement removeButton;
    @FindBy(xpath = LocationMap.NEXT_PAGE_FIELD)
    WebElement pageField;
    @FindBy(xpath = LocationMap.FIRST_ROW)
    WebElement firstRow;

    /**
     * The constructor initializes web factory.
     */
    public LocationPage() {
	PageFactory.initElements(BrowserManager.getDriver(), this);
    }

    /**
     * Verifies if a location is displayed on "Locations" page.
     * @param name name of the location which is under verification.
     * @return true if is displayed else false
     */
    public boolean verifyLocationIsDisplayed(String name) {
	
	WebElement location = ExplicitWait.getWhenVisible(
		By.xpath("//span[text()='" + name + "']"), 10, false);
	if (location != null){
	    LogManager.info("Test Passed");
	    return true;
	} else {
	    LogManager.warning("Test Failed");
	    LogManager.error("Element not found (Exception)");
	    return false;
	}
    }

    /**
     * Verifies if a location is not displayed on "Locations" page.
     * @param name name of the location which is under verification.
     * @return true if is not displayed else false
     */
    public boolean verifyLocationIsNotDisplayed(String name) {

	WebElement location = ExplicitWait.getWhenVisible(
		    By.xpath("//span[text()='" + name + "']"), 10, false);

	if (location != null){
	    LogManager.info("Test Failed");
	    return false;
	} else{
	    LogManager.warning("Test Passed");
	    return true;
	}
    }

    /**
     * Verifies if a location is displayed on "Locations" page searched by its
     * "Display Name".
     * @param displayName display name of the location under verification.
     * @return true if is displayed else false
     */
    public boolean verifyLocationIsDisplayedByDisplayName(String displayName) {

	WebElement location = ExplicitWait.getWhenVisible(
		By.xpath("//div[text()='" + displayName + "']"), 10, false);
	if (location != null) {
	    LogManager.info("Test Passed");
	    return true;
	} else {
	    LogManager.warning("Test Failed");
	    LogManager.error("Element not found (Exception)");
	    return false;
	}
    }

    /**
     * Clicks on "(+)Add" button.
     * @return a new "Add Location" page.
     */
    public AddLocationPage clickOnAddButton() {
	addButton.click();
	LogManager.info("'+Add'button has been clicked");
	return new AddLocationPage();
    }

    /**
     * Clicks on "(-)Remove" button.
     * @return a new "Remove Location" page.
     */
    public RemoveLocationPage clickOnRemoveButton() {
	ExplicitWait.clickWhenReady(
		By.xpath(LocationMap.REMOVE_LOCATION_BUTTON), 30);
	LogManager.info("'-Remove' button has been clicked");
	return new RemoveLocationPage();
    }

    /**
     * Does a double click on a Location.
     * @param displayName display name of location which will be updated.
     * @return a new "Update Location" page.
     */
    public UpdateLocationPage doubleClickOnALocation(String displayName) {
	ExplicitWait.getWhenVisible(
		By.xpath("//div[text()='" + displayName + "']"), 30);
	UIActions.doubleClick(ExplicitWait.getWhenVisible(By.xpath("//div[text()='" + displayName + "']"), 30));
	return new UpdateLocationPage();
    }

    /**
     * Clicks on a Location.
     * @param displayName display name of location which will be selected.
     * @return this "Location" page.
     */
    public LocationPage clickOnALocation(String displayName) {
	locationTable.findElement(
		By.xpath("//div[text()='" + displayName + "']")).click();
	LogManager.info(displayName + " location has been clicked");
	return this;
    }

    /**
     * Verifies if the number of associations are displayed on "Locations" page.
     * @param name name of the room which is under verification.
     * @param numberOfRooms number of associations to compare.
     * @return true if is displayed else false
     */
    public boolean verifyNumberOfAssociations(String name, String numberOfRooms) {
	if (locationTable
		.findElement(
			By.xpath("//span[text()='" + name
				+ "']/following::small[1]")).getText()
		.contains(numberOfRooms)) {
	    LogManager.info("Test Passed");
	    return true;
	}
	LogManager.warning("Test Failed");
	return false;
    }
    
    public boolean verifyNumberOfLocations(String pageSize){
    	String actualResult = getLocationTableSize(pageSize).trim();
		return (pageSize.trim().equalsIgnoreCase(actualResult))
			? true : false;
    }
    
    public String getLocationTableSize(String sizePage)
	{
	    int size = Integer.parseInt(sizePage);
	    int loactionRead =  size / 15;
		List<String> list = new ArrayList<String>();
		WebElement lastRow = null;
		for (int i = 0; i < loactionRead; i++) {
		    for (WebElement location : getLocations()) {
			if (!list.contains(location.getText().trim())) {
			    list.add(location.getText().trim());
			}
			lastRow = location;
		    }
		    ((JavascriptExecutor) BrowserManager.getDriver())
		    .executeScript(
			    "arguments[0].scrollIntoView(true);", lastRow);
		}
		return list.size() + "";
	}
    
    public List<WebElement> getLocations() {

		return ExplicitWait.getElementsWhenVisible(
			By.xpath("//div[@tabindex]/div/div/child::*[3]//span"), 60);
	    }

    public LocationPage setPage(String page) {
		pageField.clear();
		pageField.sendKeys(page);
		return this;
    }
    
    public String getFirstRow() {
    	return firstRow.getText();
    }
}
