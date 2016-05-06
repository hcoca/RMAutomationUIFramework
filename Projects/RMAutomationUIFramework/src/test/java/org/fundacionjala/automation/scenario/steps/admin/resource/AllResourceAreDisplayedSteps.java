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

import com.mashape.unirest.http.exceptions.UnirestException;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AllResourceAreDisplayedSteps {
	
	AdminPage home;
	ResourcePage resource;
	
	@Given("^I Login to Room Manager application$")
	public void i_Login_to_Room_Manager_application() throws Throwable {
		BrowserManager.openBrowser();
		LoginPage login = new LoginPage();
		home = login
					.setUserName(PropertiesReader.getUserName())
					.setPassword(PropertiesReader.getPassword())
					.clickOnSigInButton()
					.refreshPage();
	}
	@Given("^I add \"([^\"]*)\" resources$")
	public void i_add_resources(String arg1) throws Throwable {
		
		resource = home
				.leftMenu
				.clickOnResourcesButton();
		for (int i = 0; i < Integer.parseInt(arg1); i++) {
			resource
				.clickOnAddButton()
				.setResourceName("resource"+i)
				.setDisplayName("resource"+i)
				.setDescription("resource"+i)
				.selectIcon("fa-folder")
				.clickOnSaveButton();
		}
				
	}

	@When("^I click on resource option$")
	public void i_click_on_resource_option() throws Throwable {
		 resource
		 	.leftMenu
		 	.clickOnIssuesButton()
		 	.clickOnResourcesButton();
	}

	@Then("^I validate that all resources are displayed in resource table$")
	public void i_validate_that_all_resources_are_displayed_in_resource_table() throws Throwable {
		 List<Resource> listResource = ResourceAPIManager.getRequest("http://172.20.208.84:4040/resources");
		 ResourcePage resources = new ResourcePage();
		 Assert.assertTrue(
				 resources.verifyResourcesOnResourceTable(listResource));
		 //Post condition
		 String res[] = {"resource0", "resource1"};
			String idResource = "";
			List<Resource> listResources = ResourceAPIManager.getRequest("http://172.20.208.84:4040/resources");
			for (int i = 0; i < res.length; i++) {
				for (Resource resource : listResources) {
					if(resource.name.equalsIgnoreCase(res[i]))
					{
						idResource = resource._id;
						ResourceAPIManager.deleteRequest("http://172.20.208.84:4040/resources", idResource);
					}
					
				}
			}
	}
	
	@After()
	public void tearDown(Scenario scenario) throws UnirestException {
		
		
	    if (scenario.isFailed()) {
	            final byte[] screenshot = ((TakesScreenshot) BrowserManager.getDriver())
	                        .getScreenshotAs(OutputType.BYTES);
	            scenario.embed(screenshot, "image/png"); 
	    }
	}

}
