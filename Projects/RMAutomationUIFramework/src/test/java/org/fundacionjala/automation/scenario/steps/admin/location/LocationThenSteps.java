package org.fundacionjala.automation.scenario.steps.admin.location;

import java.util.List;

import org.fundacionjala.automation.framework.pages.admin.locations.LocationAssociationPage;
import org.fundacionjala.automation.framework.pages.admin.locations.LocationPage;
import org.fundacionjala.automation.framework.pages.admin.locations.UpdateLocationPage;
import org.fundacionjala.automation.framework.utils.api.managers.LocationAPIManager;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Location;
import org.fundacionjala.automation.framework.utils.common.LogManager;
import org.fundacionjala.automation.framework.utils.common.PropertiesReader;
import org.testng.Assert;

import com.mashape.unirest.http.exceptions.UnirestException;

import cucumber.api.java.After;
import cucumber.api.java.en.Then;

public class LocationThenSteps {
    private String displayNameCreated;
    private String nameCreated;
    @Then("^The location \"([^\"]*)\" is displayed on location page$")
    public void verifyDisplayedLocation(String name) throws Throwable {
	nameCreated = name;
	LocationPage locationPage = new LocationPage();
	Assert.assertTrue(locationPage.verifyLocationIsDisplayed(name));
	
    }

    @Then("^All locations added are displayed even \"([^\"]*)\" location$")
    public void verifyAllDiplayedLocations(String name) throws Throwable {
	nameCreated = name;
	LocationPage locationPage = new LocationPage();
	List<Location> locations = LocationAPIManager
		.getRequest(PropertiesReader.getServiceURL() + "/locations");

	for (Location location : locations) {
	    Assert.assertTrue(locationPage
		    .verifyLocationIsDisplayed(location.name));
	}

    }

    @Then("^The location display name \"([^\"]*)\" is displayed on location page$")
    public void verifyLocationName(String displayName) throws Throwable {
	displayNameCreated = displayName;
	LocationPage locationPage = new LocationPage();
	Assert.assertTrue(locationPage
		.verifyLocationIsDisplayedByDisplayName(displayName));
	
    }

    @Then("^The description \"([^\"]*)\" of location \"([^\"]*)\" is displayed on update location page$")
    public void verifyLocationDescription(String description, String displayName)
	    throws Throwable {
	displayNameCreated = displayName;
	LocationPage locationPage = new LocationPage();
	UpdateLocationPage updateLocationPage = new UpdateLocationPage();

	updateLocationPage = locationPage.leftMenu.clickOnIssuesButton()
		.clickOnLocationsButton().doubleClickOnALocation(displayName);

	Assert.assertTrue(updateLocationPage
		.verifyDescriptionIsDisplayed(description));
	
    }

    @Then("^The parent name \"([^\"]*)\" of location \"([^\"]*)\" is displayed on update location page$")
    public void verifyLocationParent(String parentName, String displayName)
	    throws Throwable {
	LocationPage locationPage = new LocationPage();
	UpdateLocationPage updateLocationPage = new UpdateLocationPage();

	updateLocationPage = locationPage.doubleClickOnALocation(displayName);

	Assert.assertTrue(updateLocationPage
		.verifyParentIsDisplayed(parentName));
	removeLocationByDisplayName(displayName);
	removeLocationByName(parentName);
    }

    @Then("^The location \"([^\"]*)\" is not displayed on location page$")
    public void verifyNotDisplayedLocation(String name) throws Throwable {
	LocationPage locationPage = new LocationPage();
	locationPage
		.leftMenu
		.clickOnIssuesButton()
		.clickOnLocationsButton();
	Assert.assertTrue(locationPage.verifyLocationIsNotDisplayed(name));
    }

    @Then("^The location \"([^\"]*)\" child of \"([^\"]*)\" is displayed on location page$")
    public void verifyChildLocation(String name, String parentName)
	    throws Throwable {
	LocationPage locationPage = new LocationPage();

	Assert.assertTrue(locationPage.verifyLocationIsDisplayed(name));
	removeLocationByName(name);
	removeLocationByName(parentName);
    }

