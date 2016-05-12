package org.fundacionjala.automation.framework.utils.api.managers;

import java.util.ArrayList;
import java.util.List;

import org.fundacionjala.automation.framework.utils.api.objects.RequestObject;
import org.fundacionjala.automation.framework.utils.api.objects.admin.OutOfOrder;
import org.fundacionjala.automation.framework.utils.common.LogManager;
import org.json.JSONArray;
import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * This class make request by API service for Out-of-Orders
 * @author alejandraneolopan
 */
public class OutOfOrderAPIManager {
    
   /**
     * Create a new Out-Of-Order
     * @param endPoint String URI (e.g. http://172.20.208.84:4040/out-of-order)
     * @param requestBody JSON with main data for an out-of-order 
     * @return OutOfOrder object created
     * @throws UnirestException
     */
    public static OutOfOrder postRequest(String endPoint,
	    RequestObject requestBody) throws UnirestException {
	OutOfOrder outOfOrder = new OutOfOrder();
	outOfOrder = (OutOfOrder) requestBody;
	JSONObject jsonObject = outOfOrder.getJsonObject();
	HttpResponse<JsonNode> jsonResponse = APIManager.request(endPoint,
		jsonObject, "post");
	LogManager.info("POST Response:" + jsonResponse.getBody().getObject());
	return new OutOfOrder(jsonResponse.getBody().getObject());
    }
    
    /**
     * Get all current out-of-order objects by "/out-of-orders" end-point
     * @param endPoint String URI (e.g. http://172.20.208.84:4040/out-of-orders)
     * @return List<OutOfOrder> current OutOfOrder objects
     * @throws UnirestException
     */
    public static List<OutOfOrder> getRequest(String endPoint)
	    throws UnirestException {
	HttpResponse<JsonNode> jsonResponse = APIManager.request(endPoint,
		"get");
	LogManager.info("GET Response:" + jsonResponse.getStatusText());
	JSONArray a = jsonResponse.getBody().getArray();
	List<OutOfOrder> outoforders = new ArrayList<OutOfOrder>();
	if (jsonResponse.getStatusText().equals("OK")) {
	    for (int i = 0; i < a.length(); i++) {
		JSONObject obj = (JSONObject) a.get(i);
		outoforders.add(new OutOfOrder(obj));
	    }
	}
	return outoforders;
    }
}
