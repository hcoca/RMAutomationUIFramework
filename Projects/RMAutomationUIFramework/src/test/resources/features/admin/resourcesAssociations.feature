Feature: Resources associations

Scenario: The correct quantity of the associated resource is displayed on "Quantity" column of the pop-up configuration of the resource
   
    Given I am logged to room Manager with user administrator account
    And I associate a resource
    When I go to resources page
    And open the pop-up configuration of the resource associated
    Then I see the correct quantity that was associated

