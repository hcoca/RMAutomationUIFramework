package org.fundacionjala.automation.framework.pages.tablet.search;
import java.util.List;

import org.fundacionjala.automation.framework.maps.tablet.search.SearchMap;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.ExplicitWait;
import org.fundacionjala.automation.framework.utils.common.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {

	
	public SearchPage() {
		PageFactory.initElements(BrowserManager.getDriver(), this);
	}
	
	@FindBy(id = SearchMap.SEARCH_ADVANCED_BUTTON)
    private WebElement searchButton;
	
	@FindBy(id = SearchMap.ROOM_NAME_FIELD)
    private WebElement roomNameField;
	
	@FindBy(id = SearchMap.MINIMUM_CAPACITY_FIELD)
    private WebElement minimumCapacityField;
	
	
	@FindBy(xpath = SearchMap.CLEAR_BUTTON)
    private WebElement clearButton;
	
	public SearchPage clickOnSearchButton()
	{
		if (searchButton != null) {			
			searchButton.click();
			LogManager.info("Button advanced search was clicked");
		}
		else {
			LogManager.error("The buuton advanced search has not been found");
		}
		
		return this;
	}
	
	public SearchPage setRoomName(String nameCriteria)
	{
		if (roomNameField != null) {
			roomNameField.sendKeys(nameCriteria);
			LogManager.info("Field room name was changed to " + nameCriteria);
		}
		else {
			LogManager.error("Field room name was not found");
		}
		
		return this;
	}
	
	public SearchPage setMinimumCapacity(String minCapCriteria)
	{
		if (minimumCapacityField != null) {
			minimumCapacityField.sendKeys(minCapCriteria);
			LogManager.info("Field room name was changed to " + minCapCriteria);
		}
		else {
			LogManager.error("Field room name was not found");
		}
		
		return this;
		
	}
	public SearchPage clickOnClear()
	{
		if (clearButton != null) {
			clearButton.click();
			LogManager.info("Button clear has been clicked");
		}
		
		else {
			LogManager.error("Button has not been found in the page");
		}
		return this;
	}
	
	public boolean allFieldsArePresent()
	{
		boolean res;
		res = isElementPresent(By.id(SearchMap.ROOM_NAME_FIELD), "Room name field")
		   && isElementPresent(By.id(SearchMap.MINIMUM_CAPACITY_FIELD), "Minimum capacity field")
		   && isElementPresent(By.id(SearchMap.LOCATION_COMBOBOX), "Location combobox");
		
		return res;
	}
	
	public boolean isElementPresent(By locator, String nameOfElement)
	{
		WebElement element = ExplicitWait.getWhenVisible(locator, 10);
		
	    if (element != null) {
	    	
	    	LogManager.info(nameOfElement + " was found");
			return true;
		} else {
			
			LogManager.error(nameOfElement + "has not been found");
            return false;
		} 
	}
	
	public List<WebElement> getRoomList()
	{
		return ExplicitWait
				.getElementsWhenVisible(By.xpath(SearchMap.ROOMS_LIST), 10);
		
	}
	
}
