package org.fundacionjala.automation.scenario.steps.admin.conferenceRoom;

import org.fundacionjala.automation.framework.maps.admin.conferencerooms.OutOfOrderMap;
import org.fundacionjala.automation.framework.pages.admin.conferencerooms.ConferenceRoomsPage;
import org.fundacionjala.automation.framework.pages.admin.home.AdminPage;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CreateOutOfOrderWithPastTime {
	AdminPage home;
	ConferenceRoomsPage room;

	@When("^I create an OuOfOrder on \"([^\"]*)\" room with a time in the past$")
	public void i_create_an_OuOfOrder_on_room_with_a_time_in_the_past(String roomName) throws Throwable {
		room = home.leftMenu.clickOnConferenceRoomsButton()
				    .openConfigurationPage(roomName)
				    .clickOnOutOfOrder()
				    .setTimeBeginDown()
				    .setTimeEndDown()
				    .activeOutOfOrder()
				    .clickOnSave();
	}
	
	@Then("^The Out Of Order cannot be created an error message is displayed$")
	public void the_Out_Of_Order_cannot_be_created_an_error_message_is_displayed() throws Throwable {
	    boolean verification = false;
	    WebElement messageError =BrowserManager.getDriver().findElement(By.xpath(OutOfOrderMap.ERROR_MESSAGE));
	    if(messageError.getText().contains("Cannot establish out of order as an past event")){
	    	verification = true;
	    }
	    Assert.assertTrue(verification);
	}
}
