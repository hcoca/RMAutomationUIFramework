package org.fundacionjala.automation.scenario.steps.admin.resourcesassociations;

import org.fundacionjala.automation.framework.pages.admin.conferencerooms.ConferenceRoomsPage;
import org.fundacionjala.automation.framework.pages.admin.conferencerooms.ResourceAssociationsPage;
import org.fundacionjala.automation.framework.pages.admin.home.AdminPage;
import org.fundacionjala.automation.framework.pages.admin.login.LoginPage;
import org.fundacionjala.automation.framework.pages.admin.navigation.LeftMenu;
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
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class RemoveAssociation {
	
	    AdminPage home;
		ResourcePage resource;
		ConferenceRoomsPage conferenceRoom;
		LeftMenu leftMenu;
		ResourceAssociationsPage resourceAssociations;
		
		private String resourceName;
		private Resource resourceToAssociate;
		private String roomToModify;
	
			
	@Given("^I am on the Conference Room page of Room Mnanager$")
	public void i_am_on_the_Conference_Room_page_of_Room_Mnanager() throws Throwable {

	    /*
	     *@Before 
		**/
		resourceToAssociate = ResourceAPIManager
                .postRequest("http://172.20.208.84:4040/resources"
                 , new Resource("Key", "keys", "fa fa-key", "", "Key"));

       resourceName = resourceToAssociate.customName;
		
	   roomToModify = "Room04";
	   BrowserManager.openBrowser();
		
		/*
		 * */
		
		LoginPage login = new LoginPage();
		home = login
					.setUserName(PropertiesReader.getUserName())
					.setPassword(PropertiesReader.getPassword())
					.clickOnSigInButton()
					.refreshPage();	
		
	}

	@Given("^I associate a resource on associations page$")
	public void i_associate_a_resource_on_associations_page() throws Throwable {
		conferenceRoom = home.leftMenu
				.clickOnConferenceRoomsButton()
				.openConfigurationPage(roomToModify)
				.clickOnResourceAssociations()
				.addResource(resourceName)
				.clickOnSave();
		
	}

	@When("^I remove the resource of the associated column$")
	public void i_remove_the_resource_of_the_associated_column() throws Throwable {
		
		conferenceRoom
		   .openConfigurationPage(roomToModify)
		   .clickOnResourceAssociations()
		   .removeResource(resourceName);
		
	}

	@Then("^I see the resource on available resources column$")
	public void i_see_the_resource_on_available_resources_column() throws Throwable {
	
		resourceAssociations = conferenceRoom
				.openConfigurationPage(roomToModify)
				.clickOnResourceAssociations();
		
		Assert.assertFalse(resourceAssociations.isInAssociatedColumn(resourceName),
                "The resource should not be in resource column");
		
		/*
		 * @After
		 * */
		ResourceAPIManager.deleteRequest("http://172.20.208.84:4040/resources", resourceToAssociate._id);
	}
	
}
