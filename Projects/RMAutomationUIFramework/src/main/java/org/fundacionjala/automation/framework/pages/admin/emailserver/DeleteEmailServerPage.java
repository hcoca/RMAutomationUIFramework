package org.fundacionjala.automation.framework.pages.admin.emailserver;

import org.fundacionjala.automation.framework.maps.admin.emailserver.DeleteEmailServerMap;
import org.fundacionjala.automation.framework.maps.admin.emailserver.EmailServerMap;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.ExplicitWait;
import org.fundacionjala.automation.framework.utils.common.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This class manage actions in a float window when a Email server is deleted
 * 
 * @author Samuel Sahonero
 *
 */
public class DeleteEmailServerPage {

    @FindBy(xpath = DeleteEmailServerMap.YES_BUTTON)
    WebElement yesButton;
    @FindBy(xpath = DeleteEmailServerMap.NO_BUTTON)
    WebElement noButton;

    /**
     * Initialize elements of DeleteEmailServerPage with the current driver
     */
    public DeleteEmailServerPage() {
	PageFactory.initElements(BrowserManager.getDriver(), this);
    }

    /**
     * Click on Yes button in order to confirm the remove of an Email Server
     * @return EmailServerPage previous instance
     */
    public EmailServerPage clickOnYesButton() {

	    ExplicitWait.clickWhenReady(By.xpath(DeleteEmailServerMap.YES_BUTTON), 10);
	    ExplicitWait.getWhenVisible(By.xpath(EmailServerMap.ADD_BUTTON), 30);
		
		LogManager.info("Remove Email Server Yes Button has been clicked");
	
		return new EmailServerPage();
    }
    /**
     * Click on No button in order to cancel the remove of an Email Server
     * @return EmailServerPage previous instance
     */
    public EmailServerPage clickNoButton() {
	(new WebDriverWait(BrowserManager.getDriver(), 30))
		.until(ExpectedConditions.presenceOfElementLocated(By
			.xpath(DeleteEmailServerMap.NO_BUTTON)));
	noButton.click();
	(new WebDriverWait(BrowserManager.getDriver(), 30))
		.until(ExpectedConditions.invisibilityOfElementLocated(By
			.xpath(DeleteEmailServerMap.NO_BUTTON)));

	LogManager.info("Remove Email Server No Button has been clicked");

	return new EmailServerPage();
    }
}
