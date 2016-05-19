Feature: Tablet Home Page

Scenario: All meetings are displayed on Home Page time line when it is opened
Given I have a meeting with "TestMeeting" subject and "RoomManager2" as organizer on "Room040" room
When I am on Home Page of "Room040" room
Then All meetings of "Room040" room are displayed on home time line even "TestMeeting" meeting

Scenario: Current meeting subject  is displayed on Home Page when it is created
Given I am on Home Page of "Room040" room
When I create a meeting with "TestMeeting" subject and "RoomManager2" as organizer 
Then The meeting "TestMeeting" is displayed on Home Page as current

Scenario: Current meeting organizer is displayed on Home Page when it is created
Given I am on Home Page of "Room040" room
When I create a meeting with "TestMeeting" subject and "RoomManager2" as organizer 
Then The organizer "RoomManager2" of current meeting "TestMeeting" is displayed on Home

Scenario: Next meeting subject is displayed on Home Page when it is created 
Given I am on Home Page of "Room040" room
When I create a meeting as "RoomManager2" organizer
And it meeting will be from "23:00:00.000" start time to "23:50:00.000" end time
And it has "TestMeeting" as subject, "this is a description meeting" as description
And it has the following attendee "Administrator@roommanager.local"
Then The meeting "TestMeeting" is displayed on Home Page as next

Scenario: Next meeting organizer  is displayed on Home Page when it is created
Given I am on Home Page of "Room040" room
When I create a meeting as "RoomManager2" organizer
And it meeting will be from "23:00:00.000" start time to "23:50:00.000" end time
And it has "TestMeeting" as subject, "this is a description meeting" as description
And it has the following attendee "Administrator@roommanager.local"
Then The organizer "RoomManager2" of next meeting "TestMeeting" is displayed on Home

Scenario: The name of the room is displayed on Home Page when it is configured
Given I am on Home Page of "Room040" room
When I configure "Room036" as tablet room
Then Room "Room036" is displayed on Home Page

Scenario: Time left until end of the day is displayed on Home Page when there is no meeting
When I am on Home Page of "Room040" room
Then The correct time left until end of the day should be displayed on Home page