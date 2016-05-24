
@search
Feature: Search rooms

@searchfeature1
Scenario: Display advanced search option
Given I am on search page
When I click on advanced option button 
Then I see the advanced option with all field to search

@searchfeature2
Scenario: Filter rooms by name
Given I am on search page
When I click on advanced option button
And I set the room name field 
Then I see all rooms that match with the room name criteria

@searchfeature3
Scenario: Display Schedule page
Given I am on search page
When I click on Room "Room109"
Then I see the the Schedule page of the room "Room109" 

@searchfeature4
Scenario: Filter rooms by location
Given I am in location page of Room Manager Admin
And I associate the location "loc-jalasoft" to "Room004"
When I go to search page of Room Manager tablet
And I filter rooms by the location "loc-jalasoft"
Then I see the "Room004" listed in the results

@searchfeature5
Scenario: Clear fields of search advanced
Given I am on search page
When I filter rooms by the location "<All>" 
And I set the room name field 
And I press the clear button
Then I verify that all fields of the advanced option is cleared

@searchfeature6
Scenario: Filter by capacity
Given I am on the conference rooms page of Room Manager
When I change the capacity of the "Room001" for "15" 
And I go to search page of Room Manager tablet
And I set the Minimun capacity field to "20"
Then I verify that "Room001" is not listed

@searchfeature7
Scenario: All meetings of the room are displayed aftering filter by name
Given I have a meeting with this subject "Meet" on "Room008" room
When I am on search page
And I set the room name for "Room008"
Then I validate that the meeting with subject "Meet" is displayed
