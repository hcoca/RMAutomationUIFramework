package org.fundacionjala.automation.framework.pages.admin.conferencerooms;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.fundacionjala.automation.framework.maps.admin.conferencerooms.ConferenceRoomsMap;
import org.fundacionjala.automation.framework.maps.admin.impersonation.ImpersonationMap;
import org.fundacionjala.automation.framework.pages.admin.home.AdminPage;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.ExplicitWait;
import org.fundacionjala.automation.framework.utils.common.PropertiesReader;
import org.fundacionjala.automation.framework.utils.common.UIActions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

    /**
     * Set "page" of the ConferenceRoom's table.
     * @param page: The new page that the field receives.
     * @return this "ConferenceRoomsPage".
     */
    @FindBy(xpath = ConferenceRoomsMap.NEXT_PAGE_FIELD)
    WebElement pageField;

    public ConferenceRoomsPage setPage(String page) {
	
	pageField.clear();
	pageField.sendKeys(page);
	return this;
    }

    /**
     * Get the first Row's name of the ConferenceRoom's table.
     * @return a String parameter.
     */
    @FindBy(xpath = ConferenceRoomsMap.FIRST_ROW)
    WebElement firstRow;

    public String getFirstRow() {
	
	return firstRow.getText();
    }

    /**
     * Click on a room according the Room's name.
     * @param roomName: The name of a Room.
     * @return this "ConferenceRoomsPage".
     */
    public ConferenceRoomsPage selectOutOfOrderIcon(String roomName) {

	String iconOutOfOrder = ConferenceRoomsMap.OUT_OF_ORDER_ICON.replace(
		"roomName", roomName);
	ExplicitWait.waitForElement(iconOutOfOrder, 5);
	BrowserManager.getDriver().findElement(By.xpath(iconOutOfOrder))
		.click();
	return this;
    }
   /**
    * This method is to return a list of web elements - rooms 
    * @return List<WebElement>
    */
    public List<WebElement> getRooms() {

	return ExplicitWait.getElementsWhenVisible(
		By.xpath(ConferenceRoomsMap.ROOMS_COLUMN), 60);
    }

    /**
     * This method is to return one web element
     * @param roomName - string that represent the displayname of the room
     * @return WebElement
     */
    private WebElement getRoom(String roomName) {

	String xpathRoom = ConferenceRoomsMap.ROOM
		.replace("roomName", roomName);

	return ExplicitWait.getWhenVisible(By.xpath(xpathRoom), 20);
    }
    
    /**
     * This method is to return one room disabled
     * @param ro that represent the displayname of the room
     * @return
     */
    private WebElement getRoomDisabled(String roomName) {
	
	String xpathRoom = ConferenceRoomsMap.ROOM_DISABLED
		.replace("roomName", roomName);
	return ExplicitWait.getWhenVisible(By.xpath(xpathRoom), 20);
    }
   
    /**
     * This method open the pop-up configuration of one room	
     * @param roomToModify - represent the displayname of the room
     * @return RoomInfoPage
     * @throws InterruptedException 
     */
    public RoomInfoPage openConfigurationPage(String roomToModify) throws InterruptedException {
    	Thread.sleep(2000);
    	
	UIActions.doubleClick(getRoom(roomToModify));
	return new RoomInfoPage();
    }
    
    /**
     * This method open the pop-up configuration of one room disabled	
     * @param roomToModify - represent the displayname of the room
     * @return RoomInfoPage
     * @throws InterruptedException 
     */
    public RoomInfoPage openRoomDisabled(String roomToModify) throws InterruptedException {
    Thread.sleep(2000);
	UIActions.doubleClick(getRoomDisabled(roomToModify));
	return new RoomInfoPage();
    }

    /**
     * This method is to perform a double click on one room	
     * @param roomToModify - represent the displayname of the room
     * @return RoomInfoPage
     */
    public RoomInfoPage doubleClickOnRoom(String roomToModify) {

	WebElement roomElement = getRoom(roomToModify);
	roomElement.click();
	UIActions.doubleClickJS(roomElement);
	return new RoomInfoPage();
    }

    /**
     * This method is to disable a room by mongoDB	
     * @param roomToModify - represent the displayname of the room
     * @return ConferenceRoomsPage
     */
    public ConferenceRoomsPage disableRoom(String roomToModify) throws UnknownHostException {
	    
	MongoClient mongoClient = new MongoClient(PropertiesReader.getHostIPAddress(), 
		                                  PropertiesReader.getMongoDBConnectionPort());
	
        DB db = mongoClient.getDB(PropertiesReader.getDBName());
	DBCollection collection = db.getCollection(PropertiesReader.getRoomsFieldName());
	    
        BasicDBObject newDocument = new BasicDBObject();
	newDocument.append("$set", new BasicDBObject().append("enabled", false));
				
	BasicDBObject searchQuery = new BasicDBObject().append("displayName", roomToModify);

	collection.update(searchQuery, newDocument);
	
	return this;
    }
    
    /**
     * This method is to enable a room by mongoDB	
     * @param roomToModify - represent the displayName of the room
     * @return ConferenceRoomsPage
     */
    public ConferenceRoomsPage enableRoom(String roomToModify) throws UnknownHostException {
	
	MongoClient mongoClient = new MongoClient(PropertiesReader.getHostIPAddress(), 
        PropertiesReader.getMongoDBConnectionPort());
        
        DB db = mongoClient.getDB(PropertiesReader.getDBName());
        DBCollection collection = db.getCollection(PropertiesReader.getRoomsFieldName());
        
        BasicDBObject newDocument = new BasicDBObject();
        newDocument.append("$set", new BasicDBObject().append("enabled", true));
        
        BasicDBObject searchQuery = new BasicDBObject().append("displayName", roomToModify);
        
        collection.update(searchQuery, newDocument);
        
        return this;
    }
    
    /**
     * This method is to verify if one room exists in ConferenceRooms page
     * @param roomName that represent the name of one room
     * @return boolean: return true if the room is on the conferenceRooms page
     */
    public boolean VerifyIfRoomExist(String roomName) {
	
	return ((getRoom(roomName) != null) ? true : false);
    }
    
    @FindBy(xpath = ConferenceRoomsMap.RESOURCE_BUTTONS)
    List<WebElement> resourceButtons;
    
    /**
     * This method is to get one resource of the conference rooms
     * @param resourceName - represent the name of the resource
     * @return one Web element that represent the resource
     */
    private WebElement getResource(String resourceName) {
	
	for (WebElement resource : resourceButtons) {
	    if (resource.getText().trim().equalsIgnoreCase(resourceName)) {
		return resource;
	    }
	}
	return null;
    }

    /**
     * This method is to verify if one room is created in the conference Rooms page
     * @param resourceName - represent the name of the resource
     * @return one Web element that represent the resource
     */
    public boolean verifyIfResourceCreatedIsInConferenceRoomPage(String resourceName) {
	
	if (getResource(resourceName) != null) {
	    return true;
	}
	return false;

    }

    /**
     * This method is to perform a click on enable button of a room.
     * @param roomName name of specific room
     * @return ConferenceRoomsPage
     */
    public ConferenceRoomsPage clickOnTurnOnOffButton(String roomName) {
	
	WebElement turnOnOffButton = BrowserManager.getDriver().findElement(
		By.xpath(ConferenceRoomsMap.TURN_ON_OFF_BUTTON.replace(
			"roomName", roomName)));
	
	Actions action = new Actions(BrowserManager.getDriver());
	action.click(turnOnOffButton).perform();
	
	(new WebDriverWait(BrowserManager.getDriver(), 30))
	.until(ExpectedConditions.visibilityOfElementLocated(By
			.xpath(ConferenceRoomsMap.ENABLE_DISABLE_MESSAGE)));
	return this;
    }

    /**
     * This method is to do a click on the resource that is on the top of the rooms page
     * @param resourceName - represent the name of the resource
     * @return ConferenceRoomsPage
     */
    public ConferenceRoomsPage clickOnResource(String resourceName) {

	String stringXpath = ConferenceRoomsMap.RESOURCES.replace("resource",
		resourceName);
	ExplicitWait.clickWhenReady(By.xpath(stringXpath), 10);
	return this;
    }

    /**
     * This method is to verify if the quantity is displayed on the conference rooms page
     * @param quantity - represent the quantity searched
     * @return boolean expression
     */
    public boolean isQuantityDisplayed(String quantity) {

	String stringXpath = ConferenceRoomsMap.RESOURCES_QUANTITY.replace(
		"qty", quantity);

	WebDriverWait wait = new WebDriverWait(BrowserManager.getDriver(), 5);

	try {
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By
		    .xpath(stringXpath)));
	    return true;

	} catch (TimeoutException te) {

	    return false;
	}
    }

    /**
     * This method is to generate a random number	
     * @param min - minimum value to generate e.g. 0 
     * @param max - maximum value o generate e.g. 10
     * @return int - random number that is in the interval of min and max
     */
    private int getRandomNum(int min, int max) {
	
	return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    /**
     * This method is to generate a random string name of room by mongo DB
     * @return String name of the room
     * @throws UnknownHostException - throws the exception if the database is not reached
     */
    public String getRandomRoom() throws UnknownHostException {

	MongoClient mongoClient = new MongoClient(PropertiesReader.getHostIPAddress(), PropertiesReader.getMongoDBConnectionPort());
	DB db = mongoClient.getDB(PropertiesReader.getDBName());
	DBCollection collection = db.getCollection("rooms");

	BasicDBObject query = new BasicDBObject();
	String regex = "\\ARoom00\\d";
	query.put("displayName",
		new BasicDBObject("$regex", regex).append("$options", "i"));

	BasicDBObject fields = new BasicDBObject();
	fields.put("displayName", 1);
	fields.put("_id", 0);

	ArrayList<String> rooms = new ArrayList<String>();
	DBCursor cursor = collection.find(query, fields);
	while (cursor.hasNext()) {
	    cursor.next();
	    rooms.add(cursor.curr().get("displayName").toString());
	}
	cursor.close();

	return rooms.get(getRandomNum(0, rooms.size() - 1));
    }

    @FindBy(xpath = ConferenceRoomsMap.TOTAL_ITEMS_LABEL)
    WebElement totalItemsLabel;

    /**
     * This function return a string of element of quantity of rooms.
     * @return quantity of rooms in rooms table.
     */
    public String getTotalItems() {
	
    	   ExplicitWait.getWhenVisible(By.xpath(
    		   ConferenceRoomsMap.TOTAL_ITEMS_LABEL), 30);
	return totalItemsLabel.getText().trim().replace("Total Items: ", "");
    }

    @FindBy(xpath = ConferenceRoomsMap.FILTER_TEXTBOX)
    WebElement filterTextbox;

    /**
     * This function is for writing on filter box on conference room page.
     * @param roomCriteria name of one room
     * @return ConferenceRoomsPage instance
     */
    public ConferenceRoomsPage typeOnFilterTextbox(String roomCriteria) {
	
	UIActions.clickAt(totalItemsLabel);
	UIActions.typeOn(filterTextbox, roomCriteria);
	UIActions.clickAt(totalItemsLabel);
	return this;
    }

    /**
     * Set the number of Rooms that will be displayed in the ConferenceRoomTable
     * @param sizePage: The number of Rooms in the table by page.
     * @return this "ConferenceRoomsPage".
     */
    public ConferenceRoomsPage selectPageSize(String sizePage) {
	WebElement size = BrowserManager.getDriver().findElement(
		By.xpath(ConferenceRoomsMap.PAGE_SIZE.replace("sizePage",sizePage)));
	UIActions.clickAt(size);
	return this;
    }

    /**
     * This function is to compare size page found with a criteria.
     * @param sizePage
     * @return true if the page is less than a some criteria
     */
    public boolean verifySizePage(int sizePage) {
	
	return getRoomsWithScrollBar(sizePage) <= sizePage ? true : false;
    }

    /**
     * this function is for get all rooms of one table using the scroll bar for
     * search all rooms in table.
     * @param sizePage size of page.
     * @return return size of rooms found.
     */
    private int getRoomsWithScrollBar(int sizePage) {
	
	int visibleRows = 15;
	int roomRead = sizePage / visibleRows;
	List<String> list = new ArrayList<String>();
	WebElement lastRow = null;
	for (int i = 0; i < roomRead; i++) {
	    for (WebElement room : getRooms()) {
		if (!list.contains(room.getText().trim())) {
		    list.add(room.getText().trim());
		}
		lastRow = room;
	    }
	    ((JavascriptExecutor) BrowserManager.getDriver()).executeScript(
		    "arguments[0].scrollIntoView(true);", lastRow);
	}
	return list.size();
    }

    /**
     * This method verify if there are rooms, by false does a second attempt
     * going to other menu and returning again.
     * @return Total rooms
     * @throws InterruptedException
     */
    public int verifyTotalItems() throws InterruptedException {
	WebElement totalItems =  ExplicitWait.getWhenVisible(By.xpath(
 		   ConferenceRoomsMap.TOTAL_ITEMS_LABEL), 30);
	if (totalItems == null){
	    
	    this.leftMenu.clickOnIssuesButton()
	    .clickOnConferenceRoomsButton();
	    ExplicitWait.getWhenVisible(By.xpath(
	 		   ConferenceRoomsMap.TOTAL_ITEMS_LABEL), 30);
	}
	
	return Integer.parseInt(totalItemsLabel.getText()
		.trim().replace("Total Items: ", ""));
    }
    
    @FindBy (xpath = ConferenceRoomsMap.ENABLE_DISABLE_MESSAGE) 
    WebElement enableDisableMessage;
    public String getMessage() {
	return enableDisableMessage.getText();
    }
}
