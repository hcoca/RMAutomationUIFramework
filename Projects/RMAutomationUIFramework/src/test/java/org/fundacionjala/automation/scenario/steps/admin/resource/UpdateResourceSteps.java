package org.fundacionjala.automation.scenario.steps.admin.resource;

import org.fundacionjala.automation.framework.pages.admin.home.AdminPage;
import org.fundacionjala.automation.framework.pages.admin.login.LoginPage;
import org.fundacionjala.automation.framework.pages.admin.resource.ResourceInfoPage;
import org.fundacionjala.automation.framework.pages.admin.resource.ResourcePage;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.PropertiesReader;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class UpdateResourceSteps {
	ResourcePage resource;	
	ResourceInfoPage info;
	AdminPage home;
	WebDriver driver; 
	String resourceName;
	
	@Given("^I have a resource created with name \"([^\"]*)\"$")
	public void i_have_a_resource_created(String resourceNameInput) throws Throwable {
		resourceName = resourceNameInput;
		
		BrowserManager.openBrowser();
		LoginPage login = new LoginPage();
		login.setUserName(PropertiesReader.getUserName())
			.setPassword(PropertiesReader.getPassword())
			.clickOnSigInButton()
			.leftMenu
			.clickOnIssuesButton()
			.clickOnResourcesButton()
			.clickOnAddButton()
			.setResourceName(resourceName)
			.setDisplayName(resourceName)
			.clickOnSaveButton();
		
	}

	@When("^I modify the \"([^\"]*)\" field with value \"([^\"]*)\"$")
	public void i_modify_the_name_field_with_value(String name, String value) throws Throwable {
		//Refresh
		resource =	(new AdminPage())
					.leftMenu
					.clickOnResourcesButton();
		
		info = resource.doubleClickOnResource(resourceName);
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
		resource =	(new AdminPage())
				.leftMenu
				.clickOnResourcesButton();
		
		Assert.assertTrue(resource.verifyResourceModifiedByField(resourceName,name, value)); 
		driver = BrowserManager.getDriver();
		
		if(name.charAt(0) == 'n')
			resourceName = value;
		resource.selectResource(resourceName)
   				.clickOnRemoveButton()
   				.clickOnRemoveButton();
	}
	

}
