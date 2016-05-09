package org.fundacionjala.automation.framework.pages.admin.login;

import org.fundacionjala.automation.framework.pages.admin.home.AdminPage;
import org.fundacionjala.automation.framework.pages.admin.navigation.LeftMenu;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.PropertiesReader;

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
				.refreshPage();
		}
		else{

			 BrowserManager.getDriver().navigate().refresh();
			 leftMenu.clickOnEmailServerButton();
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
