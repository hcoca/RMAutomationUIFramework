package org.fundacionjala.automation.framework.utils.api.objects.admin;

import org.fundacionjala.automation.framework.utils.api.objects.RequestObject;
import org.json.JSONObject;


public class Resource extends RequestObject{
	public String name;
	public String customName;
	public String fontIcon;
	public String from;
	public String description;
	public int __v;
	public String _id;
	
	public Resource(){
	}
	
	public Resource(String name, String customName, String fontIcon, String from, String description){
		this.name = name;
		this.customName = customName;
		this.fontIcon = fontIcon;
		this.from = from;
		this.description = description; 
	}
	
	public Resource(JSONObject obj){
		name = obj.get("name").toString();
		customName = obj.get("customName").toString();
		fontIcon = obj.get("fontIcon").toString();
		from = obj.get("from").toString();
		description = obj.get("description").toString();
		__v = Integer.parseInt(obj.get("__v").toString());
		_id = obj.get("_id").toString();
	}
	
	public JSONObject getJsonObject() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("name", this.name);
		jsonObject.put("customName", this.customName);
		jsonObject.put("fontIcon", this.fontIcon);
		jsonObject.put("from", this.from);
		jsonObject.put("description", this.description);
		return jsonObject;
	}
	
}
