package org.fundacionjala.automation.scenario.steps.tablet.createMeeting;

import org.fundacionjala.automation.framework.pages.tablet.settings.ConnectionPage;
import org.fundacionjala.automation.framework.pages.tablet.settings.NavigationPage;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.PropertiesReader;

import cucumber.api.java.en.Given;

public class CreateMeetingGivenSteps {

    @Given("^I am on Home Page of \"([^\"]*)\" room$")
    public void i_am_on_Home_Page_of_room(String roomName) throws Throwable {
	BrowserManager.openBrowser();
	ConnectionPage connection = new ConnectionPage();

	NavigationPage navigation = connection
		.setUpServiceURL(PropertiesReader.getServiceURL())
		.clickOnSaveButton()
		.clickOnNavigationButton();

	navigation.clickOnRoomToggleButton()
		.selectConferenceRoom(roomName)
		.clickOnSaveButton()
		.topMenu
		.clickOnHomeButton();
    }
}
