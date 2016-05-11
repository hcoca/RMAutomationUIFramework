package org.fundacionjala.automation.scenario.steps.admin.resourcesassociations;

import org.fundacionjala.automation.framework.pages.admin.conferencerooms.ConferenceRoomsPage;
import org.fundacionjala.automation.framework.pages.admin.conferencerooms.RoomsResourceAssociationsPage;
import org.fundacionjala.automation.framework.pages.admin.resource.IconResources;
import org.fundacionjala.automation.framework.pages.admin.resource.ResourcesActions;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Resource;
import org.testng.Assert;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AssociateAResourceWithRoomDisabledSteps {
	
	private String resourceName, roomToModify;
	private Resource resourceToAssociate;
	private ConferenceRoomsPage conferenceRoom;
	private RoomsResourceAssociationsPage resourceAssociations;
	
	
	@Before("@scenario#5")
	public void beforeScenario() throws Throwable {

		conferenceRoom = new ConferenceRoomsPage();
		resourceToAssociate = ResourcesActions.createResourceByAPI("desktop", 
				                                           IconResources.DESKTOP, 
				                                           "monitorOfDesktop");
                resourceName = resourceToAssociate.customName;
                roomToModify = conferenceRoom.getRandomRoom();
	}

	@Given("^I have a resource associated$")
	public void i_have_a_resource_associated() throws Throwable {
	    
		conferenceRoom
			      .openConfigurationPage(roomToModify)
			      .clickOnResourceAssociations()
			      .addResource(resourceName)
			      .clickOnSave();
		
	}
	
	@When("^I disable the room$")
	public void i_disable_the_room() throws Throwable {
	    
	    conferenceRoom.disableRoom(roomToModify);
	}

	@When("^when I open the pop-up configuration of the room disabled$")
	public void when_I_open_the_pop_up_configuration_of_the_room_disabled() throws Throwable {
	    
		resourceAssociations = conferenceRoom
						     .openConfigurationPage(roomToModify)
						     .clickOnResourceAssociations();
	}

	@Then("^I see the resource associated in Associated column$")
	public void i_see_the_resource_associated_in_Associated_column() throws Throwable {
		
	   Assert.assertTrue(resourceAssociations.isInAssociatedColumn(resourceName),
			     "the resource should be in resource column");
	   
	}
	@After("@scenario#5")
	public void afterScenario() throws Throwable {
	    
		ResourcesActions.deleteResourceByAPI(resourceToAssociate);
	}
	
}
