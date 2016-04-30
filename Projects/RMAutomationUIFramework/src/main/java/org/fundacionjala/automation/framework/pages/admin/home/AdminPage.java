package org.fundacionjala.automation.framework.pages.admin.home;

import org.fundacionjala.automation.framework.pages.admin.navigation.LeftMenu;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;

public class AdminPage {
	
	public LeftMenu leftMenu;
	
	public AdminPage() {
		leftMenu = new LeftMenu();
	}
	
	public AdminPage refreshPage(){
		BrowserManager.getDriver().navigate().refresh();
		return this;
	}
	
}
