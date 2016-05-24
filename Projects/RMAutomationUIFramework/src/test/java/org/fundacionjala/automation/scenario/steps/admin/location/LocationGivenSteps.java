package org.fundacionjala.automation.scenario.steps.admin.location;

import org.fundacionjala.automation.framework.pages.admin.login.LoginPage;
import org.fundacionjala.automation.framework.pages.admin.resource.ResourcesActions;
import org.fundacionjala.automation.framework.utils.api.managers.LocationAPIManager;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Location;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Resource;
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
			.clickOnSigInButton();
	}
	
	@Given("^I have a location added with name: \"([^\"]*)\", display name \"([^\"]*)\" and description \"([^\"]*)\"$")
	public void addLocation(String name, 
				String displayName, 
				String description) throws Throwable {
		try {
			LocationAPIManager.postRequest(PropertiesReader.getServiceURL() 
					+ "/locations", 
					new Location(name, displayName, "", "", description));

			
		} catch (Exception e) {
		}
		}
	
	@Given("^I have at least \"([^\"]*)\" locations created$")
	public void i_have_at_least_locations_created(String locationQuantity) throws Throwable {
		int quantity = Integer.parseInt(locationQuantity);
		for (int i = 0; i < quantity; i++) {
			Location location = new Location("location" + i,
											 "displayName" + i,
											 "", "", 
											 "This is a new Location "+i+" added");
			LocationAPIManager.postRequest(PropertiesReader.getServiceURL() 
					+ "/locations", location);
		}
	}
}
