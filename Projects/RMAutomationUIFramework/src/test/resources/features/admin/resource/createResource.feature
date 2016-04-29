

Feature: Resource
	I want to create a new resource


Scenario: Create a new resource
Given I Login to Room Manager
When I Create a new resource
Then I validate that the resource is diplayed in resource page
	And after that the resource is deleted

