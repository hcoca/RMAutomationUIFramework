package org.fundacionjala.automation.scenario.steps.admin.location;

import org.fundacionjala.automation.framework.pages.admin.locations.LocationPage;
import org.fundacionjala.automation.framework.pages.admin.locations.UpdateLocationPage;
import org.fundacionjala.automation.framework.utils.api.managers.LocationAPIManager;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Location;
import org.testng.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class UpdateLocationDescriptionSteps {
	
	private Location location;
	private LocationPage locationPage;
	private UpdateLocationPage updateLocationPage;
	private String updatedDescription;
	@Given("^I have a location added with name: \"([^\"]*)\", display name\"([^\"]*)\" and description\"([^\"]*)\" to update its description$")
	public void i_have_a_location_added_with_name_display_name_and_description_to_update_its_description(String arg1, String arg2, String arg3) throws Throwable {
		location = LocationAPIManager.postRequest("http://172.20.208.84:4040/locations", new Location(arg1, arg2, "", "", arg3));
	}

	@When("^I update a location description with: \"([^\"]*)\"$")
	public void i_update_a_location_description_with(String arg1) throws Throwable {
		this.updatedDescription = arg1;
		locationPage = new LocationPage();
		
		updateLocationPage =
		locationPage
		.doubleClickOnALocation(location.customName)
		.setDescriptionArea(arg1)
		.clickOnSaveButton()
		.doubleClickOnALocation(location.customName);
	}

	@Then("^The updated location description is displayed on update location page$")
	public void the_updated_location_description_is_displayed_on_update_location_page() throws Throwable {
		Assert.assertTrue(updateLocationPage.verifyDescriptionIsDisplayed(updatedDescription));
		
		//PostConditions
		LocationAPIManager.deleteRequest("http://172.20.208.84:4040/locations", location._id);
	}
}
