Feature: Impersonation

Scenario Outline: Enable Impersonation
Given the user "<name>" has logged in
When he enables Impersonation
Then the Impersonation Option is enabled

Examples:
    | name  |
    | SamuelSahonero  |

Scenario Outline: Disable Impersonation
Given the user "<name>" has logged into Room Manager
When he disables Impersonation
Then the Impersonation Option is disabled

Examples:
    | name  |
    | SamuelSahonero  |
    
Scenario Outline: Change Authentication Type to Credentials
Given a user has logged in
When he changes Authentication to User and Password
Then the Authentication Type is changed to Credentials

Examples:
    | name  |
    | SamuelSahonero  |

Scenario Outline: Change Authentication Type to RFID
Given a user has logged into Room Manager
When he changes Authentication to RFID
Then the Authentication Type is changed to RFID

Examples:
    | name  |
    | SamuelSahonero  |
    
Scenario Outline: Impersonation Options are displayed in the Credentials Page
Given a user "<name>" that has logged in
And he enables Impersonation Option
When he tries to create a new meeting
Then the Impersonation Options displayed in the Credentials Page

Examples:
    | name  |
    | SamuelSahonero  |
    
Scenario Outline: Impersonation Option is disabled when there is no Email Server Added
Given a user "<name>" has logged into Room Manager
When there is no Email Server Added
Then Impersonation Option is disabled

Examples:
    | name  |
    | SamuelSahonero  |