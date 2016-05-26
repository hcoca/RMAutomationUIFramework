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
	public static final String ICON_LABEL = "//span[text()='Icon:']";
	public static final String NAME_LABEL = "//form/label[text()='Name']";
	public static final String DISPLAY_NAME_LABEL = "//form/label[text()='Display Name']";
	public static final String DESCRIPTION_LABEL = "//form/label[text()='Description (optional)']";
	public static final String NAME_EMPTY_ERROR = "//form/small[text()='Name must not be empty']";
	public static final String DISPLAY_NAME_EMPTY_ERROR = "//form/small[contains(text(),'Display name must not be empty')]";
	public static final String ALREADY_EXISTS_ERROR = "//form/small[contains(text(),'already exists')]";
	public static final String INVALID_CHARACTERS_ERROR = "//form/small[contains(text(),'Invalid name')]";
	public static final String CLOSE_BUTTON = "//i[@class='fa fa-times']/parent::span[@class='btn btn-xs pull-right'][@ng-click='cancel()']";
	public static final String ADD_RESOURCE_WINDOW = "//div[@class='modal-content'][@modal-transclude='']";
	public static final String ICON_BOX = "//resource-card//span[@class='fa fa iconName']";
}
