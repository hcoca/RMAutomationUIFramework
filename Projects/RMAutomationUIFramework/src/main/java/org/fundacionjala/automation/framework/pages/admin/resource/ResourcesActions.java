package org.fundacionjala.automation.framework.pages.admin.resource;

import org.fundacionjala.automation.framework.utils.api.managers.ResourceAPIManager;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Resource;
import org.fundacionjala.automation.framework.utils.common.LogManager;
import org.fundacionjala.automation.framework.utils.common.PropertiesReader;

public class ResourcesActions {

	
	public static Resource createResourceByAPI(String name, String icon, String description) {
		Resource resource;
		
		try {
			resource = ResourceAPIManager.postRequest(PropertiesReader.getServiceURL() + "/resources",
	                new Resource(name, name, icon, "", description));
          
			LogManager.info("The resource: " + resource.customName + "  was created");
		} catch (Exception e) {
			
			LogManager.info("The resource could not be created " + e);
		   resource = null;	
		}
		return resource;
	}
	
	public static void deleteResourceByAPI(Resource resource) {
		try {
			ResourceAPIManager.deleteRequest(PropertiesReader.getServiceURL() + "/resources", resource._id);
			LogManager.info("The resource: " + resource.customName + "  was deleted");
		} catch (Exception e) {
			LogManager.info("The resource could not be deleted " + e);
		}
	}
	
	
}
