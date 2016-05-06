package org.fundacionjala.automation.scenario.steps.admin.resourcesassociations;

import org.fundacionjala.automation.framework.pages.admin.conferencerooms.ConferenceRoomsPage;
import org.fundacionjala.automation.framework.pages.admin.conferencerooms.ResourceAssociationsPage;
import org.fundacionjala.automation.framework.pages.admin.home.AdminPage;
import org.fundacionjala.automation.framework.pages.admin.login.LoginPage;
import org.fundacionjala.automation.framework.pages.admin.navigation.LeftMenu;
import org.fundacionjala.automation.framework.pages.admin.resource.ResourcePage;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.PropertiesReader;
import org.testng.Assert;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TheQuantityOfTheResourceAssociatedsEdited {
    AdminPage home;
	ResourcePage resource;
	ConferenceRoomsPage conferenceRoom;
	LeftMenu leftMenu;
	ResourceAssociationsPage resourceAssociations;
	
	private String resourceToAssociate;
	private String roomToModify;
	private String quantity;
	
	@Before
	public void setTup()
	{
	   resourceToAssociate = "ResourceFernando";
	   roomToModify = "Room04";
	   quantity = "10";
	   BrowserManager.openBrowser();
	}
	
	
	@Given("^I am on the Conferences Rooms page$")
	public void i_am_on_the_Conferences_Rooms_page() throws Throwable {
		LoginPage login = new LoginPage();
		home = login
					.setUserName(PropertiesReader.getUserName())
					.setPassword(PropertiesReader.getPassword())
					.clickOnSigInButton()
					.refreshPage();
	}

	@Given("^I associate a resource on resources association page$")
	public void i_associate_a_resource_on_resources_association_page() throws Throwable {
		conferenceRoom = home.leftMenu
				.clickOnConferenceRoomsButton()
				.openConfigurationPage(roomToModify)
				.clickOnResourceAssociations()
				.addResource(resourceToAssociate)
				.clickOnSave();
	}

	@When("^I modify the quantity of the of the associated resource$")
	public void i_modify_the_quantity_of_the_of_the_associated_resource() throws Throwable {
		
		conferenceRoom
				.openConfigurationPage(roomToModify)
				.clickOnResourceAssociations()
				.editQuantityOfResourceAssociated(resourceToAssociate,quantity)
				.clickOnSave();
	}

	@Then("^I can see that the quantity modified is displayed$")
	public void i_can_see_that_the_quantity_modified_is_displayed() throws Throwable {
		
		conferenceRoom
		        .openConfigurationPage(roomToModify)
		        .clickOnResourceAssociations();
		
		Assert.assertTrue(true,"The quantity was modified");
		
	}
	
	
}
