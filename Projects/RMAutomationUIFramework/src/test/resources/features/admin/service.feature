@service
Feature: Email Server

@service_acceptance
Scenario: Service information is saved when a service is added.
Given There is no services added
When I add a new Exchange Email Server 
Then Service infomation is saved

@service_functional
Scenario: A credential is modified when the service credential is modified.
Given There is a service existent
When I modify credential with another account 
Then The changes are saved

@service_functional_negative
Scenario: An error message is displayed when user account does not follow the requirements.
Given There is the service added
When I try to modify the Exchange Email Server credential with user account which does not follow the requirements
Then An error message is displayed

@service_acceptance	
Scenario: All Conference rooms are added from Exchange service when a service is added.
Given There is no Email Server added
When I add a new Email Server 
Then All Conference rooms are added from Exchange service

@service_functional
Scenario: All Conference rooms are deleted when Service is deleted.
Given There is an Emailservice added
When I delete the Emailservice
Then There is no rooms

@service_functional
Scenario: Service information is deleted when service is deleted.
Given There is a service alive
When I delete the current Email Server
Then Service information details are deleted

@service_functional
Scenario: Service information is deleted when service is deleted.
Given There is a service alive
When I delete the current Email Server
Then Service information details are deleted

@service_functional
Scenario: Meetings are deleted when a service is deleted.
Given There is a mail service added
And At least a meeting
When I delete the mail service
Then The meetings are deleted

@service_functional
Scenario: Out of Orders are deleted when a service is deleted.
Given There is a EmailServer added
And At least an out of order
When I delete the EmailServer
Then The out-of-orders are deleted
