
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






