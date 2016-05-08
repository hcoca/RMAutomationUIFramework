package org.fundacionjala.automation.scenario.steps.admin.resourcesassociations;


import java.util.ArrayList;
import java.util.List;

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
import org.testng.Assert;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AssociateManyResources {
	
	AdminPage home;
	ResourcePage resource;
	ConferenceRoomsPage conferenceRoom;
	LeftMenu leftMenu;
	ResourceAssociationsPage resourceAssociations;
	
	private String roomToModify;
	private ArrayList<Resource> resourcesToAssociate;
    private int numResources;
	
	
	@Given("^I am on the Conferences Rooms page of the Room Mananager$")
	public void i_am_on_the_Conferences_Rooms_page_of_the_Room_Mananager() throws Throwable {
		
		/*
		 * Before
		 * */
			resourcesToAssociate = new ArrayList<Resource>();
			numResources = 3; 
					
			for (int i = 0; i < numResources; i++) {
				Resource resource = ResourceAPIManager
		                                  .postRequest("http://172.20.208.84:4040/resources"
		                                                ,new Resource("Key_"+i, "key_"+i, "fa fa-key", "", "Key"));
				resourcesToAssociate.add(resource);
			}
		   
			   roomToModify = "Room01";
			   BrowserManager.openBrowser();
		/*
		 */
		
		LoginPage login = new LoginPage();
		home = login
					.setUserName(PropertiesReader.getUserName())
					.setPassword(PropertiesReader.getPassword())
					.clickOnSigInButton()
					.refreshPage();	
	}

	@Given("^I associate many resources to one room$")
	public void i_associate_many_resources_to_one_room() throws Throwable {
		    resourceAssociations = home.leftMenu
				.clickOnConferenceRoomsButton()
				.openConfigurationPage(roomToModify)
				.clickOnResourceAssociations();
		
		for (int i = 0; i < resourcesToAssociate.size(); i++) {
			resourceAssociations.addResource(resourcesToAssociate.get(i).customName);
		}			
		conferenceRoom = resourceAssociations.clickOnSave();
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
	}


}
