package org.fundacionjala.automation.framework.pages.admin.conferencerooms;
import java.util.List;

import org.fundacionjala.automation.framework.maps.admin.conferencerooms.RoomResourceAssociationsMap;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.ExplicitWait;
import org.fundacionjala.automation.framework.utils.common.LogManager;
import org.fundacionjala.automation.framework.utils.common.UIActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RoomsResourceAssociationsPage {

    public RoomsResourceAssociationsPage() {
	
	PageFactory.initElements(BrowserManager.getDriver(), this);
    }

    @FindBy(xpath = RoomResourceAssociationsMap.ASSOCIATEDRESOURCES_QUANTITY_ROWS)
    private List<WebElement> quantityFields;

    /**
     * This method is to return all resources available of the available column 
     * @return one list of web elements
     */
    private List<WebElement> getResourcesAvailabes() {
	
	return ExplicitWait.getElementsWhenVisible(
		By.xpath(RoomResourceAssociationsMap.RESOURCES_AVAILABLES), 30);
    }

    /**
     * This method is to return all the plus icons of the resources available
     * @return one list of web elements
     */
    private List<WebElement> getPlusIcons() {
	
	return ExplicitWait.getElementsWhenVisible(
		By.xpath(RoomResourceAssociationsMap.PLUSICONS_BUTTON), 5);
    }

    /**
     * This method is to return all the associated resources
     * @return a list of web elements
     */
    public List<WebElement> getAssociatedResources() {

	return ExplicitWait.getElementsWhenVisible(
		By.xpath(RoomResourceAssociationsMap.ASSOCIATEDRESOURCES_ROWS),2);
    }

    /**
     * This method is to return all the icon minus of the associated resources
     * @return a list of web elements
     */
    private List<WebElement> getMinusIcons() {
	
	return ExplicitWait.getElementsWhenVisible(
		By.xpath(RoomResourceAssociationsMap.MINUSICONS_BUTTON), 2);
    }

    /**
     * This method is to move one resource from available column to associated resource
     * @param resourceName - represent the displayname of the resource
     * @return RoomsResourceAssociationsPage
     */
    public RoomsResourceAssociationsPage addResource(String resourceName) {
	
	List<WebElement> resourceList = getResourcesAvailabes();
	for (int i = 0; i < resourceList.size(); i++) {
      
	    String resText = resourceList.get(i).getText();
	    if (resText.contains(resourceName)) {

		LogManager.info("The resource: " + resourceName + " was added");
		addResourceByPos(i);
	    }
	}
	return this;
    }

    /**
     * This method is to move one resource from associated column to available resource
     * @param resourceName - represent the displayname of the resource
     * @return RoomsResourceAssociationsPage
     */
    public RoomsResourceAssociationsPage removeResource(String resourceName) {
	
	List<WebElement> associatedResourceList = getAssociatedResources();

	for (int i = 0; i < associatedResourceList.size(); i++) {

	    String resText = associatedResourceList.get(i).getText();
	    if (resText.contains(resourceName)) {
		removeResourceByPos(i);
		UIActions.clickWhenReady(
			By.xpath(RoomResourceAssociationsMap.SAVE_BUTTON), 1);
		return this;
	    }
	}
	return this;
    }
    
    /**
     * This method is to perform a click on the minus icon of the resource associated
     * @param pos - integer that represent the position of the icon
     */
    private void removeResourceByPos(int pos) {
	
	WebElement btnMinusOfResource = getMinusIcons().get(pos);
	btnMinusOfResource.click();
    }

    /**
     * This method is to perform a click on the plus icon of the resource available
     * @param pos - integer that represent the position of the icon
     */
    private void addResourceByPos(int pos) {
	
	UIActions.waitFor(RoomResourceAssociationsMap.PLUSICONS_BUTTON);
	WebElement btnPlusOfResource = getPlusIcons().get(pos);

	btnPlusOfResource.click();
    }

    @FindBy(xpath = RoomResourceAssociationsMap.SAVE_BUTTON)
    private WebElement saveButton;

    /**
     * This method perform a click on save changes button
     * @return a new instance of ConferenceRoomsPage
     * @throws InterruptedException 
     */
    public ConferenceRoomsPage clickOnSave() {
	saveButton.click();
	LogManager.info("Click on save");
	return new ConferenceRoomsPage();
    }
    
    /**
     * This method is to get one associated resource
     * @param resourceName - represent the name of the associated resource 
     * @return one Web element
     */
    public WebElement getResourceAssociated(String resourceName) {

	for (WebElement resource : getAssociatedResources()) {
	    String resText = resource.getText();
	    resText = resText.replaceAll("\\s+", "");
	    if (resText.equals(resourceName)) {
		return resource;
	    }
	}
	return null;
    }

    /**
     * This method is to return one available resource
     * @param resourceName - represent the name of the resource
     * @return one web element
     */
    public WebElement getResourceAvailable(String resourceName) {

	for (WebElement resource : getResourcesAvailabes()) {
	    String resText = resource.getText();
	    resText = resText.replaceAll("\\s+", "");
	    if (resText.equals(resourceName)) {
		return resource;
	    }
	}
	return null;
    }

    /**
     * This method return true if the resource is associated
     * @param resource - name of the resource associated
     * @return a boolean expression
     */
    public boolean isInAssociatedColumn(String resource) {
	
	return ((getResourceAssociated(resource) != null) ? true : false);
    }

    /**
     * This method return true if the resource is available
     * @param resource - name of the resource available
     * @return a boolean expression
     */
    public boolean isInAvailableList(String resourceName) {
	
	return ((getResourceAvailable(resourceName) != null) ? true : false);
    }

    /**
     * This method is to edit the quantity of the resource associated
     * @param resource - String name of the resource
     * @param quantity - integer 
     * @return RoomsResourceAssociationsPage
     */
    public RoomsResourceAssociationsPage editQuantityOfResourceAssociated(String resource, 
	                                                                  String quantity) {

	int posOfResourceAssociated = getResourcePos(resource, getAssociatedResources());
	
	if (posOfResourceAssociated != -1) {
	    UIActions.typeOn(quantityFields.get(posOfResourceAssociated),quantity);
	}
	return this;
    }

    /**
     * This method is to verify if the quantity is displayed
     * @param resource
     * @param quantity
     * @return a boolean expresion true or false
     */
    public boolean hasTheQuantity(String resource, String quantity) {
	
	int posResourceAssociated = getResourcePos(resource,getAssociatedResources());
	String valueOfTheResource = quantityFields.get(posResourceAssociated).getAttribute("value"); 
	
	return (valueOfTheResource.equals(quantity)? true : false);
    }
    
    /**
     * This method is to get the Resource by position and by type: available or associated 
     * @param resource - String name of the resource
     * @param resourcesType - One list of elements: List<WebElement> associated, List<WebElement> available 
     * @return integer the position of the resource
     */
    private int getResourcePos(String resource, List<WebElement> resourcesType) {

	for (int i = 0; i < resourcesType.size(); i++) {

	    String resText = resourcesType.get(i).getText();
	    resText = resText.replaceAll("\\s+", "");

	    if (resText.equals(resource)) {
		return i;
	    }
	}
	return -1;
    }

}
