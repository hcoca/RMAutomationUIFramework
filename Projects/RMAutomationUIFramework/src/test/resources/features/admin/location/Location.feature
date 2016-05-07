Feature: Location Page

#Scenario Outline: All locations are displayed on Locations page when it is opened
#Given I have at least one location added with name: "<name>", display name"<displayName>" and description"<description>"
#	And I have the list of added locations 
#When I open locations page
#Then All locations added are displayed

#Examples:
#    | name       | displayName     | description                 |
#    | Cochabamba | Cbba-Location   | This is Cochabamba Location |

    
#Scenario Outline: A location is displayed on Location page when it is added
#Given I am on location page 
#When I add a new location with name: "<name>", display name"<displayName>" and description"<description>"
#Then The new location added is displayed on location page

#Examples:
#    | name       | displayName     | description                 |
#    | Cochabamba | Cbba-Location   | This is Cochabamba Location |
    

#Scenario Outline: A location’s  name changes are displayed on Location page when it is updated
#Given I have a location added with name: "<name>", display name"<displayName>" and description"<description>" to update its name
#	And I am on location page
#When I update a location name with: "<updateName>"
#Then The updated location name is displayed on location page

#Examples:
#    | name       | displayName     | description                 | updateName   |
#    | Cochabamba | Cbba-Location   | This is Cochabamba Location | Name Updated |
    
    
#Scenario Outline: A location’s display  name changes are displayed on Locations page when it is updated
#Given I have a location added with name: "<name>", display name"<displayName>" and description"<description>" to update its display name
#	And I am on location page
#When I update a location display name with: "<updateDisplayName>"
#Then The updated location display name is displayed on location page

#Examples:
#    | name       | displayName     | description                 | updateDisplayName    |
#    | Cochabamba | Cbba-Location   | This is Cochabamba Location | Display Name Updated |
    
#Scenario Outline: A location’s description changes are displayed on Update Location page when it is updated
#Given I have a location added with name: "<name>", display name"<displayName>" and description"<description>" to update its description
#	And I am on location page
#When I update a location description with: "<updateDescription>"
#Then The updated location description is displayed on update location page

#Examples:
#    | name       | displayName     | description                 | updateDescription   |
#    | Cochabamba | Cbba-Location   | This is Cochabamba Location | Description Updated |
    
    
Scenario Outline: A location’s parent  changes are displayed on Update Location page when it is updated
Given I have a parent location added with name: "<nameParent>" and display name"<displayNameParent>"
	And I have a location added with name: "<nameChild>" and display name"<displayNameChild>" to update its parent
	And I am on location page
When I update a location parent
Then The updated location parent is displayed on update location page

Examples:
    | nameParent | displayNameParent  | nameChild   | displayNameChild    |
    | Cochabamba | Cbba-Location      | Cbba-child  | Cbba-Location-Child |
