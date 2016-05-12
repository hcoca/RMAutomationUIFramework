package org.fundacionjala.automation.framework.utils.api.managers;

import java.util.ArrayList;
import java.util.List;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Room;
import org.fundacionjala.automation.framework.utils.common.LogManager;
import org.fundacionjala.automation.framework.utils.common.PropertiesReader;
import org.json.JSONArray;
import org.json.JSONObject;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * This class make request by API service for Rooms
 * @author alejandraneolopan
 */
public class RoomAPIManager {

    /**
     * Get all current rooms objects by "/rooms" end-point
     * @param endPoint
     *            String URI (e.g. http://172.20.208.84:4040/rooms)
     * @return List<Room> current Room objects
     * @throws UnirestException
     */
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

    /**
     * Updates a room
     * @param endPoint
     *            String URI (e.g. http://172.20.208.84:4040/rooms)
     * @param requestBody
     *            JSON with updated data for room
     * @throws UnirestException
     */
    public static void putRequest(String endPoint, JSONObject body)
	    throws UnirestException {

	HttpResponse<JsonNode> jsonResponse = APIManager.request(endPoint,
		body, "put");
    }

    /**
     * Get all current rooms objects by "/rooms" end-point according a criteria
     * @param endPoint
     *            String URI (e.g. http://172.20.208.84:4040/rooms)
     * @return List<Room> current Room objects
     * @throws UnirestException
     */
    public static List<String> getRoomsByCriteria(String criteriaFilter)
	    throws UnirestException {
	List<Room> list = getRequest(PropertiesReader.getServiceURL() + "/" 
				    + PropertiesReader.getRoomsFieldName());
	List<String> listString = new ArrayList<String>();
	for (Room room : list) {
	    if (room.displayName.contains(criteriaFilter)) {
		listString.add(room.displayName);
	    }
	}
	return listString;
    }

}
