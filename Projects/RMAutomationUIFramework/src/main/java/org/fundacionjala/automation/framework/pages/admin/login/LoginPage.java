package org.fundacionjala.automation.framework.pages.admin.login;

import org.fundacionjala.automation.framework.maps.admin.login.LoginMap;
import org.fundacionjala.automation.framework.pages.admin.home.AdminPage;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.PropertiesReader;
import org.fundacionjala.automation.framework.utils.common.UIActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	@FindBy (xpath = LoginMap.USER_NAME_TEXT_FIELD) WebElement userNameTextField;
	@FindBy (xpath = LoginMap.PASSWORD_TEXT_FIELD) WebElement passwordTextField;
	@FindBy (xpath = LoginMap.SIGN_IN_BUTTON) WebElement sigInButton;
	
	public LoginPage() {
		BrowserManager.setUrl(PropertiesReader.getAdminURL());
		PageFactory.initElements(BrowserManager.getDriver(), this);
	}
	
	public LoginPage setUserName(String userName) {
		UIActions.waitFor(LoginMap.USER_NAME_TEXT_FIELD);
		UIActions.typeOn(userNameTextField, userName);
		
		return this;
	}
	
	public LoginPage setPassword(String password) {
		UIActions.waitFor(LoginMap.PASSWORD_TEXT_FIELD);
		UIActions.typeOn(passwordTextField, password);
		
		return this;
	}
	
	public AdminPage clickOnSigInButton()	{
		UIActions.waitFor(LoginMap.SIGN_IN_BUTTON);
		UIActions.clickAt(sigInButton);
		
		return new AdminPage();
	}
}
