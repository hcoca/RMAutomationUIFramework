package org.fundacionjala.automation.scenario.steps.admin.resourcesassociations;

import org.fundacionjala.automation.framework.pages.admin.conferencerooms.ConferenceRoomsPage;
import org.fundacionjala.automation.framework.pages.admin.home.AdminPage;
import org.fundacionjala.automation.framework.pages.admin.login.LoginActions;
import org.fundacionjala.automation.framework.pages.admin.resource.ResourceAssociationsPage;
import org.fundacionjala.automation.framework.pages.admin.resource.ResourcePage;
import org.fundacionjala.automation.framework.utils.api.managers.ResourceAPIManager;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Resource;
import org.testng.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TheQuantityOfAssociatedResourceIsDisplayedOnQuantitColumn {

    private AdminPage home;
	private String resourceName;
	private Resource resourceToAssociate;
	private String roomToModify;
	private ResourcePage resourcePage;
	private ResourceAssociationsPage resourceAssociationsPage; 
	private String qty;
	private ConferenceRoomsPage conferenceRoom;
	
	
	
	@Given("^I am logged to room Manager with user administrator account$")
	public void i_am_logged_to_room_Manager_with_user_administrator_account() throws Throwable {
	 
		 resourceToAssociate = ResourceAPIManager.postRequest("http://172.20.208.84:4040/resources"
                 , new Resource("key169", "key169", "fa fa-key", "", "Key"));

		resourceName = resourceToAssociate.customName;
		
		roomToModify = "Room01";
		qty = "123";
		home = LoginActions.ExecuteLogin();
		
	}

	@Given("^I associate a resource$")
	public void i_associate_a_resource() throws Throwable {
		conferenceRoom = 
				home.leftMenu
				.clickOnConferenceRoomsButton()
				.openConfigurationPage(roomToModify)
				.clickOnResourceAssociations()
				.addResource(resourceName)
				.editQuantityOfResourceAssociated(resourceName, qty)
				.clickOnSave();	
	}

	@When("^I go to resources page$")
	public void i_go_to_resources_page() throws Throwable {
		resourcePage = home
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

	@Then("^I see the correct quantity that was associated$")
	public void i_see_the_correct_quantity_that_was_associated() throws Throwable {
		
		Assert.assertTrue(resourceAssociationsPage.isQtyDisplayed(qty)
				                      , "The correct quantity should be displayed");
	  
		ResourceAPIManager.deleteRequest("http://172.20.208.84:4040/resources", resourceToAssociate._id);
	}
}
