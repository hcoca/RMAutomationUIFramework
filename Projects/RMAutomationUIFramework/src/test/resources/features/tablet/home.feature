Feature: Tablet Home Page

Scenario: All meetings are displayed on Home Page time line when it is opened
Given I have a meeting with "Meet1" subject and "RoomManager2" as organizer on "Room121" room
When I am on Home Page of "Room121" room
Then All meetings of "Room121" room are displayed on home time line even "Meet1" meeting

Scenario: Current meeting subject  is displayed on Home Page when it is created
Given I am on Home Page of "Room111" room
When I create a meeting with "Meet2" subject and "RoomManager2" as organizer 
Then The meeting "Meet2" is displayed on Home Page as current in room "Room111"

Scenario: Current meeting organizer is displayed on Home Page when it is created 
Given I am on Home Page of "Room112" room
When I create a meeting with "Meet3" subject and "RoomManager2" as organizer 
Then The organizer "RoomManager2" of current meeting "Meet3" is displayed on Home in room "Room112"

Scenario: Next meeting subject is displayed on Home Page when it is created
Given I am on Home Page of "Room113" room
When I create a meeting as "RoomManager2" organizer
And it meeting will be from "23:00:00.000" start time to "23:50:00.000" end time
And it has "Meet4" as subject, "this is a description meeting" as description
And it has the following attendee "Administrator@roommanager.local"
Then The meeting "Meet4" is displayed on Home Page as next in room "Room113"

Scenario: Next meeting organizer  is displayed on Home Page when it is created
Given I am on Home Page of "Room114" room
When I create a meeting as "RoomManager2" organizer
And it meeting will be from "23:00:00.000" start time to "23:50:00.000" end time
And it has "Meet5" as subject, "this is a description meeting" as description
And it has the following attendee "Administrator@roommanager.local"
Then The organizer "RoomManager2" of next meeting "Meet5" is displayed on Home in room "Room114"

Scenario: The name of the room is displayed on Home Page when it is configured
Given I am on Home Page of "Room115" room
When I configure "Room067" as tablet room
Then Room "Room067" is displayed on Home Page

Scenario: The new subject of  the current meeting  is displayed on Home Page when it is updated
Given I have a meeting with "Meet6" subject and "RoomManager2" as organizer on "Room116" room
	And I am on Home Page of "Room116" room
When I update "Meet6" meeting with new subject "Updated" 
Then The meeting "Updated" is displayed on Home Page as current in room "Room116"

Scenario: The new subject of  a next meeting  is displayed on Home Page when it is updated
Given I have a meeting with "Meet7" subject and "RoomManager2" as organizer on "Room117" room in the next hour
	And I am on Home Page of "Room117" room
When I update "Meet7" meeting with new subject "Updated" 
Then The meeting "Updated" is displayed on Home Page as next in room "Room117"

Scenario: Time left until end of the day is displayed on Home Page when there is no meeting
When I am on Home Page of "Room118" room
Then The correct time left until end of the day should be displayed on Home page

Scenario: Time left until current meeting finish is displayed on Home Page when there is a current meeting
Given I am on Home Page of "Room119" room 
When I create a meeting with "Meet8" subject and "RoomManager2" as organizer 
Then The correct time left should be displayed according the current meeting "Meet8" in room "Room119"

Scenario: Time left until room is available is displayed on Home Page when there is a next meeting
Given I am on Home Page of "Room120" room
When I create a meeting with "Meet" subject and "RoomManager2" as organizer in the next hour
Then The correct time left should be displayed according the next meeting "Meet" in room "Room120"

Scenario: A meeting is not displayed on Home Page time line when it is deleted
Given I have a meeting with "Meeting" subject and "RoomManager2" as organizer on "Room122" room
	And I am on Home Page of "Room122" room
When I remove "Meet9" meeting
Then the meeting "Meet9" is not displayed on Home page in room "Room122"
