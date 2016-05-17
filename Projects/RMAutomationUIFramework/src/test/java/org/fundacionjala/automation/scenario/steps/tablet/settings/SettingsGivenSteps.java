package org.fundacionjala.automation.scenario.steps.tablet.settings;

import org.fundacionjala.automation.framework.pages.tablet.settings.ConnectionPage;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.PropertiesReader;

import cucumber.api.java.en.Given;

public class SettingsGivenSteps {
    
    @Given("^I insert the Service URL parameter on Connection-Tablet page$")
    public void i_insert_the_Service_URL_parameter_on_Connection_Tablet_page() throws Throwable {
	BrowserManager.openBrowser();
	ConnectionPage connection = new ConnectionPage();

	connection.setUpServiceURL(PropertiesReader.getServiceURL())
		  .clickOnSaveButton();
    }

}
