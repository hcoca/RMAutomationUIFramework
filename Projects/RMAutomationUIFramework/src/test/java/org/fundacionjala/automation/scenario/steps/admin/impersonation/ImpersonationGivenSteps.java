package org.fundacionjala.automation.scenario.steps.admin.impersonation;

import org.fundacionjala.automation.framework.pages.admin.emailserver.EmailServerPage;
import org.fundacionjala.automation.framework.pages.admin.login.LoginPage;
import org.fundacionjala.automation.framework.utils.api.managers.SettingsAPIManager;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Settings;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

import cucumber.api.java.en.Given;

public class ImpersonationGivenSteps {
	
	@Given("^impersonation is disabled$")
	public void impersonation_is_disabled() throws Throwable {
		MongoClient mongoClient = new MongoClient("172.20.208.84" , 27017);
		DB db = mongoClient.getDB("roommanager");
		DBCollection table = db.getCollection("services");
		
		BasicDBObject query = new BasicDBObject();
		query.put("impersonate", true);
		
		BasicDBObject newDocument = new BasicDBObject();
		newDocument.put("impersonate", false);
		
		BasicDBObject updateObj = new BasicDBObject();
		updateObj.put("$set", newDocument);
		
		table.update(query, updateObj);
	}
	
	@Given("^impersonation is enabled$")
	public void impersonation_is_enabled() throws Throwable {
		MongoClient mongoClient = new MongoClient("172.20.208.84" , 27017);
		DB db = mongoClient.getDB("roommanager");
		DBCollection table = db.getCollection("services");
		
		BasicDBObject query = new BasicDBObject();
		query.put("impersonate", false);
		
		BasicDBObject newDocument = new BasicDBObject();
		newDocument.put("impersonate", true);
		
		BasicDBObject updateObj = new BasicDBObject();
		updateObj.put("$set", newDocument);
		
		table.update(query, updateObj);
	}
	
	@Given("^authentication type configured as \"([^\"]*)\"$")
	public void authentication_type_configured_as(String type) throws Throwable {
		Settings settings = new Settings(type, 5, "blue");
		SettingsAPIManager.putRequest("http://172.20.208.84:4040/settings", settings);
	}
	
	@Given("^a user \"([^\"]*)\" has logged into Room Manager with an email server added$")
	public void a_user_has_logged_into_Room_Manager(String arg1) throws Throwable {
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
		
		if(isEmailServerPresent == true) {
			
			emailServer
				.clickOnRemoveButton()
				.clickOnYesButton();
		}
		
		LoginPage loginPage = new LoginPage();
		
		loginPage
			.setUserName(arg1)
			.setPassword("Control*123")
			.clickOnSigInButton()
			.refreshPage();
	}
}
