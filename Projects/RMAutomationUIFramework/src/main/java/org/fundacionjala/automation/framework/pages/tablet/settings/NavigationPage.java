package org.fundacionjala.automation.framework.pages.tablet.settings;

import java.util.List;

import org.fundacionjala.automation.framework.maps.tablet.settings.NavigationMap;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NavigationPage extends SettingsPage {

	@FindBy (xpath = NavigationMap.DEFAULT_ROOM_TOGGLE_BUTTON) WebElement defaultRoomToggleButton;
	@FindBy (xpath = NavigationMap.SAVE_BUTTON) WebElement saveButton;
	@FindBy (xpath = NavigationMap.ROOMS_LIST) WebElement roomsList;
	
	public NavigationPage() {
		PageFactory.initElements(BrowserManager.getDriver(), this);
	}
	
	public NavigationPage clickOnRoomToggleButton() {
		(new WebDriverWait(BrowserManager.getDriver(), 30)).until(ExpectedConditions.elementToBeClickable(defaultRoomToggleButton));
		defaultRoomToggleButton.click();
		
		LogManager.info("Room Toggle Button has been clicked");
		
		return this;
	}
	
	public NavigationPage clickOnSaveButton() {
		(new WebDriverWait(BrowserManager.getDriver(), 30)).until(ExpectedConditions.elementToBeClickable(saveButton));
		saveButton.click();
		
		LogManager.info("Save Button has been clicked");
		
		return this;
	}
	
	public NavigationPage selectConferenceRoom(String roomName){		
		WebElement room = getConferenceRoom(roomName);
		room.click();
		
		return this;
	}
	
	private WebElement getConferenceRoom(String name){
		(new WebDriverWait(BrowserManager.getDriver(), 30)).until(ExpectedConditions.visibilityOf(roomsList));
		
		List<WebElement> rooms = roomsList.findElements(By.xpath(NavigationMap.ROOMS_LIST_ELEMENT));
		
		for(WebElement room : rooms) {
			String roomName = room.findElement(By.xpath(NavigationMap.ROOM_NAME)).getText();
			
			if(roomName.equals(name)) {
				return room;
			}
		}
		
		return null;
	}
}
