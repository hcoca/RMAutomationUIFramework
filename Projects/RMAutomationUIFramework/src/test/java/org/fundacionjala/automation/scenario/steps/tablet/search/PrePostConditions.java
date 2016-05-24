package org.fundacionjala.automation.scenario.steps.tablet.search;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.fundacionjala.automation.framework.maps.admin.resource.IconResources;
import org.fundacionjala.automation.framework.pages.admin.resource.ResourcesActions;
import org.fundacionjala.automation.framework.utils.api.managers.MeetingAPIManager;
import org.fundacionjala.automation.framework.utils.api.managers.ResourceAPIManager;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Meeting;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Resource;
import org.fundacionjala.automation.framework.utils.common.DatabaseConnection;
import org.fundacionjala.automation.framework.utils.common.LogManager;
import org.fundacionjala.automation.framework.utils.common.PropertiesReader;
import org.fundacionjala.automation.framework.utils.common.RMGenerator;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import cucumber.api.java.After;
import cucumber.api.java.Before;


public class PrePostConditions {
	
	DatabaseConnection connection = new DatabaseConnection();
	
	@Before("@searchfeature4,@searchfeature8")
	public void createLocation() {
		
		connection.switchCollection("locations");
		BasicDBObject document = new BasicDBObject();
		ObjectId id = new ObjectId();
		document.put("path", id);
		document.put("name", "loc-jalasoft");
		document.put("customName", "loc-jalasoft");
		document.put("description", "loc-jalasoft");
		document.put("__v", 0);
		connection.getCollection().insert(document);
	}
	
	@After("@searchfeature4,@searchfeature8")
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
	
    @Before("@searchfeature9")
    public void CreateMeeting() {
    	
    	String roomName = "Room008";
    	String subject = "Meeting";
    	
    	String roomEmail = roomName + "@"
				+ PropertiesReader.getExchangeDomain();
		String resources = roomName + "@"
				+ PropertiesReader.getExchangeDomain();
		String start = RMGenerator.getIsoTime(0);
		String end = RMGenerator.getIsoTime(1);
		List<String> attendees = new ArrayList<String>();
		attendees.add(PropertiesReader.getExchangeInviteMail());
		Meeting meeting = new Meeting(
				PropertiesReader.getExchangeOrganizerUser(), subject, start,
				end, roomName, roomEmail, resources, attendees);
		
        try {
        	
        	MeetingAPIManager.postRequest(roomName, meeting);
        	LogManager.info("The meeting with subject: " + subject + "was created");
		} catch (Exception e) {
			
			LogManager.info("The meeting with subject: " + subject + "could not be created");
		}
		
    }

    @Before("@searchfeature10")
    public void CreateResource() {
    	
       deleteResources();
       ResourcesActions.createResourceByAPI("Folder", IconResources.FOLDER, "description");
    }
    
    @After("@searchfeature10")
    public void DeleteResource() throws UnirestException {
    	
    	 List<Resource> listResource = ResourceAPIManager.getRequest(
          		PropertiesReader.getServiceURL() + "/resources");
          for (Resource resource : listResource) {
         	 
          	if(resource.name.contains("Folder"))
          	{
          		ResourceAPIManager.deleteRequest(
          			PropertiesReader.getServiceURL() + 
          			"/resources", resource._id);
          	}
	  }
    }
    
    private void deleteResources() {

    	DatabaseConnection connection = new DatabaseConnection();
    	connection.switchCollection("resourcemodels");
    	DBCollection myCollection = connection.getCollection();
    	myCollection.drop();
    }
    
}
