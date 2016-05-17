package org.fundacionjala.automation.scenario.steps.admin.resourcesassociations;

import org.fundacionjala.automation.framework.pages.admin.conferencerooms.ConferenceRoomsPage;
import org.fundacionjala.automation.framework.pages.admin.home.AdminPage;
import org.fundacionjala.automation.framework.pages.admin.resource.ResourceAssociationsPage;
import org.fundacionjala.automation.framework.pages.admin.resource.ResourcePage;
import org.testng.Assert;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TheQuantityOfAssociatedResourceIsDisplayedOnQuantitColumn {

	private String resourceName, roomToModify;
        private AdminPage home;
        private ResourcePage resourcePage;
        private ConferenceRoomsPage conferenceRoom;
	private ResourceAssociationsPage resourceAssociationsPage;
	
   
	@Given("^I associate a resource with quantity \"([^\"]*)\"$")
	public void i_associate_a_resource_with_quantity(String qty) throws Throwable {
		conferenceRoom = new ConferenceRoomsPage();
		resourceName = "resourceassoc01";
		roomToModify = conferenceRoom.getRandomRoom();
		
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
	
}
