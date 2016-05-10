package org.fundacionjala.automation.scenario.steps.admin.service;

import org.fundacionjala.automation.framework.pages.admin.emailserver.AddEmailServerPage;
import org.fundacionjala.automation.framework.pages.admin.emailserver.EmailServerPage;
import org.fundacionjala.automation.framework.pages.admin.login.LoginPage;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.testng.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class RoomsAreDeletedWhenServiceIsDeleted {
	
	@Given("^There is an Emailservice added$")
	public void there_is_an_emailservice_added() throws Throwable {
		BrowserManager.openBrowser();
		LoginPage login = new LoginPage();
		
		EmailServerPage emailServer = login
				.setUserName("SamuelSahonero")
				.setPassword("Control*123")
				.clickOnSigInButton()
				.refreshPage()
				.leftMenu
				.clickOnEmailServerButton();
		
		boolean isAddButtonPresent = emailServer.findAddButton();
		
		if(isAddButtonPresent == true) {
			
			AddEmailServerPage addEmailServer = emailServer
					.clickOnAddButton();
			
			emailServer = addEmailServer
					.setDomainServer("roommanager.local")
					.setUserName("Administrator")
					.setPassword("Control*123")
					.clickSaveButton();
		}
	}
	
	@When("^I delete the Emailservice$")
	public void i_delete_the_Emailservice() throws Throwable {
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
	
	@Then("^There is no rooms$")
	public void there_is_no_rooms() throws Throwable {
		EmailServerPage emailServer = new EmailServerPage();
		
		boolean areConferenceRoomsPresent = emailServer
				.verifyIfThereAreRooms();

		Assert.assertFalse(areConferenceRoomsPresent);
		
		LoginPage login = new LoginPage();
		
		EmailServerPage server = login
				.setUserName("SamuelSahonero")
				.setPassword("Control*123")
				.clickOnSigInButton()
				.refreshPage()
				.leftMenu
				.clickOnEmailServerButton();
		
		boolean isAddButtonPresent = server.findAddButton();
		
		if(isAddButtonPresent == true) {
			
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
