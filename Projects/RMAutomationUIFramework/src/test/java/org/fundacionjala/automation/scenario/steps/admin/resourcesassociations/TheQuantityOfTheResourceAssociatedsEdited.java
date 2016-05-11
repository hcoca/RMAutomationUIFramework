package org.fundacionjala.automation.scenario.steps.admin.resourcesassociations;

import org.fundacionjala.automation.framework.pages.admin.conferencerooms.ConferenceRoomsPage;
import org.fundacionjala.automation.framework.pages.admin.home.AdminPage;
import org.fundacionjala.automation.framework.pages.admin.login.LoginActions;
import org.fundacionjala.automation.framework.pages.admin.resource.IconResources;
import org.fundacionjala.automation.framework.pages.admin.resource.ResourcesActions;
import org.fundacionjala.automation.framework.utils.api.managers.ResourceAPIManager;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Resource;
import org.testng.Assert;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TheQuantityOfTheResourceAssociatedsEdited {
	
	private String              roomToModify, quantity, resourceName;
	private Resource            resourceToAssociate;
	private ConferenceRoomsPage conferenceRoom;
	
	
	@Before("@scenario#3")
	public void beforeScenario() throws Throwable {
		
		conferenceRoom = new ConferenceRoomsPage();
		resourceToAssociate = ResourcesActions.createResourceByAPI("New_Key", 
				                                              IconResources.KEY,
				                                              "this key is in the room");
        resourceName = resourceToAssociate.customName;
        roomToModify = conferenceRoom.getRandomRoom();
 	    quantity = "10";
 	   
	}
	
	@Given("^I associate a resource on resources associattion page$")
	public void i_associate_a_resource_on_resources_associattion_page() throws Throwable {
		
		conferenceRoom
				.openConfigurationPage(roomToModify)
				.clickOnResourceAssociations()
				.addResource(resourceName)
				.clickOnSave();
	}

	@When("^I modify the quantity of the of the associated resource$")
	public void i_modify_the_quantity_of_the_of_the_associated_resource() throws Throwable {
		
		conferenceRoom
				.openConfigurationPage(roomToModify)
				.clickOnResourceAssociations()
				.editQuantityOfResourceAssociated(resourceName,quantity)
				.clickOnSave();
	}

	@Then("^I can see that the quantity modified is displayed$")
	public void i_can_see_that_the_quantity_modified_is_displayed() throws Throwable {
		
		Assert.assertTrue(conferenceRoom
		                  .openConfigurationPage(roomToModify)
		                  .clickOnResourceAssociations()
		                  .hasTheQuantity(resourceName, quantity));
	}
	
	@After("@scenario#3")
	public void afterScenario() throws Throwable {
		ResourcesActions.deleteResourceByAPI(resourceToAssociate);
	}
	
	
	
}
