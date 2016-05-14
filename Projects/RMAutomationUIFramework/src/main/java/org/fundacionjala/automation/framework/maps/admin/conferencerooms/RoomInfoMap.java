package org.fundacionjala.automation.framework.maps.admin.conferencerooms;

public class RoomInfoMap {

	public static final String RESOURCES_ASSOCIATIONS_LINK = "Resource Associations";
	public static final String OUT_OF_ORDER_PLANNING_LINK = "Out of Order Planning";
	public static final String OUT_OF_ORDER_LINK = "//a[text()='" + OUT_OF_ORDER_PLANNING_LINK + "']";
	public static final String DISPLAY_NAME_TEXTBOX = "//input[@ng-model='selectedRoom.customDisplayName']";
	public static final String CODE_TEXTBOX = "//input[@ng-model='selectedRoom.code']";
	public static final String CAPACITY_TEXTBOX = "//input[@ng-model='selectedRoom.capacity']";
	
	public static final String OPEN_LOCATION_LIST_BUTTON = "//label[text()='Location']/following::div[1]//button";
	public static final String DISPLAY_LOCATIONS_BUTTON = "//label[text()='Location']/following::div[1]/following::div[1]/div/treeview/div[1]/div[1]/span";
	public static final String LOCATION_NAME_LIST = "//label[text()='Location']/following::div[1]/following::div[1]/div/treeview//transclude/div[1]";
	public static final String LOCATION_TEXTBOX = "//label[text()='Location']/following::div[1]/div[1]";
	public static final String SAVE_BUTTON = "//button/span[text()='Save']";
	public static final String CANCEL_BUTTON = "//button/span[text()='Cancel']";
	
}
