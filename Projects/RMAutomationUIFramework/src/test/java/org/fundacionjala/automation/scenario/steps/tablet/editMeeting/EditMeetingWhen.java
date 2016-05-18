package org.fundacionjala.automation.scenario.steps.tablet.editMeeting;

import org.fundacionjala.automation.framework.pages.tablet.settings.ConnectionPage;
import org.fundacionjala.automation.framework.pages.tablet.settings.NavigationPage;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.PropertiesReader;

import cucumber.api.java.en.When;

public class EditMeetingWhen {

    @When("^I display the meeting \"([^\"]*)\" in the \"([^\"]*)\" room$")
    public void displayMeeting(String subject, String roomName) throws Throwable {
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
		.clickOnHomeButton()
		.clickOnScheduleButton()
		.clickOnMeetingButton(subject);
        
    }
}
