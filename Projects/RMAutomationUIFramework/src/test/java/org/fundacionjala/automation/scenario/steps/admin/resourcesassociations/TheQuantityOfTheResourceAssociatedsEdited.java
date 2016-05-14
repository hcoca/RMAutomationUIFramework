package org.fundacionjala.automation.scenario.steps.admin.resourcesassociations;

import org.fundacionjala.automation.framework.pages.admin.conferencerooms.ConferenceRoomsPage;
import org.testng.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TheQuantityOfTheResourceAssociatedsEdited {
	
	private String              roomToModify, resourceName;
	private ConferenceRoomsPage conferenceRoom;
	
	
	@Given("^I associate a resource on resources associattion page$")
	public void i_associate_a_resource_on_resources_associattion_page() throws Throwable {
		
		conferenceRoom = new ConferenceRoomsPage();
		resourceName = "resourceassoc03";
		roomToModify = conferenceRoom.getRandomRoom();
		
		conferenceRoom
				.openConfigurationPage(roomToModify)
				.clickOnResourceAssociations()
				.addResource(resourceName)
				.clickOnSave();
	}
	
	
	@When("^I modify the quantity of the associated resource to \"([^\"]*)\"$")
	public void i_modify_the_quantity_of_the_associated_resource_to(String quantity) throws Throwable {
		conferenceRoom
				.openConfigurationPage(roomToModify)
				.clickOnResourceAssociations()
				.editQuantityOfResourceAssociated(resourceName, quantity)
				.clickOnSave();
	}

	@Then("^I can see that the quantity \"([^\"]*)\" is displayed$")
	public void i_can_see_that_the_quantity_is_displayed(String quantity) throws Throwable {
		Assert.assertTrue(conferenceRoom
				  .openConfigurationPage(roomToModify)
				  .clickOnResourceAssociations()
				  .hasTheQuantity(resourceName, quantity));
		
	}
}
