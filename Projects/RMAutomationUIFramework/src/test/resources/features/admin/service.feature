@service
Feature: Email Server

@service_acceptance
Scenario: Service information is saved when a service is added.
Given There is no Email Server added
When I add a new Exchange Email Server with description "My Customized Description" 
Then Service infomation is saved with description "My Customized Description" 

@service_acceptance	
Scenario: All Conference rooms are added from Exchange service when a service is added.
Given There is no Email Server added
When I add a new Email Server
Then All Conference rooms are added from Exchange service

@service_functional
Scenario: A credential is modified when the service credential is modified.
Given There is an Email Server added
When I modify credential with user name "RoomManager1" and password "Control*123"
Then The changes: user name "RoomManager1" and password "Control*123" are saved 

@service_functional
Scenario: All Conference rooms are deleted when Service is deleted.
Given There is an Email Server added
When I delete the Email Server
Then There is no rooms

@service_functional
Scenario: Meetings are deleted when a service is deleted.
Given There is an Email Server added
	And At least a meeting
When I delete the Email Server from Conference Rooms page
Then The meetings are deleted

@service_functional
Scenario: Out of Orders are deleted when a service is deleted.
Given There is an Email Server added
	And At least an out of order 
When I delete the Email Server from Conference Rooms page
Then The out-of-orders are deleted

@service_functional_negative
Scenario: An error message is displayed when user account does not follow the requirements.
Given There is an Email Server added
When I try to modify the Exchange Email Server credential with user account which does not follow the requirements
Then An error message is displayed


