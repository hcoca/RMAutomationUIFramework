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

public class CreateResourceSteps {
	AdminPage home;
	ResourcePage resource;
	String resourceName = "";
	
	@Given("^I Login to Room Manager$")
	public void i_Login_to_Room_Manager() throws Throwable {
		BrowserManager.openBrowser();
		LoginPage login = new LoginPage();
		home = login
					.setUserName(PropertiesReader.getUserName())
					.setPassword(PropertiesReader.getPassword())
					.clickOnSigInButton()
					.refreshPage();
	}

	@When("^I Create a new resource with name \"([^\"]*)\", with display name \"([^\"]*)\", with description \"([^\"]*)\" and icon \"([^\"]*)\"$")
	public void i_Create_a_new_resource_with_name_with_display_name_with_description_and_icon(String arg1, String arg2, String arg3, String arg4) throws Throwable {
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

	@Then("^I validate that the resource with name \"([^\"]*)\" is diplayed in resource page$")
	public void i_validate_that_the_resource_with_name_is_diplayed_in_resource_page(String arg1) throws Throwable {
		
		Assert.assertTrue(resource
				.verifyResourceExist(arg1));
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
