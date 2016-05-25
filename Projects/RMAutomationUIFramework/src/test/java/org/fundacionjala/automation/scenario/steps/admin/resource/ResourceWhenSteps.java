package org.fundacionjala.automation.scenario.steps.admin.resource;

import org.fundacionjala.automation.framework.pages.admin.home.AdminPage;
import org.fundacionjala.automation.framework.pages.admin.resource.AddResourcePage;
import org.fundacionjala.automation.framework.pages.admin.resource.RemoveResourcePage;
import org.fundacionjala.automation.framework.pages.admin.resource.ResourceInfoPage;
import org.fundacionjala.automation.framework.pages.admin.resource.ResourcePage;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;




import cucumber.api.java.en.When;

public class ResourceWhenSteps {

    @When("^I Create a new resource with name \"([^\"]*)\", display name \"([^\"]*)\", description \"([^\"]*)\" and icon \"([^\"]*)\"$")
    public void i_Create_a_new_resource(String name, 
	    				String displayname, 
	    				String description, 
	    				String icon)throws Throwable {
	AdminPage home = new AdminPage();
		home
		.leftMenu
		.clickOnConferenceRoomsButton();
		home
		.leftMenu
		.clickOnResourcesButton()
		.clickOnAddButton()
		.setResourceName(name)
		.setDisplayName(displayname)
		.setDescription(description)
		.selectIcon(icon)
		.clickOnSaveButton();
    }

    @When("^I delete the resource with the name \"([^\"]*)\"$")
    public void i_delete_the_resource_with_the_name(String resourceName)
	    throws Throwable {
	BrowserManager.openBrowser();
	AdminPage home = new AdminPage();
	   
		home
		.leftMenu
		.clickOnConferenceRoomsButton();
		
		home
		.leftMenu
		.clickOnResourcesButton()
		.selectResource(resourceName)
		.clickOnRemoveButton();
    }

    @When("^Confirm the changes$")
    public void confirm_the_changes() throws Throwable {
	RemoveResourcePage removeResource = new RemoveResourcePage();
	removeResource.clickOnRemoveButton();
    }

    @When("^I search the resource \"([^\"]*)\" in resources page$")
    public void i_search_the_resource_in_resources_page(String resourceName)
	    throws Throwable {
    	
    	AdminPage home = new AdminPage();
    	
    	home
		.leftMenu
		.clickOnConferenceRoomsButton();
		
		home
		.leftMenu
		.clickOnResourcesButton()
		.setResourceFilter(resourceName);
    }

    @When("^I modify the \"([^\"]*)\" field with value \"([^\"]*)\" in the resource \"([^\"]*)\"$")
    public void i_modify_the_field_with_value_in_the_resource(String name,
	    String value,
	    String resourceName) throws Throwable {
		AdminPage home = new AdminPage();
		ResourcePage resourcePage = new ResourcePage();
		ResourceInfoPage info = new ResourceInfoPage();
		
		home
		.leftMenu
		.clickOnConferenceRoomsButton();
		
		resourcePage = home.leftMenu  
			.clickOnResourcesButton();
		
	info = resourcePage
		.doubleClickOnResource(resourceName);
	switch (name.charAt(0)) {
	case 'n':
	    info.setResourceName(value);
	    break;
	case 'd':
	    info.setDisplayName(value);
	    break;
	case 'i':
	    info.selectIcon(value);
	    break;
	}
    }

    @When("^I save the modifications$")
    public void i_save_the_modifications() throws Throwable {
	ResourceInfoPage info = new ResourceInfoPage();
	info.clickOnSaveButton();
    }

    @When("^I cliked on 'Next' button on resource table$")
    public void i_cliked_on_Next_button_on_resource_table() throws Throwable {
    	
	    AdminPage home = new AdminPage();
	    
		home
		.leftMenu
		.clickOnConferenceRoomsButton();
		
		home
		.leftMenu
		.clickOnResourcesButton()
		.clickOnNextButton();
    }

    @When("^I clicked on 'Previous' button on resource table$")
    public void i_clicked_on_Previous_button_on_resource_table()
	    throws Throwable {
    	
	AdminPage home = new AdminPage();
		
		home
		.leftMenu
		.clickOnResourcesButton()
		.clickOnPreviousButton();
    }

    @When("^Go to the \"([^\"]*)\" page on resource page$")
    public void go_to_the_page_on_resource_page(String numberPage)
	    throws Throwable {
	AdminPage home = new AdminPage();
		home
		.leftMenu
		.clickOnConferenceRoomsButton();
		
		home
		.leftMenu
		.clickOnResourcesButton()
		.typeNumberPage(numberPage);
    }

    @When("^I create a resource with name \"([^\"]*)\", display name \"([^\"]*)\", description \"([^\"]*)\" and icon \"([^\"]*)\"$")
    public void i_create_a_resource_with_name_display_name_description_and_icon(
	    String name, 
	    String displayname, 
	    String description, 
	    String icon)throws Throwable {
	AdminPage home = new AdminPage();
		home
		.leftMenu
		.clickOnResourcesButton()
		.clickOnAddButton()
		.setResourceName(name)
		.setDisplayName(displayname)
		.setDescription(description).selectIcon(icon)
		.clickOnSaveButton();
    }

