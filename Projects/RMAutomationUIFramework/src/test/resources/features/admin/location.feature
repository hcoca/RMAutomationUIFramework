@location
Feature: Location Page

@location1 
Scenario: All locations are displayed on Locations page when it is opened
Given I have a location added with name: "Cochabamba_1", display name "Cbba-Location_1" and description "This is Cochabamba Location"
	And I am logged as "Administrator" with password "Control*123"
When I open locations page
Then All locations added are displayed even "Cochabamba_1" location

@location2 
Scenario: A location is displayed on Location page when it is added
Given I am logged as "Administrator" with password "Control*123"
When I add a new location with name: "Cochabamba_2", display name "Cbba-Location_2" and description "This is Cochabamba Location"
Then The location "Cochabamba_2" is displayed on location page

@location3
Scenario: A location’s  name changes are displayed on Location page when it is updated
Given I have a location added with name: "Cochabamba_3", display name "Cbba-Location_3" and description "This is Cochabamba Location"
	And I am logged as "Administrator" with password "Control*123"
When I update location "Cbba-Location_3" with name: "Cochabamba_3NameUpdated", display name "Cbba-Location_3" and description "This is Cochabamba Location"
Then The location "Cochabamba_3NameUpdated" is displayed on location page

@location5
Scenario: A location’s description changes are displayed on Update Location page when it is updated
Given I have a location added with name: "Cochabamba_5", display name "Cbba-Location_5" and description "This is Cochabamba Location"
	And I am logged as "Administrator" with password "Control*123"
When I update location "Cbba-Location_5" with name: "Cochabamba_5", display name "Cbba-Location_5" and description "This is Updated Location"
Then The description "This is Updated Location" of location "Cbba-Location_5" is displayed on update location page

@location6 
Scenario: A location’s parent changes are displayed on Update Location page when it is updated
Given I have a location added with name: "ParentCbba", display name "Cbba-Parent" and description "This is Parent Location"
	And I have a location added with name: "ChildPunata", display name "Cbba-Child" and description "This is Child Location"
	And I am logged as "Administrator" with password "Control*123"
When I update "Cbba-Child" location parent to "ParentCbba"
Then The parent name "ParentCbba" of location "Cbba-Child" is displayed on update location page
 
@location7    
Scenario: A location is not  displayed on Locations page when it is deleted
Given I have a location added with name: "Cochabamba_7", display name "Cbba-Location_7" and description "This is Cochabamba Location"
	And I am logged as "Administrator" with password "Control*123"
When I delete the location "Cbba-Location_7"
Then The location "Cochabamba_7" is not displayed on location page

@location8
Scenario: A locations is displayed on Location page when it is added as a child of another location
Given I have a location added with name: "ParentCbba", display name "Cbba-Parent" and description "This is Parent Location"
	And I am logged as "Administrator" with password "Control*123"
When I add a new location with name: "ChildPunata", display name "Cbba-Child" and parent "ParentCbba"
Then The location "ChildPunata" child of "ParentCbba" is displayed on location page

@location9
Scenario: A room is displayed on Location Association page  when it is associated with a new location
Given I am logged as "Administrator" with password "Control*123"
When I add a new location with name: "Cochabamba_9", display name "Cbba-Location_9" and associated room "Room001"
Then The room "Room001" is displayed on Location Association page as associated with "Cbba-Location_9" location

@location10
Scenario: A room is displayed on Location Association page when it is associated with an existent location
Given I have a location added with name: "Cochabamba_10", display name "Cbba-Location_10" and description "This is Cochabamba Location"
	And I am logged as "Administrator" with password "Control*123"
When I associate the location "Cbba-Location_10" with a room "Room004"
Then The room "Room004" is displayed on Location Association page as associated with "Cbba-Location_10" location

@location11
Scenario: A room is not displayed on ‘Location Association’ page  when the association with a location is removed
Given I have a location added with name: "Cochabamba_11", display name "Cbba-Location_11" and description "This is Cochabamba Location"
	And I am logged as "Administrator" with password "Control*123"
	And I associate the location "Cbba-Location_11" with a room "Room003"
When I delete the association between location "Cbba-Location_11" and "Room003"
Then The room "Room003" is not displayed on Location Association page as associated with "Cbba-Location_11" location
 
