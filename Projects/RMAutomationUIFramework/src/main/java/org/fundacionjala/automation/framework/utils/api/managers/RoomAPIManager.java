package org.fundacionjala.automation.framework.utils.api.managers;

import java.util.ArrayList;
import java.util.List;

import org.fundacionjala.automation.framework.utils.api.objects.admin.Room;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Service;
import org.fundacionjala.automation.framework.utils.common.LogManager;
import org.fundacionjala.automation.framework.utils.common.PropertiesReader;
import org.json.JSONArray;
import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;

public class RoomAPIManager {
    public static List<Room> getRequest(String endPoint)
	    throws UnirestException {
	HttpResponse<JsonNode> jsonResponse = APIManager.request(endPoint,
		"get");
	LogManager.info("GET Response:" + jsonResponse.getStatusText());
	JSONArray a = jsonResponse.getBody().getArray();
	List<Room> roomsList = new ArrayList<Room>();
	if (jsonResponse.getStatusText().equals("OK")) {
	    for (int i = 0; i < a.length(); i++) {
		JSONObject obj = (JSONObject) a.get(i);
		roomsList.add(new Room(obj));
	    }
	}
	return roomsList;
    }

    public static void putRequest(String endPoint, JSONObject body)
	    throws UnirestException {
	HttpResponse<JsonNode> jsonResponse = APIManager.request(endPoint,
		body, "put");
    }

    public static List<String> getRoomsByCriteria(String criteriaFilter)
	    throws UnirestException {
	List<Room> list = getRequest("http://172.20.208.84:4040/rooms");
	List<String> listString = new ArrayList<String>();
	for (Room room : list) {
	    if (room.displayName.contains(criteriaFilter)) {
		listString.add(room.displayName);
	    }
	}
	return listString;
    }

    public static Room getRoomByName(String roomName) throws UnirestException {
	List<Room> roomList = getRequest(PropertiesReader.getServiceURL()
		+ "/rooms");
	Room roomSelected = new Room();
	for (Room room : roomList) {
	    if (room.displayName.equalsIgnoreCase(roomName)) {
		roomSelected = room;
	    }
	}
	return roomSelected;
    }

    /**
     * This function is for changing status (enabled/disabled) of a specific
     * room
     * @param roomName it is name of a specific room
     * @param status
     * @throws UnirestException
     */
    public static void changeStatusOfRoom(String roomName, boolean status)
	    throws UnirestException {
	Room roomSelected = RoomAPIManager.getRoomByName(roomName);
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
		roomSelected.getJsonObjectForPut(status));
    }
}
