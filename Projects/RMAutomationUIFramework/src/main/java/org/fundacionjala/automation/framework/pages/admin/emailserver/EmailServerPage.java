package org.fundacionjala.automation.framework.pages.admin.emailserver;

import java.util.List;

import org.fundacionjala.automation.framework.maps.admin.conferencerooms.ConferenceRoomsMap;
import org.fundacionjala.automation.framework.maps.admin.emailserver.EmailServerMap;
import org.fundacionjala.automation.framework.pages.admin.home.AdminPage;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.ExplicitWait;
import org.fundacionjala.automation.framework.utils.common.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This class manage actions in a Email Server Page
 * @author Samuel Sahonero
 */
public class EmailServerPage extends AdminPage {

    @FindBy(xpath = EmailServerMap.ADD_BUTTON)
    WebElement addButton;
    @FindBy(xpath = EmailServerMap.REMOVE_BUTTON)
    WebElement removeButton;
    @FindBy(xpath = EmailServerMap.EMAIL_SERVER_BUTTON)
    WebElement emailServerButton;
    @FindBy(xpath = EmailServerMap.EDIT_BUTTON)
    WebElement editButton;
    @FindBy(xpath = EmailServerMap.USERNAME_TEXT_FIELD)
    WebElement userNameTextField;
    @FindBy(xpath = EmailServerMap.PASSWORD_TEXT_FIELD)
    WebElement passwordTextField;
    @FindBy(xpath = EmailServerMap.ACCEPT_BUTTON)
    WebElement acceptButton;
    @FindBy(xpath = EmailServerMap.DESCRIPTION_TEXT_FIELD)
    WebElement descriptionTextField;
    @FindBy(xpath = EmailServerMap.ERROR_MESSAGE)
    WebElement errorMessage;
    @FindBy(xpath = EmailServerMap.CANCEL_BUTTON)
    WebElement cancelButton;
    
    /**
     * Initialize elements of EmailServerPage with the current driver
     */
    public EmailServerPage() {
	
	PageFactory.initElements(BrowserManager.getDriver(), this);
    }
    /**
     * Click on Add button
     * @return new "AddLocationPage" instance
     */
    public AddEmailServerPage clickOnAddButton() {
	
	/*(new WebDriverWait(BrowserManager.getDriver(), 20))
		.until(ExpectedConditions.visibilityOf(addButton));
	addButton.click();*/
    ExplicitWait.clickWhenReady(By.xpath(EmailServerMap.ADD_BUTTON), 10);

	LogManager.info("Add Email Server Button has been clicked");

	return new AddEmailServerPage();
    }

    /**
     * Click on Remove button
     * @return new "DeleteEmailServerPage" instance
     */
    public DeleteEmailServerPage clickOnRemoveButton() {

	ExplicitWait.clickWhenReady(By.xpath(EmailServerMap.REMOVE_BUTTON), 30);

	LogManager.info("Remove Email Server Button has been clicked");

	return new DeleteEmailServerPage();
    }

    /**
     * Click on Email Server item button
     * @return this "EmailServerPage" instance
     */
    public EmailServerPage clickOnServerButton() {
	
	(new WebDriverWait(BrowserManager.getDriver(), 30))
		.until(ExpectedConditions
			.elementToBeClickable(emailServerButton));
	emailServerButton.click();

	LogManager.info("Email Server Button has been clicked");

	return this;
    }
    
    /**
     * Click on Edit button in order to change the credential
     * @return this "EmailServerPage" instance
     */
    public EmailServerPage clickOnEditCredentialButton() {
	
	(new WebDriverWait(BrowserManager.getDriver(), 30))
		.until(ExpectedConditions.presenceOfElementLocated(By
			.xpath(EmailServerMap.EDIT_BUTTON)));
	editButton.click();

	LogManager.info("Edit Email Server Credential Button has been clicked");

	return this;
    }

    /**
     * Set the username of the account belongs to Exchange server
     * @param userName
     *            - Account username to change (e.g. Administrator)
     * @return this "EmailServerPage" instance
     */
    public EmailServerPage setUserName(String userName) {
	
	ExplicitWait.getWhenVisible(By
			.xpath(EmailServerMap.USERNAME_TEXT_FIELD), 30);
	
	userNameTextField.clear();
	userNameTextField.sendKeys(userName);

	LogManager.info("Domain User Name " + userName + " has been set up");

	return this;
    }
    
    /**
     * Set the password of the account belongs to Exchange server
     * @param password
     *            - Account password to change
     * @return this "EmailServerPage" instance
     */
    public EmailServerPage setPassword(String password) {
	
	ExplicitWait.getWhenVisible(By
		.xpath(EmailServerMap.PASSWORD_TEXT_FIELD), 30);
	passwordTextField.clear();
	passwordTextField.sendKeys(password);

	LogManager.info("Domain Password " + password + " has been set up");

	return this;
    }
    
