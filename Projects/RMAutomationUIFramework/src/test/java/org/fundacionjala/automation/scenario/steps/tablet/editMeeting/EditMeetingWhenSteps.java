package org.fundacionjala.automation.scenario.steps.tablet.editMeeting;

import java.util.List;

import org.fundacionjala.automation.framework.pages.tablet.scheduler.SchedulerPage;
import org.fundacionjala.automation.framework.pages.tablet.settings.ConnectionPage;
import org.fundacionjala.automation.framework.pages.tablet.settings.NavigationPage;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.PropertiesReader;

import cucumber.api.java.en.When;

public class EditMeetingWhenSteps {

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
		.displayAllDayOnTimeline()
		.clickOnMeetingButton(subject);
        
    }
    
    @When("^I modified the meeting \"([^\"]*)\" in the \"([^\"]*)\" room  adding the attendees$")
    public void addingAttendees(String meetingName, String roomName, List<String> attendees) throws Throwable {
	BrowserManager.openBrowser();
	ConnectionPage connection = new ConnectionPage();
	SchedulerPage schedule = new SchedulerPage();

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
		.displayAllDayOnTimeline()
		.clickOnMeetingButton(meetingName);
	
	for (String attendeName : attendees) {
	    schedule
	    	.setAttende(attendeName);
	}
		
    }
   
    @When("^Save the changes$")
    public void save_the_changes() throws Throwable {
	SchedulerPage schedule = new SchedulerPage();
	schedule
		.clickUpdateButton()
		.setPassword(PropertiesReader.getExchangeOrganizerPwd())
		.clickOkButton();
    }
}
