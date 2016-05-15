package org.fundacionjala.automation.framework.pages.admin.login;

import org.fundacionjala.automation.framework.maps.admin.emailserver.EmailServerMap;
import org.fundacionjala.automation.framework.pages.admin.home.AdminPage;
import org.fundacionjala.automation.framework.pages.admin.navigation.LeftMenu;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.ExplicitWait;
import org.fundacionjala.automation.framework.utils.common.PropertiesReader;
import org.openqa.selenium.By;

public class LoginActions {
	
	public static AdminPage ExecuteLogin() throws InterruptedException
	{
		LoginPage login;
		LeftMenu leftMenu = new LeftMenu();
		
		if(!isLogged()){

			BrowserManager.openBrowser();
			login = new LoginPage();
			
			   login
				.setUserName(PropertiesReader.getUserName())
				.setPassword(PropertiesReader.getPassword())
				.clickOnSigInButton()
				.leftMenu
				.clickOnEmailServerButton();
			   ExplicitWait.getElementsWhenVisible(By.xpath(EmailServerMap.EMAIL_SERVER_BUTTON), 15);
			   ExplicitWait.getElementsWhenVisible(By.xpath(EmailServerMap.REMOVE_BUTTON), 15);
		}
		else{
			BrowserManager.getDriver().navigate().refresh();	
			 leftMenu.clickOnEmailServerButton();
			 ExplicitWait.getElementsWhenVisible(By.xpath(EmailServerMap.EMAIL_SERVER_BUTTON), 15);
			 ExplicitWait.getElementsWhenVisible(By.xpath(EmailServerMap.REMOVE_BUTTON), 15);
		}
		
		return new AdminPage();
	}

	private static boolean isLogged() {
		
		if(BrowserManager.getDriver() != null)
		{
			return true;
		}
		
		return false;
	}
}
