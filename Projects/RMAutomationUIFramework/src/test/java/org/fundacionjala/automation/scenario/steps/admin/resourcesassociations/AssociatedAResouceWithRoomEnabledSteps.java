package org.fundacionjala.automation.scenario.steps.admin.resourcesassociations;

import org.fundacionjala.automation.framework.pages.admin.conferencerooms.ConferenceRoomsPage;
import org.fundacionjala.automation.framework.pages.admin.conferencerooms.ResourceAssociationsPage;
import org.fundacionjala.automation.framework.pages.admin.home.AdminPage;
import org.fundacionjala.automation.framework.pages.admin.login.LoginActions;
import org.fundacionjala.automation.framework.pages.admin.login.LoginPage;
import org.fundacionjala.automation.framework.pages.admin.navigation.LeftMenu;
import org.fundacionjala.automation.framework.pages.admin.resource.ResourcePage;
import org.fundacionjala.automation.framework.utils.api.managers.ResourceAPIManager;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Resource;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.PropertiesReader;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;

import com.mashape.unirest.http.exceptions.UnirestException;

import cucumber.api.PendingException;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AssociatedAResouceWithRoomEnabledSteps {
	AdminPage home;
	ResourcePage resource;
	ConferenceRoomsPage conferenceRoom;
	LeftMenu leftMenu;
	ResourceAssociationsPage resourceAssociations;
	//private String resourceToAssociate;
	private String roomToModify;
	private String resourceName;
	private Resource resourceToAssociate;
	

	@Given("^I am on the Conferenc Rooms page$")
	public void i_am_on_the_Conferenc_Rooms_page() throws Throwable {
		/*
		 * 	@Before
		 * */
		resourceToAssociate = ResourceAPIManager
                .postRequest("http://172.20.208.84:4040/resources"
                 , new Resource("Key", "keys", "fa fa-key", "", "Key"));

        resourceName = resourceToAssociate.customName;
	    roomToModify = "Room02";
	    home = LoginActions.ExecuteLogin();
	    conferenceRoom = home.leftMenu
				.clickOnConferenceRoomsButton();
	}

	@Given("^I associate a resource$")
	public void i_associate_a_resource() throws Throwable {
		conferenceRoom
				.openConfigurationPage(roomToModify)
				.clickOnResourceAssociations()
				.addResource(resourceName)
				.clickOnSave();
	}
	
	@When("^I make sure the room edited is enabled$")
	public void i_make_sure_the_room_edited_is_enabled() throws Throwable {
		conferenceRoom.enableRoom();
	}

	@When("^when I open the pop-up configuration of the room enable$")
	public void when_I_open_the_pop_up_configuration_of_the_room_enable() throws Throwable {
		resourceAssociations = conferenceRoom
				.openConfigurationPage(roomToModify)
				.clickOnResourceAssociations();
	}

	@Then("^I can see the resource associated in Associate column$")
	public void i_can_see_the_resource_associated_in_Associate_column() throws Throwable {
		Assert.assertTrue(resourceAssociations.isInAssociatedColumn(resourceName),
                "The resource should be in resource column");
		/*
		 * 	@After()
		 * */
		
		ResourceAPIManager.deleteRequest("http://172.20.208.84:4040/resources", resourceToAssociate._id);
	}
	

}
