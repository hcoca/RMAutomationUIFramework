package org.fundacionjala.automation.framework.utils.api.managers;

import java.util.ArrayList;
import java.util.List;

import org.fundacionjala.automation.framework.utils.api.objects.RequestObject;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Location;
import org.fundacionjala.automation.framework.utils.common.LogManager;
import org.json.JSONArray;
import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * This class represents an API manager for locations.
 * @author ArielYanarico
 *
 */
public class LocationAPIManager {

    /**
     * Does a get request of all locations.
     * @param endPoint end point of locations.
     * @return a list of locations.
     * @throws UnirestException
     */
    public static List<Location> getRequest(String endPoint)
	    throws UnirestException {
	HttpResponse<JsonNode> jsonResponse = APIManager.request(endPoint,
		"get");
	LogManager.info("GET Response:" + jsonResponse.getStatusText());
	JSONArray a = jsonResponse.getBody().getArray();
	List<Location> locationList = new ArrayList<Location>();

	for (int i = 0; i < a.length(); i++) {
	    JSONObject obj = (JSONObject) a.get(i);
	    locationList.add(new Location(obj));
	}

	return locationList;
    }

    /**
     * Does a post request to add a new location.
     * @param endPoint end point of locations.
     * @param requestBody the location which will be added.
     * @return the location added.
     * @throws UnirestException
     */
    public static Location postRequest(String endPoint,
	    RequestObject requestBody) throws UnirestException {
	Location location = new Location();
	location = (Location) requestBody;
	JSONObject jsonObject = location.getJsonObject();
	HttpResponse<JsonNode> jsonResponse = APIManager.request(endPoint,
		jsonObject, "post");
	LogManager.info("POST Response:" + jsonResponse.getBody().getObject());
	return new Location(jsonResponse.getBody().getObject());
    }

    /**
     * Does a delete request to remove a location.
     * @param endPoint end point of locations.
     * @param locationID ID of location which will be deleted.
     * @return the location deleted.
     * @throws UnirestException
     */
    public static JsonNode deleteRequest(String endPoint, String locationID)
	    throws UnirestException {
	String deleteEndPoint = endPoint + "/" + locationID;
	LogManager.info("Deleting by API:" + deleteEndPoint);
	HttpResponse<JsonNode> jsonResponse = APIManager.request(
		deleteEndPoint, "delete");
	LogManager.info("DELETE Response:" + jsonResponse.getBody());
	return jsonResponse.getBody();
    }
}
