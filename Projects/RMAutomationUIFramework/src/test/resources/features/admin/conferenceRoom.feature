Feature: Conference Room

Scenario Outline: "Capacity" of a room is updated when it is edited in "Room Info" form.
Given I logged to Room Manager Admin
When I edit "<RoomName>" room with a new capacity "<capacity>"
Then I validate if the Capacity has been updated in the Room Info page.

Examples:
    | RoomName  |     capacity    | 
    | Room04    |       50        | 

Scenario Outline: The icon of "Out of Order" changes its color to green so the Conference Room selected changes its state to non-available.
Given I logged to Room Manager Admin
  And I create an Out of Order on a specific "<RoomName>" room 
When  I did click on the icon of Out of Order on the "<RoomName>" room
	And I sign in to Tablet page using the "<RoomName>" room
Then I validate if the "<RoomName>" room has changed its status to non-available
Examples:
    | RoomName  |
    | Room02    | 

Scenario Outline: An "Out of Order Planning" is created with the corresponding interval of time that has been specified.
Given I logged to Admin Room Manager 
When  I create an Out of Order on "<RoomName>" room
Then I validate if the Out Of Order on "<RoomName>" room has been created with the time interval defined
Examples:
    | RoomName  |
    | Room04    |  

Scenario Outline: An "Out of Order Planning" is created with the corresponding title that has been specified.
Given I logged to RoomManagerAdmin 
When  I create an Out of Order on "<RoomName>" room with a title defined
Then I validate if the Out Of Order on "<RoomName>" room has been created with the title defined
Examples:
    | RoomName  |
    | Room07    | 

Scenario Outline: An "Out of Order Planning" can be disabled.
Given I logged RoomManagerAdmin 
When  I create an OuOfOrder on "<RoomName>" room
 And  I disable this OutOfOrder on "<RoomName>" room
Then I validate if the Out Of Order on "<RoomName>" room has been disabled correctly
Examples:
    | RoomName  |
    | Room08    |  

Scenario Outline: An "Out of Order" cannot be established when a time in the past is used.
Given I logged RoomManager Admin 
When  I create an OuOfOrder on "<RoomName>" room with a time in the past
Then I validate if the Out Of Order on "<RoomName>" room can not be created
Examples:
    | RoomName  |
    | Room09    | 

Scenario Outline: An "Out of Order" cannot be established when a date in the past is used.
Given I logged on RoomManager Admin 
When  I create an OuOfOrder on "<RoomName>" with a date in the past
Then I validate if the Out Of Order on "<RoomName>" cannot be created
Examples:
    | RoomName  |
    | Room10    |

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

Scenario Outline: "Location" of a room is updated when it is edited in "Room Info" form.
Given I logged to Room Manager Admin
When I edit "<RoomName>" room with a new Location "<location>"
Then I validate if the Capacity has been updated in the Room Info page.

Examples:
    | RoomName  |     location    | 
    | Room04    |  locationNew    |

Scenario Outline: Resource buttons created are displayed on conference room page.
Given I logged to Room Manager Admin
When I create a new "<resource>" Resource
Then I validate if the resource is in Conference Room page.

Examples:
    |     resource    | 
    |  resource123    |  