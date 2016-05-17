package org.fundacionjala.automation.scenario.steps.admin.resourcesassociations;

import org.fundacionjala.automation.framework.maps.admin.resource.IconResources;
import org.fundacionjala.automation.framework.pages.admin.conferencerooms.ConferenceRoomsPage;
import org.fundacionjala.automation.framework.pages.admin.conferencerooms.RoomsResourceAssociationsPage;
import org.fundacionjala.automation.framework.pages.admin.resource.ResourcesActions;
import org.testng.Assert;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AssociateAResourceWithRoomDisabledSteps {
	
	private String roomToModify;
	private ConferenceRoomsPage conferenceRoom;
	private RoomsResourceAssociationsPage resourceAssociations;
	private String resourceNameCreated;
	
	@Before("@scenario#5")
	public void beforeScenario5() throws Throwable {
		resourceNameCreated = "resourceassoc05";
		ResourcesActions.createResourceByAPI(resourceNameCreated, IconResources.FEMALE, "key003");
		
		conferenceRoom = new ConferenceRoomsPage();
		roomToModify = conferenceRoom.getRandomRoom();
	    conferenceRoom.disableRoom(roomToModify);
	    
	}
	
	
	@Given("^I have one Room disabled$")
	public void i_have_one_Room_disabled() throws Throwable {
		
		
	}

	@Given("^I have a resource associated$")
	public void i_have_a_resource_associated() throws Throwable {
		
	    
		  conferenceRoom
			      .openRoomDisabled(roomToModify)
			      .clickOnResourceAssociations()
			      .addResource(resourceNameCreated)
			      .clickOnSave();
		
	}
	@When("^I open the pop-up configuration of the room disabled$")
	public void i_open_the_pop_up_configuration_of_the_room_disabled() throws Throwable {
	    
	    resourceAssociations = conferenceRoom
		     .openRoomDisabled(roomToModify)
		     .clickOnResourceAssociations();
	}

	@Then("^I see the resource associated in Associated column$")
	public void i_see_the_resource_associated_in_Associated_column() throws Throwable {
		
		 conferenceRoom.enableRoom(roomToModify);
	     Assert.assertTrue(resourceAssociations.isInAssociatedColumn(resourceNameCreated),
			     "the resource should be in resource column");
	   
	}
	
}
