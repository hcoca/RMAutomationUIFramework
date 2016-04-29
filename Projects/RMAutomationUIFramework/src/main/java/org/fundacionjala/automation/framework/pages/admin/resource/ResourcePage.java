package org.fundacionjala.automation.framework.pages.admin.resource;

import java.util.List;

import org.fundacionjala.automation.framework.maps.admin.resource.ResourceMap;
import org.fundacionjala.automation.framework.pages.admin.home.AdminPage;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.UIActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResourcePage extends AdminPage {
	
	@FindBy (xpath = ResourceMap.ADD_BUTTON) WebElement addButton;
	@FindBy (xpath = ResourceMap.REMOVE_BUTTON) WebElement removeButton;
	@FindBy (xpath = ResourceMap.RESOURCE_TABLE) WebElement resourceTable;
	@FindBy (xpath = ResourceMap.RESOURCE_NAMES) List<WebElement> resourceNames;
	
	public ResourcePage() {
		PageFactory.initElements(BrowserManager.getDriver(), this);
	}
	
	public AddResourcePage clickOnAddButton(){
		UIActions.waitFor(ResourceMap.ADD_BUTTON);
		UIActions.clickAt(addButton);
		return new AddResourcePage();
	}
	
	public RemoveResourcePage clickOnRemoveButton(){
		UIActions.waitFor(ResourceMap.REMOVE_BUTTON);
		UIActions.clickAt(removeButton);
		return new RemoveResourcePage();
	}
	
	public ResourcePage selectResource(String resourceName){
		for (WebElement name : resourceNames) {
			if (name.getText().equalsIgnoreCase(resourceName)) {
				UIActions.clickAt(name);
			}
		}		
		return this;
	}
	
	public boolean verifyResourceExist(String resourceName){
		for (WebElement name : resourceNames) {
			if (name.getText().equalsIgnoreCase(resourceName)) {
				return true;
			}
		}		
		return false;
	}
}
