package org.fundacionjala.automation.scenario.steps.tablet.editMeeting;

import java.util.List;

import org.fundacionjala.automation.framework.pages.tablet.scheduler.SchedulerPage;
import org.fundacionjala.automation.framework.utils.api.managers.MeetingAPIManager;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Meeting;
import org.fundacionjala.automation.framework.utils.common.PropertiesReader;
import org.fundacionjala.automation.framework.utils.common.Utility;
import org.testng.Assert;

import com.mashape.unirest.http.exceptions.UnirestException;

import cucumber.api.java.After;
import cucumber.api.java.en.Then;

public class EditMeetingThenSteps {
    String roomNameToDelete;
    String meetingName;

    @Then("^Validate that in the meeting \"([^\"]*)\" of the \"([^\"]*)\" room the organizer is not able to modified$")
    public void validate_organizer_is_not_able_to_modified(String subject,
	    String roomName) throws Throwable {
	roomNameToDelete = roomName;
	meetingName = subject;
	SchedulerPage meeting = new SchedulerPage();
	Assert.assertTrue(meeting.verifyOrganizerTextFieldIsDisable());
    }

    @Then("^Validate that in the meeting \"([^\"]*)\" of the \"([^\"]*)\" room the attendees has been modified with$")
    public void validate_that__attendees_has_been_modified_with(String subject,
	    String roomName, List<String> attendees) throws Throwable {
	roomNameToDelete = roomName;
	meetingName = subject;
	SchedulerPage schedule = new SchedulerPage();
	Assert.assertTrue(schedule.topMenu.clickOnHomeButton()
		.clickOnScheduleButton().displayAllDayOnTimeline()
		.clickOnMeetingButton(subject).verifyAteendees(attendees));
    }

    @Then("^Validate that the \"([^\"]*)\" has been modified with the value \"([^\"]*)\" of the \"([^\"]*)\" in the \"([^\"]*)\" room$")
    public void validateModified(String field, String value, String subject,
	    String roomName) throws Throwable {
	roomNameToDelete = roomName;
	SchedulerPage scheduler = new SchedulerPage();
	scheduler.topMenu.clickOnHomeButton().clickOnScheduleButton()
		.displayAllDayOnTimeline();
	if (field.equalsIgnoreCase("subject")) {
	    boolean actualResult = false;
	    actualResult = scheduler.isMeetingPresentOnTimeLine(value);
	    meetingName = actualResult ? value : subject;
	    Assert.assertTrue(actualResult,
		    "The meeting subject cannot be modified using impersonation");
	} else {
	    meetingName = subject;
	    Assert.assertTrue(scheduler.clickOnMeetingButton(subject)
		    .verifyFieldEdited(field, value), "The field" + field
		    + "in the meeting cannot be modified using impersonation");
	}
    }

    @Then("^validate that \"([^\"]*)\" subject has been modified$")
    public void validate_that_subject_has_been_modified(String newSubject)
	    throws Throwable {
	meetingName = newSubject;
	SchedulerPage scheduler = new SchedulerPage();
	Assert.assertTrue(scheduler.topMenu.clickOnHomeButton()
		.clickOnScheduleButton().displayAllDayOnTimeline()
		.verifySubjectModified(newSubject),
		"The subject was not modified");
    }

    @Then("^validate that \"([^\"]*)\" meeting has been modified with schedule from \"([^\"]*)\" to \"([^\"]*)\"$")
    public void validate_that_meeting_has_been_modified_with_schedule_from_to(
	    String subject, String startTime, String endTime) throws Throwable {
	meetingName = subject;
	SchedulerPage scheduler = new SchedulerPage();
	Assert.assertTrue(scheduler.topMenu.clickOnHomeButton()
		.clickOnScheduleButton().clickOnMeetingButton(subject)
		.displayAllDayOnTimeline()
		.verifyTheSchedule(startTime, endTime),
		"The schedule was not modified");
    }

    @Then("^validate that the body \"([^\"]*)\" has been modified in \"([^\"]*)\"$")
    public void validate_body_has_been_modified(String bodyModified,
	    String subject) throws Throwable {
	meetingName = subject;
	SchedulerPage scheduler = new SchedulerPage();
	Assert.assertTrue(scheduler.topMenu.clickOnHomeButton()
		.clickOnScheduleButton().displayAllDayOnTimeline()
		.clickOnMeetingButton(subject).verifyTheBody(bodyModified),
		"The schedule was not modified");
    }

    @Then("^validate that an error message is displayed in the startTime field of the meeting \"([^\"]*)\"$")
    public void validate_error_message_in_startTime_field(String subject)
	    throws Throwable {
	meetingName = subject;
	SchedulerPage scheduler = new SchedulerPage();
	Assert.assertFalse(scheduler.validateErrorMessageIsDIsplayed(),
		"The error message was not displayed");
	scheduler.clickRemoveButton()
		.setPassword(PropertiesReader.getExchangeOrganizerPwd())
		.clickOnOkButton();
    }

