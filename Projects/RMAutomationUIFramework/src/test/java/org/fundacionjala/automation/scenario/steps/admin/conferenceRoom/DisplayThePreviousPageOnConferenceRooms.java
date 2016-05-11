package org.fundacionjala.automation.scenario.steps.admin.conferenceRoom;

import java.util.List;

import org.fundacionjala.automation.framework.pages.admin.conferencerooms.ConferenceRoomsPage;
import org.fundacionjala.automation.framework.pages.admin.home.AdminPage;
import org.fundacionjala.automation.framework.utils.api.managers.RoomAPIManager;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Room;
import org.testng.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class DisplayThePreviousPageOnConferenceRooms {
	@Given("^I select a \"([^\"]*)\" that has a previous page$")
	public void i_select_a_that_has_a_previous_page(String page) throws Throwable {
		AdminPage home = new AdminPage();
		ConferenceRoomsPage room = home.leftMenu
									   .clickOnConferenceRoomsButton()
									   .setPage(page);
	}

	@Then("^I validate if the previous page is displayed according the page size specified \"([^\"]*)\" and the page \"([^\"]*)\"$")
	public void i_validate_if_the_previous_page_is_displayed_according_the_page_size_specified_and_the_page(String pageSize, String page) throws Throwable {
		ConferenceRoomsPage room = new ConferenceRoomsPage();
		
		boolean verification = false;
		List<Room> listRooms = RoomAPIManager.getRequest("http://172.20.208.84:4040/rooms");
		int index= ((Integer.parseInt(page)-1)*Integer.parseInt(pageSize))+1;
		
		for (int i = 0; i < listRooms.size(); i++) {
			if(listRooms.get(i).displayName.contains(Integer.toString(index))){
				String displayNameRoom = listRooms.get(i).displayName;
				if(room.getFirstRow().contains(displayNameRoom)){
					verification = true;
				}
			}
		}
		Assert.assertTrue(verification);
	}
}
