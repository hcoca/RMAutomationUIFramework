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
    public void createOuOfOrderWithPastTime(String roomName) throws Throwable {
	room = home.leftMenu.clickOnConferenceRoomsButton()
		.openConfigurationPage(roomName).clickOnOutOfOrder()
		.setTimeBeginDown().setTimeEndDown().activeOutOfOrder()
		.clickOnSave();
    }

    @Then("^The Out Of Order cannot be created an error message is displayed$")
    public void validateErrorMessageIsDisplayed() throws Throwable {
	boolean verification = false;
	WebElement messageError = BrowserManager.getDriver().findElement(
		By.xpath(OutOfOrderMap.ERROR_MESSAGE));
	if (messageError.getText().contains(
		"Cannot establish out of order as an past event")) {
	    verification = true;
	}
	Assert.assertTrue(verification);
    }
}
