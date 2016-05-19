package org.fundacionjala.automation.scenario.steps.tablet.home;

import org.fundacionjala.automation.framework.pages.tablet.home.HomePage;
import org.fundacionjala.automation.framework.utils.common.PropertiesReader;

import cucumber.api.java.en.When;

public class HomeWhenSteps {
    
    @When("^I create a meeting with \"([^\"]*)\" subject and \"([^\"]*)\" as organizer$")
    public void addSimpleMeeting(String subject, String organizer)
	    throws Throwable {
	HomePage homePage = new HomePage();

	homePage.clickOnScheduleButton()
		.setOrganizer(organizer)
		.setSubject(subject)
		.clickOnCreateButton()
		.setUserName(organizer)
		.setPassword(PropertiesReader.getExchangeOrganizerPwd())
		.clickOkButton();
    }
    
    @When("^I configure \"([^\"]*)\" as tablet room$")
    public void changeRoom(String roomName) throws Throwable {
	HomePage homePage = new HomePage();
	
	homePage.clickOnSettinsButton()
		.clickOnNavigationButton()
		.clickOnRoomToggleButton()
		.selectConferenceRoom(roomName)
		.clickOnSaveButton();
    }
    
    @When("^I update \"([^\"]*)\" meeting with new subject \"([^\"]*)\"$")
    public void updateMeetingSubject(String subject, String newSubject) throws Throwable {
	HomePage homePage = new HomePage();
	
	homePage.clickOnScheduleButton()
        	.displayAllDayOnTimeline()
        	.clickOnMeeting(subject)
        	.setSubject(newSubject)
        	.clickUpdateButton()
        	.setPassword(PropertiesReader.getExchangeOrganizerPwd())
        	.clickOkButton();
    }
}
