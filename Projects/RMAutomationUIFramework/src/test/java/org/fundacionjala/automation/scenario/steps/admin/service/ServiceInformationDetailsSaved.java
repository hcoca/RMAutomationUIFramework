package org.fundacionjala.automation.scenario.steps.admin.service;

import org.fundacionjala.automation.framework.pages.admin.emailserver.AddEmailServerPage;
import org.fundacionjala.automation.framework.pages.admin.emailserver.EmailServerPage;
import org.fundacionjala.automation.framework.pages.admin.login.LoginPage;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.testng.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ServiceInformationDetailsSaved {
	private String myExpectedDescription = "My Customized Description";
	
	@Given("^There is no services added$")
	public void there_is_no_services_added() throws Throwable {
		BrowserManager.openBrowser();
		LoginPage login = new LoginPage();
		
		EmailServerPage emailServer = login
				.setUserName("SamuelSahonero")
				.setPassword("Control*123")
				.clickOnSigInButton()
				.refreshPage()
				.leftMenu
				.clickOnEmailServerButton();
		
		boolean isAddButtonPresent = emailServer.findAddButton();
		
		if(isAddButtonPresent == false) {
			
			emailServer
				.clickOnRemoveButton()
				.clickOnYesButton();
		}
	}

	@When("^I add a new Exchange Email Server$")
	public void i_add_a_new_Exchange_Email_Server() throws Throwable {
		LoginPage login = new LoginPage();
		
		EmailServerPage emailServer = login
				.setUserName("SamuelSahonero")
				.setPassword("Control*123")
				.clickOnSigInButton()
				.refreshPage()
				.leftMenu
				.clickOnEmailServerButton();
		
		AddEmailServerPage addEmailServer = emailServer
				.clickOnAddButton();
		
		emailServer = addEmailServer
				.setDomainServer("roommanager.local")
				.setUserName("Administrator")
				.setPassword("Control*123")
				.setDescription(myExpectedDescription)
				.clickSaveButton();
	}

	@Then("^Service infomation is saved$")
	public void service_infomation_is_saved() throws Throwable {
		EmailServerPage emailServer = new EmailServerPage();
		
		String myActualDescription = emailServer
				.clickOnServerButton()
				.getEmailServerDescription();
		
		Assert.assertEquals(myActualDescription, myExpectedDescription);
	}
}