    /**
     * Click on Accept button in order to confirm the changes and wait until
     * Edit button get visibility
     * @return this "EmailServerPage" instance
     */
    public EmailServerPage clickOnAcceptButton() {
	
	ExplicitWait.getWhenVisible(By
		.xpath(EmailServerMap.ACCEPT_BUTTON), 60);
	acceptButton.click();
	ExplicitWait.getWhenVisible(By
		.xpath(EmailServerMap.EDIT_BUTTON), 20);

	LogManager
		.info("Edit Email Server Credential Accept Button has been clicked");

	return this;
    }
    
    /**
     * Click on Accept button in order to confirm the changes and wait until
     * Edit button get visibility
     * @return this "EmailServerPage" instance
     */
    public EmailServerPage clickOnAcceptButton(boolean verify) {
	
	ExplicitWait.getWhenVisible(By
		.xpath(EmailServerMap.ACCEPT_BUTTON), 60);
	acceptButton.click();
	ExplicitWait.getWhenVisible(By
		.xpath(EmailServerMap.EDIT_BUTTON), 20, false);

	LogManager
		.info("Edit Email Server Credential Accept Button has been clicked");
	if (verify){
	    
	    //Verifying if an error message was found
	    WebElement errormsg = ExplicitWait.getWhenVisible(By
					.xpath(EmailServerMap.ERROR_MESSAGE),
					5, false);
	    if (errormsg != null){
		LogManager.error("Plugin internal error or Credential incorrect");
		ExplicitWait.getWhenVisible(By
				.xpath(EmailServerMap.CANCEL_BUTTON), 10);
		cancelButton.click();
		ExplicitWait.getWhenVisible(By
			.xpath(EmailServerMap.EDIT_BUTTON), 10);
	    }
	}
	
	return this;
    }

    /**
     * Find if Email server exist in the Email Server Page
     * @return boolean True - Email Server exist. 
     *                 False - There is no Email Server
     */
    public boolean isEmailServerPresent() {
	    			    		 
    	WebElement hostName = ExplicitWait.getWhenVisible(By.xpath(EmailServerMap.HOST_NAME), 15);	
	    if (hostName != null)					
	    {
	    	LogManager.info("Email Server Host Name " + hostName.getText()
	    		    + " has been found");
	    	return true;
	    }
	    else
	    {
	    	LogManager.info("Email Server Host Name has not been found");
	    	
	       return false;	
	    }
	    		
    }
    
    /**
     * Find if Email server exist in the Email Server Page
     * @return boolean True - Email Server exist. 
     *                 False - There is no Email Server
     */
    public boolean isAddButtonPresent() {
	    			    		 
    	WebElement addButton = ExplicitWait.getWhenVisible(By.xpath(EmailServerMap.ADD_BUTTON), 15, false);	
	    if (addButton != null)					
	    {
	    	LogManager.info("Add Button has been found");
	    	return true;
	    }
	    else
	    {
	    	LogManager.info("Add Button has not been found");
	    	
	       return false;	
	    }
	    		
    }
    /**
     * Find if Email server doesn't exist in the Email Server Page
     * @return boolean True - Email Server exist. 
     *                 False - There is no Email Server
     */
    public boolean isRemoveButtonPresent() {

	WebElement addButton = ExplicitWait.getWhenVisible(
		By.xpath(EmailServerMap.REMOVE_BUTTON), 15, false);
	if (addButton != null) {
	    LogManager.info("Remove Button has been found");
	    return true;
	} else {
	    LogManager.info("Remove Button has not been found");

	    return false;
	}

    }
    
    /**
     * Verify if exist Rooms in Conference Room Page
     * @return boolean True - There is rooms
     *                 False - There is no rooms
     */
    public boolean verifyIfThereAreRooms() {
	
	List<WebElement> roomsList = BrowserManager.getDriver().findElements(
		By.xpath(ConferenceRoomsMap.ROOMS_COLUMN));
	return (roomsList.size() > 0);
    }
    
    /**
     * @return String Username of Service Credential
     */
    public String getUserName() {
	
	String userName = userNameTextField.getAttribute("value");

	LogManager.info("User Name " + userName + " has been obtained");

	return userName;
    }
    
    /**
     * @return String Email Server Description
     */
    public String getEmailServerDescription() {
	
	String description = descriptionTextField.getAttribute("value");

	LogManager.info("Email Server Description " + description
		+ " has been obtained");

	return description;
    }
    
    /**
     * Wait for Error message in Credential edit
     */
    public EmailServerPage waitForErrorMessage() {
	
	ExplicitWait.getWhenVisible(By
		.xpath(EmailServerMap.ACCEPT_BUTTON), 60);
	acceptButton.click();
	ExplicitWait.getWhenVisible(By
		.xpath(EmailServerMap.ERROR_MESSAGE), 60);
	String myErrorMessage = errorMessage.getText();

	LogManager.info("Error Message " + myErrorMessage + " has been found");

	return this;
    }
    
    /**
     * @return String Error message in Credential edit
     */
    public String getErrorMessage() {
	
	String myErrorMessage = errorMessage.getText();

	LogManager.info("Error Message " + myErrorMessage
		+ " has been obtained");

	return myErrorMessage;
    }
}
