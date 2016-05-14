package org.fundacionjala.automation.scenario.steps.admin.resourcesassociations;

import org.fundacionjala.automation.framework.pages.admin.conferencerooms.ConferenceRoomsPage;
import org.fundacionjala.automation.framework.pages.admin.conferencerooms.RoomsResourceAssociationsPage;
import org.testng.Assert;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AssociatedAResouceWithRoomEnabledSteps {

    private String roomToModify, resourceName;
    private ConferenceRoomsPage conferenceRoom;
    private RoomsResourceAssociationsPage resourceAssociations;


    @Given("^I associate a resource on resources associations page$")
    public void i_associate_a_resource_on_resources_associations_page() throws Throwable {
	
    conferenceRoom = new ConferenceRoomsPage();
    resourceName = "resourceassoc06";
    roomToModify = conferenceRoom.getRandomRoom();
    
	conferenceRoom.openConfigurationPage(roomToModify)
		      .clickOnResourceAssociations()
		      .addResource(resourceName)
		      .clickOnSave();
    }

    @When("^I make sure the room edited is enabled$")
    public void i_make_sure_the_room_edited_is_enabled() throws Throwable {
	
	conferenceRoom.enableRoom(roomToModify);
    }

    @When("^when I open the pop-up configuration of the room enabled$")
    public void when_I_open_the_pop_up_configuration_of_the_room_enabled() throws Throwable {
	
	resourceAssociations = conferenceRoom
		                             .openConfigurationPage(roomToModify)
		                             .clickOnResourceAssociations();
    }

    @Then("^I see the resource associated in Associate column$")
    public void i_see_the_resource_associated_in_Associated_column() throws Throwable {

	Assert.assertTrue(resourceAssociations.isInAssociatedColumn(resourceName),
        		  "The resource should be in resource column");
    }

}
