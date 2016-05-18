Feature: Create Meeting

Scenario: A new meeting is displayed on timeline when a new meeting is created
Given I am on Home Page of "Room030" room
When I create a meeting as "RoomManager2" organizer
And it meeting will be from "18:00:00.000" start time to "18:10:00.000" end time
And it has "newMeeting" as subject, "this is a description for new meeting" as description
And it has the following attendee "Administrator@roommanager.local"
Then validate that "newMeeting" has been created on timeline