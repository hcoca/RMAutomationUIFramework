package org.fundacionjala.automation.framework.utils.api.managers;

import org.fundacionjala.automation.framework.utils.api.objects.RequestObject;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Settings;
import org.fundacionjala.automation.framework.utils.common.LogManager;
import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;

public class SettingsAPIManager {

	public static Settings getRequest(String endPoint) throws UnirestException{
		HttpResponse<JsonNode> jsonResponse = APIManager.request(endPoint, "get");
		LogManager.info("GET Response:" + jsonResponse.getStatusText());
		JSONObject obj = (JSONObject) jsonResponse.getBody().getArray().get(0);
		Settings settings = new Settings(obj);
		
		return settings;
	}
	
	public static Settings putRequest(String endPoint, RequestObject requestBody ) throws UnirestException {
		
		Settings settings = new Settings();
		settings = (Settings) requestBody;
		JSONObject jsonObject = settings.getJsonObject();
		HttpResponse<JsonNode> jsonResponse = APIManager.request(endPoint, jsonObject, "put");
		LogManager.info("PUT Response:" +jsonResponse.getBody().getObject());
		
		return null;
	}
}