package org.fundacionjala.automation.framework.pages.admin.resource;

import org.fundacionjala.automation.framework.maps.admin.resource.AddResourceMap;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddResourcePage {
	
	@FindBy (xpath = AddResourceMap.NAME_TEXT_FIELD) WebElement nameTextField;
	@FindBy (xpath = AddResourceMap.DISPLAY_NAME_TEXT_FIELD) WebElement displayNameTextField;
	@FindBy (xpath = AddResourceMap.DESCRIPTION_TEXT_FIELD) WebElement descriptionTextField;
	@FindBy (xpath = AddResourceMap.SAVE_BUTTON) WebElement saveButton;
	@FindBy (xpath = AddResourceMap.CANCEL_BUTTON) WebElement cancelButton;
	@FindBy (xpath = AddResourceMap.ICON_BUTTON) WebElement iconButton;
	
	public AddResourcePage()
	{
		PageFactory.initElements(BrowserManager.getDriver(), this);
	}
	
	public AddResourcePage setResourceName(String name)
	{
		By locator = By.xpath(AddResourceMap.NAME_TEXT_FIELD);
		(new WebDriverWait(BrowserManager.getDriver(), 10))
		.until(ExpectedConditions.presenceOfElementLocated(locator));
		nameTextField.sendKeys(name);
		LogManager.info("'"+name+"' has been set");
		
		return this;
	}
	
	public AddResourcePage setDisplayName(String displayName)
	{
		By locator = By.xpath(AddResourceMap.DISPLAY_NAME_TEXT_FIELD);
		(new WebDriverWait(BrowserManager.getDriver(), 10))
		.until(ExpectedConditions.presenceOfElementLocated(locator));
		displayNameTextField.clear();
		displayNameTextField.sendKeys(displayName);
		LogManager.info("'" + displayName + " has been set up in ");
		
		return this;
	}
	
	public AddResourcePage setDescription(String description)
	{
		By locator = By.xpath(AddResourceMap.DESCRIPTION_TEXT_FIELD);
		(new WebDriverWait(BrowserManager.getDriver(), 10))
		.until(ExpectedConditions.presenceOfElementLocated(locator));
		descriptionTextField.clear();
		descriptionTextField.sendKeys(description);
		LogManager.info("'" + description + " has been set up in ");
				
		return this;
	}
	
	public AddResourcePage selectIcon(String icon)
	{
		String xpath = AddResourceMap.ICON.replace("iconName", icon);
		WebElement element;
		(new WebDriverWait(BrowserManager.getDriver(), 10))
		.until(ExpectedConditions.presenceOfElementLocated(By.xpath(AddResourceMap.ICON_BUTTON)));
		iconButton.click();
		(new WebDriverWait(BrowserManager.getDriver(), 10))
		.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		element = BrowserManager.getDriver().findElement(By.xpath(xpath));
		element.click();
		LogManager.info("'Icon' button has been selected");
				
		return this;
	}
	
	public ResourcePage clickOnSaveButton()
	{
		(new WebDriverWait(BrowserManager.getDriver(), 10))
		.until(ExpectedConditions.presenceOfElementLocated(By.xpath(AddResourceMap.SAVE_BUTTON)));
		saveButton.click();
		LogManager.info("'Save' button has been clicked");
		
		return new ResourcePage();
	}
	
	public ResourcePage clickOnCancelButton()
	{
		(new WebDriverWait(BrowserManager.getDriver(), 10))
		.until(ExpectedConditions.presenceOfElementLocated(By.xpath(AddResourceMap.CANCEL_BUTTON)));
		cancelButton.click();
		LogManager.info("'Cancel' button has been clicked");
		
		return new ResourcePage();
	}

}
