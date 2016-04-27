package org.fundacionjala.automation.pages.login;

import org.fundacionjala.automation.maps.LoginMap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Login {
	public WebElement get_UsernameField(WebDriver driver){
		return driver.findElement(By.id(LoginMap.usernameField));
		}
 
	public WebElement get_PasswordField(WebDriver driver){
		return driver.findElement(By.id(LoginMap.passwordField));
		}
 
	public WebElement get_SignInButton(WebDriver driver){
		return driver.findElement(By.xpath(LoginMap.signInButton));
		}
}
