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
#Given I have at least one location added with name: "<name>", display name"<displayName>" and description"<description>"
#	And I am on location page 
#When I update a location name with: "<updateName>"
#Then The updated location name is displayed on location page

#Examples:
#    | name       | displayName     | description                 | updateName   |
#    | Cochabamba | Cbba-Location   | This is Cochabamba Location | Name Updated |