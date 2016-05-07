package org.fundacionjala.automation.framework.pages.tablet.navigation;

import org.fundacionjala.automation.framework.maps.tablet.navigation.TopMenuMap;
import org.fundacionjala.automation.framework.pages.tablet.home.HomePage;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.LogManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TopMenu {

	@FindBy (xpath = TopMenuMap.HOME_BUTTON) WebElement homeButton;
	
	public TopMenu() {
		PageFactory.initElements(BrowserManager.getDriver(), this);
	}
	
	public HomePage clickOnHomeButton() {
		(new WebDriverWait(BrowserManager.getDriver(), 30)).until(ExpectedConditions.elementToBeClickable(homeButton));
		homeButton.click();
		
		LogManager.info("Home Button has been clicked");
		
		return new HomePage();
	}
}
