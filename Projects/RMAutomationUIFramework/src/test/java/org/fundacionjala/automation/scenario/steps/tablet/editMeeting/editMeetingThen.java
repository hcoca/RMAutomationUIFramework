package org.fundacionjala.automation.scenario.steps.tablet.editMeeting;

import org.fundacionjala.automation.framework.pages.tablet.scheduler.SchedulerPage;
import org.testng.Assert;

import cucumber.api.java.en.Then;

public class editMeetingThen {
    
    
    @Then("^Validate that the organizer is not able to modified$")
    public void validate_that_the_organizer_is_not_able_to_modified() throws Throwable {
        SchedulerPage meeting = new SchedulerPage();
        Assert.assertTrue(
        meeting.verifyOrganizerTextFieldIsDisable());
    }

}
