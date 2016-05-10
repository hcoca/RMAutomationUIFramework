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

public class OutOfOrdersAreDeletedWhenServiceDeleted {
	
	@Given("^There is a EmailServer added$")
	public void there_is_a_emailserver_added() throws Throwable {
		BrowserManager.openBrowser();
		LoginPage login = new LoginPage();
		
		EmailServerPage emailServer = login
				.setUserName("SamuelSahonero")
				.setPassword("Control*123")
				.clickOnSigInButton()
				.refreshPage()
				.leftMenu
				.clickOnEmailServerButton();
		
		boolean isEmailServerPresent = emailServer.findEmailServer();
		
		if(isEmailServerPresent == false) {
			
			AddEmailServerPage addEmailServer = emailServer
					.clickOnAddButton();
			
			emailServer = addEmailServer
					.setDomainServer("roommanager.local")
					.setUserName("Administrator")
					.setPassword("Control*123")
					.clickSaveButton();
		}
	}
	
	@Given("^At least an out of order$")
	public void at_least_an_out_of_order() throws Throwable {
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
	
	@When("^I delete the EmailServer$")
	public void i_delete_the_emailserver() throws Throwable {
		ConferenceRoomsPage conferenceRooms = new ConferenceRoomsPage();
		
		EmailServerPage emailServer = conferenceRooms
				.leftMenu
				.clickOnEmailServerButton();
		
		emailServer
				.clickOnRemoveButton()
				.clickOnYesButton();
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
