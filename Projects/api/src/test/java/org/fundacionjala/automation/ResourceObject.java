package org.fundacionjala.automation;

import org.json.JSONObject;

public class ResourceObject {
	public String name;
	public String customName;
	public String fontIcon;
	public String from;
	public String description;
	public int __v;
	public String _id;
	
	public ResourceObject(JSONObject obj){
		name = obj.get("name").toString();
		customName = obj.get("customName").toString();
		fontIcon = obj.get("fontIcon").toString();
		from = obj.get("from").toString();
		description = obj.get("description").toString();
		__v = Integer.parseInt(obj.get("__v").toString());
		_id = obj.get("_id").toString();
	}
}
