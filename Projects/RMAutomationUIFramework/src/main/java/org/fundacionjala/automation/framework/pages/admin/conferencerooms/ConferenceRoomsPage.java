package org.fundacionjala.automation.framework.pages.admin.conferencerooms;

import java.util.List;

import org.fundacionjala.automation.framework.maps.admin.conferencerooms.ConferenceRoomsMap;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.ExplicitWait;
import org.fundacionjala.automation.framework.utils.common.UIActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConferenceRoomsPage {
   
	public ConferenceRoomsPage() {
		PageFactory.initElements(BrowserManager.getDriver(), this);
	}
	
	public ConferenceRoomsPage selectOutOfOrderIcon(String roomName){
		String iconOutOfOrder =ConferenceRoomsMap.OUT_OF_ORDER_ICONS.replace("roomName", roomName);
		BrowserManager.getDriver().findElement(By.xpath(iconOutOfOrder)).click();
		return this;
	}
				
	public List<WebElement> getRooms()
	{
	   return ExplicitWait.getElementsWhenVisible(By.xpath(ConferenceRoomsMap.ROOMS_COLUMN), 15);
	}
	
	@FindBy (xpath = ConferenceRoomsMap.ENABLED_ROOMS_ROWS) List<WebElement> enabledButtons;
	private List<WebElement> getEnableRooms() {
       return enabledButtons;
	}
	
	@FindBy (xpath = ConferenceRoomsMap.DISABLED_ROOMS_ROWS) List<WebElement> disabledButtons;
	private List<WebElement> getDisabledRooms() {
       return disabledButtons;
	}
	
	private WebElement getRoom(String roomName)
	{
		for(WebElement room : getRooms()){
	    	String roomText = room.getText();
	    	if (roomText.trim().equals(roomName)){
				return room;
			}
	    }
		return null;
	}
	
    public RoomInfoPage openConfigurationPage(String roomToModify) {
	    UIActions.doubleClick(getRoom(roomToModify));
		return new RoomInfoPage();
	}

	public ConferenceRoomsPage enableRoom() {
		
		return this;
	}
	public ConferenceRoomsPage disableRoom(String roomToModify) {
		return this;
	}
	
	public boolean roomIsEnabled(String roomName)
	{
		return true;
	}
	
	public boolean VerifyIfRoomExist(String expectedResult) {
		if(getRoom(expectedResult) != null)
		{
			return true;
		}else{
			return false;
		}
	}

	@FindBy (xpath = ConferenceRoomsMap.RESOURCE_BUTTONS) List<WebElement> resourceButtons;
	private WebElement getResource(String resourceName){
		for (WebElement resource : resourceButtons) {
			if(resource.getText().trim().equalsIgnoreCase(resourceName)){
				return resource;
			}
		}
		return null;
	}
	
	
	public boolean verifyIfResourceCreatedIsInConferenceRoomPage(
			String expectedResult) {
		if(getResource(expectedResult) != null){
			return true;
		}
		return false;
		
	}
}
