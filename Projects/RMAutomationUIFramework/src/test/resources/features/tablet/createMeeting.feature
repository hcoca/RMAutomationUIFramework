Feature: Create Meeting

#Scenario01
Scenario: A new meeting is displayed on timeline when a new meeting is created
Given I am on Home Page of "Room045" room
When I create a meeting as "RoomManager2" organizer
And it meeting will be from "15:00:00.000" start time to "16:00:00.000" end time
And it has "Meet" as subject, "this is a description for new meeting" as description
And it has the following attendee "Administrator@roommanager.local"
Then validate that "Meet" has been created on timeline

#Scenario02
Scenario: A new meeting is not displayed on timeline when a meeting is removed
Given I have a meeting with this subject "Meet" on "Room045" room
And I am on Home Page of "Room045" room
When I remove "Meet" meeting
Then validate that "Meet" has been removed and it is not on timeline

#Scenario03
Scenario: Timeline displays all day when zoom is reduced over timeline.
Given I am on Home Page of "Room045" room
When I reduce timeline for watching all day
Then validate that all day is displayed on timeline

#Scenario04
Scenario: An error message is displayed when a meeting is created without subject.
Given I am on Home Page of "Room031" room
When I want to create a meeting without subject
Then validate that an error message is displayed for subject