package org.fundacionjala.automation.framework.pages.admin.resource;

import org.fundacionjala.automation.framework.maps.admin.resource.SelectIconMap;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.fundacionjala.automation.framework.utils.common.ExplicitWait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
/**
 * This class represent icon page on AddResourcePage
 * @author rodrigocespedes
 *
 */
public class SelectIconPage {
    
    	/**
	* The constructor initialize the SelectIconPage
	*/
	public SelectIconPage(){
	    
		PageFactory.initElements(BrowserManager.getDriver(), this);
	}
	
	@FindBy (xpath = SelectIconMap.RIGHT_BUTTON) 
	WebElement nextButton;
	@FindBy (xpath = SelectIconMap.LEFT_BUTTON) 
	WebElement previousButton;
	@FindBy (xpath = SelectIconMap.PAGE_LABEL) 
	WebElement pageLable;
	
	/**
	 * this method is to doing click on next button
	 * @return this instance
	 */
	private void clickOnRightbutton(){
	    
		ExplicitWait.clickWhenReady(By.xpath(SelectIconMap.RIGHT_BUTTON), 30);
	}
	
	/**
	 * this method is to doing click on previous button
	 * @return this instance
	 */
	private void clickOnLeftbutton(){
	    
		ExplicitWait.clickWhenReady(By.xpath(SelectIconMap.LEFT_BUTTON), 30);
	}

	/**
	 * This method chooses according to parameter if does click on next or 
	 * previous button 
	 * @param button
	 */
	public SelectIconPage clickOnPagebutton(String button) {
	    if(button.equalsIgnoreCase("right")){
		clickOnRightbutton();
	    }else if(button.equalsIgnoreCase("left")){
		clickOnLeftbutton();
	    }
	    return this;
	}

	/**
	 * this method is to verify if the icon page changed
	 * @param button
	 * @return true if page changed to correct direction.
	 */
	public boolean verifyIfIconPageChanged(String button) {
	    String page = pageLable.getText();
	    int numberPage = Integer.parseInt(page.split(" ")[0]);
	    if(button.equalsIgnoreCase("right")){
		return 3 == numberPage ? true : false; 
	    }else{
		return 1 == numberPage ? true : false; 
	    }
	}
}
