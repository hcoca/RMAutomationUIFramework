package org.fundacionjala.automation.framework.maps.admin.resource;

public class AddResourceMap {

	public static final String NAME_TEXT_FIELD= "//resource-card//form//input[@ng-model='resource.name']";
	public static final String DISPLAY_NAME_TEXT_FIELD = "//resource-card//form//input[@ng-model='resource.customName']";
	public static final String DESCRIPTION_TEXT_FIELD = "//resource-card//form//textarea";
	public static final String SAVE_BUTTON = "//button[@class='info']";
	public static final String CANCEL_BUTTON = "//button[@class='btn-clear']";
	public static final String ICON_BUTTON = "//button[@id='convert']";
	public static final String ICON = "//table[@class='table-icons']/tbody//button[@value='iconName']";
	public static final String RIGHT_BUTTON = "//table[@class='table-icons']/thead//button[@value='1']";
	public static final String LEFT_BUTTON = "//table[@class='table-icons']/thead//button[@value='-1']";
	
}
