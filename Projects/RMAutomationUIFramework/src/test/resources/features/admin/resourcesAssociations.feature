Feature: Resources associations

  @scenario#2
  Scenario: The resources associated are displayed on the rooms table after doing a click on the resource with the quantity
    Given I am on the conference rooms page
    And I have a resource associate with quantity "30"
    When I click on the resource
    Then I see the resource associate on the table of the conference room with the quantity "30"

  @scenario#3
  Scenario: The quantity of the resources associated to one room is edited after changing the value of this
    Given I am on the conference rooms page
    And I associate a resource on resources associattion page
    When I modify the quantity of the associated resource to "25"
    Then I can see that the quantity "25" is displayed
  
  
  @scenario#1
  Scenario: The correct quantity of the associated resource is displayed on Quantity column of the pop-up configuration of the resource
    Given I am on the conference rooms page
    And I associate a resource with quantity "25"
    When I go to resources page
    And open the pop-up configuration of the resource associated
    Then I see the quantity "25" with the room associated
  
  @scenario#4
  Scenario: The resource is displayed on the Avaible column of the pop-up configuration of the room after removing the resource of the "Associated" column
    Given I am on the conference rooms page
    And I associate a resource on resources association page
    When I remove the resource of the associated column
    Then I see the resource on available resources column
    
  @scenario#5
  Scenario: The resource associated to one Room is displayed on Associated column on the pop-up configuration of the room when the room was disabled
    Given I have one Room disabled
    And I am on the conference rooms page
    And I have a resource associated
    When I open the pop-up configuration of the room disabled
    Then I see the resource associated in Associated column

  @scenario#6
  Scenario: The resource associated to one Room is displayed on Associated column on the pop-up configuration of the room when the room is enabled
    Given I am on the conference rooms page
    And I associate a resource on resources associations page
    When I make sure the room edited is enabled
    And when I open the pop-up configuration of the room enabled
    Then I see the resource associated in Associate column
    

  
