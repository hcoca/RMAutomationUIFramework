package org.fundacionjala.automation.scenario.steps.admin.resource;

import java.util.List;

import org.fundacionjala.automation.framework.pages.admin.home.AdminPage;
import org.fundacionjala.automation.framework.pages.admin.login.LoginPage;
import org.fundacionjala.automation.framework.pages.admin.resource.ResourcePage;
import org.fundacionjala.automation.framework.utils.api.managers.ResourceAPIManager;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Resource;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.PropertiesReader;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CreateResourceSteps {
	AdminPage home;
	ResourcePage resource;

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

	@When("^I Create a new resource$")
	public void i_Create_a_new_resource() throws Throwable {
		resource = home
			.leftMenu
			.clickOnResourcesButton()
			.clickOnAddButton()
			.setResourceName("nada")
			.setDisplayName("nada")
			.setDescription("New Computer")
			.selectIcon("fa-eye")
			.clickOnSaveButton();
	}

	@Then("^I validate that the resource is diplayed in resource page$")
	public void i_validate_that_the_resource_is_diplayed_in_resource_page() throws Throwable {
		home.refreshPage();
		home
		.leftMenu
		.clickOnIssuesButton()
		.clickOnResourcesButton();
		
		Assert.assertTrue(resource
			.verifyResourceExist("nada"));
	}

	@Then("^after that the resource is deleted$")
	public void after_that_the_resource_is_deleted() throws Throwable {
		String idResource = "";
		List<Resource> listResource = ResourceAPIManager.getRequest("http://172.20.208.84:4040/resources");
		for (Resource resource : listResource) {
			if(resource.name.equalsIgnoreCase("nada"))
			{
				idResource = resource._id;
			}
		}
		ResourceAPIManager.deleteRequest("http://172.20.208.84:4040/resources", idResource);
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
