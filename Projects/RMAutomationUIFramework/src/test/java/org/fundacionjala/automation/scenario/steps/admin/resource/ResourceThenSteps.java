package org.fundacionjala.automation.scenario.steps.admin.resource;

import java.util.List;

import org.fundacionjala.automation.framework.pages.admin.home.AdminPage;
import org.fundacionjala.automation.framework.pages.admin.resource.ResourcePage;
import org.fundacionjala.automation.framework.utils.api.managers.ResourceAPIManager;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Resource;
import org.fundacionjala.automation.framework.utils.common.PropertiesReader;
import org.testng.Assert;

import cucumber.api.java.en.Then;

public class ResourceThenSteps {
	
	@Then("^Validate that the resource with name \"([^\"]*)\" is diplayed in resource page$")
	public void validate_that_the_resource_with_name_is_diplayed_in_resource_page(String resourceName) throws Throwable {
		ResourcePage resources = new ResourcePage();
		Assert.assertTrue(resources
				.verifyResourceExist(resourceName));
		
		//Post condition
		String idResource = "";
		List<Resource> listResource = ResourceAPIManager.getRequest(PropertiesReader.getServiceURL()+"/resources");
		for (Resource resource : listResource) {
			if(resource.name.equalsIgnoreCase(resourceName))
			{
				idResource = resource._id;
			}
		}
		ResourceAPIManager.deleteRequest(PropertiesReader.getServiceURL()+"/resources", idResource);

	}
	@Then("^Validate that the resource with the name \"([^\"]*)\" has been deleted$")
	public void validate_that_the_resource_with_the_name_has_been_deleted(String arg1) throws Throwable {
		AdminPage admin = new AdminPage();
			
			ResourcePage resource = admin
										.leftMenu
										.clickOnIssuesButton()
										.clickOnResourcesButton();
			
			boolean isResourceNotPresent = resource.verifyResourceNotExist(arg1);
			
			Assert.assertTrue(isResourceNotPresent);
	}
	@Then("^Validate the resource \"([^\"]*)\" is displayed$")
	public void validate_the_resource_is_displayed(String resourceName) throws Throwable {
		ResourcePage resourcePage = new ResourcePage();
		Assert.assertTrue(
				resourcePage.verifyResourceExist(resourceName));
		
		//Post condition
				String idResource = "";
				List<Resource> listResource = ResourceAPIManager.getRequest(PropertiesReader.getServiceURL()+"/resources");
				for (Resource resource : listResource) {
					if(resource.name.equalsIgnoreCase(resourceName))
					{
						idResource = resource._id;
					}
				}
				ResourceAPIManager.deleteRequest(PropertiesReader.getServiceURL()+"/resources", idResource);

	}

	@Then("^Validate that the resource \"([^\"]*)\" is modified according the changes \\(\"([^\"]*)\" field with value \"([^\"]*)\"\\)$")
	public void validate_that_the_resource_is_modified_according_the_changes_field_with_value(String resourceName, String name, String value) throws Throwable {
		ResourcePage resourcePage = new ResourcePage();
		resourcePage =	(new AdminPage())
				.leftMenu
				.clickOnResourcesButton();
		
		Assert.assertTrue(resourcePage.verifyResourceModifiedByField(resourceName,name, value)); 
		
		/*PostCondition - remove resource*/
		String idResource = "";
		List<Resource> listResource = ResourceAPIManager.getRequest(PropertiesReader.getServiceURL()+"/resources");
		for (Resource resource : listResource) {
			if(resource.name.equalsIgnoreCase(resourceName))
			{
				idResource = resource._id;
			}
		}
		ResourceAPIManager.deleteRequest(PropertiesReader.getServiceURL() + "/resources", idResource);
	}
	
	@Then("^Validate that the 'next' page is displayed on resource table$")
	public void validate_that_the_next_page_is_displayed_on_resource_table() throws Throwable {
		ResourcePage resources = new ResourcePage();
		Assert.assertTrue(
				resources.verifyNextPage("2"));
		//Post condition
		List<Resource> listResource = ResourceAPIManager.getRequest(PropertiesReader.getServiceURL()+"/resources");
		listResource.get(1);
		for (Resource resource : listResource) {
			if(resource.name.contains("Gift"))
			{
				ResourceAPIManager.deleteRequest(PropertiesReader.getServiceURL() + "/resources", resource._id);
			}
		}
	}
	@Then("^I validate that the 'previous' page is displayed on resource table$")
	public void i_validate_that_the_previous_page_is_displayed_on_resource_table() throws Throwable {
		ResourcePage resources = new ResourcePage();
		int previousPage = resources.getTheTotalNumberPage();
		Assert.assertTrue(
				resources.verifyPreviousPage(previousPage));
		//Post condition
				List<Resource> listResource = ResourceAPIManager.getRequest(PropertiesReader.getServiceURL()+"/resources");
				for (Resource resource : listResource) {
					if(resource.name.contains("Gift"))
					{
						ResourceAPIManager.deleteRequest(PropertiesReader.getServiceURL() + "/resources", resource._id);
					}
				}
	}
	@Then("^I validate that thw \"([^\"]*)\" page is displayed$")
	public void i_validate_that_thw_page_is_displayed(String NumberPage) throws Throwable {
		List<Resource> listResource = ResourceAPIManager.getRequest(PropertiesReader.getServiceURL()+"/resources");
		int resourceIndex = ((Integer.parseInt(NumberPage) - 1) * 50) + 1;
		ResourcePage resources = new ResourcePage();
		String firstElement = listResource.get(resourceIndex - 1).name;
		Assert.assertTrue(
				resources.verifyTheFirstElementOnThePage(firstElement));
		//Post condition
		for (Resource resource : listResource) {
			if(resource.name.contains("Gift"))
			{
				ResourceAPIManager.deleteRequest(PropertiesReader.getServiceURL() + "/resources", resource._id);
			}
		}
	}
}
