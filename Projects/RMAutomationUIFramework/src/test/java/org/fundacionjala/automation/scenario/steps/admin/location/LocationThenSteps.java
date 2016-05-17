package org.fundacionjala.automation.scenario.steps.admin.location;

import java.util.List;

import org.fundacionjala.automation.framework.pages.admin.locations.LocationAssociationPage;
import org.fundacionjala.automation.framework.pages.admin.locations.LocationPage;
import org.fundacionjala.automation.framework.pages.admin.locations.UpdateLocationPage;
import org.fundacionjala.automation.framework.utils.api.managers.LocationAPIManager;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Location;
import org.fundacionjala.automation.framework.utils.common.PropertiesReader;
import org.testng.Assert;

import com.mashape.unirest.http.exceptions.UnirestException;

import cucumber.api.java.en.Then;

public class LocationThenSteps {
	
	@Then("^The location \"([^\"]*)\" is displayed on location page$")
	public void verifyDisplayedLocation(String name) throws Throwable {
		LocationPage locationPage = new LocationPage();
		Assert.assertTrue(locationPage.verifyLocationIsDisplayed(name));
		removeLocationByName(name);
	}
	
	@Then("^All locations added are displayed even \"([^\"]*)\" location$")
	public void verifyAllDiplayedLocations(String name) throws Throwable {
		LocationPage locationPage = new LocationPage();
		List<Location> locations = 
				LocationAPIManager.getRequest(PropertiesReader.getServiceURL() 
					+ "/locations");
		
		for (Location location : locations) {
			Assert.assertTrue(
				locationPage.verifyLocationIsDisplayed(location.name));
		}
		
		removeLocationByName(name);
	}
	
	@Then("^The location display name \"([^\"]*)\" is displayed on location page$")
	public void verifyLocationName(String displayName) throws Throwable {
		LocationPage locationPage = new LocationPage();
		Assert.assertTrue(
			locationPage.verifyLocationIsDisplayedByDisplayName(displayName));
		removeLocationByDisplayName(displayName);
	}
	
	@Then("^The description \"([^\"]*)\" of location \"([^\"]*)\" is displayed on update location page$")
	public void verifyLocationDescription(String description,
					      String displayName) throws Throwable {
		LocationPage locationPage = new LocationPage();
		UpdateLocationPage updateLocationPage = new UpdateLocationPage();
		
		updateLocationPage =
			locationPage
			.leftMenu.clickOnIssuesButton()
			.clickOnLocationsButton()
			.doubleClickOnALocation(displayName);
		
		Assert.assertTrue(
			updateLocationPage.verifyDescriptionIsDisplayed(description));
		removeLocationByDisplayName(displayName);
	}
	
	@Then("^The parent name \"([^\"]*)\" of location \"([^\"]*)\" is displayed on update location page$")
	public void verifyLocationParent(String parentName, 
					 String displayName) throws Throwable {
		LocationPage locationPage = new LocationPage();
		UpdateLocationPage updateLocationPage = new UpdateLocationPage();
		
		updateLocationPage = locationPage
					.doubleClickOnALocation(displayName);
		
		Assert.assertTrue(
			updateLocationPage.verifyParentIsDisplayed(parentName));
		removeLocationByDisplayName(displayName);
		removeLocationByName(parentName);
	}
	
	@Then("^The location \"([^\"]*)\" is not displayed on location page$")
	public void verifyNotDisplayedLocation(String name) throws Throwable {
		LocationPage locationPage = new LocationPage();
		
		Assert.assertTrue(
			locationPage.verifyLocationIsNotDisplayed(name));
	}
	
	@Then("^The location \"([^\"]*)\" child of \"([^\"]*)\" is displayed on location page$")
	public void verifyChildLocation(String name, 
					String parentName) throws Throwable {
		LocationPage locationPage = new LocationPage();
		
		Assert.assertTrue(
			locationPage.verifyLocationIsDisplayed(name));
		removeLocationByName(name);
		removeLocationByName(parentName);
	}
	
	@Then("^The room \"([^\"]*)\" is displayed on Location Association page as associated with \"([^\"]*)\" location$")
	public void verifyAssociatedRoom(String roomName, 
					 String displayName) throws Throwable {
		LocationPage locationPage = new LocationPage();
		LocationAssociationPage associationPage = new LocationAssociationPage();
		
		associationPage =
			locationPage
			.doubleClickOnALocation(displayName)
			.clickOnLocationAssociationLink();
		
		Assert.assertTrue(
			associationPage.verifyAssociatedRoomDisplayed(roomName));
		removeLocationByDisplayName(displayName);
	}
	
	@Then("^The room \"([^\"]*)\" is not displayed on Location Association page as associated with \"([^\"]*)\" location$")
	public void verifyNotAssociatedRoom(String roomName, 
					    String displayName) throws Throwable {
		LocationPage locationPage = new LocationPage();
		LocationAssociationPage associationPage = new LocationAssociationPage();
		
		associationPage =
			locationPage
			.doubleClickOnALocation(displayName)
			.clickOnLocationAssociationLink();
		
		Assert.assertTrue(
			associationPage.verifyAvailableRoomDisplayed(roomName));
		removeLocationByDisplayName(displayName);
	}
	
	@Then("^The number of assciations on Location page has been increased by \"([^\"]*)\" location association$")
	public void verifyAssociationNumber(String name) throws Throwable {
		LocationPage locationPage = new LocationPage();
		
		Assert.assertTrue(
			locationPage.verifyNumberOfAssociations(name, "1"));
		
		removeLocationByName(name);
	}
	
	private void removeLocationByName(String name) throws UnirestException{
		String idLocation = "";
		List<Location> listLocation = 
				LocationAPIManager.getRequest(PropertiesReader.getServiceURL() 
						+ "/locations");
		for (Location location : listLocation) {
			if(location.name.equalsIgnoreCase(name))
			{
				idLocation = location._id;
			}
		}
		LocationAPIManager.deleteRequest(PropertiesReader.getServiceURL() 
				+ "/locations", idLocation);
	}
	
	private void removeLocationByDisplayName(String displayName) throws UnirestException{
		String idLocation = "";
		List<Location> listLocation = 
				LocationAPIManager.getRequest(PropertiesReader.getServiceURL() 
						+ "/locations");
		for (Location location : listLocation) {
			if(location.customName.equalsIgnoreCase(displayName))
			{
				idLocation = location._id;
			}
		}
		LocationAPIManager.deleteRequest(PropertiesReader.getServiceURL() 
				+ "/locations", idLocation);
	}
}
