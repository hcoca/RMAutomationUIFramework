package org.fundacionjala.automation.scenario.steps.tablet.search;

import org.fundacionjala.automation.framework.pages.admin.conferencerooms.ConferenceRoomsPage;
import org.fundacionjala.automation.framework.pages.tablet.home.HomePage;
import org.fundacionjala.automation.framework.pages.tablet.search.SearchPage;
import org.fundacionjala.automation.framework.pages.tablet.settings.ConnectionPage;
import org.fundacionjala.automation.framework.pages.tablet.settings.NavigationPage;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.PropertiesReader;

import cucumber.api.java.en.When;

public class SearchWhenSteps {
	SearchPage search = new SearchPage();

	@When("^I click on advanced option button$")
	public void i_click_on_advanced_option_button() throws Throwable {
		
	   search.clickOnSearchButton();	
	}

	@When("^I set the room name field$")
	public void i_set_the_room_name_field() throws Throwable {
	   
	   search.setRoomName("Room00");		
	}
	
	@When("^I click on Room \"([^\"]*)\"$")
	public void i_click_on_Room(String room) throws Throwable {
		
	    search.clickOnRoom(room);
	}
	
	@When("^I go to search page of Room Manager tablet$")
	public void i_go_to_search_page_of_Room_Manager_tablet() throws Throwable {
		
		ConnectionPage connection = new ConnectionPage();
		NavigationPage navigation = connection
			.setUpServiceURL(PropertiesReader.getServiceURL())
			.clickOnSaveButton()
			.clickOnNavigationButton();

		HomePage home = 
		    navigation
				    .clickOnRoomToggleButton()
				    .insertConferenceRoom("Room118")
					.selectConferenceRoom("Room118")
					.clickOnSaveButton()
				    .topMenu
					.clickOnHomeButton();
		
	            home
	                .clickOnSearchButton();
	 
	}

	@When("^I filter rooms by the location \"([^\"]*)\"$")
	public void i_filter_rooms_by_the_location(String location) throws Throwable {
		
		 search
		    .clickOnSearchButton()
		    .selectLocation(location);
	}
	
	@When("^I press the clear button$")
	public void i_press_the_clear_button() throws Throwable {
	    
		search
		  .clickOnClear();
	}
	
	@When("^I set the room name field with the search option displayed$")
	public void i_set_the_room_name_field_with_the_search_option_displayed() throws Throwable {
	  
		 search
		      .clickOnSearchButton()
		      .setRoomName("Room00");	
	}

	@When("^I change the capacity of the \"([^\"]*)\" for \"([^\"]*)\"$")
	public void i_change_the_capacity_of_the_for(String roomName, String qty) throws Throwable {
	    ConferenceRoomsPage conferenceRoom = new ConferenceRoomsPage();
	    
	    conferenceRoom
	                .openConfigurationPage(roomName)
	                .typeOnCapacity(qty)
	                .clickOnSave();
		
	}

	@When("^I set the Minimun capacity field to \"([^\"]*)\"$")
	public void i_set_the_Minimun_capacity_field_to(String capacity) throws Throwable {

		 search
		      .clickOnSearchButton()
		      .setMinimumCapacity(capacity);	
	}
	
	@When("^I change the Minimun capacity field to \"([^\"]*)\"$")
	public void i_change_the_Minimun_capacity_field_to(String capacity) throws Throwable {

		 search
		      .setMinimumCapacity(capacity);	
	}
	
	@When("^I set the room name for \"([^\"]*)\"$")
	public void i_set_the_room_name_for(String roomName) throws Throwable {
		
		search
	      .clickOnSearchButton()
	      .setRoomName(roomName);	
	}
	
	@When("^I perform one click on the resource \"([^\"]*)\"$")
	public void i_perform_one_click_on_the_resource(String iconName) throws Throwable {
	  
		search
		  .clickOnResource();
	}
}
