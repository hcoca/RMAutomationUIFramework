package org.fundacionjala.automation.framework.pages.admin.resource;

import org.fundacionjala.automation.framework.maps.admin.resource.RemoveResourceMap;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.UIActions;
import org.openqa.selenium.By;
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
	
	public ResourcePage clickOnRemoveButton() {
		UIActions.waitFor(RemoveResourceMap.REMOVE_BUTTON);
		UIActions.clickAt(removeButton);
		
		return new ResourcePage();
	}
	
	public ResourcePage clickOnCancelButton() {
		UIActions.waitFor(RemoveResourceMap.CANCEL_BUTTON);
		UIActions.clickAt(cancelButton);
		
		return new ResourcePage();
	}
	
	public ResourcePage clickOnCloseButton() {
		UIActions.waitFor(RemoveResourceMap.CLOSE_BUTTON);
		UIActions.clickAt(closeButton);
		
		return new ResourcePage();
	}

	public boolean verifyAssociatedRoomExist(String roomName) {
		try {
			String xpath = RemoveResourceMap.NAME_ROOM.replace("roomName", roomName);
			if(BrowserManager.getDriver().findElement(By.xpath(xpath))!= null)
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		}
	}
}
