package org.fundacionjala.automation.framework.utils.api.objects.admin;

import org.fundacionjala.automation.framework.utils.api.objects.RequestObject;
import org.json.JSONObject;

public class Location extends RequestObject{
	public String name;
	public String customName;
	public String parentId;
	public String path;
	public String description;
	public int __v;
	public String _id;
	
	public Location(String name, String customName, String parentId, String path, String description){
		this.name = name;
		this.customName = customName;
		this.parentId = parentId;
		this.path = path;
		this.description = description; 
	}
	
	public Location(JSONObject obj){
		name = obj.get("name").toString();
		customName = obj.get("customName").toString();
		path = obj.get("path").toString();
		description = obj.get("description").toString();
		__v = Integer.parseInt(obj.get("__v").toString());
		_id = obj.get("_id").toString();
	}
	
	public JSONObject getJsonObject() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("name", this.name);
		jsonObject.put("customName", this.customName);
		jsonObject.put("parentId", this.parentId);
		jsonObject.put("path", this.path);
		jsonObject.put("description", this.description);
		return jsonObject;
	}
	
	public Location(){
	}
}
