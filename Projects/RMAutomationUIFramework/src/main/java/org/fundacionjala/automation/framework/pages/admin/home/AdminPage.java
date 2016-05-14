package org.fundacionjala.automation.framework.pages.admin.home;

import org.fundacionjala.automation.framework.maps.admin.home.AdminMap;
import org.fundacionjala.automation.framework.pages.admin.navigation.LeftMenu;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.ExplicitWait;
import org.openqa.selenium.By;

public class AdminPage {

    public LeftMenu leftMenu;
    /**
     * Initialize Left Menu
     */
    public AdminPage() {
	leftMenu = new LeftMenu();
    }
    /**
     * Refresh browser driver
     * @return AdminPage home
     */
    public AdminPage refreshPage() {
	
	ExplicitWait.getWhenVisible(By.xpath(AdminMap.HOME_TITLE), 60);
	BrowserManager.getDriver().navigate().refresh();
	return this;
    }

}
