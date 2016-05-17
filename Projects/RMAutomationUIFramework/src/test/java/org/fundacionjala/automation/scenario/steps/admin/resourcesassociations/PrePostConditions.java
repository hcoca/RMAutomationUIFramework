package org.fundacionjala.automation.scenario.steps.admin.resourcesassociations;

import java.util.List;

import org.fundacionjala.automation.framework.maps.admin.resource.IconResources;
import org.fundacionjala.automation.framework.pages.admin.resource.ResourcesActions;
import org.fundacionjala.automation.framework.utils.api.managers.ResourceAPIManager;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Resource;
import org.fundacionjala.automation.framework.utils.common.PropertiesReader;

import com.mashape.unirest.http.exceptions.UnirestException;

import cucumber.api.java.After;
import cucumber.api.java.Before;

public class PrePostConditions {
  private String resourceNameCreated;	
	
		@Before("@scenario#1")
		public void beforeScenario1() throws Throwable {
			
			resourceNameCreated = "resourceassoc01";
			createResource(resourceNameCreated, 
					       IconResources.FOLDER, 
					       "folder1");
			
		}
		
		@Before("@scenario#2")
		public void beforeScenario2() throws Throwable {
			
			resourceNameCreated = "resourceassoc02";
			createResource(resourceNameCreated, IconResources.DESKTOP, "desktop");
			
		}
		
		@Before("@scenario#3")
		public void beforeScenario3() throws Throwable {
			
			resourceNameCreated = "resourceassoc03";
			createResource(resourceNameCreated, IconResources.KEY, "key003");
			
		}
	    
		@Before("@scenario#4")
		public void beforeScenario4() throws Throwable {
			resourceNameCreated = "resourceassoc04";
			createResource(resourceNameCreated, IconResources.KEY, "key003");
			
		}
	    
		@Before("@scenario#5")
		public void beforeScenario5() throws Throwable {
			resourceNameCreated = "resourceassoc05";
			ResourcesActions.createResourceByAPI(resourceNameCreated, IconResources.KEY, "key003");
			
		}
	    
		@Before("@scenario#6")
		public void beforeScenario6() throws Throwable {
			resourceNameCreated = "resourceassoc06";
			createResource(resourceNameCreated, IconResources.KEY, "key003");
			
		}
		@Before("@scenario#7")
		public void beforeScenario7() throws Throwable {
			resourceNameCreated = "resourceassoc07";
			createResource(resourceNameCreated, IconResources.KEY, "key0089");
			
		}
		
	    @After("@scenario#1,@scenario#2,@scenario#3,@scenario#4,@scenario#5,@scenario#6,@scenario#7,@scenario#8")
		public void tearDown() throws Throwable
		{
		    deleteResourcesCreated();
	 	}
	 	
	 	private void deleteResourcesCreated() throws UnirestException{
	 		
	        List<Resource> listResource = ResourceAPIManager.getRequest(
	                 		PropertiesReader.getServiceURL() + "/resources");
	                 for (Resource resource : listResource) {
	                	 
	                 	if(resource.name.contains("resourceassoc"))
	                 	{
	                 		ResourceAPIManager.deleteRequest(
	                 			PropertiesReader.getServiceURL() + 
	                 			"/resources", resource._id);
	                 	}
	 		}
	 	}
	 	
		private void createResource(String name, String icon, String description)
	 	{
	 		ResourcesActions.createResourceByAPI(name, icon, description);
	 	}
	 	
	 
	 }


