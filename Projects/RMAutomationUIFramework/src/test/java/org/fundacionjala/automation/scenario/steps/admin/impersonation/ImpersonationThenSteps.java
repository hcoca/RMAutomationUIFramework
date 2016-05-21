package org.fundacionjala.automation.scenario.steps.admin.impersonation;

import java.util.List;

import org.fundacionjala.automation.framework.pages.admin.emailserver.AddEmailServerPage;
import org.fundacionjala.automation.framework.pages.admin.emailserver.EmailServerPage;
import org.fundacionjala.automation.framework.pages.admin.impersonation.ImpersonationPage;
import org.fundacionjala.automation.framework.pages.admin.login.LoginPage;
import org.fundacionjala.automation.framework.pages.tablet.scheduler.CredentialsPage;
import org.fundacionjala.automation.framework.utils.api.managers.ServiceAPIManager;
import org.fundacionjala.automation.framework.utils.api.managers.SettingsAPIManager;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Service;
import org.fundacionjala.automation.framework.utils.common.PropertiesReader;
import org.testng.Assert;

import cucumber.api.java.en.Then;

public class ImpersonationThenSteps {

	@Then("^the Impersonation Option is enabled$")
	public void verifyIfImpersonationIsEnabled() throws Throwable {
		boolean impersonate = false;
		List<Service> listServices;
		listServices = ServiceAPIManager.getRequest(PropertiesReader
				.getServiceURL() + "/services");
		
		for (Service service : listServices) {
			impersonate = service.impersonate;
		}
		
		Assert.assertTrue(impersonate);
	}

	@Then("^the Impersonation Option is disabled$")
	public void verifyIfImpersonationIsDisabled() throws Throwable {
		boolean impersonate = false;
		List<Service> listServices;
		listServices = ServiceAPIManager.getRequest(PropertiesReader
				.getServiceURL() + "/services");

		for (Service service : listServices) {
			impersonate = service.impersonate;
		}

		Assert.assertFalse(impersonate);
	}

	@Then("^the Authentication Type is changed to \"([^\"]*)\"$")
	public void verifyIfAthenticationTypeHasChanged(String type)
			throws Throwable {
		String actualAuthenticationType = SettingsAPIManager
				.getRequest(PropertiesReader.getServiceURL() + "/settings")
				.authentication;
		String expectedAuthenticationType = type;

		Assert.assertEquals(actualAuthenticationType,
				expectedAuthenticationType);
	}

	@Then("^the Impersonation Options displayed in the Credentials Page$")
	public void verifyIfImpersonationOptionsAreDisplayedInCredentialsPage()
			throws Throwable {
		CredentialsPage credentials = new CredentialsPage();
		boolean impersonationOptionsArePresent = false;

		if ((credentials.isCreateAsCheckBoxPresent())
				&& (credentials.isCreateInBehalfOfTextFieldPresent())) {
			impersonationOptionsArePresent = true;
		}

		Assert.assertTrue(impersonationOptionsArePresent);
	}

	@Then("^Impersonation Option is disabled$")
	public void verifyIfImpersonationOptionsAreDisabled() throws Throwable {
		EmailServerPage emailServer = new EmailServerPage();
		boolean impersonate = false;
		List<Service> listServices;
		listServices = ServiceAPIManager.getRequest(PropertiesReader
				.getServiceURL() + "/services");

		for (Service service : listServices) {
			impersonate = service.impersonate;
		}

		ImpersonationPage impersonation = emailServer
				.leftMenu
				.clickOnIssuesButton()
				.clickOnImpersonationButton();

		boolean isSaveButtonPresent = impersonation
				.findSaveButton();

		Assert.assertFalse(isSaveButtonPresent);
		Assert.assertFalse(impersonate);

		LoginPage login = new LoginPage();

		EmailServerPage server = login
				.setUserName(PropertiesReader.getUserName())
				.setPassword(PropertiesReader.getPassword())
				.clickOnSigInButton()
				.leftMenu
				.clickOnIssuesButton()
				.clickOnEmailServerButton();

		if (server.isAddButtonPresent()) {

			AddEmailServerPage addEmailServer = server
					.clickOnAddButton();

			server = addEmailServer
					.setDomainServer(PropertiesReader.getExchangeDomain())
					.setUserName(PropertiesReader.getExchangeConnectUserName())
					.setPassword(PropertiesReader.getExchangeConnectPassword())
					.clickSaveButton();
		}
	}
}
