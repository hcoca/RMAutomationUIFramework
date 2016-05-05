package org.fundacionjala.automation.framework.maps.admin.impersonation;

public class ImpersonationMap {

	public static final String ACCOUNT_TEXT_FIELD = "//input[@type='input'][@ng-disabled='true'][@ng-model='mailservers[0].credential.username']";
	public static final String USE_IMPERSONATION_CHECK_BOX = "//input[@type='checkbox'][@ng-model='impersonationConfig.impersonate']";
	public static final String USER_AND_PASSWORD_RADIO_BUTTON = "//input[@type='radio'][@value='credentials'][@ng-model='impersonationConfig.authentication']";
	public static final String RFID_RADIO_BUTTON = "//input[@type='radio'][@value='rfid'][@ng-model='impersonationConfig.authentication']";
	public static final String SAVE_BUTTON = "//span[text()='Save']/parent::button[@class='info pull-right'][@ng-click='setImpersonation()']";
}
