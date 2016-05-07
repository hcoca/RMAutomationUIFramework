package org.fundacionjala.automation.scenario.steps.admin.resource;

import java.util.List;

import org.fundacionjala.automation.framework.pages.admin.home.AdminPage;
import org.fundacionjala.automation.framework.pages.admin.login.LoginPage;
import org.fundacionjala.automation.framework.pages.admin.resource.ResourcePage;
import org.fundacionjala.automation.framework.utils.api.managers.ResourceAPIManager;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Resource;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.PropertiesReader;
import org.testng.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TotalItemsIsDisplayedSteps {
	AdminPage home;
	ResourcePage resource;
	
	@Given("^I Login to RoomManager APP$")
	public void i_Login_to_RoomManager_APP() throws Throwable {
		BrowserManager.openBrowser();
		LoginPage login = new LoginPage();
		home = login
					.setUserName(PropertiesReader.getUserName())
					.setPassword(PropertiesReader.getPassword())
					.clickOnSigInButton()
					.refreshPage();
	}

	@When("^I create \"([^\"]*)\" resources$")
	public void i_create_resources(String arg1) throws Throwable {
		resource = home
				.leftMenu
				.clickOnResourcesButton();
		for (int i = 0; i < Integer.parseInt(arg1); i++) {
			resource
				.clickOnAddButton()
				.setResourceName("resource" + i)
				.setDisplayName("resource" + i)
				.setDescription("resource" + i)
				.selectIcon("fa-folder")
				.clickOnSaveButton();
		}
	}

	@Then("^I validate that total resources created are displayed in resource table$")
	public void i_validate_that_total_resources_created_are_displayed_in_resource_table() throws Throwable {
		 List<Resource> listResource = ResourceAPIManager.getRequest("http://172.20.208.84:4040/resources");
		 int totalItems = listResource.size();
		 Assert.assertTrue(
				 resource.verifyTotalItems(totalItems)
				 );
		 
		 //Post condition
		 String idResource = "";
			List<Resource> listResources = ResourceAPIManager.getRequest("http://172.20.208.84:4040/resources");
			for (Resource resource : listResources) {
				if(resource.name.equalsIgnoreCase("resource0"))
				{
					idResource = resource._id;
				}
			}
			ResourceAPIManager.deleteRequest("http://172.20.208.84:4040/resources", idResource);
			BrowserManager.getDriver().quit();
	}
}
