package org.fundacionjala.automation.framework.pages.admin.locations;

import org.fundacionjala.automation.framework.maps.admin.locations.LocationAssociationMap;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LocationAssociationPage {
	
	@FindBy (xpath = LocationAssociationMap.AVAILABLE_ROOMS_GRID) WebElement availableRoomsGrid;
	@FindBy (xpath = LocationAssociationMap.ASSOCIATED_ROOMS_GRID) WebElement associatedRoomsGrid;
	@FindBy (xpath = LocationAssociationMap.SAVE_BUTTON) WebElement saveButton;
	public LocationAssociationPage(){
		PageFactory.initElements(BrowserManager.getDriver(), this);
	}
	
	public LocationAssociationPage clickOnAddAvailableRoom(String roomName){
		availableRoomsGrid.findElement(By.xpath("//div[text()='"+roomName+"']/following::button[1]")).click();
		LogManager.info("An available room has been added");
		return this;
	}
	
	public LocationAssociationPage clickOnRemoveAssociatedRoom(String roomName) {
		associatedRoomsGrid.findElement(By.xpath("//div[text()='"+roomName+"']/following::button[1]")).click();
		LogManager.info("An available room has been removed");
		return this;
	}
	
	public LocationPage clickOnSaveButton(){
		saveButton.click();
		LogManager.info("Save button has been clicked");
		return new LocationPage();
	}
	
	public boolean verifyAvailableRoomDisplayed(String roomName){
		try {
			availableRoomsGrid.findElement(By.xpath("//div[text()='"+roomName+"']"));
			LogManager.info("Test Passed");
			return true;
	    } catch (NoSuchElementException e) {
	    	LogManager.warning("Test Failed");
	    	LogManager.error("Element not found (Exception)");
			return false;
	    }
	}
	
	public boolean verifyAssociatedRoomDisplayed(String roomName){
		try {
			associatedRoomsGrid.findElement(By.xpath("//div[text()='"+roomName+"']"));
			LogManager.info("Test Passed");
			return true;
	    } catch (NoSuchElementException e) {
	    	LogManager.warning("Test Failed");
	    	LogManager.error("Element not found (Exception)");
			return false;
	    }
	}
}
