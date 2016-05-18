Feature: Impersonation

Scenario: A meeting is scheduled using Impersonation
Given impersonation is enabled
And authentication type configured as "credentials"
When I schedule a new meeting with "Subject" subject using Impersonation
Then A meeting with "My Subject" subject is created

Scenario: A meeting is cancelled using Impersonation
Given impersonation is enabled
And authentication type configured as "credentials"
And I schedule a new meeting with "Subject" subject using Impersonation
When I cancel a meeting with "Subject" subject using Impersonation
Then A meeting with "My Subject" subject is removed

Scenario: Impersonation Options are displayed in the Credentials Page 
when creating a Meeting
Given impersonation is enabled
And authentication type configured as "credentials"
When I try to create a new meeting with "My Subject" subject using Impersonation
Then create Impersonation Options are displayed in the Credentials Page

Scenario: Impersonation Options are displayed in the Credentials Page
when removing a Meeting
Given impersonation is enabled
And authentication type configured as "credentials"
And I schedule a new meeting with "Subject" subject using Impersonation
When I try to cancel a meeting with "Subject" subject using Impersonation
Then cancel Impersonation Options are displayed in the Credentials Page