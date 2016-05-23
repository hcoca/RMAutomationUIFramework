
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
When I set the room name field 
And I filter rooms by the location "<All>"
And I press the clear button
Then I verify that all fields of the advanced option is cleared



