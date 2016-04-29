package org.fundacionjala.automation.scenario.steps.admin.resource;

import org.fundacionjala.automation.framework.pages.admin.home.AdminPage;
import org.fundacionjala.automation.framework.pages.admin.login.LoginPage;
import org.fundacionjala.automation.framework.pages.admin.resource.ResourcePage;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.PropertiesReader;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

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
					.clickOnSigInButton();
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
		//(new WebDriverWait(BrowserManager.getDriver(), 10))
		//.until(ExpectedConditions.presenceOfElementLocated(By.xpath("dhkashdk")));
		
	}

	@Then("^I validate that the resource is diplayed in resource page$")
	public void i_validate_that_the_resource_is_diplayed_in_resource_page() throws Throwable {
		Assert.assertTrue(resource
			.verifyResourceExist("nada"));
	}

	@Then("^after that the resource is deleted$")
	public void after_that_the_resource_is_deleted() throws Throwable {
		//BrowserManager.getDriver().quit();
	}

}
