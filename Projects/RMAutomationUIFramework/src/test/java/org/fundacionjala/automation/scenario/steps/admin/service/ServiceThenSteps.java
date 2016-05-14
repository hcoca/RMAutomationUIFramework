package org.fundacionjala.automation.scenario.steps.admin.service;

import org.fundacionjala.automation.framework.pages.admin.conferencerooms.ConferenceRoomsPage;
import org.fundacionjala.automation.framework.pages.admin.emailserver.AddEmailServerPage;
import org.fundacionjala.automation.framework.pages.admin.emailserver.EmailServerPage;
import org.fundacionjala.automation.framework.pages.admin.login.LoginPage;
import org.fundacionjala.automation.framework.utils.common.PropertiesReader;
import org.testng.Assert;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

import cucumber.api.java.en.Then;

public class ServiceThenSteps {
    @Then("^Service infomation is saved with description \"([^\"]*)\"$")
    public void service_infomation_is_saved_with_description(
	    String myExpectedDescription) throws Throwable {
	EmailServerPage emailServer = new EmailServerPage();

	String myActualDescription = emailServer.clickOnServerButton()
		.getEmailServerDescription();

	Assert.assertEquals(myActualDescription, myExpectedDescription);
    }

    @Then("^The changes: user name \"([^\"]*)\" and password \"([^\"]*)\" are saved$")
    public void the_changes_user_name_and_password_are_saved(
	    String newUserName, String newPassword) throws Throwable {
	EmailServerPage emailServer = new EmailServerPage();

	String myActualUserName = emailServer.getUserName();

	Assert.assertEquals(myActualUserName, newUserName);

	LoginPage login = new LoginPage();

	EmailServerPage server = login
		.setUserName(PropertiesReader.getUserName())
		.setPassword(PropertiesReader.getPassword())
		.clickOnSigInButton().leftMenu.clickOnIssuesButton()
		.clickOnEmailServerButton();

	server.clickOnServerButton().clickOnEditCredentialButton()
		.setUserName(PropertiesReader.getUserName())
		.setPassword(PropertiesReader.getPassword())
		.clickOnAcceptButton();
    }

    @Then("^An error message is displayed$")
    public void an_error_message_is_displayed() throws Throwable {
	String expectedErrorMessage = "Credentials don't have authorization, "
		+ "please try with another";
	EmailServerPage emailServer = new EmailServerPage();

	String myActualErrorMessage = emailServer.getErrorMessage();

	Assert.assertEquals(myActualErrorMessage, expectedErrorMessage);

    }

    @Then("^All Conference rooms are added from Exchange service$")
    public void all_Conference_rooms_are_added_from_Exchange_service()
	    throws Throwable {
	EmailServerPage emailServer = new EmailServerPage();
	ConferenceRoomsPage rooms;
	rooms = emailServer
			.leftMenu
			.clickOnIssuesButton()
			.clickOnConferenceRoomsButton();

	Assert.assertEquals(rooms.verifyTotalItems(), 210);
    }

    @Then("^There is no rooms$")
    public void there_is_no_rooms() throws Throwable {
	EmailServerPage emailServer = new EmailServerPage();

	boolean areConferenceRoomsPresent = emailServer.verifyIfThereAreRooms();

	Assert.assertFalse(areConferenceRoomsPresent);

	LoginPage login = new LoginPage();

	EmailServerPage server = login
		.setUserName(PropertiesReader.getUserName())
		.setPassword(PropertiesReader.getPassword())
		.clickOnSigInButton().leftMenu.clickOnIssuesButton()
		.clickOnEmailServerButton();

	if (server.isAddButtonPresent()) {

	    AddEmailServerPage addEmailServer = server.clickOnAddButton();

	    server = addEmailServer
		    .setDomainServer(PropertiesReader.getExchangeHostname())
		    .setUserName(PropertiesReader.getExchangeConnectUserName())
		    .setPassword(PropertiesReader.getExchangeConnectPassword())
		    .clickSaveButton();
	}
    }