    @Then("^validate that information meeting is displayed subject \"([^\"]*)\"$")
    public void validate_that_information_meeting_is_displayed_subject(
	    String subject) throws Throwable {
	meetingName = subject;
	SchedulerPage schedule = new SchedulerPage();
	Assert.assertTrue(schedule.isTitlePresent(subject),
		"The subject is not present");
    }

    @Then("^schedule from \"([^\"]*)\" to \"([^\"]*)\"$")
    public void schedule_from_to(String startTime, String endTime)
	    throws Throwable {
	SchedulerPage schedule = new SchedulerPage();
	Assert.assertTrue(schedule.verifyTheSchedule(startTime, endTime),
		"The schedule is not present");
    }

    @Then("^body is \"([^\"]*)\"$")
    public void body_is(String body) throws Throwable {

	SchedulerPage schedule = new SchedulerPage();
	Assert.assertTrue(schedule.verifyTheBody(body),
		"The body is not present");
    }

    @Then("^attendees:$")
    public void attendees(List<String> attendees) throws Throwable {

	SchedulerPage schedule = new SchedulerPage();
	Assert.assertTrue(schedule.verifyAteendees(attendees),
		"The attendees is not present");
    }

    @Then("^Validate that in the \"([^\"]*)\" the schedule start \"([^\"]*)\" and end time \"([^\"]*)\" on \"([^\"]*)\" were \"([^\"]*)\" \"([^\"]*)\" hour$")
    public void validate_that_the_schedule_start_and_end_time_were_hour(
	    String roomName, String startTime, String endTime,
	    String meetingName, String status, int hours) throws Throwable {
	roomNameToDelete = roomName;
	this.meetingName = meetingName;
	SchedulerPage scheduler = new SchedulerPage();
	String startTimeExpected, endTimeExpected;
	scheduler.topMenu.clickOnHomeButton().clickOnScheduleButton();
	startTimeExpected = Utility.getnewStartTimeByHour(startTime, endTime,
		status, hours);
	endTimeExpected = Utility.getnewEndTimeByHour(startTime, endTime,
		status, hours);

	Assert.assertTrue(scheduler.clickOnMeetingButton(meetingName)
		.verifyFieldEdited("startTime", startTimeExpected),
		"The field start time in the meeting cannot be modified");
	Assert.assertTrue(
		scheduler.verifyFieldEdited("endTime", endTimeExpected),
		"The field end time in the meeting cannot be modified");
    }

    @Then("^Validate that in the \"([^\"]*)\" the schedule end \"([^\"]*)\"  with start time \"([^\"]*)\" on \"([^\"]*)\" were \"([^\"]*)\" \"([^\"]*)\" hour$")
    public void validate_that_the_schedule_end(String roomName, String endTime,
	    String startTime, String meetingName, String status, int hours)
	    throws Throwable {
	roomNameToDelete = roomName;
	this.meetingName = meetingName;
	SchedulerPage scheduler = new SchedulerPage();
	String endTimeExpected;
	scheduler.topMenu.clickOnHomeButton().clickOnScheduleButton();
	endTimeExpected = Utility.getnewEndTimeByHour(startTime, endTime,
		status, hours);

	Assert.assertTrue(
		scheduler.verifyFieldEdited("endTime", endTimeExpected),
		"The field end time in the meeting cannot be modified");
    }

    @Then("^Validate that in the \"([^\"]*)\" the schedule start \"([^\"]*)\" with end time \"([^\"]*)\" on \"([^\"]*)\" were \"([^\"]*)\" \"([^\"]*)\" hour$")
    public void validate_that_the_schedule_start(String roomName,
	    String startTime, String endTime, String meetingName,
	    String status, int hours) throws Throwable {
	roomNameToDelete = roomName;
	this.meetingName = meetingName;
	SchedulerPage scheduler = new SchedulerPage();
	String startTimeExpected;
	scheduler.topMenu.clickOnHomeButton().clickOnScheduleButton();
	startTimeExpected = Utility.getnewStartTimeByHour(startTime, endTime,
		status, hours);

	Assert.assertTrue(scheduler.clickOnMeetingButton(meetingName)
		.verifyFieldEdited("startTime", startTimeExpected),
		"The field start time in the meeting cannot be modified");

    }

    @After("@DeleteTwoMeetings")
    public void deleteTwoMeetings() throws UnirestException {
	deleteMeetingByAPI("Room111", "first meeting");
	deleteMeetingByAPI("Room111", "second meeting");
    }

    @After("@DeleteMeeting")
    public void deleteMeeting() throws UnirestException {
	deleteMeetingByAPI(roomNameToDelete, meetingName);
    }

    @After("@DeleteMeetingOutOfOrder")
    public void deleteMeetingOutOfOrder() {
	SchedulerPage schedule = new SchedulerPage();
	schedule.clickOnMeetingButton(meetingName).clickOnRemoveButton()
		.setPassword(PropertiesReader.getExchangeOrganizerPwd())
		.clickOkButton();
    }

    private void deleteMeetingByAPI(String roomName, String subject)
	    throws UnirestException {

	Meeting meetingToDelete = MeetingAPIManager.getMeetingBySubject(
		roomName, subject);
	MeetingAPIManager.deleteRequest(roomName, meetingToDelete);
    }

}
