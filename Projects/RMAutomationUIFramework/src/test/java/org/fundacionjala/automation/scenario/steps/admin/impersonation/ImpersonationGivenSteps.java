package org.fundacionjala.automation.scenario.steps.admin.impersonation;

import java.util.List;

import org.fundacionjala.automation.framework.utils.api.managers.ServiceAPIManager;
import org.fundacionjala.automation.framework.utils.api.managers.SettingsAPIManager;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Service;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Settings;

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
}
