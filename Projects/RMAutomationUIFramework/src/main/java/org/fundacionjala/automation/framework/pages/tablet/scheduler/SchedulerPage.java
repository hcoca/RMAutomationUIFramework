package org.fundacionjala.automation.framework.pages.tablet.scheduler;

import java.util.List;

import org.fundacionjala.automation.framework.maps.tablet.scheduler.SchedulerMap;
import org.fundacionjala.automation.framework.pages.tablet.navigation.TopMenu;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.ExplicitWait;
import org.fundacionjala.automation.framework.utils.common.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author SamuelSahonero
 *
 */
public class SchedulerPage {
    
    public TopMenu topMenu;

    @FindBy(xpath = SchedulerMap.ORGANIZER_TEXT_FIELD)
    WebElement organizerTextField;
    @FindBy(xpath = SchedulerMap.SUBJECT_TEXT_FIELD)
    WebElement subjectTextField;
    @FindBy(xpath = SchedulerMap.ATTENDEES_TEXT_FIELD)
    WebElement attendeesTextField;
    @FindBy(xpath = SchedulerMap.CREATE_BUTTON)
    WebElement createButton;
    @FindBy(xpath = SchedulerMap.UPDATE_BUTTON)
    WebElement updateButton;
    @FindBy(xpath = SchedulerMap.REMOVE_BUTTON)
    WebElement removeButton;
    @FindBy(xpath = SchedulerMap.TIME_LINE)
    WebElement timeLine;
    @FindBy(xpath = SchedulerMap.ATTENDEES_LIST)
    WebElement attendeesList;

    public SchedulerPage() {
	PageFactory.initElements(BrowserManager.getDriver(), this);
    }

    public SchedulerPage setOrganizer(String organizerName) {
	(new WebDriverWait(BrowserManager.getDriver(), 30))
		.until(ExpectedConditions.visibilityOf(organizerTextField));
	organizerTextField.clear();
	organizerTextField.sendKeys(organizerName);

	LogManager.info("Organizer Name " + organizerName + " has been set up");

	return this;
    }

    public SchedulerPage setSubject(String subject) {
	(new WebDriverWait(BrowserManager.getDriver(), 30))
		.until(ExpectedConditions.visibilityOf(subjectTextField));
	subjectTextField.clear();
	subjectTextField.sendKeys(subject);

	LogManager.info("Subject " + subject + " has been set up");

	return this;
    }

    public SchedulerPage setAttende(String attendeName) {
	(new WebDriverWait(BrowserManager.getDriver(), 30))
		.until(ExpectedConditions.visibilityOf(attendeesTextField));
	attendeesTextField.clear();
	attendeesTextField.sendKeys(attendeName);
	attendeesTextField.sendKeys(Keys.ENTER);

	LogManager.info("Attende Name " + attendeName + " has been set up");

	return this;
    }

    public CredentialsPage clickOnCreateButton() {
	(new WebDriverWait(BrowserManager.getDriver(), 30))
		.until(ExpectedConditions.elementToBeClickable(createButton));
	createButton.click();

	LogManager.info("Create Button has been clicked");

	return new CredentialsPage();
    }

    public CredentialsPage clickUpdateButton() {
	(new WebDriverWait(BrowserManager.getDriver(), 30))
		.until(ExpectedConditions.elementToBeClickable(updateButton));
	updateButton.click();

	LogManager.info("Update button has been clicked");

	return new CredentialsPage();
    }

    public CredentialsPage clickRemoveButton() {
	(new WebDriverWait(BrowserManager.getDriver(), 30))
		.until(ExpectedConditions.elementToBeClickable(removeButton));
	removeButton.click();

	LogManager.info("Remove Button has been clicked");

	return new CredentialsPage();
    }

    public SchedulerPage clickOnMeetingButton(String subject) {
	getMeetingButton(subject).click();
	(new WebDriverWait(BrowserManager.getDriver(), 30))
		.until(ExpectedConditions.visibilityOf(updateButton));

	LogManager.info("Meeting Button " + subject + " has been clicked");

	return this;
    }
    
    public String getOrganizer() {
	return organizerTextField.getAttribute("value");
    }
    
    public String getSubject() {
	return subjectTextField.getAttribute("value");
    }

    public boolean isMeetingPresentOnTimeLine(String subject) {
	return getMeetingButton(subject) != null ? true : false;
    }

    public boolean isAttendeePresent(String attendee) {
	return getAttendee(attendee) != null;
    }

    private WebElement getAttendee(String attendee) {
	(new WebDriverWait(BrowserManager.getDriver(), 60))
		.until(ExpectedConditions.visibilityOf(attendeesList));
	WebElement list = BrowserManager.getDriver().findElement(
		By.xpath(SchedulerMap.ATTENDEES_LIST));

	List<WebElement> attendees = list.findElements(By
		.xpath(SchedulerMap.ATTENDEE_NAME));

	for (WebElement element : attendees) {
	    if (element.getText().equals(attendee)) {
		LogManager.info("Attendee " + attendee + " has been found");
		return element;
	    }
	}

	LogManager.info("Attendee " + attendee + " has not been found");

	return null;
    }

    private WebElement getMeetingButton(String subject) {
	(new WebDriverWait(BrowserManager.getDriver(), 60))
		.until(ExpectedConditions.visibilityOf(timeLine));
	WebElement time = BrowserManager.getDriver().findElement(
		By.xpath(SchedulerMap.TIME_LINE));

	List<WebElement> meetings = time.findElements(By
		.xpath(SchedulerMap.MEETING_BUTTON));

	for (WebElement element : meetings) {
	    if (element.getText().equals(subject)) {
		LogManager.info("Meeting " + subject + " has been found");
		return element;
	    }
	}

	LogManager.info("Meeting " + subject + " has not been found");

	return null;
    }
	
    public SchedulerPage clickOnMeeting(String subject) {
 	WebElement meeting = ExplicitWait.getWhenVisible(By.xpath("//span[@class='vis-item-content' and text()='"+subject+"']/parent::div"), 30);	    	meeting.click();
	LogManager.info("Meeting " + subject + " has been selected");
	return this;
    }
    
    public CredentialsPage clickOnRemoveButton() {
	ExplicitWait.waitElementVisible(removeButton, 30);
	removeButton.click();
	    	
	LogManager.info("Remove Button has been clicked");
	    	
	return new CredentialsPage();
    }
    
    public boolean isTitlePresent(String name) {
    	
    	String title = SchedulerMap.ROOM_NAME_TITLE.replace("roomName", name);
    	WebElement element = ExplicitWait.getWhenVisible(By.xpath(title), 15);
    	
    	return ((element != null)? true : false);
    }
    
}
