Feature: Resources A

#scenario 1
@resourceCreated
Scenario: A form with resource information is displayed when remove button is clicked
Given I have a resource created with the name "resourceassoc", display name "resourceassoc", description "resourceassoc" and icon "fa-fire"
And I as Administrator Login to Room Manager
When I want to remove the resource "resourceassoc"
Then Validate that a form with "resourceassoc" resource and "fa-fire" icon is displayed