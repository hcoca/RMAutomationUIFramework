package org.fundacionjala.automation.scenario.steps.admin.conferenceRoom;

import java.util.List;

import org.fundacionjala.automation.framework.pages.admin.home.AdminPage;
import org.fundacionjala.automation.framework.pages.tablet.settings.ConnectionPage;
import org.fundacionjala.automation.framework.pages.tablet.settings.NavigationPage;
import org.fundacionjala.automation.framework.utils.api.managers.RoomAPIManager;
import org.fundacionjala.automation.framework.utils.api.managers.ServiceAPIManager;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Room;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Service;
import org.fundacionjala.automation.framework.utils.common.PropertiesReader;
import org.junit.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class RoomIsDisabled {
	@Given("^I have \"([^\"]*)\" room enabled$")
	public void i_have_room_enabled(String roomName) throws Throwable {
		List<Room> roomList = RoomAPIManager.getRequest(PropertiesReader.getServiceURL() + "/rooms");
		Room roomSelected = new Room();
		for (Room room : roomList) {
			if(room.displayName.equalsIgnoreCase(roomName)){
				roomSelected = room;
			}
		}
		String serviceId = "";
		List<Service> listServices;
		listServices = ServiceAPIManager.getRequest(PropertiesReader.getServiceURL() + "/services");
		for(Service service : listServices) {
			serviceId = service._id;
		}
		String putEndPoint = PropertiesReader.getServiceURL() + "/services/" + serviceId + "/rooms/" + roomSelected._id;
		RoomAPIManager.putRequest(putEndPoint, roomSelected.getJsonObjectForPut(true));
	}

	@When("^I disabled \"([^\"]*)\" room$")
	public void i_disabled_room(String roomName) throws Throwable {
		AdminPage Home = new AdminPage();
		Home
			.refreshPage()
			.leftMenu
			.clickOnConferenceRoomsButton()
			.clickOnTurnOnOffButton(roomName);
	}

	@Then("^I validate if the room \"([^\"]*)\" is disabled in tablet page$")
	public void i_validate_if_the_room_is_disabled_in_tablet_page(String roomName) throws Throwable {
		ConnectionPage connection = new ConnectionPage();
		NavigationPage navigation = connection
				    	.setUpServiceURL("http://172.20.208.84:4040/")
				    	.clickOnSaveButton()
				    	.clickOnNavigationButton();
		
		Assert.assertFalse(
			navigation
				.clickOnRoomToggleButton()
				.verifyIfExistRoomInList(roomName)
			);
		
		List<Room> roomList = RoomAPIManager.getRequest(PropertiesReader.getServiceURL() + "/rooms");
		Room roomSelected = new Room();
		for (Room room : roomList) {
			if(room.displayName.equalsIgnoreCase(roomName)){
				roomSelected = room;
			}
		}
		String serviceId = "";
		List<Service> listServices;
		listServices = ServiceAPIManager.getRequest(PropertiesReader.getServiceURL() + "/services");
		for(Service service : listServices) {
			serviceId = service._id;
		}
		String putEndPoint = PropertiesReader.getServiceURL() + "/services/" + serviceId + "/rooms/" + roomSelected._id;
		RoomAPIManager.putRequest(putEndPoint, roomSelected.getJsonObjectForPut(true));
	}
}
