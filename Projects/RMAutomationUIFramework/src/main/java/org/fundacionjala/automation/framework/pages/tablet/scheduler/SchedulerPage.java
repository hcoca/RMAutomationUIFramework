package org.fundacionjala.automation.framework.pages.tablet.scheduler;

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

public class SchedulerPage {
    
    	public TopMenu topMenu;

	@FindBy (xpath = SchedulerMap.ORGANIZER_TEXT_FIELD) WebElement organizerTextField;
	@FindBy (xpath = SchedulerMap.SUBJECT_TEXT_FIELD) WebElement subjectTextField;
	@FindBy (xpath = SchedulerMap.ATTENDEES_TEXT_FIELD) WebElement attendeesTextField;
	@FindBy (xpath = SchedulerMap.CREATE_BUTTON) WebElement createButton;
	@FindBy (xpath = SchedulerMap.REMOVE_BUTTON) WebElement removeButton;
	
	public SchedulerPage() {
	    	topMenu = new TopMenu();
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
	
	public SchedulerPage clickOnMeeting(String subject) {
	    	WebElement meeting = ExplicitWait.getWhenVisible(By.xpath("//span[@class='vis-item-content' and text()='"+subject+"']/parent::div"), 30);
	    	meeting.click();
	    	LogManager.info("Meeting " + subject + " has been selected");
	    	return this;
	}

	public CredentialsPage clickOnRemoveButton() {
	    	ExplicitWait.waitElementVisible(removeButton, 30);
	    	removeButton.click();
	    	
	    	LogManager.info("Remove Button has been clicked");
	    	
	    	return new CredentialsPage();
	}
}
