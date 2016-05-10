package org.fundacionjala.automation.scenario.steps.admin.service;

import org.fundacionjala.automation.framework.pages.admin.conferencerooms.ConferenceRoomsPage;
import org.fundacionjala.automation.framework.pages.admin.emailserver.AddEmailServerPage;
import org.fundacionjala.automation.framework.pages.admin.emailserver.EmailServerPage;
import org.fundacionjala.automation.framework.pages.admin.login.LoginPage;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;

import cucumber.api.java.en.Given;

public class ServiceGivenSteps {
	
	@Given("^There is an Email Server added$")
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
	
	@Given("^There is no Email Server added$")
	public void there_is_no_services_added() throws Throwable {
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
	
	@Given("^At least a meeting$")
	public void at_least_a_meeting() throws Throwable {
		LoginPage login = new LoginPage();
		
		ConferenceRoomsPage conferenceRooms = login
			.setUserName("SamuelSahonero")
			.setPassword("Control*123")
			.clickOnSigInButton()
			.refreshPage()
			.leftMenu
			.clickOnIssuesButton()
			.clickOnConferenceRoomsButton();
		
		conferenceRooms
			.doubleClickOnRoom("Room04")
			.SelectOutOfOrder()
			.activeOutOfOrder()
			.clickOnSave();
	}
	
	@Given("^At least an out of order$")
	public void at_least_an_out_of_order() throws Throwable {
		LoginPage login = new LoginPage();
		
		ConferenceRoomsPage conferenceRooms = login
			.setUserName("SamuelSahonero")
			.setPassword("Control*123")
			.clickOnSigInButton()
			.refreshPage()
			.leftMenu
			.clickOnIssuesButton()
			.clickOnConferenceRoomsButton();
		
		conferenceRooms
			.doubleClickOnRoom("Room04")
			.SelectOutOfOrder()
			.activeOutOfOrder()
			.clickOnSave();
	}
}
