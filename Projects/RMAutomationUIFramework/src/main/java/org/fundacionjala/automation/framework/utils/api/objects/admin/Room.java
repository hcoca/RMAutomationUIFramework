package org.fundacionjala.automation.framework.utils.api.objects.admin;

import org.fundacionjala.automation.framework.utils.api.objects.RequestObject;
import org.json.JSONObject;

public class Room extends RequestObject{
	public String _id;
	public String displayName;
	public String emailAddress;
	public String serviceId;
	public String customDisplayName;
	
	public Room(String name, String customName){
		this.displayName = name;
		this.customDisplayName = customName;
	}
	
	public Room(JSONObject obj){
		_id = obj.get("_id").toString();
		displayName = obj.get("displayName").toString();
		emailAddress = obj.get("emailAddress").toString();
		serviceId = obj.get("serviceId").toString();
		customDisplayName = obj.get("customDisplayName").toString();
	}
	
	public JSONObject getJsonObject() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("_id", this._id);
		jsonObject.put("displayName", this.displayName);
		jsonObject.put("emailAddress", this.emailAddress);
		jsonObject.put("serviceId", this.serviceId);
		jsonObject.put("customDisplayName", this.customDisplayName);
		return jsonObject;
	}
	
	public Room(){
	}

}
