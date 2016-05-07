package org.fundacionjala.automation.framework.maps.admin.conferencerooms;

public class ConferenceRoomsMap {

	public static final String ENABLED_ROOMS_ROWS = "//span[@class='text-glow-green fa fa-power-off']";
	public static final String DISABLED_ROOMS_ROWS = "//span[@class='text-glow-gray fa fa-power-off']";
	public static final String OUT_OF_ORDER_ICONS= "//span[text()='roomName']/preceding::span[ @class='fa fa-calendar ng-scope text-glow-gray']";
	public static final String ROOMS_COLUMN = "//div[@class='ngCanvas']/*/div[3]//span[2]";
	
	
	public static final String RESOURCE_BUTTONS = "//div[@ng-repeat='resource in resources']/span/span";
}
