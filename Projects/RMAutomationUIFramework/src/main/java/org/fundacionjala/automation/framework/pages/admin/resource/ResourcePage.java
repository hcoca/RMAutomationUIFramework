package org.fundacionjala.automation.framework.pages.admin.resource;

import java.util.List;

import org.fundacionjala.automation.framework.maps.admin.resource.ResourceMap;
import org.fundacionjala.automation.framework.pages.admin.conferencerooms.RoomsResourceAssociationsPage;
import org.fundacionjala.automation.framework.pages.admin.home.AdminPage;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Resource;
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
	@FindBy (xpath = ResourceMap.FIRST_PAGE_BUTTON) WebElement firstPageButton;
	@FindBy (xpath = ResourceMap.INPUT_NUMBER_PAGE) WebElement inputNumberPage;
	@FindBy (xpath = ResourceMap.LAST_PAGE_BUTTON) WebElement lastPageButton;
	
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
		UIActions.waitFor(ResourceMap.RESOURCE_NAMES);
		for (WebElement name : resourceNames) {
			if (name.getText().equalsIgnoreCase(resourceName)) {
				UIActions.clickAt(name);
			}
		}		
		return this;
	}
	
	
	
	public boolean verifyResourceExist(String resourceName){

		
		if (verifyExist(resourceName)) {
				LogManager.info("[TRUE] Resource " + resourceName + " exists");
				return true;
			}
		LogManager.error("[FALSE] Resource " + resourceName + " doesn't exist");
		return false;
	}
	
	public boolean verifyResourceNotExist(String resourceName)
	{
			if (verifyExist(resourceName)) {
				LogManager.error("[FALSE] Resource " + resourceName + " exists");
				return false;
			}
		LogManager.info("[TRUE] Resource " + resourceName + " doesn't exist");
		return true;
	}
	private boolean verifyExist(String resourceName)
	{
		UIActions.waitFor(ResourceMap.RESOURCE_NAMES);
		for (WebElement name : resourceNames) {
			if (name.getText().equalsIgnoreCase(resourceName)) {
				return true;
			}
		}
		return false;
	}
	
	public ResourceInfoPage doubleClickOnResource(String name) throws InterruptedException
	{
		Thread.sleep(2000);
		
		for (WebElement element : resourceNames) {
			if (element.getText().equalsIgnoreCase(name)) {
				element.click();
				UIActions.doubleClick(element);
				LogManager.info("Double click on Resource " + name);
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
	public boolean verifyIconExist(String resourceName, String value)
	{
		WebDriver driver = BrowserManager.getDriver();
		try {
			if(driver.findElement(By.xpath("//div[@id='resourcesGrid']/div[2]/div//span[text()= '"+resourceName+"']/ancestor::div[4]/div[2]//span[@class='fa "+value+"']"))!= null)
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		}
		
	}

	public boolean verifyResourcesOnResourceTable(List<Resource> listResource) {
		int counter = 0;
		UIActions.waitFor(ResourceMap.RESOURCE_NAMES);
		for (Resource resource : listResource) {
			 if(returnResourceName(resource.name)==true)
				 counter = counter + 1;
		}
		if(counter == listResource.size())
			return true;
		else
			return false;
	}
	private boolean returnResourceName(String name)
	{
		for (WebElement element : resourceNames) {
			if (element.getText().equalsIgnoreCase(name)) {
				return true;
			}
		}
		return false;
	}

	public boolean verifyResourceDisplayed(String arg1, String arg2, String arg3) {
		UIActions.waitFor(ResourceMap.RESOURCE_NAMES);
		if(verifyResourceExist(arg1)){
			if(verifyDisplayNameExist(arg1, arg2)){
				if(verifyIconExist(arg1, arg3))
					return true;
			}
		}
		return false;
	}

	public boolean verifyTotalItems(int totalItems) {
		UIActions.waitFor(ResourceMap.RESOURCE_TABLE);
		String xpath = ResourceMap.TOTAL_ITEMS.replace("number", String.valueOf(totalItems));
		try {
			if(resourceTable.findElement(By.xpath(xpath))!= null)
				return true;
			else return false;
		} catch (Exception e) {
			return false;
		}
	}
	
	public ResourcePage selectPageSizeOnDropDown(String quantity)
	{
		UIActions.waitFor(ResourceMap.RESOURCE_TABLE);
		String xpath = ResourceMap.DROPDOWN_PAGE_SIZE.replace("number", quantity);
		BrowserManager.getDriver().findElement(By.xpath(xpath)).click();
		return new ResourcePage();
	}

	public boolean verifyNumberOfResources(int pageSize) {
		UIActions.waitFor(ResourceMap.RESOURCE_NAMES);
		if(resourceNames.size()== pageSize )
			return true;
		else
			return false;
		
	}

	public ResourcePage clickOnFirstPageButton() {
		UIActions.waitFor(ResourceMap.FIRST_PAGE_BUTTON);
		UIActions.clickAt(firstPageButton);
		return new ResourcePage();
	}

	public boolean verifyTheFirstPage(String firstPage) {
		UIActions.waitFor(ResourceMap.INPUT_NUMBER_PAGE);
		if(inputNumberPage.getAttribute("value").equalsIgnoreCase(firstPage))
			return true;
		else
			return false;
	}

	public ResourcePage clickOnLastPageButton() {
		UIActions.waitFor(ResourceMap.LAST_PAGE_BUTTON);
		UIActions.clickAt(lastPageButton);
		return new ResourcePage();
	}

	public boolean verifyTheLastPage() {
		UIActions.waitFor(ResourceMap.INPUT_NUMBER_PAGE);
		String lastPage = inputNumberPage.getAttribute("value").trim();
		String xpath = ResourceMap.TOTAL_NUMBER_PAGE.replace("totalPages", lastPage);
		try {
			//UIActions.waitFor(xpath);
			if(BrowserManager.getDriver().findElement(By.xpath(xpath))!= null)
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		}
	}
	
	public ResourceAssociationsPage clickOnAssociations()
	{
		
		return new ResourceAssociationsPage();
	}

}
