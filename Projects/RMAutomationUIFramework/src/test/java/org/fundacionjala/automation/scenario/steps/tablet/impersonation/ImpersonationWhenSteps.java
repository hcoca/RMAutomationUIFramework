package org.fundacionjala.automation.scenario.steps.tablet.impersonation;

import org.fundacionjala.automation.framework.pages.tablet.home.HomePage;
import org.fundacionjala.automation.framework.pages.tablet.scheduler.CredentialsPage;
import org.fundacionjala.automation.framework.pages.tablet.scheduler.SchedulerPage;
import org.fundacionjala.automation.framework.pages.tablet.settings.ConnectionPage;
import org.fundacionjala.automation.framework.pages.tablet.settings.NavigationPage;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.PropertiesReader;

import cucumber.api.java.en.When;

public class ImpersonationWhenSteps {

    	@When("^I schedule a new meeting with \"([^\"]*)\" subject using Impersonation in the Room \"([^\"]*)\"$")
   	public void createMeetingUsingImpersonation(String subject, String roomName) throws Throwable {
        	BrowserManager.openBrowser();
        	ConnectionPage connection = new ConnectionPage();

   		NavigationPage navigation = connection
   				.setUpServiceURL(PropertiesReader.getServiceURL())
   				.clickOnSaveButton()
   				.clickOnNavigationButton();

   		HomePage home = navigation
   				.clickOnRoomToggleButton()
   				.selectConferenceRoom(roomName)
   				.clickOnSaveButton()
   				.topMenu
   				.clickOnHomeButton();

   		SchedulerPage scheduler = home
   				.clickOnScheduleButton()
   				.setOrganizer(PropertiesReader.getExchangeOrganizerUser())
   				.setSubject(subject)
   				.setAttende(PropertiesReader.getExchangeInviteMail());

   		CredentialsPage credentials = scheduler
   				.clickOnCreateButton();
   		
   		scheduler = credentials
   				.clickCreateAsCheckBox()
   				.setUserName(PropertiesReader.getExchangeInviteUser())
   				.setPassword(PropertiesReader.getExchangeInvitePwd())
   				.clickOkButton();
   	}
    
    	@When("^I cancel a meeting with \"([^\"]*)\" subject using Impersonation in the Room \"([^\"]*)\"$")
    	public void cancelMeetingUsingImpersonation(String subject, String roomName) throws Throwable {	
		BrowserManager.openBrowser();
		ConnectionPage connection = new ConnectionPage();

		NavigationPage navigation = connection
				.setUpServiceURL(PropertiesReader.getServiceURL())
				.clickOnSaveButton()
				.clickOnNavigationButton();

		HomePage home = navigation
				.clickOnRoomToggleButton()
				.selectConferenceRoom(roomName)
				.clickOnSaveButton()
				.topMenu
				.clickOnHomeButton();
		
		SchedulerPage scheduler = home
				.clickOnScheduleButton();
		
		CredentialsPage credentials = scheduler
				.clickOnMeetingButton(subject)
				.clickRemoveButton();
		
		credentials
			.clickCancelAsCheckBox()
			.setUserName(PropertiesReader.getExchangeInviteUser())
			.setPassword(PropertiesReader.getExchangeInvitePwd())
			.clickOkButton();
    	}
    	
    	@When("^I try to create a new meeting with \"([^\"]*)\" subject using Impersonation in the Room \"([^\"]*)\"$")
    	public void tryToCreateMeetingUsingImpersonation(String subject, String roomName) throws Throwable {
    	    	BrowserManager.openBrowser();
        	ConnectionPage connection = new ConnectionPage();
    
        	NavigationPage navigation = connection
    				.setUpServiceURL(PropertiesReader.getServiceURL())
    				.clickOnSaveButton()
    				.clickOnNavigationButton();
    
        	HomePage home = navigation
    				.clickOnRoomToggleButton()
    				.selectConferenceRoom(roomName)
    				.clickOnSaveButton()
    				.topMenu
    				.clickOnHomeButton();
    
        	SchedulerPage scheduler = home.clickOnScheduleButton()
    				.setOrganizer(PropertiesReader.getExchangeConnectUserName())
    				.setSubject(subject)
    				.setAttende(PropertiesReader.getExchangeInviteMail());
    
        	scheduler
        		.clickOnCreateButton();
    	}
    	
