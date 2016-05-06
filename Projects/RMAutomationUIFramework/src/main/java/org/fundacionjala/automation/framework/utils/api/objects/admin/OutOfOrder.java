package org.fundacionjala.automation.framework.utils.api.objects.admin;

import org.fundacionjala.automation.framework.utils.api.objects.RequestObject;
import org.json.JSONObject;

public class OutOfOrder extends RequestObject{
	public String from;
	public String to;
	public String roomId;
	public String title;
	public String description;
	public boolean sendEmail;
	public String serviceId;
	
	public OutOfOrder(String from, String to, String roomId,String title, boolean sendEmail){
		this.from = from;
		this.to = to;
		this.roomId = roomId;
		this.title = title; 
		this.sendEmail = sendEmail;
	}
	
	public OutOfOrder(JSONObject obj){
		from = obj.get("from").toString();
		to = obj.get("to").toString();
		roomId = obj.get("roomId").toString();
		title = obj.get("title").toString();
		sendEmail = Boolean.parseBoolean(obj.get("sendEmail").toString());
	}
	
	public JSONObject getJsonObject() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("from", this.from);
		jsonObject.put("to", this.to);
		jsonObject.put("roomId", this.roomId);
		jsonObject.put("title", this.title);
		jsonObject.put("sendEmail", this.sendEmail);
		return jsonObject;
	}
	
	public OutOfOrder(){
	}
}
