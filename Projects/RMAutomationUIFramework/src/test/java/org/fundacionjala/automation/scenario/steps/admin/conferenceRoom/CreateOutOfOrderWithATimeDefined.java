package org.fundacionjala.automation.scenario.steps.admin.conferenceRoom;

import org.fundacionjala.automation.framework.pages.admin.conferencerooms.ConferenceRoomsPage;
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
	String beginTime = "12";
	String endTime = "01";
	
	@Given("^I logged to Admin Room Manager$")
	public void i_logged_to_Admin_Room_Manager() throws Throwable {
		BrowserManager.openBrowser();
		LoginPage login = new LoginPage();
		home = login.setUserName("Administrator")
					.setPassword("Control*123")
					.clickOnSigInButton()
					.refreshPage();
	}
	
	@When("^I create an Out of Order$")
	public void i_create_an_Out_of_Order() throws Throwable {
		room = home.leftMenu.clickOnConferenceRoomsButton()
	   						.openConfigurationPage("Room01")
	   						.clickOnOutOfOrder()
	   						.insertFromValue(beginTime)
	   						.insertToValue(endTime)
	   						.activeOutOfOrder()
	   						.clickOnSave();
	}

	@Then("^I validate if the Out Of Order has been created with the time interval defined$")
	public void i_validate_if_the_Out_Of_Order_has_been_created_with_the_time_interval_defined() throws Throwable {
		boolean verification = false;
		ConnectionPage connection = new ConnectionPage();
		NavigationPage navigation = connection
				    	.setUpServiceURL("http://172.20.208.84:4040/")
				    	.clickOnSaveButton()
				    	.clickOnNavigationButton();
	    	
	    HomePage home =	navigation
				    	.clickOnRoomToggleButton()
				    	.selectConferenceRoom("Room01")
				    	.clickOnSaveButton()
				    	.topMenu
				    	.clickOnHomeButton();
	    WebElement time =BrowserManager.getDriver().findElement(By.xpath("//div[@class='time']/div"));
	    if(time.getText().contains(beginTime) && time.getText().contains(endTime)){
	    	verification = true;
	    }
	    Assert.assertTrue(verification);
	    
	}
}
