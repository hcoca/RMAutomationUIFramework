Feature: Location Page

Scenario Outline: All locations are displayed on Locations page when it is opened
Given I have at least one location added with name: "<name>", display name"<displayName>" and description"<description>"
	And I have the list of added locations 
When I open locations page
Then All locations added are displayed

Examples:
    | name       | displayName     | description                 |
    | Cochabamba | Cbba-Location   | This is Cochabamba Location |

    
Scenario Outline: A location is displayed on Location page when it is added
Given I am on location page 
When I add a new location with name: "<name>", display name"<displayName>" and description"<description>"
Then The new location added is displayed on location page

Examples:
    | name       | displayName     | description                 |
    | Cochabamba | Cbba-Location   | This is Cochabamba Location |
    

Scenario Outline: A location’s  name changes are displayed on Location page when it is updated
Given I have a location added with name: "<name>", display name"<displayName>" and description"<description>" to update its name
	And I am on location page
When I update a location name with: "<updateName>"
Then The updated location name is displayed on location page

Examples:
    | name       | displayName     | description                 | updateName   |
    | Cochabamba | Cbba-Location   | This is Cochabamba Location | Name Updated |
    
    
Scenario Outline: A location’s display  name changes are displayed on Locations page when it is updated
Given I have a location added with name: "<name>", display name"<displayName>" and description"<description>" to update its display name
	And I am on location page
When I update a location display name with: "<updateDisplayName>"
Then The updated location display name is displayed on location page

Examples:
    | name       | displayName     | description                 | updateDisplayName    |
    | Cochabamba | Cbba-Location   | This is Cochabamba Location | Display Name Updated |
    
Scenario Outline: A location’s description changes are displayed on Update Location page when it is updated
Given I have a location added with name: "<name>", display name"<displayName>" and description"<description>" to update its description
	And I am on location page
When I update a location description with: "<updateDescription>"
Then The updated location description is displayed on update location page

Examples:
    | name       | displayName     | description                 | updateDescription   |
    | Cochabamba | Cbba-Location   | This is Cochabamba Location | Description Updated |
    
    
Scenario Outline: A location’s parent  changes are displayed on Update Location page when it is updated
Given I have a parent location added with name: "<nameParent>" and display name"<displayNameParent>"
	And I have a location added with name: "<nameChild>" and display name"<displa-yNameChild>" to update its parent
	And I am on location page
When I update a location parent
Then The updated location parent is displayed on update location page

Examples:
    | nameParent | displayNameParent  | nameChild   | displayNameChild    |
    | Cochabamba | Cbba-Location      | Cbba-child  | Cbba-Location-Child |
    
    
Scenario Outline: A location is not  displayed on Locations page when it is deleted
Given I have a location added with name: "<name>" and display name"<displayName>" to delete
	And I am on location page
When I delete the location 
Then The location updated is not displayed on location page

Examples:
    | name       | displayName   | 
    | Cochabamba | Cbba-Location |


Scenario Outline: A locations is displayed on Location page when it is added as a child of another location
Given I have one parent location added with name: "<nameParent>" and display name"<displayNameParent>"
	And I am on location page 
When I add a new location with name: "<name>" and display name"<displayName>" as child
Then The new child location added is displayed on location page

Examples:
    | nameParent | displayNameParent | name             | displayName           |
    | Cochabamba | Cbba-Location     | Cochabamba-Child | Cbba-Location-Child   |
    
    
Scenario Outline: A room is displayed on Location Association page  when it is associated with a new location
Given I am on location page
When I add a new location with name: "<name>" and display name"<displayName>" with an associated room"<roomName>"
Then The room is displayed on Location Association page as associated

Examples:
    | name       | displayName   | roomName |
    | Cochabamba | Cbba-Location | Room01   |

Scenario Outline: A room is displayed on Location Association page when it is associated with an existent location
Given I have a location added with name: "<name>" and display name"<displayName>" to update its associations
	And I am on location page
When I associate the location with a room"<roomName>"
Then The room is displayed on Location Association page as location association

Examples:
    | name       | displayName   | roomName |
    | Cochabamba | Cbba-Location | Room01   |


Scenario Outline: A room is not displayed on ‘Location Association’ page  when the association with a location is removed
Given I have a location added with name: "<name>" and display name"<displayName>" to delete its associations
	And I am on location page
	And I associate this location with a room"<roomName>"
When I delete the association
Then The room is not displayed on Location Association page as associated

Examples:
    | name       | displayName   | roomName |
    | Cochabamba | Cbba-Location | Room01   |  

Scenario Outline: The number of rooms associated displayed on Locations page increases when a room is associated
Given I have a location added with name: "<name>" and display name"<displayName>" to add an associations
	And I am on location page
When I add a location association with a room"<roomName>"
Then The number of assciation on Location page has been increased

Examples:
    | name       | displayName       | roomName |
    | Cochabamba | Cbba-Location     | Room01   | 
 