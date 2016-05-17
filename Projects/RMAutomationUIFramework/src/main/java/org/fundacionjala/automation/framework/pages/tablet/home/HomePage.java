package org.fundacionjala.automation.framework.pages.tablet.home;

import java.util.List;

import org.fundacionjala.automation.framework.maps.tablet.home.HomeMap;
import org.fundacionjala.automation.framework.pages.tablet.scheduler.SchedulerPage;
import org.fundacionjala.automation.framework.pages.tablet.search.SearchPage;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.ExplicitWait;
import org.fundacionjala.automation.framework.utils.common.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This class represents a Room Manager Home Page.
 * @author ArielYanarico
 *
 */
public class HomePage {

    @FindBy(xpath = HomeMap.SCHEDULE_BUTTON)
    WebElement scheduleButton;
    @FindBy(xpath = HomeMap.HOME_TIME_LINE)
    WebElement homeTimeLine;

    /**
     * The constructor initializes all Web Elements.
     */
    public HomePage() {
	PageFactory.initElements(BrowserManager.getDriver(), this);
    }

    /**
     * This method is to click on "Schedule Button".
     * @return a new "Scheduler" page.
     */
    public SchedulerPage clickOnScheduleButton() {
	(new WebDriverWait(BrowserManager.getDriver(), 30))
		.until(ExpectedConditions.elementToBeClickable(scheduleButton));
	scheduleButton.click();

	LogManager.info("Schedule Button has been clicked");

	return new SchedulerPage();
    }

    /**
     * This method is to verify if Meetings are displayed on home page
     * @param numberOfMeetings
     * @return true if meetings are displayed else false
     */
    public boolean verifyTimeLineMeetings(long numberOfMeetings) {
	ExplicitWait.waitElementVisible(homeTimeLine, 30);

	List<WebElement> meetings = homeTimeLine.findElements(By
		.xpath(HomeMap.HOME_TIME_LINE_MEETINGS));

	LogManager.info("There are: " + numberOfMeetings + " meetings and "
		+ meetings.size() + " are displayed");

	return meetings.size() == numberOfMeetings;
    }

    public boolean verifyCurrentMeetingDisplayed(String subject) {
	try {
	    BrowserManager.getDriver().findElement(
		    By.xpath("//div[@ng-bind='current._title' and text()='"
			    + subject + "']"));
	    LogManager.info("Test Passed: " + subject + " has been displayed");
	    return true;
	} catch (NoSuchElementException e) {
	    LogManager.warning("Test Failed");
	    LogManager.error("Element not found (Exception)");
	    return false;
	}
    }

    public SearchPage clickOnSearchButton() {

	ExplicitWait.clickWhenReady(By.cssSelector(HomeMap.SEARCH_BUTTON), 20);
	return new SearchPage();
    }
}
