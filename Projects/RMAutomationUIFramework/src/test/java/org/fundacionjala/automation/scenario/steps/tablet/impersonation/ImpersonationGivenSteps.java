package org.fundacionjala.automation.scenario.steps.tablet.impersonation;

import org.fundacionjala.automation.framework.pages.admin.home.AdminPage;
import org.fundacionjala.automation.framework.pages.admin.impersonation.ImpersonationPage;
import org.fundacionjala.automation.framework.pages.admin.login.LoginPage;
import org.fundacionjala.automation.framework.pages.tablet.home.HomePage;
import org.fundacionjala.automation.framework.pages.tablet.scheduler.CredentialsPage;
import org.fundacionjala.automation.framework.pages.tablet.scheduler.SchedulerPage;
import org.fundacionjala.automation.framework.pages.tablet.settings.ConnectionPage;
import org.fundacionjala.automation.framework.pages.tablet.settings.NavigationPage;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.PropertiesReader;

import cucumber.api.java.en.Given;

public class ImpersonationGivenSteps {
    	@Given("^I schedule a new meeting with a \"([^\"]*)\" subject in the Room \"([^\"]*)\"$")
   	public void createMeeting(String subject, String roomName) throws Throwable {
    	BrowserManager.openBrowser();
    	ConnectionPage connection = new ConnectionPage();

		NavigationPage navigation = connection
				.setUpServiceURL(PropertiesReader.getServiceURL())
				.clickOnSaveButton()
				.clickOnNavigationButton();

		HomePage home = navigation
				.clickOnRoomToggleButton()
				.selectConferenceRoom(roomName)
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
    	
    	@Given("^impersonation is enabled by UI$")
   	public void enableImpersonationUI() throws Throwable {
            	BrowserManager.openBrowser();
        	LoginPage loginPage = new LoginPage();
        	
        	AdminPage admin = loginPage
        		.setUserName(PropertiesReader.getUserName())
        		.setPassword(PropertiesReader.getPassword())
        		.clickOnSigInButton();
        	
        	String message = "Impersonation is now disabled.";

		ImpersonationPage impersonation = admin
						.leftMenu
						.clickOnIssuesButton()
						.clickOnImpersonationButton();
		
		while(message.equals("Impersonation is now disabled.")) {
			
			message = impersonation	
					.clickOnUseImpersonationCheckBox()
					.clickOnUserAndPasswordRadioButton()
					.clickOnSaveButton()
					.waitForImpersonationMessage()
					.getImpersonationMessage();
			
			impersonation
				.waitForImpersonationMessageDisappear();
		}
    	}
}
