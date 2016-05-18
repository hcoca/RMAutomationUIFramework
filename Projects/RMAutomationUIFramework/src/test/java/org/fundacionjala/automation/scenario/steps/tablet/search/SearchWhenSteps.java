package org.fundacionjala.automation.scenario.steps.tablet.search;

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
			.clickOnSaveButton().clickOnNavigationButton();

		HomePage home = 
		    navigation
				    .clickOnRoomToggleButton()
					.selectConferenceRoom("Room002").clickOnSaveButton().topMenu
					.clickOnHomeButton();
		
	   home.clickOnSearchButton();
	 
	}

	@When("^I filter rooms by the location \"([^\"]*)\"$")
	public void i_filter_rooms_by_the_location(String location) throws Throwable {
		
		 search
		    .clickOnSearchButton()
		    .selectLocation(location);
	}
}
