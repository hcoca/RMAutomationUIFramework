package org.fundacionjala.automation.framework.pages.admin.locations;

import org.fundacionjala.automation.framework.maps.admin.locations.LocationMap;
import org.fundacionjala.automation.framework.pages.admin.home.AdminPage;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.LogManager;
import org.fundacionjala.automation.framework.utils.common.UIActions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LocationPage extends AdminPage{
	
	@FindBy (xpath = LocationMap.LOCATION_TABLE) WebElement locationTable;
	@FindBy (xpath = LocationMap.ADD_LOCATION_BUTTON) WebElement addButton;
	@FindBy (xpath = LocationMap.REMOVE_LOCATION_BUTTON) WebElement removeButton;
	public LocationPage(){
		PageFactory.initElements(BrowserManager.getDriver(), this);
	}
	
	public boolean verifyLocationIsDisplayed(String name){
		try {
			locationTable.findElement(By.xpath("//span[text()='"+name+"']"));
			LogManager.info("Test Passed");
			return true;
	    } catch (NoSuchElementException e) {
	    	LogManager.warning("Test Failed");
	    	LogManager.error("Element not found (Exception)");
			return false;
	    }
	}
	
	public boolean verifyLocationIsDisplayedByDisplayName(String displayName){
		try {
			locationTable.findElement(By.xpath("//div[text()='"+displayName+"']"));
			LogManager.info("Test Passed");
			return true;
	    } catch (NoSuchElementException e) {
	    	LogManager.warning("Test Failed");
	    	LogManager.error("Element not found (Exception)");
			return false;
	    }
	}

	public AddLocationPage clickOnAddButton() {
		addButton.click();
		LogManager.info("'+Add'button has been clicked");
		return new AddLocationPage();
	}

	public UpdateLocationPage doubleClickOnALocation(String displayName) {
		UIActions.doubleClick(locationTable.findElement(By.xpath("//div[text()='"+displayName+"']")));
		return new UpdateLocationPage();
	}
}
