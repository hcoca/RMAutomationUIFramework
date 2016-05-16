package org.fundacionjala.automation.scenario.steps.admin.resource;

import org.fundacionjala.automation.framework.maps.admin.emailserver.EmailServerMap;
import org.fundacionjala.automation.framework.pages.admin.home.AdminPage;
import org.fundacionjala.automation.framework.pages.admin.login.LoginActions;
import org.fundacionjala.automation.framework.pages.admin.login.LoginPage;
import org.fundacionjala.automation.framework.pages.admin.resource.ResourcePage;
import org.fundacionjala.automation.framework.pages.tablet.home.HomePage;
import org.fundacionjala.automation.framework.utils.api.managers.ResourceAPIManager;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Resource;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.ExplicitWait;
import org.fundacionjala.automation.framework.utils.common.PropertiesReader;
import org.openqa.selenium.By;

import cucumber.api.java.en.Given;

public class ResourceGivenSteps {
    @Given("^I as Administrator Login to Room Manager$")
    public void i_as_Administrator_Login_to_Room_Manager() throws Throwable {
    	
    	
    	AdminPage home = 
            	LoginActions
 	    		.ExecuteLogin();
    	
    	home.leftMenu.clickOnConferenceRoomsButton();
 	  
		
    }

    @Given("^I have a resource created with the name \"([^\"]*)\", display name \"([^\"]*)\", description \"([^\"]*)\" and icon \"([^\"]*)\"$")
    public void i_have_a_resource_created(
	    String name, String displayname, String description, String icon)
	    throws Throwable {
	Resource resource = new Resource(name, displayname, "fa " + icon, "",
					description);
	ResourceAPIManager.postRequest(PropertiesReader.getServiceURL()
					+ "/resources", resource);
    }

    @Given("^I am in the first page$")
    public void i_am_in_the_first_page() throws Throwable {
	AdminPage home = new AdminPage();
		home
		.leftMenu
		.clickOnResourcesButton()
		.clickOnFirstPageButton();
    }

    @Given("^I am in the last page$")
    public void i_am_in_the_last_page() throws Throwable {
	AdminPage home = new AdminPage();
		home
		.leftMenu
		.clickOnResourcesButton()
		.clickOnLastPageButton();
    }

    @Given("^I add \"([^\"]*)\" resources$")
    public void i_add_resources(String numberOfResources) throws Throwable {
	AdminPage home = new AdminPage();
	ResourcePage resource = new ResourcePage();
	resource = home
			.leftMenu
			.clickOnResourcesButton();
	for (int i = 0; i < Integer.parseInt(numberOfResources); i++) {
            resource
            .clickOnAddButton()
            .setResourceName("Gift" + i)
            .setDisplayName("Gift" + i)
            .setDescription("Gift" + i)
            .clickOnSaveButton();
	}
    }
    
    @Given("^I have atleast \"([^\"]*)\" created resources$")
    public void i_have_created_resources(String resourcesQuantity)throws Throwable {
	int quantity = Integer.parseInt(resourcesQuantity);
	for (int i = 0; i < quantity; i++) {
		Resource resource = new Resource("Gift" + i,
						 "Gift" + i,
						 "fa fa-gift", "", 
						 "Gift" + i);
		ResourceAPIManager.postRequest(
			PropertiesReader.getServiceURL()
				+ "/resources", resource);
	}
    }
}
