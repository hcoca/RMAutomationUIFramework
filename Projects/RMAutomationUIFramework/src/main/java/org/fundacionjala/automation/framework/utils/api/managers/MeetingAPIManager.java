package org.fundacionjala.automation.framework.utils.api.managers;

import java.util.ArrayList;
import java.util.List;

import org.fundacionjala.automation.framework.utils.api.objects.admin.Meeting;
import org.fundacionjala.automation.framework.utils.common.LogManager;
import org.fundacionjala.automation.framework.utils.common.PropertiesReader;
import org.json.JSONArray;
import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * This class make request by API service for Meetings
 * 
 * @author alejandraneolopan
 */
public class MeetingAPIManager {

    /**
     * Get all current Meetings objects by "/meetings" end-point
     * @param endPoint String URI (e.g. http://172.20.208.84:4040/meetings)
     * @param serviceId String with Service id
     * @return List<Meeting> current Service objects
     * @throws UnirestException
     */
    public static List<Meeting> getRequest(String endPoint, String serviceId,
	    String roomId) throws UnirestException {

	HttpResponse<JsonNode> jsonResponse = APIManager.request(endPoint
		+ "/services/" + serviceId + "/rooms/" + roomId + "/meetings",
		"get");
	LogManager.info("GET " + endPoint + " - Response:"
		+ jsonResponse.getStatusText());
	JSONArray a = jsonResponse.getBody().getArray();
	List<Meeting> meetingList = new ArrayList<Meeting>();
	if (jsonResponse.getStatusText().equals("OK")) {
	    for (int i = 0; i < a.length(); i++) {
		JSONObject obj = (JSONObject) a.get(i);
		meetingList.add(new Meeting(obj));
	    }
	}
	return meetingList;
    }

    /**
     * this method is for doing an API request for creating a meeting
     * @param roomName room where meeting will be created
     * @param body data for creating meeting
     * @throws UnirestException
     */
    public static void postRequest(String roomName, Meeting body)
	    throws UnirestException {
	String roomId = RoomAPIManager.getRoomByName(roomName)._id;
	String serviceId = ServiceAPIManager.getRequest(
		PropertiesReader.getServiceURL() + "/services").get(0)._id;
	String endPoint = PropertiesReader.getServiceURL() + "/services/"
		+ serviceId + "/rooms/" + roomId
		+ "/meetings?misrepresent=true";
	APIManager.request(endPoint, body.getJsonObject(), "post");
    }

    /**
     * this method is to do a meeting delete request 
     * @param roomName
     * @param body the meeting which will be deleted
     * @throws UnirestException
     */
    public static void deleteRequest(String roomName, Meeting body)
	    throws UnirestException {
	String roomId = RoomAPIManager.getRoomByName(roomName)._id;
	String serviceId = ServiceAPIManager.getRequest(
		PropertiesReader.getServiceURL() + "/services").get(0)._id;
	String endPoint = PropertiesReader.getServiceURL() + "/services/"
		+ serviceId + "/rooms/" + roomId
		+ "/meetings/cancel?meetingId=" + body._id
		+ "&misrepresent=true";
	HttpResponse<JsonNode> jsonResponse = APIManager.request(endPoint,
		body.getJsonObject(), "post");
	LogManager.info("DELETE " + endPoint + " - Response:"
		+ jsonResponse.getStatusText());
    }
    
    /**
     * this method is to get a meeting by its subject
     * @param roomName the room where is the meeting
     * @param meetingSubject the subject of the meeting wanted
     * @return the meeting wanted
     * @throws UnirestException
     */
    public static Meeting getMeetingBySubject(String roomName,
	    String meetingSubject) throws UnirestException {
	String roomId = RoomAPIManager.getRoomByName(roomName)._id;
	String serviceId = ServiceAPIManager.getRequest(
		PropertiesReader.getServiceURL() + "/services").get(0)._id;

	List<Meeting> meetingList = getRequest(
		PropertiesReader.getServiceURL(), serviceId, roomId);
	Meeting meetingSelected = new Meeting();
	for (Meeting meeting : meetingList) {
	    if (meeting.title.equalsIgnoreCase(meetingSubject)) {
		meetingSelected = meeting;
	    }
	}
	return meetingSelected;
    }
}
