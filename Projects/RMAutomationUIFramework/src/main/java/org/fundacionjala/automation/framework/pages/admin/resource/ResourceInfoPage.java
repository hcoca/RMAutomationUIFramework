package org.fundacionjala.automation.framework.pages.admin.resource;

import java.util.List;

import org.fundacionjala.automation.framework.maps.admin.resource.ResourceInfoMap;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.ExplicitWait;
import org.fundacionjala.automation.framework.utils.common.LogManager;
import org.fundacionjala.automation.framework.utils.common.UIActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * This class represents ResourceInfoPage
 * @author mariaalcocer
 *
 */
public class ResourceInfoPage {
    
	@FindBy (xpath = ResourceInfoMap.NAME_TEXT_FIELD) 
	WebElement nameTextField;
	@FindBy (xpath = ResourceInfoMap.DISPLAY_NAME_TEXT_FIELD)
	WebElement displayNameTextField;
	@FindBy (xpath = ResourceInfoMap.DESCRIPTION_TEXT_FIELD) 
	WebElement descriptionTextField;
	@FindBy (xpath = ResourceInfoMap.SAVE_BUTTON) 
	WebElement saveButton;
	@FindBy (xpath = ResourceInfoMap.CANCEL_BUTTON) 
	WebElement cancelButton;
	@FindBy (xpath = ResourceInfoMap.ICON_BUTTON) 
	WebElement iconButton;
	@FindBy (xpath = ResourceInfoMap.LEFT_BUTTON) 
	WebElement leftButton;
	@FindBy (xpath = ResourceInfoMap.RIGHT_BUTTON) 
	WebElement rightButton;
	@FindBy (css = ResourceInfoMap.PAGE_COUNT) 
	WebElement txt_count;
	
	/**
	 * The constructor initialize the ResourceInfoPage class
	 */
	public ResourceInfoPage()
	{
		PageFactory.initElements(BrowserManager.getDriver(), this);
	}
	
	/**
	 * Fills name text field on ResourceInfoPage
	 * @param name
	 * @return ResourceInfoPage
	 */
	public ResourceInfoPage setResourceName(String name)
	{
		UIActions.waitFor(ResourceInfoMap.NAME_TEXT_FIELD);
		UIActions.typeOn(nameTextField, name);
		
		return this;
	}
	
	/**
	 * Fills displayName text field on ResourceInfoPage
	 * @param displayName
	 * @return ResourceInfoPage
	 */
	public ResourceInfoPage setDisplayName(String displayName)
	{
		UIActions.waitFor(ResourceInfoMap.DISPLAY_NAME_TEXT_FIELD);
		UIActions.typeOn(displayNameTextField, displayName);
		
		return this;
	}
	
	/**
	 * Fills description text field on ResourceInfoPage
	 * @param description
	 * @return ResourceInfoPage
	 */
	public ResourceInfoPage setDescription(String description)
	{
		UIActions.waitFor(ResourceInfoMap.DESCRIPTION_TEXT_FIELD);
		UIActions.typeOn(descriptionTextField, description);
		
		return this;
	}
	
	/**
	 * Selects an icon on ResourceInfoPage
	 * @param icon
	 * @return ResourceInfoPage
	 */
	public ResourceInfoPage selectIcon(String icon)
	{	
		
		UIActions.waitFor(ResourceInfoMap.ICON_BUTTON);
		UIActions.clickAt(iconButton);
		WebElement iconToChange = FindIcon(icon);
		if (iconToChange != null){
			iconToChange.click();
		}
		
		return this;
	}
	
	/**
	 * This function is to click on SAVE button on ResourceInfoPage
	 * @return ResourceInfoPage
	 */
	public ResourcePage clickOnSaveButton()
	{
		UIActions.waitFor(ResourceInfoMap.SAVE_BUTTON);
		UIActions.clickAt(saveButton);
		
		return new ResourcePage();
	}
	
	/**
	 * This function is to click on CANCEL button on ResourceInfoPage
	 * @return ResourceInfoPage
	 */
	public ResourcePage clickOnCancelButton()
	{
		UIActions.waitFor(ResourceInfoMap.CANCEL_BUTTON);
		UIActions.clickAt(cancelButton);
		
		return new ResourcePage();
	}
	
	/**
	 * This function is to select an ICON button on ResourceInfoPage
	 * @param iconName
	 * @return ResourceInfoPage
	 */
	public WebElement FindIcon(String iconName)
	{
		String xpath = 
			ResourceInfoMap.ICON.replace("iconName", iconName);
		List<WebElement> elements;
		LogManager.info("Finding the icon from page 1:" + iconName);
		
		while(txt_count.getText().charAt(0) != '1')
		{
			leftButton.click();
		}
		
		byte count = 1;
		elements = 
			BrowserManager.getDriver().findElements(By.xpath(xpath));
		while(elements.size() == 0 && count <= 9)
		{
			LogManager.info(iconName 
					+ " is not in the page " 
					+ count); 
			rightButton.click();
			elements = BrowserManager.getDriver().findElements(
				By.xpath(xpath));
			count++;
		}
		
		if(elements.size()>0){
			LogManager.info(iconName 
					+ " is in the page " 
					+ count 
					+ " of icons"); 
			return elements.get(0);
		}
		return null;
		
	}
	
	/**
	 * This function is to selects on RECOURCE ASSOCIATION option
	 * @return ResourceAssociationsPage
	 */
	public ResourceAssociationsPage clickOnResourcesAssociation()
	{
	   ExplicitWait.clickWhenReady(By.linkText(
		   ResourceInfoMap.RESOURCES_ASSOCIATIONS_LINK), 5);
	   return new ResourceAssociationsPage();	
	}
	
}