@location12
Scenario: The number of rooms associated displayed on Locations page increases when a room is associated
Given I have a location added with name: "Cochabamba_12", display name "Cbba-Location_12" and description "This is Cochabamba Location"
	And I am logged as "Administrator" with password "Control*123"
When I associate the location "Cbba-Location_12" with a room "Room004"
Then The number of associations on Location page has been increased by "Cochabamba_12" location association

@location4   
Scenario: A location’s display  name changes are displayed on Locations page when it is updated
Given I have a location added with name: "Cochabamba_4", display name "Cbba-Location_4" and description "This is Cochabamba Location"
	And I am logged as "Administrator" with password "Control*123"
When I update location "Cbba-Location_4" with name: "Cochabamba_4", display name "Cbba-Location_4-Updated" and description "This is Cochabamba Location"
Then The location display name "Cbba-Location_4-Updated" is displayed on location page

@location23
Scenario: A message is displayed on ‘Update Location’ page  when a location display name is updated to empty
Given I have a location added with name: "Cochabamba_3", display name "Cbba-Location_3" and description "This is Cochabamba Location"
	And I am logged as "Administrator" with password "Control*123"
When I update location "Cbba-Location_3" with name: "Cochabamba_3NameUpdated", display name "" and description "This is Cochabamba Location"
Then An error message should be displayed  

@location24
Scenario: An error message is displayed on ‘Update Location’ page when a location is updated with special characters in its name
Given I have a location added with name: "Cochabamba_3", display name "Cbba-Location_3" and description "This is Cochabamba Location"
	And I am logged as "Administrator" with password "Control*123"
When I update location "Cbba-Location_3" with name: "/*/*****", display name "Cbba-Location_2" and description "This is Cochabamba Location"
Then An error message should be displayed

@location26
Scenario: The quantity selected in page size is displayed in resource table
Given I have at least "200" locations created 
  And I am logged as "Administrator" with password "Control*123"
When I select "100" on location page size option 
Then Validate that the location table size is same than the option "100" selected 

@location27
Scenario: First Locations page is displayed when First Page button is pressed
Given I have at least "200" locations created 
  And I am logged as "Administrator" with password "Control*123"
When I select "50" on location page size option 
  When I select the First Page button
Then I validate if the corresonding page is displayed on Location page according the page size specified "200" and the page "1"    

@location28
Scenario: Last Locations page is displayed when Last Page button is pressed
Given I have at least "200" locations created 
  And I am logged as "Administrator" with password "Control*123"
When I select "50" on location page size option 
  When I select the Last Page button
Then I validate if the corresonding page is displayed on Location page according the page size specified "200" and the page "4"

@location29
Scenario: Previous Locations page is displayed when Previous Page button is pressed
Given I have at least "200" locations created 
  And I am logged as "Administrator" with password "Control*123"
When I select "50" on location page size option 
  And I set the page by "3" on location page 
  And I set the page by "2" on location page  
Then I validate if the corresonding page is displayed on Location page according the page size specified "100" and the page "2" 

@location30
Scenario: Next page is displayed in "Location" table when "Next Page" button is clicked.
Given I have at least "200" locations created  
  And I am logged as "Administrator" with password "Control*123"
When I select "100" on location page size option 
  And I set the page by "2" on location page  
Then I validate if the corresonding page is displayed on Location page according the page size specified "100" and the page "2" 

@location13
Scenario: The number of rooms associated displayed on Locations page decreases when a room association is removed
Given I have a location added with name: "Cochabamba_13", display name "Cbba-Location_13" and description "This is Cochabamba Location"
	And I am logged as "Administrator" with password "Control*123"
	And I associate the location "Cbba-Location_13" with a room "Room005"
When I delete the association between location "Cbba-Location_13" and "Room005"
Then The number of associations on Location page has been decreased by removing "Cochabamba_13" location association

@location14
Scenario: The number of roms of a parent location increases when a room is associated to its child locations
Given I have a location added with name: "Cochabamba_14", display name "Cbba-Location_14" and description "This is Cochabamba Location"
	And I am logged as "Administrator" with password "Control*123"
	And I add a new location with name: "Cochabamba_14_Child", display name "Cbba-Child" and parent "Cochabamba_14"
When I associate the location "Cbba-Child" with a room "Room006"
Then The number of associations on Location page has been increased by "Cochabamba_14" location association

