package org.fundacionjala.automation.scenario.steps.admin.conferenceRoom;

import org.fundacionjala.automation.framework.pages.admin.conferencerooms.ConferenceRoomsPage;
import org.fundacionjala.automation.framework.pages.admin.home.AdminPage;
import org.fundacionjala.automation.framework.pages.admin.resource.ResourcePage;
import org.junit.Assert;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ResourceButtonsInConferenceRoomPage {
	String expectedResult;
	AdminPage Home = new AdminPage();
	ConferenceRoomsPage ConferenceRoom = new ConferenceRoomsPage();
	ResourcePage Resource = new ResourcePage();
	@When("^I create a new \"([^\"]*)\" Resource$")
	public void i_create_a_new_Resource(String resourceName) throws Throwable {
	    
		expectedResult = resourceName;
		Home
			.leftMenu
			.clickOnResourcesButton()
			.clickOnAddButton()
			.setResourceName(resourceName)
			.setDisplayName(resourceName)
			.setDescription(resourceName)
			.clickOnSaveButton()
			.refreshPage();
	}

	@Then("^I validate if the resource is in Conference Room page\\.$")
	public void i_validate_if_the_resource_is_in_Conference_Room_page() throws Throwable {
	    
	    Assert.assertTrue(
		Home
	    	.leftMenu
	    	.clickOnConferenceRoomsButton()
	    	.verifyIfResourceCreatedIsInConferenceRoomPage(expectedResult)
	    );
	    
	    Home
		.leftMenu
		.clickOnResourcesButton()
		.selectResource(expectedResult)
		.clickOnRemoveButton()
		.clickOnRemoveButton();
	}
}