    @Then("^Service information details are deleted$")
    public void service_information_details_are_deleted() throws Throwable {
	MongoClient mongoClient = new MongoClient(
		PropertiesReader.getHostIPAddress(),
		PropertiesReader.getMongoDBConnectionPort());
	DB db = mongoClient.getDB(PropertiesReader.getDBName());
	DBCollection table = db.getCollection(PropertiesReader
		.getServicesTableName());
	DBCursor cursor = table.find();

	long actualServiceSize = cursor.getCollection().count();
	long expectedServiceSize = 0;

	Assert.assertEquals(actualServiceSize, expectedServiceSize);

	LoginPage login = new LoginPage();

	EmailServerPage server = login
		.setUserName(PropertiesReader.getUserName())
		.setPassword(PropertiesReader.getPassword())
		.clickOnSigInButton().leftMenu.clickOnIssuesButton()
		.clickOnEmailServerButton();

	if (server.isAddButtonPresent()) {

	    AddEmailServerPage addEmailServer = server.clickOnAddButton();

	    server = addEmailServer
		    .setDomainServer(PropertiesReader.getExchangeHostname())
		    .setUserName(PropertiesReader.getExchangeConnectUserName())
		    .setPassword(PropertiesReader.getExchangeConnectPassword())
		    .clickSaveButton();
	}
    }

    @Then("^The meetings are deleted$")
    public void the_meetings_are_deleted() throws Throwable {
	MongoClient mongoClient = new MongoClient(
		PropertiesReader.getHostIPAddress(),
		PropertiesReader.getMongoDBConnectionPort());
	DB db = mongoClient.getDB(PropertiesReader.getDBName());
	DBCollection table = db.getCollection(PropertiesReader
		.getMongoDBMeetingTable());
	DBCursor cursor = table.find();

	long actualMeetingsSize = cursor.getCollection().count();
	long expectedMeetingsSize = 0;

	Assert.assertEquals(actualMeetingsSize, expectedMeetingsSize);

	LoginPage login = new LoginPage();

	EmailServerPage server = login
		.setUserName(PropertiesReader.getUserName())
		.setPassword(PropertiesReader.getPassword())
		.clickOnSigInButton().leftMenu.clickOnIssuesButton()
		.clickOnEmailServerButton();

	if (server.isAddButtonPresent()) {

	    AddEmailServerPage addEmailServer = server.clickOnAddButton();

	    server = addEmailServer
		    .setDomainServer(PropertiesReader.getExchangeHostname())
		    .setUserName(PropertiesReader.getExchangeConnectUserName())
		    .setPassword(PropertiesReader.getExchangeConnectPassword())
		    .clickSaveButton();
	}
    }

    @Then("^The out-of-orders are deleted$")
    public void the_out_of_orders_are_deleted() throws Throwable {
	MongoClient mongoClient = new MongoClient(
		PropertiesReader.getHostIPAddress(),
		PropertiesReader.getMongoDBConnectionPort());
	DB db = mongoClient.getDB(PropertiesReader.getDBName());
	DBCollection table = db.getCollection(PropertiesReader
		.getMongoDBOutOfOrderTable());
	DBCursor cursor = table.find();

	long actualOutOfOrdersSize = cursor.getCollection().count();
	long expectedOutOfOrdersSize = 0;

	Assert.assertEquals(actualOutOfOrdersSize, expectedOutOfOrdersSize);

	LoginPage login = new LoginPage();

	EmailServerPage server = login
		.setUserName(PropertiesReader.getUserName())
		.setPassword(PropertiesReader.getPassword())
		.clickOnSigInButton().leftMenu.clickOnIssuesButton()
		.clickOnEmailServerButton();

	if (server.isAddButtonPresent()) {

	    AddEmailServerPage addEmailServer = server.clickOnAddButton();

	    server = addEmailServer
		    .setDomainServer(PropertiesReader.getExchangeHostname())
		    .setUserName(PropertiesReader.getExchangeConnectUserName())
		    .setPassword(PropertiesReader.getExchangeConnectPassword())
		    .clickSaveButton();
	}
    }
}
