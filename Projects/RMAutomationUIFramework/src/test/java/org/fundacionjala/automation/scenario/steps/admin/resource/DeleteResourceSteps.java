package org.fundacionjala.automation.scenario.steps.admin.resource;

import org.fundacionjala.automation.framework.pages.admin.home.AdminPage;
import org.fundacionjala.automation.framework.pages.admin.login.LoginPage;
import org.fundacionjala.automation.framework.pages.admin.resource.RemoveResourcePage;
import org.fundacionjala.automation.framework.pages.admin.resource.ResourcePage;
import org.fundacionjala.automation.framework.utils.api.managers.ResourceAPIManager;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Resource;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class DeleteResourceSteps {

	@Given("^I have a resource created with the name \"([^\"]*)\"$")
	public void i_have_a_resource_created_with_the_name(String arg1) throws Throwable {
		Resource resource = new Resource(arg1, arg1, "fa fa-fire", "", arg1);
		ResourceAPIManager.postRequest("http://172.20.208.84:4040/resources", resource);
	}

	@When("^I delete the resource with the name \"([^\"]*)\"$")
	public void i_delete_the_resource_with_the_name(String arg1) throws Throwable {
	    BrowserManager.openBrowser();
		LoginPage login = new LoginPage();
		
		login
			.setUserName("SamuelSahonero")
			.setPassword("Control123!")
			.clickOnSigInButton()
			.refreshPage()
			.leftMenu
			.clickOnIssuesButton()
			.clickOnResourcesButton()
	   		.selectResource(arg1)
	   		.clickOnRemoveButton();
	}

	@When("^Confirm the changes$")
	public void confirm_the_changes() throws Throwable {
	    RemoveResourcePage removeResource = new RemoveResourcePage();
	   
	    removeResource.clickOnRemoveButton();
	}

	@Then("^I verify that the resource with the name \"([^\"]*)\" has been deleted$")
	public void i_verify_that_the_resource_with_the_name_has_been_deleted(String arg1) throws Throwable {
		AdminPage admin = new AdminPage();
		
		ResourcePage resource = admin
									.leftMenu
									.clickOnIssuesButton()
									.clickOnResourcesButton();
		
		boolean isResourcePresent = resource.verifyResourceExist(arg1);
		
		Assert.assertFalse(isResourcePresent);
	}
	
	@After()
	public void tearDown(Scenario scenario) {
	    if (scenario.isFailed()) {
	            final byte[] screenshot = ((TakesScreenshot) BrowserManager.getDriver())
	                        .getScreenshotAs(OutputType.BYTES);
	            scenario.embed(screenshot, "image/png"); 
	    }
	}
}
