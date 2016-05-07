package org.fundacionjala.automation.scenario.steps.admin.impersonation;

import java.util.List;

import org.fundacionjala.automation.framework.pages.admin.emailserver.EmailServerPage;
import org.fundacionjala.automation.framework.pages.admin.home.AdminPage;
import org.fundacionjala.automation.framework.pages.admin.login.LoginPage;
import org.fundacionjala.automation.framework.utils.api.managers.ServiceAPIManager;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Service;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.testng.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ImpersonationOptionIsDisabledWhenThereIsNoEmailServerSteps {

	@Given("^a user \"([^\"]*)\" has logged into Room Manager$")
	public void a_user_has_logged_into_Room_Manager(String arg1) throws Throwable {
		BrowserManager.openBrowser();
		LoginPage login = new LoginPage();
		
		login
			.setUserName(arg1)
			.setPassword("Control*123")
			.clickOnSigInButton()
			.refreshPage();
	}
	
	@When("^there is no Email Server Added$")
	public void there_is_no_Email_Server_Added() throws Throwable {
		String idService = null;
		List<Service> listServices;
		listServices = ServiceAPIManager.getRequest("http://172.20.208.84:4040/services");
		
		for(Service service : listServices) {
				idService = service._id;
		}
		if(idService != null)
		{
			ServiceAPIManager.deleteRequest("http://172.20.208.84:4040/services", idService);
		}
		
		AdminPage admin = new AdminPage();
		
		EmailServerPage emailServer = admin 
		    	.leftMenu
		    	.clickOnEmailServerButton();
		
		boolean isEmailServerPresent = emailServer.findEmailServer();
		
		Assert.assertFalse(isEmailServerPresent);
	}
	
	@Then("^Impersonation Option is disabled$")
	public void Impersonation_Option_is_disabled() throws Throwable {
		boolean impersonate = true;
		List<Service> listServices;
		listServices = ServiceAPIManager.getRequest("http://172.20.208.84:4040/services");
		
		for (Service service : listServices) {
			impersonate = service.impersonate;
		}
		
		Assert.assertFalse(impersonate);
	}
}
