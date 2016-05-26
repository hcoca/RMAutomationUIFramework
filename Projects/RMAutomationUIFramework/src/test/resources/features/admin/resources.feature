Feature: Resources
	I want to create, update, remove and search resources

#scenario 1
@resourcesbvt
Scenario: Create a new resource
Given I as Administrator Login to Room Manager
 When I Create a new resource with name "computer", display name "Computer", description "this is a new computer" and icon "fa-desktop"
 Then Validate that the resource with name "computer" is diplayed in resource page

 #scenario 2
 @resourcesbvt
Scenario: Delete Resource
Given I have a resource created with the name "testResource", display name "TestResource", description "TestResource" and icon "fa-fire"
  And I as Administrator Login to Room Manager 
 When I delete the resource with the name "testResource"
  And Confirm the changes
 Then Validate that the resource with the name "testResource" has been deleted
 
 #scenario 3
@resourcesbvt
Scenario: A resource is displayed when it is filtered.
Given I have a resource created with the name "Resource12345", display name "Resource12345", description "Resource12345>" and icon "fa-fire"
  And I as Administrator Login to Room Manager
 When I search the resource "Resource12345" in resources page
 Then Validate the resource "Resource12345" is displayed  


#scenario 4
@resourcesbvt 	
Scenario: Update Resource
Given I have a resource created with the name "Fire", display name "Fire", description "Fire resource" and icon "fa-fire"
  And I as Administrator Login to Room Manager
 When I modify the "displayname" field with value "newFirevalue" in the resource "Fire"
	And I save the modifications
 Then Validate that the resource "Fire" is modified according the changes ("displayname" field with value "newFirevalue")
 
#scenario 9
@resouceDelete @resourcesbvt 
Scenario: The quantity selected in page size is displayed in resource table
Given I have atleast "200" created resources
  And I as Administrator Login to Room Manager
 When I select a option "100" on page size option
 Then Validate that the resource table size is same than the option "100" selected
	
#scenario 10
@resouceDelete @resourcesbvt 
Scenario: When First button is clicked the first page is displayed in resource table
Given I have atleast "53" created resources
  And I as Administrator Login to Room Manager
 When I Clicked on First button on resource table
 Then Validate that the first page is displayed on resource table		
 		
#scenario 11 
@resouceDelete @resourcesbvt 
Scenario: When 'Last' button is clicked the last page is displayed in resource table
Given I have atleast "205" created resources
 	And I as Administrator Login to Room Manager
 When I Clicked on Last button on resource table
 Then Validate that the last page is displayed on resource table

#scenario 12
@resouceDelete @resourcesbvt 
Scenario: When 'Next' button is clicked the next page is displayed in resource table
Given I have atleast "200" created resources 
  And I as Administrator Login to Room Manager
  And I am in the first page
 When I cliked on 'Next' button on resource table
 Then Validate that the 'next' page is displayed on resource table 

#scenario 13
@resouceDelete @resourcesbvt 
Scenario: When 'Previous' button is clicked the before page is displayed in resource table
Given I have atleast "200" created resources
  And I as Administrator Login to Room Manager
  And I am in the last page
 When I clicked on 'Previous' button on resource table
 Then Validate that the 'previous' page is displayed on resource table

 #scenario 14
 @resouceDelete @resourcesbvt 
 Scenario: The 'current page' field displays the number of the current page
 Given I have atleast "200" created resources
   And I as Administrator Login to Room Manager
  When Go to the "3" page on resource page
  Then Validate that the "3" page is displayed 
  
#scenario 15
@resourceCreated
Scenario: A form with resource information is displayed when remove button is clicked
Given I have a resource created with the name "resourceassoc", display name "resourceassoc", description "resourceassoc" and icon "fa-fire"
And I as Administrator Login to Room Manager
When I want to remove the resource "resourceassoc"
Then Validate that a form with "resourceassoc" resource and "fa-fire" icon is displayed

#scenario 16
Scenario: A form to create a new resource is displayed when add button is clicked
Given I as Administrator Login to Room Manager
When I want to create a new resource
Then Validate that a form to create a resource is diplayed

#scenario 17
Scenario: A message is displayed when data is not entered to create a resource
Given I as Administrator Login to Room Manager
When I want to create a new resource without filling data
Then Validate that an error message is displayed

#scenario 18
Scenario: An message is displayed when characters not allowed are entered in the text field 
Given I as Administrator Login to Room Manager
When I want to create a new resource with special characters like "**.,/*//?+=-"
Then Validate that an error message is displayed for special characters

#scenario 19
@resourceCreated
Scenario: A message is displayed when a name already exists is entered in resource name text field
Given I have a resource created with the name "resourceassoc", display name "resourceassoc", description "resourceassoc" and icon "fa-fire"
And I as Administrator Login to Room Manager
When I create a resource with name "resourceassoc", display name "resourceassoc", description "Description" and icon "fa-desktop"
Then Validate that an error message is displayed when I want to create a new resource that already exists.

#scenario 20
@resourceCreated
Scenario: A form to delete a resource is displayed when Remove button is clicked
Given I have a resource created with the name "resourceassoc", display name "resourceassoc", description "resourceassoc" and icon "fa-fire"
And I as Administrator Login to Room Manager
When I want to remove the resource "resourceassoc"
Then Validate that a form to remove a resource is displayed

#scenario 21
Scenario Outline: The icon table change the page when right/left button is clicked
Given I as Administrator Login to Room Manager
And I try to create a new Resource
When I want to change to "<button>" icon page
Then Validate that icon page change to "<button>"

Examples:
    | button  |
    |  left   |
    |  right  |

#scenario 22
Scenario: The image in the icon box is updated when different icon is selected
Given I as Administrator Login to Room Manager
And I try to create a new Resource
When I select "fa-fire" Icon
Then Validate that icon image is updated to "fa-fire" Icon on add page.
 
Scenario: Resource Info Page is closed when clicking Cancel button
Given I as Administrator Login to Room Manager
And I try to create a new Resource
When I click on Cancel button on the Add Resource Page
Then the Resource Info Page is closed

Scenario: Resource Info Page is closed when clicking Close button
Given I as Administrator Login to Room Manager
And I try to create a new Resource
When I click on Close button on the Add Resource Page
Then the Resource Info Page is closed

@resouceDelete
Scenario: The quantity of selected resources is displayed when selecting resources
Given I have atleast "5" created resources 
And I as Administrator Login to Room Manager
When I select "3" resources
Then the quantity of selected resources displayed is the same as the number of resources selected "3"

@resouceDelete
Scenario: All the resources are checked when checking the Table Header CheckBox
Given I have atleast "5" created resources
And I as Administrator Login to Room Manager
When I check the Table Header CheckBox
Then All the resources are selected

@resouceDelete
Scenario: All the resources are unchecked when unchecking the Table Header CheckBox
Given I have atleast "5" created resources
And I as Administrator Login to Room Manager
And I check the Table Header CheckBox
When the Table Header CheckBox is unchecked
Then All the resources are unselected

@resouceDelete
Scenario: The Remove button is enabled when a resource is checked
Given I have atleast "1" created resources
And I as Administrator Login to Room Manager
When I select "1" resources
Then the Remove button is enabled

@resouceDelete
Scenario: A resource is checked when clicking the resource
Given I have atleast "1" created resources
And I as Administrator Login to Room Manager
When I select "1" resources
Then the resource "Gift0" is checked
