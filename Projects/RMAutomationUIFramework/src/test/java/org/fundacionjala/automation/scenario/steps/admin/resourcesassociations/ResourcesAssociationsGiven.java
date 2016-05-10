package org.fundacionjala.automation.scenario.steps.admin.resourcesassociations;

import org.fundacionjala.automation.framework.pages.admin.home.AdminPage;
import org.fundacionjala.automation.framework.pages.admin.login.LoginActions;

import cucumber.api.java.en.Given;

public class ResourcesAssociationsGiven {
	private AdminPage home;

	
	@Given("^I am on the conference rooms page$")
	public void i_am_on_the_conference_rooms_page() throws Throwable {
	   	
	   home = LoginActions.ExecuteLogin()
			   .leftMenu
			   .clickOnConferenceRoomsButton();
	}
	
}
