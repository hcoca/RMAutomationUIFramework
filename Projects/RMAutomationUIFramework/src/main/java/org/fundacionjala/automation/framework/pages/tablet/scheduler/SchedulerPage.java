package org.fundacionjala.automation.framework.pages.tablet.scheduler;

import org.fundacionjala.automation.framework.maps.tablet.scheduler.SchedulerMap;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.LogManager;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SchedulerPage {

	@FindBy (xpath = SchedulerMap.ORGANIZER_TEXT_FIELD) WebElement organizerTextField;
	@FindBy (xpath = SchedulerMap.SUBJECT_TEXT_FIELD) WebElement subjectTextField;
	@FindBy (xpath = SchedulerMap.ATTENDEES_TEXT_FIELD) WebElement attendeesTextField;
	@FindBy (xpath = SchedulerMap.CREATE_BUTTON) WebElement createButton;
	
	public SchedulerPage() {
		PageFactory.initElements(BrowserManager.getDriver(), this);
	}
	
	public SchedulerPage setOrganizer(String organizerName) {
		(new WebDriverWait(BrowserManager.getDriver(), 30)).until(ExpectedConditions.visibilityOf(organizerTextField));
		organizerTextField.clear();
		organizerTextField.sendKeys(organizerName);
		
		LogManager.info("Organizer Name " + organizerName + " has been set up");
		
		return this;
	}
	
	public SchedulerPage setSubject(String subject) {
		(new WebDriverWait(BrowserManager.getDriver(), 30)).until(ExpectedConditions.visibilityOf(subjectTextField));
		subjectTextField.clear();
		subjectTextField.sendKeys(subject);
		
		LogManager.info("Subject " + subject + " has been set up");
		
		return this;
	}
	
	public SchedulerPage setAttende(String attendeName) {
		(new WebDriverWait(BrowserManager.getDriver(), 30)).until(ExpectedConditions.visibilityOf(attendeesTextField));
		attendeesTextField.clear();
		attendeesTextField.sendKeys(attendeName);
		attendeesTextField.sendKeys(Keys.ENTER);
		
		LogManager.info("Attende Name " + attendeName + " has been set up");
		
		return this;
	}
	
	public CredentialsPage clickOnCreateButton() {
		(new WebDriverWait(BrowserManager.getDriver(), 30)).until(ExpectedConditions.elementToBeClickable(createButton));
		createButton.click();
		
		LogManager.info("Create Button has been clicked");
		
		return new CredentialsPage();
	}
}
