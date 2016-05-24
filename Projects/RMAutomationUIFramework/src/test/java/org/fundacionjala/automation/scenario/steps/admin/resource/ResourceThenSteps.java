package org.fundacionjala.automation.scenario.steps.admin.resource;

import java.util.List;

import org.fundacionjala.automation.framework.pages.admin.home.AdminPage;
import org.fundacionjala.automation.framework.pages.admin.resource.AddResourcePage;
import org.fundacionjala.automation.framework.pages.admin.resource.RemoveResourcePage;
import org.fundacionjala.automation.framework.pages.admin.resource.ResourcePage;
import org.fundacionjala.automation.framework.utils.api.managers.ResourceAPIManager;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Resource;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.PropertiesReader;
import org.testng.Assert;

import com.mashape.unirest.http.exceptions.UnirestException;

import cucumber.api.java.After;
import cucumber.api.java.en.Then;

public class ResourceThenSteps {

    @Then("^Validate that the resource with name \"([^\"]*)\" is diplayed in resource page$")
    public void validate_that_the_resource_with_name_is_diplayed(
	    String resourceName) throws Throwable {

	ResourcePage resources = new ResourcePage();

	BrowserManager.getDriver().navigate().refresh();
	AdminPage admin = new AdminPage();
	admin.leftMenu.clickOnConferenceRoomsButton();
	admin.leftMenu.clickOnResourcesButton();

	boolean expectedResult = resources.verifyResourceExist(resourceName);
	deleteResourceByName(resourceName);

	Assert.assertTrue(expectedResult);

    }

    @Then("^Validate that the resource with the name \"([^\"]*)\" has been deleted$")
    public void validate_that_the_resource_with_the_name_has_been_deleted(
	    String arg1) throws Throwable {

	BrowserManager.getDriver().navigate().refresh();

	AdminPage admin = new AdminPage();

	admin.leftMenu.clickOnConferenceRoomsButton();

	ResourcePage resource = admin.leftMenu.clickOnResourcesButton();
	boolean isResourceNotPresent = resource.verifyResourceNotExist(arg1);
	Assert.assertTrue(isResourceNotPresent);
    }

    @Then("^Validate the resource \"([^\"]*)\" is displayed$")
    public void validate_the_resource_is_displayed(String resourceName)
	    throws Throwable {
	ResourcePage resourcePage = new ResourcePage();

	boolean expectedResult = resourcePage.verifyResourceExist(resourceName);
	deleteResourceByName(resourceName);

	Assert.assertTrue(expectedResult);
    }

    @Then("^Validate that the resource \"([^\"]*)\" is modified according the changes \\(\"([^\"]*)\" field with value \"([^\"]*)\"\\)$")
    public void validate_that_the_resource_is_modified(String resourceName,
	    String nameField, String value) throws Throwable {
	ResourcePage resourcePage = new ResourcePage();
	resourcePage = (new AdminPage()).leftMenu.clickOnResourcesButton();

	boolean expectedResult = resourcePage.verifyResourceModifiedByField(
		resourceName, nameField, value);
	deleteResourceByName(resourceName);
	Assert.assertTrue(expectedResult);
    }

    @Then("^Validate that the 'next' page is displayed on resource table$")
    public void validate_that_the_next_page_is_displayed() throws Throwable {
	ResourcePage resources = new ResourcePage();
	Assert.assertTrue(resources.verifyNextPage("2"));

    }

    @Then("^Validate that the 'previous' page is displayed on resource table$")
    public void validate_the_previous_page_is_displayed() throws Throwable {
	ResourcePage resources = new ResourcePage();
	int previousPage = resources.getTheTotalNumberPage();
	Assert.assertTrue(resources.verifyPreviousPage(previousPage));

    }

    @Then("^Validate that the \"([^\"]*)\" page is displayed$")
    public void validate_that_the_page_is_displayed(String NumberPage)
	    throws Throwable {
	List<Resource> listResource = ResourceAPIManager
		.getRequest(PropertiesReader.getServiceURL() + "/resources");
	int resourceIndx = ((Integer.parseInt(NumberPage) - 1) * 50) + 1;
	ResourcePage resources = new ResourcePage();
	String firstElement = listResource.get(resourceIndx - 1).name;
	Assert.assertTrue(resources
		.verifyTheFirstElementOnThePage(firstElement));

    }

    @Then("^Validate that resource with \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\" is displayed$")
    public void validate_that_resource_with_and_is_displayed(
	    String resourceName, String displayname, String icon)
	    throws Throwable {
	ResourcePage resources = new ResourcePage();

	BrowserManager.getDriver().navigate().refresh();
	AdminPage admin = new AdminPage();
	admin.leftMenu.clickOnConferenceRoomsButton();
	admin.leftMenu.clickOnResourcesButton();

	boolean expectedResult = resources.verifyResourceDisplayed(
		resourceName, displayname, icon);

	deleteResourceByName(resourceName);
	Assert.assertTrue(expectedResult);

    }

    @Then("^Validate that all resources are displayed in resource table$")
    public void validate_all_resources_are_displayed() throws Throwable {
	ResourcePage resources = new ResourcePage();
	List<Resource> listResource = ResourceAPIManager
		.getRequest(PropertiesReader.getServiceURL() + "/resources");

	Assert.assertTrue(resources
		.verifyResourcesOnResourceTable(listResource));

    }

