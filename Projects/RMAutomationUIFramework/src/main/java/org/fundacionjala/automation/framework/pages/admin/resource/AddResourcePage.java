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
	 * this method is to do click on icon button
	 * @param icon is the value to select an icon
	 * @return AddResourcePage
	 */
	public SelectIconPage ClickOnIconButton(){
	    
		(new WebDriverWait(BrowserManager.getDriver(), 10))
		.until(ExpectedConditions.presenceOfElementLocated(
			By.xpath(AddResourceMap.ICON_BUTTON)));
		iconButton.click();
		LogManager.info("'Icon' button has been clicked");
				
		return new SelectIconPage();
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

	/**
	 * This method is to verify if the form to create a resource is displayed
	 * @return true if form is displayed else false
	 */
	public boolean verifyIfFormIsDisplayed() {
	return (ExplicitWait.waitForElement(AddResourceMap.ICON_LABEL, 30) && 
		ExplicitWait.waitForElement(AddResourceMap.NAME_LABEL, 30) &&
		ExplicitWait.waitForElement(AddResourceMap.DISPLAY_NAME_LABEL, 30) &&
		ExplicitWait.waitForElement(AddResourceMap.DESCRIPTION_LABEL, 30) ) 
		? true : false;
	}
	
	@FindBy (xpath = AddResourceMap.NAME_EMPTY_ERROR) 
	WebElement nameEmptyError;
	@FindBy (xpath = AddResourceMap.DISPLAY_NAME_EMPTY_ERROR) 
	WebElement displayNameEmptyError;

	/**
	 * This method is to verify the error messages when a resources with
	 * empty fields is created.
	 * @return true if messages are displayed else false
	 */
	public boolean verifyErrorMessagesWhenFieldsAreEmpty() {
	    String classNameEmpty = nameEmptyError.getAttribute("class");
	    String classDisplayNameEmpty = displayNameEmptyError.getAttribute("class");	   
	    return ( !classNameEmpty.contains("ng-hide") && !classDisplayNameEmpty.contains("ng-hide")) 
		? true : false;
	}

	@FindBy (xpath = AddResourceMap.INVALID_CHARACTERS_ERROR) 
	WebElement specialCharacterError;
	
	/**
	 * this method is to verify that error messages is displayed when
	 * name contains special characters
	 * @return true if message is displayed else false
	 */
	public boolean verifyErrorMessageWhenSpecialCharactersAreEntered() {
	    String invalidCharacters = specialCharacterError.getAttribute("class");   
	    return !invalidCharacters.contains("ng-hide") ? true : false;
	}

	@FindBy (xpath = AddResourceMap.ALREADY_EXISTS_ERROR) 
	WebElement alreadyExistsError;
	
	/**
	 * This method is to verify that an error message appear when a resources
	 * is created with the same name
	 * @return true if the message appear else false
	 */
	public boolean verifyErrorMessageWhenNameAlreadyExists() {
	    String alreadyExists = alreadyExistsError.getAttribute("class");   
	    return !alreadyExists.contains("ng-hide") ? true : false;
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

	/**
	 * This method is to verify if icon is updated when it is changed
	 * @return true if icon change else false
	 */
	public boolean verifyIconUpdate(String icon) {
	    return ExplicitWait.waitForElement(AddResourceMap.ICON_BOX
		    .replace("iconName", icon), 30) ? true : false;
	}
}
