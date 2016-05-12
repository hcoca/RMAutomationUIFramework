package org.fundacionjala.automation.framework.utils.api.managers;

import java.util.ArrayList;
import java.util.List;

import org.fundacionjala.automation.framework.utils.api.objects.admin.Meeting;
import org.fundacionjala.automation.framework.utils.common.LogManager;
import org.json.JSONArray;
import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * This class make request by API service for Meetings
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
	
	HttpResponse<JsonNode> jsonResponse = APIManager.request(endPoint + "/"
		+ serviceId + "/rooms/" + roomId + "/meetings", "get");
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

}
