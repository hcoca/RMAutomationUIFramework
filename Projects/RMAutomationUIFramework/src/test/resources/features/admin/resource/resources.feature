#Feature: Resourcesasdssdsda
#	I want to create, update, remove and search resources


#Scenario Outline: Create a new resource
#Given I Login to Room Manager
#When I Create a new resource with name "<name>", with display name "<displayName>", with description "<description>" and icon "<icon>"
#Then I validate that the resource with name "<name>" is diplayed in resource page

#Examples:
#	|name		 |displayName	|description					 |icon			|
#	|computer|Computer	  |this is a new computer|fa-desktop|
	
#scenario 2
#Scenario Outline: Delete Resource
#Given I have a resource created with the name "<name>"
#When I delete the resource with the name "<name>"
#And Confirm the changes
#Then I verify that the resource with the name "<name>" has been deleted

#Examples:
#    | name  |
#    | TestResource  |

#scenario 3
#Scenario: A resource is displayed when it is filtered.
#Given I have a resource
#When I search the resource in resources page
#Then I validate the resource is displayed 
#	And then the resource is deleted

#scenario 4	
#Scenario Outline: Update Resource
#Given I have a resource created with name "<name>"
#When I modify the "<field>" field with value "<value>"
#	And I save the modifications
#Then the resource is modified according the changes ("<field>" field with value "<value>")

#Fields to be modified : name, displayname, icon
#Examples:
#    | name                | field  |value 														|
#    | Resource12345       | name   |ThisisalongstringThisisalongstring|
    
#scenario 5
#Scenario Outline: All resources are displayed in resource table
#Given I Login to Room Manager application
#	And I add "<number>" resources
# When I click on resource option
# Then I validate that all resources are displayed in resource table

#Examples:
#		|number|
#		|		2	 |
		
#scenario 6
#Scenario Outline: The correct information is displayed when a resource is created
#Given I log in to Room Manager app
# When I create a resource with name "<name>", display name "<displayName>", description "<description>" and icon "<icon>"
# Then I validate that resource with "<name>", "<displayName>" and "<icon>" is displayed
 
# Examples:
# 	|name		 |displayName	|description					 |icon			|
#	|folder	 |folder 	    |this is a new resource|fa-folder|
 
 #scenario 7
# Scenario Outline: The number of total items is displayed in the resource table
# Given I Login to RoomManager APP
#  When I create "<number>" resources
#  Then I validate that total resources created are displayed in resource table 
	