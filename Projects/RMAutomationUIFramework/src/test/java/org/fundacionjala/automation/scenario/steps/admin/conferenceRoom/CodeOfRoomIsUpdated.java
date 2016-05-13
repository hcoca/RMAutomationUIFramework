package org.fundacionjala.automation.scenario.steps.admin.conferenceRoom;

import org.fundacionjala.automation.framework.pages.admin.conferencerooms.ConferenceRoomsPage;
import org.fundacionjala.automation.framework.pages.admin.home.AdminPage;
import org.testng.Assert;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CodeOfRoomIsUpdated {
    String expectedResult;
    String room;
    ConferenceRoomsPage ConferenceRoom = new ConferenceRoomsPage();
    AdminPage Home = new AdminPage();

    @When("^I edit \"([^\"]*)\" room with a new code \"([^\"]*)\"$")
    public void i_edit_room_with_a_new_code(String roomName, String newCode)
	    throws Throwable {
	
	expectedResult = newCode;
	room = roomName;
	ConferenceRoom = Home.leftMenu.clickOnConferenceRoomsButton()
		.openConfigurationPage(roomName)
		.typeOnCode(newCode)
		.clickOnSave();
    }

    @Then("^I validate if the Code has been updated in the Room Info page.$")
    public void i_validate_if_the_Code_has_been_updated_in_the_Room_Info_Page()
	    throws Throwable {
	
	Assert.assertTrue(Home.leftMenu
		.clickOnConferenceRoomsButton()
		.openConfigurationPage(room)
		.VerifyIfCodeUpdate(expectedResult));

    }
}
