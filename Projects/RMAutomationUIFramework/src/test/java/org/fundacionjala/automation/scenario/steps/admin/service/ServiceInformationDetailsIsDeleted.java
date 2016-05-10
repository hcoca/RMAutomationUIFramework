package org.fundacionjala.automation.scenario.steps.admin.service;

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

public class ServiceInformationDetailsIsDeleted {
	
	@Given("^There is a service alive$")
	public void there_is_a_service_alive() throws Throwable {
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
	
	@When("^I delete the current Email Server$")
	public void i_delete_the_current_email_server() throws Throwable {
		LoginPage login = new LoginPage();
		
		EmailServerPage emailServer = login
				.setUserName("SamuelSahonero")
				.setPassword("Control*123")
				.clickOnSigInButton()
				.refreshPage()
				.leftMenu
				.clickOnEmailServerButton();
		
		emailServer
				.clickOnRemoveButton()
				.clickOnYesButton();
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
}
