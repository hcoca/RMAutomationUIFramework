package org.fundacionjala.automation.framework.pages.admin.resource;

import org.fundacionjala.automation.framework.maps.admin.resource.AddResourceMap;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.UIActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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
		UIActions.waitFor(AddResourceMap.NAME_TEXT_FIELD);
		UIActions.typeOn(nameTextField, name);
		
		return this;
	}
	
	public AddResourcePage setDisplayName(String displayName)
	{
		UIActions.waitFor(AddResourceMap.DISPLAY_NAME_TEXT_FIELD);
		UIActions.typeOn(displayNameTextField, displayName);
		
		return this;
	}
	
	public AddResourcePage setDescription(String description)
	{
		UIActions.waitFor(AddResourceMap.DESCRIPTION_TEXT_FIELD);
		UIActions.typeOn(descriptionTextField, description);
		
		return this;
	}
	
	public AddResourcePage selectIcon(String icon)
	{
		String xpath = AddResourceMap.ICON.replace("iconName", icon);
		WebElement element;
		UIActions.waitFor(AddResourceMap.ICON_BUTTON);
		UIActions.clickAt(iconButton);
		UIActions.waitFor(xpath);
		element = BrowserManager.getDriver().findElement(By.xpath(xpath));
		UIActions.clickAt(element);
		
		return this;
	}
	
	public ResourcePage clickOnSaveButton()
	{
		UIActions.waitFor(AddResourceMap.SAVE_BUTTON);
		UIActions.clickAt(saveButton);
		
		return new ResourcePage();
	}
	
	public ResourcePage clickOnCancelButton()
	{
		UIActions.waitFor(AddResourceMap.CANCEL_BUTTON);
		UIActions.clickAt(cancelButton);
		
		return new ResourcePage();
	}

}
