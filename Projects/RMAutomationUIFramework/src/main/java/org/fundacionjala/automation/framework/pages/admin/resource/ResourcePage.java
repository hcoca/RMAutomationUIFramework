package org.fundacionjala.automation.framework.pages.admin.resource;

import java.util.List;

import org.fundacionjala.automation.framework.maps.admin.resource.ResourceMap;
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
/**
 * This class represents Resource page
 * @author mariaalcocer
 *
 */
public class ResourcePage extends AdminPage {
	
	@FindBy (xpath = ResourceMap.ADD_BUTTON) 
	WebElement addButton;
	@FindBy (xpath = ResourceMap.REMOVE_BUTTON) 
	WebElement removeButton;
	@FindBy (xpath = ResourceMap.RESOURCE_TABLE) 
	WebElement resourceTable;
	@FindBy (xpath = ResourceMap.RESOURCE_NAMES) 
	List<WebElement> resourceNames;
	@FindBy (xpath = ResourceMap.RESOURCE_FILTER) 
	WebElement resourceFilter;
	@FindBy (xpath = ResourceMap.FIRST_PAGE_BUTTON) 
	WebElement firstPageButton;
	@FindBy (xpath = ResourceMap.INPUT_NUMBER_PAGE) 
	WebElement inputNumberPage;
	@FindBy (xpath = ResourceMap.LAST_PAGE_BUTTON) 
	WebElement lastPageButton;
	@FindBy (xpath = ResourceMap.NEXT_PAGE_BUTTON) 
	WebElement nextPageButton;
	@FindBy (xpath = ResourceMap.PREVIOUS_PAGE_BUTTON) 
	WebElement previousPageButton;
	@FindBy (xpath = ResourceMap.TOTAL_PAGES_LABEL) 
	WebElement totalPagesLabel;
	
	/**
	 * The constructor initializes the page factory
	 */
	public ResourcePage() {
		PageFactory.initElements(BrowserManager.getDriver(), this);
	}
	
	/**
	 * This function is to click on Add button on ResourcePage
	 * @return AddResourcePage
	 */
	public AddResourcePage clickOnAddButton(){
	    
		UIActions.waitFor(ResourceMap.ADD_BUTTON);
		UIActions.clickAt(addButton);
		return new AddResourcePage();
	}
	
	/**
	 * This function is to click on Add button on ResourcePage
	 * @return RemoveResourcePage
	 */
	public RemoveResourcePage clickOnRemoveButton(){
	    
		UIActions.waitFor(ResourceMap.REMOVE_BUTTON);
		UIActions.clickAt(removeButton);
		return new RemoveResourcePage();
	}
	
	/**
	 * Enter a resourceName in ResourceNameFilter text field
	 * @param resourceName
	 * @return ResourcePage
	 */
	public ResourcePage setResourceFilter(String resourceName){
	    
		UIActions.waitFor(ResourceMap.RESOURCE_FILTER);
		UIActions.typeOn(resourceFilter, resourceName);
		return this;
	}
	
	/**
	 * Selects a resource of the resource table
	 * @param resourceName
	 * @return ResourcePage
	 */
	public ResourcePage selectResource(String resourceName){
	    
		UIActions.waitFor(ResourceMap.RESOURCE_NAMES);
		for (WebElement name : resourceNames) {
			if (name.getText().equalsIgnoreCase(resourceName)) {
				UIActions.clickAt(name);
			}
		}		
		return this;
	}
	
	/**
	 * This function verifies if a resource exist on resource table	
	 * @param resourceName
	 * @return true if find the resourceName else return false
	 */
	public boolean verifyResourceExist(String resourceName){
	    
		if (verifyExist(resourceName)) {
			LogManager.info("[TRUE] Resource " 
					+ resourceName + " exists");
			return true;
		}
		LogManager.error("[FALSE] Resource " 
				 + resourceName + " doesn't exist");
		return false;
	}
	
	/**
	 * This functions is to verify if a resource not exist on resource table
	 * @param resourceName
	 * @return true if the resourceName is not find else return false
	 */
	public boolean verifyResourceNotExist(String resourceName){
	    
		if (verifyExist(resourceName)) {
			LogManager.error("[FALSE] Resource "
					 + resourceName + " exists");
			return false;
		}
		LogManager.info("[TRUE] Resource " 
				+ resourceName + " doesn't exist");
		return true;
	}
	
