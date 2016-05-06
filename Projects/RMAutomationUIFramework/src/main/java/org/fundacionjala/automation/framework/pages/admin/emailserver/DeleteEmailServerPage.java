package org.fundacionjala.automation.framework.pages.admin.emailserver;

import org.fundacionjala.automation.framework.maps.admin.emailserver.DeleteEmailServerMap;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.LogManager;
import org.fundacionjala.automation.framework.utils.common.UIActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DeleteEmailServerPage {
	
	@FindBy (xpath = DeleteEmailServerMap.YES_BUTTON) WebElement yes_button;
	public DeleteEmailServerPage()
	{
		PageFactory.initElements(BrowserManager.getDriver(), this);
	}
	public EmailServerPage clickOnYesButton() {
		UIActions.waitFor(DeleteEmailServerMap.YES_BUTTON);
		yes_button.click();
		LogManager.info("Click on Yes button");
		return new EmailServerPage();
	}

}
