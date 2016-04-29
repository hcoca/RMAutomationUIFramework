package org.fundacionjala.automation.framework.pages.admin.resource;

import org.fundacionjala.automation.framework.maps.admin.resource.ResourceInfoMap;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.UIActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResourceInfoPage {
	@FindBy (xpath = ResourceInfoMap.NAME_TEXT_FIELD) WebElement nameTextField;
	@FindBy (xpath = ResourceInfoMap.DISPLAY_NAME_TEXT_FIELD) WebElement displayNameTextField;
	@FindBy (xpath = ResourceInfoMap.DESCRIPTION_TEXT_FIELD) WebElement descriptionTextField;
	@FindBy (xpath = ResourceInfoMap.SAVE_BUTTON) WebElement saveButton;
	@FindBy (xpath = ResourceInfoMap.CANCEL_BUTTON) WebElement cancelButton;
	@FindBy (xpath = ResourceInfoMap.ICON_BUTTON) WebElement iconButton;
	
	public ResourceInfoPage()
	{
		PageFactory.initElements(BrowserManager.getDriver(), this);
	}
	
	public ResourceInfoPage setResourceName(String name)
	{
		UIActions.waitFor(ResourceInfoMap.NAME_TEXT_FIELD);
		UIActions.typeOn(nameTextField, name);
		
		return this;
	}
	
	public ResourceInfoPage setDisplayName(String displayName)
	{
		UIActions.waitFor(ResourceInfoMap.DISPLAY_NAME_TEXT_FIELD);
		UIActions.typeOn(displayNameTextField, displayName);
		
		return this;
	}
	
	public ResourceInfoPage setDescription(String description)
	{
		UIActions.waitFor(ResourceInfoMap.DESCRIPTION_TEXT_FIELD);
		UIActions.typeOn(descriptionTextField, description);
		
		return this;
	}
	
	public ResourceInfoPage selectIcon(String icon)
	{//TODO: Find by page of icons
		String xpath = ResourceInfoMap.ICON.replace("iconName", icon);
		WebElement element;
		UIActions.waitFor(ResourceInfoMap.ICON_BUTTON);
		UIActions.clickAt(iconButton);
		UIActions.waitFor(xpath);
		element = BrowserManager.getDriver().findElement(By.xpath(xpath));
		UIActions.clickAt(element);
		
		return this;
	}
	
	public ResourcePage clickOnSaveButton()
	{
		UIActions.waitFor(ResourceInfoMap.SAVE_BUTTON);
		UIActions.clickAt(saveButton);
		
		return new ResourcePage();
	}
	
	public ResourcePage clickOnCancelButton()
	{
		UIActions.waitFor(ResourceInfoMap.CANCEL_BUTTON);
		UIActions.clickAt(cancelButton);
		
		return new ResourcePage();
	}
}
