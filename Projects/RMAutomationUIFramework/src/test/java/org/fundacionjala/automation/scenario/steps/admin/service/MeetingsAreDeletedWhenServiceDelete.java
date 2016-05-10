package org.fundacionjala.automation.scenario.steps.admin.service;

import org.fundacionjala.automation.framework.pages.admin.conferencerooms.ConferenceRoomsPage;
import org.fundacionjala.automation.framework.pages.admin.emailserver.AddEmailServerPage;
import org.fundacionjala.automation.framework.pages.admin.emailserver.EmailServerPage;
import org.fundacionjala.automation.framework.pages.admin.login.LoginPage;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.testng.Assert;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MeetingsAreDeletedWhenServiceDelete {
	
	@Given("^There is a mail service added$")
	public void there_is_a_mail_server_added() throws Throwable {
		BrowserManager.openBrowser();
		LoginPage login = new LoginPage();
		
		EmailServerPage emailServer = login
				.setUserName("SamuelSahonero")
				.setPassword("Control*123")
				.clickOnSigInButton()
				.refreshPage()
				.leftMenu
				.clickOnEmailServerButton();
		
		boolean isAddButtonPresent = emailServer.findAddButton();
		
		if(isAddButtonPresent == true) {
			
			AddEmailServerPage addEmailServer = emailServer
					.clickOnAddButton();
			
			emailServer = addEmailServer
					.setDomainServer("roommanager.local")
					.setUserName("Administrator")
					.setPassword("Control*123")
					.clickSaveButton();
		}
	}
	
	@Given("^At least a meeting$")
	public void at_least_a_meeting() throws Throwable {
		LoginPage login = new LoginPage();
		
		ConferenceRoomsPage conferenceRooms = login
			.setUserName("SamuelSahonero")
			.setPassword("Control*123")
			.clickOnSigInButton()
			.refreshPage()
			.leftMenu
			.clickOnIssuesButton()
			.clickOnConferenceRoomsButton();
		
		conferenceRooms
			.doubleClickOnRoom("Room04")
			.SelectOutOfOrder()
			.activeOutOfOrder()
			.clickOnSave();
	}
	
	@When("^I delete the mail service$")
	public void i_delete_the_mail_service() throws Throwable {
		ConferenceRoomsPage conferenceRooms = new ConferenceRoomsPage();
		
		EmailServerPage emailServer = conferenceRooms
				.leftMenu
				.clickOnEmailServerButton();
		
		emailServer
				.clickOnRemoveButton()
				.clickOnYesButton();
	}
	
	@Then("^The meetings are deleted$")
	public void the_meetings_are_deleted() throws Throwable {
		MongoClient mongoClient = new MongoClient("172.20.208.84" , 27017);
		DB db = mongoClient.getDB("roommanager");
		DBCollection table = db.getCollection("meetings");
		DBCursor cursor = table.find();
		
		long actualMeetingsSize = cursor.getCollection().count();
		long expectedMeetingsSize = 0;
		
		Assert.assertEquals(actualMeetingsSize, expectedMeetingsSize);

		LoginPage login = new LoginPage();
		
		EmailServerPage server = login
				.setUserName("SamuelSahonero")
				.setPassword("Control*123")
				.clickOnSigInButton()
				.refreshPage()
				.leftMenu
				.clickOnEmailServerButton();
		
		boolean isAddButtonPresent = server.findAddButton();
		
		if(isAddButtonPresent == true) {
			
			AddEmailServerPage addEmailServer = server
					.clickOnAddButton();
			
			server = addEmailServer
					.setDomainServer("roommanager.local")
					.setUserName("Administrator")
					.setPassword("Control*123")
					.clickSaveButton();
		}
	}
}
