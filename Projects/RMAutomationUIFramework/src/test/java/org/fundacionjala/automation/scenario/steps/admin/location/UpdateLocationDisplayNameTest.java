package org.fundacionjala.automation.scenario.steps.admin.location;

import org.fundacionjala.automation.framework.pages.admin.locations.LocationPage;
import org.fundacionjala.automation.framework.utils.api.managers.LocationAPIManager;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Location;
import org.testng.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class UpdateLocationDisplayNameTest {
	private Location location;
	private LocationPage locationPage;
	private String updatedDisplayName;
	
	@Given("^I have a location added with name: \"([^\"]*)\", display name\"([^\"]*)\" and description\"([^\"]*)\" to update its display name$")
	public void i_have_a_location_added_with_name_display_name_and_description_to_update_its_display_name(String arg1, String arg2, String arg3) throws Throwable {
		location = LocationAPIManager.postRequest("http://172.20.208.84:4040/locations", new Location(arg1, arg2, "", "", arg3));
	}

	@When("^I update a location display name with: \"([^\"]*)\"$")
	public void i_update_a_location_display_name_with(String arg1) throws Throwable {
		this.updatedDisplayName = arg1;
		locationPage = new LocationPage();
		
		locationPage
		.doubleClickOnALocation(location.customName)
		.setDisplayNameField(arg1)
		.clickOnSaveButton();
	}

	@Then("^The updated location display name is displayed on location page$")
	public void the_updated_location_display_name_is_displayed_on_location_page() throws Throwable {
		Assert.assertTrue(locationPage.verifyLocationIsDisplayedByDisplayName(updatedDisplayName));
		
		//PostConditions
		LocationAPIManager.deleteRequest("http://172.20.208.84:4040/locations", location._id);
	}
}
