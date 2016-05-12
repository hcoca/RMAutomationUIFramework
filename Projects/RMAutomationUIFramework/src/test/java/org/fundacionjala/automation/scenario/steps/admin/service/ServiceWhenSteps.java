package org.fundacionjala.automation.scenario.steps.admin.service;

import org.fundacionjala.automation.framework.pages.admin.conferencerooms.ConferenceRoomsPage;
import org.fundacionjala.automation.framework.pages.admin.emailserver.AddEmailServerPage;
import org.fundacionjala.automation.framework.pages.admin.emailserver.EmailServerPage;
import org.fundacionjala.automation.framework.pages.admin.login.LoginPage;
import org.fundacionjala.automation.framework.utils.common.PropertiesReader;

import cucumber.api.java.en.When;

public class ServiceWhenSteps {

    @When("^I add a new Exchange Email Server with description \"([^\"]*)\"$")
    public void i_add_a_new_Exchange_Email_Server_with_description(
	    String myExpectedDescription) throws Throwable {
	LoginPage login = new LoginPage();

	EmailServerPage emailServer = login
		.setUserName(PropertiesReader.getUserName())
		.setPassword(PropertiesReader.getPassword())
		.clickOnSigInButton().refreshPage().leftMenu
		.clickOnEmailServerButton();

	AddEmailServerPage addEmailServer = emailServer.clickOnAddButton();

	emailServer = addEmailServer
		.setDomainServer(PropertiesReader.getExchangeHostname())
		.setUserName(PropertiesReader.getExchangeConnectUserName())
		.setPassword(PropertiesReader.getExchangeConnectPassword())
		.setDescription(myExpectedDescription).clickSaveButton();
    }

    @When("^I modify credential with user name \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void i_modify_credential_with_user_name_and_password(
	    String newUserName, String newPassword) throws Throwable {
	LoginPage login = new LoginPage();

	EmailServerPage emailServer = login
		.setUserName(PropertiesReader.getUserName())
		.setPassword(PropertiesReader.getPassword())
		.clickOnSigInButton().refreshPage().leftMenu
		.clickOnEmailServerButton();

	emailServer.clickOnServerButton().clickOnEditCredentialButton()
		.setUserName(newUserName).setPassword(newPassword)
		.clickOnAcceptButton();
    }

    @When("^I try to modify the Exchange Email Server credential with user account which does not follow the requirements$")
    public void i_modify_Exchange_Email_Server_credential() throws Throwable {
	LoginPage login = new LoginPage();

	EmailServerPage emailServer = login
		.setUserName(PropertiesReader.getUserName())
		.setPassword(PropertiesReader.getPassword())
		.clickOnSigInButton().refreshPage().leftMenu
		.clickOnEmailServerButton();

	emailServer.clickOnServerButton().clickOnEditCredentialButton()
		.setUserName("Pepito").setPassword("Springfield")
		.waitForErrorMessage();
    }

    @When("^I add a new Email Server$")
    public void i_add_a_new_Exchange_Email_Server() throws Throwable {
	LoginPage login = new LoginPage();

	EmailServerPage emailServer = login
		.setUserName(PropertiesReader.getUserName())
		.setPassword(PropertiesReader.getPassword())
		.clickOnSigInButton().refreshPage().leftMenu
		.clickOnEmailServerButton();

	AddEmailServerPage addEmailServer = emailServer.clickOnAddButton();

	emailServer = addEmailServer
		.setDomainServer(PropertiesReader.getExchangeHostname())
		.setUserName(PropertiesReader.getExchangeConnectUserName())
		.setPassword(PropertiesReader.getExchangeConnectPassword())
		.clickSaveButton();
    }

    @When("^I delete the Email Server$")
    public void i_delete_the_Emailservice() throws Throwable {
	LoginPage login = new LoginPage();

	EmailServerPage emailServer = login
		.setUserName(PropertiesReader.getUserName())
		.setPassword(PropertiesReader.getPassword())
		.clickOnSigInButton().refreshPage().leftMenu
		.clickOnEmailServerButton();

	emailServer.clickOnRemoveButton().clickOnYesButton();
    }

    @When("^I delete the Email Server from Conference Rooms page$")
    public void i_delete_the_mail_service() throws Throwable {
	ConferenceRoomsPage conferenceRooms = new ConferenceRoomsPage();

	EmailServerPage emailServer = conferenceRooms.leftMenu
		.clickOnEmailServerButton();

	emailServer.clickOnRemoveButton().clickOnYesButton();
    }
}
