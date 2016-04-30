package org.fundacionjala.automation.scenario.steps.admin.resource;

import java.util.List;

import org.fundacionjala.automation.framework.pages.admin.home.AdminPage;
import org.fundacionjala.automation.framework.pages.admin.login.LoginPage;
import org.fundacionjala.automation.framework.pages.admin.resource.ResourceInfoPage;
import org.fundacionjala.automation.framework.pages.admin.resource.ResourcePage;
import org.fundacionjala.automation.framework.utils.api.managers.ResourceAPIManager;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Resource;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.PropertiesReader;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class UpdateResourceSteps {
	private ResourcePage resourcePage;	
	private ResourceInfoPage info;
	private AdminPage home;
	private String resourceName;
	private Resource resource;
	
	@Given("^I have a resource created with name \"([^\"]*)\"$")
	public void i_have_a_resource_created(String resourceNameInput) throws Throwable {
		resourceName = resourceNameInput;
		resource = ResourceAPIManager.postRequest("http://172.20.208.84:4040/resources", new Resource(resourceName, resourceName, "fa fa-desktop", "", resourceName));
		BrowserManager.openBrowser();
		LoginPage login = new LoginPage();
		login.setUserName(PropertiesReader.getUserName())
			.setPassword(PropertiesReader.getPassword())
			.clickOnSigInButton()
			.refreshPage();
		
	}

	@When("^I modify the \"([^\"]*)\" field with value \"([^\"]*)\"$")
	public void i_modify_the_name_field_with_value(String name, String value) throws Throwable {
		home = new AdminPage();
		resourcePage = home
						.leftMenu
						.clickOnIssuesButton()
						.clickOnResourcesButton();
		
		info = resourcePage.doubleClickOnResource(resourceName);
		switch(name.charAt(0))
		{
		case 'n':
			info.setResourceName(value);
			break;
		case 'd':
			info.setDisplayName(value);
			break;
		case 'i':
			info.selectIcon(value);
			break;
		}
	}

	@When("^I save the modifications$")
	public void i_save_the_modifications() throws Throwable {
		info.clickOnSaveButton();
	}
	@Then("^the resource is modified according the changes \\(\"([^\"]*)\" field with value \"([^\"]*)\"\\)$")
	public void the_resource_is_modified_according_the_changes(String name, String value) throws Throwable {
		resourcePage =	(new AdminPage())
				.leftMenu
				.clickOnIssuesButton()
				.clickOnResourcesButton();
		
		Assert.assertTrue(resourcePage.verifyResourceModifiedByField(resourceName,name, value)); 
		
		/*PostCondition - remove resource*/
		ResourceAPIManager.deleteRequest("http://172.20.208.84:4040/resources", resource._id);
		home.refreshPage();
		home.leftMenu
			.clickOnIssuesButton()
			.clickOnResourcesButton();
		BrowserManager.getDriver().quit();
	}
}
