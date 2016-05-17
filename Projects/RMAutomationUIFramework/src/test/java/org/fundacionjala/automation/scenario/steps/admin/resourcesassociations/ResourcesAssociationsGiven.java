package org.fundacionjala.automation.scenario.steps.admin.resourcesassociations;
import org.fundacionjala.automation.framework.pages.admin.login.LoginActions;

import cucumber.api.java.en.Given;

public class ResourcesAssociationsGiven {
    

    @Given("^I am on the conference rooms page$")
    public void i_am_on_the_conference_rooms_page() throws Throwable {
    	
    	LoginActions
		    		.ExecuteLogin()
		    		.leftMenu
			        .clickOnConferenceRoomsButton();
    }

}
