package org.fundacionjala.automation.framework.maps.admin.conferencerooms;

public class RoomResourceAssociationsMap {

	public static final String RESOURCES_AVAILABLES 
	                     = "//div[@class='list-group']/div[@ng-repeat='availableResource in availableResources']/div[2]/span";
	
	public static final String PLUSICONS_BUTTON 
	                     = "//div[@class='list-group']/div[@ng-repeat='availableResource in availableResources']/div[3]/*";
	
	public static final String ASSOCIATEDRESOURCES_ROWS 
	                     = "//div[@class='list-group']/div[@ng-repeat='associatedResource in roomResources']/div[2]/*";
	
	public static final String ASSOCIATEDRESOURCES_QUANTITY_ROWS
	                     = "//div[@class='list-group']/div[@ng-repeat='associatedResource in roomResources']/div[3]/*";
	
	public static final String MINUSICONS_BUTTON 
	                     = "//div[@class='list-group']/div[@ng-repeat='associatedResource in roomResources']/div[4]/button/*";

	public static final String SAVE_BUTTON = "//button[contains(.,'Save')]";
	
	public static final String CANCEL_BUTTON = "//button[contains(.,'Cancel')]";
   

	                  
	

}
