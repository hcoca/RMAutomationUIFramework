package org.fundacionjala.automation.framework.pages.admin.login;

import org.fundacionjala.automation.framework.maps.admin.login.LoginMap;
import org.fundacionjala.automation.framework.pages.admin.home.AdminPage;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.ExplicitWait;
import org.fundacionjala.automation.framework.utils.common.PropertiesReader;
import org.fundacionjala.automation.framework.utils.common.UIActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.deps.com.thoughtworks.xstream.security.ExplicitTypePermission;

public class LoginPage {

    @FindBy(xpath = LoginMap.USER_NAME_TEXT_FIELD)
    WebElement userNameTextField;
    @FindBy(xpath = LoginMap.PASSWORD_TEXT_FIELD)
    WebElement passwordTextField;
    @FindBy(xpath = LoginMap.SIGN_IN_BUTTON)
    WebElement sigInButton;
    
    /**
     * Open Admin page and Initialize elements with driver 
     */
    public LoginPage() {
	BrowserManager.setUrl(PropertiesReader.getAdminURL());
	PageFactory.initElements(BrowserManager.getDriver(), this);
    }
    
    /**
     * Set login username
     * @param userName
     * @return this current object 
     */
    public LoginPage setUserName(String userName) {
	UIActions.waitFor(LoginMap.USER_NAME_TEXT_FIELD);
	UIActions.typeOn(userNameTextField, userName);

	return this;
    }

    /**
     * Set login password
     * @param password
     * @return this current object
     */
    public LoginPage setPassword(String password) {
	UIActions.waitFor(LoginMap.PASSWORD_TEXT_FIELD);
	UIActions.typeOn(passwordTextField, password);

	return this;
    }

    /**
     * Press Sign In button
     * @return AdminPage 
     */
    public AdminPage clickOnSigInButton() {

	UIActions.clickAt(sigInButton);
	return new AdminPage();
    }
}
