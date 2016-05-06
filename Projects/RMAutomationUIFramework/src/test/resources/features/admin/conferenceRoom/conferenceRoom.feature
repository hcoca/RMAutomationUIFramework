Feature: Conference Room

Scenario Outline: The quantity of a resource associated is displayed in the table when a resource is clicked.
Given I logged to Room Manager Admin
	And I created <ResourceName> resource 
  And I associate this resource with <RoomName> room
	And I assign an specific <quantity> 
When I click on the resource associated
Then I validate that the quantity specified of resources is displayed in the table

Examples:
    | RoomName  |ResourceName | quantity |
    | Room06    |  Monitor    | 5        |

Scenario Outline: The icon of "Out of Order" changes its color to green so the Conference Room selected changes its state to non-available.
Given I logged to Room Manager Admin
  And I create an Out of Order on a specific "<RoomName>" room 
When  I did click on the icon of Out of Order
	And I sign in to Tablet page using the "<RoomName>" room
Then I validate if the "<RoomName>" room has changed its status to non-available

Examples:
    | RoomName  |
    | Room06    | 

Scenario Outline: "Display Name" of room is updated in the "Conference Room" table when it is edited in "Room Info" form.
Given I logged to Room Manager Admin
When I edit the display name with a this <displayName>  of <RoomName> room
Then I validate if the display name has been updated in the Conference Room table

Examples:
    | RoomName  | displayName | 
    | Room06    |  Room006    |
