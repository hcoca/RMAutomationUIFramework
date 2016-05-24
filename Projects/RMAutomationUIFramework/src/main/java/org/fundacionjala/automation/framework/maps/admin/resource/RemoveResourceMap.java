package org.fundacionjala.automation.framework.maps.admin.resource;

public class RemoveResourceMap {

	public static final String REMOVE_BUTTON = "//span[text()='Remove']/parent::button[@class='info']";
	public static final String CANCEL_BUTTON = "//span[text()='Cancel']/parent::button[@class='btn-clear'][@ng-click='cancel()']";
	public static final String CLOSE_BUTTON = "//span[@class='btn btn-xs pull-right'][@ng-click='cancel()']";
	public static final String NAME_ROOM = "//div[@ng-grid='gridResourceOptions']//div[@class='ngCanvas']//span[text()='roomName']";
	public static final String ROOM_COLUMN = "//div[@class='ngCellText ng-scope col0 colt0']/span[text()='roomName']";
	public static final String QTY_COLUMN = "//div[@class='ngCellText ng-scope col0 colt0']/span[text()='roomName']/ancestor::div[3]/following::div[1]/div[2]/div";
	public static final String RESOURCE_NAME = "html/body/div[4]/div/div/div[2]/div/div/div[1]/div[1]/h3[text()='resourceName']";
	public static final String ICON_NAME = "html/body/div[4]/div/div/div[2]/div/div/div[1]/div[2]/div/span[@class='iconName']";
}
