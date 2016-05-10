package org.fundacionjala.automation.scenario.steps.admin.resource;

import java.util.List;

import org.fundacionjala.automation.framework.pages.admin.home.AdminPage;
import org.fundacionjala.automation.framework.pages.admin.login.LoginPage;
import org.fundacionjala.automation.framework.pages.admin.resource.RemoveResourcePage;
import org.fundacionjala.automation.framework.pages.admin.resource.ResourceInfoPage;
import org.fundacionjala.automation.framework.pages.admin.resource.ResourcePage;
import org.fundacionjala.automation.framework.utils.api.managers.ResourceAPIManager;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Resource;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;

import cucumber.api.java.en.When;

public class ResourceWhenSteps {
	@When("^I Create a new resource with name \"([^\"]*)\", display name \"([^\"]*)\", description \"([^\"]*)\" and icon \"([^\"]*)\"$")
	public void i_Create_a_new_resource_with_name_display_name_description_and_icon(String arg1, String arg2, String arg3, String arg4) throws Throwable {
		AdminPage home = new AdminPage();
		home
			.leftMenu
			.clickOnResourcesButton()
			.clickOnAddButton()
			.setResourceName(arg1)
			.setDisplayName(arg2)
			.setDescription(arg3)
			.selectIcon(arg4)
			.clickOnSaveButton();
	}
	@When("^I delete the resource with the name \"([^\"]*)\"$")
	public void i_delete_the_resource_with_the_name(String arg1) throws Throwable {
	    BrowserManager.openBrowser();
	    AdminPage home = new AdminPage();
		home
			.leftMenu
			.clickOnResourcesButton()
	   		.selectResource(arg1)
	   		.clickOnRemoveButton();
	}

	@When("^Confirm the changes$")
	public void confirm_the_changes() throws Throwable {
	    RemoveResourcePage removeResource = new RemoveResourcePage();
	   
	    removeResource.clickOnRemoveButton();
	}
	@When("^I search the resource \"([^\"]*)\" in resources page$")
	public void i_search_the_resource_in_resources_page(String resourceName) throws Throwable {
		AdminPage home = new AdminPage();
		    home
			.leftMenu
			.clickOnIssuesButton()
			.clickOnResourcesButton()
		    .setResourceFilter(resourceName);
	}
	@When("^I modify the \"([^\"]*)\" field with value \"([^\"]*)\" in the resource \"([^\"]*)\"$")
	public void i_modify_the_field_with_value_in_the_resource(String name, String value, String resourceName) throws Throwable {
		AdminPage home = new AdminPage();
		ResourcePage resourcePage = new ResourcePage();
		ResourceInfoPage info = new ResourceInfoPage();
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
		ResourceInfoPage info = new ResourceInfoPage();
		info.clickOnSaveButton();
	}
	////onyl for me

	@When("^I delete the resource \"([^\"]*)\"$")
	public void i_delete_the_resource(String arg1) throws Throwable {
		String idResource = "";
		List<Resource> listResources = ResourceAPIManager.getRequest("http://172.20.208.84:4040/resources");
		for (int i = 0; i < 50; i++) {
			for (Resource resource : listResources) {
				if(resource.name.equalsIgnoreCase(arg1 + i))
				{
					idResource = resource._id;
					ResourceAPIManager.deleteRequest("http://172.20.208.84:4040/resources", idResource);
				}
				
			}
		}
	}
}
