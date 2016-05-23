package org.fundacionjala.automation.scenario.steps.tablet.search;

import org.fundacionjala.automation.framework.pages.admin.home.AdminPage;
import org.fundacionjala.automation.framework.pages.admin.locations.LocationPage;
import org.fundacionjala.automation.framework.pages.admin.login.LoginActions;
import org.fundacionjala.automation.framework.pages.tablet.home.HomePage;
import org.fundacionjala.automation.framework.pages.tablet.settings.ConnectionPage;
import org.fundacionjala.automation.framework.pages.tablet.settings.NavigationPage;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.DatabaseConnection;
import org.fundacionjala.automation.framework.utils.common.PropertiesReader;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;

public class SearchGivenSteps {
	
	
	@Given("^I am on search page$")
	public void i_am_on_search_page() throws Throwable {
		
		BrowserManager.openBrowser();
		ConnectionPage connection = new ConnectionPage();
		NavigationPage navigation = connection
			.setUpServiceURL(PropertiesReader.getServiceURL())
			.clickOnSaveButton().clickOnNavigationButton();

		HomePage home = 
		    navigation
		    .clickOnRoomToggleButton()
			.selectConferenceRoom("Room118").clickOnSaveButton().topMenu
			.clickOnHomeButton();
		
	        home.clickOnSearchButton();
	}
	
	@Given("^I am in location page of Room Manager Admin$")
	public void i_am_in_location_page_of_Room_Manager_Admin() throws Throwable {
		
		AdminPage home = LoginActions.ExecuteLogin();
		
			 home
			  .leftMenu
			  .clickOnLocationsButton();
	}

	@Given("^I associate the location \"([^\"]*)\" to \"([^\"]*)\"$")
	public void i_associate_the_location_to(String location, String room) throws Throwable {
		
		LocationPage locationPage = new LocationPage();
		locationPage
		            .doubleClickOnALocation(location)
		            .clickOnLocationAssociationLink()
		            .setRoomName(room)
		            .clickOnAssigned()
		            .clickOnAddAvailableRoom()
		            .clickOnSaveButton();
	}
	
	@Given("^I am on the conference rooms page of Room Manager$")
	public void i_am_on_the_conference_rooms_page_of_Room_Manager() throws Throwable {

    	LoginActions
		    		.ExecuteLogin()
		    		.leftMenu
			        .clickOnConferenceRoomsButton();
	}
}
