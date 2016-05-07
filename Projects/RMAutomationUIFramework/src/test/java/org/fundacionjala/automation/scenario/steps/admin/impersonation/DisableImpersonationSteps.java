package org.fundacionjala.automation.scenario.steps.admin.impersonation;

import java.util.List;

import org.fundacionjala.automation.framework.pages.admin.home.AdminPage;
import org.fundacionjala.automation.framework.pages.admin.login.LoginPage;
import org.fundacionjala.automation.framework.utils.api.managers.ServiceAPIManager;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Service;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.testng.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class DisableImpersonationSteps {

	@Given("^the user \"([^\"]*)\" has logged into Room Manager$")
	public void the_user_has_logged_into_Room_Manager(String arg1) throws Throwable {
		boolean impersonate = false;
		String id = null;
		List<Service> listServices;
		listServices = ServiceAPIManager.getRequest("http://172.20.208.84:4040/services");
		
		for(Service service : listServices) {
			id = service._id;
		}
		
		if(id != null)
		{
			for(Service service : listServices) {
				impersonate = service.impersonate;
			}
			
			if(impersonate == false) {	
				ServiceAPIManager.putImpersonationRequest("http://172.20.208.84:4040/services", id, true);
			}
		}
		
		BrowserManager.openBrowser();
		LoginPage login = new LoginPage();
		
		login
			.setUserName(arg1)
			.setPassword("Control*123")
			.clickOnSigInButton()
			.refreshPage();
	}

	@When("^he disables Impersonation$")
	public void he_disables_Impersonation() throws Throwable {
		AdminPage admin = new AdminPage();
		
	    admin
	    	.leftMenu
	    	.clickOnImpersonationButton()
	    	.clickOnUseImpersonationCheckBox()
	    	.clickOnSaveButton();
	}
	
	@Then("^the Impersonation Option is disabled$")
	public void the_Impersonation_Option_is_disabled() throws Throwable {
		boolean impersonate = true;
		String id = null;
		List<Service> listServices;
		listServices = ServiceAPIManager.getRequest("http://172.20.208.84:4040/services");
		
		for (Service service : listServices) {
			impersonate = service.impersonate;
		}
		
		Assert.assertFalse(impersonate);
		
		List<Service> servicesList;
		servicesList = ServiceAPIManager.getRequest("http://172.20.208.84:4040/services");
		
		for(Service service : servicesList) {
			id = service._id;
		}
		
		if(id != null)
		{
			if(impersonate == true) {	
				ServiceAPIManager.putImpersonationRequest("http://172.20.208.84:4040/services", id, false);
			}
		}
	}
}
