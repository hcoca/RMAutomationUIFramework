package org.fundacionjala.automation.scenario.steps.admin.service;

import org.fundacionjala.automation.framework.pages.admin.emailserver.AddEmailServerPage;
import org.fundacionjala.automation.framework.pages.admin.emailserver.EmailServerPage;
import org.fundacionjala.automation.framework.pages.admin.login.LoginPage;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.testng.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ErrorMessageWhenAccountIncorrect {
	
	@Given("^There is the service added$")
	public void there_is_no_service_added() throws Throwable {
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

	@When("^I try to modify the Exchange Email Server credential with user account which does not follow the requirements$")
	public void i_modify_Exchange_Email_Server_credential() throws Throwable {
		LoginPage login = new LoginPage();
		
		EmailServerPage emailServer = login
				.setUserName("SamuelSahonero")
				.setPassword("Control*123")
				.clickOnSigInButton()
				.refreshPage()
				.leftMenu
				.clickOnEmailServerButton();
		
		emailServer
				.clickOnServerButton()
				.clickOnEditCredentialButton()
				.setUserName("Pepito")
				.setPassword("Springfield")
				.waitForErrorMessage();
	}
	
	@Then("^An error message is displayed$")
	public void an_error_message_is_displayed() throws Throwable {
		String expectedErrorMessage = "Credentials don't have authorization, please try with another";
		EmailServerPage emailServer = new EmailServerPage();
		
		String myActualErrorMessage = emailServer
				.getErrorMessage();
		
		Assert.assertEquals(myActualErrorMessage, expectedErrorMessage);
	}
}
