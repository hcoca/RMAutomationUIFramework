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
@resouceDelete
Scenario: All resources are displayed in resource table
Given I as Administrator Login to Room Manager
	And I add "2" resources
 When Click on resource option
 Then Validate that all resources are displayed in resource table

#scenario 6
Scenario: The correct information is displayed when a resource is created
Given I as Administrator Login to Room Manager 
 When I create a resource with name "folder", display name "folder", description "this is a new resource" and icon "fa-folder"
 Then Validate that resource with "folder", "folder" and "fa-folder" is displayed
		
#scenario 7
@resouceDelete
Scenario: The number of total items is displayed in the resource table
Given I as Administrator Login to Room Manager 
 When I add "4" resources
 Then Validate that total resources are displayed

#scenario 8
Scenario: A form with resource association is displayed when a resource is going to remove
Given I have a resource created with the name "dashboard", display name "dashboard", description "dashboard resource" and icon "fa-dashboard" 
  And I as Administrator Login to Room Manager
 When I associate the resource "dashboard" to a room "Room001"
  And I want to remove the resource "dashboard"
 Then Validate that the association with the "Room001" room is displayed

#scenario 9
@resouceDelete
Scenario: The quantity selected in page size is displayed in resource table
Given I have atleast "200" created resources
  And I as Administrator Login to Room Manager
 When I select a option "100" on page size option
 Then Validate that the resource table size is same than the option "100" selected
	
#scenario 10
@resouceDelete
Scenario: When First button is clicked the first page is displayed in resource table
Given I have atleast "53" created resources
  And I as Administrator Login to Room Manager
 When I Clicked on First button on resource table
 Then Validate that the first page is displayed on resource table		
 		
#scenario 11 
@resouceDelete
Scenario: When 'Last' button is clicked the last page is displayed in resource table
Given I have atleast "205" created resources
 	And I as Administrator Login to Room Manager
 When I Clicked on Last button on resource table
 Then Validate that the last page is displayed on resource table

#scenario 12
@resouceDelete
Scenario: When 'Next' button is clicked the next page is displayed in resource table
Given I have atleast "200" created resources 
  And I as Administrator Login to Room Manager
  And I am in the first page
 When I cliked on 'Next' button on resource table
 Then Validate that the 'next' page is displayed on resource table 

#scenario 13
@resouceDelete
Scenario: When 'Previous' button is clicked the before page is displayed in resource table
Given I have atleast "200" created resources
  And I as Administrator Login to Room Manager
  And I am in the last page
 When I clicked on 'Previous' button on resource table
 Then Validate that the 'previous' page is displayed on resource table

 #scenario 14
 @resouceDelete
 Scenario: The 'current page' field displays the number of the current page
 Given I have atleast "200" created resources
   And I as Administrator Login to Room Manager
  When Go to the "3" page on resource page
  Then Validate that the "3" page is displayed 