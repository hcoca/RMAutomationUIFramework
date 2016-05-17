package org.fundacionjala.automation.scenario.steps.tablet.search;

import org.fundacionjala.automation.framework.pages.tablet.home.HomePage;
import org.fundacionjala.automation.framework.pages.tablet.settings.ConnectionPage;
import org.fundacionjala.automation.framework.pages.tablet.settings.NavigationPage;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.PropertiesReader;

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
			.selectConferenceRoom("Room002").clickOnSaveButton().topMenu
			.clickOnHomeButton();
		
	   home.clickOnSearchButton();
	
	}
	
}
