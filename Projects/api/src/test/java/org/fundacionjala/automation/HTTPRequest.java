package org.fundacionjala.automation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.axis.AxisProperties;
import org.apache.http.HttpHost;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;
import org.testng.annotations.Test;

import bsh.util.JRemoteApplet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;


public class HTTPRequest {
	
	@Test
	public void postRequest() throws UnirestException{
		
		HttpResponse<JsonNode> jsonResponse = Unirest.post("http://172.20.208.84:4040/resources")
				  .header("Content-Type", "application/json")
				  .body("{\"name\":\"monitdor\",\"customName\":\"Monsitor\",\"fontIcon\":\"fa fa-desktop\",\"from\":\"\",\"description\":\"description of resource\"}")
				  .asJson();
	}	
	
	@Test
	public void getRequest() throws UnirestException{

		HttpResponse<JsonNode> jsonResponse = Unirest.get("http://172.20.208.84:4040/resources")
				  .header("accept", "application/json")
				  .asJson();
		
		JSONArray a = jsonResponse.getBody().getArray();
		List<ResourceObject> resourceList = new ArrayList<ResourceObject>();
		for (int i = 0; i < a.length() ; i++) {
			JSONObject obj = (JSONObject) a.get(i);
			resourceList.add(new ResourceObject(obj));
		}
		
		System.out.println(resourceList.get(0).__v);
		System.out.println(resourceList.get(0)._id);
		System.out.println(resourceList.get(0).customName);
		System.out.println(resourceList.get(0).description);
		System.out.println(resourceList.get(0).fontIcon);
		System.out.println(resourceList.get(0).from);
		System.out.println(resourceList.get(0).name);
	}
	
	
}


