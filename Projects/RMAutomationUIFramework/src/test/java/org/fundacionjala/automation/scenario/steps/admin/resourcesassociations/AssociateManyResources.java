package org.fundacionjala.automation.scenario.steps.admin.resourcesassociations;


import java.util.ArrayList;

import org.fundacionjala.automation.framework.pages.admin.conferencerooms.ConferenceRoomsPage;
import org.fundacionjala.automation.framework.pages.admin.conferencerooms.RoomsResourceAssociationsPage;
import org.fundacionjala.automation.framework.pages.admin.home.AdminPage;
import org.fundacionjala.automation.framework.pages.admin.login.LoginActions;
import org.fundacionjala.automation.framework.utils.api.managers.ResourceAPIManager;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Resource;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.testng.Assert;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AssociateManyResources {
	
	private ConferenceRoomsPage conferenceRoom;
	private RoomsResourceAssociationsPage resourceAssociations;
	private String roomToModify;
	private ArrayList<Resource> resourcesToAssociate;
    private int numResources;

    
    @Before("@num#7")
	public void beforeScenario() throws Throwable {

		conferenceRoom = new ConferenceRoomsPage();

		resourcesToAssociate = new ArrayList<Resource>();
		numResources = 2; 
				
		for (int i = 0; i < numResources; i++) {
			Resource resource = ResourceAPIManager
	                                  .postRequest("http://172.20.208.84:4040/resources"
	                                   ,new Resource("Key"+i, "key"+i, "fa fa-key", "", "Key"));
			resourcesToAssociate.add(resource);
		}
	   
		   roomToModify = conferenceRoom.getRandomRoom();
	
	}
    

	@Given("^I associate many resources to one room$")
	public void i_associate_many_resources_to_one_room() throws Throwable {
		
		resourceAssociations = conferenceRoom	
				.openConfigurationPage(roomToModify)
				.clickOnResourceAssociations();
		
		for (int i = 0; i < resourcesToAssociate.size(); i++) {
			resourceAssociations.addResource(resourcesToAssociate.get(i).customName);
		}			
		
		    resourceAssociations.clickOnSave();
		
	}

	@When("^I open the pop-up configuration of the room$")
	public void i_open_the_pop_up_configuration_of_the_room() throws Throwable {
		
		conferenceRoom
		         .openConfigurationPage(roomToModify)
		         .clickOnResourceAssociations();
		
	}

	@Then("^I see the all resources associated to this room$")
	public void i_see_the_all_resources_associated_to_this_room() throws Throwable {
		
		for (int i = 0; i < resourcesToAssociate.size(); i++) {
			Assert.assertTrue(resourceAssociations
					           .isInAssociatedColumn(resourcesToAssociate.get(i).customName));
		}
		BrowserManager.getDriver().navigate().refresh();
		
	}
	
	@After("@num#7")
	public void afterScenario() throws Throwable {
		
		for (int i = 0; i < resourcesToAssociate.size(); i++) {
			 ResourceAPIManager.deleteRequest("http://172.20.208.84:4040/resources"
					                                  , resourcesToAssociate.get(i)._id);
		}
		
	}
	
}
