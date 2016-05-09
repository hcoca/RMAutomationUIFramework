package org.fundacionjala.automation.scenario.steps.admin.conferenceRoom;

import org.fundacionjala.automation.framework.pages.admin.conferencerooms.ConferenceRoomsPage;
import org.fundacionjala.automation.framework.pages.admin.home.AdminPage;
import org.fundacionjala.automation.framework.pages.admin.login.LoginPage;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CreateOutOfOrderWithPastTime {
	AdminPage home;
	ConferenceRoomsPage room;
	
	@Given("^I logged RoomManager Admin$")
	public void i_logged_RoomManager_Admin() throws Throwable {
		BrowserManager.openBrowser();
		LoginPage login = new LoginPage();
		home = login.setUserName("Administrator")
					.setPassword("Control*123")
					.clickOnSigInButton()
					.refreshPage();
	}

	@When("^I create an OuOfOrder on \"([^\"]*)\" room with a time in the past$")
	public void i_create_an_OuOfOrder_on_room_with_a_time_in_the_past(String arg1) throws Throwable {
		room = home.leftMenu.clickOnConferenceRoomsButton()
							.openConfigurationPage(arg1)
							.clickOnOutOfOrder()
							.setTimeBeginDown()
							.setTimeEndDown()
							.activeOutOfOrder()
							.clickOnSave();
	}

	@Then("^I validate if the Out Of Order on \"([^\"]*)\" room can not be created$")
	public void i_validate_if_the_Out_Of_Order_on_room_can_not_be_created(String arg1) throws Throwable {
		boolean verification = false;
		WebElement messageError =BrowserManager.getDriver().findElement(By.xpath("//small[@class='inline-error ng-binding']"));
	    if(messageError.getText().contains("Cannot establish out of order as an past event")){
	    	verification = true;
	    }
	    Assert.assertTrue(verification);
	}
}
