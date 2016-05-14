package org.fundacionjala.automation.framework.pages.admin.navigation;

import org.fundacionjala.automation.framework.maps.admin.conferencerooms.ConferenceRoomsMap;
import org.fundacionjala.automation.framework.maps.admin.navigation.LeftMenuMap;
import org.fundacionjala.automation.framework.pages.admin.emailserver.EmailServerPage;
import org.fundacionjala.automation.framework.pages.admin.impersonation.ImpersonationPage;
import org.fundacionjala.automation.framework.pages.admin.conferencerooms.ConferenceRoomsPage;
import org.fundacionjala.automation.framework.pages.admin.locations.LocationPage;
import org.fundacionjala.automation.framework.pages.admin.resource.ResourcePage;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.ExplicitWait;
import org.fundacionjala.automation.framework.utils.common.LogManager;
import org.fundacionjala.automation.framework.utils.common.UIActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LeftMenu {
	@FindBy (xpath = LeftMenuMap.EMAILSERVER_BUTTON) WebElement emailServerButton;
	@FindBy (xpath = LeftMenuMap.RESOURCES_BUTTON) WebElement resourcesButton;
	@FindBy (xpath = LeftMenuMap.ISSUES_BUTTON) WebElement issuesButton;
	@FindBy (xpath = LeftMenuMap.IMPERSONATION_BUTTON) WebElement impersonationButton;
	@FindBy (linkText = LeftMenuMap.CONFERENCE_ROOMS_BUTTON) WebElement conferenceRoomsButton;
	@FindBy (xpath = LeftMenuMap.LOCATIONS_BUTTON) WebElement locationsButton;
	
	
	public LeftMenu() {
		PageFactory.initElements(BrowserManager.getDriver(), this);
	}
	
	public ResourcePage clickOnResourcesButton() {
		LogManager.info("Click on resource menu");
		UIActions.waitFor(LeftMenuMap.RESOURCES_BUTTON);
		UIActions.clickAt(resourcesButton);
		
		return new ResourcePage();
	}
	
	public ConferenceRoomsPage clickOnConferenceRoomsButton() throws InterruptedException{
		LogManager.info("Click on conference rooms page");
		boolean existAtLeasARoom = false;
		do{
		    clickOnEmailServerButton();
		    UIActions.clickAt(conferenceRoomsButton);
		    if(ExplicitWait.waitForElement(ConferenceRoomsMap.FIRST_ROW, 5)){
			existAtLeasARoom = true;
		    }
		}while(existAtLeasARoom == false);
		return new ConferenceRoomsPage();
	}
	
	public LocationPage clickOnLocationsButton() {
		UIActions.waitFor(LeftMenuMap.LOCATIONS_BUTTON);
		UIActions.clickAt(locationsButton);
		return new LocationPage();
	}

	public LeftMenu clickOnIssuesButton() {
		UIActions.waitFor(LeftMenuMap.ISSUES_BUTTON,60);
		issuesButton.click();
		return this;
	}
	
	public ImpersonationPage clickOnImpersonationButton() {
		(new WebDriverWait(BrowserManager.getDriver(), 30)).until(ExpectedConditions.visibilityOf(impersonationButton));
		impersonationButton.click();
		LogManager.info("Impersonation Button has been clicked");
		return new ImpersonationPage();
	}
	
	public EmailServerPage clickOnEmailServerButton() {
		LogManager.info("Click on Email Server menu");
		UIActions.waitFor(LeftMenuMap.EMAILSERVER_BUTTON);
		emailServerButton.click();
		return new EmailServerPage();
	}
}
