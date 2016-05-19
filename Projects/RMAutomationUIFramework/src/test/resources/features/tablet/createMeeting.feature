Feature: Create Meeting

#Scenario01
Scenario: A new meeting is displayed on timeline when a new meeting is created
Given I am on Home Page of "Room045" room
When I create a meeting as "RoomManager2" organizer
And it meeting will be from "15:00:00.000" start time to "16:00:00.000" end time
And it has "newMeeting" as subject, "this is a description for new meeting" as description
And it has the following attendee "Administrator@roommanager.local"
Then validate that "newMeeting" has been created on timeline

#Scenario02
Scenario: A new meeting is not displayed on timeline when a meeting is removed
Given I have a meeting with this subject "removeMeet" on "Room045" room
And I am on Home Page of "Room045" room
When I remove "removeMeet" meeting
Then validate that "removeMeet" has been removed and it is not on timeline

#Scenario03
Scenario: Timeline displays all day when zoom is reduced over timeline.
Given I am on Home Page of "Room045" room
When I reduce timeline for watching all day
Then validate that all day is displayed on timeline