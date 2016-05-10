package org.fundacionjala.automation.scenario.steps.admin.resourcesassociations;

import java.util.ArrayList;

import org.fundacionjala.automation.framework.pages.admin.conferencerooms.ConferenceRoomsPage;
import org.fundacionjala.automation.framework.pages.admin.conferencerooms.RoomsResourceAssociationsPage;
import org.fundacionjala.automation.framework.pages.admin.home.AdminPage;
import org.fundacionjala.automation.framework.pages.admin.login.LoginActions;
import org.fundacionjala.automation.framework.utils.api.managers.ResourceAPIManager;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Resource;
import org.testng.Assert;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class OneResourceAssociateToRooms {

	private ConferenceRoomsPage conferenceRoom;
	private RoomsResourceAssociationsPage resourceAssociations;
	private String resourceName;
	private Resource resourceToAssociate;
	private ArrayList<String> rooms;
	private boolean result;
	
	
	@Before("@num#8")
	public void beforeScenario() throws Throwable {

		conferenceRoom = new ConferenceRoomsPage();
		
		resourceToAssociate = ResourceAPIManager.postRequest("http://172.20.208.84:4040/resources"
	                                       , new Resource("Key09", "keys09", "fa fa-key", "", "Key"));
	    resourceName = resourceToAssociate.customName;
	    result = false;
	   
 	   String roomToModify01 = "Room001";
 	   String roomToModify02 = "Room005";
 	   String roomToModify03 = "Room006";
 	   String roomToModify04 = "Room007";
 	   rooms = new ArrayList<String>();
 	   rooms.add(roomToModify01);
 	   rooms.add(roomToModify02);
 	   rooms.add(roomToModify03);
 	   rooms.add(roomToModify04);
 	    
        
	}

	@Given("^I associate one resource to many rooms$")
	public void i_associate_one_resource_to_many_rooms() throws Throwable {
		
		for (int i = 0; i < 4; i++) {
			conferenceRoom
					.openConfigurationPage(rooms.get(i))
					.clickOnResourceAssociations()
					.addResource(resourceName)
					.clickOnSave();		
		}
		
	}

	@When("^I open the pop-up configuration of the each room$")
	public void i_open_the_pop_up_configuration_of_the_each_room() throws Throwable {
		
		for (int i = 0; i < 4; i++) {
			resourceAssociations = 
					           conferenceRoom
								  .openConfigurationPage(rooms.get(i))
								  .clickOnResourceAssociations();
									
			result = resourceAssociations.isInAssociatedColumn(resourceName);
			         resourceAssociations.clickOnSave();
		}
	}

	@Then("^I see the resource associate in each room that was modified$")
	public void i_see_the_resource_associate_in_each_room_that_was_modified() throws Throwable {
		
		Assert.assertTrue(result, "All roooms have the resource associated");
		
	}

	@After("@num#8")
	public void afterScenario() throws Throwable {
		ResourceAPIManager.deleteRequest("http://172.20.208.84:4040/resources", resourceToAssociate._id);
	
	}
}
