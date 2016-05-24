Feature: Edit meeting
	I want to edit alll fields allowed in a created meeting

#Scenario 1
Scenario: The organizer is not able to modified in a meeting created
Given I have a created meeting with "Pizza" subject in the "Room082" room 
 When I display the meeting "Pizza" in the "Room082" room
 Then Validate that the organizer is not able to modified

#Scenario 2
Scenario: The attendees text field is modified in a meeting created
Given I had a created meeting in the "Room082" room, with "New Project RM" subject and attendees: 
|RoomManager5@roommanager.local|
|RoomManager6@roommanager.local|
When I modified the meeting "New Project RM" in the "Room082" room  adding the attendees
|RoomManager7@roommanager.local|
|RoomManager8@roommanager.local|
  And Save the changes
 Then Validate that the attendees has been modified with
|RoomManager5@roommanager.local|
|RoomManager6@roommanager.local|
|RoomManager7@roommanager.local|
|RoomManager8@roommanager.local|

#Scenario 3
@IMP-DisableImpersonationUI @DeleteMeeting
Scenario Outline: A meeting created is modified when the information meeting  is edited using impersonation
Given I have a created in the "Room013" room with subject "meeting QADEV06"
 And the schedule with start time: "09:00" end time "10:30"
 And attendees
|RoomManager1@roommanager.local|
|RoomManager4@roommanager.local|
 And body "This is a new meeting"
 And impersonation is enabled by UI
When I modify the "<field>" with "<value>" in the "meeting QADEV06" meeting in "Room013" room
 And Confirm the changes with the user "RoomManager1" and password "Control*123"
Then Validate that the "<field>" has been modified with the value "<value>" of the "meeting QADEV06" 

#field to modify (subject, startTime, endTime, attendees, body)
Examples:
|field		|value													|
|subject	|this is a new subject					|
|startTime|10:00													|
|endTime	|12:00													|
|attendees|RoomManager5@roommanager.local	|
|body			|This meeting has been modified	|

#Scenario 4
@DeleteMeetingOutOfOrder
Scenario: A message is displayed when a meeting is modified over the same time of out of order time
Given I have a created meeting from "08:00:00.000" to "09:00:00.000", with "Meeting QDAEV06" subject in the "Room002" room
Given the "Room002" room is out of order
 When I want to modify the meeting  "Meeting QDAEV06" in the "Room002" room over the same time of out of order
 Then validate that an error message is displayed with conflict of time interval
 
#Scenario 5
@DeleteMeeting
Scenario: The schedule in a meeting is modified when the new time is entered
Given I have a created meeting with "meeting QADEV06" subject in the "Room132" room
When I modify the "meeting QADEV06" meeting in "Room132" room from "18:00" to "19:30"
Then validate that "meeting QADEV06" meeting has been modified with schedule from "18:00" to "19:30"

#Scenario 6
@DeleteMeeting
Scenario: The subject text field is modified when it is edited
Given I have a created meeting with "meeting QADEV06" subject in the "Room112" room
When I modify the "meeting QADEV06" meeting in "Room112" room with new subject "subject modified"
Then validate that "subject modified" subject has been modified
 
#Scenario 7
@DeleteMeeting
Scenario: The body text field is modified when it is edited
Given I have a created meeting with "meeting QADEV06" subject in the "Room111" room
When I modify the "meeting QADEV06" meeting in "Room111" room with new body "This is a meeting modified in body field"
Then validate that the body "This is a meeting modified in body field" has been modified in "meeting QADEV06"

#Scenario 8
@DeleteTwoMeetings
Scenario: An error message is displayed when a meeting is modified over the same interval than other meeting
Given I have a created meeting with "first meeting" subject in the "Room111" room 
  And I have a created meeting from "08:00:00.000" to "09:00:00.000", with "second meeting" subject in the "Room111" room
 When I want to modify the schedule of the meeting "first meeting" from "07:00" to "11:00"
 Then validate that an error message is displayed with conflict of time interval 
 