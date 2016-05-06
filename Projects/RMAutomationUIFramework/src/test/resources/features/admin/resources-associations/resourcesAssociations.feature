Feature: Resources associations
  Scenario: The resource associated to one Room is displayed on Associated column on the pop-up configuration of the room when the room was disabled
    Given I am on the Conference Rooms page
    And I have a resource associated
    When I disable the room
    And when I open the pop-up configuration of the room disabled
    Then I see the resource associated in Associated column

  Scenario: The resource associated to one Room is displayed on "Associated" column on the pop-up configuration of the room when the room is enabled
    Given I am on the Conference Rooms page
    And I have a resource associated
    When I make sure the room edited is enabled
    And when I open the pop-up configuration of the room disabled
    Then I see the resource associated in Associated column

       
   Scenario: The quantity of the resources associated to one room is edited after changing the value of this
    Given I am on the Conferences Rooms page
     And I associate a resource on resources association page
    When I modify the quantity of the of the associated resource
     Then I can see that the quantity modified is displayed
     
