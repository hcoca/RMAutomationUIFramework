Feature: Conference Room
@conferenceDisplayNameChanged
Scenario: "Display Name" of room is updated in the "Conference Room" table when it is edited in "Room Info" form.
Given I am logged as "Administrator" with password "Control*123"
When I edit "Room002" room with a new display name "ARoomIsChanged"
Then I validate if the display name has been updated in the Conference Room table
@conferenceCodeChanged     
Scenario: "Code" of a room is updated when it is edited in "Room Info" form.
Given I am logged as "Administrator" with password "Control*123"
When I edit "Room003" room with a new code "codeForTry"
Then I validate if the Code has been updated in the Room Info page.

Scenario: "Capacity" of a room is updated when it is edited in "Room Info" form.
Given I am logged as "Administrator" with password "Control*123"
When I edit "Room001" room with a new capacity "50"
Then I validate if the Capacity has been updated in the Room Info page.

@locationOfRoomIsUpdated
Scenario: "Location" of a room is updated when it is edited in "Room Info" form.
Given I am logged as "Administrator" with password "Control*123"
And I have a new Location "locationNew"
When I edit "Room004" room with a new Location "locationNew"
Then I validate if the Location has been updated in the Room Info page.
@conferenceResource
Scenario: Resource buttons created are displayed on conference room page.
Given I am logged as "Administrator" with password "Control*123"
When I create a new "resourceForTry" Resource
Then I validate if the resource is in Conference Room page.
@conferenceDisabled    
Scenario: A room is enabled when its button is clicked.
Given I have "Room005" room disabled
And I am logged as "Administrator" with password "Control*123"
When I enabled "Room005" room
Then I validate if the room "Room005" is enabled in tablet page
@conferenceDisabled
Scenario: A room is disabled when its button is clicked.
Given I have "Room006" room enabled
And I am logged as "Administrator" with password "Control*123"
When I disabled "Room006" room
Then I validate if the room "Room006" is disabled in tablet page

Scenario: "Total Items" label displays number of rooms that there are in "Conference Room" table.
Given I am logged as "Administrator" with password "Control*123"
When I go to conference room page
Then validate if the quantity of rooms in server is the same displayed in conference room page

Scenario: Rooms that match with filter criteria are displayed on "Conference Room" table..
Given I am logged as "Administrator" with password "Control*123"
When I search rooms with this criteria "13"
Then validate if the table have all rooms that have this criteria "13" in their names

Scenario Outline: "Page Size" displays in the "Conference Room" table the quantity of rooms that specifies its dropdown list.
Given I am logged as "Administrator" with password "Control*123"
When I select a page size "<size>" 
Then validate if there are "<size>" or less rooms on table.

Examples:
    | size  |
    |  200  |
    |  100  |
    |  35   |

Scenario: The icon of "Out of Order" changes its color to green so the Conference Room selected changes its state to non-available.
Given I am logged as "Administrator" with password "Control*123"
  And I create an Out of Order on a specific "Room002" room 
When  I did click on the icon of Out of Order on the "Room002" room
	And I sign in to Tablet page using the "Room002" room
Then The "Room002" room should changes its status to non-available with the "Closed for maintenance" title corresponding

Scenario: An "Out of Order Planning" is created with the corresponding interval of time that has been specified.
Given I am logged as "Administrator" with password "Control*123"
When  I create an Out of Order on "Room003" room with a time by default
Then The Out Of Order on "Room003" room should be created with the time interval defined

Scenario: An "Out of Order Planning" is created with the corresponding title that has been specified.
Given I am logged as "Administrator" with password "Control*123"
When  I create an Out Of Order on "Room004" room with a title defined 
Then The Out Of Order on "Room004" room should have been created an OutOfOrder with the "Closed for maintenance" title corresponding

Scenario: An "Out of Order Planning" can be disabled.
Given I am logged as "Administrator" with password "Control*123"
When  I create an Out Of Order on "Room005" room with a title defined 
 And  I disable this OutOfOrder on "Room005" room
Then The Out Of Order on "Room005" room should has been disabled correctly with the "Closed for maintenance" title corresponding

Scenario: An "Out of Order" cannot be established when a time in the past is used.
Given I am logged as "Administrator" with password "Control*123" 
When  I create an OutOfOrder on "Room006" room with a time in the past
Then The Out Of Order cannot be created an error message is displayed

Scenario: An "Out of Order" cannot be established when a date in the past is used.
Given I am logged as "Administrator" with password "Control*123"
When  I create an OutOfOrder on "Room007" with a date in the past
Then The Out Of Order cannot be created an error message is displayed

Scenario: Next page is displayed in "Conference Room" table when "Next Page" button is clicked.
Given I am logged as "Administrator" with password "Control*123"
When I select a page size "100" 
 And I set the page "2" 
Then I validate if the next page is displayed according the page size specified "100" and the page "2"

Scenario: Previus page is displayed in "Conference Room" table when "Previous Page" button is clicked.
Given I am logged as "Administrator" with password "Control*123"
 And  I select a "3" that has a previous page 
When I select a page size "100" 
 And I set the page "2" 
Then I validate if the previous page is displayed according the page size specified "100" and the page "2"
