package org.fundacionjala.automation.scenario.steps.tablet.editMeeting;

import java.util.List;

import org.fundacionjala.automation.framework.pages.tablet.scheduler.CredentialsPage;
import org.fundacionjala.automation.framework.pages.tablet.scheduler.SchedulerPage;
import org.fundacionjala.automation.framework.utils.api.managers.MeetingAPIManager;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Meeting;
import org.fundacionjala.automation.framework.utils.common.DatabaseConnection;
import org.fundacionjala.automation.framework.utils.common.PropertiesReader;
import org.fundacionjala.automation.framework.utils.common.Utility;
import org.testng.Assert;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.mongodb.DBCollection;

import cucumber.api.java.After;
import cucumber.api.java.en.Then;

public class EditMeetingThenSteps {
    String roomNameToDelete;
    String meetingName;
    String meetingName2;

    @Then("^Validate that in the meeting \"([^\"]*)\" of the \"([^\"]*)\" room the organizer is not able to modified$")
    public void validate_organizer_is_not_able_to_modified(String subject,
	    String roomName) throws Throwable {
	roomNameToDelete = roomName;
	meetingName = subject;
	SchedulerPage meeting = new SchedulerPage();
	Assert.assertTrue(meeting.verifyOrganizerTextFieldIsDisable(),
		"The organizer is not disable");
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
		    .verifyFieldEdited(field, value), "The field " + field
		    + " in the meeting cannot be modified using impersonation");
	}
    }

    @Then("^validate that \"([^\"]*)\" subject has been modified in the \"([^\"]*)\" room$")
    public void validate_that_subject_has_been_modified_in_the_room(
	    String newSubject, String roomName) throws Throwable {
	roomNameToDelete = roomName;
	meetingName = newSubject;
	SchedulerPage scheduler = new SchedulerPage();
	Assert.assertTrue(scheduler.topMenu.clickOnHomeButton()
		.clickOnScheduleButton().displayAllDayOnTimeline()
		.verifySubjectModified(newSubject),
		"The subject was not modified");
    }

    @Then("^validate that \"([^\"]*)\" meeting in the \"([^\"]*)\" room has been modified with schedule from \"([^\"]*)\" to \"([^\"]*)\"$")
    public void validateMeetingModifiedWithSchedule(String subject,
	    String roomName, String startTime, String endTime) throws Throwable {
	roomNameToDelete = roomName;
	meetingName = subject;
	SchedulerPage scheduler = new SchedulerPage();
	Assert.assertTrue(
		scheduler.topMenu.clickOnHomeButton().clickOnScheduleButton()
			.displayAllDayOnTimeline()
			.clickOnMeetingButton(subject)
			.verifyTheSchedule(startTime, endTime),
		"The schedule was not modified");
    }

    @Then("^validate that the body \"([^\"]*)\" has been modified in \"([^\"]*)\" in the \"([^\"]*)\" room$")
    public void validateBodyModified(String body, String subject,
	    String roomName) throws Throwable {

	roomNameToDelete = roomName;
	meetingName = subject;
	SchedulerPage scheduler = new SchedulerPage();
	Assert.assertTrue(scheduler.topMenu.clickOnHomeButton()
		.clickOnScheduleButton().displayAllDayOnTimeline()
		.clickOnMeetingButton(subject).verifyTheBody(body),
		"The body was not modified");
    }

    @Then("^validate that an error message is displayed in the \"([^\"]*)\" and \"([^\"]*)\" meetings of the \"([^\"]*)\" room$")
    public void validateErrorMessageInMmeetings(String firstSubject,
	    String secondSubject, String roomName) throws Throwable {
	roomNameToDelete = roomName;
	meetingName = firstSubject;
	meetingName2 = secondSubject;
	CredentialsPage credential = new CredentialsPage();
	Assert.assertTrue(credential
		.verifyErrorMessageOfMeetingConflictIsDisplayed());
	credential.clickCancelButton();
    }

    @Then("^validate that an error message is displayed in the startTime field of the meeting \"([^\"]*)\" in the \"([^\"]*)\" room$")
    public void validateErrorMessageInStartTimeField(String subject,
	    String roomName) throws Throwable {
	roomNameToDelete = roomName;
	meetingName = subject;
	SchedulerPage scheduler = new SchedulerPage();
	Assert.assertFalse(scheduler.validateErrorMessageIsDIsplayed(),
		"The error message was not displayed");
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
	schedule.clickOnRemoveButton()
		.setPassword(PropertiesReader.getExchangeOrganizerPwd())
		.clickOkButton();
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
		"The field start time must be " + startTimeExpected
			+ "in the meeting");
	Assert.assertTrue(
		scheduler.verifyFieldEdited("endTime", endTimeExpected),
		"The field end time must be " + endTimeExpected
			+ "in the meeting");
    }

    @Then("^Validate that in the \"([^\"]*)\" the schedule end \"([^\"]*)\"  with start time \"([^\"]*)\" on \"([^\"]*)\" were \"([^\"]*)\" \"([^\"]*)\" hour$")
    public void validate_that_the_schedule_end(String roomName, String endTime,
	    String startTime, String meetingName, String status, int hours)
	    throws Throwable {
	roomNameToDelete = roomName;
	this.meetingName = meetingName;
	SchedulerPage scheduler = new SchedulerPage();
	String startTimeExpected, endTimeExpected;
	scheduler.topMenu.clickOnHomeButton().clickOnScheduleButton();
	endTimeExpected = Utility.getnewEndTimeforEndPulledByHour(startTime,
		endTime, status, hours);// 11
	startTimeExpected = startTime;
	Assert.assertTrue(scheduler.clickOnMeetingButton(meetingName)
		.verifyFieldEdited("endTime", endTimeExpected),
		"The field end time must be " + endTimeExpected
			+ " in the meeting");
	Assert.assertTrue(
		scheduler.verifyFieldEdited("startTime", startTimeExpected),
		"The field start time must be " + startTimeExpected
			+ " in the meeting");
    }

    @Then("^Validate that in the \"([^\"]*)\" the schedule start \"([^\"]*)\" with end time \"([^\"]*)\" on \"([^\"]*)\" were \"([^\"]*)\" \"([^\"]*)\" hour$")
    public void validate_that_the_schedule_start(String roomName,
	    String startTime, String endTime, String meetingName,
	    String status, int hours) throws Throwable {
	roomNameToDelete = roomName;
	this.meetingName = meetingName;
	SchedulerPage scheduler = new SchedulerPage();
	String startTimeExpected, endTimeExpected;
	scheduler.topMenu.clickOnHomeButton().clickOnScheduleButton();
	startTimeExpected = Utility.getnewStartTimeforStartPulledByHour(
		startTime, endTime, status, hours);

	endTimeExpected = endTime;
	Assert.assertTrue(scheduler.clickOnMeetingButton(meetingName)
		.verifyFieldEdited("startTime", startTimeExpected),
		"The field start time must be " + startTimeExpected
			+ " in the meeting cannot be modified");
	Assert.assertTrue(
		scheduler.verifyFieldEdited("endTime", endTimeExpected),
		"The field end time must be " + endTimeExpected
			+ " in the meeting cannot be modified");

    }

    @Then("^validate that an error message is displayed with conflict of time interval in the \"([^\"]*)\" of the \"([^\"]*)\" room$")
    public void validate_error_message_with_conflict_of_time_interval(
	    String subject, String roomName) throws Throwable {
	roomNameToDelete = roomName;
	meetingName = subject;
	CredentialsPage credential = new CredentialsPage();
	Assert.assertTrue(
		credential.verifyErrorMessageOfMeetingConflictIsDisplayed(),
		"The error message was not displayed");
	credential.clickCancelButton();
    }

    @Then("^Validate that there two meeting with subject \"([^\"]*)\" in the \"([^\"]*)\" room$")
    public void validate_that_there_two_meeting_with_subject_in_the_room(
	    String subject, String roomName) throws Throwable {
	roomNameToDelete = roomName;
	meetingName = subject;
	meetingName2 = subject;
	SchedulerPage scheduler = new SchedulerPage();
	Assert.assertTrue(scheduler.topMenu.clickOnHomeButton()
		.clickOnScheduleButton().displayAllDayOnTimeline()
		.verifyTwoMeetingWithSameSubject(subject),
		"The body was not modified");
    }

    @After("@DeleteTwoMeetings")
    public void deleteTwoMeetings() throws UnirestException {
	deleteMeetingByAPI(roomNameToDelete, meetingName);
	deleteMeetingByAPI(roomNameToDelete, meetingName2);
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

    @After("@DeleteOutOfOrders")
    public void deleteOutOfOrders() {

	DatabaseConnection connection = new DatabaseConnection();
	connection.switchCollection("outoforders");
	DBCollection myCollection = connection.getCollection();
	myCollection.drop();
    }

}
