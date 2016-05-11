package org.fundacionjala.automation.scenario.steps.admin.conferenceRoom;

import java.util.List;

import org.fundacionjala.automation.framework.utils.api.managers.RoomAPIManager;
import org.fundacionjala.automation.framework.utils.api.managers.ServiceAPIManager;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Room;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Service;
import org.fundacionjala.automation.framework.utils.common.PropertiesReader;

import cucumber.api.java.en.Given;

public class ConferenceRoomGivenSteps {
	
	@Given("^I have \"([^\"]*)\" room disabled$")
	public void i_have_room_disabled(String roomName) throws Throwable {
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
		RoomAPIManager.putRequest(putEndPoint, roomSelected.getJsonObjectForPut(false));	
	}
	
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

}
