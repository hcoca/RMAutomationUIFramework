Feature: Edit meeting
	I want to edit alll fields allowed in a created meeting

#Scenario 11 This work only with times o'clock, hours positive integer
@DeleteMeeting
 Scenario Outline: The schedule of a created meeting is modified doing drag and drop
Given I have a created meeting from "<start>" to "<end>", with "Meeting Scheduled" subject in the "Room201" room
When I modify the schedule in the "Meeting Scheduled" meeting on "Room201" drag and drop the whole meeting to the "<direction>" "<hours>" hour 
Then Validate that in the "Room201" the schedule start "<start>" and end time "<end>" on "Meeting Scheduled" were "<status>" "<hours>" hour
Examples:
|start |end |direction	|status	|hours |
|08:00	|09:00	|left		|ahead	|1	|
|08:00	|09:00	|right	|delayed	|1	|
#Scenario 12 This work only with times o'clock, at least 2 hours of duration, hours must be positive integer
@DeleteMeeting 
 Scenario Outline: The start time in a meeting created is modified when the start time in time line is pulled
Given I have a created meeting from "<start>" to "<end>", with "Meeting Start pulled to <direction>" subject in the "Room203" room
When I modify the schedule in the "Meeting Start pulled to <direction>" meeting on "Room203" drag and drop the start time in "<direction>" "<hours>" hour 
Then Validate that in the "Room203" the schedule start "<start>" with end time "<end>" on "Meeting Start pulled to <direction>" were "<status>" "<hours>" hour
Examples:
|start |end |direction	|status	|hours |
|08:00	|10:00	|left		|ahead	|1	|
|08:00	|10:00	|right	|delayed	|1	|

@DeleteMeeting
 Scenario Outline: The end time in a meeting created is modified when the end time in time line is pulled
Given I have a created meeting from "<start>" to "<end>", with "Meeting End pulled to <direction>" subject in the "Room207" room
When I modify the schedule in the "Meeting End pulled to <direction>" meeting on "Room207" drag and drop the end time in "<direction>" "<hours>" hour 
Then Validate that in the "Room207" the schedule end "<end>"  with start time "<start>" on "Meeting End pulled to <direction>" were "<status>" "<hours>" hour
Examples:
|start |end |direction	|status	|hours |
|08:00	|10:00	|left		|ahead	|1	|
|08:00	|10:00	|right	|delayed	|1	|
 