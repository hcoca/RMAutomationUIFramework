package org.fundacionjala.automation.framework.pages.admin.login;

import org.fundacionjala.automation.framework.maps.admin.login.LoginMap;
import org.fundacionjala.automation.framework.pages.admin.home.AdminPage;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.UIActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	@FindBy (xpath = LoginMap.SIGN_IN_BUTTON) WebElement sigInButton;
	
	public LoginPage() {
		BrowserManager.setUrl("http://172.20.208.84:4040/admin/#/login");
		PageFactory.initElements(BrowserManager.getDriver(), this);
	}
	
	public AdminPage clickSigInButton()	{
		UIActions.waitFor(LoginMap.SIGN_IN_BUTTON);
		UIActions.clickAt(sigInButton);
		
		return new AdminPage();
	}
}
