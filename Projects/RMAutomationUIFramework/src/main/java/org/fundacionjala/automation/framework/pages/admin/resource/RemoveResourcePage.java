package org.fundacionjala.automation.framework.pages.admin.resource;

import org.fundacionjala.automation.framework.maps.admin.resource.RemoveResourceMap;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.UIActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RemoveResourcePage {

	@FindBy (xpath = RemoveResourceMap.REMOVE_BUTTON) WebElement removeButton;
	@FindBy (xpath = RemoveResourceMap.CANCEL_BUTTON) WebElement cancelButton;
	@FindBy (xpath = RemoveResourceMap.CLOSE_BUTTON) WebElement closeButton;
	
	public RemoveResourcePage() {
		PageFactory.initElements(BrowserManager.getDriver(), this);
	}
	
	public ResourcePage clickRemoveButton() {
		UIActions.waitFor(RemoveResourceMap.REMOVE_BUTTON);
		UIActions.clickAt(removeButton);
		
		return new ResourcePage();
	}
	
	public ResourcePage clickCancelButton() {
		UIActions.waitFor(RemoveResourceMap.CANCEL_BUTTON);
		UIActions.clickAt(cancelButton);
		
		return new ResourcePage();
	}
	
	public ResourcePage clickCloseButton() {
		UIActions.waitFor(RemoveResourceMap.CLOSE_BUTTON);
		UIActions.clickAt(closeButton);
		
		return new ResourcePage();
	}
}
