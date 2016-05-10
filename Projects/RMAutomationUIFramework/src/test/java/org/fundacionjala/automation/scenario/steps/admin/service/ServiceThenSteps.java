package org.fundacionjala.automation.scenario.steps.admin.service;

import java.util.List;

import org.fundacionjala.automation.framework.pages.admin.emailserver.AddEmailServerPage;
import org.fundacionjala.automation.framework.pages.admin.emailserver.EmailServerPage;
import org.fundacionjala.automation.framework.pages.admin.login.LoginPage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

import cucumber.api.java.en.Then;

public class ServiceThenSteps {
	@Then("^Service infomation is saved with description \"([^\"]*)\"$")
	public void service_infomation_is_saved_with_description(String myExpectedDescription) throws Throwable {
		EmailServerPage emailServer = new EmailServerPage();
		
		String myActualDescription = emailServer
				.clickOnServerButton()
				.getEmailServerDescription();
		
		Assert.assertEquals(myActualDescription, myExpectedDescription);
	}
	
	@Then("^The changes: user name \"([^\"]*)\" and password \"([^\"]*)\" are saved$")
	public void the_changes_user_name_and_password_are_saved(String newUserName, String newPassword) throws Throwable {
		EmailServerPage emailServer = new EmailServerPage();
		
		String myActualUserName = emailServer
				.getUserName();
		
		Assert.assertEquals(myActualUserName, newUserName);
		
		LoginPage login = new LoginPage();
		
		EmailServerPage server = login
				.setUserName("SamuelSahonero")
				.setPassword("Control*123")
				.clickOnSigInButton()
				.refreshPage()
				.leftMenu
				.clickOnEmailServerButton();
		
		server
			.clickOnServerButton()
			.clickOnEditCredentialButton()
			.setUserName("Administrator")
			.setPassword("Control*123")
			.clickOnAcceptButton();
	}
	
	@Then("^An error message is displayed$")
	public void an_error_message_is_displayed() throws Throwable {
		String expectedErrorMessage = "Credentials don't have authorization, please try with another";
		EmailServerPage emailServer = new EmailServerPage();
		
		String myActualErrorMessage = emailServer
				.getErrorMessage();
		
		Assert.assertEquals(myActualErrorMessage, expectedErrorMessage);
	}
	
	@Then("^All Conference rooms are added from Exchange service$")
	public void all_Conference_rooms_are_added_from_Exchange_service() throws Throwable {
		EmailServerPage emailServer = new EmailServerPage();
		
		List<WebElement> roomsList = emailServer
				.leftMenu
				.clickOnConferenceRoomsButton()
				.getRooms();
		
		Assert.assertEquals(roomsList.size(), 210);
	}
	
	@Then("^There is no rooms$")
	public void there_is_no_rooms() throws Throwable {
		EmailServerPage emailServer = new EmailServerPage();
		
		boolean areConferenceRoomsPresent = emailServer
				.verifyIfThereAreRooms();

		Assert.assertFalse(areConferenceRoomsPresent);
		
		LoginPage login = new LoginPage();
		
		EmailServerPage server = login
				.setUserName("SamuelSahonero")
				.setPassword("Control*123")
				.clickOnSigInButton()
				.refreshPage()
				.leftMenu
				.clickOnEmailServerButton();
		
		boolean isEmailServerPresent = server.findEmailServer();
		
		if(isEmailServerPresent == false) {
			
			AddEmailServerPage addEmailServer = server
					.clickOnAddButton();
			
			server = addEmailServer
					.setDomainServer("roommanager.local")
					.setUserName("Administrator")
					.setPassword("Control*123")
					.clickSaveButton();
		}
	}
	
	@Then("^Service information details are deleted$")
	public void service_information_details_are_deleted() throws Throwable {
		MongoClient mongoClient = new MongoClient("172.20.208.84" , 27017);
		DB db = mongoClient.getDB("roommanager");
		DBCollection table = db.getCollection("services");
		DBCursor cursor = table.find();
		
		long actualServiceSize = cursor.getCollection().count();
		long expectedServiceSize = 0;
		
		Assert.assertEquals(actualServiceSize, expectedServiceSize);
		
		LoginPage login = new LoginPage();
		
		EmailServerPage server = login
				.setUserName("SamuelSahonero")
				.setPassword("Control*123")
				.clickOnSigInButton()
				.refreshPage()
				.leftMenu
				.clickOnEmailServerButton();
		
		boolean isEmailServerPresent = server.findEmailServer();
		
		if(isEmailServerPresent == false) {
			
			AddEmailServerPage addEmailServer = server
					.clickOnAddButton();
			
			server = addEmailServer
					.setDomainServer("roommanager.local")
					.setUserName("Administrator")
					.setPassword("Control*123")
					.clickSaveButton();
		}
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
		
		boolean isEmailServerPresent = server.findEmailServer();
		
		if(isEmailServerPresent == false) {
			
			AddEmailServerPage addEmailServer = server
					.clickOnAddButton();
			
			server = addEmailServer
					.setDomainServer("roommanager.local")
					.setUserName("Administrator")
					.setPassword("Control*123")
					.clickSaveButton();
		}
	}
	
	@Then("^The out-of-orders are deleted$")
	public void the_out_of_orders_are_deleted() throws Throwable {
		MongoClient mongoClient = new MongoClient("172.20.208.84" , 27017);
		DB db = mongoClient.getDB("roommanager");
		DBCollection table = db.getCollection("outoforders");
		DBCursor cursor = table.find();
		
		long actualOutOfOrdersSize = cursor.getCollection().count();
		long expectedOutOfOrdersSize = 0;
		
		Assert.assertEquals(actualOutOfOrdersSize, expectedOutOfOrdersSize);
		
		LoginPage login = new LoginPage();
		
		EmailServerPage server = login
				.setUserName("SamuelSahonero")
				.setPassword("Control*123")
				.clickOnSigInButton()
				.refreshPage()
				.leftMenu
				.clickOnEmailServerButton();
		
		boolean isEmailServerPresent = server.findEmailServer();
		
		if(isEmailServerPresent == false) {
			
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
