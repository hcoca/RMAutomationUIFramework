Feature: Settings

#scenario 1
Scenario: Verify that the connection is successful when a correct "Service URL"   is inserted.
Given I insert the Service URL parameter on Connection-Tablet page
Then A message that describes that the connection has been successful should be displayed
  
#scenario 2
Scenario: Verify that all rooms are displayed after the connection has been successful
Given I insert the Service URL parameter on Connection-Tablet page
When I go to Navigation page
Then The list of Conference Rooms should have the corresponding rooms

#scenario 3
Scenario: Verify that when a room in the "Default Conference Room" parameter is selected the connection with this room is successful
Given I insert the Service URL parameter on Connection-Tablet page
When I select "Room001" room in the Default Conference Room parameter
Then A message that describes that the connection with the room has been successful should be displayed 

#scenario 4
Scenario: Verify that when the "Connection" option is selected the page corresponding is displayed
Given I insert the Service URL parameter on Connection-Tablet page
When I go to Connection page
Then The Connection page should be displayed with the elements corresponding

#scenario 5
Scenario: Verify that when the "Navigation" option is selected the page corresponding is displayed
Given I insert the Service URL parameter on Connection-Tablet page
When I go to Navigation page
Then The Navigation page should be displayed with the elements corresponding

#scenario 6
Scenario: Verify that when a type of filter is inserted in "Search" option on "Default Conference Room" parameter it displays the results according the filter inserted
Given I insert the Service URL parameter on Connection-Tablet page
When I go to Navigation page
And I insert a kind of filter "07" on the Default Conference Room parameter
Then The results displayed should be according the filter inserted "07"

