package org.fundacionjala.automation.framework.maps.admin.emailserver;

public class AddEmailServerMap {
	
	public static final String DOMAIN_SERVER_TEXT_FIELD = "//input[@id='add-mailserver-hostname'][@type='text']";
	public static final String USERNAME_TEXT_FIELD = "//input[@id='add-mailserver-username'][@type='text']";
	public static final String PASSWORD_TEXT_FIELD = "//input[@id='add-mailserver-password'][@type='password']";
	public static final String DESCRIPTION_TEXT_FIELD = "//textarea[@id='add-mailserver-description']";
	public static final String SAVE_BUTTON = "//span[text()='Save']/parent::button[@class='btn btn-primary'][@type='submit']";
	
	public static final String TITLE = "//h3[text()='Add Server']";
}
