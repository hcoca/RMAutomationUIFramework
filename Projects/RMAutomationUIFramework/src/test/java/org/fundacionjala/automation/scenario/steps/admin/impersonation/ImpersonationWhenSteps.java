package org.fundacionjala.automation.scenario.steps.admin.impersonation;

import org.fundacionjala.automation.framework.pages.admin.emailserver.EmailServerPage;
import org.fundacionjala.automation.framework.pages.admin.home.AdminPage;
import org.fundacionjala.automation.framework.pages.tablet.home.HomePage;
import org.fundacionjala.automation.framework.pages.tablet.scheduler.SchedulerPage;
import org.fundacionjala.automation.framework.pages.tablet.settings.ConnectionPage;
import org.fundacionjala.automation.framework.pages.tablet.settings.NavigationPage;
import org.fundacionjala.automation.framework.utils.common.PropertiesReader;
import org.testng.Assert;

import cucumber.api.java.en.When;

public class ImpersonationWhenSteps {

	@When("^I enable Impersonation$")
	public void enableImpersonation() throws Throwable {
		AdminPage admin = new AdminPage();

		admin
		  .leftMenu
		  .clickOnImpersonationButton()
		  .clickOnUseImpersonationCheckBox()
		  .clickOnSaveButton();
	}

	@When("^I disable Impersonation$")
	public void disableImpersonation() throws Throwable {
		AdminPage admin = new AdminPage();

		admin
		  .leftMenu
	      	  .clickOnImpersonationButton()
		  .clickOnUseImpersonationCheckBox()
		  .clickOnSaveButton();
	}

	@When("^I change Authentication to User and Password$")
	public void changeAuthenticationToCredentials() throws Throwable {
		AdminPage admin = new AdminPage();

		admin
		  .leftMenu
		  .clickOnImpersonationButton()
		  .clickOnUserAndPasswordRadioButton()
		  .clickOnSaveButton()
		  .waitForImpersonationMessageDisappear();
	}

	@When("^I change Authentication to RFID$")
	public void changeAuthenticationToRFID() throws Throwable {
		AdminPage admin = new AdminPage();

		admin
		  .leftMenu
		  .clickOnImpersonationButton()
		  .clickOnRFIDRadioButton()
		  .clickOnSaveButton()
		  .waitForImpersonationMessageDisappear();
	}

	@When("^I create a new meeting with subject \"([^\"]*)\"$")
	public void createMeetingWithSubject(String subject) throws Throwable {
		ConnectionPage connection = new ConnectionPage();

		NavigationPage navigation = connection
				.setUpServiceURL(PropertiesReader.getServiceURL())
				.clickOnSaveButton()
				.clickOnNavigationButton();

		HomePage home = navigation.clickOnRoomToggleButton()
				.selectConferenceRoom(PropertiesReader.getConferenceRoom())
				.clickOnSaveButton()
				.topMenu
				.clickOnHomeButton();

		SchedulerPage scheduler = home.clickOnScheduleButton()
				.setOrganizer(PropertiesReader.getExchangeConnectUserName())
				.setSubject(subject)
				.setAttende(PropertiesReader.getExchangeInviteUser());

		scheduler.clickOnCreateButton();
	}

	@When("^there is no Email Server Added to do impersonation$")
	public void verifyIfNoEmailServerIsAdded() throws Throwable {
		AdminPage admin = new AdminPage();

		EmailServerPage emailServer = admin
						.leftMenu
						.clickOnEmailServerButton();

		boolean isEmailServerPresent = emailServer
						.findEmailServer();

		Assert.assertFalse(isEmailServerPresent);
	}
}
