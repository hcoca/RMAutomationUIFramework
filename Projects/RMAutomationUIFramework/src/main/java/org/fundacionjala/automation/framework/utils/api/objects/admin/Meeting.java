package org.fundacionjala.automation.framework.utils.api.objects.admin;

import org.fundacionjala.automation.framework.utils.api.objects.RequestObject;
import org.json.JSONObject;

/**
 * Representation of MongoDB Meeting collection
 * @author alejandraneolopan
 */
public class Meeting extends RequestObject {
    public String start;
    public String end;
    public String roomId;
    public String title;
    public String description;
    public String serviceId;

    /**
     * Initialize a Meeting object with the main data for create a POST request
     * @param from String DateTime start
     * @param to String DateTime finish
     * @param roomId String room Id
     * @param subject String Meeting subject
     * @param serviceId String Service Id
     */
    public Meeting(String from, String to, String roomId, String subject,
	    String serviceId) {
	this.start = from;
	this.end = to;
	this.roomId = roomId;
	this.title = subject;
	this.serviceId = serviceId;
    }
    
    /**
     * Initialize a Meeting from a JSON response from API service
     * @param obj JSON which contains one item of Meetings collection
     */
    public Meeting(JSONObject obj) {
	start = obj.get("start").toString();
	end = obj.get("end").toString();
	roomId = obj.get("roomId").toString();
	title = obj.get("title").toString();
	serviceId = obj.get("serviceId").toString();
    }
    
    /**
     * Create JSON object with Meeting main data for POST request body
     * @return JSON object
     */
    public JSONObject getJsonObject() {
	JSONObject jsonObject = new JSONObject();
	jsonObject.put("start", this.start);
	jsonObject.put("end", this.end);
	jsonObject.put("roomId", this.roomId);
	jsonObject.put("title", this.title);
	return jsonObject;
    }
    
    /**
     * Constructor by default
     */
    public Meeting() {
    }
}
