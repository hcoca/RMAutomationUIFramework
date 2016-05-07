package org.fundacionjala.automation.framework.maps.admin.resource;

public class ResourceMap {
	public static final String ADD_BUTTON = "//span[text()='Add']/parent::button";
	public static final String REMOVE_BUTTON = "//span[text()='Remove']/parent::button";
	public static final String RESOURCE_TABLE = "//div[@id='resourcesGrid']";
	public static final String RESOURCE_NAMES = "//div[@tabindex]/div/div/child::*[3]//span";
	public static final String RESOURCE_FILTER = "//span[text()='Filter By Name']/following::input[1]";
	public static final String TOTAL_ITEMS = "//div[@id='resourcesGrid']//div[@class='ngFooterTotalItems']//span[text()= 'Total Items: number']";
	
}
