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

public class ResourceCreatedIsDisplayedCorrectlySteps {
	
	AdminPage home;
	ResourcePage resource;
	String resourceName="";
	
	@Given("^I log in to Room Manager app$")
	public void i_log_in_to_Room_Manager_app() throws Throwable {
		BrowserManager.openBrowser();
		LoginPage login = new LoginPage();
		home = login
					.setUserName(PropertiesReader.getUserName())
					.setPassword(PropertiesReader.getPassword())
					.clickOnSigInButton()
					.refreshPage();
	}

	@When("^I create a resource with name \"([^\"]*)\", display name \"([^\"]*)\", description \"([^\"]*)\" and icon \"([^\"]*)\"$")
	public void i_create_a_resource_with_name_display_name_description_and_icon(String arg1, String arg2, String arg3, String arg4) throws Throwable {
		resourceName = arg1;
		resource = home
				.leftMenu
				.clickOnResourcesButton()
				.clickOnAddButton()
				.setResourceName(arg1)
				.setDisplayName(arg2)
				.setDescription(arg3)
				.selectIcon(arg4)
				.clickOnSaveButton();
	}

	@Then("^I validate that resource with \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\" is displayed$")
	public void i_validate_that_resource_with_and_is_displayed(String arg1, String arg2, String arg3) throws Throwable {
	    Assert.assertTrue(resource.verifyResourceDisplayed(arg1, arg2, arg3));
		 //Post condition
		 String idResource = "";
			List<Resource> listResource = ResourceAPIManager.getRequest("http://172.20.208.84:4040/resources");
			for (Resource resource : listResource) {
				if(resource.name.equalsIgnoreCase(resourceName))
				{
					idResource = resource._id;
				}
			}
			ResourceAPIManager.deleteRequest("http://172.20.208.84:4040/resources", idResource);
	}
}
