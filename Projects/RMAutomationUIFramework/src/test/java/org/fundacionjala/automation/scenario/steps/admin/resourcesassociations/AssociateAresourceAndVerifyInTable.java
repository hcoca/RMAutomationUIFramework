package org.fundacionjala.automation.scenario.steps.admin.resourcesassociations;

import org.fundacionjala.automation.framework.pages.admin.conferencerooms.ConferenceRoomsPage;
import org.fundacionjala.automation.framework.pages.admin.home.AdminPage;
import org.fundacionjala.automation.framework.pages.admin.login.LoginActions;
import org.fundacionjala.automation.framework.pages.admin.resource.IconResources;
import org.fundacionjala.automation.framework.pages.admin.resource.ResourcesActions;
import org.fundacionjala.automation.framework.utils.api.managers.ResourceAPIManager;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Resource;
import org.testng.Assert;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AssociateAresourceAndVerifyInTable {

    private String resourceName, roomToModify;
    private Resource resourceToAssociate;
    private ConferenceRoomsPage conferenceRoom;

    @Before("@scenario#2")
    public void beforeScenario() throws Throwable {

	conferenceRoom = new ConferenceRoomsPage();
	resourceToAssociate = ResourcesActions.createResourceByAPI("key02",
		IconResources.KEY, "key02");
	resourceName = resourceToAssociate.customName;
	roomToModify = conferenceRoom.getRandomRoom();
    }

    @Given("^I have a resource associate with quantity \"([^\"]*)\"$")
    public void i_have_a_resource_associate_with_quantity(String qty)
	    throws Throwable {

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

    @After("@scenario#2")
    public void afterScenario() throws Throwable {

	ResourcesActions.deleteResourceByAPI(resourceToAssociate);
    }

}
