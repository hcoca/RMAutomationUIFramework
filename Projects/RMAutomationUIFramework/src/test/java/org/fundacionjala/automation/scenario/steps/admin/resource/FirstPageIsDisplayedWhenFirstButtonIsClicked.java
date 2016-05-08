package org.fundacionjala.automation.scenario.steps.admin.resource;

import java.util.List;

import org.fundacionjala.automation.framework.pages.admin.home.AdminPage;
import org.fundacionjala.automation.framework.pages.admin.resource.ResourcePage;
import org.fundacionjala.automation.framework.utils.api.managers.ResourceAPIManager;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Resource;
import org.junit.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class FirstPageIsDisplayedWhenFirstButtonIsClicked {
		ResourcePage resource = new ResourcePage();
		String firstPage = "1";
		int quantity = 0;
		String resourceName = "Gift";
	@Given("^I have atleast \"([^\"]*)\" created resources$")
	public void i_have_atleast_created_resources(String arg1) throws Throwable {
		quantity = Integer.parseInt(arg1);
		for (int i = 0; i < quantity; i++) {
			Resource resource = new Resource(resourceName+i, resourceName+i, "fa fa-gift", "", resourceName+i);
			ResourceAPIManager.postRequest("http://172.20.208.84:4040/resources", resource);
		}
	}
	@When("^I Clicked on First button on resource table$")
	public void i_Clicked_on_First_button_on_resource_table() throws Throwable {
		AdminPage home = new AdminPage();
		resource = home
						.leftMenu
						.clickOnResourcesButton()
						.clickOnFirstPageButton();
	}

	@Then("^I validate that the first page is displayed on resource table$")
	public void i_validate_that_the_first_page_is_displayed_on_resource_table() throws Throwable {
	    Assert.assertTrue(
			resource
		    	.verifyTheFirstPage(firstPage));
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
