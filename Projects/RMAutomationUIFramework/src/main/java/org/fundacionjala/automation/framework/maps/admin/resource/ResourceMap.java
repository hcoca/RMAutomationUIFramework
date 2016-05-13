package org.fundacionjala.automation.framework.maps.admin.resource;

public class ResourceMap {
	public static final String ADD_BUTTON = "//span[text()='Add']/parent::button";
	public static final String REMOVE_BUTTON = "//span[text()='Remove']/parent::button";
	public static final String RESOURCE_TABLE = "//div[@id='resourcesGrid']";
	public static final String ROWS_CONTAINER = "//div[@id='resourcesGrid']//div[@class='ngCanvas']";
	public static final String RESOURCE_NAMES = "//div[@tabindex]/div/div/child::*[3]//span";
	public static final String RESOURCE_FILTER = "//span[text()='Filter By Name']/following::input[1]";
	public static final String TOTAL_ITEMS = "//div[@id='resourcesGrid']//div[@class='ngFooterTotalItems']//span[text()= 'Total Items: number']";
	public static final String DROPDOWN_PAGE_SIZE = "//div[@id='resourcesGrid']/div[3]/div/div[2]/div[1]/select//option[text()='number']";
	public static final String FIRST_PAGE_BUTTON = "//div[@id='resourcesGrid']//div[@class='ngPagerContainer']//button[@title='First Page']";
	public static final String INPUT_NUMBER_PAGE = "//div[@id='resourcesGrid']//div[@class='ngPagerContainer']//input[@ng-model='pagingOptions.currentPage']";
	public static final String LAST_PAGE_BUTTON = "//div[@id='resourcesGrid']//div[@class='ngPagerContainer']//button[@title='Last Page']";
	public static final String TOTAL_NUMBER_PAGE = "//div[@id='resourcesGrid']//div[@class='ngPagerContainer']//div[@class='ngPagerControl']//span[contains(text(), 'totalPages')]";
	public static final String NEXT_PAGE_BUTTON = "//div[@id='resourcesGrid']//div[@class='ngPagerContainer']//button[@title='Next Page']";
	public static final String PREVIOUS_PAGE_BUTTON = "//div[@id='resourcesGrid']//div[@class='ngPagerContainer']//button[@title='Previous Page']";
	public static final String TOTAL_PAGES_LABEL = "//div[@id='resourcesGrid']//div[@class='ngPagerContainer']/div[@class='ngPagerControl']//span";
	
}
