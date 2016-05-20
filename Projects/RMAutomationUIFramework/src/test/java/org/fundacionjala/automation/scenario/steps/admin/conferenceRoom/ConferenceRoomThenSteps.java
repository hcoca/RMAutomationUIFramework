package org.fundacionjala.automation.scenario.steps.admin.conferenceRoom;

import java.util.List;

import org.fundacionjala.automation.framework.maps.admin.conferencerooms.OutOfOrderMap;
import org.fundacionjala.automation.framework.maps.tablet.home.HomeMap;
import org.fundacionjala.automation.framework.pages.admin.conferencerooms.ConferenceRoomsPage;
import org.fundacionjala.automation.framework.pages.admin.conferencerooms.OutOfOrderPage;
import org.fundacionjala.automation.framework.pages.admin.home.AdminPage;
import org.fundacionjala.automation.framework.pages.admin.login.LoginPage;
import org.fundacionjala.automation.framework.pages.tablet.settings.ConnectionPage;
import org.fundacionjala.automation.framework.pages.tablet.settings.NavigationPage;
import org.fundacionjala.automation.framework.utils.api.managers.RoomAPIManager;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Room;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.ConferenceRoomDataBase;
import org.fundacionjala.automation.framework.utils.common.PropertiesReader;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import cucumber.api.java.After;
import cucumber.api.java.en.Then;

public class ConferenceRoomThenSteps {
    String roomNameModified;
    @Then("^I validate if the room \"([^\"]*)\" is enabled in tablet page$")
    public void validateRoomEnabled(String roomName)
	    throws Throwable {
	roomNameModified = roomName;
	ConnectionPage connection = new ConnectionPage();
	NavigationPage navigation = connection
		.setUpServiceURL(PropertiesReader.getServiceURL())
		.clickOnSaveButton()
		.clickOnNavigationButton();

	Assert.assertTrue(roomName + " room is not in the list",
		navigation
		.clickOnRoomToggleButton()
		.verifyIfExistRoomInList(roomName)
		);
    }

    @Then("^I validate if the room \"([^\"]*)\" is disabled in tablet page$")
    public void validateRoomDisabled(String roomName) throws Throwable {
	roomNameModified = roomName;
	ConnectionPage connection = new ConnectionPage();
	NavigationPage navigation = connection
		.setUpServiceURL(PropertiesReader.getServiceURL())
		.clickOnSaveButton().clickOnNavigationButton();
 
	RoomAPIManager.changeStatusOfRoom(roomName, true);
	Assert.assertFalse(roomName + " room is in the list",
		navigation
		.clickOnRoomToggleButton()
		.verifyIfExistRoomInList(roomName)
		);
    }

    @Then("^validate if the quantity of rooms in server is the same displayed in conference room page$")
    public void validate_if_the_quantity_of_rooms_in_server_is_the_same_displayed_in_conference_room_page()
	    throws Throwable {
	ConferenceRoomsPage conferenceRoom = new ConferenceRoomsPage();
	int expectedResult = Integer.parseInt(conferenceRoom.getTotalItems());
	int totalItems = 0;
	List<Room> roomList = RoomAPIManager.getRequest(PropertiesReader
		.getServiceURL() + "/rooms");
	totalItems = roomList.size();
	Assert.assertEquals(expectedResult, totalItems);
    }

    @Then("^validate if the table have all rooms that have this criteria \"([^\"]*)\" in their names$")
    public void validate_if_the_table_have_all_rooms_that_have_this_criteria_in_their_names(
	    String criteriaFilter) throws Throwable {
	ConferenceRoomsPage conferenceRoom = new ConferenceRoomsPage();
	List<WebElement> actualRoomList = conferenceRoom.getRooms();
	List<String> expectedListString = RoomAPIManager
		.getRoomsByCriteria(criteriaFilter);
	int actualCount = 0;
	for (WebElement e : actualRoomList) {
	    if (expectedListString.contains(e.getText().trim())) {
		actualCount++;
	    }
	}
	Assert.assertEquals(
		"Quantity of rooms on rooms table is not the same than rooms that match with a citeria",
		expectedListString.size(), actualCount);
    }

    @Then("^validate if there are \"([^\"]*)\" or less rooms on table\\.$")
    public void validate_if_there_are_rooms_on_table(int sizePage)
	    throws Throwable {
	ConferenceRoomsPage conferenceRoom = new ConferenceRoomsPage();
	Assert.assertTrue("There are more than " + sizePage
		+ " rooms in this page",
		conferenceRoom.verifySizePage(sizePage));
    }

    @Then("^I validate if the next page is displayed according the page size specified \"([^\"]*)\" and the page \"([^\"]*)\"$")
    public void i_validate_if_the_next_page_is_displayed_according_the_page_size_specified_and_the_page(
	    String pageSize, String page) throws Throwable {
	ConferenceRoomsPage room = new ConferenceRoomsPage();

	boolean verification = false;
	List<Room> listRooms = RoomAPIManager.getRequest(PropertiesReader
		.getServiceURL() + "/rooms");
	int index = ((Integer.parseInt(page) - 1) * Integer.parseInt(pageSize)) + 1;

	for (int i = 0; i < listRooms.size(); i++) {
	    if (listRooms.get(i).displayName.contains(Integer.toString(index))) {
		String displayNameRoom = listRooms.get(i).displayName;
		if (room.getFirstRow().contains(displayNameRoom)) {
		    verification = true;
		}
	    }
	}
	Assert.assertTrue("Next page isn't displaying according page size",
		verification);
    }

