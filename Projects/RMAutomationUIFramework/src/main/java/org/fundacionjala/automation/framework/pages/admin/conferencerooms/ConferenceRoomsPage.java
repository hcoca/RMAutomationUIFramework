package org.fundacionjala.automation.framework.pages.admin.conferencerooms;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.fundacionjala.automation.framework.maps.admin.conferencerooms.ConferenceRoomsMap;
import org.fundacionjala.automation.framework.pages.admin.home.AdminPage;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.ExplicitWait;
import org.fundacionjala.automation.framework.utils.common.PropertiesReader;
import org.fundacionjala.automation.framework.utils.common.UIActions;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

public class ConferenceRoomsPage extends AdminPage {
   
	public ConferenceRoomsPage() {
		PageFactory.initElements(BrowserManager.getDriver(), this);
	}
	
	public ConferenceRoomsPage selectOutOfOrderIcon(String roomName) {
		String iconOutOfOrder = ConferenceRoomsMap.OUT_OF_ORDER_ICONS.replace("roomName", roomName);
		ExplicitWait.getWhenVisible(By.xpath(iconOutOfOrder), 5);
		BrowserManager.getDriver().findElement(By.xpath(iconOutOfOrder)).click();
		return this;
	}
				
	public List<WebElement> getRooms() {
	   return ExplicitWait.getElementsWhenVisible(By.xpath(ConferenceRoomsMap.ROOMS_COLUMN), 15);
	}
	
	private WebElement getRoom(String roomName) {
	    String xpathRoom = ConferenceRoomsMap.ROOM.replace("roomName", roomName);
		return ExplicitWait.getWhenVisible(By.xpath(xpathRoom), 5);
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
	
	public boolean VerifyIfRoomExist(String expectedResult) {
		return ((getRoom(expectedResult) != null) ? true : false);
	}

	@FindBy (xpath = ConferenceRoomsMap.RESOURCE_BUTTONS) List<WebElement> resourceButtons;
	private WebElement getResource(String resourceName) {
		for (WebElement resource : resourceButtons) {
			if (resource.getText().trim().equalsIgnoreCase(resourceName)) {
				return resource;
			}
		}
		return null;
	}
	
	
	public boolean verifyIfResourceCreatedIsInConferenceRoomPage(String expectedResult) {
		
		return ((getResource(expectedResult) != null)? true : false);
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
	private int getRandomNum(int min, int max)
	{
		return ThreadLocalRandom.current().nextInt(min, max + 1);
	}
	
	public String getRandomRoom() throws UnknownHostException
	{
		String fieldName = "displayName";
		String regex = "\\ARoom00\\d";
		
		MongoClient mongoClient = new MongoClient(PropertiesReader.getHostIPAddress(), 27017);
		
		DB db = mongoClient.getDB(PropertiesReader.getDBName());
		DBCollection collection = db.getCollection(PropertiesReader.getRoomsFieldName());
		
		BasicDBObject query = new BasicDBObject();
		
		query.put(fieldName, new BasicDBObject("$regex", regex).append("$options", "i"));
		
		BasicDBObject fields = new BasicDBObject();
		fields.put(fieldName, 1);
		fields.put("_id", 0);
		
		ArrayList<String> rooms = new ArrayList<String>();
		DBCursor cursor = collection.find(query, fields);
		while (cursor.hasNext()) {
			cursor.next();
			rooms.add(cursor.curr().get(fieldName).toString());
		}
		cursor.close();
		
		return rooms.get(getRandomNum(0, rooms.size()-1));
	}
	
}
