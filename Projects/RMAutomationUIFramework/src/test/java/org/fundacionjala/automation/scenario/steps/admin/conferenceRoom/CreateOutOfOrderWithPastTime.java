package org.fundacionjala.automation.scenario.steps.admin.conferenceRoom;

import org.fundacionjala.automation.framework.pages.admin.conferencerooms.ConferenceRoomsPage;
import org.fundacionjala.automation.framework.pages.admin.home.AdminPage;
import cucumber.api.java.en.When;

public class CreateOutOfOrderWithPastTime {
    AdminPage home;
    ConferenceRoomsPage room;

    @When("^I create an OuOfOrder on \"([^\"]*)\" room with a time in the past$")
    public void createOuOfOrderWithPastTime(String roomName) throws Throwable {
	AdminPage adminPage = new AdminPage();
	room = adminPage.leftMenu.clickOnConferenceRoomsButton()
                    		 .openConfigurationPage(roomName)
                    		 .clickOnOutOfOrder()
                    		 .setTimeBeginDown()
                    		 .setTimeEndDown()
                    		 .activeOutOfOrder()
                    		 .clickOnSave();
    }
}
