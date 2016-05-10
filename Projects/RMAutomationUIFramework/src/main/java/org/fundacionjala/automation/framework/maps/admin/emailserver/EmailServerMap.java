package org.fundacionjala.automation.framework.maps.admin.emailserver;

public class EmailServerMap {
	
	public static final String ADD_BUTTON = "//button[@ng-click='openModal()']";
	public static final String REMOVE_BUTTON = "//span[text()='Remove']/parent::button[@class='btn btn-clear']";
	public static final String EMAIL_SERVER_BUTTON = "//a[@ng-click='serverSelected($index)'][@ng-repeat='item in mailservers']";
	public static final String EDIT_BUTTON = "//span[text()='Edit']/parent::button[@class='btn btn-default'][@ng-click='edit()']";
	public static final String USERNAME_TEXT_FIELD = "//input[@id='credential-username'][@type='text']";
	public static final String PASSWORD_TEXT_FIELD = "//input[@id='credential-password'][@type='password']";
	public static final String ACCEPT_BUTTON = "//span[text()='Accept']/parent::button[@class='btn btn-primary'][@ng-click='save()']";
	public static final String DESCRIPTION_TEXT_FIELD = "//textarea[@id='mailserver-description'][@ng-model='currentSelected.description']";
	public static final String ERROR_MESSAGE = "//small[@ng-repeat='errorMessage in credentialErrors'][contains(text(),'please try with another')]";
	public static final String HOST_NAME = "//h4[@class='ng-binding']";
}
