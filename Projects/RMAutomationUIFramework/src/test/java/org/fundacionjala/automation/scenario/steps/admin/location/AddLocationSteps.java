package org.fundacionjala.automation.scenario.steps.admin.location;

import java.util.List;

import org.fundacionjala.automation.framework.pages.admin.locations.LocationPage;
import org.fundacionjala.automation.framework.pages.admin.login.LoginPage;
import org.fundacionjala.automation.framework.utils.api.managers.LocationAPIManager;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Location;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.testng.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AddLocationSteps {
	private String name;
	private LocationPage locationPage;
	
	@Given("^I am on location page$")
	public void i_am_on_location_page() throws Throwable {
		BrowserManager.openBrowser();
	    LoginPage loginPage = new LoginPage();
	    
	    locationPage =
	    loginPage
	    .clickOnSigInButton()
	    .refreshPage()
		.leftMenu
		.clickOnLocationsButton();
	}

	@When("^I add a new location with name: \"([^\"]*)\", display name\"([^\"]*)\" and description\"([^\"]*)\"$")
	public void i_add_a_new_location_with_name_display_name_and_description(String arg1, String arg2, String arg3) throws Throwable {
	    this.name = arg1;

	    locationPage
	    .clickOnAddButton()
	    .setNameField(arg1)
	    .setDisplayNameField(arg2)
	    .setDescriptionArea(arg3)
	    .clickOnSaveButton();
	}

	@Then("^The new location added is displayed on location page$")
	public void the_new_location_added_is_displayed_on_location_page() throws Throwable {
		Assert.assertTrue(locationPage.verifyLocationIsDisplayed(name));
		
		//PostConditions
		String idLocation = "";
		List<Location> listLocation = LocationAPIManager.getRequest("http://172.20.208.84:4040/locations");
		for (Location location : listLocation) {
			if(location.name.equalsIgnoreCase(name))
			{
				idLocation = location._id;
			}
		}
		LocationAPIManager.deleteRequest("http://172.20.208.84:4040/locations", idLocation);
	}
}
