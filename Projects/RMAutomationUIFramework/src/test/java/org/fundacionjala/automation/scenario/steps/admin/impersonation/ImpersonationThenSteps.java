package org.fundacionjala.automation.scenario.steps.admin.impersonation;

import java.util.List;

import org.fundacionjala.automation.framework.pages.admin.emailserver.AddEmailServerPage;
import org.fundacionjala.automation.framework.pages.admin.emailserver.EmailServerPage;
import org.fundacionjala.automation.framework.pages.admin.impersonation.ImpersonationPage;
import org.fundacionjala.automation.framework.pages.admin.login.LoginPage;
import org.fundacionjala.automation.framework.pages.tablet.scheduler.CredentialsPage;
import org.fundacionjala.automation.framework.utils.api.managers.ServiceAPIManager;
import org.fundacionjala.automation.framework.utils.api.managers.SettingsAPIManager;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Service;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Settings;
import org.testng.Assert;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

import cucumber.api.java.en.Then;

public class ImpersonationThenSteps {
	
	@Then("^the Impersonation Option is enabled$")
	public void the_Impersonation_Option_is_enabled() throws Throwable {
		boolean impersonate = false;
		List<Service> listServices;
		listServices = ServiceAPIManager.getRequest("http://172.20.208.84:4040/services");
		
		for(Service service : listServices) {
			impersonate = service.impersonate;
		}
		
		Assert.assertTrue(impersonate);

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
	
	@Then("^the Impersonation Option is disabled$")
	public void the_Impersonation_Option_is_disabled() throws Throwable {
		boolean impersonate = false;
		List<Service> listServices;
		listServices = ServiceAPIManager.getRequest("http://172.20.208.84:4040/services");
		
		for (Service service : listServices) {
			impersonate = service.impersonate;
		}
		
		Assert.assertFalse(impersonate);
		
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
	
	@Then("^the Authentication Type is changed to \"([^\"]*)\"$")
	public void the_Athentication_Type_is_changed_to(String type) throws Throwable {
	    String actualAuthenticationType = SettingsAPIManager.getRequest("http://172.20.208.84:4040/settings").authentication;
	    String expectedAuthenticationType = type;
	    
	    Assert.assertEquals(actualAuthenticationType, expectedAuthenticationType);
	    
	    if(actualAuthenticationType.equals("rfid")) {
			Settings settings = new Settings("credentials", 5, "blue");
			SettingsAPIManager.putRequest("http://172.20.208.84:4040/settings", settings);
		}
	}
	
	@Then("^the Impersonation Options displayed in the Credentials Page$")
	public void the_Impersonation_Options_displayed_in_the_Credentials_Page() throws Throwable {
		CredentialsPage credentials = new CredentialsPage();
		boolean impersonationOptionsArePresent = false;
		
		if(credentials.isCreateAsCheckBoxPresent() && credentials.isCreateInBehalfOfTextFieldPresent()) {
			impersonationOptionsArePresent = true;
		}
		
		Assert.assertTrue(impersonationOptionsArePresent);
		
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
	
	@Then("^Impersonation Option is disabled$")
	public void Impersonation_Option_is_disabled() throws Throwable {
		EmailServerPage emailServer = new EmailServerPage();
		boolean impersonate = false;
		List<Service> listServices;
		listServices = ServiceAPIManager.getRequest("http://172.20.208.84:4040/services");
		
		for (Service service : listServices) {
			impersonate = service.impersonate;
		}
		
		ImpersonationPage impersonation = emailServer
				.leftMenu
				.clickOnImpersonationButton();
		
		boolean isSaveButtonPresent = impersonation.findSaveButton();
		
		Assert.assertFalse(isSaveButtonPresent);
		Assert.assertFalse(impersonate);
		
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
