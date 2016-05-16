Feature: Location Page

Scenario: All locations are displayed on Locations page when it is opened
Given I have a location added with name: "Cochabamba", display name "Cbba-Location" and description "This is Cochabamba Location"
	And I am logged as "Administrator" with password "Control*123"
When I open locations page
Then All locations added are displayed even "Cochabamba" location
   
Scenario: A location is displayed on Location page when it is added
Given I am logged as "Administrator" with password "Control*123"
When I add a new location with name: "Cochabamba", display name "Cbba-Location" and description "This is Cochabamba Location"
Then The location "Cochabamba" is displayed on location page
    
Scenario: A location’s  name changes are displayed on Location page when it is updated
Given I have a location added with name: "Cochabamba", display name "Cbba-Location" and description "This is Cochabamba Location"
	And I am logged as "Administrator" with password "Control*123"
When I update location "Cbba-Location" with name: "CochabambaNameUpdated", display name "Cbba-Location" and description "This is Cochabamba Location"
Then The location "CochabambaNameUpdated" is displayed on location page    
    
Scenario: A location’s display  name changes are displayed on Locations page when it is updated
Given I have a location added with name: "Cochabamba", display name "Cbba-Location" and description "This is Cochabamba Location"
	And I am logged as "Administrator" with password "Control*123"
When I update location "Cbba-Location" with name: "Cochabamba", display name "Cbba-Location-Updated" and description "This is Cochabamba Location"
Then The location display name "Cbba-Location-Updated" is displayed on location page
    
Scenario: A location’s description changes are displayed on Update Location page when it is updated
Given I have a location added with name: "Cochabamba", display name "Cbba-Location" and description "This is Cochabamba Location"
	And I am logged as "Administrator" with password "Control*123"
When I update location "Cbba-Location" with name: "Cochabamba", display name "Cbba-Location" and description "This is Updated Location"
Then The description "This is Updated Location" of location "Cbba-Location" is displayed on update location page
    
Scenario: A location’s parent  changes are displayed on Update Location page when it is updated
Given I have a location added with name: "ParentCbba", display name "Cbba-Parent" and description "This is Parent Location"
	And I have a location added with name: "ChildPunata", display name "Cbba-Child" and description "This is Child Location"
	And I am logged as "Administrator" with password "Control*123"
When I update "Cbba-Child" location parent to "ParentCbba"
Then The parent name "ParentCbba" of location "Cbba-Child" is displayed on update location page 
    
Scenario: A location is not  displayed on Locations page when it is deleted
Given I have a location added with name: "Cochabamba", display name "Cbba-Location" and description "This is Cochabamba Location"
	And I am logged as "Administrator" with password "Control*123"
When I delete the location "Cbba-Location"
Then The location "Cochabamba" is not displayed on location page

Scenario: A locations is displayed on Location page when it is added as a child of another location
Given I have a location added with name: "ParentCbba", display name "Cbba-Parent" and description "This is Parent Location"
	And I am logged as "Administrator" with password "Control*123"
When I add a new location with name: "ChildPunata", display name "Cbba-Child" and parent "ParentCbba"
Then The location "ChildPunata" child of "ParentCbba" is displayed on location page

Scenario: A room is displayed on Location Association page  when it is associated with a new location
Given I am logged as "Administrator" with password "Control*123"
When I add a new location with name: "Cochabamba", display name "Cbba-Location" and associated room "Room001"
Then The room "Room001" is displayed on Location Association page as associated with "Cbba-Location" location

Scenario: A room is displayed on Location Association page when it is associated with an existent location
Given I have a location added with name: "Cochabamba", display name "Cbba-Location" and description "This is Cochabamba Location"
	And I am logged as "Administrator" with password "Control*123"
When I associate the location "Cbba-Location" with a room "Room001"
Then The room "Room001" is displayed on Location Association page as associated with "Cbba-Location" location

Scenario: A room is not displayed on ‘Location Association’ page  when the association with a location is removed
Given I have a location added with name: "Cochabamba", display name "Cbba-Location" and description "This is Cochabamba Location"
	And I am logged as "Administrator" with password "Control*123"
	And I associate the location "Cbba-Location" with a room "Room001"
When I delete the association between location "Cbba-Location" and "Room001"
Then The room "Room001" is not displayed on Location Association page as associated with "Cbba-Location" location 

Scenario: The number of rooms associated displayed on Locations page increases when a room is associated
Given I have a location added with name: "Cochabamba", display name "Cbba-Location" and description "This is Cochabamba Location"
	And I am logged as "Administrator" with password "Control*123"
When I associate the location "Cbba-Location" with a room "Room001"
Then The number of assciations on Location page has been increased by "Cochabamba" location association

 