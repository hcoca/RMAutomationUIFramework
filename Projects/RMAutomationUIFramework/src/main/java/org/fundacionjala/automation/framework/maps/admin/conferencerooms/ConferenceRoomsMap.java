package org.fundacionjala.automation.framework.maps.admin.conferencerooms;

public class ConferenceRoomsMap {

	public static final String TURN_ON_OFF_BUTTON = "//div[@class='ngCanvas']/*/div[3]//span[text()='roomName']/ancestor::div[4]/div[1]//span";
	public static final String TURN_OFF_BUTTON = "//div[@class='ngCanvas']/*/div[3]//span[text()='roomName']/ancestor::div[4]/div[1]//span[@class='text-glow-gray fa fa-power-off']";
	public static final String TURN_ON_BUTTON = "//div[@class='ngCanvas']/*/div[3]//span[text()='roomName']/ancestor::div[4]/div[1]//span[@class='text-glow-green fa fa-power-off']";
	public static final String OUT_OF_ORDER_ICON = "//div[@class='ngCanvas']/*/div[3]//span[text()='roomName']/ancestor::div[4]/div[2]//span";
	public static final String ROOMS_COLUMN = "//div[@class='ngCanvas']/*/div[3]//span[2]";
	public static final String ROOM = "//span[text()='roomName'][2]";
	public static final String ROOM_DISABLED = "//span[text()='roomName'][1]";
	public static final String RESOURCE_BUTTONS = "//div[@ng-repeat='resource in resources']/span/span";
	public static final String RESOURCES = "//span[contains(.,'resource')]";
	public static final String RESOURCES_QUANTITY = "//span[contains(.,'x qty')]";

	public static final String PAGE_SIZE_BOX_SELECTOR = "//div[@id='roomsGrid']/div[3]/div/div[2]/div/select";
	public static final String PAGE_SIZE_OPTION = "//option[@value='sizePage']";
	public static final String NEXT_PAGE_FIELD = "//input[@type='number']";
	public static final String FIRST_ROW = "//div[@ng-row][1]/div[3]/div[2]/div";

	public static final String TOTAL_ITEMS_LABEL = "//span[contains(text(), 'Total Items')]";
	public static final String FILTER_TEXTBOX = "//span[text()='Filter By Room']/following::input";
	public static final String PAGE_SIZE = ".//*[@id='roomsGrid']//select/option[text()='sizePage']";
	public static final String ENABLE_DISABLE_MESSAGE = "//div[@class='ng-binding ng-scope'][contains(text(),'was')]";

}
