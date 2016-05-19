Feature: Tablet Home Page

Scenario: All meetings are displayed on Home Page time line when it is opened
Given I have a meeting with "Meeting" subject and "RoomManager2" as organizer on "Room020" room
When I am on Home Page of "Room020" room
Then All meetings of "Room020" room are displayed on home time line even "Meeting" meeting

Scenario: Current meeting subject  is displayed on Home Page when it is created
Given I am on Home Page of "Room020" room
When I create a meeting with "Meeting" subject and "RoomManager2" as organizer 
Then The meeting "Meeting" is displayed on Home Page as current

Scenario: Current meeting organizer is displayed on Home Page when it is created
Given I am on Home Page of "Room020" room
When I create a meeting with "Meeting" subject and "RoomManager2" as organizer 
Then The organizer "RoomManager2" of current meeting "Meeting" is displayed on Home

Scenario: Next meeting subject is displayed on Home Page when it is created 
Given I am on Home Page of "Room020" room
When I create a meeting as "RoomManager2" organizer
And it meeting will be from "23:00:00.000" start time to "23:50:00.000" end time
And it has "Meeting" as subject, "this is a description meeting" as description
And it has the following attendee "Administrator@roommanager.local"
Then The meeting "Meeting" is displayed on Home Page as next

Scenario: Next meeting organizer  is displayed on Home Page when it is created
Given I am on Home Page of "Room020" room
When I create a meeting as "RoomManager2" organizer
And it meeting will be from "23:00:00.000" start time to "23:50:00.000" end time
And it has "Meeting" as subject, "this is a description meeting" as description
And it has the following attendee "Administrator@roommanager.local"
Then The organizer "RoomManager2" of next meeting "Meeting" is displayed on Home

Scenario: The name of the room is displayed on Home Page when it is configured
Given I am on Home Page of "Room020" room
When I configure "Room036" as tablet room
Then Room "Room036" is displayed on Home Page

Scenario: The new subject of  the current meeting  is displayed on Home Page when it is updated
Given I have a meeting with "Meeting" subject and "RoomManager2" as organizer on "Room020" room
	And I am on Home Page of "Room020" room
When I update "Meeting" meeting with new subject "Updated" 
Then The meeting "Updated" is displayed on Home Page as current

Scenario: The new subject of  a next meeting  is displayed on Home Page when it is updated
Given I have a meeting with "Meeting" subject and "RoomManager2" as organizer on "Room020" room in the next hour
	And I am on Home Page of "Room020" room
When I update "Meeting" meeting with new subject "Updated" 
Then The meeting "Updated" is displayed on Home Page as next

Scenario: Time left until end of the day is displayed on Home Page when there is no meeting
When I am on Home Page of "Room020" room
Then The correct time left until end of the day should be displayed on Home page

Scenario: Time left until current meeting finish is displayed on Home Page when there is a current meeting
Given I am on Home Page of "Room181" room 
When I create a meeting with "Meet" subject and "RoomManager2" as organizer 
Then The correct time left should be displayed according the current meeting "Meet" 
