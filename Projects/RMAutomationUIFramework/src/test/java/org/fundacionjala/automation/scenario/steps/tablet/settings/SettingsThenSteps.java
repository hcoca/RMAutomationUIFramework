package org.fundacionjala.automation.scenario.steps.tablet.settings;

import org.fundacionjala.automation.framework.maps.tablet.settings.ConnectionMap;
import org.fundacionjala.automation.framework.maps.tablet.settings.NavigationMap;
import org.fundacionjala.automation.framework.pages.tablet.settings.NavigationPage;
import org.fundacionjala.automation.framework.utils.common.ExplicitWait;
import org.junit.Assert;

import cucumber.api.java.en.Then;

public class SettingsThenSteps {
    
    @Then("^A message that describes that the connection has been successful should be displayed$")
    public void a_message_that_describes_that_the_connection_has_been_successful_should_be_displayed() throws Throwable {
	boolean verification = false;
        if(ExplicitWait.waitForElement(ConnectionMap.MESSAGE_STATUS, 20)){
	   verification = true;
        }
        Assert.assertTrue(verification); 
    }

    @Then("^The list of Conference Rooms should have the corresponding rooms$")
    public void the_list_of_Conference_Rooms_should_have_the_corresponding_rooms() throws Throwable {
	NavigationPage navigation = new NavigationPage();
	Assert.assertTrue(navigation.clickOnRoomToggleButton()
				    .verifyIfRoomsExist());
    }

    @Then("^A message that describes that the connection with the room has been successful should be displayed$")
    public void a_message_that_describes_that_the_connection_with_the_room_has_been_successful_should_be_displayed() throws Throwable {
	boolean verification = false;
        if(ExplicitWait.waitForElement(NavigationMap.MESSAGE_STATUS, 20)){
	   verification = true;
        }
        Assert.assertTrue(verification); 
    }

    @Then("^The Connection page should be displayed with the elements corresponding$")
    public void the_Connection_page_should_be_displayed_with_the_elements_corresponding() throws Throwable {
	boolean verification = false;
        if(ExplicitWait.waitForElement(ConnectionMap.SERVICE_URL_TEXT_FIELD, 20)){
	   verification = true;
        }
        Assert.assertTrue(verification); 
    }

    @Then("^The Navigation page should be displayed with the elements corresponding$")
    public void the_Navigation_page_should_be_displayed_with_the_elements_corresponding() throws Throwable {
	boolean verification = false;
        if((ExplicitWait.waitForElement(NavigationMap.DEFAULT_ROOM_TOGGLE_BUTTON, 20))&& 
        	(ExplicitWait.waitForElement(NavigationMap.INACTIVITY_TIMEOUT_FIELD, 20))){
	   verification = true;
        }
        Assert.assertTrue(verification); 
    }
    
    @Then("^The results displayed should be according the filter inserted \"([^\"]*)\"$")
    public void the_results_displayed_should_be_according_the_filter_inserted(String filter) throws Throwable {
	NavigationPage navigation = new NavigationPage();
	navigation.verifyIfRoomsExistAccordingFilter(filter);
    }

}
