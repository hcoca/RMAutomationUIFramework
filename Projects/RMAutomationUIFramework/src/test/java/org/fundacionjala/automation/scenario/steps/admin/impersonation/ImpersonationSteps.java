package org.fundacionjala.automation.scenario.steps.admin.impersonation;

import org.fundacionjala.automation.framework.pages.admin.home.AdminPage;
import org.fundacionjala.automation.framework.pages.admin.login.LoginPage;
import org.fundacionjala.automation.framework.utils.api.managers.SettingsAPIManager;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.testng.Assert;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ImpersonationSteps {

	private LoginPage login;
	private AdminPage admin;
	
	@Given("^the user \"([^\"]*)\" has logged in$")
	public void the_user_has_logged_in(String arg1) throws Throwable {
		BrowserManager.openBrowser();
		login = new LoginPage();
		
		login
			.setUserName(arg1)
			.setPassword("Control*123")
			.clickOnSigInButton()
			.refreshPage();
	}

	@When("^he enables Impersonation$")
	public void he_enables_Impersonation() throws Throwable {
		admin = new AdminPage();
		
	    admin
	    	.leftMenu
	    	.clickOnImpersonationButton()
	    	.clickOnUseImpersonationCheckBox()
	    	.clickOnSaveButton();
	}
	
	@When("^he disables Impersonation$")
	public void he_disables_Impersonation() throws Throwable {
		admin = new AdminPage();
		
	    admin
	    	.leftMenu
	    	.clickOnImpersonationButton()
	    	.clickOnUseImpersonationCheckBox()
	    	.clickOnSaveButton();
	}
	
	@When("^he changes Authentication to User and Password$")
	public void he_changes_Authentication_to_User_and_Password() throws Throwable {
		admin = new AdminPage();
		
	    admin
	    	.leftMenu
	    	.clickOnImpersonationButton()
	    	.clickOnUserAndPasswordRadioButton()
	    	.clickOnSaveButton();
	}
	
	@When("^he tries to create a new meeting$")
	public void he_tries_to_create_a_new_meeting() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}
	
	@When("^he changes Authentication to RFID$")
	public void he_changes_Authentication_to_RFID() throws Throwable {
		admin = new AdminPage();
		
	    admin
	    	.leftMenu
	    	.clickOnImpersonationButton()
	    	.clickOnRFIDRadioButton()
	    	.clickOnSaveButton();
	}
	
	@When("^there is no Email Server Added$")
	public void there_is_no_Email_Server_Added() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}
	
	@Then("^the Authentication Type is changed to Credentials$")
	public void the_Athentication_Type_is_changed_to_Credentials() throws Throwable {
	    String actualAuthenticationType = SettingsAPIManager.getRequest("http://172.20.208.84:4040/settings").authentication;
	    String expectedAuthenticationType = "credentials";
	    Assert.assertEquals(actualAuthenticationType, expectedAuthenticationType);
	}

	@Then("^the Impersonation Option is enabled$")
	public void the_Impersonation_Option_is_enabled() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^the Impersonation Options displayed in the Credentials Page$")
	public void the_Impersonation_Options_displayed_in_the_Credentials_Page() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}
	
	@Then("^the Impersonation Option is disabled$")
	public void the_Impersonation_Option_is_disabled() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}
	
	@Then("^the Authentication Type is changed to RFID$")
	public void the_Athentication_Type_is_changed_to_RFID() throws Throwable {
		String actualAuthenticationType = SettingsAPIManager.getRequest("http://172.20.208.84:4040/settings").authentication;
	    String expectedAuthenticationType = "rfid";
	    Assert.assertEquals(actualAuthenticationType, expectedAuthenticationType);
	}
}
