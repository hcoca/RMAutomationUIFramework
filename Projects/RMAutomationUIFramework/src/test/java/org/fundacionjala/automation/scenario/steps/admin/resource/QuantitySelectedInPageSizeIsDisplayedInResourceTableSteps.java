package org.fundacionjala.automation.scenario.steps.admin.resource;

import java.util.List;
import org.fundacionjala.automation.framework.pages.admin.home.AdminPage;
import org.fundacionjala.automation.framework.pages.admin.resource.ResourcePage;
import org.fundacionjala.automation.framework.utils.api.managers.ResourceAPIManager;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Resource;
import org.testng.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class QuantitySelectedInPageSizeIsDisplayedInResourceTableSteps {
	ResourcePage resource = new ResourcePage();
	String resourceName = "Editor";
	int quantity = 0;
	@Given("^I have atleast \"([^\"]*)\" resources created$")
	public void i_have_atleast_resources_created(String arg1) throws Throwable {
		quantity = Integer.parseInt(arg1);
		for (int i = 0; i < quantity; i++) {
			Resource resource = new Resource(resourceName+i, resourceName+i, "fa fa-edit", "", resourceName+i);
			ResourceAPIManager.postRequest("http://172.20.208.84:4040/resources", resource);
		}
		
	}

	@When("^I select a option \"([^\"]*)\" on page size option$")
	public void i_select_a_option_on_page_size_option(String arg1) throws Throwable {
		AdminPage home = new AdminPage();
		resource = home
				.leftMenu
				.clickOnResourcesButton()
				.selectPageSizeOnDropDown(arg1);
	}

	@Then("^Validate that the resource table size is same than the option \"([^\"]*)\" selected$")
	public void validate_that_the_resource_table_size_is_same_than_the_option_selected(String arg1) throws Throwable {
		Assert.assertTrue(
				resource.verifyNumberOfResources(Integer.parseInt(arg1))
				);
		//Post condition
		String idResource = "";
		List<Resource> listResources = ResourceAPIManager.getRequest("http://172.20.208.84:4040/resources");
		for (int i = 0; i < quantity; i++) {
			for (Resource resource : listResources) {
				if(resource.name.equalsIgnoreCase(resourceName + i))
				{
					idResource = resource._id;
					ResourceAPIManager.deleteRequest("http://172.20.208.84:4040/resources", idResource);
				}
				
			}
		}
	}
}
