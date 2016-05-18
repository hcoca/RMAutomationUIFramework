package org.fundacionjala.automation.scenario.steps.tablet.createMeeting;

import org.fundacionjala.automation.framework.pages.tablet.scheduler.SchedulerPage;
import org.fundacionjala.automation.framework.utils.common.PropertiesReader;
import org.testng.Assert;

import cucumber.api.java.en.Then;

public class CreateMeetingThenSteps {

    @Then("^validate that \"([^\"]*)\" has been created on timeline$")
    public void validate_that_has_been_created_on_timeline(String subject) throws Throwable {
        SchedulerPage scheduler = new SchedulerPage();
        Assert.assertTrue(
        scheduler
        .isMeetingPresentOnTimeLine(subject));
        
        scheduler
        .clickOnMeeting(subject)
        .clickOnRemoveButton()
        .setPassword(PropertiesReader.getExchangeOrganizerPwd())
        .clickOkButton();
    }
}
