package org.fundacionjala.automation.framework.pages.admin.emailserver;

import org.fundacionjala.automation.framework.maps.admin.emailserver.AddEmailServerMap;
import org.fundacionjala.automation.framework.maps.admin.emailserver.EmailServerMap;
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
 * Float Page Object for adding a new Email Server
 *
 * @version 1.0 11 May 2016
 * @author Samuel Sahonero
 */
public class AddEmailServerPage {

    @FindBy(xpath = AddEmailServerMap.DOMAIN_SERVER_TEXT_FIELD)
    WebElement domainServerTextField;
    @FindBy(xpath = AddEmailServerMap.USERNAME_TEXT_FIELD)
    WebElement userNameTextField;
    @FindBy(xpath = AddEmailServerMap.PASSWORD_TEXT_FIELD)
    WebElement passwordTextField;
    @FindBy(xpath = AddEmailServerMap.DESCRIPTION_TEXT_FIELD)
    WebElement descriptionTextField;
    @FindBy(xpath = AddEmailServerMap.SAVE_BUTTON)
    WebElement saveButton;
    
    /**
     * Initialize elements of AddEmailServerPage with the current driver
     */
    public AddEmailServerPage() {
	PageFactory.initElements(BrowserManager.getDriver(), this);
    }

    /**
     * Set the Hostname
     * 
     * @param domainServer
     *            Name of hostname where is Exchange server (e.g.
     *            qadv02vhenco02.roommanager.local, roommanager.local)
     * @return this AddEmailServerPage instance
     */
    public AddEmailServerPage setDomainServer(String domainServer) {

	(new WebDriverWait(BrowserManager.getDriver(), 60))
		.until(ExpectedConditions.visibilityOf(domainServerTextField));
	domainServerTextField.clear();
	domainServerTextField.sendKeys(domainServer);

	LogManager.info("Domain Server '" + domainServer + "' has been set up");

	return this;
    }
    
    /**
     * Set the UserName
     * 
     * @param userName
     *            userName belongs Exchange server, take account that username
     *            must have cn, displayname and samAccount equal
     * @return this AddEmailServerPage instance
     * @see setPassword
     */
    public AddEmailServerPage setUserName(String userName) {

	(new WebDriverWait(BrowserManager.getDriver(), 60))
		.until(ExpectedConditions.visibilityOf(userNameTextField));
	userNameTextField.clear();
	userNameTextField.sendKeys(userName);

	LogManager.info("Domain User Name '" + userName + "' has been set up");

	return this;
    }
    
    /**
     * Set the Password
     * 
     * @param password
     *            password belongs to the user Exchange server, take account 
     *            that username must have cn, displayname and samAccount equal
     * @return this AddEmailServerPage instance
     * @see setUserName
     */
    public AddEmailServerPage setPassword(String password) {
	(new WebDriverWait(BrowserManager.getDriver(), 60))
		.until(ExpectedConditions.visibilityOf(passwordTextField));
	passwordTextField.clear();
	passwordTextField.sendKeys(password);

	LogManager.info("Domain Password '" + password + "' has been set up");

	return this;
    }
    
    /**
     * Set a description of Email Server
     * 
     * @param description 
     *            
     * @return this AddEmailServerPage instance
     */
    public AddEmailServerPage setDescription(String description) {
	(new WebDriverWait(BrowserManager.getDriver(), 60))
		.until(ExpectedConditions.visibilityOf(descriptionTextField));
	descriptionTextField.clear();
	descriptionTextField.sendKeys(description);

	LogManager.info("Email Server Description " + description
		+ " has been set up");

	return this;
    }
    
    /**
     * Click on Save Button
     *            
     * @return EmailServerPage previous instance
     */
    public EmailServerPage clickSaveButton() {
	
	ExplicitWait.clickWhenReady(By.xpath(AddEmailServerMap.SAVE_BUTTON), 60);
	LogManager.info("Add Email Server Save Button has been clicked");
	
	ExplicitWait.getWhenVisible(By.xpath(EmailServerMap.EMAIL_SERVER_BUTTON),60);

	return new EmailServerPage();
    }
}
