Feature: Admin Resources
	I want to update a resource

Scenario Outline: Update Resource
Given I have a resource created
When I modify the "<field>" field with value "<value>"
	And I save the modifications
Then the resource is modified according the changes.

Examples:
    | field  |value 																		 |
    | name   |  cscjiejfiejfiejfiejfiejfiejfiejfyytytytyt| 
    | icon   |  fa-refresh                               | 