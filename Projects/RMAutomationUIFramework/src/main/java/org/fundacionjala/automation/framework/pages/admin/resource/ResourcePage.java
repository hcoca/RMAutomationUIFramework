package org.fundacionjala.automation.framework.pages.admin.resource;

import java.util.List;

import mx4j.log.Log;

import org.fundacionjala.automation.framework.maps.admin.resource.ResourceMap;
import org.fundacionjala.automation.framework.pages.admin.home.AdminPage;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.LogManager;
import org.fundacionjala.automation.framework.utils.common.UIActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResourcePage extends AdminPage {
	
	@FindBy (xpath = ResourceMap.ADD_BUTTON) WebElement addButton;
	@FindBy (xpath = ResourceMap.REMOVE_BUTTON) WebElement removeButton;
	@FindBy (xpath = ResourceMap.RESOURCE_TABLE) WebElement resourceTable;
	@FindBy (xpath = ResourceMap.RESOURCE_NAMES) List<WebElement> resourceNames;
	@FindBy (xpath = ResourceMap.RESOURCE_FILTER) WebElement resourceFilter;
	
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
	
	public ResourcePage setResourceFilter(String resourceName){
		UIActions.waitFor(ResourceMap.RESOURCE_FILTER);
		UIActions.typeOn(resourceFilter, resourceName);
		return this;
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
				LogManager.info("[TRUE] Resource " + resourceName + " exists");
				return true;
			}
		}
		LogManager.error("[FALSE] Resource " + resourceName + " doesn't exist");
		return false;
	}
	
	public ResourceInfoPage doubleClickOnResource(String name)
	{
		for (WebElement element : resourceNames) {
			if (element.getText().equalsIgnoreCase(name)) {
				element.click();
				UIActions.doubleClick(element);
				LogManager.error("Double click on Resource " + name);
				return new ResourceInfoPage();
			}
		}
		return null;
	}

	public boolean verifyResourceModifiedByField(String resourceName, String name, String value) {
		switch(name.charAt(0))
		{
		case 'n':
			return verifyResourceExist(value);
			
		case 'd':
			return verifyDisplayNameExist(resourceName,value);
		case 'i':
			
			return verifyIconAssignment(resourceName,value);
		}
		return false;
	}

	public boolean verifyIconAssignment(String resourceName, String value) {
		
		WebDriver driver = BrowserManager.getDriver();
		WebElement resourceDisplayElement = driver.findElement(By.xpath("//div[@tabindex]/div/div/child::*[3]//span[text()='"+ resourceName +"']/ancestor::div[3]/preceding-sibling::div[1]//span"));
		
		return (resourceDisplayElement.getAttribute("class").contains(value));
	}

	public boolean verifyDisplayNameExist(String resourceName, String value) {
		WebDriver driver = BrowserManager.getDriver();
		WebElement resourceDisplayElement = driver.findElement(By.xpath("//div[@tabindex]/div/div/child::*[3]//span[text()='"+ resourceName +"']/ancestor::div[3]/following-sibling::div[1]//span"));
		
		return (resourceDisplayElement.getText().equalsIgnoreCase(value));
	}

	
}
