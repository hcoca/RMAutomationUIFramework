package org.fundacionjala.automation.scenario.steps.admin.service;

import org.fundacionjala.automation.framework.pages.admin.emailserver.AddEmailServerPage;
import org.fundacionjala.automation.framework.pages.admin.emailserver.EmailServerPage;
import org.fundacionjala.automation.framework.pages.admin.login.LoginPage;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.testng.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ServiceCredentialModified {
	private String newUserName = "RoomManager1";
	private String newPassword = "Control*123";
	
	@Given("^There is a service existent$")
	public void there_is_a_service_existent() throws Throwable {
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

	@When("^I modify credential with another account$")
	public void i_modify_credential_with_another_account() throws Throwable {
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
				.setUserName(newUserName)
				.setPassword(newPassword)
				.clickOnAcceptButton();
	}

	@Then("^The changes are saved$")
	public void the_changes_are_saved() throws Throwable {
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
}
