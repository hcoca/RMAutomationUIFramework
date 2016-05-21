package org.fundacionjala.automation.scenario.steps.tablet.home;

import java.awt.AWTException;

import org.fundacionjala.automation.framework.pages.tablet.home.HomePage;
import org.fundacionjala.automation.framework.pages.tablet.scheduler.SchedulerPage;
import org.fundacionjala.automation.framework.pages.tablet.settings.NavigationPage;
import org.fundacionjala.automation.framework.utils.api.managers.MeetingAPIManager;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Meeting;
import org.fundacionjala.automation.framework.utils.common.PropertiesReader;
import org.testng.Assert;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

import cucumber.api.java.en.Then;

public class HomeThenSteps {

    @Then("^All meetings of \"([^\"]*)\" room are displayed on home time line even \"([^\"]*)\" meeting$")
    public void verifyTimeLineMeetings(String roomName, String subject)
	    throws Throwable {
	HomePage homePage = new HomePage();

	MongoClient mongoClient = new MongoClient(
		PropertiesReader.getHostIPAddress(),
		PropertiesReader.getMongoDBConnectionPort());
	DB db = mongoClient.getDB(PropertiesReader.getDBName());
	DBCollection table = db.getCollection(PropertiesReader
		.getMongoDBMeetingTable());
	BasicDBObject query = new BasicDBObject("location", roomName);
	DBCursor cursor = table.find();

	boolean actualResult = homePage.verifyTimeLineMeetings(cursor
		.getCollection().count(query));

	deleteMeetingByAPI(roomName, subject);

	Assert.assertTrue(actualResult);
    }

    @Then("^The meeting \"([^\"]*)\" is displayed on Home Page as current in room \"([^\"]*)\"$")
    public void verifyCurrentMeeting(String subject, String roomName) throws Throwable {
	SchedulerPage schedulerPage = new SchedulerPage();
	HomePage homePage = new HomePage();

	schedulerPage.topMenu.clickOnHomeButton();

	boolean actualResult = homePage.verifyCurrentMeetingDisplayed(subject);

	deleteMeetingByAPI(roomName, subject);

	Assert.assertTrue(actualResult);
    }

    @Then("^The organizer \"([^\"]*)\" of current meeting \"([^\"]*)\" is displayed on Home in room \"([^\"]*)\"$")
    public void verifyCurrentMeetingOrganizer(String organizer, String subject, String roomName)
	    throws Throwable {
	SchedulerPage schedulerPage = new SchedulerPage();
	HomePage homePage = new HomePage();

	schedulerPage.topMenu.clickOnHomeButton();

	boolean actualResult = homePage
		.verifyCurrentOrganizerDisplayed(organizer);

	deleteMeetingByAPI(roomName, subject);

	Assert.assertTrue(actualResult);
    }

    @Then("^The meeting \"([^\"]*)\" is displayed on Home Page as next in room \"([^\"]*)\"$")
    public void verifyNextMeeting(String subject, String roomName) throws Throwable {
	SchedulerPage schedulerPage = new SchedulerPage();
	HomePage homePage = new HomePage();

	schedulerPage.topMenu.clickOnHomeButton();

	boolean actualResult = homePage.verifyNextMeetingDisplayed(subject);

	deleteMeetingByAPI(roomName, subject);

	Assert.assertTrue(actualResult);
    }

    @Then("^The organizer \"([^\"]*)\" of next meeting \"([^\"]*)\" is displayed on Home in room \"([^\"]*)\"$")
    public void verifyNextMeetingOrganizer(String organizer, String subject, String roomName)
	    throws Throwable {
	SchedulerPage schedulerPage = new SchedulerPage();
	HomePage homePage = new HomePage();

	schedulerPage.topMenu.clickOnHomeButton();

	boolean actualResult = homePage.verifyNextOrganizerDisplayed(organizer);

	deleteMeetingByAPI(roomName, subject);

	Assert.assertTrue(actualResult);
    }

    @Then("^The correct time left until end of the day should be displayed on Home page$")
    public void verifyCorrectLeftTime() throws Throwable {
	HomePage homePage = new HomePage();
	boolean actualResult = homePage.verifyTimeLeft();
	Assert.assertTrue(actualResult);
    }

    @Then("^Room \"([^\"]*)\" is displayed on Home Page$")
    public void room_is_displayed_on_Home_Page(String roomName)
	    throws Throwable {
	NavigationPage navigation = new NavigationPage();
	HomePage homePage = new HomePage();

	navigation.topMenu.clickOnHomeButton();

	boolean actualResult = homePage.verifyRoomNameDisplayed(roomName);

	Assert.assertTrue(actualResult);
    }

    @Then("^The correct time left should be displayed according the current meeting \"([^\"]*)\" in room \"([^\"]*)\"$")
    public void verifyTimeLeftOnCurrentMeeting(String subject, String roomName) throws Throwable {
	NavigationPage navigation = new NavigationPage();
	HomePage homePage = new HomePage();

	navigation.topMenu.clickOnHomeButton();
	boolean actualResult = homePage.verifyTimeLeftOfCurrentMeeting();
	deleteMeetingByAPI(roomName, subject);
	Assert.assertTrue(actualResult,
		"The Time Left displayed is not according the current Meeting");
    }

    @Then("^The correct time left should be displayed according the next meeting \"([^\"]*)\" in room \"([^\"]*)\"$")
    public void verifyTimeLeftOnNextMeeting(String subject, String roomName) throws Throwable {
	NavigationPage navigation = new NavigationPage();
	HomePage homePage = new HomePage();
	
	navigation.topMenu.clickOnHomeButton();
	boolean actualResult = homePage.verifyTimeLeftOfNextMeeting();
	deleteMeetingByAPI(roomName, subject);
	Assert.assertTrue(actualResult,
		"The Time Left displayed is not according the next Meeting");
    }
    
    @Then("^the meeting \"([^\"]*)\" is not displayed on Home page in room \"([^\"]*)\"$")
    public void meetingNotInTimeLine(String subject, String roomName) throws Throwable {
	SchedulerPage schedulerPage = new SchedulerPage();
	HomePage homePage = new HomePage();

	schedulerPage.topMenu.clickOnHomeButton();

	boolean actualResult = homePage.verifyMeetingInTimeLine(subject);

	Meeting meetingToDelete = MeetingAPIManager.getMeetingBySubject(
		roomName, subject);
	if (meetingToDelete != null) {
	    MeetingAPIManager.deleteRequest(roomName, meetingToDelete);
	}
	
	Assert.assertTrue(actualResult);
    }

    public void deleteMeetingByUI(String subject) throws AWTException {
	HomePage homePage = new HomePage();

	homePage.clickOnScheduleButton().displayAllDayOnTimeline()
		.clickOnMeeting(subject).clickOnRemoveButton()
		.setPassword(PropertiesReader.getExchangeOrganizerPwd())
		.clickOkButton();
    }

    public void deleteMeetingByAPI(String roomName, String subject)
	    throws AWTException, UnirestException {
	Meeting meetingToDelete = MeetingAPIManager.getMeetingBySubject(
		roomName, subject);
	MeetingAPIManager.deleteRequest(roomName, meetingToDelete);
    }
}
