package org.fundacionjala.automation.framework.pages.tablet.settings;

import org.fundacionjala.automation.framework.maps.tablet.settings.ConnectionMap;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.LogManager;
import org.fundacionjala.automation.framework.utils.common.PropertiesReader;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ConnectionPage extends SettingsPage {

    @FindBy(xpath = ConnectionMap.SERVICE_URL_TEXT_FIELD)
    WebElement serviceURLTextField;
    @FindBy(xpath = ConnectionMap.SAVE_BUTTON)
    WebElement saveButton;

    public ConnectionPage() {
	BrowserManager.setUrl(PropertiesReader.getTabletURL());
	PageFactory.initElements(BrowserManager.getDriver(), this);
    }

    public ConnectionPage setUpServiceURL(String serviceURL) {
	(new WebDriverWait(BrowserManager.getDriver(), 30))
		.until(ExpectedConditions.visibilityOf(serviceURLTextField));
	serviceURLTextField.clear();
	serviceURLTextField.sendKeys(serviceURL);

	LogManager.info("Service URL " + serviceURL + " has been set up");

	return this;
    }

    public ConnectionPage clickOnSaveButton() {
	(new WebDriverWait(BrowserManager.getDriver(), 30))
		.until(ExpectedConditions.elementToBeClickable(saveButton));
	saveButton.click();

	LogManager.info("Save Button has been clicked");

	return this;
    }
}