    @Then("^Validate that total resources are displayed$")
    public void validate_total_resources_are_displayed() throws Throwable {

	ResourcePage resources = new ResourcePage();
	AdminPage home = new AdminPage();

	BrowserManager.getDriver().navigate().refresh();
	home.leftMenu.clickOnConferenceRoomsButton();
	home.leftMenu.clickOnResourcesButton();

	List<Resource> listResource = ResourceAPIManager
		.getRequest(PropertiesReader.getServiceURL() + "/resources");
	int totalItems = listResource.size();
	Assert.assertTrue(resources.verifyTotalItems(totalItems));

    }

    @Then("^Validate that the resource table size is same than the option \"([^\"]*)\" selected$")
    public void validate_resource_table_size(String sizeTable) throws Throwable {
	ResourcePage resources = new ResourcePage();
	Assert.assertTrue(resources.verifyNumberOfResources(sizeTable));
    }

    @Then("^Validate that the association with the \"([^\"]*)\" room is displayed$")
    public void validate_resource_association(String RoomName) throws Throwable {
	RemoveResourcePage removeResource = new RemoveResourcePage();

	boolean expectedResult = removeResource
		.verifyAssociatedRoomExist(RoomName);

	Assert.assertTrue(expectedResult);
	removeResource.clickOnRemoveButton();

    }

    @Then("^Validate that the first page is displayed on resource table$")
    public void validate_the_first_page() throws Throwable {

	ResourcePage resources = new ResourcePage();
	Assert.assertTrue(resources.verifyTheFirstPage("1"));
    }

    @Then("^Validate that the last page is displayed on resource table$")
    public void validate_the_last_page() throws Throwable {

	ResourcePage resources = new ResourcePage();
	Assert.assertTrue(resources.verifyTheLastPage());
    }

    @After("@resouceDelete")
    public void tearDown() throws Throwable {
	deleteResourcesCreated();
    }

    private void deleteResourceByName(String resourceName)
	    throws UnirestException {
	String idResource = "";
	List<Resource> listResource = ResourceAPIManager
		.getRequest(PropertiesReader.getServiceURL() + "/resources");
	for (Resource resource : listResource) {
	    if (resource.name.equalsIgnoreCase(resourceName)) {
		idResource = resource._id;
	    }
	}
	ResourceAPIManager.deleteRequest(PropertiesReader.getServiceURL()
		+ "/resources", idResource);
    }

    private void deleteResourcesCreated() throws UnirestException {

	List<Resource> listResource = ResourceAPIManager
		.getRequest(PropertiesReader.getServiceURL() + "/resources");
	for (Resource resource : listResource) {
	    if (resource.name.contains("Gift")) {
		ResourceAPIManager.deleteRequest(
			PropertiesReader.getServiceURL() + "/resources",
			resource._id);
	    }
	}

    }

    @Then("^Validate that a form with \"([^\"]*)\" resource and \"([^\"]*)\" icon is displayed$")
    public void displayResourceInformation(
	    String resourceName, String iconName) throws Throwable {
	RemoveResourcePage removeResourcePage = new RemoveResourcePage();
	Assert.assertTrue(
	removeResourcePage.verifyResourceInformation(resourceName, iconName),
	"The information of Resource is Incorrect");
	removeResourcePage.clickOnCloseButton();
    }

    @Then("^Validate that a form to create a resource is diplayed$")
    public void validate_that_a_form_to_create_a_resource_is_diplayed() throws Throwable {
	AddResourcePage addResourcePage = new AddResourcePage();
	Assert.assertTrue(
	addResourcePage.verifyIfFormIsDisplayed(),
	"The form is not displayed");
	addResourcePage.clickOnCancelButton();
    }
    
    @Then("^Validate that an error message is displayed$")
    public void validate_that_an_error_message_is_displayed() throws Throwable {
	AddResourcePage addResourcePage = new AddResourcePage();
	Assert.assertTrue(
	addResourcePage.verifyErrorMessagesWhenFieldsAreEmpty(),
	"The form is not displayed");
	addResourcePage.clickOnCancelButton();
    }
    
    @Then("^Validate that an error message is displayed for special characters$")
    public void validate_that_an_error_message_is_displayed_for_special_characters() throws Throwable {
	AddResourcePage addResourcePage = new AddResourcePage();
	Assert.assertTrue(
	addResourcePage.verifyErrorMessageWhenSpecialCharactersAreEntered(),
	"The form is not displayed");
	addResourcePage.clickOnCancelButton();
    }

    @Then("^Validate that a form to remove a resource is diplayed$")
    public void validate_that_a_form_to_remove_a_resource_is_diplayed() throws Throwable {
	AddResourcePage addResourcePage = new AddResourcePage();
	Assert.assertTrue(
	addResourcePage.verifyErrorMessageWhenNameAlreadyExists(),
	"The form is not displayed");
	addResourcePage.clickOnCancelButton();
    }

    @Then("^Validate that an error message is displayed when I want to create a new resource that already exists\\.$")
    public void validate_that_an_error_message_is_displayed_when_I_want_to_create_a_new_resource_that_already_exists() throws Throwable {
        
    }
}
