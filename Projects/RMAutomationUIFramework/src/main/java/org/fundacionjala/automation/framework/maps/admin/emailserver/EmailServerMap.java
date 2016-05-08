package org.fundacionjala.automation.framework.maps.admin.emailserver;

public class EmailServerMap {
	public static final String ADD_BUTTON = "//span[text()='Add']/parent::button";
	public static final String REMOVE_BUTTON = "//span[text()='Remove']/parent::button";
	public static final String EMAILSERVER_ITEM = "//a[@ng-click='serverSelected($index)']";
	public static final String EDIT_BUTTON = "//span[text()='Edit']/parent::button";
	public static final String USERNAME_CRED_INPUT = "//input[@id='credential-username']";
	public static final String PASSWORD_CRED_INPUT = "//input[@id='credential-password']";
	public static final String ACCEPT_BUTTON = "//span[text()='Accept']/parent::button";
	public static final String END_PROCESSING = "//input[@id='credential-password']/parent::div[contains(@class,'hide')]";
	public static final String ERROR_MSG_CREDENTIAL = "//small[contains(text(),'please try with another')]";
}
