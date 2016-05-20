Feature: Tablet Home Page

Scenario: All meetings are displayed on Home Page time line when it is opened
Given I have a meeting with "Meet1" subject and "RoomManager2" as organizer on "Room092" room
When I am on Home Page of "Room092" room
Then All meetings of "Room092" room are displayed on home time line even "Meet1" meeting

Scenario: Current meeting subject  is displayed on Home Page when it is created
Given I am on Home Page of "Room092" room
When I create a meeting with "Meet2" subject and "RoomManager2" as organizer 
Then The meeting "Meet2" is displayed on Home Page as current in room "Room092"

Scenario: Current meeting organizer is displayed on Home Page when it is created 
Given I am on Home Page of "Room092" room
When I create a meeting with "Meet3" subject and "RoomManager2" as organizer 
Then The organizer "RoomManager2" of current meeting "Meet3" is displayed on Home in room "Room092"

Scenario: Next meeting subject is displayed on Home Page when it is created
Given I am on Home Page of "Room092" room
When I create a meeting as "RoomManager2" organizer
And it meeting will be from "23:00:00.000" start time to "23:50:00.000" end time
And it has "Meet4" as subject, "this is a description meeting" as description
And it has the following attendee "Administrator@roommanager.local"
Then The meeting "Meet4" is displayed on Home Page as next in room "Room092"

Scenario: Next meeting organizer  is displayed on Home Page when it is created
Given I am on Home Page of "Room092" room
When I create a meeting as "RoomManager2" organizer
And it meeting will be from "23:00:00.000" start time to "23:50:00.000" end time
And it has "Meet5" as subject, "this is a description meeting" as description
And it has the following attendee "Administrator@roommanager.local"
Then The organizer "RoomManager2" of next meeting "Meet5" is displayed on Home in room "Room092"

Scenario: The name of the room is displayed on Home Page when it is configured
Given I am on Home Page of "Room092" room
When I configure "Room055" as tablet room
Then Room "Room055" is displayed on Home Page

Scenario: The new subject of  the current meeting  is displayed on Home Page when it is updated
Given I have a meeting with "Meet6" subject and "RoomManager2" as organizer on "Room092" room
	And I am on Home Page of "Room092" room
When I update "Meet6" meeting with new subject "Updated" 
Then The meeting "Updated" is displayed on Home Page as current in room "Room092"

Scenario: The new subject of  a next meeting  is displayed on Home Page when it is updated
Given I have a meeting with "Meet7" subject and "RoomManager2" as organizer on "Room092" room in the next hour
	And I am on Home Page of "Room092" room
When I update "Meet7" meeting with new subject "Updated" 
Then The meeting "Updated" is displayed on Home Page as next in room "Room092"

Scenario: Time left until end of the day is displayed on Home Page when there is no meeting
When I am on Home Page of "Room092" room
Then The correct time left until end of the day should be displayed on Home page

Scenario: Time left until current meeting finish is displayed on Home Page when there is a current meeting
Given I am on Home Page of "Room092" room 
When I create a meeting with "Meet8" subject and "RoomManager2" as organizer 
Then The correct time left should be displayed according the current meeting "Meet8" in room "Room092"

Scenario: Time left until room is available is displayed on Home Page when there is a next meeting
Given I am on Home Page of "Room092" room
When I create a meeting with "Meet" subject and "RoomManager2" as organizer in the next hour
Then The correct time left should be displayed according the next meeting "Meet" in room "Room092"
