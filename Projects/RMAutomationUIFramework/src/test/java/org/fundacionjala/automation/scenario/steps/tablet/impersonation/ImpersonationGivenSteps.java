package org.fundacionjala.automation.scenario.steps.tablet.impersonation;

import org.fundacionjala.automation.framework.pages.tablet.home.HomePage;
import org.fundacionjala.automation.framework.pages.tablet.scheduler.CredentialsPage;
import org.fundacionjala.automation.framework.pages.tablet.scheduler.SchedulerPage;
import org.fundacionjala.automation.framework.pages.tablet.settings.ConnectionPage;
import org.fundacionjala.automation.framework.pages.tablet.settings.NavigationPage;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.PropertiesReader;

import cucumber.api.java.en.Given;

public class ImpersonationGivenSteps {
    	@Given("^I schedule a new meeting with \"([^\"]*)\" subject$")
   	public void createMeetingUsing(String subject) throws Throwable {
    	BrowserManager.openBrowser();
    	ConnectionPage connection = new ConnectionPage();

		NavigationPage navigation = connection
				.setUpServiceURL(PropertiesReader.getServiceURL())
				.clickOnSaveButton()
				.clickOnNavigationButton();

		HomePage home = navigation
				.clickOnRoomToggleButton()
				.selectConferenceRoom(PropertiesReader.getConferenceRoom())
				.clickOnSaveButton()
				.topMenu
				.clickOnHomeButton();

		SchedulerPage scheduler = home
				.clickOnScheduleButton()
				.setOrganizer(PropertiesReader.getExchangeOrganizerUser())
				.setSubject(subject)
				.setAttende(PropertiesReader.getExchangeInviteMail());

		CredentialsPage credentials = scheduler
				.clickOnCreateButton();
		
		scheduler = credentials
				.setUserName(PropertiesReader.getExchangeOrganizerUser())
				.setPassword(PropertiesReader.getExchangeOrganizerPwd())
				.clickOkButton();
	}
}
