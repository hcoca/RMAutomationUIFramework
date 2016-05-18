package org.fundacionjala.automation.scenario.steps.tablet.home;

import org.fundacionjala.automation.framework.pages.tablet.home.HomePage;
import org.fundacionjala.automation.framework.pages.tablet.scheduler.SchedulerPage;
import org.fundacionjala.automation.framework.utils.common.PropertiesReader;
import org.testng.Assert;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

import cucumber.api.java.en.Then;

public class HomeThenSteps {

    @Then("^All meetings of \"([^\"]*)\" room are displayed on home time line even \"([^\"]*)\" meeting$")
    public void verifyTimeLineMeetings(String roomName, String subject) throws Throwable {
	SchedulerPage schedulerPage = new SchedulerPage();
        HomePage homePage = new HomePage();
        
        schedulerPage
        .topMenu
        .clickOnHomeButton();

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
	
	deleteMeeting(subject);
	
	Assert.assertTrue(actualResult);
    }
    
    @Then("^The meeting \"([^\"]*)\" is displayed on Home Page as current$")
    public void verifyCurrentMeeting(String subject) throws Throwable {
        SchedulerPage schedulerPage = new SchedulerPage();
        HomePage homePage = new HomePage();
        
        schedulerPage
        .topMenu
        .clickOnHomeButton();
        
        boolean actualResult = homePage.verifyCurrentMeetingDisplayed(subject);
        
        deleteMeeting(subject);
        
        Assert.assertTrue(actualResult);
    }
    
    @Then("^The organizer \"([^\"]*)\" of current meeting \"([^\"]*)\" is displayed on Home$")
    public void verifyCurrentMeetingOrganizer(String organizer, String subject) throws Throwable {
	SchedulerPage schedulerPage = new SchedulerPage();
        HomePage homePage = new HomePage();
        
        schedulerPage
        .topMenu
        .clickOnHomeButton();
        
        boolean actualResult = homePage.verifyCurrentOrganizerDisplayed(organizer);
      
        deleteMeeting(subject);
        
        Assert.assertTrue(actualResult);
    }
    
    public void deleteMeeting(String subject){
	HomePage homePage = new HomePage();
	
	homePage
        .clickOnScheduleButton()
        .clickOnMeeting(subject)
        .clickOnRemoveButton()
	.setPassword(PropertiesReader.getExchangeOrganizerPwd())
	.clickOkButton();
    }
    
    @Then("^The correct time left until end of the day should be displayed on Home page$")
    public void verifyCorrectLeftTime() throws Throwable {
	HomePage homePage = new HomePage();
        Assert.assertTrue(homePage.verifyTimeLeft());
    }
}
