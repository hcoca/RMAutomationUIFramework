package org.fundacionjala.automation.framework.maps.tablet.settings;

public class ConnectionMap {

	public static final String SERVICE_URL_TEXT_FIELD = "//input[@id='service-url-input'][@type='text'][@name='serviceURL']";
	public static final String SAVE_BUTTON = "//span[text()='Save']/parent::button[@class='clean item item-btn'][@ng-click='saveConnectionSettings()']";
	public static final String MESSAGE_STATUS = "//div[@ng-switch-default]";
}