    	@When("^I try to cancel a meeting with \"([^\"]*)\" subject using Impersonation in the Room \"([^\"]*)\"$")
    	public void tryToCancelMeetingUsingImpersonation(String subject, String roomName) throws Throwable {
            	BrowserManager.openBrowser();
        	ConnectionPage connection = new ConnectionPage();
        
        	NavigationPage navigation = connection
        			.setUpServiceURL(PropertiesReader.getServiceURL())
        			.clickOnSaveButton()
        			.clickOnNavigationButton();
        
        	HomePage home = navigation
        			.clickOnRoomToggleButton()
        			.selectConferenceRoom(roomName)
        			.clickOnSaveButton()
        			.topMenu
        			.clickOnHomeButton();
        	
        	SchedulerPage scheduler = home
        			.clickOnScheduleButton();
        	
        	scheduler
        		.clickOnMeetingButton(subject)
        		.clickRemoveButton();
    	}
    	
    	@When("^I try to create a new meeting with \"([^\"]*)\" subject in the Room \"([^\"]*)\"$")
    	public void tryToCreateMeeting(String subject, String roomName) throws Throwable {
    	    	BrowserManager.openBrowser();
        	ConnectionPage connection = new ConnectionPage();
    
        	NavigationPage navigation = connection
    				.setUpServiceURL(PropertiesReader.getServiceURL())
    				.clickOnSaveButton()
    				.clickOnNavigationButton();
    
        	HomePage home = navigation
    				.clickOnRoomToggleButton()
    				.selectConferenceRoom(roomName)
    				.clickOnSaveButton()
    				.topMenu
    				.clickOnHomeButton();
    
        	SchedulerPage scheduler = home.clickOnScheduleButton()
    				.setOrganizer(PropertiesReader.getExchangeConnectUserName())
    				.setSubject(subject)
    				.setAttende(PropertiesReader.getExchangeInviteMail());
    
        	scheduler
        		.clickOnCreateButton();
    	}
    	
    	@When("^I try to cancel a meeting with \"([^\"]*)\" subject in the Room \"([^\"]*)\"$")
    	public void tryToCancelMeeting(String subject, String roomName) throws Throwable {
            	BrowserManager.openBrowser();
        	ConnectionPage connection = new ConnectionPage();
        
        	NavigationPage navigation = connection
        			.setUpServiceURL(PropertiesReader.getServiceURL())
        			.clickOnSaveButton()
        			.clickOnNavigationButton();
        
        	HomePage home = navigation
        			.clickOnRoomToggleButton()
        			.selectConferenceRoom(roomName)
        			.clickOnSaveButton()
        			.topMenu
        			.clickOnHomeButton();
        	
        	SchedulerPage scheduler = home
        			.clickOnScheduleButton();
        	
        	scheduler
        		.clickOnMeetingButton(subject)
        		.clickRemoveButton();
    	}
    	
    	@When("^I schedule a new meeting with \"([^\"]*)\" subject in the Room \"([^\"]*)\"$")
   	public void createMeeting(String subject, String roomName) throws Throwable {
        	BrowserManager.openBrowser();
        	ConnectionPage connection = new ConnectionPage();

   		NavigationPage navigation = connection
   				.setUpServiceURL(PropertiesReader.getServiceURL())
   				.clickOnSaveButton()
   				.clickOnNavigationButton();

   		HomePage home = navigation
   				.clickOnRoomToggleButton()
   				.selectConferenceRoom(roomName)
   				.clickOnSaveButton()
   				.topMenu
   				.clickOnHomeButton();

   		SchedulerPage scheduler = home
   				.clickOnScheduleButton()
   				.setOrganizer(PropertiesReader.getExchangeOrganizerUser())
   				.setSubject(subject)
   				.setAttende(PropertiesReader.getExchangeInviteMail());

   		CredentialsPage credentials = scheduler
   				.clickOnCreateButton();
   		
   		scheduler = credentials
   				.setUserName(PropertiesReader.getExchangeOrganizerUser())
   				.setPassword(PropertiesReader.getExchangeOrganizerPwd())
   				.clickOkButton();
   	}
}
