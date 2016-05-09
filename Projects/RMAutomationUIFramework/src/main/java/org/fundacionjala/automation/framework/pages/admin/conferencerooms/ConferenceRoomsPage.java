package org.fundacionjala.automation.framework.pages.admin.conferencerooms;

import java.util.List;

import org.fundacionjala.automation.framework.maps.admin.conferencerooms.ConferenceRoomsMap;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.ExplicitWait;
import org.fundacionjala.automation.framework.utils.common.UIActions;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ConferenceRoomsPage {
   
	public ConferenceRoomsPage() {
		PageFactory.initElements(BrowserManager.getDriver(), this);
	}
	
	public ConferenceRoomsPage selectOutOfOrderIcon(String roomName){
		String iconOutOfOrder =ConferenceRoomsMap.OUT_OF_ORDER_ICONS.replace("roomName", roomName);
		ExplicitWait.getWhenVisible(By.xpath(iconOutOfOrder), 5);
		BrowserManager.getDriver().findElement(By.xpath(iconOutOfOrder)).click();
		return this;
	}
				
	public List<WebElement> getRooms()
	{
	   return ExplicitWait.getElementsWhenVisible(By.xpath(ConferenceRoomsMap.ROOMS_COLUMN), 15);
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
    
    public RoomInfoPage doubleClickOnRoom(String roomToModify) {
    	WebElement roomElement = getRoom(roomToModify);
    	roomElement.click();
    	UIActions.doubleClickJS(roomElement);
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

	public ConferenceRoomsPage clickOnTurnOnOffButton(String roomName) {
		WebElement turnOnOffButton = BrowserManager.getDriver()
				.findElement(By.xpath(ConferenceRoomsMap.TURN_ON_OFF_BUTTON.replace("roomName", roomName)));
		UIActions.clickAt(turnOnOffButton);
		return this;
	}
	
public ConferenceRoomsPage clickOnResource(String resourceName) {
		
		String stringXpath = ConferenceRoomsMap.RESOURCES.replace("resource", resourceName);
		ExplicitWait.clickWhenReady(By.xpath(stringXpath), 10);
		return this;
	}

	public boolean isQuantityDisplayed(String quantity) {
		
		String stringXpath = ConferenceRoomsMap.RESOURCES_QUANTITY.replace("qty", quantity);

		WebDriverWait wait = new WebDriverWait(BrowserManager.getDriver(),5);

		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(stringXpath)));
			return true;
			
		} catch (TimeoutException te) {
			
			return false;
		}	
	}
}
