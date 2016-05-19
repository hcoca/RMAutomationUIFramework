package org.fundacionjala.automation.scenario.steps.tablet.search;

import org.fundacionjala.automation.framework.utils.common.DatabaseConnection;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import cucumber.api.java.After;
import cucumber.api.java.Before;


public class PrePostConditions {
	
	DatabaseConnection connection = new DatabaseConnection();
	
	@Before("@searchfeature4")
	public void createLocation() {
		
		connection.switchCollection("locations");
		BasicDBObject document = new BasicDBObject();
		
		document.put("path", "573cbf195580d33809e2270c");
		document.put("name", "loc-jalasoft");
		document.put("customName", "loc-jalasoft");
		document.put("description", "loc-jalasoft");
		document.put("__v", 0);
		connection.getCollection().insert(document);
	}
	
	@After("@searchfeature4")
	public void deleteLocation() {
		
		connection.switchCollection("locations");
		BasicDBObject deleteQuery = new BasicDBObject();
		deleteQuery.put("name", "loc-jalasoft");
		DBCursor cursor = connection.getCollection().find(deleteQuery);
		while (cursor.hasNext()) {
			
		    DBObject item = cursor.next();
		    connection.getCollection().remove(item);
		}
	
	}
}
