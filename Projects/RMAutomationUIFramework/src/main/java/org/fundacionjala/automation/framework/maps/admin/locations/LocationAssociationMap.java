package org.fundacionjala.automation.framework.maps.admin.locations;

public class LocationAssociationMap {
	public static final String ASSOCIATED_ROOMS_GRID = "//h4[text()='Associated']/following::div[@ng-show='toAssociateRoomItems.length']";
	public static final String AVAILABLE_ROOMS_GRID = "//h4[text()='Available']/following::div[1]";
	public static final String SAVE_BUTTON = "//span[text()='Save']/parent::button";
	public static final String ROOMS = "//div[text()='room']/following::button[1]";
	public static final String SEARCH_ROOM_INPUT = "//input[@placeholder='Search by Display Name']";
	public static final String ASSIGNED_BUTTON = "//span[text()='Assigned']";
	public static final String NON_ASSIGNED_BUTTON = "//label[@ng-click='collapseUnassociated()']/i";
	public static final String PLUS_BUTTON = "//i[@class='fa fa-plus']";
}
