package org.fundacionjala.automation.framework.maps.admin.emailserver;

public class AddEmailServerMap {
	public static final String TITLE = "//h3[text()='Add Server']";
	public static final String HOSTNAME_INPUT = "//input[@id='add-mailserver-hostname'][@ng-model='request.hostname']";
	public static final String USERNAME_INPUT = "//input[@id='add-mailserver-username'][@ng-model='request.username']";
	public static final String PASSWORD_INPUT = "//input[@id='add-mailserver-password'][@ng-model='request.password']";
	public static final String DESCRIPTION_INPUT = "//textarea[@id='add-mailserver-description']";
	public static final String SAVE_BUTTON = "//span[text()='Save']/parent::button";

}
