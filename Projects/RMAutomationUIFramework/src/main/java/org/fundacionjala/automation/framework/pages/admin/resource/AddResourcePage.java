package org.fundacionjala.automation.framework.pages.admin.resource;

import org.fundacionjala.automation.framework.maps.admin.resource.AddResourceMap;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.UIActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddResourcePage {
	
	@FindBy (xpath = AddResourceMap.NAME_TEXTBOX) WebElement nameTextBox;
	@FindBy (xpath = AddResourceMap.DISPLAY_NAME_TEXTBOX) WebElement displayNameTextBox;
	@FindBy (xpath = AddResourceMap.DESCRIPTION_TXTAREA) WebElement descriptionTextarea;
	@FindBy (xpath = AddResourceMap.SAVE_BTN) WebElement saveButton;
	@FindBy (xpath = AddResourceMap.CANCEL_BTN) WebElement cancelButton;
	@FindBy (xpath = AddResourceMap.ICON_BTN) WebElement iconButton;
	
	public AddResourcePage()
	{
		PageFactory.initElements(BrowserManager.getDriver(), this);
	}
	
	public AddResourcePage writeResourceName(String name)
	{
		UIActions.waitFor(AddResourceMap.NAME_TEXTBOX);
		UIActions.typeOn(nameTextBox, name);
		
		return this;
	}
	
	public AddResourcePage writeDisplayName(String displayName)
	{
		UIActions.waitFor(AddResourceMap.DISPLAY_NAME_TEXTBOX);
		UIActions.typeOn(displayNameTextBox, displayName);
		
		return this;
	}
	
	public AddResourcePage writeDescription(String description)
	{
		UIActions.waitFor(AddResourceMap.DESCRIPTION_TXTAREA);
		UIActions.typeOn(descriptionTextarea, description);
		
		return this;
	}
	
	public AddResourcePage selectIcon(String icon)
	{
		String xpath = AddResourceMap.ICON.replace("iconName", icon);
		WebElement element;
		UIActions.waitFor(AddResourceMap.ICON_BTN);
		UIActions.clickAt(iconButton);
		UIActions.waitFor(xpath);
		element = BrowserManager.getDriver().findElement(By.xpath(xpath));
		UIActions.clickAt(element);
		
		return this;
	}
	
	public ResourcePage clickOnSaveButton()
	{
		UIActions.waitFor(AddResourceMap.SAVE_BTN);
		UIActions.clickAt(saveButton);
		
		return new ResourcePage();
	}
	
	public ResourcePage clickOnCancelButton()
	{
		UIActions.waitFor(AddResourceMap.CANCEL_BTN);
		UIActions.clickAt(cancelButton);
		
		return new ResourcePage();
	}

}