    @When("^Click on resource option$")
    public void click_on_resource_option() throws Throwable {
		AdminPage home = new AdminPage();
		
		BrowserManager.getDriver().navigate().refresh();
		home.leftMenu.clickOnConferenceRoomsButton();
		home.leftMenu.clickOnResourcesButton();
		
    }
    
    @When("^I select a option \"([^\"]*)\" on page size option$")
    public void i_select_page_size_option(String sizeTable) throws Throwable {
    	
    	
    	AdminPage home = new AdminPage();
        	home
        	.leftMenu
        	.clickOnConferenceRoomsButton();
        	
        	home
        	.leftMenu
        	.clickOnResourcesButton()
        	.selectPageSizeOnDropDown(sizeTable);
    }
    
    @When("^I associate the resource \"([^\"]*)\" to a room \"([^\"]*)\"$")
    public void i_associate_the_resource_to_a_room(
	    String resourceName, 
	    String roomName) throws Throwable {
    	
    	AdminPage home = new AdminPage();
    	home
        	.leftMenu
        	.clickOnConferenceRoomsButton()
        	.openConfigurationPage(roomName)
        	.clickOnResourceAssociations()
        	.addResource(resourceName)
        	.clickOnSave();
    }
    
    @When("^I want to remove the resource \"([^\"]*)\"$")
    public void i_want_to_remove_the_resource(String resourceName) throws Throwable {
    	
    	AdminPage adminPage = new AdminPage();
    	adminPage
		.leftMenu
		.clickOnConferenceRoomsButton();
    	
    	adminPage
    	.leftMenu
		.clickOnResourcesButton()
		.selectAResource(resourceName)
		.clickOnRemoveButton();
    }
    
    @When("^I Clicked on First button on resource table$")
    public void i_Clicked_on_First_button_on_resource_table() throws Throwable {
    	AdminPage home = new AdminPage();
    		home
        	.leftMenu
        	.clickOnResourcesButton()
        	.clickOnFirstPageButton();
    }
    
    @When("^I Clicked on Last button on resource table$")
    public void i_Clicked_on_Last_button_on_resource_table() throws Throwable {
    	AdminPage home = new AdminPage();
    		home
		.refreshPage()
		.leftMenu
		.clickOnEmailServerButton()
		.refreshPage()
		.leftMenu
		.clickOnResourcesButton()
		.clickOnLastPageButton();
    }

    @When("^I want to create a new resource$")
    public void i_want_to_create_a_new_resource() throws Throwable {
	AdminPage home = new AdminPage();
	home
        	.leftMenu
        	.clickOnResourcesButton()
        	.clickOnAddButton();
    }

    @When("^I want to create a new resource without filling data$")
    public void i_want_to_create_a_new_resource_without_filling_data() throws Throwable {
	AdminPage home = new AdminPage();
	home
        	.leftMenu
        	.clickOnResourcesButton()
        	.clickOnAddButton()
        	.clickOnSaveButton();
    }
    
    @When("^I want to create a new resource with special characters like \"([^\"]*)\"$")
    public void i_want_to_create_a_new_resource_with_special_characters_like(String specialCharacters) throws Throwable {
	AdminPage home = new AdminPage();
	home
        	.leftMenu
        	.clickOnResourcesButton()
        	.clickOnAddButton()
        	.setResourceName(specialCharacters)
        	.setDisplayName(specialCharacters)
        	.clickOnSaveButton();
    }
    
    @When("^I click on Cancel button on the Add Resource Page$")
    public void cancelResourceCreation() {
	AddResourcePage addResource = new AddResourcePage();
	
	addResource
		.clickOnCancelButtonAndWaitForAddResourcePageDissapear();
    }
    
    @When("^I click on Close button on the Add Resource Page$")
    public void closeResourceCreationPage() {
	AddResourcePage addResource = new AddResourcePage();
	
	addResource
		.clickOnCloseButtonAndWaitForAddResourcePageDissapear();
    }
    
    @When("^I select \"([^\"]*)\" resources$")
    public void selectResources(String resourceNumber) throws Throwable {
	int quantity = Integer.parseInt(resourceNumber);
	AdminPage adminPage = new AdminPage();
	
	ResourcePage resource = adminPage
				    .leftMenu
				    .clickOnConferenceRoomsButton()
				    .leftMenu
				    .clickOnResourcesButton();

	for (int i = 0; i < quantity; i++) {
	    resource.clickResourceCheckBox("Gift" + i);
	}
    }
    
    @When("^I check the Table Header CheckBox$")
    public void checkTableResourceCheckBox() throws Throwable { 
	AdminPage adminPage = new AdminPage();
	
	ResourcePage resource = adminPage
				    .leftMenu
				    .clickOnConferenceRoomsButton()
				    .leftMenu
				    .clickOnResourcesButton();
	resource
		.clickResourceTableHeaderCheckBox();
    }
    
    @When("^the Table Header CheckBox is unchecked$")
    public void uncheckTableResourceCheckBox() { 
	AdminPage adminPage = new AdminPage();
	
	ResourcePage resource = adminPage
				    .leftMenu
				    .clickOnResourcesButton();
	resource
		.clickResourceTableHeaderCheckBox();
    }
    
    @When("^I select \"([^\"]*)\" Icon$")
    public void i_select_Icon(String icon) throws Throwable {
	AddResourcePage addResourcePage = new AddResourcePage();
	
	addResourcePage
	.selectIcon(icon);
    }
}
