package org.fundacionjala.automation.scenario.steps.tablet.editMeeting;

import java.util.List;

import org.fundacionjala.automation.framework.pages.tablet.scheduler.SchedulerPage;
import org.fundacionjala.automation.framework.utils.api.managers.MeetingAPIManager;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Meeting;
import org.fundacionjala.automation.framework.utils.common.PropertiesReader;
import org.testng.Assert;

import com.mashape.unirest.http.exceptions.UnirestException;

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
    
    @Then("^validate that \"([^\"]*)\" subject has been modified$")
    public void validate_that_subject_has_been_modified(String newSubject) throws Throwable {

	SchedulerPage scheduler = new SchedulerPage();
	Assert.assertTrue(
        	scheduler.topMenu
        		.clickOnHomeButton()
        		.clickOnScheduleButton()
        		.displayAllDayOnTimeline()
        		.verifySubjectModified(newSubject),
        		"The subject was not modified");
    }
    
    @Then("^validate that \"([^\"]*)\" meeting has been modified with schedule from \"([^\"]*)\" to \"([^\"]*)\"$")
    public void validate_that_meeting_has_been_modified_with_schedule_from_to(String subject, String startTime, String endTime) throws Throwable {
	
	SchedulerPage scheduler = new SchedulerPage();
	Assert.assertTrue(
        	scheduler.topMenu
        		.clickOnHomeButton()
        		.clickOnScheduleButton()
        		.clickOnMeetingButton(subject)
        		.displayAllDayOnTimeline()
        		.verifyTheScheduleModified(startTime, endTime),
        		"The schedule was not modified");
    }


    @Then("^validate that the body \"([^\"]*)\" has been modified in \"([^\"]*)\"$")
    public void validate_body_has_been_modified(String bodyModified, String subject) throws Throwable {
	SchedulerPage scheduler = new SchedulerPage();
	Assert.assertTrue(
        	scheduler.topMenu
        		.clickOnHomeButton()
        		.clickOnScheduleButton()
        		.displayAllDayOnTimeline()
        		.clickOnMeetingButton(subject)
        		.verifyTheBodyModified(bodyModified),
        		"The schedule was not modified");
    }
    
    @Then("^validate that an error message is displayed in the startTime field of the meeting \"([^\"]*)\"$")
    public void validate_error_message_in_startTime_field(String subject) throws Throwable {
	SchedulerPage scheduler = new SchedulerPage();
	Assert.assertFalse(
		scheduler.validateErrorMessageIsDIsplayed(), "The error message was not displayed");
	scheduler.clickRemoveButton()
		.setPassword(PropertiesReader.getExchangeOrganizerPwd())
		.clickOnOkButton();
    }

      
    @After("@DeleteTwoMeetings")
    public void deleteTwoMeetings() throws UnirestException{
	deleteMeetingByAPI("Room111", "first meeting");
	deleteMeetingByAPI("Room111", "second meeting");
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
     private void deleteMeetingByAPI(String roomName, String subject) throws UnirestException{
	 
	 Meeting meetingToDelete = MeetingAPIManager.getMeetingBySubject(
			roomName, subject);
		MeetingAPIManager.deleteRequest(roomName, meetingToDelete);
     }
     

}
