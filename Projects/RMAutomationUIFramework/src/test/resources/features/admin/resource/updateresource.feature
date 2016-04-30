Feature: Admin Resources
	I want to update a resource

Scenario Outline: Update Resource
Given I have a resource created with name "<name>"
When I modify the "<field>" field with value "<value>"
	And I save the modifications
Then the resource is modified according the changes ("<field>" field with value "<value>")

#Fields to be modified : name, displayname, icon
Examples:
    | name                | field  |value 																		 |
    | Resource12345       | name   |  cscjiejfiejfiejfiejfiejfiejfiejfyytytytyt| 

    
