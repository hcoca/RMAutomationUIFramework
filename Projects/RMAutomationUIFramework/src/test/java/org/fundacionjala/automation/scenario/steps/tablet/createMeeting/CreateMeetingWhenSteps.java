package org.fundacionjala.automation.scenario.steps.tablet.createMeeting;

import org.fundacionjala.automation.framework.pages.tablet.home.HomePage;
import org.fundacionjala.automation.framework.pages.tablet.scheduler.SchedulerPage;
import org.fundacionjala.automation.framework.utils.common.PropertiesReader;

import cucumber.api.java.en.When;

public class CreateMeetingWhenSteps {

	@When("^I create a meeting as \"([^\"]*)\" for this time with \"([^\"]*)\" subject, \"([^\"]*)\" attendee and \"([^\"]*)\" body$")
	public void i_create_a_meeting(String organizer, String subject,
			String attendee, String body) throws Throwable {

		HomePage home = new HomePage();
		home.clickOnScheduleButton();

		SchedulerPage scheduler = new SchedulerPage();
		scheduler.setOrganizer(organizer).setSubject(subject)
				.setAttende(attendee).setBody(body).clickOnCreateButton()
				.setUserName(PropertiesReader.getExchangeOrganizerUser())
				.setPassword(PropertiesReader.getExchangeOrganizerPwd())
				.clickOkButton();
	}

	@When("^I create a meeting as \"([^\"]*)\" organizer$")
	public void i_create_a_meeting(String organizer) throws Throwable {
		HomePage home = new HomePage();
		home.clickOnScheduleButton();

		SchedulerPage scheduler = new SchedulerPage();
		scheduler.setOrganizer(organizer);
	}

	@When("^it meeting will be from \"([^\"]*)\" start time to \"([^\"]*)\" end time$")
	public void it_meeting_will_be_from_start_time_to_end_time(String start,
			String end) throws Throwable {
		SchedulerPage scheduler = new SchedulerPage();
		scheduler.setStartTime(start).setEndTime(end);
	}

	@When("^it has \"([^\"]*)\" as subject, \"([^\"]*)\" as description$")
	public void it_has_as_subject_as_description(String subject,
			String description) throws Throwable {
		SchedulerPage scheduler = new SchedulerPage();
		scheduler.setSubject(subject).setBody(description);
	}

	@When("^it has the following attendee \"([^\"]*)\"$")
	public void it_has_the_following_attendee(String attendee) throws Throwable {
		SchedulerPage scheduler = new SchedulerPage();
		scheduler.setAttende(attendee).clickOnCreateButton()
				.setUserName(PropertiesReader.getExchangeOrganizerUser())
				.setPassword(PropertiesReader.getExchangeOrganizerPwd())
				.clickOkButton();
	}

	@When("^I remove \"([^\"]*)\" meeting$")
	public void i_remove_meeting(String meetingName) throws Throwable {
		HomePage home = new HomePage();
		home.clickOnScheduleButton();
		SchedulerPage scheduler = new SchedulerPage();
		scheduler.displayAllDayOnTimeline().clickOnMeeting(meetingName)
				.clickOnRemoveButton()
				.setPassword(PropertiesReader.getExchangeOrganizerPwd())
				.clickOkButton();
	}

	@When("^I reduce timeline for watching all day$")
	public void i_reduce_timeline_for_watching_all_day() throws Throwable {
		HomePage home = new HomePage();
		home.clickOnScheduleButton();
		SchedulerPage scheduler = new SchedulerPage();
		scheduler.displayAllDayOnTimeline();
	}

	@When("^I want to create a meeting without subject$")
	public void i_want_to_create_a_meeting_without_subject() throws Throwable {
		HomePage home = new HomePage();
		home.clickOnScheduleButton();

		SchedulerPage scheduler = new SchedulerPage();
		scheduler.setOrganizer(PropertiesReader.getExchangeOrganizerUser())
				.clickOnCreateButton();
	}

	@When("^I want to create a meeting as \"([^\"]*)\" and this password \"([^\"]*)\"$")
	public void createMeetingWithInvalidCredentials(String organizer,
			String password) throws Throwable {
		HomePage home = new HomePage();
		home.clickOnScheduleButton();

		SchedulerPage scheduler = new SchedulerPage();
		scheduler.setOrganizer(organizer).setSubject(password)
				.clickOnCreateButton().setUserName(organizer)
				.setPassword(password).clickOnOkButton();
	}

	@When("^I want to create a meeting over interval time of other meeting  from \"([^\"]*)\" start time to \"([^\"]*)\" end time with \"([^\"]*)\" subject$")
	public void i_want_to_create_a_meeting_over_interval(String start,
			String end, String subject) throws Throwable {
		HomePage home = new HomePage();
		home.clickOnScheduleButton();

		SchedulerPage scheduler = new SchedulerPage();
		scheduler.setOrganizer(PropertiesReader.getExchangeOrganizerUser())
				.setSubject(PropertiesReader.getExchangeOrganizerPwd())
				.setStartTime(start).setEndTime(end).clickOnCreateButton()
				.setUserName(PropertiesReader.getExchangeOrganizerUser())
				.setPassword(PropertiesReader.getExchangeOrganizerPwd())
				.clickOnOkButton();
	}
	
	@When("^I try to create a meeting for \"([^\"]*)\" date$")
	public void i_try_to_create_a_meeting_for_date(String date) throws Throwable {
		HomePage home = new HomePage();
		home.clickOnScheduleButton();

		SchedulerPage scheduler = new SchedulerPage();
		scheduler.setFromDate(date).setToDate(date);
	}	
}
