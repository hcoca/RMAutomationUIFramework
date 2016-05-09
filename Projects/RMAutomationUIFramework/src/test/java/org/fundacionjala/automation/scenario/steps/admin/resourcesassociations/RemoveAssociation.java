package org.fundacionjala.automation.scenario.steps.admin.resourcesassociations;

import org.fundacionjala.automation.framework.pages.admin.conferencerooms.ConferenceRoomsPage;
import org.fundacionjala.automation.framework.pages.admin.conferencerooms.RoomsResourceAssociationsPage;
import org.fundacionjala.automation.framework.pages.admin.home.AdminPage;
import org.fundacionjala.automation.framework.pages.admin.login.LoginActions;
import org.fundacionjala.automation.framework.utils.api.managers.ResourceAPIManager;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Resource;
import org.testng.Assert;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class RemoveAssociation {
	
	    private AdminPage home;
		private ConferenceRoomsPage conferenceRoom;
		private RoomsResourceAssociationsPage resourceAssociations;
		private String resourceName;
		private Resource resourceToAssociate;
		private String roomToModify;
	
			
	@Given("^I am on the Conference Room page of Room Mnanager$")
	public void i_am_on_the_Conference_Room_page_of_Room_Mnanager() throws Throwable {

	   
		resourceToAssociate = ResourceAPIManager
                .postRequest("http://172.20.208.84:4040/resources"
                 , new Resource("Key05", "keys05", "fa fa-key", "", "Key"));

       resourceName = resourceToAssociate.customName;
		
	   roomToModify = "Room06";
	  
	   home = LoginActions.ExecuteLogin();
	}

	@Given("^I associate a resource on resources association page$")
	public void i_associate_a_resource_on_resources_association_page() throws Throwable {
		conferenceRoom = home.leftMenu
				.clickOnConferenceRoomsButton()
				.openConfigurationPage(roomToModify)
				.clickOnResourceAssociations()
				.addResource(resourceName)
				.clickOnSave();
	}

	@When("^I remove the resource of the associated column$")
	public void i_remove_the_resource_of_the_associated_column() throws Throwable {
		
		conferenceRoom
		   .openConfigurationPage(roomToModify)
		   .clickOnResourceAssociations()
		   .removeResource(resourceName);
		
	}

	@Then("^I see the resource on available resources column$")
	public void i_see_the_resource_on_available_resources_column() throws Throwable {
	
		resourceAssociations = conferenceRoom
				.openConfigurationPage(roomToModify)
				.clickOnResourceAssociations();
		
		Assert.assertTrue(resourceAssociations.isInAvailableList(resourceName),
                "The resource should not be in resource column");
		
		ResourceAPIManager.deleteRequest("http://172.20.208.84:4040/resources", resourceToAssociate._id);
	}
	
}
