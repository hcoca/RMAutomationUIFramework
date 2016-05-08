package org.fundacionjala.automation.scenario.steps.admin.conferenceRoom;

import org.fundacionjala.automation.framework.pages.admin.conferencerooms.ConferenceRoomsPage;
import org.fundacionjala.automation.framework.pages.admin.conferencerooms.OutOfOrderPage;
import org.fundacionjala.automation.framework.pages.admin.home.AdminPage;
import org.fundacionjala.automation.framework.pages.admin.login.LoginPage;
import org.fundacionjala.automation.framework.pages.tablet.home.HomePage;
import org.fundacionjala.automation.framework.pages.tablet.settings.ConnectionPage;
import org.fundacionjala.automation.framework.pages.tablet.settings.NavigationPage;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CreateOutOfOrderWithATimeDefined {
	AdminPage home;
	ConferenceRoomsPage room;
	
	@Given("^I logged to Admin Room Manager$")
	public void i_logged_to_Admin_Room_Manager() throws Throwable {
		BrowserManager.openBrowser();
		LoginPage login = new LoginPage();
		home = login.setUserName("Administrator")
					.setPassword("Control*123")
					.clickOnSigInButton()
					.refreshPage();
	}

	@When("^I create an Out of Order on \"([^\"]*)\" room$")
	public void i_create_an_Out_of_Order_on_room(String arg1) throws Throwable {
		room = home.leftMenu.clickOnConferenceRoomsButton()
							.openConfigurationPage(arg1)
							.clickOnOutOfOrder()
							.setTimeBeginUp()
							.setTimeEndUp()
							.storeFromTime()
							.storeToTime()
							.activeOutOfOrder()
							.clickOnSave();
	}

	@Then("^I validate if the Out Of Order on \"([^\"]*)\" room has been created with the time interval defined$")
	public void i_validate_if_the_Out_Of_Order_on_room_has_been_created_with_the_time_interval_defined(String arg1) throws Throwable {
		boolean verification = false;
		ConnectionPage connection = new ConnectionPage();
		NavigationPage navigation = connection
				    	.setUpServiceURL("http://172.20.208.84:4040/")
				    	.clickOnSaveButton()
				    	.clickOnNavigationButton();
	    	
	    HomePage hometablet =	navigation
						    	.clickOnRoomToggleButton()
						    	.selectConferenceRoom(arg1)
						    	.clickOnSaveButton()
						    	.topMenu
						    	.clickOnHomeButton();
	    WebElement time =BrowserManager.getDriver().findElement(By.xpath("//div[@class='time']/div"));
	    if(time.getText().contains(OutOfOrderPage.timeBegin) && time.getText().contains(OutOfOrderPage.timeEnd)){
	    	verification = true;
	    }
	    Assert.assertTrue(verification);
	    //PostCondition
	    BrowserManager.openBrowser();
		LoginPage login = new LoginPage();
		home = login.setUserName("Administrator")
					.setPassword("Control*123")
					.clickOnSigInButton()
					.refreshPage();
		room = home.leftMenu.clickOnConferenceRoomsButton()
							.openConfigurationPage(arg1)
							.clickOnOutOfOrder()
							.activeOutOfOrder()
							.clickOnSave();
	}

}
