package org.fundacionjala.automation.scenario.steps.admin.service;

import java.util.List;

import org.fundacionjala.automation.framework.pages.admin.emailserver.AddEmailServerPage;
import org.fundacionjala.automation.framework.pages.admin.emailserver.EmailServerPage;
import org.fundacionjala.automation.framework.pages.admin.login.LoginPage;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class RoomsAreAddedWhenServiceAdded {
	
	@Given("^There is no Email Server added$")
	public void there_is_no_email_server_added() throws Throwable {
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
	}

	@When("^I add a new Email Server$")
	public void i_add_a_new_Exchange_Email_Server() throws Throwable {
		LoginPage login = new LoginPage();
		
		EmailServerPage emailServer = login
				.setUserName("SamuelSahonero")
				.setPassword("Control*123")
				.clickOnSigInButton()
				.refreshPage()
				.leftMenu
				.clickOnEmailServerButton();
		
		AddEmailServerPage addEmailServer = emailServer
				.clickOnAddButton();
		
		emailServer = addEmailServer
				.setDomainServer("roommanager.local")
				.setUserName("Administrator")
				.setPassword("Control*123")
				.clickSaveButton();
	}
	
	@Then("^All Conference rooms are added from Exchange service$")
	public void all_Conference_rooms_are_added_from_Exchange_service() throws Throwable {
		EmailServerPage emailServer = new EmailServerPage();
		
		List<WebElement> roomsList = emailServer
				.leftMenu
				.clickOnConferenceRoomsButton()
				.getRooms();
		
		Assert.assertEquals(roomsList.size(), 210);
	}
}
