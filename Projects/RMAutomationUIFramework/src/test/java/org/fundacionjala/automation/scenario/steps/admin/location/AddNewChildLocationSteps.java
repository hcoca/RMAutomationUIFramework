package org.fundacionjala.automation.scenario.steps.admin.location;

import java.util.List;

import org.fundacionjala.automation.framework.pages.admin.locations.LocationPage;
import org.fundacionjala.automation.framework.utils.api.managers.LocationAPIManager;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Location;
import org.testng.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AddNewChildLocationSteps {
	
	private String name;
	private LocationPage locationPage;
	private Location locationParent;
	@Given("^I have one parent location added with name: \"([^\"]*)\" and display name\"([^\"]*)\"$")
	public void i_have_one_parent_location_added_with_name_and_display_name(String arg1, String arg2) throws Throwable {
		locationParent = LocationAPIManager.postRequest("http://172.20.208.84:4040/locations", new Location(arg1, arg2, "", "", ""));
	}

	@When("^I add a new location with name: \"([^\"]*)\" and display name\"([^\"]*)\" as child$")
	public void i_add_a_new_location_with_name_and_display_name_as_child(String arg1, String arg2) throws Throwable {
		this.name = arg1;
		locationPage = new LocationPage();
		
		locationPage
		.clickOnAddButton()
		.setNameField(arg1)
	    .setDisplayNameField(arg2)
		.clickOnAddParentButton().selectAParentLocation(locationParent.name)
		.clickOnSaveButton();
	}

	@Then("^The new child location added is displayed on location page$")
	public void the_new_child_location_added_is_displayed_on_location_page() throws Throwable {
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
		LocationAPIManager.deleteRequest("http://172.20.208.84:4040/locations", locationParent._id);
	}
}
