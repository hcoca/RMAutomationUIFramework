package org.fundacionjala.automation.scenario.steps.admin.resourcesassociations;

import org.fundacionjala.automation.framework.pages.admin.conferencerooms.ConferenceRoomsPage;
import org.fundacionjala.automation.framework.pages.admin.conferencerooms.RoomsResourceAssociationsPage;
import org.testng.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class RemoveAssociation {

    private String resourceName, roomToModify;
    private ConferenceRoomsPage conferenceRoom;
    private RoomsResourceAssociationsPage resourceAssociations;


    @Given("^I associate a resource on resources association page$")
    public void i_associate_a_resource_on_resources_association_page() throws Throwable {
        
    	conferenceRoom = new ConferenceRoomsPage();
    	resourceName = "resourceassoc04";
    	roomToModify = conferenceRoom.getRandomRoom();
    	
		conferenceRoom
		             .openConfigurationPage(roomToModify)
				     .clickOnResourceAssociations()
				     .addResource(resourceName)
				     .clickOnSave();
    }

    @When("^I remove the resource of the associated column$")
    public void i_remove_the_resource_of_the_associated_column() throws Throwable {

		conferenceRoom
		             .openConfigurationPage(roomToModify)
			         .clickOnResourceAssociations().removeResource(resourceName);

    }

    @Then("^I see the resource on available resources column$")
    public void i_see_the_resource_on_available_resources_column() throws Throwable {

	resourceAssociations = 
						conferenceRoom
					        		.openConfigurationPage(roomToModify)
					        		.clickOnResourceAssociations();

	Assert.assertTrue(resourceAssociations.isInAvailableList(resourceName),
		          "The resource should not be in resource column");
    }
}
