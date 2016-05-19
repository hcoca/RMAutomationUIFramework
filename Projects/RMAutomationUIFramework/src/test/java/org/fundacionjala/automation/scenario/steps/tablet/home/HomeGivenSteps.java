package org.fundacionjala.automation.scenario.steps.tablet.home;

import java.util.ArrayList;
import java.util.List;

import org.fundacionjala.automation.framework.utils.api.managers.MeetingAPIManager;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Meeting;
import org.fundacionjala.automation.framework.utils.common.PropertiesReader;
import org.fundacionjala.automation.framework.utils.common.RMGenerator;

import cucumber.api.java.en.Given;

public class HomeGivenSteps {

    @Given("^I have a meeting with \"([^\"]*)\" subject and \"([^\"]*)\" as organizer on \"([^\"]*)\" room$")
    public void addMeetingByAPI(String subject, String organizer, String room)
	    throws Throwable {
	List<String> attendees = new ArrayList<String>();
	attendees.add(PropertiesReader.getExchangeInviteMail());
	Meeting meeting = new Meeting(organizer, subject,
		RMGenerator.getIsoTime(0), RMGenerator.getIsoTime(1), room,
		room + "@" + PropertiesReader.getExchangeDomain(), 
		room + "@" + PropertiesReader.getExchangeDomain(),
		attendees);
	MeetingAPIManager.postRequest(room, meeting);
    }
    
    @Given("^I have a meeting with \"([^\"]*)\" subject and \"([^\"]*)\" as organizer on \"([^\"]*)\" room in the next hour$")
    public void addNextMeetingByAPI(String subject, String organizer, String room)
	    throws Throwable {
	List<String> attendees = new ArrayList<String>();
	attendees.add(PropertiesReader.getExchangeInviteMail());
	Meeting meeting = new Meeting(organizer, subject,
		RMGenerator.getIsoTime(1), RMGenerator.getIsoTime(2), room,
		room + "@" + PropertiesReader.getExchangeDomain(), 
		room + "@" + PropertiesReader.getExchangeDomain(),
		attendees);
	MeetingAPIManager.postRequest(room, meeting);
    }
}
