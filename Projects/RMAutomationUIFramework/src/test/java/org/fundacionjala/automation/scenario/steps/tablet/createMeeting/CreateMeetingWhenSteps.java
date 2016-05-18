package org.fundacionjala.automation.scenario.steps.tablet.createMeeting;

import org.fundacionjala.automation.framework.pages.tablet.home.HomePage;
import org.fundacionjala.automation.framework.pages.tablet.scheduler.SchedulerPage;
import org.fundacionjala.automation.framework.utils.common.PropertiesReader;

import cucumber.api.java.en.When;

public class CreateMeetingWhenSteps {

    @When("^I create a meeting as \"([^\"]*)\" for this time with \"([^\"]*)\" subject, \"([^\"]*)\" attendee and \"([^\"]*)\" body$")
    public void i_create_a_meeting(String organizer, String subject, String attendee, String body) throws Throwable {
        
	HomePage home = new HomePage();
        	home
        	.clickOnScheduleButton();
        	
        SchedulerPage scheduler = new SchedulerPage();
        	scheduler
        	.setOrganizer(organizer)
        	.setSubject(subject)
        	.setAttende(attendee)
        	.setBody(body)
        	.clickOnCreateButton()
        	.setUserName(PropertiesReader.getExchangeOrganizerUser())
        	.setPassword(PropertiesReader.getExchangeOrganizerPwd())
        	.clickOkButton();
    }
}
