package org.fundacionjala.automation.framework.maps.admin.locations;

public class LocationAssociationMap {
	public static final String ASSOCIATED_ROOMS_GRID = "//h4[text()='Associated']/following::div[@ng-show='toAssociateRoomItems.length']";
	public static final String AVAILABLE_ROOMS_GRID = "//h4[text()='Available']/following::div[1]";
	public static final String SAVE_BUTTON = "//span[text()='Save']/parent::button";
}
