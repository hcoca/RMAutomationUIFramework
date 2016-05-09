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

public class ServiceInformationDetailsIsDeleted {
	private List<Service> listServices;
	private String idService = null;
	private Service currentService;
	AdminPage home;
	@Given("^There is a service alive$")
	public void there_is_a_service_alive() throws Throwable {
		LogManager.info("Scenario: Service Information Details is Deleted When Service is Deleted");
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
	@When("^I delete the current Email Server$")
	public void i_delete_the_current_email_server() throws Throwable {
		home
			.leftMenu
			.clickOnIssuesButton()
			.clickOnEmailServerButton()
			.clickOnRemoveButton()
			.clickOnYesButton()
			.waitItemDeleted();
	}

	@Then("^Service information details are deleted$")
	public void service_information_details_are_deleted() throws Throwable {
		EmailServerPage email = new EmailServerPage();
		listServices = ServiceAPIManager.getRequest("http://172.20.208.84:4040/services");
		boolean state = email.verifyDetailsDeleted(listServices);
		Assert.assertTrue(state);
		
		//Post-condition : Restore Service
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
