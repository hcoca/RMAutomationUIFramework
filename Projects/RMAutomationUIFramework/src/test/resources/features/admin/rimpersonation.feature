Feature: Impersonation

Scenario: Enable Impersonation
Given impersonation is disabled
	And I am logged as "SamuelSahonero" with password "Control*123"
When I enable Impersonation
Then the Impersonation Option is enabled

Scenario: Disable Impersonation
Given impersonation is enabled
	And I am logged as "SamuelSahonero" with password "Control*123"
When I disable Impersonation
Then the Impersonation Option is disabled
    
Scenario: Change Authentication Type to Credentials
Given authentication type configured as "rfid"
	And I am logged as "SamuelSahonero" with password "Control*123"	
When I change Authentication to User and Password
Then the Authentication Type is changed to "credentials" 

Scenario: Change Authentication Type to RFID
Given authentication type configured as "credentials"
	And I am logged as "SamuelSahonero" with password "Control*123"	
When I change Authentication to RFID
Then the Authentication Type is changed to "rfid"
    
Scenario: Impersonation Options are displayed in the Credentials Page
Given impersonation is disabled
	And I am logged as "SamuelSahonero" with password "Control*123"
	And I enable Impersonation
When I create a new meeting on room "Room005"
Then the Impersonation Options displayed in the Credentials Page
    
Scenario: Impersonation Option is disabled when there is no Email Server Added
Given a user "SamuelSahonero" has logged into Room Manager with an email server added
When there is no Email Server Added to do impersonation
Then Impersonation Option is disabled