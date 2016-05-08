package org.fundacionjala.automation.scenario.steps.admin.location;

import org.fundacionjala.automation.framework.pages.admin.locations.LocationPage;
import org.fundacionjala.automation.framework.utils.api.managers.LocationAPIManager;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Location;
import org.testng.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class NumberOfAssociationIncreasesSteps {
	
	private Location location;
	private LocationPage locationPage;
	@Given("^I have a location added with name: \"([^\"]*)\" and display name\"([^\"]*)\" to add an associations$")
	public void i_have_a_location_added_with_name_and_display_name_to_add_an_associations(String arg1, String arg2) throws Throwable {
		location = LocationAPIManager.postRequest("http://172.20.208.84:4040/locations", new Location(arg1, arg2, "", "", ""));
	}

	@When("^I add a location association with a room\"([^\"]*)\"$")
	public void i_add_a_location_association_with_a_room(String arg1) throws Throwable {
		locationPage = new LocationPage();
		
	    locationPage
	    .doubleClickOnALocation(location.customName)
	    .clickOnLocationAssociationLink()
	    .clickOnAddAvailableRoom(arg1)
	    .clickOnSaveButton();
	}

	@Then("^The number of assciation on Location page has been increased$")
	public void the_number_of_assciation_on_Location_page_has_been_increased() throws Throwable {
		Assert.assertTrue(locationPage.verifyNumberOfAssociations(location.name, "1"));
		
		//PostConditions
		LocationAPIManager.deleteRequest("http://172.20.208.84:4040/locations", location._id);
	}
}
