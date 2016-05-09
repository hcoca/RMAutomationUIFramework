package org.fundacionjala.automation.framework.pages.admin.resource;

import org.fundacionjala.automation.framework.maps.admin.resource.ResourceAssociationsMap;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ResourceAssociationsPage {
  
	public ResourceAssociationsPage()
	{
		PageFactory.initElements(BrowserManager.getDriver(), this);
	}

	public boolean isQtyDisplayed(String quantity) {
		String xpathText = ResourceAssociationsMap.QUANTITY.replace("qty", quantity);
		
		WebDriverWait wait = new WebDriverWait(BrowserManager.getDriver(),5);

		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathText)));
			return true;
			
		} catch (TimeoutException te) {
			
			return false;
		}	
	}
	
	
	
	
}
