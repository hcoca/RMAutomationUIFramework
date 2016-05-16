package org.fundacionjala.automation.scenario.steps.admin.resource;

import java.util.List;

import org.fundacionjala.automation.framework.pages.admin.home.AdminPage;
import org.fundacionjala.automation.framework.pages.admin.resource.RemoveResourcePage;
import org.fundacionjala.automation.framework.pages.admin.resource.ResourcePage;
import org.fundacionjala.automation.framework.utils.api.managers.ResourceAPIManager;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Resource;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.PropertiesReader;
import org.testng.Assert;

import com.mashape.unirest.http.exceptions.UnirestException;

import cucumber.api.java.After;
import cucumber.api.java.en.Then;

public class ResourceThenSteps {
	
	@Then("^Validate that the resource with name \"([^\"]*)\" is diplayed in resource page$")
	public void validate_that_the_resource_with_name_is_diplayed(
		String resourceName) throws Throwable {
			
		ResourcePage resources = new ResourcePage();
		
		BrowserManager.getDriver().navigate().refresh();
	    AdminPage admin = new AdminPage();
	    admin.leftMenu.clickOnConferenceRoomsButton();
	    admin.leftMenu.clickOnResourcesButton();
		
		Assert.assertTrue(resources
					.verifyResourceExist(resourceName));
		//Post condition
		deleteResourceByName(resourceName);
	}
	@Then("^Validate that the resource with the name \"([^\"]*)\" has been deleted$")
	public void validate_that_the_resource_with_the_name_has_been_deleted(
		String arg1) throws Throwable {
		AdminPage admin = new AdminPage();
		ResourcePage resource = admin
					.leftMenu
					.clickOnIssuesButton()
					.clickOnResourcesButton();
		boolean isResourceNotPresent = resource
						.verifyResourceNotExist(arg1);
		Assert.assertTrue(isResourceNotPresent);
	}
	@Then("^Validate the resource \"([^\"]*)\" is displayed$")
	public void validate_the_resource_is_displayed(
		String resourceName) throws Throwable {
		ResourcePage resourcePage = new ResourcePage();
		Assert.assertTrue(
				resourcePage.verifyResourceExist(resourceName));
		
		//Post condition
		deleteResourceByName(resourceName);
	}

	@Then("^Validate that the resource \"([^\"]*)\" is modified according the changes \\(\"([^\"]*)\" field with value \"([^\"]*)\"\\)$")
	public void validate_that_the_resource_is_modified(String resourceName, 
		String nameField, 
		String value) throws Throwable {
		ResourcePage resourcePage = new ResourcePage();
		resourcePage =	(new AdminPage())
				.leftMenu
				.clickOnResourcesButton();
		
		Assert.assertTrue(
			resourcePage
			.verifyResourceModifiedByField(resourceName,nameField,
							value)); 
		//Post condition
		deleteResourceByName(resourceName);
	}
	
	@Then("^Validate that the 'next' page is displayed on resource table$")
	public void validate_that_the_next_page_is_displayed() throws Throwable {
		ResourcePage resources = new ResourcePage();
		Assert.assertTrue(
				resources.verifyNextPage("2"));
		//Post condition
	}
	
	@Then("^Validate that the 'previous' page is displayed on resource table$")
	public void validate_the_previous_page_is_displayed() throws Throwable {
	    ResourcePage resources = new ResourcePage();
		int previousPage = resources.getTheTotalNumberPage();
		Assert.assertTrue(
				resources.verifyPreviousPage(previousPage));
		//Post condition
	}
	
	@Then("^Validate that the \"([^\"]*)\" page is displayed$")
	public void validate_that_the_page_is_displayed(
		String NumberPage) throws Throwable {
	    	List<Resource> listResource = 
			ResourceAPIManager.getRequest(
				PropertiesReader.getServiceURL() + "/resources");
		int resourceIndx = ((Integer.parseInt(NumberPage) - 1) * 50) + 1;
		ResourcePage resources = new ResourcePage();
		String firstElement = listResource.get(resourceIndx - 1).name;
		Assert.assertTrue(
				resources
				.verifyTheFirstElementOnThePage(firstElement));
		//Post condition
	}
	
