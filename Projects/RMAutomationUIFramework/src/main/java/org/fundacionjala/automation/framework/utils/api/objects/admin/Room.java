package org.fundacionjala.automation.framework.utils.api.objects.admin;

import java.util.ArrayList;

import org.fundacionjala.automation.framework.utils.api.objects.RequestObject;
import org.json.JSONObject;
/**
 * Representation of MongoDB Room collection
 * @author alejandraneolopan
 *
 */
public class Room extends RequestObject {
    public String _id;
    public String displayName;
    public String emailAddress;
    public String serviceId;
    public String customDisplayName;
    
    /**
     * Initialize a Room object with the main data
     * @param name Room name
     * @param customName Room custom name
     */
    public Room(String name, String customName) {
	this.displayName = name;
	this.customDisplayName = customName;
    }
    
    /**
     * Initialize a Room from a JSON response from API service
     * @param obj JSON which contains one item of Rooms collection
     */
    public Room(JSONObject obj) {
	_id = obj.get("_id").toString();
	displayName = obj.get("displayName").toString();
	emailAddress = obj.get("emailAddress").toString();
	serviceId = obj.get("serviceId").toString();
	customDisplayName = obj.get("customDisplayName").toString();
    }

    /**
     * Create JSON object with Rooms main data for POST request body
     * @return JSON object
     */
    public JSONObject getJsonObject() {
	JSONObject jsonObject = new JSONObject();
	jsonObject.put("_id", this._id);
	jsonObject.put("displayName", this.displayName);
	jsonObject.put("emailAddress", this.emailAddress);
	jsonObject.put("serviceId", this.serviceId);
	jsonObject.put("customDisplayName", this.customDisplayName);
	return jsonObject;
    }

    public Room() {
    }

    public JSONObject getJsonObjectForPut(boolean enabled) {
	ArrayList<String> resources = new ArrayList<String>();
	JSONObject jsonObject = new JSONObject();
	jsonObject.put("customDisplayName", this.customDisplayName);
	jsonObject.put("serviceId", this.serviceId);
	jsonObject.put("enabled", enabled);
	jsonObject.put("emailAddress", this.emailAddress);
	jsonObject.put("displayName", this.displayName);
	jsonObject.put("code", "");
	jsonObject.put("resources", resources);
	jsonObject.put("__v", "");
	return jsonObject;
    }

}
