package org.fundacionjala.automation.scenario.steps.admin.location;

import org.fundacionjala.automation.framework.pages.admin.home.AdminPage;
import org.fundacionjala.automation.framework.pages.admin.locations.LocationPage;

import cucumber.api.java.en.When;


public class LocationWhenSteps {
	
	@When("^I add a new location with name: \"([^\"]*)\", display name \"([^\"]*)\" and description \"([^\"]*)\"$")
	public void i_add_a_new_location_with_name_display_name_and_description(String name, String displayName, String description) throws Throwable {
		AdminPage adminPage = new AdminPage();
				
	    adminPage
	    .leftMenu
	    .clickOnLocationsButton()
	    .clickOnAddButton()
	    .setNameField(name)
	    .setDisplayNameField(displayName)
	    .setDescriptionArea(description)
	    .clickOnSaveButton();
	}
	
	@When("^I open locations page$")
	public void i_open_locations_page() throws Throwable {
		AdminPage adminPage = new AdminPage();
		
	    adminPage
	    .leftMenu
	    .clickOnLocationsButton();
	}
	
	@When("^I update location \"([^\"]*)\" with name: \"([^\"]*)\", display name \"([^\"]*)\" and description \"([^\"]*)\"$")
	public void i_update_location_with_name_display_name_and_description(String currentLocation, String name, String displayName, String description) throws Throwable {
		AdminPage adminPage = new AdminPage();
		
	    adminPage
	    .leftMenu
	    .clickOnLocationsButton()
		.doubleClickOnALocation(currentLocation)
		.setNameField(name)
		.setDisplayNameField(displayName)
		.setDescriptionArea(description)
		.clickOnSaveButton();
	}
	
	@When("^I update \"([^\"]*)\" location parent to \"([^\"]*)\"$")
	public void i_update_location_parent_to(String displayName, String parentName) throws Throwable {
		AdminPage adminPage = new AdminPage();
		
		adminPage
	    .leftMenu
	    .clickOnLocationsButton()
		.doubleClickOnALocation(displayName)
		.clickOnAddParentButton()
		.selectAParentLocation(parentName)
		.clickOnSaveButton();
	}
	
	@When("^I delete the location \"([^\"]*)\"$")
	public void i_delete_the_location(String displayName) throws Throwable {
		AdminPage adminPage = new AdminPage();
		
		adminPage
	    .leftMenu
	    .clickOnLocationsButton()
	    .clickOnALocation(displayName)
		.clickOnRemoveButton()
		.clickOnRemoveButton();
	}
	
	@When("^I add a new location with name: \"([^\"]*)\", display name \"([^\"]*)\" and parent \"([^\"]*)\"$")
	public void i_add_a_new_location_with_name_display_name_and_parent(String name, String displayName, String parentName) throws Throwable {
		AdminPage adminPage = new AdminPage();
		
	    adminPage
	    .leftMenu
	    .clickOnLocationsButton()
	    .clickOnAddButton()
	    .setNameField(name)
	    .setDisplayNameField(displayName)
	    .clickOnAddParentButton()
		.selectAParentLocation(parentName)
		.clickOnSaveButton();
	}
	
	@When("^I add a new location with name: \"([^\"]*)\", display name \"([^\"]*)\" and associated room \"([^\"]*)\"$")
	public void i_add_a_new_location_with_name_display_name_and_associated_room(String name, String displayName, String roomName) throws Throwable {
		AdminPage adminPage = new AdminPage();
		
	    adminPage
	    .leftMenu
	    .clickOnLocationsButton()
	    .clickOnAddButton()
	    .setNameField(name)
	    .setDisplayNameField(displayName)
	    .clickOnLocationAssociationLink()
	    .clickOnAddAvailableRoom(roomName)
	    .clickOnSaveButton();
	}
	
	@When("^I associate the location \"([^\"]*)\" with a room \"([^\"]*)\"$")
	public void i_associate_the_location_with_a_room(String displayName, String roomName) throws Throwable {
		AdminPage adminPage = new AdminPage();
		
	    adminPage
	    .leftMenu
	    .clickOnLocationsButton().doubleClickOnALocation(displayName)
	    .clickOnLocationAssociationLink()
	    .clickOnAddAvailableRoom(roomName)
	    .clickOnSaveButton();
	}
	
	@When("^I delete the association between location \"([^\"]*)\" and \"([^\"]*)\"$")
	public void i_delete_the_association_between_location_and(String displayName, String roomName) throws Throwable {
		LocationPage locationPage = new LocationPage();
		
		locationPage
		.doubleClickOnALocation(displayName)
	    .clickOnLocationAssociationLink()
	    .clickOnRemoveAssociatedRoom(roomName)
	    .clickOnSaveButton();
	}
}
