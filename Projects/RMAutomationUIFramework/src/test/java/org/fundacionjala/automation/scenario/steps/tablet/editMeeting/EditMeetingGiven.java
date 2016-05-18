package org.fundacionjala.automation.scenario.steps.tablet.editMeeting;

import org.fundacionjala.automation.framework.utils.api.managers.MeetingAPIManager;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Meeting;
import org.fundacionjala.automation.framework.utils.common.PropertiesReader;

import cucumber.api.java.en.Given;

public class EditMeetingGiven {
    @Given("^I had a created meeting with \"([^\"]*)\" organizer, with \"([^\"]*)\" subject in the \"([^\"]*)\" room$")
    public void IHadAMeetingCreated(String organizer, String subject, String room) throws Throwable {
	String startDate = "2016-05-18T22:22:00.000Z";
	String endDate = "2016-05-18T22:32:00.000Z";
	String roomEmail = room +"@roommanager.local";
       Meeting meeting = new Meeting(organizer, subject, startDate, endDate, 
	       room, roomEmail, roomEmail, PropertiesReader.getExchangeInviteUser());
       MeetingAPIManager.postRequest(room, meeting);
    }
}
