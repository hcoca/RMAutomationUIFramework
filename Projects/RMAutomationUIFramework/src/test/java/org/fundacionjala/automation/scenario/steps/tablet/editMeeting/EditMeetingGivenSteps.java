package org.fundacionjala.automation.scenario.steps.tablet.editMeeting;

import java.util.ArrayList;
import java.util.List;
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
	List<String> attendees = new ArrayList<String>();
	attendees.add(PropertiesReader.getExchangeInviteMail());
	
       Meeting meeting = new Meeting(organizer, subject, startDate, endDate, 
	       room, roomEmail, roomEmail,attendees );
       MeetingAPIManager.postRequest(room, meeting);
    }
    
    @Given("^I had a created meeting in the \"([^\"]*)\" room, with \"([^\"]*)\" subject and attendees:$")
    public void i_had_a_created_meeting_in_the_room_with_subject_and_attendees(String roomName, String subject, List<String> attendees) throws Throwable {
	String startDate = RMGenerator.getIsoTime(0);
	String endDate = RMGenerator.getIsoTime(1);
	String roomEmail = roomName + "@" + PropertiesReader.getExchangeDomain();
	
       Meeting meeting = new Meeting(PropertiesReader.getExchangeOrganizerUser(), subject, startDate, endDate, 
	       roomName, roomEmail, roomEmail,attendees );
       MeetingAPIManager.postRequest(roomName, meeting);
        
    }
}
