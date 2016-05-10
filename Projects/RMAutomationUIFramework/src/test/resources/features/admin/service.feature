@service
Feature: Email Server

@service_functional
Scenario: Out of Orders are deleted when a service is deleted.
Given There is a EmailServer added
And At least an out of order
When I delete the EmailServer
Then The out-of-orders are deleted