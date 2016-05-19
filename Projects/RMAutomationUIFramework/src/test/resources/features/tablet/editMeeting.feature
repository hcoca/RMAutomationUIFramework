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