package org.fundacionjala.automation.actions;

import org.fundacionjala.automation.pages.login.Login;
import org.openqa.selenium.WebDriver;

public class LoginAction {
	static Login loginPage = new Login();

	public static void input_Username(WebDriver driver, String userName){
		loginPage.get_UsernameField(driver).clear();
		loginPage.get_UsernameField(driver).sendKeys(userName);
		}
 
	public static void input_Password(WebDriver driver, String password){ 
		loginPage.get_PasswordField(driver).clear();
		loginPage.get_PasswordField(driver).sendKeys(password);
		}
 
	public static void click_Login(WebDriver driver){
		loginPage.get_SignInButton(driver).click();
		}
}
