package org.fundacionjala.automation.scenario.steps.admin.location;

import java.util.List;

import org.fundacionjala.automation.framework.pages.admin.locations.LocationAssociationPage;
import org.fundacionjala.automation.framework.pages.admin.locations.LocationPage;
import org.fundacionjala.automation.framework.utils.api.managers.LocationAPIManager;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Location;
import org.testng.Assert;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AssociationNewLocationSteps {
	private String name;
	private String roomName;
	private LocationAssociationPage associationPage;
	private LocationPage locationPage;
	@When("^I add a new location with name: \"([^\"]*)\" and display name\"([^\"]*)\" with an associated room\"([^\"]*)\"$")
	public void i_add_a_new_location_with_name_and_display_name_with_an_associated_room(String arg1, String arg2, String arg3) throws Throwable {
		this.name = arg1;
		this.roomName = arg3;
		locationPage = new LocationPage();
		
		associationPage =
	    locationPage
	    .clickOnAddButton()
	    .setNameField(arg1)
	    .setDisplayNameField(arg2)
	    .clickOnLocationAssociationLink()
	    .clickOnAddAvailableRoom(arg3)
	    .clickOnSaveButton()
	    .doubleClickOnALocation(arg2)
	    .clickOnLocationAssociationLink();
	}

	@Then("^The room is displayed on Location Association page as associated$")
	public void the_room_is_displayed_on_Location_Association_page_as_associated() throws Throwable {
	    Assert.assertTrue(associationPage.verifyAnAssociatedRoomDisplayed(roomName));
	    
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
