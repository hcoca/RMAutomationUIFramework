package org.fundacionjala.automation.framework.pages.admin.locations;

import org.fundacionjala.automation.framework.maps.admin.locations.RemoveLocationMap;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.LogManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * This class represents an "Remove Location" page.
 * 
 * @author ArielYanarico
 *
 */
public class RemoveLocationPage {

    @FindBy(xpath = RemoveLocationMap.REMOVE_BUTTON)
    WebElement removeButton;

    /**
     * The constructor initializes web factory.
     */
    public RemoveLocationPage() {
	PageFactory.initElements(BrowserManager.getDriver(), this);
    }

    /**
     * Clicks on "(-)Remove" button.
     * @return a new "Location" page.
     */
    public LocationPage clickOnRemoveButton() {
	removeButton.click();
	LogManager.info("'-Remove'button has been clicked");
	return new LocationPage();
    }
}
