package org.fundacionjala.automation.scenario.steps.tablet.editMeeting;

import org.fundacionjala.automation.framework.utils.api.managers.MeetingAPIManager;
import org.fundacionjala.automation.framework.utils.api.objects.admin.Meeting;
import org.fundacionjala.automation.framework.utils.common.PropertiesReader;
import org.fundacionjala.automation.framework.utils.common.RMGenerator;

import cucumber.api.java.en.Given;

public class EditMeetingGivenSteps {
    
    @Given("^I had a created meeting with \"([^\"]*)\" organizer, with \"([^\"]*)\" subject in the \"([^\"]*)\" room$")
    public void IHadAMeetingCreated(String organizer, String subject, 
	    String room) throws Throwable {
	
	String startDate = RMGenerator.getIsoTime(0);
	String endDate = RMGenerator.getIsoTime(1);
	String roomEmail = room + "@" + PropertiesReader.getExchangeDomain();
       Meeting meeting = new Meeting(organizer, subject, startDate, endDate, 
	       room, roomEmail, roomEmail, PropertiesReader.getExchangeInviteUser());
       MeetingAPIManager.postRequest(room, meeting);
    }
}
