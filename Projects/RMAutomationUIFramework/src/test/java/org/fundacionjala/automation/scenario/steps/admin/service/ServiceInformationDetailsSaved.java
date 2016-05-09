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

public class ServiceInformationDetailsSaved{
	private List<Service> listServices;
	private String idService = null;
	AdminPage home;
	@Given("^There is no services added$")
	public void there_is_no_services_added() throws Throwable {
		LogManager.info("Scenario: Service Information Details Saved  when service added");
		listServices = ServiceAPIManager.getRequest("http://172.20.208.84:4040/services");
		for (Service service : listServices) {
				idService = service._id;
		}
		if (idService != null)
		{
			ServiceAPIManager.deleteRequest("http://172.20.208.84:4040/services",idService);
		}
		BrowserManager.openBrowser();
		LoginPage login = new LoginPage();
		login.setUserName(PropertiesReader.getUserName())
			.setPassword(PropertiesReader.getPassword())
			.clickOnSigInButton()
			.refreshPage();
	}

	@When("^I add a new Exchange Email Server$")
	public void i_add_a_new_Exchange_Email_Server() throws Throwable {
		home
			.leftMenu
			.clickOnIssuesButton()
			.clickOnEmailServerButton()
			.clickOnAddButton()
			.setDomainServer(PropertiesReader.getExchangeHostname())
			.setUserName(PropertiesReader.getExchangeConnectUserName())
			.setPassword(PropertiesReader.getExchangeConnectPassword())
			.clickSaveButton()
			.waitAddWindowInvisible();
	}

	@Then("^Service infomation is saved$")
	public void service_infomation_is_saved() throws Throwable {
		EmailServerPage email = new EmailServerPage();
		listServices = ServiceAPIManager.getRequest("http://172.20.208.84:4040/services");
		Assert.assertTrue(email.verifyDetailsExist(listServices));
	}

}
