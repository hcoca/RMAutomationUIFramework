package org.fundacionjala.automation.scenario.steps.admin.impersonation;

import org.fundacionjala.automation.framework.pages.admin.emailserver.EmailServerPage;
import org.fundacionjala.automation.framework.pages.admin.login.LoginPage;
import org.fundacionjala.automation.framework.utils.api.managers.SettingsAPIManager;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Settings;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.PropertiesReader;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

import cucumber.api.java.en.Given;

public class ImpersonationGivenSteps {

    @Given("^impersonation is disabled$")
    public void impersonation_is_disabled() throws Throwable {
	MongoClient mongoClient = new MongoClient(
		PropertiesReader.getHostIPAddress(),
		PropertiesReader.getMongoDBConnectionPort());
	
	DB db = mongoClient.getDB(PropertiesReader.getDBName());
	DBCollection table = db.getCollection(PropertiesReader
		.getServicesTableName());

	BasicDBObject query = new BasicDBObject();
	query.put(PropertiesReader.getImpersonateFieldName(), true);

	BasicDBObject newDocument = new BasicDBObject();
	newDocument.put(PropertiesReader.getImpersonateFieldName(), false);

	BasicDBObject updateObj = new BasicDBObject();
	updateObj.put("$set", newDocument);

	table.update(query, updateObj);
    }

    @Given("^impersonation is enabled$")
    public void impersonation_is_enabled() throws Throwable {
	MongoClient mongoClient = new MongoClient(
		PropertiesReader.getHostIPAddress(),
		PropertiesReader.getMongoDBConnectionPort());
	
	DB db = mongoClient.getDB(PropertiesReader.getDBName());
	DBCollection table = db.getCollection(PropertiesReader
		.getServicesTableName());

	BasicDBObject query = new BasicDBObject();
	query.put(PropertiesReader.getImpersonateFieldName(), false);

	BasicDBObject newDocument = new BasicDBObject();
	newDocument.put(PropertiesReader.getImpersonateFieldName(), true);

	BasicDBObject updateObj = new BasicDBObject();
	updateObj.put("$set", newDocument);

	table.update(query, updateObj);
    }

    @Given("^authentication type configured as \"([^\"]*)\"$")
    public void authentication_type_configured_as(String type) throws Throwable {
	Settings settings = new Settings(type, 5, "blue");
	SettingsAPIManager.putRequest(PropertiesReader.getServiceURL()
		+ "/settings", settings);
    }

    @Given("^a user has logged into Room Manager with an email server added$")
    public void a_user_has_logged_into_Room_Manager()
	    throws Throwable {
	BrowserManager.openBrowser();
	LoginPage login = new LoginPage();

	EmailServerPage emailServer = login
		.setUserName(PropertiesReader.getUserName())
		.setPassword(PropertiesReader.getPassword())
		.clickOnSigInButton().refreshPage().leftMenu
		.clickOnEmailServerButton();

	boolean isEmailServerPresent = emailServer
		.findEmailServer();

	if (isEmailServerPresent == true) {
	    emailServer
	    	.clickOnRemoveButton()
	    	.clickOnYesButton();
	}

	LoginPage loginPage = new LoginPage();

	loginPage
		.setUserName(PropertiesReader.getUserName())
		.setPassword(PropertiesReader.getPassword())
		.clickOnSigInButton().refreshPage();
    }
}