	/**
	 * This function is to verify if a resource is found on resource table
	 * @param resourceName
	 * @return true if a resourceName is found else return false
	 */
	private boolean verifyExist(String resourceName){
	    
		UIActions.waitFor(ResourceMap.RESOURCE_NAMES);
		for (WebElement name : resourceNames) {
			if (name.getText().equalsIgnoreCase(resourceName)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Double click on a resource
	 * @param name
	 * @return ResourceInfoPage
	 * @throws InterruptedException
	 */
	public ResourceInfoPage doubleClickOnResource(String name) 
		throws InterruptedException{
	    
		Thread.sleep(2000);
		for (WebElement element : resourceNames) {
			if (element.getText().equalsIgnoreCase(name)) {
				element.click();
				UIActions.doubleClick(element);
				LogManager.info("Double click on Resource "
						+ name);
				return new ResourceInfoPage();
			}
		}
		return null;
	}

	/**
	 * Verifies if a text field has been modified
	 * @param resourceName
	 * @param name
	 * @param value
	 * @return true if the text field was modified else return false
	 */
	public boolean verifyResourceModifiedByField(String resourceName, 
						     String name, 
						     String value) {
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

	/**
	 * Verify if an icon is find on resource table
	 * @param resourceName
	 * @param value
	 * @return true if the icon is found else return false
	 */
	public boolean verifyIconAssignment(String resourceName, String value) {
		
		WebDriver driver = BrowserManager.getDriver();
		WebElement resourceDisplayElement = 
			driver.findElement(By.xpath(
			"//div[@tabindex]/div/div/child::*[3]//span[text()='" 
			+ resourceName
			+ "']/ancestor::div[3]/preceding-sibling::div[1]//span"));
		
		return (resourceDisplayElement
				.getAttribute("class").contains(value));
	}

	/**
	 * Verifies the value of resourceName
	 * @param resourceName
	 * @param value
	 * @return true if the resourceName was find else return false
	 */
	public boolean verifyDisplayNameExist(String resourceName, 
					      String value) {
		WebDriver driver = BrowserManager.getDriver();
		WebElement resourceDisplayElement = 
			driver.findElement(By.xpath(
			"//div[@tabindex]/div/div/child::*[3]//span[text()='"
			+ resourceName 
			+ "']/ancestor::div[3]/following-sibling::div[1]//span"));
		
		return (resourceDisplayElement
			.getText().equalsIgnoreCase(value));
	}
	
	/**
	 * Verifies the icon on resource table
	 * @param resourceName
	 * @param value
	 * @return true if the resource has the icon else return false
	 */
	public boolean verifyIconExist(String resourceName, String value){
	    
		WebDriver driver = BrowserManager.getDriver();
		try {
		    return (driver.findElement(By.xpath(
			"//div[@id='resourcesGrid']/div[2]/div//span[text()= '"
			+ resourceName 
			+ "']/ancestor::div[4]/div[2]//span[@class='fa "
			+ value + "']"))!= null) ? true : false;
		} catch (Exception e) {
			return false;
		}
		
	}

	/**
	 * Verifies if the resources in the table are the same 
	 * than the parameter listResource
	 * @param listResource
	 * @return true if the size is same else return false
	 */
	public boolean verifyResourcesOnResourceTable(
		List<Resource> listResource) {
		int counter = 0;
		UIActions.waitFor(ResourceMap.RESOURCE_NAMES);
		for (Resource resource : listResource) {
			 if(returnResourceName(resource.name)==true){
				 counter = counter + 1;
			 }
		}
		return (counter == listResource.size()) ? true : false;
	}
	
	/**
	 * Search a resource on resource table
	 * @param name
	 * @return true if the name has been found else return false
	 */
	private boolean returnResourceName(String name){
	    
		for (WebElement element : resourceNames) {
			if (element.getText().equalsIgnoreCase(name)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Verifies all values of a resource on resource table
	 * @param resourceName
	 * @param displayname
	 * @param icon
	 * @return true if the name, display name and icon are same else return
	 * false
	 */
	public boolean verifyResourceDisplayed(String resourceName, 
					       String displayname, 
					       String icon) {
		UIActions.waitFor(ResourceMap.RESOURCE_NAMES);
		if(verifyResourceExist(resourceName)){
			if(verifyDisplayNameExist(resourceName, displayname)){
				if(verifyIconExist(resourceName, icon))
					return true;
			}
		}
		return false;
	}

	/**
	 * Verify the total resources
	 * @param totalItems
	 * @return true if the total is same else return false
	 */
	public boolean verifyTotalItems(int totalItems) {
	    
		UIActions.waitFor(ResourceMap.RESOURCE_TABLE);
		String xpath = ResourceMap.TOTAL_ITEMS.replace(
			"number", String.valueOf(totalItems));
		try {
		    return (resourceTable.findElement(By.xpath(xpath))!= null)
			    ? true : false;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * Selects a number on the dropdown of page size
	 * @param quantity
	 * @return ResourcePage
	 */
	public ResourcePage selectPageSizeOnDropDown(String quantity){
	    
		UIActions.waitFor(ResourceMap.RESOURCE_TABLE);
		String xpath = ResourceMap.DROPDOWN_PAGE_SIZE
			.replace("number", quantity);
		BrowserManager.getDriver().findElement(By.xpath(xpath)).click();
		
		return new ResourcePage();
	}
	
	/**
	 * Verifies if the pagesize selected displays the correct number of 
	 * resource
	 * @param pageSize
	 * @return true if the resource table size is same else return false
	 */
	public boolean verifyNumberOfResources(Integer pageSize){
	    
	    	String xpath = ResourceMap.DROPDOWN_PAGE_SIZE
	    				  .replace("number", 
	    					  pageSize.toString());
		UIActions.waitFor(xpath);
		System.out.println("tamanio "+resourceNames.size());
		return (resourceNames.size()== pageSize ) ? true : false;
		
	}

	/**
	 * Click on firstPage button on resource table
	 * @return ResourcePage
	 */
	public ResourcePage clickOnFirstPageButton() {
	    
		UIActions.waitFor(ResourceMap.FIRST_PAGE_BUTTON);
		UIActions.clickAt(firstPageButton);
		return new ResourcePage();
	}

	/**
	 * Verifies if the page selected is the first page
	 * @param firstPage
	 * @return true if the value is the first page else return false
	 */
	public boolean verifyTheFirstPage(String firstPage) {
	    
		UIActions.waitFor(ResourceMap.INPUT_NUMBER_PAGE);
		return (inputNumberPage.getAttribute("value")
			.equalsIgnoreCase(firstPage)) ? true : false;
	}

	/**
	 * Click on LastPage button on ResourcePage
	 * @return ResourcePage
	 */
	public ResourcePage clickOnLastPageButton() {
	    
		UIActions.waitFor(ResourceMap.LAST_PAGE_BUTTON);
		UIActions.clickAt(lastPageButton);
		return new ResourcePage();
	}

	/**
	 * This function verifies that the page in text field is the last page
	 * @return true if is the last page else return false
	 */
	public boolean verifyTheLastPage() {
	    
		UIActions.waitFor(ResourceMap.INPUT_NUMBER_PAGE);
		String lastPage = inputNumberPage.getAttribute("value").trim();
		String xpath = ResourceMap.TOTAL_NUMBER_PAGE.replace("totalPages", lastPage);
		try {
		    return (BrowserManager.getDriver().findElement(
			    By.xpath(xpath))!= null) ? true: false;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Click on NextPage button on ResourcePage
	 * @return ResourcePage
	 */
	public ResourcePage clickOnNextButton() {
	    
		UIActions.waitFor(ResourceMap.NEXT_PAGE_BUTTON);
		UIActions.clickAt(nextPageButton);
		return new ResourcePage();
	}

	/**
	 * Verifies that the parameter numberPage is same than the pageNumber 
	 * text field
	 * @param numberPage
	 * @return true if is same else return false
	 */
	public boolean verifyNextPage(String numberPage) {
	    
		UIActions.waitFor(ResourceMap.INPUT_NUMBER_PAGE);
		return (inputNumberPage.getAttribute("value")
			.equalsIgnoreCase(numberPage))? true: false;
		
	}

	/**
	 * Click on PreviuosPage button on resource table
	 * @return ResourcePage
	 */
	public ResourcePage clickOnPreviousButton() {
		UIActions.waitFor(ResourceMap.PREVIOUS_PAGE_BUTTON);
		UIActions.clickAt(previousPageButton);
		
		return new ResourcePage();
	}

	/**
	 * Verifies if the parameter is same than numberPage text field
	 * @param previousPage
	 * @return true is the values are same else return false
	 */
	public boolean verifyPreviousPage(int previousPage) {
		UIActions.waitFor(ResourceMap.INPUT_NUMBER_PAGE);
		
		String totalNumberPage = inputNumberPage
			.getAttribute("value").trim();
		
		System.out.println("con"+totalNumberPage);
		return (totalNumberPage.equalsIgnoreCase(
			String.valueOf(previousPage)))? true : false;
	}

	/**
	 * this function is to return the total pages on resource table
	 * @return the number of pages
	 */
	public int getTheTotalNumberPage() {
	    
		UIActions.waitFor(ResourceMap.TOTAL_PAGES_LABEL);
		String totalNumberPage = 
			totalPagesLabel.getText().replace("/", "").trim();
		return (totalNumberPage.equalsIgnoreCase("1")) ?
			Integer.parseInt(totalNumberPage) 
			: (Integer.parseInt(totalNumberPage) -1);
	}

	/**
	 * Set the numberPage text field
	 * @param numberPage
	 */
	public void typeNumberPage(String numberPage) {
	    
		UIActions.waitFor(ResourceMap.INPUT_NUMBER_PAGE);
		inputNumberPage.clear();
		inputNumberPage.sendKeys(numberPage);
	}

	/**
	 * This function verifies the first resource value on the current page
	 * @param firstElement
	 * @return true if the values are same else return true
	 */
	public boolean verifyTheFirstElementOnThePage(String firstElement) {
	    
		UIActions.waitFor(ResourceMap.RESOURCE_NAMES);
		return (firstElement.equalsIgnoreCase(
			resourceNames.get(0).getText())) ? true : false;
	}

}
