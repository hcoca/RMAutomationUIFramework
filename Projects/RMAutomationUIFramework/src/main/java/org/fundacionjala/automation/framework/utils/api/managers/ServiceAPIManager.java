package org.fundacionjala.automation.framework.utils.api.managers;

import java.util.ArrayList;
import java.util.List;

import org.fundacionjala.automation.framework.utils.api.objects.RequestObject;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Service;
import org.fundacionjala.automation.framework.utils.common.LogManager;
import org.json.JSONArray;
import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * This class make request by API service for Email Server page
 * @author alejandraneolopan
 */
public class ServiceAPIManager {
    
    /**
     * Get all current Service objects by "/services" end-point
     * @param endPoint String URI (e.g. http://172.20.208.84:4040/services)
     * @return List<Service> current Service objects
     * @throws UnirestException
     */
    public static List<Service> getRequest(String endPoint)
	    throws UnirestException {
	
	HttpResponse<JsonNode> jsonResponse = APIManager.request(endPoint,
		"get");
	LogManager.info("GET " + endPoint + " - Response:"
		+ jsonResponse.getStatusText());
	JSONArray a = jsonResponse.getBody().getArray();
	List<Service> serviceList = new ArrayList<Service>();
	if (jsonResponse.getStatusText().equals("OK")) {
	    for (int i = 0; i < a.length(); i++) {
		JSONObject obj = (JSONObject) a.get(i);
		serviceList.add(new Service(obj));
	    }
	}
	return serviceList;
    }
    
    /**
     * Create a new service
     * @param endPoint URI (e.g. http://172.20.208.84:4040/services)
     * @param requestBody JSON with hostname, username and password
     * @return Service created or null
     * @throws UnirestException
     */
    public static Service postRequest(String endPoint, RequestObject requestBody)
	    throws UnirestException {
	
	Service service = new Service();
	service = (Service) requestBody;
	JSONObject jsonObject = service.getJsonObject();
	HttpResponse<JsonNode> jsonResponse = APIManager.request(endPoint,
		jsonObject, "post");
	LogManager.info("POST Response:" + jsonResponse.getBody().getObject());
	return new Service(jsonResponse.getBody().getObject());
    }

    /**
     * Deletes a specific service
     * @param endPoint URI (e.g. http://172.20.208.84:4040/services)
     * @param serviceID String with Service id
     * @return JSON response body
     * @throws UnirestException
     */
    public static JsonNode deleteRequest(String endPoint, String serviceID)
	    throws UnirestException {
	
	String deleteEndPoint = endPoint + "/" + serviceID;
	LogManager.info("Deleting by API:" + deleteEndPoint);
	HttpResponse<JsonNode> jsonResponse = APIManager.request(
		deleteEndPoint, "delete");
	LogManager.info("DELETE Response:" + jsonResponse.getBody());
	return jsonResponse.getBody();
    }

    /**
     * Get a specific service
     * @param endPoint URI (e.g. http://172.20.208.84:4040/services)
     * @param serviceID String with Service id
     * @return Service object
     * @throws UnirestException
     */
    public static Service getRequest(String endPoint, String resourceID)
	    throws UnirestException {
	
	String getEndPoint = endPoint + "/" + resourceID;
	HttpResponse<JsonNode> jsonResponse = APIManager.request(getEndPoint,
		"get");
	LogManager.info("GET Response:" + jsonResponse.getBody());
	return new Service(jsonResponse.getBody().getObject());
    }

    /**
     * Update Email Server impersonation state
     * @param endPoint URI (e.g. http://172.20.208.84:4040/services)
     * @param serviceID String with Service id
     * @param impersonate Boolean with impersonate status (true, false) 
     * @return null
     * @throws UnirestException
     */
    public static Service putImpersonationRequest(String endPoint, String id,
	    boolean impersonate) throws UnirestException {
	
	String putEndPoint = endPoint + "/" + id;
	JSONObject jsonObject = new JSONObject();
	jsonObject.put("impersonate", impersonate);
	HttpResponse<JsonNode> jsonResponse = APIManager.request(putEndPoint,
		jsonObject, "put");
	LogManager.info("PUT Response:" + jsonResponse.getBody().getObject());

	return null;
    }
}
