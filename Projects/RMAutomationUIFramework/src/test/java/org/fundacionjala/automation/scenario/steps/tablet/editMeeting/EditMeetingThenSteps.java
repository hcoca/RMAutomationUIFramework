package org.fundacionjala.automation.scenario.steps.tablet.editMeeting;

import java.util.List;

import org.fundacionjala.automation.framework.pages.tablet.scheduler.SchedulerPage;
import org.fundacionjala.automation.framework.utils.common.PropertiesReader;
import org.testng.Assert;

import cucumber.api.java.After;
import cucumber.api.java.en.Then;

public class EditMeetingThenSteps {

    @Then("^Validate that the organizer is not able to modified$")
    public void validateOrganizerIsNotAbleToModified() throws Throwable {

	SchedulerPage meeting = new SchedulerPage();
	Assert.assertTrue(meeting.verifyOrganizerTextFieldIsDisable());
	meeting.clickOnRemoveButton()
		.setPassword(PropertiesReader.getExchangeOrganizerPwd())
		.clickOkButton();
    }

    @Then("^Validate that the attendees has been modified with$")
    public void validateAttendeesModified(List<String> attendees)
	    throws Throwable {

	SchedulerPage schedule = new SchedulerPage();
	Assert.assertTrue(schedule.verifyAteendees(attendees));
	schedule.clickOnRemoveButton()
		.setPassword(PropertiesReader.getExchangeOrganizerPwd())
		.clickOkButton();
    }

    @Then("^Validate that the \"([^\"]*)\" has been modified with the value \"([^\"]*)\" of the \"([^\"]*)\"$")
    public void validate_that_the_has_been_modified_with_the_value_of_the(
	    String field, String value, String subject) throws Throwable {

	SchedulerPage scheduler = new SchedulerPage();
	scheduler.topMenu.clickOnHomeButton().clickOnScheduleButton();
	if (field.equalsIgnoreCase("subject")) {
	    Assert.assertTrue(scheduler.isMeetingPresentOnTimeLine(value),
		    "The meeting subject cannot be modified using impersonation");
	} else {
	    Assert.assertTrue(scheduler.clickOnMeetingButton(subject)
		    .verifyFieldEdited(field, value), "The field" + field
		    + "in the meeting cannot be modified using impersonation");
	}

    }

    @Then("^Validate that the schedule start and end time on \"([^\"]*)\" were \"([^\"]*)\" (\\d+) hour$")
    public void validate_that_the_schedule_start_and_end_time_were_hour(
	    String meetingName, String status, int arg2) throws Throwable {
	// Write code here that turns the phrase above into concrete actions
	SchedulerPage scheduler = new SchedulerPage();
	scheduler.topMenu.clickOnHomeButton().clickOnScheduleButton();

	Assert.assertTrue(scheduler.clickOnMeetingButton(meetingName)
		.verifyFieldEdited("startTime", "09:00:00.000"),
		"The field start time" + "in the meeting cannot be modified");
	Assert.assertTrue(
		scheduler.verifyFieldEdited("endTime", "10:00:00.000"),
		"The field end time" + "in the meeting cannot be modified");
    }

    @After("@DeleteMeeting")
    public void deleteMeeting() {
	SchedulerPage schedule = new SchedulerPage();
	schedule.clickOnRemoveButton()
		.setPassword(PropertiesReader.getExchangeOrganizerPwd())
		.clickOkButton();
    }

    @After("@DeleteMeetingOutOfOrder")
    public void deleteMeetingOutOfOrder() {
	SchedulerPage schedule = new SchedulerPage();
	schedule.clickOnMeetingButton("Meeting QDAEV06").clickOnRemoveButton()
		.setPassword(PropertiesReader.getExchangeOrganizerPwd())
		.clickOkButton();
    }

}
