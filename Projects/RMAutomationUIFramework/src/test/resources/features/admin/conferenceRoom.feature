Feature: Conference Room

Scenario: "Capacity" of a room is updated when it is edited in "Room Info" form.
Given I logged to Room Manager Admin
When I edit "Room001" room with a new capacity "50"
Then I validate if the Capacity has been updated in the Room Info page.

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

Scenario: "Display Name" of room is updated in the "Conference Room" table when it is edited in "Room Info" form.
Given I logged to Room Manager Admin
When I edit "Room002" room with a new display name "RoomChanged"
Then I validate if the display name has been updated in the Conference Room table
     
Scenario: "Code" of a room is updated when it is edited in "Room Info" form.
Given I logged to Room Manager Admin
When I edit "Room003" room with a new code "codeForTry"
Then I validate if the Code has been updated in the Room Info page.

Scenario: "Location" of a room is updated when it is edited in "Room Info" form.
Given I logged to Room Manager Admin
And I have a new Location "<location>"
When I edit "Room004" room with a new Location "locationNew"
Then I validate if the Location has been updated in the Room Info page.

Scenario: Resource buttons created are displayed on conference room page.
Given I logged to Room Manager Admin
When I create a new "resourceForTry" Resource
Then I validate if the resource is in Conference Room page.
    
Scenario: A room is enabled when its button is clicked.
Given I logged to Room Manager Admin
And I have "Room005" room disabled
When I enabled "Room005" room
Then I validate if the room "Room005" is enabled in tablet page

Scenario: A room is disabled when its button is clicked.
Given I logged to Room Manager Admin
And I have "Room006" room enabled
When I disabled "Room006" room
Then I validate if the room "Room006" is disabled in tablet page

Scenario: "Total Items" label displays number of rooms that there are in "Conference Room" table.
Given I logged to Room Manager Admin
When I go to conference room page
Then validate if the quantity of rooms in server is the same displayed in conference room page

Scenario: Rooms that match with filter criteria are displayed on "Conference Room" table..
Given I logged to Room Manager Admin
When I search rooms with this criteria "13"
Then validate if the table have all rooms that have this criteria "13" in their names

Scenario Outline: "Page Size" displays in the "Conference Room" table the quantity of rooms that specifies its dropdown list.
Given I logged to Room Manager Admin
When I specify size "<size>" of rooms quantity on conference room page.
Then validate if there are "<size>" or less rooms on table.

Examples:
    | size  |
    |  200  |
    |  100  |
    |  35   |