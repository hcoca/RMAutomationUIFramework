package org.fundacionjala.automation.scenario.steps.admin.conferenceRoom;

import org.fundacionjala.automation.framework.maps.tablet.home.HomeMap;
import org.fundacionjala.automation.framework.pages.admin.conferencerooms.ConferenceRoomsPage;
import org.fundacionjala.automation.framework.pages.admin.conferencerooms.OutOfOrderPage;
import org.fundacionjala.automation.framework.pages.admin.home.AdminPage;
import org.fundacionjala.automation.framework.pages.admin.login.LoginPage;
import org.fundacionjala.automation.framework.pages.tablet.home.HomePage;
import org.fundacionjala.automation.framework.pages.tablet.settings.ConnectionPage;
import org.fundacionjala.automation.framework.pages.tablet.settings.NavigationPage;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.PropertiesReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CreateOutOfOrderWithATimeDefined {

    @When("^I create an Out of Order on \"([^\"]*)\" room with a time by default$")
    public void createOutOfOrderTimeDefault(String roomName) throws Throwable {
	AdminPage home = new AdminPage();
	ConferenceRoomsPage room = new ConferenceRoomsPage();
	room = home.leftMenu.clickOnConferenceRoomsButton()
		.openConfigurationPage(roomName).clickOnOutOfOrder()
		.setTimeBeginUp().setTimeEndUp().storeFromTime().storeToTime()
		.activeOutOfOrder().clickOnSave();
    }

    @Then("^The Out Of Order on \"([^\"]*)\" room should be created with the time interval defined$")
    public void outOfOrderShouldBeCreatedWithIntervalTimeDefined(String roomName)
	    throws Throwable {
	boolean verification = false;
	ConnectionPage connection = new ConnectionPage();

	NavigationPage navigation = connection
		.setUpServiceURL(PropertiesReader.getServiceURL())
		.clickOnSaveButton().clickOnNavigationButton();

	HomePage hometablet = navigation.clickOnRoomToggleButton()
		.selectConferenceRoom(roomName).clickOnSaveButton().topMenu
		.clickOnHomeButton();

	WebElement time = BrowserManager.getDriver().findElement(
		By.xpath(HomeMap.TIME_OUT_OF_ORDER));
	if ((time.getText().contains(OutOfOrderPage.timeBegin))
		&& (time.getText().contains(OutOfOrderPage.timeEnd))) {
	    verification = true;
	}
	Assert.assertTrue(verification);
	// PostCondition
	BrowserManager.openBrowser();
	LoginPage login = new LoginPage();
	AdminPage home = login.setUserName(PropertiesReader.getUserName())
		.setPassword(PropertiesReader.getPassword())
		.clickOnSigInButton().refreshPage();
	ConferenceRoomsPage room = home.leftMenu.clickOnConferenceRoomsButton()
		.openConfigurationPage(roomName).clickOnOutOfOrder()
		.activeOutOfOrder().clickOnSave();
    }

}
