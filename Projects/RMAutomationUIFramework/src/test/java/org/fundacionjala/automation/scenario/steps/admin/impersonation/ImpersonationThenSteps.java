package org.fundacionjala.automation.scenario.steps.admin.impersonation;

import java.util.List;

import org.fundacionjala.automation.framework.pages.tablet.scheduler.CredentialsPage;
import org.fundacionjala.automation.framework.utils.api.managers.ServiceAPIManager;
import org.fundacionjala.automation.framework.utils.api.managers.SettingsAPIManager;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Service;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Settings;
import org.testng.Assert;

import cucumber.api.java.en.Then;

public class ImpersonationThenSteps {
	@Then("^the Impersonation Option is enabled$")
	public void the_Impersonation_Option_is_enabled() throws Throwable {
		boolean impersonate = false;
		String id = null;
		List<Service> listServices;
		listServices = ServiceAPIManager.getRequest("http://172.20.208.84:4040/services");
		
		for(Service service : listServices) {
			impersonate = service.impersonate;
		}
		
		Assert.assertTrue(impersonate);

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
	
	@Then("^the Authentication Type is changed to \"([^\"]*)\"$")
	public void the_Athentication_Type_is_changed_to(String type) throws Throwable {
	    String actualAuthenticationType = SettingsAPIManager.getRequest("http://172.20.208.84:4040/settings").authentication;
	    String expectedAuthenticationType = type;
	    
	    Assert.assertEquals(actualAuthenticationType, expectedAuthenticationType);
	    
	    if(actualAuthenticationType.equals("rfid")) {
			Settings settings = new Settings("credentials", 5, "blue");
			SettingsAPIManager.putRequest("http://172.20.208.84:4040/settings", settings);
		}
	}
	
	@Then("^the Impersonation Options displayed in the Credentials Page$")
	public void the_Impersonation_Options_displayed_in_the_Credentials_Page() throws Throwable {
		CredentialsPage credentials = new CredentialsPage();
		boolean impersonationOptionsArePresent = false;
		
		if(credentials.isCreateAsCheckBoxPresent() && credentials.isCreateInBehalfOfTextFieldPresent()) {
			impersonationOptionsArePresent = true;
		}
		
		Assert.assertTrue(impersonationOptionsArePresent);
		
		boolean impersonate = false;
		List<Service> listServices;
		String id = null;
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
}
