package org.fundacionjala.automation.scenario.steps.admin.resourcesassociations;
import org.fundacionjala.automation.framework.pages.admin.conferencerooms.ConferenceRoomsPage;
import org.fundacionjala.automation.framework.pages.admin.home.AdminPage;
import org.fundacionjala.automation.framework.pages.admin.login.LoginActions;
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
	private ConferenceRoomsPage conferenceRoom;
	private String resourceName;
	private Resource resourceToAssociate;
	private String roomToModify;
	private String qty;


	@Before("@num#2")
	public void beforeScenario() throws Throwable {
		
		conferenceRoom = new ConferenceRoomsPage();
		resourceToAssociate = ResourceAPIManager.postRequest("http://172.20.208.84:4040/resources"
                                                     , new Resource("keyf", "keyf", "fa fa-key", "", "Key"));

        resourceName = resourceToAssociate.customName;
        qty = "123";
        roomToModify = conferenceRoom.getRandomRoom();
	}


	@Given("^I have a resource associate$")
	public void i_have_a_resource_associate() throws Throwable {
		
		conferenceRoom
				.openConfigurationPage(roomToModify)
				.clickOnResourceAssociations()
				.addResource(resourceName)
				.editQuantityOfResourceAssociated(resourceName, qty)
				.clickOnSave();	
	}

	@When("^I click on the resource$")
	public void i_click_on_the_resource() throws Throwable {
		
		conferenceRoom.clickOnResource(resourceName);
	}

	@Then("^I see the resource associate on the conference room page$")
	public void i_see_the_resource_associate_on_the_conference_room_page() throws Throwable {
		
	   Assert.assertTrue(conferenceRoom.isQuantityDisplayed(qty), "The quantity should be displayed");
			
	}
	
	@After("@num#2")
	public void afterScenario() throws Throwable {
		ResourceAPIManager.deleteRequest("http://172.20.208.84:4040/resources", resourceToAssociate._id);
	}
	
	
}
