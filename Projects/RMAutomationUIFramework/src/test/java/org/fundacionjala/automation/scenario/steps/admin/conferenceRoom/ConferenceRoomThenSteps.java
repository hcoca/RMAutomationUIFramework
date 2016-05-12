package org.fundacionjala.automation.scenario.steps.admin.conferenceRoom;

import java.util.List;

import org.fundacionjala.automation.framework.maps.admin.conferencerooms.OutOfOrderMap;
import org.fundacionjala.automation.framework.pages.admin.conferencerooms.ConferenceRoomsPage;
import org.fundacionjala.automation.framework.pages.tablet.settings.ConnectionPage;
import org.fundacionjala.automation.framework.pages.tablet.settings.NavigationPage;
import org.fundacionjala.automation.framework.utils.api.managers.RoomAPIManager;
import org.fundacionjala.automation.framework.utils.api.managers.ServiceAPIManager;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Room;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Service;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.PropertiesReader;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import cucumber.api.java.en.Then;

public class ConferenceRoomThenSteps {

    @Then("^I validate if the room \"([^\"]*)\" is enabled in tablet page$")
    public void i_validate_if_the_room_is_enabled_in_tablet_page(String roomName)
	    throws Throwable {
	ConnectionPage connection = new ConnectionPage();
	NavigationPage navigation = connection
		.setUpServiceURL("http://172.20.208.84:4040/")
		.clickOnSaveButton().clickOnNavigationButton();

	Assert.assertTrue(navigation.clickOnRoomToggleButton()
		.verifyIfExistRoomInList(roomName));
    }

    @Then("^I validate if the room \"([^\"]*)\" is disabled in tablet page$")
    public void i_validate_if_the_room_is_disabled_in_tablet_page(
	    String roomName) throws Throwable {
	ConnectionPage connection = new ConnectionPage();
	NavigationPage navigation = connection
		.setUpServiceURL("http://172.20.208.84:4040/")
		.clickOnSaveButton().clickOnNavigationButton();

	Assert.assertFalse(navigation.clickOnRoomToggleButton()
		.verifyIfExistRoomInList(roomName));

	List<Room> roomList = RoomAPIManager.getRequest(PropertiesReader
		.getServiceURL() + "/rooms");
	Room roomSelected = new Room();
	for (Room room : roomList) {
	    if (room.displayName.equalsIgnoreCase(roomName)) {
		roomSelected = room;
	    }
	}
	String serviceId = "";
	List<Service> listServices;
	listServices = ServiceAPIManager.getRequest(PropertiesReader
		.getServiceURL() + "/services");
	for (Service service : listServices) {
	    serviceId = service._id;
	}
	String putEndPoint = PropertiesReader.getServiceURL() + "/services/"
		+ serviceId + "/rooms/" + roomSelected._id;
	RoomAPIManager.putRequest(putEndPoint,
		roomSelected.getJsonObjectForPut(true));
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
	List<Room> listRooms = RoomAPIManager
		.getRequest("http://172.20.208.84:4040/rooms");
	int index = ((Integer.parseInt(page) - 1) * Integer.parseInt(pageSize)) + 1;

	for (int i = 0; i < listRooms.size(); i++) {
	    if (listRooms.get(i).displayName.contains(Integer.toString(index))) {
		String displayNameRoom = listRooms.get(i).displayName;
		if (room.getFirstRow().contains(displayNameRoom)) {
		    verification = true;
		}
	    }
	}
	Assert.assertTrue(verification);
    }

    @Then("^I validate if the previous page is displayed according the page size specified \"([^\"]*)\" and the page \"([^\"]*)\"$")
    public void i_validate_if_the_previous_page_is_displayed_according_the_page_size_specified_and_the_page(
	    String pageSize, String page) throws Throwable {
	ConferenceRoomsPage room = new ConferenceRoomsPage();

	boolean verification = false;
	List<Room> listRooms = RoomAPIManager
		.getRequest("http://172.20.208.84:4040/rooms");
	int index = ((Integer.parseInt(page) - 1) * Integer.parseInt(pageSize)) + 1;

	for (int i = 0; i < listRooms.size(); i++) {
	    if (listRooms.get(i).displayName.contains(Integer.toString(index))) {
		String displayNameRoom = listRooms.get(i).displayName;
		if (room.getFirstRow().contains(displayNameRoom)) {
		    verification = true;
		}
	    }
	}
	Assert.assertTrue(verification);
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
	Assert.assertTrue(verification);
    }
}
