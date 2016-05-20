package org.fundacionjala.automation.framework.pages.admin.login;
import org.fundacionjala.automation.framework.pages.admin.home.AdminPage;
import org.fundacionjala.automation.framework.pages.admin.navigation.LeftMenu;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.PropertiesReader;

public class LoginActions {
	
	
	public static AdminPage ExecuteLogin() throws InterruptedException
	{
		LeftMenu leftMenu = new LeftMenu();
		if(!driverExists()){
			
			BrowserManager.openBrowser();
			insertCredentials();
		}
		
		else if(isInLogginPage()) {
			
			insertCredentials();
		}
		
		else if(BrowserManager.getDriver().getCurrentUrl().contains("tablet")){
			 
			BrowserManager.openBrowser();
			insertCredentials();
		}
		else {
			 BrowserManager.getDriver().navigate().refresh();	
			 leftMenu.clickOnEmailServerButton();
		}
		
		return new AdminPage();
	}

	private static boolean isInLogginPage() {
		
		String currentUrl = BrowserManager.getDriver().getCurrentUrl();
		String expectedUrl = PropertiesReader.getAdminURL();
		
		return ((currentUrl == expectedUrl)? true : false);
	}

	private static boolean driverExists() {
		
		return ((BrowserManager.getDriver() == null)? false : true);
	}
	
	private static void insertCredentials() {

		LoginPage login = new LoginPage();
		login
			.setUserName(PropertiesReader.getUserName())
			.setPassword(PropertiesReader.getPassword())
			.clickOnSigInButton()
			.leftMenu
			.clickOnEmailServerButton();
	}
	
}
