package org.fundacionjala.automation.scenario.steps.admin.service;

import org.fundacionjala.automation.framework.pages.admin.conferencerooms.ConferenceRoomsPage;
import org.fundacionjala.automation.framework.pages.admin.emailserver.AddEmailServerPage;
import org.fundacionjala.automation.framework.pages.admin.emailserver.EmailServerPage;
import org.fundacionjala.automation.framework.pages.admin.login.LoginPage;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.PropertiesReader;


import org.testng.Assert;

import cucumber.api.java.en.Given;

public class ServiceGivenSteps {

    @Given("^There is an Email Server added$")
    public void there_is_a_service_existent() throws Throwable {
	BrowserManager.openBrowser();
	LoginPage login = new LoginPage();

	EmailServerPage emailServer = login
		.setUserName(PropertiesReader.getUserName())
		.setPassword(PropertiesReader.getPassword())
		.clickOnSigInButton().leftMenu.clickOnIssuesButton()
		.clickOnEmailServerButton();

	if (emailServer.isAddButtonPresent()) {

	    AddEmailServerPage addEmailServer = emailServer.clickOnAddButton();

	    emailServer = addEmailServer
		    .setDomainServer(PropertiesReader.getExchangeHostname())
		    .setUserName(PropertiesReader.getExchangeConnectUserName())
		    .setPassword(PropertiesReader.getExchangeConnectPassword())
		    .clickSaveButton();
	}
    }

    @Given("^There is no Email Server added$")
    public void there_is_no_services_added() throws Throwable {
	BrowserManager.openBrowser();
	LoginPage login = new LoginPage();

	EmailServerPage emailServer = login
		.setUserName(PropertiesReader.getUserName())
		.setPassword(PropertiesReader.getPassword())
		.clickOnSigInButton().leftMenu.clickOnIssuesButton()
		.clickOnEmailServerButton();

	if (emailServer.isRemoveButtonPresent()) {

	    emailServer.clickOnRemoveButton().clickOnYesButton();
	}
    }

    @Given("^At least a meeting$")
    public void at_least_a_meeting() throws Throwable {
	LoginPage login = new LoginPage();

	ConferenceRoomsPage conferenceRooms = login
		.setUserName(PropertiesReader.getUserName())
		.setPassword(PropertiesReader.getPassword())
		.clickOnSigInButton().leftMenu.clickOnIssuesButton()
		.clickOnConferenceRoomsButton();

	Assert.assertTrue(
		conferenceRooms
			.doubleClickOnRoom(
				PropertiesReader.getSecondConferenceRoom())
			.SelectOutOfOrder().activeOutOfOrder()
			.clickOnSaveButtonState(),
		"Meeting-out of order could not be created");

    }

    @Given("^At least an out of order$")
    public void at_least_an_out_of_order() throws Throwable {
	LoginPage login = new LoginPage();

	ConferenceRoomsPage conferenceRooms = login
		.setUserName(PropertiesReader.getUserName())
		.setPassword(PropertiesReader.getPassword())
		.clickOnSigInButton().leftMenu.clickOnIssuesButton()
		.clickOnConferenceRoomsButton();

	Assert.assertTrue(conferenceRooms
		.doubleClickOnRoom(PropertiesReader.getConferenceRoom())
		.SelectOutOfOrder().clickOnSaveButtonState(),
		"Out of orders could not be created");
	
    }
}
