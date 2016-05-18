package org.fundacionjala.automation.framework.utils.api.objects.admin;

import java.util.ArrayList;

import org.fundacionjala.automation.framework.utils.api.objects.RequestObject;
import org.json.JSONObject;

/**
 * Representation of MongoDB Meeting collection
 * @author alejandraneolopan
 */
public class Meeting extends RequestObject {
    public String organizer;
    public String title;
    public String start;
    public String end;
    public String location;
    public String roomEmail;
    public ArrayList<String> resources = new ArrayList<String>();
    public ArrayList<String> attendees = new ArrayList<String>();
    
    public Meeting(String organizer, String title, String start, String end, 
	    String location, String roomEmail, String resources, String attendees) {
	
	this.organizer = organizer;
	this.title = title;
	this.start = start;
	this.end = end;
	this.location = location;
	this.roomEmail = roomEmail;
	this.resources.add(resources);
	this.attendees.add(attendees);
    }
    
    /**
     * Initialize a Meeting from a JSON response from API service
     * @param obj JSON which contains one item of Meetings collection
     */
    public Meeting(JSONObject obj) {
	/*
	start = obj.get("start").toString();
	end = obj.get("end").toString();
	roomId = obj.get("roomId").toString();
	title = obj.get("title").toString();
	serviceId = obj.get("serviceId").toString();
	*/
    }
    
    /**
     * Create JSON object with Meeting main data for POST request body
     * @return JSON object
     */
    public JSONObject getJsonObject() {
	JSONObject jsonObject = new JSONObject();
	jsonObject.put("organizer", this.organizer);
	jsonObject.put("title", this.title);
	jsonObject.put("start", this.start);
	jsonObject.put("end", this.end);
	jsonObject.put("location", this.location);
	jsonObject.put("roomEmail", this.roomEmail);
	jsonObject.put("resources", this.resources);
	jsonObject.put("attendees", this.attendees);
	return jsonObject;
    }
    
    /**
     * Constructor by default
     */
    public Meeting() {
    }
}
