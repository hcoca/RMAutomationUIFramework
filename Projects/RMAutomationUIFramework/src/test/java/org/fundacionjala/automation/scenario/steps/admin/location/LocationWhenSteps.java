package org.fundacionjala.automation.scenario.steps.admin.location;

import org.fundacionjala.automation.framework.pages.admin.home.AdminPage;
import org.fundacionjala.automation.framework.pages.admin.locations.LocationPage;

import cucumber.api.java.en.When;


public class LocationWhenSteps {
	
	@When("^I add a new location with name: \"([^\"]*)\", display name \"([^\"]*)\" and description \"([^\"]*)\"$")
	public void addALocation(String name, 
			         String displayName, 
			         String description) throws Throwable { 
		
	    	goToLocationPage()
	    	.clickOnAddButton()
	    	.setNameField(name)
	    	.setDisplayNameField(displayName)
	    	.setDescriptionArea(description)
	    	.clickOnSaveButton();
	}
	
	@When("^I open locations page$")
	public void i_open_locations_page() throws Throwable {
		goToLocationPage();
	}
	
	@When("^I update location \"([^\"]*)\" with name: \"([^\"]*)\", display name \"([^\"]*)\" and description \"([^\"]*)\"$")
	public void updateLocation(String currentLocation, 
				   String name, 
				   String displayName, 
				   String description) throws Throwable {
		
		goToLocationPage()
		.doubleClickOnALocation(currentLocation)
		.setNameField(name)
		.setDisplayNameField(displayName)
		.setDescriptionArea(description)
		.clickOnSaveButton();
	}
	
	@When("^I update \"([^\"]*)\" location parent to \"([^\"]*)\"$")
	public void updateLocationParent(String displayName, 
					 String parentName) throws Throwable {
		
		goToLocationPage()
			.doubleClickOnALocation(displayName)
			.clickOnAddParentButton()
			.selectAParentLocation(parentName)
			.clickOnSaveButton();
	}
	
	@When("^I delete the location \"([^\"]*)\"$")
	public void deleteLocation(String displayName) throws Throwable {
		
		goToLocationPage()
			.clickOnALocation(displayName)
			.clickOnRemoveButton()
			.clickOnRemoveButton();
	}
	
	@When("^I add a new location with name: \"([^\"]*)\", display name \"([^\"]*)\" and parent \"([^\"]*)\"$")
	public void addChildLocation(String name, 
				     String displayName, 
				     String parentName) throws Throwable {
		
		goToLocationPage()
			.clickOnAddButton()
			.setNameField(name)
			.setDisplayNameField(displayName)
			.clickOnAddParentButton()
			.selectAParentLocation(parentName)
			.clickOnSaveButton();
	}
	
	@When("^I add a new location with name: \"([^\"]*)\", display name \"([^\"]*)\" and associated room \"([^\"]*)\"$")
	public void addAssociatedLocation(String name, 
					  String displayName, 
					  String roomName) throws Throwable {
		
		 goToLocationPage()
					.clickOnAddButton()
					.setNameField(name)
					.setDisplayNameField(displayName)
					.clickOnLocationAssociationLink()
					.setRoomName(roomName)
					.clickOnAssigned()
					.clickOnAddAvailableRoom(roomName)
					.clickOnSaveButton();
	}
	
	@When("^I associate the location \"([^\"]*)\" with a room \"([^\"]*)\"$")
	public void associateLocation(String displayName, 
				      String roomName) throws Throwable {
		
		goToLocationPage()
			.doubleClickOnALocation(displayName)
			.clickOnLocationAssociationLink()
			.setRoomName(roomName)
			.clickOnAssigned()
			.clickOnAddAvailableRoom(roomName)
			.clickOnSaveButton();
	}
	
	@When("^I delete the association between location \"([^\"]*)\" and \"([^\"]*)\"$")
	public void removeLocationAssociation(String displayName, 
					      String roomName) throws Throwable {
		
		LocationPage locationPage = new LocationPage();
		locationPage
		    .leftMenu
		    .clickOnConferenceRoomsButton();
		
		locationPage
			.leftMenu
			.clickOnLocationsButton()
			.doubleClickOnALocation(displayName)
			.clickOnLocationAssociationLink()
			.clickOnRemoveAssociatedRoom(roomName)
			.clickOnSaveButton();
	}
	
	private LocationPage goToLocationPage(){
		
		AdminPage adminPage = new AdminPage();
		
		LocationPage locationPage = adminPage
						.refreshPage()
						.leftMenu
	 		     		.clickOnLocationsButton();
	    
	    return locationPage;
	}
}
