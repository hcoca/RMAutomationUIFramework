package org.fundacionjala.automation.scenario.steps.admin.conferenceRoom;

import org.fundacionjala.automation.framework.pages.admin.conferencerooms.ConferenceRoomsPage;
import org.fundacionjala.automation.framework.pages.admin.home.AdminPage;

import cucumber.api.java.en.When;

public class ConferenceRoomWhenSteps {

    private ConferenceRoomsPage goToConferenceRoomPage()
	    throws InterruptedException {
	return new AdminPage().refreshPage().leftMenu
		.clickOnConferenceRoomsButton();
    }

    @When("^I enabled \"([^\"]*)\" room$")
    public void enableARoom(String roomName) throws Throwable {
	goToConferenceRoomPage().clickOnTurnOnOffButton(roomName);
    }

    @When("^I disabled \"([^\"]*)\" room$")
    public void disableARoom(String roomName) throws Throwable {
	goToConferenceRoomPage().clickOnTurnOnOffButton(roomName);
    }

    @When("^I go to conference room page$")
    public void i_go_to_conference_room_page() throws Throwable {
	goToConferenceRoomPage();
    }

    @When("^I search rooms with this criteria \"([^\"]*)\"$")
    public void i_search_rooms_with_this_criteria(String roomCriteria)
	    throws Throwable {
	goToConferenceRoomPage().typeOnFilterTextbox(roomCriteria);
    }

    @When("^I select a page size \"([^\"]*)\"$")
    public void i_select_a_page_size(String pageSize) throws Throwable {
	AdminPage home = new AdminPage();
	home.leftMenu.clickOnConferenceRoomsButton()
		.selectPageSize(pageSize);
    }

    @When("^I set the page \"([^\"]*)\"$")
    public void i_set_the_page(String page) throws Throwable {
	AdminPage home = new AdminPage();
	home.leftMenu.clickOnConferenceRoomsButton()
		.setPage(page);
    }
}
