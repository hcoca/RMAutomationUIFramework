package org.fundacionjala.automation.scenario.steps.admin.service;

import java.util.List;
import org.fundacionjala.automation.framework.pages.admin.home.AdminPage;
import org.fundacionjala.automation.framework.pages.admin.login.LoginPage;
import org.fundacionjala.automation.framework.utils.api.managers.OutOfOrderAPIManager;
import org.fundacionjala.automation.framework.utils.api.managers.ServiceAPIManager;
import org.fundacionjala.automation.framework.utils.api.objects.admin.OutOfOrder;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Service;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.LogManager;
import org.fundacionjala.automation.framework.utils.common.PropertiesReader;
import org.testng.Assert;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class OutOfOrdersAreDeletedWhenServiceDeleted{
	private List<Service> listServices;
	private String idService = null;
	private Service currentService;
	private AdminPage home;
	
	@Given("^There is a EmailServer added$")
	public void there_is_a_emailserver_added() throws Throwable {
		LogManager.info("Scenario: All Out of orders Are Deleted When Service is  Deleted");
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
	
	@Given("^At least an out of order$")
	public void at_least_an_out_of_order() throws Throwable {
		home
		.leftMenu
		.clickOnIssuesButton()
		.clickOnConferenceRoomsButton()
		.doubleClickOnRoom("Room04")
		.SelectOutOfOrder()
		.clickOnSave();
	}
	@When("^I delete the EmailServer$")
	public void i_delete_the_emailserver() throws Throwable {
		
		home
			.leftMenu
			.clickOnIssuesButton()
			.clickOnEmailServerButton()
			.clickOnRemoveButton()
			.clickOnYesButton()
			.waitItemDeleted();
	}
	@Then("^The out-of-orders are deleted$")
	public void the_out_of_orders_are_deleted() throws Throwable {
		List<OutOfOrder> currentlistOutOfOrders;
		currentlistOutOfOrders = OutOfOrderAPIManager.getRequest("http://172.20.208.84:4040/out-of-orders");
		boolean quantityZero = currentlistOutOfOrders.size() == 0;
		if (quantityZero)
			LogManager.info("[PASSED]");
		else
			LogManager.error("[FAILED]");
		
		Assert.assertTrue(currentlistOutOfOrders.size() == 0);
		
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