    @Then("^The room \"([^\"]*)\" is displayed on Location Association page as associated with \"([^\"]*)\" location$")
    public void verifyAssociatedRoom(String roomName, String displayName)
	    throws Throwable {
	displayNameCreated = displayName;
	LocationPage locationPage = new LocationPage();
	LocationAssociationPage associationPage = new LocationAssociationPage();

	associationPage = locationPage
				.leftMenu
				.clickOnIssuesButton()
				.clickOnLocationsButton()
                		.doubleClickOnALocation(displayName)
                		.clickOnLocationAssociationLink();

	Assert.assertTrue(associationPage
		.verifyAssociatedRoomDisplayed(roomName));
	
    }

    @Then("^The room \"([^\"]*)\" is not displayed on Location Association page as associated with \"([^\"]*)\" location$")
    public void verifyNotAssociatedRoom(String roomName, String displayName)
	    throws Throwable {
	displayNameCreated = displayName;
	LocationPage locationPage = new LocationPage();
	LocationAssociationPage associationPage = new LocationAssociationPage();
	locationPage
		.leftMenu
		.clickOnIssuesButton()
		.clickOnLocationsButton();
	associationPage = locationPage.doubleClickOnALocation(displayName)
		.clickOnLocationAssociationLink();

	Assert.assertTrue(associationPage
		.verifyAvailableRoomDisplayed(roomName));

    }

    @Then("^The number of associations on Location page has been increased by \"([^\"]*)\" location association$")
    public void verifyAssociationNumber(String name) throws Throwable {
	nameCreated = name;
	LocationPage locationPage = new LocationPage();
	locationPage
    		.leftMenu
    		.clickOnIssuesButton()
    		.clickOnLocationsButton();
	Assert.assertTrue(locationPage.verifyNumberOfAssociations(name, "1"));

    }

    private void removeLocationByName(String name) throws UnirestException, InterruptedException {
	String idLocation = "";
	Thread.sleep(2000);
	List<Location> listLocation = LocationAPIManager
		.getRequest(PropertiesReader.getServiceURL() + "/locations");
	for (Location location : listLocation) {
	    if (location.name.equalsIgnoreCase(name)) {
		idLocation = location._id;
	    }
	}
	LocationAPIManager.deleteRequest(PropertiesReader.getServiceURL()
		+ "/locations", idLocation);
    }

    private void removeLocationByDisplayName(String displayName)
	    throws UnirestException, InterruptedException {
	String idLocation = "";
	Thread.sleep(2000);
	List<Location> listLocation = LocationAPIManager
		.getRequest(PropertiesReader.getServiceURL() + "/locations");
	for (Location location : listLocation) {
	    LogManager.info("Finding " + displayName + " Location by API:" + location.customName);
	    if (location.customName.equalsIgnoreCase(displayName)) {
		idLocation = location._id;
		break;
	    }
	}
	if (idLocation.length() > 0) {
	    LocationAPIManager.deleteRequest(PropertiesReader.getServiceURL()
		    + "/locations", idLocation);
	} else {
	    LogManager
		    .error("DELETE API can not execute, Location Id is empty for name:"
			    + displayName);
	}
    }

    @After("@location_by_displayname")
    public void afterLocationCreatedByDisplayName() {
	try {
	    LogManager.info("Cleaning location displayName " + displayNameCreated);
	    removeLocationByDisplayName(displayNameCreated);
	} catch (Exception exception) {
	    LogManager.error("DELETE error");
	}
    }
    
    @After("@location_by_name")
    public void afterLocationCreatedByName() {
	try {
	    LogManager.info("Cleaning location name " + nameCreated);
	    removeLocationByName(nameCreated);
	} catch (Exception exception) {
	    LogManager.error("DELETE error");
	}
    }
}
