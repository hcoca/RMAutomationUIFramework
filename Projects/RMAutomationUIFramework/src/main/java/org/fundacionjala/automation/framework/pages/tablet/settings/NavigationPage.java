package org.fundacionjala.automation.framework.pages.tablet.settings;

import java.util.List;
import org.fundacionjala.automation.framework.maps.tablet.settings.NavigationMap;
import org.fundacionjala.automation.framework.utils.api.managers.RoomAPIManager;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Room;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.ExplicitWait;
import org.fundacionjala.automation.framework.utils.common.LogManager;
import org.fundacionjala.automation.framework.utils.common.PropertiesReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NavigationPage extends SettingsPage {

    @FindBy(xpath = NavigationMap.DEFAULT_ROOM_TOGGLE_BUTTON)
    WebElement defaultRoomToggleButton;
    @FindBy(xpath = NavigationMap.SAVE_BUTTON)
    WebElement saveButton;
    @FindBy(xpath = NavigationMap.ROOMS_LIST)
    WebElement roomsList;
    @FindBy(xpath = NavigationMap.SEARCH_FIELD)
    WebElement searchField;

    public NavigationPage() {
	PageFactory.initElements(BrowserManager.getDriver(), this);
    }

    public NavigationPage clickOnRoomToggleButton() {
	(new WebDriverWait(BrowserManager.getDriver(), 30))
		.until(ExpectedConditions
			.elementToBeClickable(defaultRoomToggleButton));
	defaultRoomToggleButton.click();

	LogManager.info("Room Toggle Button has been clicked");

	return this;
    }

    public NavigationPage clickOnSaveButton() {
	(new WebDriverWait(BrowserManager.getDriver(), 30))
		.until(ExpectedConditions.elementToBeClickable(saveButton));
	saveButton.click();

	LogManager.info("Save Button has been clicked");

	return this;
    }

    public NavigationPage selectConferenceRoom(String roomName) {
	WebElement room = getConferenceRoom(roomName);
	if (room != null) {
	    room.click();
	}
	return this;
    }

    /**
     * This function is for getting a room by name
     * @param name
     * @return one Room if it is found else null
     */
    private WebElement getConferenceRoom(String name) {
	ExplicitWait.getWhenVisible(By.xpath(NavigationMap.ROOMS_LIST), 120);
	List<WebElement> rooms = roomsList.findElements(By
		.xpath(NavigationMap.ROOMS_LIST_ELEMENT));
	for (WebElement room : rooms) {
	    String roomName = room.findElement(
		    By.xpath(NavigationMap.ROOM_NAME)).getText();
	    if (roomName.equals(name)) {
		return room;
	    }
	}
	return null;
    }

    /**
     * This function is to verify if a room exist in room list on Navigate page.
     * @param roomName
     * @return true if any room is found else false
     */
    public boolean verifyIfExistRoomInList(String roomName) {
	return getConferenceRoom(roomName) != null ? true : false;
    }

    /**
     * This function is to verify that all rooms are in the list on Navigate
     * page.
     * @return true if the size of the list on Navigate page is equals to the
     * corresponding size(API)
     */
    public boolean verifyIfRoomsExist() throws Throwable {
	boolean verification = false;
	new RoomAPIManager();
	List<Room> rooms = RoomAPIManager.getRequest(PropertiesReader
		.getServiceURL() + "/rooms");
	List<WebElement> roomsTable = roomsList.findElements(By
		.xpath(NavigationMap.ROOMS_LIST_ELEMENT));
	if (rooms.size() == roomsTable.size()) {
	    verification = true;
	}
	return verification;
    }

    /**
     * This function is to insert a filter criteria on the search field
     * @param filter is a String that represents the criteria of search
     * @return NavigationPage
     */
    public NavigationPage insertFilterSearch(String filter) {
	searchField.clear();
	searchField.sendKeys(filter);
	LogManager.info("A filter criteria has been inserted: " + filter );
	return this;
    }

    /**
     * This function is to verify that the rooms displayed on the results field
     * are according the filter criteria
     * @param filter
     */
    public boolean verifyIfRoomsExistAccordingFilter(String filter)
	    throws Throwable {
	boolean verification = false;
	new RoomAPIManager();
	List<String> rooms = RoomAPIManager.getRoomsByCriteria(filter);
	List<WebElement> roomsSearch = roomsList.findElements(By
		.xpath(NavigationMap.ROOMS_LIST_ELEMENT));
	if(rooms.size() == roomsSearch.size()){
	    verification = true;
	}
	return verification;
    }
}
