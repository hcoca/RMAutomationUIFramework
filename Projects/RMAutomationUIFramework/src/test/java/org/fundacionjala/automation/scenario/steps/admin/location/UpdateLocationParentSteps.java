package org.fundacionjala.automation.scenario.steps.admin.location;

import org.fundacionjala.automation.framework.pages.admin.locations.LocationPage;
import org.fundacionjala.automation.framework.pages.admin.locations.UpdateLocationPage;
import org.fundacionjala.automation.framework.utils.api.managers.LocationAPIManager;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Location;
import org.testng.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class UpdateLocationParentSteps {
	
	private Location locationParent;
	private Location locationChild;
	private LocationPage locationPage;
	private UpdateLocationPage updateLocationPage;
	private String updatedParent;
	@Given("^I have a parent location added with name: \"([^\"]*)\" and display name\"([^\"]*)\"$")
	public void i_have_a_parent_location_added_with_name_and_display_name(String arg1, String arg2) throws Throwable {
		locationParent = LocationAPIManager.postRequest("http://172.20.208.84:4040/locations", new Location(arg1, arg2, "", "", ""));
	}

	@Given("^I have a location added with name: \"([^\"]*)\" and display name\"([^\"]*)\" to update its parent$")
	public void i_have_a_location_added_with_name_and_display_name_to_update_its_parent(String arg1, String arg2) throws Throwable {
		locationChild = LocationAPIManager.postRequest("http://172.20.208.84:4040/locations", new Location(arg1, arg2, "", "", ""));
	}

	@When("^I update a location parent$")
	public void i_update_a_location_parent() throws Throwable {
		this.updatedParent = locationParent.name;
		locationPage = new LocationPage();
		
		updateLocationPage =
		locationPage
		.doubleClickOnALocation(locationChild.customName)
		.clickOnAddParentButton().selectAParentLocation(locationParent.name)
		.clickOnSaveButton()
		.doubleClickOnALocation(locationChild.customName);
	    
	}

	@Then("^The updated location parent is displayed on update location page$")
	public void the_updated_location_parent_is_displayed_on_update_location_page() throws Throwable {
		Assert.assertTrue(updateLocationPage.verifyParentIsDisplayed(updatedParent));
		
		//PostConditions
		LocationAPIManager.deleteRequest("http://172.20.208.84:4040/locations", locationChild._id);
		LocationAPIManager.deleteRequest("http://172.20.208.84:4040/locations", locationParent._id);
	}
}
