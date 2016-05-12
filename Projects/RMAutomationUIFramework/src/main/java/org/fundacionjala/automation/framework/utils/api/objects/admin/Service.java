package org.fundacionjala.automation.framework.utils.api.objects.admin;

import org.fundacionjala.automation.framework.utils.api.objects.RequestObject;
import org.json.JSONObject;


/**
 * Representation of MongoDB Service collection
 * @author alejandraneolopan
 *
 */
public class Service extends RequestObject {

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

    /**
     * Initialize a Email Server with the main data for create a POST request
     * @param hostname Exchange server name (e.g. roommanager.local)
     * @param username Account in Exchange server
     * @param password Password of the account
     */
    public Service(String hostname, String username, String password) {
	this.Hostname = hostname;
	this.Username = username;
	this.Password = password;
    }
    /**
     * Initialize a Email Server from a JSON response from API service
     * @param obj JSON which contains one item of Service collection
     */
    public Service(JSONObject obj) {
	name = obj.get("name").toString();
	type = obj.get("type").toString();
	version = obj.get("version").toString();
	serviceUrl = obj.get("serviceUrl").toString();
	impersonate = Boolean.valueOf(obj.get("impersonate").toString());
	_id = obj.get("_id").toString();
	credential_username = new JSONObject(obj.get("credential").toString())
		.get("username").toString();
    }
    /**
     * Create JSOJ object with Email Server main data for POST request body
     * @return JSON object
     */
    public JSONObject getJsonObject() {
	JSONObject jsonObject = new JSONObject();
	jsonObject.put("username", Username);
	jsonObject.put("password", Password);
	jsonObject.put("hostname", Hostname);
	return jsonObject;
    }
    
    /**
     * Constructor by default
     */
    public Service() {
    }

}
