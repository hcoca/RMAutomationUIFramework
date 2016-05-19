Feature: Impersonation

Scenario: A meeting is scheduled using Impersonation
Given impersonation is enabled
And authentication type configured as "credentials"
When I schedule a new meeting with "Subject" subject using Impersonation
Then A meeting with "Subject" subject is created

Scenario: A meeting is cancelled using Impersonation
Given impersonation is enabled
And authentication type configured as "credentials"
And I schedule a new meeting with "My Test Subject" subject using Impersonation
When I cancel a meeting with "My Test Subject" subject using Impersonation
Then A meeting with "My Test Subject" subject is removed

Scenario: Impersonation Options are displayed in the Credentials Page 
when creating a Meeting
Given impersonation is enabled
And authentication type configured as "credentials"
When I try to create a new meeting with "New Subject" subject using Impersonation
Then create Impersonation Options are displayed in the Credentials Page

Scenario: Impersonation Options are displayed in the Credentials Page
when removing a Meeting
Given impersonation is enabled
And authentication type configured as "credentials"
And I schedule a new meeting with "Another Subject" subject using Impersonation
When I try to cancel a meeting with "Another Subject" subject using Impersonation
Then cancel Impersonation Options are displayed in the Credentials Page

Scenario: Impersonation Options are not displayed in the Credentials Page when creating a Meeting with Impersonation disabled
Given impersonation is disabled
And authentication type configured as "credentials"
When I try to create a new meeting with "New Subject" subject
Then create Impersonation Options are not displayed in the Credentials Page

Scenario: Impersonation Options are not displayed in the Credentials Page when removing a Meeting with Impersonation disabled
Given impersonation is disabled
And authentication type configured as "credentials"
And I schedule a new meeting with "Another Subject" subject
When I try to cancel a meeting with "Another Subject" subject
Then cancel Impersonation Options are not displayed in the Credentials Page

Scenario: Credentials Authentication Options are displayed in the Credentials Page when creating a Meeting
Given authentication type configured as "credentials"
When I try to create a new meeting with "My Subject" subject
Then create Credentials Authentication Options are displayed in the Credentials Page

Scenario: Credentials Authentication Options are displayed in the Credentials Page when removing a Meeting
Given authentication type configured as "credentials"
When I schedule a new meeting with "Subject" subject
Then cancel Credentials Authentication Options are displayed in the Credentials Page when cancelling "Subject" meeting

Scenario: Credentials Authentication Options are not displayed in the Credentials Page when creating a Meeting with Credentials authentication disabled
Given authentication type configured as "rfid"
When I try to create a new meeting with "My Subject" subject
Then create Credentials Authentication Options are not displayed in the Credentials Page

Scenario: Credentials Authentication Options are not displayed in the Credentials Page when removing a Meeting with Credentials authentication disabled
Given authentication type configured as "credentials"
And I schedule a new meeting with "Subject" subject
When authentication type configured as "rfid"
Then cancel Credentials Authentication Options are not displayed in the Credentials Page when cancelling "Subject" meeting

Scenario: Credentials RFID Options are displayed in the Credentials
Page when creating a Meeting
Given authentication type configured as "rfid"
When I try to create a new meeting with "My Subject" subject
Then create RFID Authentication Options are displayed in the Credentials Page

Scenario: Credentials RFID Options are displayed in the Credentials Page when removing a Meeting
Given I schedule a new meeting with a "Custom Subject" subject
And authentication type configured as "rfid"
When I try to cancel a meeting with "Custom Subject" subject
Then cancel RFID Authentication Options are displayed in the Credentials Page

Scenario: Credentials RFID Options are not displayed in the Credentials Page when creating a Meeting with RFID authentication disabled
Given authentication type configured as "credentials"
When I try to create a new meeting with "My Subject" subject
Then create RFID Authentication Options are not displayed in the Credentials Page

Scenario: Credentials RFID Options are not displayed in the Credentials Page when removing a Meeting with RFID authentication disabled
Given authentication type configured as "credentials"
And I schedule a new meeting with "My new Subject" subject
When I try to cancel a meeting with "My new Subject" subject
Then cancel RFID Authentication Options are not displayed in the Credentials Page
