package org.fundacionjala.automation.scenario.steps.admin.location;

import org.fundacionjala.automation.framework.pages.admin.locations.LocationPage;
import org.fundacionjala.automation.framework.utils.api.managers.LocationAPIManager;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Location;
import org.testng.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class DeleteLocationSteps {
	private Location location;
	private LocationPage locationPage;
	private String nameDeletedLocation;

	@Given("^I have a location added with name: \"([^\"]*)\" and display name\"([^\"]*)\" to delete$")
	public void i_have_a_location_added_with_name_and_display_name_to_delete(String arg1, String arg2) throws Throwable {
		location = LocationAPIManager.postRequest("http://172.20.208.84:4040/locations", new Location(arg1, arg2, "", "", ""));
	}

	@When("^I delete the location$")
	public void i_delete_the_location() throws Throwable {
		this.nameDeletedLocation = location.name;
		locationPage = new LocationPage();
		
		locationPage
		.clickOnALocation(location.customName)
		.clickOnRemoveButton()
		.clickOnRemoveButton();
		
	}

	@Then("^The location updated is not displayed on location page$")
	public void the_location_updated_is_not_displayed_on_location_page() throws Throwable {
	    Assert.assertTrue(locationPage.verifyLocationIsNotDisplayed(nameDeletedLocation));
	}
}
