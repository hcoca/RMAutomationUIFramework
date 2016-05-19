package org.fundacionjala.automation.scenario.steps.tablet.editMeeting;

import java.util.List;

import org.fundacionjala.automation.framework.pages.tablet.scheduler.SchedulerPage;
import org.fundacionjala.automation.framework.utils.common.PropertiesReader;
import org.testng.Assert;

import cucumber.api.java.en.Then;

public class EditMeetingThenSteps {
    
    @Then("^Validate that the organizer is not able to modified$")
    public void validateOrganizerIsNotAbleToModified() throws Throwable {
	
        SchedulerPage meeting = new SchedulerPage();
        Assert.assertTrue(
        	meeting.verifyOrganizerTextFieldIsDisable());
        meeting
        	.clickOnRemoveButton()
        	.setPassword(PropertiesReader.getExchangeOrganizerPwd())
        	.clickOkButton();
    }
    
    @Then("^Validate that the attendees has been modified with$")
    public void validateAttendeesModified(List<String> attendees) throws Throwable {
	SchedulerPage schedule = new SchedulerPage();
	Assert.assertTrue(
		schedule.verifyAteendees(attendees));
	schedule
		.clickOnRemoveButton()
		.setPassword(PropertiesReader.getExchangeOrganizerPwd())
		.clickOkButton();
    }


}
