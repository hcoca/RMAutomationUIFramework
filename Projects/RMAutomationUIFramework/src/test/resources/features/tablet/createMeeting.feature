Feature: Conference Room

Scenario: A new meeting is displayed on timeline when a new meeting is created
Given I am on Home Page of "Room010" room
When I create a meeting as "RoomManager2" for this time with "newMeeting" subject, "Administrator@roommanager.local" attendee and "this is the body" body
Then validate that "newMeeting" has been created on timeline