package org.fundacionjala.automation.scenario.steps.tablet.settings;

import org.fundacionjala.automation.framework.pages.tablet.settings.ConnectionPage;
import org.fundacionjala.automation.framework.pages.tablet.settings.NavigationPage;

import cucumber.api.java.en.When;

public class SettingsWhenSteps {
 
    @When("^I select \"([^\"]*)\" room in the Default Conference Room parameter$")
    public void i_select_room_in_the_Default_Conference_Room_parameter(String roomName) throws Throwable {
	ConnectionPage connection = new ConnectionPage();
	
	connection.clickOnNavigationButton()
		  .clickOnRoomToggleButton()
		  .selectConferenceRoom(roomName)
		  .clickOnSaveButton();
    }
    
    @When("^I go to Connection page$")
    public void i_go_to_Connection_page() throws Throwable {
	ConnectionPage connection = new ConnectionPage();
	connection.clickOnConnectionButton();
    }

    @When("^I go to Navigation page$")
    public void i_go_to_Navigation_page() throws Throwable {
	NavigationPage navigation = new NavigationPage();
	navigation.clickOnNavigationButton();
    }

    @When("^I insert a kind of filter \"([^\"]*)\" on the Default Conference Room parameter$")
    public void i_insert_a_kind_of_filter_on_the_Default_Conference_Room_parameter(String filter) throws Throwable {
	NavigationPage navigation = new NavigationPage();
	navigation.clickOnRoomToggleButton().insertFilterSearch(filter);
    }


}
