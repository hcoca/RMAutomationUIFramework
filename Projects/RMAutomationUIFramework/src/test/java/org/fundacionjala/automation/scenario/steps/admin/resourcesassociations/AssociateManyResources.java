package org.fundacionjala.automation.scenario.steps.admin.resourcesassociations;
import java.util.ArrayList;
import org.fundacionjala.automation.framework.maps.admin.resource.IconResources;
import org.fundacionjala.automation.framework.pages.admin.conferencerooms.ConferenceRoomsPage;
import org.fundacionjala.automation.framework.pages.admin.conferencerooms.RoomsResourceAssociationsPage;
import org.fundacionjala.automation.framework.pages.admin.resource.ResourcesActions;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Resource;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.testng.Assert;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AssociateManyResources {
	
        private int numResources;
	private String roomToModify;
	private ArrayList<Resource> resourcesToAssociate;
	private ConferenceRoomsPage conferenceRoom;
	private RoomsResourceAssociationsPage resourceAssociations;
        

    
    @Before("@scenario#8")
	public void beforeScenario() throws Throwable {

		conferenceRoom = new ConferenceRoomsPage();
		resourcesToAssociate = new ArrayList<Resource>();
		numResources = 5; 
				
		for (int i = 0; i < numResources; i++) {
			Resource resource = ResourcesActions.createResourceByAPI("resourceassoc" + i, IconResources.KEY, "key");
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
	
}
