package org.fundacionjala.automation.framework.utils.api.objects.admin;

import org.fundacionjala.automation.framework.utils.api.objects.RequestObject;
import org.json.JSONObject;

import com.google.gson.JsonParser;

public class Service extends RequestObject{

	public String credential_username;
	public String Hostname;
	public String Username;
	public String Password;
	public String type;
	public String name;
	public String version;
	public String serviceUrl;
	public String _id;
	public boolean impersonate;
	public int __v;
	
	
	public Service(String hostname, String username, String password){
		this.Hostname = hostname;
		this.Username = username;
		this.Password = password;
	}
	
	public Service(JSONObject obj){
		name = obj.get("name").toString();
		type = obj.get("type").toString();
		version = obj.get("version").toString();
		serviceUrl = obj.get("serviceUrl").toString();
		impersonate = Boolean.valueOf(obj.get("impersonate").toString());
		_id = obj.get("_id").toString();
		credential_username = new JSONObject(obj.get("credential").toString()).get("username").toString();
	}
	
	public JSONObject getJsonObject() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("username", Username);
		jsonObject.put("password", Password);
		jsonObject.put("hostname", Hostname);
		return jsonObject;
	}
	
	public Service(){
	}
	
}
