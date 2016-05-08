package org.fundacionjala.automation.framework.maps.admin.resource;

public class RemoveResourceMap {

	public static final String REMOVE_BUTTON = "//span[text()='Remove']/parent::button[@class='info']";
	public static final String CANCEL_BUTTON = "//span[text()='Cancel']/parent::button[@class='btn-clear'][@ng-click='cancel()']";
	public static final String CLOSE_BUTTON = "//span[@class='btn btn-xs pull-right'][@ng-click='cancel()']";
	public static final String NAME_ROOM = "//div[@ng-grid='gridResourceOptions']//div[@class='ngCanvas']//span[text()='roomName']";
	
}
