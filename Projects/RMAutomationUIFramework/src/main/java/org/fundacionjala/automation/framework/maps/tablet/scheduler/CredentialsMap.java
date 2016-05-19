package org.fundacionjala.automation.framework.maps.tablet.scheduler;

public class CredentialsMap {

	public static final String CREATE_IN_BEHALF_OF_TEXT_FIELD = "//h4[contains(text(),'Create in behalf of:')]/parent::div/descendant::input[@type='text'][@placeholder='username']";
	public static final String USER_NAME_TEXT_FIELD = "//label[text()='Username']/parent::div/descendant::input[@type='text'][@placeholder='username']";
	public static final String PASSWORD_TEXT_FIELD = "//input[@type='password'][@ng-model='dialog.credentials.password'][@placeholder='password']";
	public static final String OK_BUTTON = "//button[@ng-click='dialog.ok()'][@type='button']";
	public static final String CANCEL_BUTTON = "//button[@ng-click='dialog.modalDismiss()'][@type='button']";
	public static final String CREATE_AS_CHECKBOX = "//span[@class='checkbox-label ng-binding'][contains(text(),'Create as')]";
	public static final String CANCEL_AS_CHECKBOX = "//span[@class='checkbox-label ng-binding'][contains(text(),'Cancel as')]";
	public static final String CANCEL_IN_BEHALF_OF_MESSAGE = "//h4[@class='ng-binding'][contains(text(),'Cancel in behalf of')]";
	public static final String NO_FUNCTIONALITY_PROVIDED_MESSAGE = "//h3[text()='No functionality provided yet']";
}
