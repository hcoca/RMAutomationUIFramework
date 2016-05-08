package org.fundacionjala.automation.scenario.steps.admin.location;

import org.fundacionjala.automation.framework.pages.admin.locations.LocationAssociationPage;
import org.fundacionjala.automation.framework.pages.admin.locations.LocationPage;
import org.fundacionjala.automation.framework.utils.api.managers.LocationAPIManager;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Location;
import org.testng.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AssociationUpdateLocationSteps {
	
	private Location location;
	private LocationPage locationPage;
	private String roomName;
	private LocationAssociationPage associationPage;
	@Given("^I have a location added with name: \"([^\"]*)\" and display name\"([^\"]*)\" to update its associations$")
	public void i_have_a_location_added_with_name_and_display_name_to_update_its_associations(String arg1, String arg2) throws Throwable {
		location = LocationAPIManager.postRequest("http://172.20.208.84:4040/locations", new Location(arg1, arg2, "", "", ""));
	}

	@When("^I associate the location with a room\"([^\"]*)\"$")
	public void i_associate_the_location_with_a_room(String arg1) throws Throwable {
		this.roomName = arg1;
		locationPage = new LocationPage();
		
		associationPage =
	    locationPage
	    .doubleClickOnALocation(location.customName)
	    .clickOnLocationAssociationLink()
	    .clickOnAddAvailableRoom(arg1)
	    .clickOnSaveButton()
	    .doubleClickOnALocation(location.customName)
	    .clickOnLocationAssociationLink();
	}

	@Then("^The room is displayed on Location Association page as location association$")
	public void the_room_is_displayed_on_Location_Association_page_as_location_association() throws Throwable {
		Assert.assertTrue(associationPage.verifyAnAvailableRoomDisplayed(roomName));
		
		//PostConditions
		LocationAPIManager.deleteRequest("http://172.20.208.84:4040/locations", location._id);
	}
}
