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
	public void the_location_is_displayed_on_location_page(String name) throws Throwable {
		LocationPage locationPage = new LocationPage();
		
		Assert.assertTrue(locationPage.verifyLocationIsDisplayed(name));

		removeLocationByName(name);
	}
	
	@Then("^All locations added are displayed even \"([^\"]*)\" location$")
	public void all_locations_added_are_displayed_even_location(String name) throws Throwable {
		LocationPage locationPage = new LocationPage();
		List<Location> locations = LocationAPIManager.getRequest("http://172.20.208.84:4040/locations");
		
		for (Location location : locations) {
			Assert.assertTrue(locationPage.verifyLocationIsDisplayed(location.name));
		}
		
		removeLocationByName(name);
	}
	
	@Then("^The location display name \"([^\"]*)\" is displayed on location page$")
	public void the_location_display_name_is_displayed_on_location_page(String displayName) throws Throwable {
		LocationPage locationPage = new LocationPage();
		
		Assert.assertTrue(locationPage.verifyLocationIsDisplayedByDisplayName(displayName));
		
		removeLocationByDisplayName(displayName);
	}
	
	@Then("^The description \"([^\"]*)\" of location \"([^\"]*)\" is displayed on update location page$")
	public void the_description_of_location_is_displayed_on_update_location_page(String description, String displayName) throws Throwable {
		LocationPage locationPage = new LocationPage();
		UpdateLocationPage updateLocationPage = new UpdateLocationPage();
		
		updateLocationPage =
			locationPage
			.doubleClickOnALocation(displayName);
		
		Assert.assertTrue(updateLocationPage.verifyDescriptionIsDisplayed(description));
		
		removeLocationByDisplayName(displayName);
	}
	
	@Then("^The parent name \"([^\"]*)\" of location \"([^\"]*)\" is displayed on update location page$")
	public void the_parent_name_of_location_is_displayed_on_update_location_page(String parentName, String displayName) throws Throwable {
		LocationPage locationPage = new LocationPage();
		UpdateLocationPage updateLocationPage = new UpdateLocationPage();
		
		updateLocationPage =
			locationPage
			.doubleClickOnALocation(displayName);
		
		Assert.assertTrue(updateLocationPage.verifyParentIsDisplayed(parentName));
		
		removeLocationByDisplayName(displayName);
		removeLocationByName(parentName);
	}
	
	@Then("^The location \"([^\"]*)\" is not displayed on location page$")
	public void the_location_is_not_displayed_on_location_page(String name) throws Throwable {
		LocationPage locationPage = new LocationPage();
		
		Assert.assertTrue(locationPage.verifyLocationIsNotDisplayed(name));
	}
	
	@Then("^The location \"([^\"]*)\" child of \"([^\"]*)\" is displayed on location page$")
	public void the_location_child_of_is_displayed_on_location_page(String name, String parentName) throws Throwable {
		LocationPage locationPage = new LocationPage();
		
		Assert.assertTrue(locationPage.verifyLocationIsDisplayed(name));
		
		removeLocationByName(name);
		removeLocationByName(parentName);
	}
	
	@Then("^The room \"([^\"]*)\" is displayed on Location Association page as associated with \"([^\"]*)\" location$")
	public void the_room_is_displayed_on_Location_Association_page_as_associated_with_location(String roomName, String displayName) throws Throwable {
		LocationPage locationPage = new LocationPage();
		LocationAssociationPage associationPage = new LocationAssociationPage();
		
		associationPage =
			locationPage
			.doubleClickOnALocation(displayName)
			.clickOnLocationAssociationLink();
		
		Assert.assertTrue(associationPage.verifyAnAssociatedRoomDisplayed(roomName));
		
		removeLocationByDisplayName(displayName);
	}
	
	@Then("^The room \"([^\"]*)\" is not displayed on Location Association page as associated with \"([^\"]*)\" location$")
	public void the_room_is_not_displayed_on_Location_Association_page_as_associated_with_location(String roomName, String displayName) throws Throwable {
		LocationPage locationPage = new LocationPage();
		LocationAssociationPage associationPage = new LocationAssociationPage();
		
		associationPage =
			locationPage
			.doubleClickOnALocation(displayName)
			.clickOnLocationAssociationLink();
		
		Assert.assertTrue(associationPage.verifyAnAvailableRoomDisplayed(roomName));
		
		removeLocationByDisplayName(displayName);
	}
	
	@Then("^The number of assciations on Location page has been increased by \"([^\"]*)\" location association$")
	public void the_number_of_assciations_on_Location_page_has_been_increased_by_location_association(String name) throws Throwable {
		LocationPage locationPage = new LocationPage();
		
		Assert.assertTrue(locationPage.verifyNumberOfAssociations(name, "1"));
		
		removeLocationByName(name);
	}
	
	private void removeLocationByName(String name) throws UnirestException{
		String idLocation = "";
		List<Location> listLocation = LocationAPIManager.getRequest(PropertiesReader.getServiceURL() +"/locations");
		for (Location location : listLocation) {
			if(location.name.equalsIgnoreCase(name))
			{
				idLocation = location._id;
			}
		}
		LocationAPIManager.deleteRequest(PropertiesReader.getServiceURL() +"/locations", idLocation);
	}
	
	private void removeLocationByDisplayName(String displayName) throws UnirestException{
		String idLocation = "";
		List<Location> listLocation = LocationAPIManager.getRequest(PropertiesReader.getServiceURL() +"/locations");
		for (Location location : listLocation) {
			if(location.customName.equalsIgnoreCase(displayName))
			{
				idLocation = location._id;
			}
		}
		LocationAPIManager.deleteRequest(PropertiesReader.getServiceURL() +"/locations", idLocation);
	}
}
