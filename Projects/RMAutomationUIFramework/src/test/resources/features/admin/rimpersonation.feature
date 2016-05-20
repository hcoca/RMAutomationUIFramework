Feature: Impersonation

@IMP-DisableImpersonation
Scenario: Enable Impersonation
Given impersonation is disabled
And I am logged as "Administrator" with password "Control*123"
When I enable Impersonation
Then the Impersonation Option is enabled

@IMP-DisableImpersonation
Scenario: Disable Impersonation
Given impersonation is enabled
And I am logged as "Administrator" with password "Control*123"
When I disable Impersonation
Then the Impersonation Option is disabled

@IMP-EnableCredentialsAuthentication
Scenario: Change Authentication Type to Credentials
Given authentication type configured as "rfid"
And I am logged as "Administrator" with password "Control*123"	
When I change Authentication to User and Password
Then the Authentication Type is changed to "credentials" 

@IMP-EnableCredentialsAuthentication
Scenario: Change Authentication Type to RFID
Given authentication type configured as "credentials"
And I am logged as "Administrator" with password "Control*123"	
When I change Authentication to RFID
Then the Authentication Type is changed to "rfid"

@IMP-DisableImpersonation
Scenario: Impersonation Options are displayed in the Credentials Page
Given impersonation is disabled
And I am logged as "Administrator" with password "Control*123"
And I enable Impersonation
When I create a new meeting with subject "My Subject" in the Room "Room013"
Then the Impersonation Options displayed in the Credentials Page

Scenario: Impersonation Option is disabled when there is no Email Server Added
Given a user has logged into Room Manager with no email server added 
When there is no Email Server Added to do impersonation
Then Impersonation Option is disabled
