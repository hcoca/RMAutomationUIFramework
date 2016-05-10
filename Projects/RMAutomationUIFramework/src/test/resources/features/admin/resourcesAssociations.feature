Feature: Resources associations

  @num#1
  Scenario: The correct quantity of the associated resource is displayed on "Quantity" column of the pop-up configuration of the resource
    Given I am on the conference rooms page
    And I associate a resource
    When I go to resources page
    And open the pop-up configuration of the resource associated
    Then I see the correct quantity that was associated

  @num#2
  Scenario: Associate a resource
    Given I am on the conference rooms page
    And I have a resource associate
    When I click on the resource
    Then I see the resource associate on the conference room page

  @num#3
  Scenario: The quantity of the resources associated to one room is edited after changing the value of this
    Given I am on the conference rooms page
    And I associate a resource on resources associattion page
    When I modify the quantity of the of the associated resource
    Then I can see that the quantity modified is displayed

  @num#4
  Scenario: The resource is displayed on the "Avaible" column of the pop-up configuration of the room after removing the resource of the "Associated" column
    Given I am on the conference rooms page
    And I associate a resource on resources association page
    When I remove the resource of the associated column
    Then I see the resource on available resources column

  @num#5
  Scenario: The resource associated to one Room is displayed on Associated column on the pop-up configuration of the room when the room was disabled
    Given I am on the conference rooms page
    And I have a resource associated
    When I disable the room
    And when I open the pop-up configuration of the room disabled
    Then I see the resource associated in Associated column

  @num#6
  Scenario: The resource associated to one Room is displayed on "Associated" column on the pop-up configuration of the room when the room is enabled
    Given I am on the conference rooms page
    And I associate a resource on resources associations page
    When I make sure the room edited is enabled
    And when I open the pop-up configuration of the room enabled
    Then I see the resource associated in Associate column

  @num#8
  Scenario: One resource is associated to more than one room after adding the resource from the Avaible column to Associated column of the pop-up configuration of the rooms
    Given I am on the conference rooms page
    And I associate one resource to many rooms
    When I open the pop-up configuration of the each room
    Then I see the resource associate in each room that was modified

  @num#7
  Scenario: More than ten resources are associated to one room after doing a click on the resources avaibles
    Given I am on the conference rooms page
    And I associate many resources to one room
    When I open the pop-up configuration of the room
    Then I see the all resources associated to this room
