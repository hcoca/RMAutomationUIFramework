package org.fundacionjala.automation.scenario.steps.admin.impersonation;

import org.fundacionjala.automation.framework.pages.admin.home.AdminPage;
import org.fundacionjala.automation.framework.pages.admin.login.LoginPage;
import org.fundacionjala.automation.framework.pages.tablet.home.HomePage;
import org.fundacionjala.automation.framework.pages.tablet.scheduler.CredentialsPage;
import org.fundacionjala.automation.framework.pages.tablet.scheduler.SchedulerPage;
import org.fundacionjala.automation.framework.pages.tablet.settings.ConnectionPage;
import org.fundacionjala.automation.framework.pages.tablet.settings.NavigationPage;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.testng.Assert;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ImpersonationOptionsAreDisplayedInCredentialsPageSteps {

	private CredentialsPage credentials;
	
	@Given("^a user \"([^\"]*)\" that has logged in$")
	public void a_user_that_has_logged_in(String arg1) throws Throwable {
		BrowserManager.openBrowser();
		LoginPage login = new LoginPage();
		
		login
			.setUserName(arg1)
			.setPassword("Control*123")
			.clickOnSigInButton()
			.refreshPage();
	}
	
	@And("^he enables Impersonation Option$")
	public void he_enables_Impersonation_Option() throws Throwable {
		AdminPage admin = new AdminPage();
		
	    admin
	    	.leftMenu
	    	.clickOnImpersonationButton()
	    	.clickOnUseImpersonationCheckBox()
	    	.clickOnSaveButton();
	}
	
	@When("^he tries to create a new meeting$")
	public void he_tries_to_create_a_new_meeting() throws Throwable {
	    ConnectionPage connection = new ConnectionPage();
	    
		NavigationPage navigation = connection
			    	.setUpServiceURL("http://172.20.208.84:4040/")
			    	.clickOnSaveButton()
			    	.clickOnNavigationButton();
	    	
	    HomePage home =	navigation
			    	.clickOnRoomToggleButton()
			    	.selectConferenceRoom("Room05")
			    	.clickOnSaveButton()
			    	.topMenu
			    	.clickOnHomeButton();
	    
	    SchedulerPage scheduler = home
	    			.clickOnScheduleButton()
	    			.setOrganizer("Administrator")
	    			.setSubject("My Subject")
	    			.setAttende("RoomManager1@roommanager.local");
	    
	    credentials = scheduler
	    			.clickOnCreateButton();
	}
	
	@Then("^the Impersonation Options displayed in the Credentials Page$")
	public void the_Impersonation_Options_displayed_in_the_Credentials_Page() throws Throwable {
		boolean impersonationOptionsArePresent = false;
		
		if(credentials.isCreateAsCheckBoxPresent() && credentials.isCreateInBehalfOfTextFieldPresent()) {
			impersonationOptionsArePresent = true;
		}
		
		Assert.assertTrue(impersonationOptionsArePresent);
	}
}
