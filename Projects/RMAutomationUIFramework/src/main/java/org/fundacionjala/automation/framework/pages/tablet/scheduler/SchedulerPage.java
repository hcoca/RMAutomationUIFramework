package org.fundacionjala.automation.framework.pages.tablet.scheduler;

import java.awt.AWTException;
import java.util.List;

import org.fundacionjala.automation.framework.maps.tablet.scheduler.SchedulerMap;
import org.fundacionjala.automation.framework.pages.tablet.navigation.TopMenu;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.ExplicitWait;
import org.fundacionjala.automation.framework.utils.common.LogManager;
import org.fundacionjala.automation.framework.utils.common.UIActions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
    @FindBy(xpath = SchedulerMap.START_TEXT_FIELD)
    WebElement startTextField;
    @FindBy(xpath = SchedulerMap.END_TEXT_FIELD)
    WebElement endTextField;
    @FindBy(xpath = SchedulerMap.ATTENDEES_TEXT_FIELD)
    WebElement attendeesTextField;
    @FindBy(xpath = SchedulerMap.BODY_TEXT_FIELD)
    WebElement bodyTextField;
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
    @FindBy(xpath = SchedulerMap.CENTRAL_TIMELINE)
    WebElement timelineCenter;
    @FindBy(xpath = SchedulerMap.TIME_TIMELINE)
    List<WebElement> timeList;
    @FindBy(xpath = SchedulerMap.SUBJECT_ERROR_MESSAGE)
    WebElement subjectErrorMessage;
    @FindBy(xpath = SchedulerMap.FROM_DATE)
    WebElement fromDateTextbox;
    @FindBy(xpath = SchedulerMap.TO_DATE)
    WebElement toDateTextbox;

    public SchedulerPage() {
	this.topMenu = new TopMenu();
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
	Actions action = new Actions(BrowserManager.getDriver());
	action.click(getMeetingButton(subject));
	action.perform();
	(new WebDriverWait(BrowserManager.getDriver(), 30))
		.until(ExpectedConditions.visibilityOf(updateButton));
	LogManager.info("Meeting Button " + subject + " has been clicked");
	return this;
    }

    /**
     * This function is to return the organizer value
     * 
     * @return organizer value
     */
    public String getOrganizer() {
	return organizerTextField.getAttribute("value");
    }

    /**
     * This function is to return the subject value
     * 
     * @return subject value
     */
    public String getSubject() {
	return subjectTextField.getAttribute("value");
    }

    /**
     * This function is to return the start time value
     * 
     * @return start time value
     */
    public String getStartTime() {
	return startTextField.getAttribute("value");
    }

    /**
     * This function is to return the end time value
     * 
     * @return end time value
     */
    public String getEndTime() {
	return endTextField.getAttribute("value");
    }

    /**
     * This function is to return the body value
     * 
     * @return body value
     */
    public String getBody() {
	return bodyTextField.getAttribute("value");
    }

    public boolean isMeetingPresentOnTimeLine(String subject) {
	WebElement html = BrowserManager.getDriver().findElement(
		By.tagName("html"));
	boolean response = getMeetingButton(subject) != null ? true : false;
	html.sendKeys(Keys.chord(Keys.CONTROL, "0"));
	return response;
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
	WebElement meeting = ExplicitWait.getWhenVisible(
		By.xpath("//span[@class='vis-item-content' and text()='"
			+ subject + "']"), 60);

	Actions action = new Actions(BrowserManager.getDriver());
	action.click(meeting).build().perform();
	(new WebDriverWait(BrowserManager.getDriver(), 30))
		.until(ExpectedConditions.visibilityOf(updateButton));
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

	return ((element != null) ? true : false);
    }

    /**
     * this method is for setting description of meeting
     * 
     * @param body
     *            meeting description
     * @return SchedulerPage instance
     */
    public SchedulerPage setBody(String body) {
	(new WebDriverWait(BrowserManager.getDriver(), 30))
		.until(ExpectedConditions.visibilityOf(bodyTextField));
	bodyTextField.clear();
	bodyTextField.sendKeys(body);
	LogManager.info("Body " + bodyTextField + " has been set up");
	return this;
    }

    /**
     * this method is for setting start time of meeting
     * 
     * @param start
     * @return SchedulerPage instance
     */
    public SchedulerPage setStartTime(String start) {
	(new WebDriverWait(BrowserManager.getDriver(), 30))
		.until(ExpectedConditions.visibilityOf(startTextField));
	startTextField.clear();
	startTextField.sendKeys(start);
	LogManager.info("Start time " + startTextField + " has been set up");
	return this;
    }

    /**
     * this method is for setting end time of meeting
     * 
     * @param end
     * @return SchedulerPage instance
     */
    public SchedulerPage setEndTime(String end) {
	(new WebDriverWait(BrowserManager.getDriver(), 30))
		.until(ExpectedConditions.visibilityOf(endTextField));
	endTextField.clear();
	endTextField.sendKeys(end);
	LogManager.info("End time " + endTextField + " has been set up");
	return this;
    }

    /**
     * this method is to verify that the organizer test field is disable to edit
     * 
     * @return true id found the attribute disable else return false
     */
    public boolean verifyOrganizerTextFieldIsDisable() {

	ExplicitWait.waitForElement(SchedulerMap.ORGANIZER_TEXT_FIELD, 30);

	return organizerTextField.getAttribute("disabled").equalsIgnoreCase(
		"true") ? true : false;

    }

    /**
     * this method expand the time line in order to display all day on time line
     * 
     * @return SchedulerPage instance
     * @throws AWTException
     */
    public SchedulerPage displayAllDayOnTimeline() throws AWTException {
	ExplicitWait.waitForElement(SchedulerMap.TIME_LINE, 30);
	UIActions.scrollTimeline(timelineCenter, 5000);
	return this;
    }

    /**
     * This method is to verify that a list of attendees is present
     * 
     * @param attendees
     * @return true if all attendees are present else return false
     */
    public boolean verifyAteendees(List<String> attendees) {
	int count = 0;
	for (String attendee : attendees) {
	    if (isAttendeePresent(attendee)) {
		count = count + 1;
	    }
	}
	return (count == attendees.size()) ? true : false;
    }

    /**
     * this method is to verify if if time line displays all day, comparing time
     * list with 24 hours
     * 
     * @return true if time line displays all day else false
     */
    public boolean verifyIfTimelineDisplayAllDay() {
	return (timeList.size() == 24 || timeList.size() == 6) ? true : false;
    }

    public boolean verifyErrorMessageOfSubject() {
	return ExplicitWait.waitForElement(SchedulerMap.SUBJECT_ERROR_MESSAGE,
		30) ? true : false;
    }

    /**
     * This method verify that an field of a meeting is the same the parameter
     * value
     * 
     * @param field
     * @param value
     * @return true if the values are equal else return false
     */
    public boolean verifyFieldEdited(String field, String value) {
	switch (field) {
	case "subject": {
	    return (getSubject().equalsIgnoreCase(value)) ? true : false;
	}

	case "startTime": {
	    return (getStartTime().equalsIgnoreCase(value)) ? true : false;
	}
	case "endTime": {
	    return (getEndTime().equalsIgnoreCase(value)) ? true : false;
	}
	case "attendees": {
	    return (isAttendeePresent(value)) ? true : false;
	}
	case "body": {
	    return (getBody().equalsIgnoreCase(value)) ? true : false;
	}
	}
	return false;
    }

    /**
     * this method is to set up date on "from" text box
     * 
     * @param date
     * @return SchedulerPage instance
     */
    public SchedulerPage setFromDate(String date) {
	if (fromDateTextbox.getAttribute("disabled").equalsIgnoreCase("false")) {
	    fromDateTextbox.clear();
	    fromDateTextbox.sendKeys(date);
	    LogManager.info("Date " + fromDateTextbox + " has been set up");
	}
	LogManager.info("Date " + fromDateTextbox
		+ " has not been set up, because textbox is disabled");
	return this;
    }

    /**
     * this method is to set up date on "to" text box
     * 
     * @param date
     * @return SchedulerPage instance
     */
    public SchedulerPage setToDate(String date) {
	if (toDateTextbox.getAttribute("disabled").equalsIgnoreCase("false")) {
	    toDateTextbox.clear();
	    toDateTextbox.sendKeys(date);
	    LogManager.info("Date " + toDateTextbox + " has been set up");
	}
	LogManager.info("Date " + toDateTextbox
		+ " has not been set up, because textbox is disabled");
	return this;
    }

    /**
     * This method is to verify date text box are disabled
     * 
     * @return true if "to" and "from" are disabled else false
     */
    public boolean verifyTextboxDisabled() {
	return (toDateTextbox.getAttribute("disabled").equalsIgnoreCase("true") && fromDateTextbox
		.getAttribute("disabled").equalsIgnoreCase("true")) ? true
		: false;
    }

    public void extendTimeline() throws AWTException {
	ExplicitWait.waitForElement(SchedulerMap.TIME_LINE, 30);
	UIActions.scrollTimeline(timelineCenter, -3000);

    }

    public boolean verifyTimeIntervals() throws InterruptedException {
	Thread.sleep(5000);
	List<WebElement> timelineList = BrowserManager.getDriver()
		.findElements(By.xpath(SchedulerMap.TIME_TIMELINE));
	
	    if (verifyInterval(timelineList.get(1).getText().trim(),
		    timelineList.get(2).getText().trim())) {
		return true;
	    }
	    else{
		return false;
	    }
    }

    private boolean verifyInterval(String firstTime, String secondTime) {
	System.out.println(firstTime);
	System.out.println(secondTime);
	String[] firstTimeArray = firstTime.split(":");
	String[] secondTimeArray = secondTime.split(":");

	int hour = Integer.parseInt(secondTimeArray[0])
		- Integer.parseInt(firstTimeArray[0]);
	System.out.println(Integer.parseInt(secondTimeArray[0]) + "-"
		+ Integer.parseInt(firstTimeArray[0]) + "=" + hour);
	if (hour > 0) {
	    secondTimeArray[1] = "60";
	}

	int minutes = Integer.parseInt(secondTimeArray[1])
		- Integer.parseInt(firstTimeArray[1]);

	System.out.println(Integer.parseInt(secondTimeArray[1]) + "-"
		+ Integer.parseInt(firstTimeArray[1]) + "=" + minutes);

	return (minutes == 10) ? true : false;
    }

    /**
     * This function is to validate if the subject value is the same that the
     * parameter
     * @param newSubject
     * @return true if the values are same else return false
     */
    public boolean verifySubjectModified(String newSubject) {
	
	if(isMeetingPresentOnTimeLine(newSubject)){
	    clickOnMeetingButton(newSubject);
	    return (getSubject().equalsIgnoreCase(newSubject)) ?
		    true : false;
	}
	else{
	    return false;
	}
	
    }
}
