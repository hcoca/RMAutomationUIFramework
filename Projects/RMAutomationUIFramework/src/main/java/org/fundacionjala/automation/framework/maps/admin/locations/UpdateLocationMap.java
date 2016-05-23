package org.fundacionjala.automation.framework.maps.admin.locations;

public class UpdateLocationMap {
	public static final String SAVE_BUTTON = "//span[text()='Save']/parent::button";
	public static final String CANCEL_BUTTON = "//span[text()='Cancel']/parent::button";
	public static final String LOCATION_NAME_FIELD = "//input[@id='location-add-name']";
	public static final String LOCATION_DISPLAY_NAME_FIELD = "//input[@id='location-add-display-name']";
	public static final String LOCATION_DESCRIPTION_AREA = "//textarea[@id='location-add-description']";
	public static final String ADD_PARENT_LOCATION_BUTTON = "//div[@id='location-add-parent-location']/following::button[1]";
	public static final String PARENT_LOCATION_FIELD = "//div[@id='location-add-parent-location']";
	public static final String LOCATION_ASSOCIATION_LINK = "//a[text()='Location Associations']";
}
