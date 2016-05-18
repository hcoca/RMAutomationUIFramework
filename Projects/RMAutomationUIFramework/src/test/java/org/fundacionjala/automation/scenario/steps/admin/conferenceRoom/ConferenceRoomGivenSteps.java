package org.fundacionjala.automation.scenario.steps.admin.conferenceRoom;

import org.fundacionjala.automation.framework.pages.admin.conferencerooms.ConferenceRoomsPage;
import org.fundacionjala.automation.framework.pages.admin.home.AdminPage;
import org.fundacionjala.automation.framework.utils.api.managers.RoomAPIManager;

import cucumber.api.java.en.Given;

public class ConferenceRoomGivenSteps {

    @Given("^I have \"([^\"]*)\" room disabled$")
    public void disableRoom(String roomName) throws Throwable {
	new ConferenceRoomsPage().disableRoom(roomName);
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
    
    @Given("^I create an Out of Order on a specific \"([^\"]*)\" room$")
    public void createOutOfOrderOnRoom(String roomName) throws Throwable {
	AdminPage home = new AdminPage();
		home.leftMenu.clickOnIssuesButton()
		.clickOnConferenceRoomsButton()
		.openConfigurationPage(roomName).clickOnOutOfOrder()
		.setTimeBeginUp().setTimeEndUp().clickOnBoxButon()
		.ClickOnClosedForMaintenanceLink()
		.clickOnSaveButton();
    }
}
