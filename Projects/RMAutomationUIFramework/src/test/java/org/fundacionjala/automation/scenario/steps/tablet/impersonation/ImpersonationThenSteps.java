package org.fundacionjala.automation.scenario.steps.tablet.impersonation;

import org.fundacionjala.automation.framework.pages.tablet.scheduler.CredentialsPage;
import org.fundacionjala.automation.framework.pages.tablet.scheduler.SchedulerPage;
import org.fundacionjala.automation.framework.utils.api.managers.SettingsAPIManager;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Settings;
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
        	
        	scheduler
        		.displayAllDayOnTimeline()
        		.clickOnMeetingButton(subject);
        	
        	String expectedSubject = subject;
        	String actualSubject = scheduler.getSubject();
        	
        	String expectedOrganizer = PropertiesReader.getExchangeOrganizerUser();
        	String actualOrganizer = scheduler.getOrganizer();
        	
        	Assert.assertEquals(actualSubject, expectedSubject);
        	Assert.assertEquals(actualOrganizer, expectedOrganizer);
        	Assert.assertTrue(scheduler.isAttendeePresent(PropertiesReader.getExchangeInviteMail()));
        	
        	CredentialsPage credentials = scheduler
        		.displayAllDayOnTimeline()
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
    	
    	@Then("^create Impersonation Options are not displayed in the Credentials Page$")
    	public void verifyCreateImpersonationOptionsAreNotDisplayed() throws Throwable {
            	CredentialsPage credentials = new CredentialsPage();
        	boolean impersonationOptionsArePresent = false;
        
        	if ((credentials.isCreateAsCheckBoxPresent())
        			&& (credentials.isCreateInBehalfOfTextFieldPresent())) {
        		impersonationOptionsArePresent = true;
        	}
        
        	Assert.assertFalse(impersonationOptionsArePresent);
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
    	
    	@Then("^cancel Impersonation Options are not displayed in the Credentials Page$")
    	public void verifyCancelImpersonationOptionsAreNotDisplayed() throws Throwable {
            	CredentialsPage credentials = new CredentialsPage();
        	boolean impersonationOptionsArePresent = false;
        
        	if ((credentials.isCancelAsCheckBoxPresent())
        			&& (credentials.isCancelInBehalfOfMessagePresent())) {
        		impersonationOptionsArePresent = true;
        	}
        
        	Assert.assertFalse(impersonationOptionsArePresent);
        	
            	credentials
            		.setPassword(PropertiesReader.getExchangeOrganizerPwd())
            		.clickOkButton();
    	}
    	
    	@Then("^create Credentials Authentication Options are displayed in the Credentials Page$")
    	public void verifyCreateCredentialsAuthenticationOptionsAreDisplayed() throws Throwable {
    	    	CredentialsPage credentials = new CredentialsPage();
    	    
    	    	Assert.assertTrue(credentials.isUserNameTextFieldPresent());
    	    	Assert.assertTrue(credentials.isPasswordTextFieldPresent());
    	    	Assert.assertFalse(credentials.isCreateAsCheckBoxPresent());
    	    	Assert.assertFalse(credentials.isCreateInBehalfOfTextFieldPresent());
    	}
    	
    	@Then("^create Credentials Authentication Options are not displayed in the Credentials Page$")
    	public void verifyCreateCredentialsAuthenticationOptionsAreNotDisplayed() throws Throwable {
    	    	CredentialsPage credentials = new CredentialsPage();
    	    
    	    	Assert.assertFalse(credentials.isUserNameTextFieldPresent());
    	    	Assert.assertFalse(credentials.isPasswordTextFieldPresent());
    	    	Assert.assertFalse(credentials.isCreateAsCheckBoxPresent());
    	    	Assert.assertFalse(credentials.isCreateInBehalfOfTextFieldPresent());
    	    	
    	    	Settings settings = new Settings(PropertiesReader
	    		.getCredentialsAuthenticationType(), 5, "blue");
		SettingsAPIManager.putRequest(PropertiesReader.getServiceURL()
				+ "/settings", settings);
    	}
    	
    	@Then("^cancel Credentials Authentication Options are displayed in the Credentials Page when cancelling \"([^\"]*)\" meeting$")
    	public void verifyCancelCredentialsAuthenticationOptionsAreDisplayed(String subject) throws Throwable {
    	    	SchedulerPage scheduler = new SchedulerPage();
    	    	
    	    	CredentialsPage credentials = scheduler
    	    			.displayAllDayOnTimeline()
				.clickOnMeeting(subject)
				.clickOnRemoveButton();
	    
	    	Assert.assertTrue(credentials.isUserNameTextFieldPresent());
	    	Assert.assertTrue(credentials.isPasswordTextFieldPresent());
	    	Assert.assertFalse(credentials.isCancelAsCheckBoxPresent());
	    	Assert.assertFalse(credentials.isCancelInBehalfOfMessagePresent());
	    	
	    	credentials
	    		.setPassword(PropertiesReader.getExchangeOrganizerPwd())
	    		.clickOkButton();
    	}
    	
    	@Then("^cancel Credentials Authentication Options are not displayed in the Credentials Page when cancelling \"([^\"]*)\" meeting$")
    	public void verifyCancelCredentialsAuthenticationOptionsAreNotDisplayed(String subject) throws Throwable {
    	    	SchedulerPage scheduler = new SchedulerPage();
    	    
    	    	CredentialsPage credentials = scheduler
    	    					.displayAllDayOnTimeline()
    	    					.clickOnMeeting(subject)
    	    					.clickOnRemoveButton();
	    
	    	Assert.assertFalse(credentials.isUserNameTextFieldPresent());
	    	Assert.assertFalse(credentials.isPasswordTextFieldPresent());
	    	Assert.assertFalse(credentials.isCancelAsCheckBoxPresent());
	    	Assert.assertFalse(credentials.isCancelInBehalfOfMessagePresent());
	    	
	    	Settings settings = new Settings(PropertiesReader
	    		.getCredentialsAuthenticationType(), 5, "blue");
		SettingsAPIManager.putRequest(PropertiesReader.getServiceURL()
				+ "/settings", settings);
		
		scheduler = credentials
				.clickCancelButton();
		
		credentials = scheduler
				.clickRemoveButton();
		
		credentials
			.setPassword(PropertiesReader.getExchangeOrganizerPwd())
			.clickOkButton();
    	}
    	
    	@Then("^create RFID Authentication Options are displayed in the Credentials Page$")
    	public void verifyCreateRFIDAuthenticationOptionsAreDisplayed() throws Throwable {
    	    	CredentialsPage credentials = new CredentialsPage();
    	    	
    	    	Assert.assertTrue(credentials.isNoFunctionalityProvidedMessagePresent());
    	    	
    	    	Settings settings = new Settings(PropertiesReader
    	    		.getCredentialsAuthenticationType(), 5, "blue");
		SettingsAPIManager.putRequest(PropertiesReader.getServiceURL()
				+ "/settings", settings);
    	}
    	
    	@Then("^create RFID Authentication Options are not displayed in the Credentials Page$")
    	public void verifyCreateRFIDAuthenticationOptionsAreNotDisplayed() throws Throwable {
    	    	CredentialsPage credentials = new CredentialsPage();
    	    	
    	    	Assert.assertFalse(credentials.isNoFunctionalityProvidedMessagePresent());
    	}
    	
    	@Then("^cancel RFID Authentication Options are displayed in the Credentials Page$")
    	public void verifyCancelRFIDAuthenticationOptionsAreDisplayed() throws Throwable {
    	    	CredentialsPage credentials = new CredentialsPage();
    	    	
    	    	Assert.assertTrue(credentials.isNoFunctionalityProvidedMessagePresent());
	    	
	    	Settings settings = new Settings(PropertiesReader
	    		.getCredentialsAuthenticationType(), 5, "blue");
		SettingsAPIManager.putRequest(PropertiesReader.getServiceURL()
				+ "/settings", settings);
		
		SchedulerPage scheduler = credentials
						.clickCancelButton();
		
		credentials = scheduler
				.clickRemoveButton();
		
		credentials
			.setPassword(PropertiesReader.getExchangeOrganizerPwd())
			.clickOkButton();
    	}
    	
    	@Then("^cancel RFID Authentication Options are not displayed in the Credentials Page$")
    	public void verifyCancelRFIDAuthenticationOptionsAreNotDisplayed() throws Throwable {
    	    	CredentialsPage credentials = new CredentialsPage();
    	    	
    	    	Assert.assertFalse(credentials.isNoFunctionalityProvidedMessagePresent());
	    	
            	credentials
        		.setPassword(PropertiesReader.getExchangeOrganizerPwd())
        		.clickOkButton();
    	}
}
