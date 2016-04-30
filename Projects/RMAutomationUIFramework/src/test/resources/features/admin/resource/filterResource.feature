Feature: Title of your feature
	I want to use this template for my feature file

Scenario: A resource is displayed when it is filtered.
Given I have a resource
When I search the resource in resources page
Then I validate the resource is displyed 
	And then the resource is deleted
