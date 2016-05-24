package org.fundacionjala.automation.framework.pages.admin.resource;

import org.fundacionjala.automation.framework.maps.admin.resource.AddResourceMap;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.ExplicitWait;
import org.fundacionjala.automation.framework.utils.common.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
/**
 * This class represents AddResourcePage
 * @author mariaalcocer
 *
 */
public class AddResourcePage {
	
	@FindBy (xpath = AddResourceMap.NAME_TEXT_FIELD) 
	WebElement nameTextField;
	@FindBy (xpath = AddResourceMap.DISPLAY_NAME_TEXT_FIELD) 
	WebElement displayNameTextField;
	@FindBy (xpath = AddResourceMap.DESCRIPTION_TEXT_FIELD) 
	WebElement descriptionTextField;
	@FindBy (xpath = AddResourceMap.SAVE_BUTTON) 
	WebElement saveButton;
	@FindBy (xpath = AddResourceMap.CANCEL_BUTTON) 
	WebElement cancelButton;
	@FindBy (xpath = AddResourceMap.ICON_BUTTON) 
	WebElement iconButton;
	@FindBy (xpath = AddResourceMap.CLOSE_BUTTON)
	WebElement closeButton;
	
	/**
	 * The constructor initialize the AddResourcePage
	 */
	public AddResourcePage(){
	    
		PageFactory.initElements(BrowserManager.getDriver(), this);
	}
	
	/**
	 * Fills name text field on AddResourcePage
	 * @param name is the value to fill the text field
	 * @return AddResourcePage
	 */
	public AddResourcePage setResourceName(String name){
	    
		By locator = By.xpath(AddResourceMap.NAME_TEXT_FIELD);
		(new WebDriverWait(BrowserManager.getDriver(), 10))
		.until(ExpectedConditions.presenceOfElementLocated(locator));
		nameTextField.sendKeys(name);
		LogManager.info("'" + name + "' has been set");
		
		return this;
	}
	
	/**
	 * Fills displayName text field on AddResourcePage
	 * @param displayName is the value to fill the text field
	 * @return AddResourcePage
	 */
	public AddResourcePage setDisplayName(String displayName){
	    
		By locator = By.xpath(AddResourceMap.DISPLAY_NAME_TEXT_FIELD);
		(new WebDriverWait(BrowserManager.getDriver(), 10))
		.until(ExpectedConditions.presenceOfElementLocated(locator));
		displayNameTextField.clear();
		displayNameTextField.sendKeys(displayName);
		LogManager.info("'" + displayName 
				+ " has been set up in display Name field ");
		
		return this;
	}
	
	/**
	 * Fills description text field on AddResourcePage
	 * @param description is the value to fill the text field
	 * @return AddResourcePage
	 */
	public AddResourcePage setDescription(String description){
	    
		By locator = By.xpath(AddResourceMap.DESCRIPTION_TEXT_FIELD);
		(new WebDriverWait(BrowserManager.getDriver(), 10))
		.until(ExpectedConditions.presenceOfElementLocated(locator));
		descriptionTextField.clear();
		descriptionTextField.sendKeys(description);
		LogManager.info("'" + description 
				+ " has been set up in description text field");
				
		return this;
	}
	
	/**
	 * Selects icon text field on AddResourcePage
	 * @param icon is the value to select an icon
	 * @return AddResourcePage
	 */
	public AddResourcePage selectIcon(String icon){
	    
		String xpath = AddResourceMap.ICON.replace("iconName", icon);
		WebElement element;
		(new WebDriverWait(BrowserManager.getDriver(), 10))
		.until(ExpectedConditions.presenceOfElementLocated(
			By.xpath(AddResourceMap.ICON_BUTTON)));
		iconButton.click();
		(new WebDriverWait(BrowserManager.getDriver(), 10))
		.until(ExpectedConditions.presenceOfElementLocated(
							By.xpath(xpath)));
		element = BrowserManager.getDriver().findElement(By.xpath(xpath));
		element.click();
		LogManager.info("'Icon' button has been selected");
				
		return this;
	}
	
	/**
	 * This function is to click on SAVE button on AddResourcePage
	 * @return ResourcePage
	 */
        public ResourcePage clickOnSaveButton() {
    
    		ExplicitWait.clickWhenReady(By.xpath(
    			AddResourceMap.SAVE_BUTTON), 30);
    		LogManager.info("'Save' button has been clicked");
    
    	return new ResourcePage();
        }
	
	/**
	 * This function is to click on CANCEL button on AddResourcePage
	 * @return
	 */
	public ResourcePage clickOnCancelButton(){
	    
		(new WebDriverWait(BrowserManager.getDriver(), 10))
		.until(ExpectedConditions.presenceOfElementLocated(
			By.xpath(AddResourceMap.CANCEL_BUTTON)));
		cancelButton.click();
		LogManager.info("'Cancel' button has been clicked");
		
		return new ResourcePage();
	}

	public ResourcePage clickOnCloseButton() {
	    	(new WebDriverWait(BrowserManager.getDriver(), 30))
		.until(ExpectedConditions.elementToBeClickable(closeButton));
	    	
		Actions action = new Actions(BrowserManager.getDriver());
	    	action.click(closeButton);
		action.perform();
		
		LogManager.info("'Close' button has been clicked");
	    
	    	return new ResourcePage();
	}
	
	public ResourcePage clickOnCancelButtonAndWaitForAddResourcePageDissapear() {
	    
	    	(new WebDriverWait(BrowserManager.getDriver(), 30))
		.until(ExpectedConditions.elementToBeClickable(cancelButton));
		
		cancelButton.click();
		
		LogManager.info("'Cancel' button has been clicked");
		
		(new WebDriverWait(BrowserManager.getDriver(), 30))
		.until(ExpectedConditions.invisibilityOfElementLocated(By
				.xpath(AddResourceMap.ADD_RESOURCE_WINDOW)));
		
		LogManager.info("Add Resource Window has dissapeared");

		return new ResourcePage();
	}
	
	public ResourcePage clickOnCloseButtonAndWaitForAddResourcePageDissapear() {
	    	(new WebDriverWait(BrowserManager.getDriver(), 30))
		.until(ExpectedConditions.elementToBeClickable(closeButton));
	    	
		Actions action = new Actions(BrowserManager.getDriver());
	    	action.click(closeButton);
		action.perform();
		
		LogManager.info("'Close' button has been clicked");
		
		(new WebDriverWait(BrowserManager.getDriver(), 30))
		.until(ExpectedConditions.invisibilityOfElementLocated(By
				.xpath(AddResourceMap.ADD_RESOURCE_WINDOW)));
		
		LogManager.info("Add Resource Window has dissapeared");
	    
	    	return new ResourcePage();
	}
}
