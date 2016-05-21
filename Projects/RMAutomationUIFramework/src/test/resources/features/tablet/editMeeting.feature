Feature: Edit meeting
	I want to edit alll fields allowed in a created meeting

#Scenario: The organizer is not able to modified in a meeting created
#Given I had a created meeting with "RoomManager2" organizer, with "Maria" subject in the "Room082" room
# When I display the meeting "Maria" in the "Room082" room
# Then Validate that the organizer is not able to modified

#Scenario: A meeting created with impersonation is modified when the information meeting  is edited
#Given impersonation is enabled
#  And I had a created meeting with "RoomManager2" organizer, with "New meeting" subject in the "Room052" room
# When I edit the subject with "New subject to test", 

#Scenario 3
#Scenario: The attendees text field is modified in a meeting created
#Given I had a created meeting in the "Room082" room, with "Maria" subject and attendees: 
#|RoomManager5@roommanager.local|
#|RoomManager6@roommanager.local|
 
 #When I modified the meeting "Maria" in the "Room082" room  adding the attendees
#|RoomManager7@roommanager.local|
#|RoomManager8@roommanager.local|
#  And Save the changes
# Then Validate that the attendees has been modified with
#|RoomManager5@roommanager.local|
#|RoomManager6@roommanager.local|
#|RoomManager7@roommanager.local|
#|RoomManager8@roommanager.local|

#Scenario 4
Scenario Outline: A meeting created with impersonation is modified when the information meeting  is edited
Given impersonation is enabled
And I have a created in the "Room177" room with subject "New meeting"
And the schedule with start time: "23:00:00.000" end time "23:15:00.000"
And attendees
|RoomManager5@roommanager.local|
|RoomManager6@roommanager.local|
And body "This is a meeting created using impersonation"
And impersonate the meeting with the user "RoomManager5" and password "Control*123"
When I modify the "Field" with "value"
Then Validate that the "Field" has been modified with the value "value"

Examples:
|Field		|Value													|
|subject	|this is a new subject					|
#|StartTime|23:00:00.000										|
#|EndTime	|23:00:00.000										|
#|Attendees|RoomManager5@roommanager.local	|
