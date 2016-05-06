package org.fundacionjala.automation.framework.pages.tablet.home;

import org.fundacionjala.automation.framework.maps.tablet.home.HomeMap;
import org.fundacionjala.automation.framework.pages.tablet.scheduler.SchedulerPage;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.LogManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

	@FindBy (xpath = HomeMap.SCHEDULE_BUTTON) WebElement scheduleButton;
	
	public HomePage() {
		PageFactory.initElements(BrowserManager.getDriver(), this);
	}
	
	public SchedulerPage clickOnScheduleButton() {
		(new WebDriverWait(BrowserManager.getDriver(), 30)).until(ExpectedConditions.elementToBeClickable(scheduleButton));
		scheduleButton.click();
		
		LogManager.info("Schedule Button has been clicked");
		
		return new SchedulerPage();
	}
}
