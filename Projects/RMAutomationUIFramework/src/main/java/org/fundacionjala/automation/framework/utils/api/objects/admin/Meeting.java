package org.fundacionjala.automation.framework.utils.api.objects.admin;

import org.fundacionjala.automation.framework.utils.api.objects.RequestObject;
import org.json.JSONObject;

public class Meeting extends RequestObject{
	public String start;
	public String end;
	public String roomId;
	public String title;
	public String description;
	public String serviceId;
	
	public Meeting(String from, String to, String roomId,String subject, String serviceId){
		this.start = from;
		this.end = to;
		this.roomId = roomId;
		this.title = subject; 
		this.serviceId = serviceId;
	}
	
	public Meeting(JSONObject obj){
		start = obj.get("start").toString();
		end = obj.get("end").toString();
		roomId = obj.get("roomId").toString();
		title = obj.get("title").toString();
		serviceId = obj.get("serviceId").toString();
	}
	
	public JSONObject getJsonObject() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("start", this.start);
		jsonObject.put("end", this.end);
		jsonObject.put("roomId", this.roomId);
		jsonObject.put("title", this.title);
		return jsonObject;
	}
	
	public Meeting(){
	}
}
