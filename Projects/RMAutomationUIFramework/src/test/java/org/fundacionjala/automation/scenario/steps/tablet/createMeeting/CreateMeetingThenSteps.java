package org.fundacionjala.automation.scenario.steps.tablet.createMeeting;

import org.fundacionjala.automation.framework.pages.tablet.scheduler.SchedulerPage;
import org.fundacionjala.automation.framework.utils.common.PropertiesReader;
import org.junit.Assert;
import cucumber.api.java.en.Then;

public class CreateMeetingThenSteps {

    @Then("^validate that \"([^\"]*)\" has been created on timeline$")
    public void validate_that_has_been_created_on_timeline(String subject) throws Throwable {
        SchedulerPage scheduler = new SchedulerPage();
        Assert.assertTrue(
            scheduler
            .displayAllDayOnTimeline()
            .isMeetingPresentOnTimeLine(subject));
        
        scheduler
            .displayAllDayOnTimeline()
            .clickOnMeeting(subject)
            .clickOnRemoveButton()
            .setPassword(PropertiesReader.getExchangeOrganizerPwd())
            .clickOkButton();
    }

    @Then("^validate that \"([^\"]*)\" has been removed and it is not on timeline$")
    public void validate_that_has_been_removed_and_it_is_not_on_timeline(String meetingName) throws Throwable {
	SchedulerPage scheduler = new SchedulerPage();
        Assert.assertFalse(
        	scheduler
        	.isMeetingPresentOnTimeLine(meetingName));
    }
    
    @Then("^validate that all day is displayed on timeline$")
    public void validate_that_all_day_is_displayed_on_timeline() throws Throwable {
	SchedulerPage scheduler = new SchedulerPage();
        Assert.assertTrue(
        	scheduler
        	.verifyIfTimelineDisplayAllDay());
    }
}
