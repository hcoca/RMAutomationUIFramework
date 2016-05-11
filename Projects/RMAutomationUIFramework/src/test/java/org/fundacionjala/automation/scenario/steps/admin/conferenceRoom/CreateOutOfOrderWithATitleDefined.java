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

public class CreateOutOfOrderWithATitleDefined {
	AdminPage home;
	ConferenceRoomsPage room;
	HomePage homeTablet;
	
	@Given("^I logged to RoomManagerAdmin$")
	public void i_logged_to_RoomManagerAdmin() throws Throwable {
		BrowserManager.openBrowser();
		LoginPage login = new LoginPage();
		home = login.setUserName("Administrator")
					.setPassword("Control*123")
					.clickOnSigInButton()
					.refreshPage();
	}

	@When("^I create an Out of Order on \"([^\"]*)\" room with a title defined$")
	public void i_create_an_Out_of_Order_on_room_with_a_title_defined(String roomName) throws Throwable {
		room = home.leftMenu.clickOnConferenceRoomsButton()
							.openConfigurationPage(roomName)
							.clickOnOutOfOrder()
							.setTimeBeginUp()
							.setTimeEndUp()
							.clickOnBoxButon()
							.ClickOnClosedForMaintenanceLink()
							.activeOutOfOrder()
							.clickOnSave();
	}
	
	@Then("^The Out Of Order on \"([^\"]*)\" room should have been created an OutOfOrder with the \"([^\"]*)\" title corresponding$")
	public void the_Out_Of_Order_on_room_should_have_been_created_an_OutOfOrder_with_the_title_corresponding(String roomName, String titleOutOfOrder) throws Throwable {
	    boolean verification = false;
	    ConnectionPage connection = new ConnectionPage();
	    NavigationPage navigation = connection.setUpServiceURL(PropertiesReader.getServiceURL())
						  .clickOnSaveButton()
						  .clickOnNavigationButton();
	    	
	    homeTablet = navigation.clickOnRoomToggleButton()
				   .selectConferenceRoom(roomName)
				   .clickOnSaveButton()
				   .topMenu
				   .clickOnHomeButton();
	    
	    WebElement title =BrowserManager.getDriver().findElement(By.xpath(HomeMap.TITLE_OUT_OF_ORDER));
	    if(title.getText().contains(titleOutOfOrder)){
	    	verification = true;
	    }
	    Assert.assertTrue(verification);
	    //PostCondition
	    BrowserManager.openBrowser();
		LoginPage login = new LoginPage();
		home = login.setUserName(PropertiesReader.getUserName())
					.setPassword(PropertiesReader.getPassword())
					.clickOnSigInButton()
					.refreshPage();
		room = home.leftMenu.clickOnConferenceRoomsButton()
				    .openConfigurationPage(roomName)
				    .clickOnOutOfOrder()
				    .activeOutOfOrder()
				    .clickOnSave();
	}
	
}
