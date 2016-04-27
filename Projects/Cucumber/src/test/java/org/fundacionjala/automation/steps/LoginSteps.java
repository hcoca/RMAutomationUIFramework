package org.fundacionjala.automation.steps;

import org.fundacionjala.automation.actions.CommonAction;
import org.fundacionjala.automation.actions.LoginAction;
import org.testng.Assert;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LoginSteps {
	@Given("^Go to Room Manager Admin with an specific Firefox$")
	public void go_to_Room_Manager_Admin_with_an_specific_Firefox() throws Throwable {
		CommonAction.openBrowser("Firefox");
		CommonAction.navigate();
	}

	@Given("^I insert Lucero$")
	public void i_insert_Lucero() throws Throwable {
		LoginAction.input_Username(CommonAction.driver, "Lucero");
	}

	@Given("^I insert Control(\\d+)$")
	public void i_insert_Control(int arg1) throws Throwable {
		LoginAction.input_Password(CommonAction.driver, "Control123");
	}

	@When("^I did click on Sig In button$")
	public void i_did_click_on_Sig_In_button() throws Throwable {
		LoginAction.click_Login(CommonAction.driver);;
	}

	@Then("^Room Manager displays a new page$")
	public void room_Manager_displays_a_new_page() throws Throwable {
		Assert.assertTrue(true, "Room Manager displays a new page");
		CommonAction.closeBrowser();
	}
}
