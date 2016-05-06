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

public class DisplayAllLocationsSteps {
	private Location location;
	private List<Location> locations;
	private LocationPage locationPage;
	
	@Given("^I have at least one location added with name: \"([^\"]*)\", display name\"([^\"]*)\" and description\"([^\"]*)\"$")
	public void i_have_at_least_one_location_added_with_name_display_name_and_description(String arg1, String arg2, String arg3) throws Throwable {
		location = LocationAPIManager.postRequest("http://172.20.208.84:4040/locations", new Location(arg1, arg2, "572bd5d02af305ac2ee0580c", "", arg3));
	}

	@Given("^I have the list of added locations$")
	public void i_have_the_list_of_added_locations() throws Throwable {
	    locations = LocationAPIManager.getRequest("http://172.20.208.84:4040/locations");
	}

	@When("^I open locations page$")
	public void i_open_locations_page() throws Throwable {
		BrowserManager.openBrowser();
	    LoginPage loginPage = new LoginPage();
	    
	    locationPage =
	    loginPage
	    .clickOnSigInButton()
	    .refreshPage()
		.leftMenu
		.clickOnLocationsButton();
	}

	@Then("^All locations added are displayed$")
	public void all_locations_added_are_displayed() throws Throwable {
		for (Location location : locations) {
			Assert.assertTrue(locationPage.verifyLocationIsDisplayed(location.name));
		}
		
		//PostConditions
	    LocationAPIManager.deleteRequest("http://172.20.208.84:4040/locations", location._id);
	}
}
