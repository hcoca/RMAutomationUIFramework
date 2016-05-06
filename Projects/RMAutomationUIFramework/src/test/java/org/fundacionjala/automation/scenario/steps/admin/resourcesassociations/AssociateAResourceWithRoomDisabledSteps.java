package org.fundacionjala.automation.scenario.steps.admin.resourcesassociations;

import org.fundacionjala.automation.framework.pages.admin.conferencerooms.ConferenceRoomsPage;
import org.fundacionjala.automation.framework.pages.admin.conferencerooms.ResourceAssociationsPage;
import org.fundacionjala.automation.framework.pages.admin.home.AdminPage;
import org.fundacionjala.automation.framework.pages.admin.login.LoginPage;
import org.fundacionjala.automation.framework.pages.admin.navigation.LeftMenu;
import org.fundacionjala.automation.framework.pages.admin.resource.ResourcePage;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.PropertiesReader;
import org.testng.Assert;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AssociateAResourceWithRoomDisabledSteps {
	AdminPage home;
	ResourcePage resource;
	ConferenceRoomsPage conferenceRoom;
	LeftMenu leftMenu;
	ResourceAssociationsPage resourceAssociations;
	private String resourceToAssociate;
	private String roomToModify;
	
	@Before
	public void setTup()
	{
	   resourceToAssociate = "ResourceFernando";
	   roomToModify = "Room01";
	   BrowserManager.openBrowser();
	}
	
	@Given("^I am on the Conference Rooms page$")
	public void i_am_on_the_Conference_Rooms_page() throws Throwable {
		
		LoginPage login = new LoginPage();
		home = login
					.setUserName(PropertiesReader.getUserName())
					.setPassword(PropertiesReader.getPassword())
					.clickOnSigInButton()
					.refreshPage();	
	}

	@Given("^I have a resource associated$")
	public void i_have_a_resource_associated() throws Throwable {
		conferenceRoom = home.leftMenu
						.clickOnConferenceRoomsButton()
						.openConfigurationPage(roomToModify)
						.clickOnResourceAssociations()
						.addResource(resourceToAssociate)
						.clickOnSave();
	}
	
	@When("^I disable the room$")
	public void i_disable_the_room() throws Throwable {
	    conferenceRoom.disableRoom(roomToModify);
	}

	@When("^when I open the pop-up configuration of the room disabled$")
	public void when_I_open_the_pop_up_configuration_of_the_room_disabled() throws Throwable {
		resourceAssociations = conferenceRoom
								.openConfigurationPage(roomToModify)
								.clickOnResourceAssociations();
	}

	@Then("^I see the resource associated in Associated column$")
	public void i_see_the_resource_associated_in_Associated_column() throws Throwable {
		
		
	   Assert.assertTrue(resourceAssociations.isInAssociatedColumn(resourceToAssociate),
			                                  "the resource should be in resource column");
	   
	
	}

	
	

}
