package org.fundacionjala.automation.scenario.steps.tablet.impersonation;

import org.fundacionjala.automation.framework.pages.tablet.scheduler.CredentialsPage;
import org.fundacionjala.automation.framework.pages.tablet.scheduler.SchedulerPage;
import org.fundacionjala.automation.framework.utils.common.PropertiesReader;
import org.testng.Assert;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

import cucumber.api.java.en.Then;

public class ImpersonationThenSteps {

    	@Then("^A meeting with \"([^\"]*)\" subject is created$")
	public void verifyAMeetingIsCreated(String subject) throws Throwable {
        	SchedulerPage scheduler = new SchedulerPage();
        	
        	Assert.assertTrue(scheduler.isMeetingPresentOnTimeLine(subject));
        	
        	scheduler.clickOnMeetingButton(subject);
        	
        	String expectedSubject = subject;
        	String actualSubject = scheduler.getSubject();
        	
        	String expectedOrganizer = PropertiesReader.getExchangeOrganizerUser();
        	String actualOrganizer = scheduler.getOrganizer();
        	
        	Assert.assertEquals(actualSubject, expectedSubject);
        	Assert.assertEquals(actualOrganizer, expectedOrganizer);
        	Assert.assertTrue(scheduler.isAttendeePresent(PropertiesReader.getExchangeOrganizerMail()));
        	Assert.assertTrue(scheduler.isAttendeePresent(PropertiesReader.getExchangeInviteMail()));
        	
        	CredentialsPage credentials = scheduler
			.clickOnMeetingButton(subject)
			.clickRemoveButton();
	
        	credentials
        		.setPassword(PropertiesReader.getExchangeOrganizerPwd())
        		.clickOkButton();
        	
        	MongoClient mongoClient = new MongoClient(
			PropertiesReader.getHostIPAddress(),
			PropertiesReader.getMongoDBConnectionPort());

        	DB db = mongoClient.getDB(PropertiesReader.getDBName());
        	DBCollection table = db.getCollection(PropertiesReader.getServiceURL());
        
        	BasicDBObject query = new BasicDBObject();
        	query.put(PropertiesReader.getImpersonateFieldName(), true);
        
        	BasicDBObject newDocument = new BasicDBObject();
        	newDocument.put(PropertiesReader.getImpersonateFieldName(), false);
        
        	BasicDBObject updateObj = new BasicDBObject();
        	updateObj.put("$set", newDocument);
        
        	table.update(query, updateObj);
    	}
    
    	@Then("^A meeting with \"([^\"]*)\" subject is removed$")
    	public void verifyAMeetingIsCancelled(String subject) throws Throwable {
		SchedulerPage scheduler = new SchedulerPage();
		
		Assert.assertFalse(scheduler.isMeetingPresentOnTimeLine(subject));
		
		MongoClient mongoClient = new MongoClient(
			PropertiesReader.getHostIPAddress(),
			PropertiesReader.getMongoDBConnectionPort());

        	DB db = mongoClient.getDB(PropertiesReader.getDBName());
        	DBCollection table = db.getCollection(PropertiesReader.getServiceURL());
        
        	BasicDBObject query = new BasicDBObject();
        	query.put(PropertiesReader.getImpersonateFieldName(), true);
        
        	BasicDBObject newDocument = new BasicDBObject();
        	newDocument.put(PropertiesReader.getImpersonateFieldName(), false);
        
        	BasicDBObject updateObj = new BasicDBObject();
        	updateObj.put("$set", newDocument);
        
        	table.update(query, updateObj);
    	}
    	
    	@Then("^create Impersonation Options are displayed in the Credentials Page$")
    	public void verifyCreateImpersonationOptionsAreDisplayed() throws Throwable {
            	CredentialsPage credentials = new CredentialsPage();
        	boolean impersonationOptionsArePresent = false;
        
        	if ((credentials.isCreateAsCheckBoxPresent())
        			&& (credentials.isCreateInBehalfOfTextFieldPresent())) {
        		impersonationOptionsArePresent = true;
        	}
        
        	Assert.assertTrue(impersonationOptionsArePresent);
        	
        	MongoClient mongoClient = new MongoClient(
			PropertiesReader.getHostIPAddress(),
			PropertiesReader.getMongoDBConnectionPort());

        	DB db = mongoClient.getDB(PropertiesReader.getDBName());
        	DBCollection table = db.getCollection(PropertiesReader.getServiceURL());
        
        	BasicDBObject query = new BasicDBObject();
        	query.put(PropertiesReader.getImpersonateFieldName(), true);
        
        	BasicDBObject newDocument = new BasicDBObject();
        	newDocument.put(PropertiesReader.getImpersonateFieldName(), false);
        
        	BasicDBObject updateObj = new BasicDBObject();
        	updateObj.put("$set", newDocument);
        
        	table.update(query, updateObj);
    	}
    	
    	@Then("^cancel Impersonation Options are displayed in the Credentials Page$")
    	public void verifyCancelImpersonationOptionsAreDisplayed() throws Throwable {
            	CredentialsPage credentials = new CredentialsPage();
        	boolean impersonationOptionsArePresent = false;
        
        	if ((credentials.isCancelAsCheckBoxPresent())
        			&& (credentials.isCancelInBehalfOfMessagePresent())) {
        		impersonationOptionsArePresent = true;
        	}
        
        	Assert.assertTrue(impersonationOptionsArePresent);
        	
            	credentials
            		.setPassword(PropertiesReader.getExchangeOrganizerPwd())
            		.clickOkButton();
        	
        	MongoClient mongoClient = new MongoClient(
			PropertiesReader.getHostIPAddress(),
			PropertiesReader.getMongoDBConnectionPort());

        	DB db = mongoClient.getDB(PropertiesReader.getDBName());
        	DBCollection table = db.getCollection(PropertiesReader.getServiceURL());
        
        	BasicDBObject query = new BasicDBObject();
        	query.put(PropertiesReader.getImpersonateFieldName(), true);
        
        	BasicDBObject newDocument = new BasicDBObject();
        	newDocument.put(PropertiesReader.getImpersonateFieldName(), false);
        
        	BasicDBObject updateObj = new BasicDBObject();
        	updateObj.put("$set", newDocument);
        
        	table.update(query, updateObj);
    	}
}
