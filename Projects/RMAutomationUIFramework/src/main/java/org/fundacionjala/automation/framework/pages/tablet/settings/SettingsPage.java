package org.fundacionjala.automation.framework.pages.tablet.settings;

import org.fundacionjala.automation.framework.maps.tablet.settings.SettingsMap;
import org.fundacionjala.automation.framework.pages.tablet.navigation.TopMenu;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.LogManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SettingsPage {

    public TopMenu topMenu;

    @FindBy(xpath = SettingsMap.CONNECTION_BUTTON)
    WebElement connectionButton;
    @FindBy(xpath = SettingsMap.NAVIGATION_BUTTON)
    WebElement navigationButton;

    public SettingsPage() {
	topMenu = new TopMenu();
	PageFactory.initElements(BrowserManager.getDriver(), this);
    }

    public ConnectionPage clickOnConnectionButton() {
	(new WebDriverWait(BrowserManager.getDriver(), 30))
		.until(ExpectedConditions
			.elementToBeClickable(connectionButton));
	connectionButton.click();

	LogManager.info("Connection Button has been clicked");

	return new ConnectionPage();
    }

    public NavigationPage clickOnNavigationButton() {
	(new WebDriverWait(BrowserManager.getDriver(), 30))
		.until(ExpectedConditions
			.elementToBeClickable(navigationButton));
	navigationButton.click();

	LogManager.info("Navigation Button has been clicked");

	return new NavigationPage();
    }
}
