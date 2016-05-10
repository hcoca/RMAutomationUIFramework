package org.fundacionjala.automation.scenario.steps.admin.resource;

import org.fundacionjala.automation.framework.pages.admin.home.AdminPage;
import org.fundacionjala.automation.framework.pages.admin.login.LoginPage;
import org.fundacionjala.automation.framework.pages.admin.resource.RemoveResourcePage;
import org.fundacionjala.automation.framework.utils.api.managers.ResourceAPIManager;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Resource;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.PropertiesReader;
import org.testng.Assert;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AssociationIsDisplayedWhenRemoveButtonIsClickedSteps {
	
	AdminPage home;
	RemoveResourcePage removeResource;
	String RoomName = "";
	@Given("^I have a \"([^\"]*)\" resource created$")
	public void i_have_a_resource_created(String arg1) throws Throwable {
		Resource resource = new Resource(arg1, arg1, "fa fa-dashboard", "", arg1);
		ResourceAPIManager.postRequest("http://172.20.208.84:4040/resources", resource);
	}
	
	@Given("^I Login to Room Manager web app$")
	public void i_Login_to_Room_Manager_web_app() throws Throwable {
		BrowserManager.openBrowser();
		LoginPage login = new LoginPage();
		home = login
					.setUserName(PropertiesReader.getUserName())
					.setPassword(PropertiesReader.getPassword())
					.clickOnSigInButton()
					.refreshPage();
	}

	@When("^I associate the resource \"([^\"]*)\" to a room \"([^\"]*)\"$")
	public void i_associate_the_resource_to_a_room(String arg1, String arg2) throws Throwable {
		RoomName = arg2;
		home
	    	.leftMenu
	    	.clickOnConferenceRoomsButton()
	    	.openConfigurationPage(arg2)
	    	.clickOnResourceAssociations()
	    	.addResource(arg1)
	    	.clickOnSave();
	}

	@When("^I want to remove the resource \"([^\"]*)\"$")
	public void i_want_to_remove_the_resource(String arg1) throws Throwable {
		AdminPage adminPage = new AdminPage();
		removeResource = adminPage
							.leftMenu
							.clickOnLocationsButton()
							.refreshPage()
							.leftMenu
							.clickOnIssuesButton()
							.clickOnResourcesButton()
							.selectResource(arg1)
							.clickOnRemoveButton();
	}

	@Then("^I validate that the association is displayed$")
	public void i_validate_that_the_association_is_displayed() throws Throwable {
		Assert.assertTrue(
				removeResource.verifyAssociatedRoomExist(RoomName));
		
		//Post condition
		removeResource.clickOnRemoveButton();
		

	}
}
