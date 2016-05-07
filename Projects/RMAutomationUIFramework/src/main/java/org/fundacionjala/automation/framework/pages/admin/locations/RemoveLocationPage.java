package org.fundacionjala.automation.framework.pages.admin.locations;

import org.fundacionjala.automation.framework.maps.admin.locations.RemoveLocationMap;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.LogManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RemoveLocationPage {
	
	@FindBy (xpath = RemoveLocationMap.REMOVE_BUTTON) WebElement removeButton;
	public RemoveLocationPage(){
		PageFactory.initElements(BrowserManager.getDriver(), this);
	}
	
	public LocationPage clickOnRemoveButton() {
		removeButton.click();
		LogManager.info("'-Remove'button has been clicked");
		return new LocationPage();
	}
}
