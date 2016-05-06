package org.fundacionjala.automation.framework.pages.admin.conferencerooms;

import org.fundacionjala.automation.framework.maps.admin.conferencerooms.RoomInfoMap;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RoomInfoPage {

	public RoomInfoPage() {
		PageFactory.initElements(BrowserManager.getDriver(), this);
	}

	
	@FindBy (linkText = RoomInfoMap.RESOURCES_ASSOCIATIONS_LINK) WebElement resourcesAssociations;
	public ResourceAssociationsPage clickOnResourceAssociations() {
		resourcesAssociations.click();
		return new ResourceAssociationsPage();
	}
	
	

}
