package org.fundacionjala.automation.scenario.steps.admin.resourcesassociations;

import org.fundacionjala.automation.framework.pages.admin.conferencerooms.ConferenceRoomsPage;
import org.fundacionjala.automation.framework.pages.admin.home.AdminPage;
import org.fundacionjala.automation.framework.pages.admin.login.LoginActions;
import org.fundacionjala.automation.framework.pages.admin.resource.IconResources;
import org.fundacionjala.automation.framework.pages.admin.resource.ResourceAssociationsPage;
import org.fundacionjala.automation.framework.pages.admin.resource.ResourcePage;
import org.fundacionjala.automation.framework.pages.admin.resource.ResourcesActions;
import org.fundacionjala.automation.framework.utils.api.managers.ResourceAPIManager;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Resource;
import org.testng.Assert;

import com.mashape.unirest.http.exceptions.UnirestException;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TheQuantityOfAssociatedResourceIsDisplayedOnQuantitColumn {

	private String                   resourceName, roomToModify;
	private Resource                 resourceToAssociate;
    private AdminPage                home;
    private ResourcePage             resourcePage;
    private ConferenceRoomsPage      conferenceRoom;
	private ResourceAssociationsPage resourceAssociationsPage;
	
	
	@Before("@scenario#1")
	public void beforeScenario() throws Throwable {

		conferenceRoom = new ConferenceRoomsPage();
		resourceToAssociate = ResourcesActions.createResourceByAPI("folder1", IconResources.FOLDER, "folder1");
        resourceName = resourceToAssociate.customName;
        roomToModify = conferenceRoom.getRandomRoom();
	
	}
   
	@Given("^I associate a resource with quantity \"([^\"]*)\"$")
	public void i_associate_a_resource_with_quantity(String qty) throws Throwable {
		
		conferenceRoom
		.openConfigurationPage(roomToModify)
		.clickOnResourceAssociations()
		.addResource(resourceName)
		.editQuantityOfResourceAssociated(resourceName, qty)
		.clickOnSave();
	}

	@When("^I go to resources page$")
	public void i_go_to_resources_page() throws Throwable {
		home = new AdminPage();
		
		resourcePage = 
				 home
				.leftMenu
				.clickOnResourcesButton();
	}

	@When("^open the pop-up configuration of the resource associated$")
	public void open_the_pop_up_configuration_of_the_resource_associated() throws Throwable {
		resourceAssociationsPage =
			resourcePage
			    .doubleClickOnResource(resourceName)
			    .clickOnResourcesAssociation();
	}

	@Then("^I see the quantity \"([^\"]*)\" with the room associated$")
	public void i_see_the_quantity_with_the_room_associated(String qty) throws Throwable {
		Assert.assertTrue(resourceAssociationsPage.isQtyDisplayed(qty)
                          , "The correct quantity should be displayed");

	}
	
	@After("@scenario#1")
	public void afterScenario() throws Throwable {
		ResourcesActions.deleteResourceByAPI(resourceToAssociate);
	}
	
}
