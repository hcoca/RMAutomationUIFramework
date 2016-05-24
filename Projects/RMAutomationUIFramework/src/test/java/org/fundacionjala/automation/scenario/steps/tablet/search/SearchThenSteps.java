package org.fundacionjala.automation.scenario.steps.tablet.search;

import java.awt.AWTException;

import org.fundacionjala.automation.framework.pages.tablet.scheduler.SchedulerPage;
import org.fundacionjala.automation.framework.pages.tablet.search.SearchPage;
import org.fundacionjala.automation.framework.utils.api.managers.MeetingAPIManager;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Meeting;
import org.fundacionjala.automation.framework.utils.common.CrudOperationRooms;
import org.junit.Assert;

import com.mashape.unirest.http.exceptions.UnirestException;

import cucumber.api.java.After;
import cucumber.api.java.en.Then;

public class SearchThenSteps {
	
	private SearchPage search = new SearchPage();
    private String subject;
	
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
	
	@Then("^I verify that all fields of the advanced option is cleared$")
	public void i_verify_that_all_fields_of_the_advanced_option_is_cleared() throws Throwable {
	 
		Assert.assertTrue("All fields should be empty", search.isRoomNameEmpty());		
	}
	
	@Then("^I verify that \"([^\"]*)\" is not listed$")
	public void i_verify_that_is_not_listed(String roomName) throws Throwable {
		
		Assert.assertFalse(search.isListedRoom(roomName));
	}
	
	@Then("^I validate that the meeting with subject \"([^\"]*)\" is displayed$")
	public void i_validate_that_the_meeting_with_subject_is_displayed(String meetingSubject) throws Throwable {
		
		subject = meetingSubject;
	    Assert.assertTrue(search.isMeetingPresent(meetingSubject));
	}
	
    @After("@searchfeature7,@searchfeature8,@searchfeature9")
	public void deleteMeetingByAPI()
		    throws AWTException, UnirestException {
    	
		Meeting meetingToDelete = MeetingAPIManager.getMeetingBySubject(
			"Room008", subject);
		MeetingAPIManager.deleteRequest("Room008", meetingToDelete);
	}
	
}
