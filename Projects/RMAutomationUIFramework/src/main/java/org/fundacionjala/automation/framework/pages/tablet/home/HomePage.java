package org.fundacionjala.automation.framework.pages.tablet.home;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;

import org.fundacionjala.automation.framework.maps.tablet.home.HomeMap;
import org.fundacionjala.automation.framework.pages.tablet.scheduler.SchedulerPage;
import org.fundacionjala.automation.framework.pages.tablet.search.SearchPage;
import org.fundacionjala.automation.framework.pages.tablet.settings.SettingsPage;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.ExplicitWait;
import org.fundacionjala.automation.framework.utils.common.LogManager;
import org.fundacionjala.automation.framework.utils.common.PropertiesReader;
import org.fundacionjala.automation.framework.utils.common.UIActions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
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
    @FindBy(xpath = HomeMap.SETTINGS_BUTTON)
    WebElement settingsButton;
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
     * this method expand the time line in order to display all day on time line 
     * @return SchedulerPage instance
     * @throws AWTException
     */
    public HomePage displayAllDayOnTimeline() throws AWTException {
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	double y = screenSize.getHeight();
	ExplicitWait.waitForElement(HomeMap.HOME_TIME_LINE, 30);
	UIActions
	.scrollTimelineWithCoordinates(200,y-100, 5000);  
	return this;	
    }

    public SettingsPage clickOnSettinsButton() {
	(new WebDriverWait(BrowserManager.getDriver(), 30))
		.until(ExpectedConditions.elementToBeClickable(settingsButton));
	settingsButton.click();

	LogManager.info("Settings Button has been clicked");

	return new SettingsPage();
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

    public boolean verifyTimeLeft() {
	ExplicitWait.waitForUrl(PropertiesReader.getServiceURL()
		+ "/tablet/#/home", 15);
	BrowserManager.getDriver().navigate().refresh();
	boolean verification = false;
	WebElement currentTime = ExplicitWait.getWhenVisible(
		By.xpath(HomeMap.CURRENT_TIME), 60);
	WebElement leftTime = ExplicitWait.getWhenVisible(
		By.xpath(HomeMap.LEFT_TIME), 60);
	if (Integer.parseInt(currentTime.getText().replace(":", ""))
		+ Integer.parseInt(leftTime.getText().replace(":", "")) == 2359) {
	    verification = true;
	}
	return verification;
    }

    public boolean verifyCurrentOrganizerDisplayed(String organizer) {
	try {
	    BrowserManager.getDriver().findElement(
		    By.xpath("//div[@ng-bind='current._organizer' and text()='"
			    + organizer + "']"));
	    LogManager
		    .info("Test Passed: " + organizer + " has been displayed");
	    return true;
	} catch (NoSuchElementException e) {
	    LogManager.warning("Test Failed");
	    LogManager.error("Element not found (Exception)");
	    return false;
	}
    }

    public boolean verifyNextMeetingDisplayed(String subject) {
	try {
	    BrowserManager.getDriver().findElement(
		    By.xpath("//div[@ng-bind='next._title' and text()='"
			    + subject + "']"));
	    LogManager.info("Test Passed: " + subject + " has been displayed");
	    return true;
	} catch (NoSuchElementException e) {
	    LogManager.warning("Test Failed");
	    LogManager.error("Element not found (Exception)");
	    return false;
	}
    }

    public boolean verifyNextOrganizerDisplayed(String organizer) {
	try {
	    BrowserManager.getDriver().findElement(
		    By.xpath("//div[@ng-bind='next._organizer' and text()='"
			    + organizer + "']"));
	    LogManager
		    .info("Test Passed: " + organizer + " has been displayed");
	    return true;
	} catch (NoSuchElementException e) {
	    LogManager.warning("Test Failed");
	    LogManager.error("Element not found (Exception)");
	    return false;
	}
    }

    public boolean verifyRoomNameDisplayed(String roomName) {
	try {
	    BrowserManager
		    .getDriver()
		    .findElement(
			    By.xpath("//span[@ng-bind='room.customDisplayName' and text()='"
				    + roomName + "']"));
	    LogManager.info("Test Passed: " + roomName + " has been displayed");
	    return true;
	} catch (NoSuchElementException e) {
	    LogManager.warning("Test Failed");
	    LogManager.error("Element not found (Exception)");
	    return false;
	}
    }

    public boolean verifyTimeLeftOfCurrentMeeting() {
	ExplicitWait.waitForUrl(PropertiesReader.getServiceURL()
		+ "/tablet/#/home", 15);
	BrowserManager.getDriver().navigate().refresh();
	boolean verification = false;
	WebElement currentTime = ExplicitWait.getWhenVisible(
		By.xpath(HomeMap.CURRENT_TIME), 60);
	WebElement leftTime = ExplicitWait.getWhenVisible(
		By.xpath(HomeMap.LEFT_TIME), 60);
	WebElement endTime = ExplicitWait.getWhenVisible(
		By.xpath(HomeMap.TIME_OUT_OF_ORDER), 60);
	int result = (Integer.parseInt(endTime.getText().replace("-0:00", "")
		.replace(":", "")) - Integer.parseInt(currentTime.getText()
		.replace(":", ""))) - 1;
	if (result >= 60) {
	    result = result - 40;
	}
	if (result == Integer.parseInt(leftTime.getText().replace(":", ""))) {
	    verification = true;
	}
	return verification;
    }

    public boolean verifyTimeLeftOfNextMeeting() {
	ExplicitWait.waitForUrl(PropertiesReader.getServiceURL()
		+ "/tablet/#/home", 15);
	BrowserManager.getDriver().navigate().refresh();
	boolean verification = false;
	WebElement currentTime = ExplicitWait.getWhenVisible(
		By.xpath(HomeMap.CURRENT_TIME), 60);
	WebElement leftTime = ExplicitWait.getWhenVisible(
		By.xpath(HomeMap.LEFT_TIME), 60);
	WebElement startTimeNextMeeting = ExplicitWait.getWhenVisible(
		By.xpath(HomeMap.TIME_OUT_OF_ORDER), 60);
	int result = (Integer.parseInt(startTimeNextMeeting.getText()
		.substring(0, 5).replace(":", "")) - Integer
		.parseInt(currentTime.getText().replace(":", ""))) - 41;
	if (result == Integer.parseInt(leftTime.getText().replace(":", ""))) {
	    verification = true;
	}
	return verification;
    }

    public boolean verifyMeetingInTimeLine(String subject) {
	try {
	    (new WebDriverWait(BrowserManager.getDriver(), 5))
		    .until(ExpectedConditions.presenceOfElementLocated(By
			    .xpath("//span[@class='vis-item-content' and text()='"
				    + subject + "']")));
	    LogManager.warning("Test Failed: The meeting is displayed");
	    return false;
	} catch (TimeoutException e) {
	    LogManager.info("Test Passed: " + subject
		    + " has not been displayed");
	    return true;
	}
    }

}
