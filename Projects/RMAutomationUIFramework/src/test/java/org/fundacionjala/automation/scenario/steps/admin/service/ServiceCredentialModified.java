package org.fundacionjala.automation.scenario.steps.admin.service;

import java.util.List;
import org.fundacionjala.automation.framework.pages.admin.emailserver.EmailServerPage;
import org.fundacionjala.automation.framework.pages.admin.home.AdminPage;
import org.fundacionjala.automation.framework.pages.admin.login.LoginPage;
import org.fundacionjala.automation.framework.utils.api.managers.ServiceAPIManager;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Service;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.LogManager;
import org.fundacionjala.automation.framework.utils.common.PropertiesReader;
import org.testng.Assert;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ServiceCredentialModified {
	private List<Service> listServices;
	private String idService = null;
	private Service currentService;
	AdminPage home;
	@Given("^There is a service existent$")
	public void there_is_a_service_existent() throws Throwable {
		LogManager.info("Scenario: Service Credential is modified whe it it modified");
		listServices = ServiceAPIManager.getRequest("http://172.20.208.84:4040/services");
		for (Service service : listServices) {
				idService = service._id;
				currentService =service;
		}
		if (idService == null)
		{
			currentService = ServiceAPIManager.postRequest("http://172.20.208.84:4040/services?type=exchange",
					new Service(PropertiesReader.getExchangeHostname(),
							PropertiesReader.getExchangeConnectUserName(),
							PropertiesReader.getExchangeConnectPassword()));
			idService = currentService._id;
		}
		home = new AdminPage();
		if(BrowserManager.getDriver() != null)
		{
			home.refreshPage();
		}
		BrowserManager.openBrowser();
		LoginPage login = new LoginPage();
		login.setUserName(PropertiesReader.getUserName())
			.setPassword(PropertiesReader.getPassword())
			.clickOnSigInButton()
			.refreshPage();
	}

	@When("^I modify credential with another account$")
	public void i_modify_credential_with_another_account() throws Throwable {
		
		home
			.leftMenu
			.clickOnIssuesButton()
			.clickOnEmailServerButton()
			.clickOnServerItemButton()
			.clickOnEditCredentialButton()
			.setUserName("RoomManager1")
			.setPassword("Control*123")
			.clickOnAcceptButton()
			.waitProcessing();
	}

	@Then("^The changes are saved$")
	public void the_changes_are_saved() throws Throwable {
		EmailServerPage email = new EmailServerPage();
		currentService = ServiceAPIManager.getRequest("http://172.20.208.84:4040/services",idService);
		Assert.assertTrue(email.verifyCredential(currentService.credential_username, "RoomManager1"));
	}
}
