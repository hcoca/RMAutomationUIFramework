package org.fundacionjala.automation.scenario.steps.tablet.editMeeting;

import java.util.ArrayList;
import java.util.List;

import org.fundacionjala.automation.framework.pages.tablet.home.HomePage;
import org.fundacionjala.automation.framework.pages.tablet.scheduler.CredentialsPage;
import org.fundacionjala.automation.framework.pages.tablet.scheduler.SchedulerPage;
import org.fundacionjala.automation.framework.pages.tablet.settings.ConnectionPage;
import org.fundacionjala.automation.framework.pages.tablet.settings.NavigationPage;
import org.fundacionjala.automation.framework.utils.api.managers.MeetingAPIManager;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Meeting;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.PropertiesReader;
import org.fundacionjala.automation.framework.utils.common.RMGenerator;

import cucumber.api.java.en.Given;

public class EditMeetingGivenSteps {

	@Given("^I had a created meeting with \"([^\"]*)\" organizer, with \"([^\"]*)\" subject in the \"([^\"]*)\" room$")
	public void IHadAMeetingCreated(String organizer, String subject,
			String room) throws Throwable {

		String startDate = RMGenerator.getIsoTime(0);
		String endDate = RMGenerator.getIsoTime(1);
		String roomEmail = room + "@" + PropertiesReader.getExchangeDomain();
		List<String> attendees = new ArrayList<String>();
		attendees.add(PropertiesReader.getExchangeInviteMail());

		Meeting meeting = new Meeting(organizer, subject, startDate, endDate,
				room, roomEmail, roomEmail, attendees);
		MeetingAPIManager.postRequest(room, meeting);
	}

	@Given("^I had a created meeting in the \"([^\"]*)\" room, with \"([^\"]*)\" subject and attendees:$")
	public void i_had_a_created_meeting_in_the_room_with_subject_and_attendees(
			String roomName, String subject, List<String> attendees)
			throws Throwable {
		String startDate = RMGenerator.getIsoTime(0);
		String endDate = RMGenerator.getIsoTime(1);
		String roomEmail = roomName + "@"
				+ PropertiesReader.getExchangeDomain();

		Meeting meeting = new Meeting(
				PropertiesReader.getExchangeOrganizerUser(), subject,
				startDate, endDate, roomName, roomEmail, roomEmail, attendees);
		MeetingAPIManager.postRequest(roomName, meeting);

	}

	@Given("^I have a created in the \"([^\"]*)\" room with subject \"([^\"]*)\"$")
	public void createdMeetingWithRoomSubject(String roomName, String subject)
			throws Throwable {
		HomePage home = new HomePage();
		BrowserManager.openBrowser();
		ConnectionPage connection = new ConnectionPage();

		NavigationPage navigation = connection
				.setUpServiceURL(PropertiesReader.getServiceURL())
				.clickOnSaveButton().clickOnNavigationButton();

		home = navigation.clickOnRoomToggleButton()
				.selectConferenceRoom(roomName)
				.clickOnSaveButton()
				.topMenu
				.clickOnHomeButton();
		
		home.clickOnScheduleButton();

		SchedulerPage scheduler = new SchedulerPage();
		scheduler.setOrganizer(PropertiesReader.getExchangeOrganizerUser())
				.setSubject(subject);
	}

	@Given("^the schedule with start time: \"([^\"]*)\" end time \"([^\"]*)\"$")
	public void the_schedule_with_start_time_end_time(String startTime,
			String EndTime) throws Throwable {
		
		SchedulerPage scheduler = new SchedulerPage();
		scheduler
			.setStartTime(startTime)
			.setEndTime(EndTime);

	}

	@Given("^attendees$")
	public void attendees(List<String> attendees) throws Throwable {
		
		SchedulerPage scheduler = new SchedulerPage();
		for (String attendee : attendees) {
			scheduler.setAttende(attendee);
			
		}
	}
	
	@Given("^body \"([^\"]*)\"$")
	public void body(String body) throws Throwable {
	    
		SchedulerPage scheduler = new SchedulerPage();
		scheduler.setBody(body);
	}
	
	@Given("^impersonate the meeting with the user \"([^\"]*)\" and password \"([^\"]*)\"$")
	public void impersonate_the_meeting_with_the_user_and_password(String user, String pass) throws Throwable {
		
		SchedulerPage scheduler = new SchedulerPage();
		CredentialsPage credentials = scheduler
   				.clickOnCreateButton();
   		
   		scheduler = credentials
   				.clickCreateAsCheckBox()
   				.setUserName(user)
   				.setPassword(pass)
   				.clickOkButton();
	}


}
