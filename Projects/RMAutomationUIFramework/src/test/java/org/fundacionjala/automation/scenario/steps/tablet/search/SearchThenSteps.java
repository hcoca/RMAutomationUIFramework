package org.fundacionjala.automation.scenario.steps.tablet.search;

import org.fundacionjala.automation.framework.pages.tablet.search.SearchPage;
import org.fundacionjala.automation.framework.utils.common.CrudOpearionRooms;
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
	   
		int expectedQty = CrudOpearionRooms.getRooms("Room00").size();
		int actualQty = search.getRoomList().size();
		
	    Assert.assertEquals(expectedQty, actualQty);
		
	}
	
}
