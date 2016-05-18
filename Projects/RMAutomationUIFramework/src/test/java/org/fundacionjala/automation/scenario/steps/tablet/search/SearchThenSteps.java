package org.fundacionjala.automation.scenario.steps.tablet.search;

import org.fundacionjala.automation.framework.pages.tablet.scheduler.SchedulerPage;
import org.fundacionjala.automation.framework.pages.tablet.search.SearchPage;
import org.fundacionjala.automation.framework.utils.common.CrudOperationRooms;
import org.junit.Assert;

import cucumber.api.java.en.Then;

public class SearchThenSteps {
	
	SearchPage search = new SearchPage();

	
	@Then("^I see the advanced option with all field to search$")
	public void i_see_the_advanced_option_with_all_field_to_search() throws Throwable {
		
		Assert.assertTrue(search.allFieldsArePresent());		
	}
	
	@Then("^I see all rooms that match with the room name criteria$")
	public void i_see_all_rooms_that_match_with_the_room_name_criteria() throws Throwable {
	   
		int expectedQty = CrudOperationRooms.getRooms("Room00").size();
		int actualQty = search.getRoomList().size();
		
	    Assert.assertEquals(expectedQty, actualQty);
	}

	@Then("^I see the the Schedule page of the room \"([^\"]*)\"$")
	public void i_see_the_the_Schedule_page_of_the_room(String roomName) throws Throwable {
		
	    SchedulerPage schedule = new SchedulerPage();
	    
		Assert.assertTrue(schedule.isTitlePresent(roomName));
	}
	
	@Then("^I see the \"([^\"]*)\" listed in the results$")
	public void i_see_the_listed_in_the_results(String roomName) throws Throwable {
	    
	    Assert.assertTrue(search.isListedRoom(roomName));
	}
	
}
