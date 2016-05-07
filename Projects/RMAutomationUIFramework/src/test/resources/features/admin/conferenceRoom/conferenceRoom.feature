Feature: Conference Room

Scenario Outline: "Capacity" of a room is updated when it is edited in "Room Info" form.
Given I logged to Room Manager Admin
When I edit "<RoomName>" room with a new capacity "<capacity>"
Then I validate if the Capacity has been updated in the Room Info page.

Examples:
    | RoomName  |     capacity    | 
    | Room04    |       50        | 