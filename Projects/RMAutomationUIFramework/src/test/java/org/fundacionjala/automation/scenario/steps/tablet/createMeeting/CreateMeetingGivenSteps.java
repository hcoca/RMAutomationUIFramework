package org.fundacionjala.automation.scenario.steps.tablet.createMeeting;

import java.util.ArrayList;
import java.util.List;

import org.fundacionjala.automation.framework.pages.tablet.settings.ConnectionPage;
import org.fundacionjala.automation.framework.pages.tablet.settings.NavigationPage;
import org.fundacionjala.automation.framework.utils.api.managers.MeetingAPIManager;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Meeting;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.PropertiesReader;
import org.fundacionjala.automation.framework.utils.common.RMGenerator;

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
    
    @Given("^I have a meeting with this subject \"([^\"]*)\" on \"([^\"]*)\" room$")
    public void i_have_a_meeting(String subject, String roomName) throws Throwable {
	String roomEmail = roomName + "@" + PropertiesReader.getExchangeDomain();
	String resources = roomName + "@" + PropertiesReader.getExchangeDomain();
	String start = RMGenerator.getIsoTime(0);
	String end = RMGenerator.getIsoTime(1);
	List<String> attendees = new ArrayList<String>();
	attendees.add(PropertiesReader.getExchangeInviteMail());
	Meeting meeting = new Meeting(PropertiesReader.getExchangeOrganizerUser(),
		subject, start, end, roomName, roomEmail, resources,
		attendees);
	
	MeetingAPIManager.postRequest(roomName, meeting);
    }
}
