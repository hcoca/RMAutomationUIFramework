package org.fundacionjala.automation.scenario.steps.admin.conferenceRoom;

import org.fundacionjala.automation.framework.pages.admin.conferencerooms.ConferenceRoomsPage;
import org.fundacionjala.automation.framework.pages.admin.home.AdminPage;
import org.fundacionjala.automation.framework.pages.admin.login.LoginPage;
import org.fundacionjala.automation.framework.utils.common.BrowserManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ConferenceRoomGroupByColumn {
	AdminPage home;
	ConferenceRoomsPage room;
	
	@When("^I drag and drop a specific column \"([^\"]*)\" to the header of the table$")
	public void i_drag_and_drop_a_specific_column_to_the_header_of_the_table(String arg1) throws Throwable {
		BrowserManager.openBrowser();
		LoginPage login = new LoginPage();
		home = login.setUserName("Administrator")
					.setPassword("Control*123")
					.clickOnSigInButton()
					.refreshPage();
		room = home.leftMenu.clickOnConferenceRoomsButton();

		Actions builder = new Actions(BrowserManager.getDriver());
		WebElement element = BrowserManager.getDriver().findElement(By.xpath("//div[@id='roomsGrid']/div/div[2]/div/div/div[2]/div/div"));
		WebElement target = BrowserManager.getDriver().findElement(By.cssSelector("div.ngGroupPanelDescription.ng-binding"));//div.ngGroupPanel//div.ngGroupPanelDescription.ng-binding
	    element.click();
	   
		builder.clickAndHold(element).build().perform();
		builder.moveByOffset(164, 167);
	   //builder.moveToElement(target).build().perform();
	    builder.release(target).build().perform();
	    
	    //(new Actions(BrowserManager.getDriver())).dragAndDrop(element, target).build().perform();
	    
		/*Action dragAndDrop = builder.clickAndHold(element).moveToElement(target).release(target).build();
		dragAndDrop.perform();*/
		
		/*Action dragAndDrop = builder.clickAndHold(element)
		   .moveToElement(target)
		   .release(target)
		   .build();
		   dragAndDrop.perform();*/
	    //builder.moveToElement(element).moveToElement(target).click().build().perform();
		
		By from = By.xpath("//div[@id='roomsGrid']/div/div[2]/div/div/div[2]/div/div");
		By to = By.cssSelector("div.ngGroupPanelDescription.ng-binding");
		dragdrop(from, to);
	}
	
	  public void dragdrop(By ByFrom, By ByTo) {
    	  WebElement LocatorFrom = BrowserManager.getDriver().findElement(ByFrom);
    	  WebElement LocatorTo = BrowserManager.getDriver().findElement(ByTo);
    	  String xto=Integer.toString(LocatorTo.getLocation().x);
    	  String yto=Integer.toString(LocatorTo.getLocation().y);
    	  System.out.println("POSITIONS :"+ xto +"-"+ yto);
    	  ((JavascriptExecutor)BrowserManager.getDriver()).executeScript(
    			  "function simulate(f,c,d,e){var b,a=null;for(b in eventMatchers)if(eventMatchers[b].test(c)){a=b;break}if(!a)return!1;document.createEvent?(b=document.createEvent(a),a==\"HTMLEvents\"?b.initEvent(c,!0,!0):b.initMouseEvent(c,!0,!0,document.defaultView,0,d,e,d,e,!1,!1,!1,!1,0,null),f.dispatchEvent(b)):(a=document.createEventObject(),a.detail=0,a.screenX=d,a.screenY=e,a.clientX=d,a.clientY=e,a.ctrlKey=!1,a.altKey=!1,a.shiftKey=!1,a.metaKey=!1,a.button=1,f.fireEvent(\"on\"+c,a));return!0} var eventMatchers={HTMLEvents:/^(?:load|unload|abort|error|select|change|submit|reset|focus|blur|resize|scroll)$/,MouseEvents:/^(?:click|dblclick|mouse(?:down|up|over|move|out))$/}; " +
    			  "simulate(arguments[0],\"mousemove\",arguments[1],arguments[2]);",
    			  LocatorFrom,xto,yto);
    	 }

	@Then("^I validate if the format of the table changes to group by the column specified \"([^\"]*)\"$")
	public void i_validate_if_the_format_of_the_table_changes_to_group_by_the_column_specified(String arg1) throws Throwable {
	    
	}
}
