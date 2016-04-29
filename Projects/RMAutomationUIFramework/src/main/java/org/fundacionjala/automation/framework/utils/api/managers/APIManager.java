package org.fundacionjala.automation.framework.utils.api.managers;
import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class APIManager {
	
	private static HttpResponse<JsonNode> jsonResponse = null;
	private static String requestType;
	private static JSONObject jsonObject;
	private static String endPoint;
	
	public static HttpResponse<JsonNode> request(String endpoint, JSONObject jsonobject, String requesttype) throws UnirestException{
		endPoint = endpoint;
		jsonObject = jsonobject;
		requestType = requesttype;
		return execute();
	}
	
	public static HttpResponse<JsonNode> request(String endpoint, String requesttype) throws UnirestException{
		endPoint = endpoint;
		requestType = requesttype;
		return execute();
	}
	
	private static HttpResponse<JsonNode> execute() throws UnirestException{
		switch (requestType) {
		case "get":
			jsonResponse = Unirest.get(endPoint)
			  .header("accept", "application/json")
			  .asJson();
			break;
        case "post":
        	jsonResponse = Unirest.post(endPoint)
			  .header("Content-Type", "application/json")
			  .body(jsonObject)
			  .asJson();
        	break;
        case "put":
			break;
        case "delete":
			break;
		}
		return jsonResponse;
	}
	
}
