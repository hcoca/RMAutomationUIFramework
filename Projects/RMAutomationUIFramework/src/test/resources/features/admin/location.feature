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

@location13
Scenario: The number of rooms asociated displayed on Locations page decreases when a room association is removed
Given I have a location added with name: "Cochabamba_13", display name "Cbba-Location_13" and description "This is Cochabamba Location"
	And I am logged as "Administrator" with password "Control*123"
	And I associate the location "Cbba-Location_13" with a room "Room005"
When I delete the association between location "Cbba-Location_13" and "Room005"
Then The number of associations on Location page has been decreased by removing "Cochabamba_13" location association
 