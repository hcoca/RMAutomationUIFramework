package org.fundacionjala.automation.scenario.steps.admin.conferenceRoom;

import org.fundacionjala.automation.framework.pages.admin.conferencerooms.ConferenceRoomsPage;
import org.fundacionjala.automation.framework.pages.admin.home.AdminPage;
import org.fundacionjala.automation.framework.pages.admin.login.LoginPage;
import org.fundacionjala.automation.framework.pages.tablet.settings.ConnectionPage;
import org.fundacionjala.automation.framework.pages.tablet.settings.NavigationPage;
import org.fundacionjala.automation.framework.utils.common.PropertiesReader;

import cucumber.api.java.en.When;

public class ConferenceRoomWhenSteps {

    private ConferenceRoomsPage goToConferenceRoomPage()
	    throws InterruptedException {
	return new AdminPage().leftMenu
		.clickOnConferenceRoomsButton();
    }

    @When("^I enabled \"([^\"]*)\" room$")
    public void enableARoom(String roomName) throws Throwable {
	ConferenceRoomsPage rooms = new ConferenceRoomsPage();
	String message = "Room " + roomName + " was disabled";	
	rooms = goToConferenceRoomPage();
	while(message.equals("Room " + roomName + " was disabled") || message.contains("Error on trying to")){
	    message = rooms
		    .clickOnTurnOnOffButton(roomName)
		    .getMessage();
	}
    }

    @When("^I disabled \"([^\"]*)\" room$")
    public void disableARoom(String roomName) throws Throwable {
	ConferenceRoomsPage rooms = new ConferenceRoomsPage();
	String message = "Room " + roomName + " was enabled";
	rooms = goToConferenceRoomPage();
	while(message.equals("Room " + roomName + " was enabled") || message.contains("Error on trying to")){
	    message = rooms
		    .clickOnTurnOnOffButton(roomName)
		    .getMessage();
	}
    }

    @When("^I go to conference room page$")
    public void i_go_to_conference_room_page() throws Throwable {
	goToConferenceRoomPage();
    }

    @When("^I search rooms with this criteria \"([^\"]*)\"$")
    public void i_search_rooms_with_this_criteria(String roomCriteria)
	    throws Throwable {
	goToConferenceRoomPage().typeOnFilterTextbox(roomCriteria);
    }
    
    @When("^I select a page size \"([^\"]*)\"$")
    public void i_select_a_page_size(String pageSize) throws Throwable {
	AdminPage home = new AdminPage();
	home.leftMenu.clickOnConferenceRoomsButton()
		.selectPageSize(pageSize);
    }
    
    @When("^I set the page \"([^\"]*)\"$")
    public void i_set_the_page(String page) throws Throwable {
	new ConferenceRoomsPage().setPage(page);
    }
    
    @When("^I did click on the icon of Out of Order on the \"([^\"]*)\" room$")
    public void clickOnIconOutOfOrder(String roomName) throws Throwable {
	AdminPage home = new AdminPage();
	home.leftMenu.clickOnConferenceRoomsButton()
		.selectOutOfOrderIcon(roomName);
    }

    @When("^I sign in to Tablet page using the \"([^\"]*)\" room$")
    public void signInTabletOnRoom(String roomName) throws Throwable {
	ConnectionPage connection = new ConnectionPage();
	NavigationPage navigation = connection
		.setUpServiceURL(PropertiesReader.getServiceURL())
		.clickOnSaveButton().clickOnNavigationButton();

	navigation.clickOnRoomToggleButton()
		.selectConferenceRoom(roomName).clickOnSaveButton().topMenu
		.clickOnHomeButton();
    }
    
    @When("^I create an Out of Order on \"([^\"]*)\" room with a time by default$")
    public void createOutOfOrderTimeDefault(String roomName) throws Throwable {
	AdminPage adminPage = new AdminPage();
	adminPage.leftMenu.clickOnConferenceRoomsButton()
                        	 .openConfigurationPage(roomName)
                        	 .clickOnOutOfOrder()
                        	 .setTimeBeginUp()
                        	 .setTimeEndUp()
                        	 .storeFromTime()
                        	 .storeToTime()
                        	 .activeOutOfOrder()
                        	 .clickOnSave();
    }
    
    @When("^I create an Out Of Order on \"([^\"]*)\" room with a title defined$")
    public void i_create_an_Out_Of_Order_on_room_with_a_title_defined(String roomName) throws Throwable {
	AdminPage adminPage = new AdminPage();
	adminPage.leftMenu.clickOnConferenceRoomsButton()
        	 .openConfigurationPage(roomName)
        	 .clickOnOutOfOrder()
        	 .setTimeBeginUp()
        	 .setTimeEndUp()
        	 .clickOnBoxButon()
        	 .ClickOnClosedForMaintenanceLink()
        	 .activeOutOfOrder()
        	 .clickOnSave();
    }
    
    @When("^I create an OuOfOrder on \"([^\"]*)\" with a date in the past$")
    public void createOuOfOrderWithPastDate(String roomName) throws Throwable {
	AdminPage adminPage = new AdminPage();
	adminPage.leftMenu.clickOnConferenceRoomsButton()
                		.openConfigurationPage(roomName)
                		.clickOnOutOfOrder()
                		.setTimeBeginDown()
                		.setTimeEndDown()
                		.activeOutOfOrder()
                       	 	.clickOnSaveButton();
    }
    
    @When("^I disable this OutOfOrder on \"([^\"]*)\" room$")
    public void i_disable_this_OutOfOrder_on_room(String roomName)
	    throws Throwable {
	LoginPage login = new LoginPage();
	AdminPage adminPage = new AdminPage();
	login.setUserName(PropertiesReader.getUserName())
		.setPassword(PropertiesReader.getPassword())
		.clickOnSigInButton().refreshPage();
	adminPage.leftMenu
		.clickOnConferenceRoomsButton()
		.openConfigurationPage(roomName)
		.clickOnOutOfOrder()
		.activeOutOfOrder()
       	 	.clickOnSave();
    }
    
    @When("^I create an OuOfOrder on \"([^\"]*)\" room with a time in the past$")
    public void createOuOfOrderWithPastTime(String roomName) throws Throwable {
	AdminPage adminPage = new AdminPage();
	adminPage.leftMenu.clickOnConferenceRoomsButton()
                    		 .openConfigurationPage(roomName)
                    		 .clickOnOutOfOrder()
                    		 .setTimeBeginDown()
                    		 .setTimeEndDown()
                    		 .activeOutOfOrder()
                        	 .clickOnSaveButton();
                        	 
    }
}
