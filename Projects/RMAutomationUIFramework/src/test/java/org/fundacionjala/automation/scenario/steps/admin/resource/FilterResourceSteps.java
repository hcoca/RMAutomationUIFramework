package org.fundacionjala.automation.scenario.steps.admin.resource;

import org.fundacionjala.automation.framework.pages.admin.login.LoginPage;
import org.fundacionjala.automation.framework.pages.admin.resource.ResourcePage;
import org.fundacionjala.automation.framework.utils.api.managers.ResourceAPIManager;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Resource;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.testng.Assert;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class FilterResourceSteps {
	private ResourcePage resourcePage = new ResourcePage();
	private Resource resource;
	@Given("^I have a resource$")
	public void i_have_a_resource() throws Throwable {
		resource = ResourceAPIManager.postRequest("http://172.20.208.84:4040/resources", new Resource("Fire", "Fire-Ball", "fa fa-fire", "", "You are on fire"));	
	}

	@When("^I search the resource in resources page$")
	public void i_search_the_resource_in_resources_page() throws Throwable {
		BrowserManager.openBrowser();
	    LoginPage loginPage = new LoginPage();
	    
	    loginPage
	    .clickOnSigInButton()
	    .refreshPage()
		.leftMenu
		.clickOnIssuesButton()
		.clickOnResourcesButton()
	    .setResourceFilter("Fire");
	}
	
	@Then("^I validate the resource is displayed$")
	public void i_validate_the_resource_is_displayed() throws Throwable {
		Assert.assertTrue(resourcePage.verifyResourceExist("Fire"));
	}

	@Then("^then the resource is deleted$")
	public void then_the_resource_is_deleted() throws Throwable {
		ResourceAPIManager.deleteRequest("http://172.20.208.84:4040/resources", resource._id);
	}
}
