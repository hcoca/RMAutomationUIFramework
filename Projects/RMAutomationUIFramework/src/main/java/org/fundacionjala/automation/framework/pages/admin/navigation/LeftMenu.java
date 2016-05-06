package org.fundacionjala.automation.framework.pages.admin.navigation;

import org.fundacionjala.automation.framework.maps.admin.navigation.LeftMenuMap;
import org.fundacionjala.automation.framework.pages.admin.emailserver.EmailServerPage;
import org.fundacionjala.automation.framework.pages.admin.resource.ResourcePage;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.LogManager;
import org.fundacionjala.automation.framework.utils.common.UIActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LeftMenu {
	@FindBy (xpath = LeftMenuMap.EMAILSERVER_BUTTON) WebElement emailServerButton;
	@FindBy (xpath = LeftMenuMap.RESOURCES_BUTTON) WebElement resourcesButton;
	@FindBy (xpath = LeftMenuMap.ISSUES_BUTTON) WebElement issuesButton;
	public LeftMenu() {
		PageFactory.initElements(BrowserManager.getDriver(), this);
	}
	
	public ResourcePage clickOnResourcesButton() {
		LogManager.info("Click on resource menu");
		UIActions.waitFor(LeftMenuMap.RESOURCES_BUTTON);
		UIActions.clickAt(resourcesButton);
		
		return new ResourcePage();
	}

	public LeftMenu clickOnIssuesButton() {
		UIActions.waitFor(LeftMenuMap.ISSUES_BUTTON);
		UIActions.clickAt(issuesButton);
		return this;
	}

	public EmailServerPage clickOnEmailServerButton() {
		LogManager.info("Click on Email Server menu");
		UIActions.waitFor(LeftMenuMap.EMAILSERVER_BUTTON);
		UIActions.clickAt(emailServerButton);
		return new EmailServerPage();
	}
}