	@Then("^Validate that resource with \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\" is displayed$")
	public void validate_that_resource_with_and_is_displayed(
		String resourceName, 
		String displayname, 
		String icon) throws Throwable {
		ResourcePage resources = new ResourcePage();
		Assert.assertTrue(
			resources
			.verifyResourceDisplayed(resourceName, displayname,
						icon));
		 //Post condition
		deleteResourceByName(resourceName);
	}
	
	@Then("^Validate that all resources are displayed in resource table$")
	public void validate_all_resources_are_displayed() throws Throwable {
		ResourcePage resources = new ResourcePage();
		List<Resource> listResource = 
			ResourceAPIManager.getRequest(
				PropertiesReader.getServiceURL() + "/resources");
		AdminPage admin = new AdminPage();
		admin.leftMenu.clickOnConferenceRoomsButton();
	    admin.leftMenu.clickOnResourcesButton();
		
		 Assert.assertTrue(
				 resources
				 .verifyResourcesOnResourceTable(listResource));
		 //Post condition
	}
	
	@Then("^Validate that total resources are displayed$")
	public void validate_total_resources_are_displayed() throws Throwable {
		ResourcePage resources = new ResourcePage();
		List<Resource> listResource = 
			ResourceAPIManager.getRequest(
				PropertiesReader.getServiceURL() + "/resources");
		int totalItems = listResource.size();
		Assert.assertTrue(
				 resources.verifyTotalItems(totalItems));
		 
		//Post condition
	}
	
	@Then("^Validate that the resource table size is same than the option \"([^\"]*)\" selected$")
	public void validate_resource_table_size(
		String sizeTable) throws Throwable {
                ResourcePage resources = new ResourcePage();
                Assert.assertTrue(
                	resources
                		.verifyNumberOfResources(sizeTable));
                //Post condition
	}
	
	@Then("^Validate that the association with the \"([^\"]*)\" room is displayed$")
	public void validate_resource_association(
		String RoomName) throws Throwable {
	    RemoveResourcePage removeResource = new RemoveResourcePage();
	    Assert.assertTrue(
		    	removeResource.verifyAssociatedRoomExist(RoomName));
	 	
	    	//Post condition
	    	removeResource.clickOnRemoveButton();
	}
	
	@Then("^Validate that the first page is displayed on resource table$")
	public void validate_the_first_page() throws Throwable {
	    	ResourcePage resources = new ResourcePage();
	    	Assert.assertTrue(
			resources
			    	.verifyTheFirstPage("1"));
		//Post condition
	}
	
	@Then("^Validate that the last page is displayed on resource table$")
	public void validate_the_last_page() throws Throwable {
	    ResourcePage resources = new ResourcePage();
	    	Assert.assertTrue(
				resources
			    	.verifyTheLastPage());  	
	}
	
	@After("@resouceDelete")
	public void tearDown() throws Throwable
	{
	    deleteResourcesCreated();
	}
	
	private void deleteResourceByName(
		String resourceName) throws UnirestException{
                String idResource = "";
                List<Resource> listResource = ResourceAPIManager.getRequest(
                			  PropertiesReader.getServiceURL() 
                			  +"/resources");
                for (Resource resource : listResource) {
                if(resource.name.equalsIgnoreCase(resourceName)){
                    idResource = resource._id;
                }
                }
                ResourceAPIManager.deleteRequest(PropertiesReader.getServiceURL()
		    				+ "/resources", idResource);
	}
	
	private void deleteResourcesCreated() throws UnirestException{
                List<Resource> listResource = 
                	ResourceAPIManager.getRequest(
                		PropertiesReader.getServiceURL() + "/resources");
                for (Resource resource : listResource) {
                	if(resource.name.contains("Gift"))
                	{
                		ResourceAPIManager.deleteRequest(
                			PropertiesReader.getServiceURL() + 
                			"/resources", resource._id);
                	}
		}
	}
	
}
