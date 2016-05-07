package org.fundacionjala.automation.scenario.steps.admin.conferenceRoom;

import org.fundacionjala.automation.framework.pages.admin.conferencerooms.ConferenceRoomsPage;
import org.fundacionjala.automation.framework.pages.admin.home.AdminPage;
import org.testng.Assert;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CapacityOfRoomsIsUpdated {
	String expectedResult;
	String room;
	ConferenceRoomsPage ConferenceRoom = new ConferenceRoomsPage();
	AdminPage Home = new AdminPage();
	@When("^I edit \"([^\"]*)\" room with a new capacity \"([^\"]*)\"$")
	public void i_edit_room_with_a_new_capacity(String roomName, String newCapacity) throws Throwable {
		expectedResult = newCapacity;
		room = roomName;
		
		ConferenceRoom = 
		Home
		   	.leftMenu
		   	.clickOnConferenceRoomsButton()
		   	.openConfigurationPage(roomName)
		   	.typeOnCapacity(newCapacity)
		   	.clickOnSave();
	}

	@Then("^I validate if the Capacity has been updated in the Room Info page\\.$")
	public void i_validate_if_the_Capacity_has_been_updated_in_the_Room_Info_page() throws Throwable {
		Assert.assertTrue(
				Home
			   	.leftMenu
			   	.clickOnConferenceRoomsButton()
			   	.openConfigurationPage(room)
				.VerifyIfCapacityUpdate(expectedResult)
				);
	}
}
