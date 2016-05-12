package org.fundacionjala.automation.scenario.steps.admin.conferenceRoom;

import org.fundacionjala.automation.framework.pages.admin.conferencerooms.ConferenceRoomsPage;
import org.fundacionjala.automation.framework.pages.admin.home.AdminPage;
import cucumber.api.java.en.When;

public class CreateOutOfOrderWithPastDate {
    AdminPage home;
    ConferenceRoomsPage room;

    @When("^I create an OuOfOrder on \"([^\"]*)\" with a date in the past$")
    public void createOuOfOrderWithPastDate(String roomName) throws Throwable {
	room = home.leftMenu.clickOnConferenceRoomsButton()
		.openConfigurationPage(roomName).clickOnOutOfOrder()
		.setTimeBeginDown().setTimeEndDown().activeOutOfOrder()
		.clickOnSave();
    }
}
