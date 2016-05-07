package org.fundacionjala.automation.scenario.steps.admin.conferenceRoom;


import org.fundacionjala.automation.framework.pages.admin.conferencerooms.ConferenceRoomsPage;
import org.fundacionjala.automation.framework.pages.admin.home.AdminPage;
import org.testng.Assert;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class DisplayNameOfRoomIsUpdated {
	
	String expectedResult;
	String roomOriginalName;
	
	ConferenceRoomsPage ConferenceRoom = new ConferenceRoomsPage();
	
	@When("^I edit \"([^\"]*)\" room with a new display name \"([^\"]*)\"$")
	public void i_edit_room_with_a_new_display_name(String roomName, String newDisplayName) throws Throwable {
		expectedResult = newDisplayName;
		roomOriginalName = roomName;
		AdminPage Home = new AdminPage();
		
		ConferenceRoom = 
		Home
		   	.leftMenu
		   	.clickOnConferenceRoomsButton()
		   	.openConfigurationPage(roomName)
		   	.typeOnDisplayName(newDisplayName)
		   	.clickOnSave();
	}

	@Then("^I validate if the display name has been updated in the Conference Room table$")
	public void i_validate_if_the_display_name_has_been_updated_in_the_Conference_Room_table() throws Throwable {
		Assert.assertTrue(
				ConferenceRoom
				.VerifyIfRoomExist(expectedResult)
				);
		
	}
}
