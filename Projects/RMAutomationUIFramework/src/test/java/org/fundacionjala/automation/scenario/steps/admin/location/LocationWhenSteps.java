package org.fundacionjala.automation.scenario.steps.admin.location;

import org.fundacionjala.automation.framework.pages.admin.home.AdminPage;
import org.fundacionjala.automation.framework.pages.admin.locations.AddLocationPage;
import org.fundacionjala.automation.framework.pages.admin.locations.LocationPage;
import org.fundacionjala.automation.framework.pages.admin.locations.RemoveLocationPage;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.UIActions;
import org.openqa.selenium.By;

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
	
	@When("^I try to update location \"([^\"]*)\" with name: \"([^\"]*)\", display name \"([^\"]*)\" and description \"([^\"]*)\"$")
	public void i_try_to_update_location_with_name_display_name_and_description(String currentLoc, String nameLoc, String dispName, String desc) throws Throwable {
		
		goToLocationPage()
		.doubleClickOnALocation(currentLoc)
		.setNameField(nameLoc)
		.setDisplayNameField(dispName)
		.setDescriptionArea(desc);
		
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
	
	@When("^I try to delete the location \"([^\"]*)\"$")
	public void i_try_to_delete_the_location(String locationName) throws Throwable {
		
		goToLocationPage()
			.clickOnALocation(locationName)
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
					.clickOnAddAvailableRoom()
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
			.clickOnAddAvailableRoom()
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
	
	@When("^I select \"([^\"]*)\" on location page size option$")
	public LocationPage i_select_on_location_page_size_option(String quantity) throws Throwable {
		LocationPage locationPage = new LocationPage();
		locationPage
		    .leftMenu
		    .clickOnConferenceRoomsButton();
		
		locationPage
			.leftMenu
			.clickOnLocationsButton();
		
		UIActions.waitFor("//div[@id='locationGrid']");
		String xpath = "//div[@id='locationGrid']/div[3]/div/div[2]/div[1]/select//option[text()='number']"
			.replace("number", quantity);
		BrowserManager.getDriver().findElement(By.xpath(xpath)).click();
		
		return new LocationPage();
	}
	
	@When("^I set the page by \"([^\"]*)\" on location page$")
	public void setPageOnLocation(String page) throws Throwable {
	   LocationPage location = new LocationPage();
	   location.setPage(page);
	}
	
	@When("^I tried to add a new location with name: \"([^\"]*)\", display name \"([^\"]*)\" and description \"([^\"]*)\"$")
	public void i_tried_to_add_a_new_location_with_name_display_name_and_description(String name, String dispName, String desc) throws Throwable {
		
		 goToLocationPage()
			.clickOnAddButton()
			.setNameField(name)
			.setDisplayNameField(dispName)
			.setDescriptionArea(desc);
			
		
	}

	@When("^I press the cancel button$")
	public void i_press_the_cancel_button() throws Throwable {
		
		AddLocationPage addLocation = new AddLocationPage();
		
		addLocation
		         .clickOnCancelButton();
	}
	
	@When("^I press the cancel button on delete page$")
	public void i_press_the_cancel_button_on_delete_page() throws Throwable {
		
	   RemoveLocationPage removeLocation = new RemoveLocationPage();
		
	   removeLocation
	               .clickOnCancelButton();
	}
}

