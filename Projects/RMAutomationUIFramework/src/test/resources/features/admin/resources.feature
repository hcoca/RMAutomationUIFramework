Feature: Resources
	I want to create, update, remove and search resources

#scenario 1
Scenario: Create a new resource
Given I as Administrator Login to Room Manager
 When I Create a new resource with name "computer", display name "Computer", description "this is a new computer" and icon "fa-desktop"
 Then Validate that the resource with name "computer" is diplayed in resource page

 #scenario 2
Scenario: Delete Resource
Given I have a resource created with the name "testResource", display name "TestResource", description "TestResource" and icon "fa-fire"
  And I as Administrator Login to Room Manager 
 When I delete the resource with the name "testResource"
  And Confirm the changes
 Then Validate that the resource with the name "testResource" has been deleted
 
 #scenario 3
Scenario: A resource is displayed when it is filtered.
Given I have a resource created with the name "Resource12345", display name "Resource12345", description "Resource12345>" and icon "fa-fire"
  And I as Administrator Login to Room Manager
 When I search the resource "Resource12345" in resources page
 Then Validate the resource "Resource12345" is displayed 
 
#scenario 4	
Scenario: Update Resource
Given I have a resource created with the name "Fire", display name "Fire", description "Fire resource" and icon "fa-fire"
  And I as Administrator Login to Room Manager
 When I modify the "displayname" field with value "newFirevalue" in the resource "Fire"
	And I save the modifications
 Then Validate that the resource "Fire" is modified according the changes ("displayname" field with value "newFirevalue")
 
#scenario 5
Scenario Outline: All resources are displayed in resource table
Given I Login to Room Manager application
	And I add "<number>" resources
 When I click on resource option
 Then I validate that all resources are displayed in resource table

Examples:
		|number|
		|		2	 |
		
#scenario 6
Scenario Outline: The correct information is displayed when a resource is created
Given I log in to Room Manager app
 When I create a resource with name "<name>", display name "<displayName>", description "<description>" and icon "<icon>"
 Then I validate that resource with "<name>", "<displayName>" and "<icon>" is displayed

 Examples:
	|name		 |displayName	|description					 |icon			|
  |folder	 |folder 	    |this is a new resource|fa-folder|
#scenario 7
Scenario Outline: The number of total items is displayed in the resource table
Given I Login to RoomManager APP
 When I create "<number>" resources
 Then I validate that total resources created are displayed in resource table 
 Examples:
  	|number|
		|		1	 | 

#scenario 8
Scenario Outline: A form with resource association is displayed when a resource is going to remove
Given I have a "<name>" resource created 
  And I Login to Room Manager web app
 When I associate the resource "<name>" to a room "<room>"
  And I want to remove the resource "<name>"
 Then I validate that the association is displayed
 Examples:
	 	|name		 	 |room	|
		|dashboard |Room001|

#scenario 9
Scenario Outline: The quantity selected in page size is displayed in resource table
Given I have atleast "<numberResources>" resources created
  And I as Administrator Login to Room Manager
 When I select a option "<number>" on page size option
 Then I validate that the resource table size is same than the option "<number>" selected
 Examples:
 		|numberResources|number|
 		|				52	 		|	50	|
 		
#scenario 10
Scenario Outline: When First button is clicked the first page is displayed in resource table
Given I have atleast "<numberResources>" created resources
  And I as Administrator Login to Room Manager
 When I Clicked on First button on resource table
 Then I validate that the first page is displayed on resource table
Examples:  
		|numberResources|
 		|				53	 	|	
 		
 		
#scenario 11 
Scenario Outline: When 'Last' button is clicked the last page is displayed in resource table
Given There are atleast "<numberResources>" created resources
 When I Clicked on Last button on resource table
 Then I validate that the last page is displayed on resource table 
 Examples:  
		|numberResources|
 		|				15	 	|

#scenario 12
