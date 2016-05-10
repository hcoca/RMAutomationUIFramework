package org.fundacionjala.automation.scenario.steps.admin.location;

import org.fundacionjala.automation.framework.pages.admin.login.LoginPage;
import org.fundacionjala.automation.framework.utils.api.managers.LocationAPIManager;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Location;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;

import cucumber.api.java.en.Given;

public class LocationGivenSteps {
	
	@Given("^I am logged as \"([^\"]*)\" with password \"([^\"]*)\"$")
	public void i_am_logged_as_with_password(String userName, String password) throws Throwable {
		BrowserManager.openBrowser();
	    LoginPage loginPage = new LoginPage();
	    
	    loginPage
	    .setUserName(userName)
	    .setPassword(password)
	    .clickOnSigInButton()
	    .refreshPage();
	}
	
	@Given("^I have a location added with name: \"([^\"]*)\", display name \"([^\"]*)\" and description \"([^\"]*)\"$")
	public void i_have_a_location_added_with_name_display_name_and_description(String name, String displayName, String description) throws Throwable {
		LocationAPIManager.postRequest("http://172.20.208.84:4040/locations", new Location(name, displayName, "", "", description));
	}
}
