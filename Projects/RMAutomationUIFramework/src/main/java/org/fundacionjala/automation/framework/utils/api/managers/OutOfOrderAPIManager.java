package org.fundacionjala.automation.framework.utils.api.managers;

import org.fundacionjala.automation.framework.utils.api.objects.RequestObject;
import org.fundacionjala.automation.framework.utils.api.objects.admin.OutOfOrder;
import org.fundacionjala.automation.framework.utils.common.LogManager;
import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;

public class OutOfOrderAPIManager {

	public static OutOfOrder postRequest(String endPoint, RequestObject requestBody ) throws UnirestException{
		OutOfOrder outOfOrder = new OutOfOrder();
		outOfOrder = (OutOfOrder) requestBody;
		JSONObject jsonObject = outOfOrder.getJsonObject();
		HttpResponse<JsonNode> jsonResponse = APIManager.request(endPoint, jsonObject, "post");
		LogManager.info("POST Response:" +jsonResponse.getBody().getObject());
		return new OutOfOrder(jsonResponse.getBody().getObject());
	}
}
