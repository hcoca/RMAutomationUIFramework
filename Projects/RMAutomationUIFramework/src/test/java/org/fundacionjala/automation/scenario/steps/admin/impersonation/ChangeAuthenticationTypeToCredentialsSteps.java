package org.fundacionjala.automation.scenario.steps.admin.impersonation;

import org.fundacionjala.automation.framework.pages.admin.home.AdminPage;
import org.fundacionjala.automation.framework.pages.admin.login.LoginPage;
import org.fundacionjala.automation.framework.utils.api.managers.SettingsAPIManager;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Settings;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.testng.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ChangeAuthenticationTypeToCredentialsSteps {
	
	@Given("^a user has logged in$")
	public void a_user_has_logged_in() throws Throwable {
		String authenticationType = SettingsAPIManager.getRequest("http://172.20.208.84:4040/settings").authentication;
		
		if(authenticationType.equals("credentials")) {
			Settings settings = new Settings("rfid", 5, "blue");
			SettingsAPIManager.putRequest("http://172.20.208.84:4040/settings", settings);
		}
		
		BrowserManager.openBrowser();
		LoginPage login = new LoginPage();
		
		login
			.setUserName("SamuelSahonero")
			.setPassword("Control*123")
			.clickOnSigInButton()
			.refreshPage();
	}
	
	@When("^he changes Authentication to User and Password$")
	public void he_changes_Authentication_to_User_and_Password() throws Throwable {
		AdminPage admin = new AdminPage();
		
	    admin
	    	.leftMenu
	    	.clickOnImpersonationButton()
	    	.clickOnUserAndPasswordRadioButton()
	    	.clickOnSaveButton()
	    	.waitForImpersonationMessageDisappear();
	}
	
	@Then("^the Authentication Type is changed to Credentials$")
	public void the_Athentication_Type_is_changed_to_Credentials() throws Throwable {
	    String actualAuthenticationType = SettingsAPIManager.getRequest("http://172.20.208.84:4040/settings").authentication;
	    String expectedAuthenticationType = "credentials";
	    
	    Assert.assertEquals(actualAuthenticationType, expectedAuthenticationType);
	    
	    if(actualAuthenticationType.equals("rfid")) {
			Settings settings = new Settings("credentials", 5, "blue");
			SettingsAPIManager.putRequest("http://172.20.208.84:4040/settings", settings);
		}
	}
}
