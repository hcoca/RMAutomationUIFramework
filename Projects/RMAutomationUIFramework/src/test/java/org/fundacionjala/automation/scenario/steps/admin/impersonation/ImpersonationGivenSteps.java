package org.fundacionjala.automation.scenario.steps.admin.impersonation;

import java.util.List;

import org.fundacionjala.automation.framework.pages.admin.emailserver.EmailServerPage;
import org.fundacionjala.automation.framework.pages.admin.login.LoginPage;
import org.fundacionjala.automation.framework.utils.api.managers.ServiceAPIManager;
import org.fundacionjala.automation.framework.utils.api.managers.SettingsAPIManager;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Service;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Settings;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;

import cucumber.api.java.en.Given;

public class ImpersonationGivenSteps {
	
	@Given("^impersonation is disabled$")
	public void impersonation_is_disabled() throws Throwable {
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
			
			if(impersonate == true) {	
				ServiceAPIManager.putImpersonationRequest("http://172.20.208.84:4040/services", id, false);
			}
		}
	}
	
	@Given("^impersonation is enabled$")
	public void impersonation_is_enabled() throws Throwable {
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
	}
	
	@Given("^authentication type configured as \"([^\"]*)\"$")
	public void authentication_type_configured_as(String type) throws Throwable {
		Settings settings = new Settings(type, 5, "blue");
		SettingsAPIManager.putRequest("http://172.20.208.84:4040/settings", settings);
	}
	
	@Given("^a user \"([^\"]*)\" has logged into Room Manager with an email server added$")
	public void a_user_has_logged_into_Room_Manager(String arg1) throws Throwable {
		BrowserManager.openBrowser();
		LoginPage login = new LoginPage();
		
		EmailServerPage emailServer = login
				.setUserName("SamuelSahonero")
				.setPassword("Control*123")
				.clickOnSigInButton()
				.refreshPage()
				.leftMenu
				.clickOnEmailServerButton();
		
		boolean isEmailServerPresent = emailServer.findEmailServer();
		
		if(isEmailServerPresent == true) {
			
			emailServer
				.clickOnRemoveButton()
				.clickOnYesButton();
		}
		
		LoginPage loginPage = new LoginPage();
		
		loginPage
			.setUserName(arg1)
			.setPassword("Control*123")
			.clickOnSigInButton()
			.refreshPage();
	}
}
