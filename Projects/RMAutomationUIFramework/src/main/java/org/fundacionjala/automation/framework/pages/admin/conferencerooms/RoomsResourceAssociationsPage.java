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
  
	public RoomsResourceAssociationsPage() 
	{
		PageFactory.initElements(BrowserManager.getDriver(), this);
	}
	
	@FindBy (xpath = RoomResourceAssociationsMap.ASSOCIATEDRESOURCES_QUANTITY_ROWS) 
	private List<WebElement> quantityFields;
	
	
	private List<WebElement> getResourcesAvailabes()
	{
	   return ExplicitWait.getElementsWhenVisible(By.xpath(RoomResourceAssociationsMap.RESOURCES_AVAILABLES), 2);
	}
	
	private List<WebElement> getPlusIcons()
	{
		return ExplicitWait.getElementsWhenVisible(By.xpath(RoomResourceAssociationsMap.PLUSICONS_BUTTON), 5);
	} 
	
	public List<WebElement> getAssociatedResources()
	{
	
	  return ExplicitWait.getElementsWhenVisible(By.xpath(RoomResourceAssociationsMap.ASSOCIATEDRESOURCES_ROWS), 2);
		
	} 
	
	private List<WebElement> getMinusIcons()
	{
		return ExplicitWait.getElementsWhenVisible(By.xpath(RoomResourceAssociationsMap.MINUSICONS_BUTTON), 2);
	} 
	
	public RoomsResourceAssociationsPage addResource(String resourceName)
	{
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
	
	public RoomsResourceAssociationsPage removeResource(String resourceName)
	{
		List<WebElement> associatedResourceList = getAssociatedResources();
		
		for (int i = 0; i < associatedResourceList.size(); i++) {
			
			String resText = associatedResourceList.get(i).getText();
	    	if (resText.contains(resourceName)) {
				removeResourceByPos(i);
				UIActions.clickWhenReady(By.xpath(RoomResourceAssociationsMap.SAVE_BUTTON), 1);
				return this;		
			}
		}
		return this;
	}
	
	
	private void removeResourceByPos(int pos) {
		WebElement btnMinusOfResource = getMinusIcons().get(pos);
		btnMinusOfResource.click();
	}
	private void addResourceByPos(int pos)
	{
		UIActions.waitFor(RoomResourceAssociationsMap.PLUSICONS_BUTTON );
		WebElement btnPlusOfResource = getPlusIcons().get(pos);
		
		btnPlusOfResource.click();
	}

	@FindBy (xpath = RoomResourceAssociationsMap.SAVE_BUTTON)
	private WebElement saveButton;
	public ConferenceRoomsPage clickOnSave() throws InterruptedException
	{
		saveButton.click();
    	LogManager.info("Click on save");
		return new ConferenceRoomsPage();
	}
	
	
	public WebElement getResourceAssociated(String resourceName) 
	{
		
		 for(WebElement resource : getAssociatedResources()){
		    	String resText = resource.getText();
		    	resText = resText.replaceAll("\\s+","");
		    	if (resText.equals(resourceName)) {
					return resource;
				}
		    }
			return null;
	}
	
	public WebElement getResourceAvailable(String resourceName) 
	{
		
		 for(WebElement resource : getResourcesAvailabes()){
		    	String resText = resource.getText();
		    	resText = resText.replaceAll("\\s+","");
		    	if (resText.equals(resourceName)) {
					return resource;
				}
		    }
			return null;
	}
	
	public boolean isInAssociatedColumn(String resource) 
	{
		
		if(getResourceAssociated(resource)!= null)
		{
			return true;
		}
		
		return false;
	}
	
    public boolean isInAvailableList(String resourceName) {
    	
    	if(getResourceAvailable(resourceName) != null)
		{
			return true;
		}
		
		return false;
	}
    
	
	
	public RoomsResourceAssociationsPage editQuantityOfResourceAssociated(String resource, String quantity) 
	{
		int posOfResourceAssociated = getResourcePos(resource,getAssociatedResources());
		if(posOfResourceAssociated != -1)
		{
			UIActions.typeOn(quantityFields.get(posOfResourceAssociated), quantity);	
			
		}
		else {
			System.out.println("Resource associated not found!!");
		}
		return this;
		
	}
	
	
	public boolean hasTheQuantity(String resource, String quantity)
	{
		int posResourceAssociated = getResourcePos(resource, getAssociatedResources());
		return quantityFields.get(posResourceAssociated).getAttribute("value").equals(quantity);
	}
	
	/**
	 * resources types can be: associated or availables
	 */
	private int getResourcePos(String resource, List<WebElement> resourcesType)
	{
		
		for (int i = 0; i < resourcesType.size(); i++) {
			
			String resText = resourcesType.get(i).getText();
	    	resText = resText.replaceAll("\\s+","");
	    	
	    	if (resText.equals(resource)) {
				return i;
			}
		}
		return -1;
	}

	
	
}
