package org.fundacionjala.automation.scenario.steps.admin.resourcesassociations;

import org.fundacionjala.automation.framework.pages.admin.conferencerooms.ConferenceRoomsPage;
import org.testng.Assert;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AssociateAresourceAndVerifyInTable {

    private String resourceName, roomToModify;
    private ConferenceRoomsPage conferenceRoom;
    

    @Given("^I have a resource associate with quantity \"([^\"]*)\"$")
    public void i_have_a_resource_associate_with_quantity(String qty)
	    throws Throwable {
    	
    conferenceRoom = new ConferenceRoomsPage();	
    resourceName = "resourceassoc02";
	roomToModify = conferenceRoom.getRandomRoom();

	conferenceRoom.openConfigurationPage(roomToModify)
		      .clickOnResourceAssociations().addResource(resourceName)
		      .editQuantityOfResourceAssociated(resourceName, qty)
		      .clickOnSave();

    }

    @When("^I click on the resource$")
    public void i_click_on_the_resource() throws Throwable {

	conferenceRoom.clickOnResource(resourceName);
    }

    @Then("^I see the resource associate on the table of the conference room with the quantity \"([^\"]*)\"$")
    public void i_see_the_resource_associate_on_the_table_of_the_conference_room_with_the_quantity(String qty) throws Throwable {

	Assert.assertTrue(conferenceRoom.isQuantityDisplayed(qty),
		              "The quantity should be displayed");

    }

}
