package org.fundacionjala.automation.framework.pages.admin.conferencerooms;

import java.util.List;

import org.fundacionjala.automation.framework.maps.admin.conferencerooms.ResourceAssociationsMap;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.UIActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResourceAssociationsPage {
  
	public ResourceAssociationsPage() {
		PageFactory.initElements(BrowserManager.getDriver(), this);
	}
	
	@FindBy (xpath = ResourceAssociationsMap.ASSOCIATEDRESOURCES_QUANTITY_ROWS) 
	private List<WebElement> quantityFields;
	
	
	
	@FindBy (xpath = ResourceAssociationsMap.RESOURCES_AVAILABLES) 
	private List<WebElement> resourcesAvailables;
	private List<WebElement> getResourcesAvailabes()
	{
	   return resourcesAvailables;
	}
	
	@FindBy (xpath = ResourceAssociationsMap.PLUSICONS_BUTTON) 
	private List<WebElement> plusIcons;
	private List<WebElement> getPlusIcons()
	{
		   return plusIcons;
	} 
	
	@FindBy (xpath = ResourceAssociationsMap.ASSOCIATEDRESOURCES_ROWS) 
	private List<WebElement> resourcesAssociated;
	public List<WebElement> getAssociatedResources()
	{
		   return resourcesAssociated;
	} 
	
	@FindBy (xpath = ResourceAssociationsMap.MINUSICONS_BUTTON) 
	private List<WebElement> minusIcons;
	private List<WebElement> getMinusIcons()
	{
		   return minusIcons;
		   
	} 
	public ResourceAssociationsPage addResource(String resourceName) throws InterruptedException
	{

		List<WebElement> resourceList = getResourcesAvailabes();
		System.out.println(resourceList);
		for (int i = 0; i < resourceList.size(); i++) {
			
			String resText = resourceList.get(i).getText();
			resText = resText.replaceAll("\\s+","");
	    	if (resText.contains(resourceName)) {
				addResourceByPos(i);
			}
		}
		return this;
	}
	private void addResourceByPos(int pos)
	{
		WebElement btnPlusOfResource = getPlusIcons().get(pos);
		btnPlusOfResource.click();
	}

	@FindBy (xpath = ResourceAssociationsMap.SAVE_BUTTON)
	private WebElement saveButton;
	public ConferenceRoomsPage clickOnSave()
	{
		saveButton.click();
		return new ConferenceRoomsPage();
	}
	
	public WebElement getResourceAssociated(String resourceName) {
		
		 for(WebElement resource : getAssociatedResources()){
		    	String resText = resource.getText();
		    	resText = resText.replaceAll("\\s+","");
		    	if (resText.equals(resourceName)) {
					return resource;
				}
		    }
			return null;
	}
	
	public boolean isInAssociatedColumn(String resource) {
		
		if(getResourceAssociated(resource)!= null)
		{
			return true;
		}
		
		return false;
	}
	public ResourceAssociationsPage editQuantityOfResourceAssociated(String resource, String quantity) {
		
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
	
	/**
	 * resources types can be: associated and availables
	 */
	private int getResourcePos(String resource, List<WebElement> resourcesType) {
		
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
