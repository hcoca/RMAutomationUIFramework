Feature: Conference Room

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
When I edit "<RoomName>" room with a new display name "<displayName>"
Then I validate if the display name has been updated in the Conference Room table

Examples:
    | RoomName  | displayName | 
    | Room06    |  Room006    | 
     
Scenario Outline: "Code" of a room is updated when it is edited in "Room Info" form.
Given I logged to Room Manager Admin
When I edit "<RoomName>" room with a new code "<code>"
Then I validate if the Code has been updated in the Room Info page.

Examples:
    | RoomName  |     code    | 
    | Room06    |  code006    | 
