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

public class CreateOutOfOrderWithATitleDefined {
	AdminPage home;
	ConferenceRoomsPage room;
	
	@Given("^I logged to RoomManagerAdmin$")
	public void i_logged_to_RoomManagerAdmin() throws Throwable {
		BrowserManager.openBrowser();
		LoginPage login = new LoginPage();
		home = login.setUserName("Administrator")
					.setPassword("Control*123")
					.clickOnSigInButton()
					.refreshPage();
	}

	@When("^I create an Out of Order with a title defined$")
	public void i_create_an_Out_of_Order_with_a_title_defined() throws Throwable {
		room = home.leftMenu.clickOnConferenceRoomsButton()
					.openConfigurationPage("Room02")
					.clickOnOutOfOrder()
					.clickOnBoxButon()
					.ClickOnClosedForMaintenanceLink()
					.activeOutOfOrder()
					.clickOnSave();
	}
	
	@Then("^I validate if the Out Of Order has been created with the title defined$")
	public void i_validate_if_the_Out_Of_Order_has_been_created_with_the_title_defined() throws Throwable {
		boolean verification = false;
		ConnectionPage connection = new ConnectionPage();
		NavigationPage navigation = connection
				    	.setUpServiceURL("http://172.20.208.84:4040/")
				    	.clickOnSaveButton()
				    	.clickOnNavigationButton();
	    	
	    HomePage home =	navigation
				    	.clickOnRoomToggleButton()
				    	.selectConferenceRoom("Room02")
				    	.clickOnSaveButton()
				    	.topMenu
				    	.clickOnHomeButton();
	    
	    WebElement title =BrowserManager.getDriver().findElement(By.xpath("//div[@ng-bind='next._title']"));
	    if(title.getText().contains("Closed for maintenance")){
	    	verification = true;
	    }
	    Assert.assertTrue(verification);
	}
}
