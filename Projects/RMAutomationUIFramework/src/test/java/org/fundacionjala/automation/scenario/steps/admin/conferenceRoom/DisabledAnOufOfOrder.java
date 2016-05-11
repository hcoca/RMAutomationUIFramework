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

public class DisabledAnOufOfOrder {
	AdminPage home;
	ConferenceRoomsPage room;
	HomePage homeTablet;

	@When("^I disable this OutOfOrder on \"([^\"]*)\" room$")
	public void i_disable_this_OutOfOrder_on_room(String roomName) throws Throwable {
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

	@Then("^The Out Of Order on \"([^\"]*)\" room should has been disabled correctly with the \"([^\"]*)\" title corresponding$")
	public void the_Out_Of_Order_on_room_should_has_been_disabled_correctly_with_the_title_corresponding(String roomName, String titleOutOfOrder) throws Throwable {
	    boolean verification = true;
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
	    	verification = false;
	    }
	    Assert.assertTrue(verification);
	}
}
