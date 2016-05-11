package org.fundacionjala.automation.scenario.steps.admin.location;

import org.fundacionjala.automation.framework.pages.admin.login.LoginPage;
import org.fundacionjala.automation.framework.utils.api.managers.LocationAPIManager;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Location;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.PropertiesReader;

import cucumber.api.java.en.Given;

public class LocationGivenSteps {
	
	@Given("^I am logged as \"([^\"]*)\" with password \"([^\"]*)\"$")
	public void logInWithAnAccount(String userName, 
				       String password) throws Throwable {
		
		BrowserManager.openBrowser();
		LoginPage loginPage = new LoginPage();
	    
		loginPage
			.setUserName(userName)
			.setPassword(password)
			.clickOnSigInButton()
			.refreshPage();
	}
	
	@Given("^I have a location added with name: \"([^\"]*)\", display name \"([^\"]*)\" and description \"([^\"]*)\"$")
	public void addLocation(String name, 
				String displayName, 
				String description) throws Throwable {
		
		LocationAPIManager.postRequest(PropertiesReader.getServiceURL() 
			+ "/locations", 
			new Location(name, displayName, "", "", description));
	}
}