    @Then("^I validate if the previous page is displayed according the page size specified \"([^\"]*)\" and the page \"([^\"]*)\"$")
    public void i_validate_if_the_previous_page_is_displayed_according_the_page_size_specified_and_the_page(
	    String pageSize, String page) throws Throwable {
	ConferenceRoomsPage room = new ConferenceRoomsPage();

	boolean verification = false;
	List<Room> listRooms = RoomAPIManager.getRequest(PropertiesReader
		.getServiceURL() + "/rooms");
	int index = ((Integer.parseInt(page) - 1) * Integer.parseInt(pageSize)) + 1;

	for (int i = 0; i < listRooms.size(); i++) {
	    if (listRooms.get(i).displayName.contains(Integer.toString(index))) {
		String displayNameRoom = listRooms.get(i).displayName;
		if (room.getFirstRow().contains(displayNameRoom)) {
		    verification = true;
		}
	    }
	}
	Assert.assertTrue(
		"Previous page is not displaying acccording page size",
		verification);
    }
    
    @Then("^The Out Of Order cannot be created an error message is displayed$")
    public void validateErrorMessageIsDisplayed() throws Throwable {
	boolean verification = false;
	WebElement messageError = BrowserManager.getDriver().findElement(
		By.xpath(OutOfOrderMap.ERROR_MESSAGE));
	if (messageError.getText().contains(
		"Cannot establish out of order as an past event")) {
	    verification = true;
	}
	Assert.assertTrue("Error message is not present", verification);
	new OutOfOrderPage().clickOnCancelButton();
    }
    
    @Then("^The \"([^\"]*)\" room should changes its status to non-available with the \"([^\"]*)\" title corresponding$")
    public void validateRoomChangesStatusNonAvailable(String roomName,
	    String titleOutOfOrder) throws Throwable {
	AdminPage home = new AdminPage();
	boolean verification = false;

	WebElement title = BrowserManager.getDriver().findElement(
		By.xpath(HomeMap.TITLE_OUT_OF_ORDER));
	if (title.getText().contains(titleOutOfOrder)) {
	    verification = true;
	}
	Assert.assertTrue("Meeting for out of order couldn't be updated.", verification);

    }
    
    @Then("^The Out Of Order on \"([^\"]*)\" room should be created with the time interval defined$")
    public void outOfOrderShouldBeCreatedWithIntervalTimeDefined(String roomName)
	    throws Throwable {
	boolean verification = false;
	ConnectionPage connection = new ConnectionPage();

	NavigationPage navigation = connection
		.setUpServiceURL(PropertiesReader.getServiceURL())
		.clickOnSaveButton().clickOnNavigationButton();

	navigation.clickOnRoomToggleButton()
		.selectConferenceRoom(roomName).clickOnSaveButton()
		.topMenu
		.clickOnHomeButton();

	WebElement time = BrowserManager.getDriver().findElement(
		By.xpath(HomeMap.TIME_OUT_OF_ORDER));
	if ((time.getText().contains(OutOfOrderPage.timeBegin))
		&& (time.getText().contains(OutOfOrderPage.timeEnd))) {
	    verification = true;
	}
	Assert.assertTrue(
		"Out of order couldn't be created or it is not visible",
		verification);
	
    }
    
    @Then("^The Out Of Order on \"([^\"]*)\" room should have been created an OutOfOrder with the \"([^\"]*)\" title corresponding$")
    public void outOfOrderShouldHaveTitleCorresponding(String roomName,
	    String titleOutOfOrder) throws Throwable {
	boolean verification = false;
	ConnectionPage connection = new ConnectionPage();
	NavigationPage navigation = connection
		.setUpServiceURL(PropertiesReader.getServiceURL())
		.clickOnSaveButton().clickOnNavigationButton();

	navigation.clickOnRoomToggleButton()
		.selectConferenceRoom(roomName).clickOnSaveButton().topMenu
		.clickOnHomeButton();

	WebElement title = BrowserManager.getDriver().findElement(
		By.xpath(HomeMap.TITLE_OUT_OF_ORDER));
	if (title.getText().contains(titleOutOfOrder)) {
	    verification = true;
	}
	Assert.assertTrue("Out of order couldn't be created or it is not visible", verification);

    }
    
    @Then("^The Out Of Order on \"([^\"]*)\" room should has been disabled correctly with the \"([^\"]*)\" title corresponding$")
    public void the_Out_Of_Order_on_room_should_has_been_disabled_correctly_with_the_title_corresponding(
	    String roomName, String titleOutOfOrder) throws Throwable {
	boolean verification = true;
	ConnectionPage connection = new ConnectionPage();
	NavigationPage navigation = connection
		.setUpServiceURL(PropertiesReader.getServiceURL())
		.clickOnSaveButton().clickOnNavigationButton();

	navigation.clickOnRoomToggleButton().selectConferenceRoom(roomName)
		.clickOnSaveButton().topMenu.clickOnHomeButton();

	WebElement title = BrowserManager.getDriver().findElement(
		By.xpath(HomeMap.TITLE_OUT_OF_ORDER));
	if (title.getText().contains(titleOutOfOrder)) {
	    verification = false;
	}
	Assert.assertTrue("Out of order is not disable or it couldn't be created", verification);
    }
    
    @After ("@conferenceDisabled")
    public void tearDownRoomDisabled (){
	new ConferenceRoomDataBase(roomNameModified).setEnable(true);
    }
}