@location15
Scenario: The number of roms of a parent location increases when a room is associated to its child locations
Given I have a location added with name: "Cochabamba_15", display name "Cbba-Location_15" and description "This is Cochabamba Location"
	And I am logged as "Administrator" with password "Control*123"
	And I add a new location with name: "Cochabamba_15_Child", display name "Cbba-Child" and parent "Cochabamba_15"
	And I associate the location "Cbba-Child" with a room "Room007"
When I delete the association between location "Cbba-Child" and "Room007"
Then The number of associations on Location page has been decreased by removing "Cochabamba_15" location association

@location16
Scenario: A message is displayed on Add Location  page when a new location is added with the same name than another location
Given I have a location added with name: "Cochabamba_16", display name "Cbba-Location_16" and description "This is Cochabamba Location"
	And I am logged as "Administrator" with password "Control*123"
When I add a new location with name: "Cochabamba_16", display name "Cbba-Location_Diferent" and description "This is Cochabamba Location"
Then An error message should be displayed

@location31
Scenario: Location is not created after canceling the creation process
Given I am logged as "Administrator" with password "Control*123" 
When I tried to add a new location with name: "Fundacion", display name "Cbba-Fundacion" and description "This is Cochabamba Location"
And I press the cancel button
Then The location "Fundacion" is not displayed on location page

@location32
Scenario: Location is not updated 
Given I have a location added with name: "Cochabamba_3", display name "Cbba-Location_3" and description "This is Cochabamba Location"
And I am logged as "Administrator" with password "Control*123"
When I try to update location "Cbba-Location_3" with name: "Fundacion", display name "Cbba-Location_2" and description "This is Fundacion Location"
And I press the cancel button
Then The location "Fundacion" is not displayed on location page

@location33
Scenario: Location is not deleted
Given I have a location added with name: "Cochabamba_3", display name "Cochabamba_3" and description "This is Cochabamba Location"
And I am logged as "Administrator" with password "Control*123"
When I try to delete the location "Cochabamba_3"
And I press the cancel button on delete page
Then The location "Cochabamba_3" is displayed on location page

@location17
Scenario: A message is displayed on Add Location page when a new location is added with empty name
Given I am logged as "Administrator" with password "Control*123"
When I add a new location with name: "", display name "Cbba-Location_17" and description "This is Cochabamba Location"
Then An error message should be displayed

@location18
Scenario: A message is displayed on Add Location page when a location is added with empty display name
Given I am logged as "Administrator" with password "Control*123"
When I add a new location with name: "Cochabamba_18", display name "" and description "This is Cochabamba Location"
Then An error message should be displayed

@location19
Scenario: A message is displayed on Add Location page when a new location is added with special characters in its name
Given I am logged as "Administrator" with password "Control*123"
When I add a new location with name: "/\?%*:|", display name "Cbba-Location_19" and description "This is Cochabamba Location"
Then An error message should be displayed		
 
@location21
Scenario: A message is displayed on ‘Update Location’ page when a location name is updated to already existent name
Given I have a location added with name: "Cochabamba_21", display name "Cbba-Location_21" and description "Description1"		
	And I have a location added with name: "Other_Cochabamba", display name "Other_Cbba" and description "Description2"
	And I am logged as "Administrator" with password "Control*123"
When I update location "Other_Cbba" with name: "Cochabamba_21", display name "Other_Cbba_21" and description "Cochabamba Location"		
Then An error message should be displayed		

@location22
Scenario: A message is displayed on ‘Update Location’ page  when a location name is updated to empty		
Given I have a location added with name: "Cochabamba_22", display name "Cbba-Location_22" and description "This is Cochabamba Location"		
	And I am logged as "Administrator" with password "Control*123"		
When I update location "Cbba-Location_22" with name: "", display name "Cbba-Location_22Updated" and description "Description"		
Then An error message should be displayed

@location20
Scenario: A message is displayed on Add Location page when a new location is added with special characters in its name
Given I am logged as "Administrator" with password "Control*123"		
When I add a new location with a 258 characters name and diaplay name "Cbba-Location_20"
Then An error message should be displayed

@location25
Scenario: An error message is displayed on ‘Update Location’ page when a location is updated with name length greater than 255 characters
Given I have a location added with name: "Cochabamba_25", display name "Cbba-Location_25" and description "This is Cochabamba Location"
	And I am logged as "Administrator" with password "Control*123"
When I update location "Cbba-Location_25" with a 258 characters name and diaplay name "Cbba-Location_20"
Then An error message should be displayed 
