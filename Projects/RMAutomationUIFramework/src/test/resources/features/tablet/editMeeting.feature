Feature: Edit meeting
	I want to edit alll fields allowed in a created meeting

Scenario: The organizer is not able to modified in a meeting created
Given I had a created meeting with "RoomManager2" organizer, with "Maria" subject in the "Room082" room
 When I display the meeting "Maria" in the "Room082" room
 Then Validate that the organizer is not able to modified


