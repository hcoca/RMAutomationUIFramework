package org.fundacionjala.automation.framework.pages.admin.resource;

import org.fundacionjala.automation.framework.maps.admin.resource.RemoveResourceMap;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.ExplicitWait;
import org.fundacionjala.automation.framework.utils.common.UIActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * This class represents RemoveResourcePage
 * @author mariaalcocer
 *
 */
public class RemoveResourcePage {

	@FindBy (xpath = RemoveResourceMap.REMOVE_BUTTON) 
	WebElement removeButton;
	@FindBy (xpath = RemoveResourceMap.CANCEL_BUTTON) 
	WebElement cancelButton;
	@FindBy (xpath = RemoveResourceMap.CLOSE_BUTTON) 
	WebElement closeButton;
	
	/**
	 * The constructor initialize the RemoveResourcePage
	 */
	public RemoveResourcePage() {
	    
		PageFactory.initElements(BrowserManager.getDriver(), this);
	}
	
	/**
	 * This function is to click on REMOVE button
	 * @return ResourcePage
	 * @throws InterruptedException 
	 */
        public ResourcePage clickOnRemoveButton() throws InterruptedException {
    
        	UIActions.waitFor(RemoveResourceMap.REMOVE_BUTTON);
        	UIActions.clickAt(removeButton);
        	Thread.sleep(3000);
        	if (!ExplicitWait.isElementInvisible(
        		By.xpath(RemoveResourceMap.REMOVE_BUTTON), 10)) {
        	    clickOnCancelButton();
        	}
        	return new ResourcePage();
        }
	
	/**
	 * This function is to click on CANCEL button
	 * @return ResourcePage
	 */
	public ResourcePage clickOnCancelButton() {
	    
		UIActions.waitFor(RemoveResourceMap.CANCEL_BUTTON);
		UIActions.clickAt(cancelButton);
		
		return new ResourcePage();
	}
	
	/**
	 * This function is to click on CLOSE button
	 * @return ResourcePage
	 */
	public ResourcePage clickOnCloseButton() {
	    
		UIActions.waitFor(RemoveResourceMap.CLOSE_BUTTON);
		UIActions.clickAt(closeButton);
		
		return new ResourcePage();
	}
	
	/**
	 * This function is to verify if an association is displayed
	 * @param roomName
	 * @return true if the association id displayed else false
	 */
	public boolean verifyAssociatedRoomExist(String roomName) {
	    
		try {
			String xpath = RemoveResourceMap.NAME_ROOM.replace(
				"roomName", roomName);
			return (BrowserManager.getDriver().findElement(
				By.xpath(xpath))!= null) ? true : false;
		} catch (Exception e) {
			return false;
		}
	}
}
