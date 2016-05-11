package org.fundacionjala.automation.scenario.steps.admin.resourcesassociations;

import org.fundacionjala.automation.framework.pages.admin.conferencerooms.ConferenceRoomsPage;
import org.fundacionjala.automation.framework.pages.admin.conferencerooms.RoomsResourceAssociationsPage;
import org.fundacionjala.automation.framework.pages.admin.resource.IconResources;
import org.fundacionjala.automation.framework.pages.admin.resource.ResourcesActions;
import org.fundacionjala.automation.framework.utils.api.managers.ResourceAPIManager;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Resource;
import org.testng.Assert;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class RemoveAssociation {

    private String resourceName, roomToModify;
    private Resource resourceToAssociate;
    private ConferenceRoomsPage conferenceRoom;
    private RoomsResourceAssociationsPage resourceAssociations;

    @Before("@scenario#4")
    public void beforeScenario() throws Throwable {

	conferenceRoom = new ConferenceRoomsPage();
	resourceToAssociate = ResourcesActions.createResourceByAPI("key05",
		                                                   IconResources.KEY, "key");
	resourceName = resourceToAssociate.customName;
	roomToModify = conferenceRoom.getRandomRoom();

    }

    @Given("^I associate a resource on resources association page$")
    public void i_associate_a_resource_on_resources_association_page() throws Throwable {
	conferenceRoom
	        .openConfigurationPage(roomToModify)
		.clickOnResourceAssociations().addResource(resourceName)
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

    @After("@scenario#4")
    public void afterScenario() throws Throwable {
	
	ResourcesActions.deleteResourceByAPI(resourceToAssociate);
    }
}
