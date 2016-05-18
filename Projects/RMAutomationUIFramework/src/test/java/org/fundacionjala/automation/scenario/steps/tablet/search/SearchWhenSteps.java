package org.fundacionjala.automation.scenario.steps.tablet.search;

import org.fundacionjala.automation.framework.pages.tablet.search.SearchPage;
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
}
