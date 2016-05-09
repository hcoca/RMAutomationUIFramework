package org.fundacionjala.automation.scenario.steps.admin.service;

import java.util.List;
import org.fundacionjala.automation.framework.pages.admin.home.AdminPage;
import org.fundacionjala.automation.framework.pages.admin.login.LoginPage;
import org.fundacionjala.automation.framework.utils.api.managers.ServiceAPIManager;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Service;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.LogManager;
import org.fundacionjala.automation.framework.utils.common.PropertiesReader;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class RoomsAreAddedWhenServiceAdded{
	private List<Service> listServices;
	private String idService = null;
	private AdminPage home;
	@Given("^There is no Email Server added$")
	public void there_is_no_email_server_added() throws Throwable {
		LogManager.info("Scenario: All Rooms Are Added When Service Added");
		listServices = ServiceAPIManager.getRequest("http://172.20.208.84:4040/services");
		for (Service service : listServices) {
				idService = service._id;
		}
		if (idService != null)
		{
			ServiceAPIManager.deleteRequest("http://172.20.208.84:4040/services",idService);
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

	@When("^I add a new Email Server$")
	public void i_add_a_new_Exchange_Email_Server() throws Throwable {
		home
			.leftMenu
			.clickOnIssuesButton()
			.clickOnEmailServerButton()
			.clickOnAddButton()
			.setHostName(PropertiesReader.getExchangeHostname())
			.setUserName(PropertiesReader.getExchangeConnectUserName())
			.setPassword(PropertiesReader.getExchangeConnectPassword())
			.clickSaveButton()
			.waitAddWindowInvisible();
	}
	
	@Then("^All Conference rooms are added from Exchange service$")
	public void all_Conference_rooms_are_added_from_Exchange_service() throws Throwable {
		List<WebElement> roomsList =
				home
					.leftMenu
					.clickOnConferenceRoomsButton()
					.getRooms();
		if (roomsList.size() == 10)
			LogManager.info("[PASSED]");
		else
			LogManager.info("[FAILED]");
		Assert.assertEquals(roomsList.size(), 10);
	}
}
