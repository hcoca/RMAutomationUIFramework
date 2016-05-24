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

#Scenario05
Scenario: An error message is displayed when a meeting is created with invalid password
Given I am on Home Page of "Room033" room
When I want to create a meeting as "RoomManager2" and this password "incorrect"
Then validate that an error message is displayed with invalid credentials

#Scenario06
Scenario: An error message is displayed when a meeting is created with invalid username
Given I am on Home Page of "Room033" room
When I want to create a meeting as "incorrectUser" and this password "Control*123"
Then validate that an error message is displayed with invalid credentials

#Scenario07
Scenario: A meeting is not created when it is created over interval time of other meeting
Given I have a meeting created on "Room057" as "RoomManager2" from "08:00:00.000Z" start time to "09:00:00.000Z" end time with "Meet" subject
And I am on Home Page of "Room057" room
When I want to create a meeting over interval time of other meeting  from "00:10:00.000" start time to "23:50:00.000" end time with "Over" subject
Then validate that an error message is displayed with conflict of time interval

#Scenario08
Scenario: Meeting only is created for current date
Given I am on Home Page of "Room045" room
When I try to create a meeting for "2016/05" date
Then Validate that start time date and end time date are disabled to edit

#Scenario09
Scenario: Timeline displays  ten minutes interval when zoom is extendest.
Given I am on Home Page of "Room077" room
When I extend the time line.
Then validate that the timeline displays time in intervals of ten minutes

#Scenario10
Scenario: Timeline show a created meeting when user moves timeline up to it
Given I am on Home Page of "Room0165" room
When I create a meeting as "RoomManager2" organizer
And it meeting will be from "08:30:00.000" start time to "10:30:00.000" end time
And it has "Meet" as subject, "this is a description for new meeting" as description
And it has the following attendee "Administrator@roommanager.local"
Then validate that "Meet" meeting is found on timeline