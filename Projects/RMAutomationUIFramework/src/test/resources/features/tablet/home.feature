Feature: Tablet Home Page

Scenario: All meetings are displayed on Home Page time line when it is opened
Given I have a meeting with "TestMeeting" subject and "Ariel" as organizer on "Room003" room
When I am on Home Page of "Room003" room
Then All meetings of "Room003" room are displayed on home time line even "TestMeeting" meeting

Scenario: Current meeting subject  is displayed on Home Page when it is created
Given I am on Home Page of "Room004" room
When I create a meeting with "TestMeeting" subject and "RoomManager2" as organizer 
Then The meeting "TestMeeting" is displayed on Home Page as current

Scenario: Time left until end of the day is displayed on Home Page when there is no meeting
When I am on Home Page of "Room004" room
Then The correct time left until end of the day should be displayed on Home page

Scenario: Current meeting organizer is displayed on Home Page when it  is created
Given I am on Home Page of "Room003" room
When I create a meeting with "TestMeeting" subject and "Ariel" as organizer 
Then The meeting "TestMeeting" is displayed on Home Page as current

Scenario: Current meeting organizer is displayed on Home Page when it  is created
Given I am on Home Page of "Room003" room
When I create a meeting with "TestMeeting" subject and "Ariel" as organizer 
Then The organizer "Ariel" of current meeting "TestMeeting" is displayed on Home
