package org.fundacionjala.automation.scenario.steps.tablet.editMeeting;

import org.fundacionjala.automation.framework.pages.tablet.scheduler.SchedulerPage;
import org.fundacionjala.automation.framework.utils.common.PropertiesReader;
import org.testng.Assert;

import cucumber.api.java.en.Then;

public class EditMeetingThen {
    
    
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

}
