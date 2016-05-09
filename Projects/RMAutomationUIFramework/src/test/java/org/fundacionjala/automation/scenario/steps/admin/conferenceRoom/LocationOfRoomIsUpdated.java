package org.fundacionjala.automation.scenario.steps.admin.conferenceRoom;

import org.fundacionjala.automation.framework.pages.admin.conferencerooms.ConferenceRoomsPage;
import org.fundacionjala.automation.framework.pages.admin.home.AdminPage;
import org.fundacionjala.automation.framework.utils.api.managers.LocationAPIManager;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Location;
import org.testng.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LocationOfRoomIsUpdated {
	
	String locationEndPoint = "http://172.20.208.84:4040/locations";
	String locationId = "";
	
	String expectedResult;
	String room;
	ConferenceRoomsPage ConferenceRoom = new ConferenceRoomsPage();
	AdminPage Home = new AdminPage();
	
	@Given("^I have a new Location \"([^\"]*)\"$")
	public void i_have_a_new_Location(String locationName) throws Throwable {
		Location location = new Location(locationName, locationName, "", "", locationName);
		location = LocationAPIManager.postRequest(locationEndPoint, location);
		locationId = location._id;
	}
	
	@When("^I edit \"([^\"]*)\" room with a new Location \"([^\"]*)\"$")
	public void i_edit_room_with_a_new_Location(String roomName, String locationName) throws Throwable {
		expectedResult = locationName;
		room = roomName;
		
		ConferenceRoom = 
		Home
		   	.leftMenu
		   	.clickOnConferenceRoomsButton()
		   	.openConfigurationPage(roomName)
		   	.clickOnLocationIconPlus()
		   	.selectLocation(locationName)
		   	.clickOnLocationIconPlus()
		   	.clickOnSave();
	}

	@Then("^I validate if the Location has been updated in the Room Info page\\.$")
	public void i_validate_if_the_Location_has_been_updated_in_the_Room_Info_page() throws Throwable {
		Assert.assertTrue(
				Home
			   	.leftMenu
			   	.clickOnConferenceRoomsButton()
			   	.openConfigurationPage(room)
				.VerifyIfLocationUpdate(expectedResult)
				);
		LocationAPIManager.deleteRequest(locationEndPoint, locationId);
	}
}
