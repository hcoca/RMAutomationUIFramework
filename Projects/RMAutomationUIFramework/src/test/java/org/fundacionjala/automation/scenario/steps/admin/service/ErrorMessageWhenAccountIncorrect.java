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

public class ErrorMessageWhenAccountIncorrect{
	private List<Service> listServices;
	private String idService = null;
	private AdminPage home;
	private Service currentService;
	@Given("^There is the service added$")
	public void there_is_no_service_added() throws Throwable {
		LogManager.info("Scenario: An Error Message is displayed when Account is Incorrect");
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

	@When("^I try to modify the Exchange Email Server credential with user account which does not follow the requirements$")
	public void i_modify_Exchange_Email_Server_credential() throws Throwable {
		home
		.leftMenu
		.clickOnIssuesButton()
		.clickOnEmailServerButton()
		.clickOnServerItemButton()
		.clickOnEditCredentialButton()
		.setUserName("RoomManagerX")
		.setPassword("Control*123")
		.clickOnAcceptButton();
	}
	
	@Then("^An error message is displayed$")
	public void an_error_message_is_displayed() throws Throwable {
		EmailServerPage email = new EmailServerPage();
		boolean error = email.verifyErrorMessageInCredential();
		Assert.assertTrue(error);
		
		//Post-condition : Restore 
		idService = null;
		listServices = ServiceAPIManager.getRequest("http://172.20.208.84:4040/services");
		for (Service service : listServices) {
				idService = service._id;
		}
		if (idService == null)
		{
			ServiceAPIManager.postRequest("http://172.20.208.84:4040/services?type=exchange",
					new Service(PropertiesReader.getExchangeHostname(),
							PropertiesReader.getExchangeConnectUserName(),
							PropertiesReader.getExchangeConnectPassword()));
		}
	}
}
