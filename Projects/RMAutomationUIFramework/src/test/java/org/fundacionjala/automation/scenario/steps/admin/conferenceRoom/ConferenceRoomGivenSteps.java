package org.fundacionjala.automation.scenario.steps.admin.conferenceRoom;

import org.fundacionjala.automation.framework.pages.admin.home.AdminPage;
import org.fundacionjala.automation.framework.utils.api.managers.RoomAPIManager;
import cucumber.api.java.en.Given;

public class ConferenceRoomGivenSteps {

    @Given("^I have \"([^\"]*)\" room disabled$")
    public void disableRoom(String roomName) throws Throwable {
	RoomAPIManager.changeStatusOfRoom(roomName, false);
    }

    @Given("^I have \"([^\"]*)\" room enabled$")
    public void enableRoom(String roomName) throws Throwable {
	RoomAPIManager.changeStatusOfRoom(roomName, true);
    }

    @Given("^I select a \"([^\"]*)\" that has a previous page$")
    public void selectAPreviousPage(String page)
	    throws Throwable {
	AdminPage home = new AdminPage();
	home.leftMenu.clickOnConferenceRoomsButton()
		.setPage(page);
    }
}
