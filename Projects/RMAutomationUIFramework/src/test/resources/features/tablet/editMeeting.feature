Feature: Edit meeting
	I want to edit alll fields allowed in a created meeting

#Scenario 1
Scenario: The organizer is not able to modified in a meeting created
Given I had a created meeting with "RoomManager2" organizer, with "Maria" subject in the "Room082" room
 When I display the meeting "Maria" in the "Room082" room
 Then Validate that the organizer is not able to modified

#Scenario 2
Scenario: The attendees text field is modified in a meeting created
Given I had a created meeting in the "Room082" room, with "Maria" subject and attendees: 
|RoomManager5@roommanager.local|
|RoomManager6@roommanager.local|
When I modified the meeting "Maria" in the "Room082" room  adding the attendees
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
Given I have a created in the "Room004" room with subject "New meeting"
 And the schedule with start time: "09:00:00.000" end time "10:30:00.000"
 And attendees
|RoomManager1@roommanager.local|
|RoomManager4@roommanager.local|
 And body "This is a new meeting"
 And impersonation is enabled by UI
When I modify the "Field" with "value" in the "New meeting" meeting in "Room004" room
 And Confirm the changes with the user "RoomManager1" and password "Control*123"
Then Validate that the "Field" has been modified with the value "value" of the "New meeting" 

#field to modify (subject, startTime, endTime, attendees, body)
Examples:
|Field		|Value													|
|subject	|this is a new subject					|
|startTime|10:00:00.000										|
|endTime	|12:00:00.000										|
|attendees|RoomManager5@roommanager.local	|
|body			|This meeting has been modified	|
