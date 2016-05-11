package org.fundacionjala.automation.scenario.steps.admin.conferenceRoom;

import org.fundacionjala.automation.framework.maps.tablet.home.HomeMap;
import org.fundacionjala.automation.framework.pages.admin.conferencerooms.ConferenceRoomsPage;
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

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AvailableOutOfOrderSteps {

    @Given("^I create an Out of Order on a specific \"([^\"]*)\" room$")
    public void createOutOfOrderOnRoom(String roomName) throws Throwable {
	AdminPage home = new AdminPage();
	ConferenceRoomsPage room = new ConferenceRoomsPage();
	room = home.leftMenu.clickOnConferenceRoomsButton()
            		.openConfigurationPage(roomName)
            		.clickOnOutOfOrder()
            		.setTimeBeginUp()
            		.setTimeEndUp()
            		.clickOnBoxButon()
            		.ClickOnClosedForMaintenanceLink()
            		.clickOnSave();
    }

    @When("^I did click on the icon of Out of Order on the \"([^\"]*)\" room$")
    public void clickOnIconOutOfOrder(String roomName) throws Throwable {
	AdminPage home = new AdminPage();
	ConferenceRoomsPage room = new ConferenceRoomsPage();
	room = home.leftMenu.clickOnConferenceRoomsButton()
			    .selectOutOfOrderIcon(roomName);
    }

    @When("^I sign in to Tablet page using the \"([^\"]*)\" room$")
    public void signInTabletOnRoom(String roomName) throws Throwable {
	ConnectionPage connection = new ConnectionPage();
	NavigationPage navigation = connection
		.setUpServiceURL(PropertiesReader.getServiceURL())
		.clickOnSaveButton()
		.clickOnNavigationButton();

	HomePage homeTablet = navigation.clickOnRoomToggleButton()
		.selectConferenceRoom(roomName).clickOnSaveButton().topMenu
		.clickOnHomeButton();
    }

    @Then("^The \"([^\"]*)\" room should changes its status to non-available with the \"([^\"]*)\" title corresponding$")
    public void validateRoomChangesStatusNonAvailable(String roomName, String titleOutOfOrder) throws Throwable {
	AdminPage home = new AdminPage();
	ConferenceRoomsPage room = new ConferenceRoomsPage();
	boolean verification = false;
	
	WebElement title = BrowserManager.getDriver().findElement(By.xpath(HomeMap.TITLE_OUT_OF_ORDER));
	if (title.getText().contains(titleOutOfOrder)) {
	    verification = true;
	}
	Assert.assertTrue(verification);
	
	//PostCondition
	BrowserManager.openBrowser();
	LoginPage login = new LoginPage();
	home = login.setUserName(PropertiesReader.getUserName())
    		    .setPassword(PropertiesReader.getPassword())
    		    .clickOnSigInButton().refreshPage();
	room = home.leftMenu.clickOnConferenceRoomsButton()
    		            .openConfigurationPage(roomName)
    		            .clickOnOutOfOrder()
    		            .activeOutOfOrder()
    		            .clickOnSave();
    
    }

}
