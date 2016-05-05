#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios 
#<> (placeholder)
#""
## (Comments)

#Sample Feature Definition Template
@tag
Feature: Impersonation

@tag1
Scenario: Enable Impersonation
Given the user "<name>" has logged in
When he enables Impersonation
Then the Impersonation Option is enabled

@tag2
Scenario Outline: Disable Impersonation
Given the user "<name>" has logged in
When he disables Impersonation
Then the Impersonation Option is disabled

@tag3
Scenario Outline: Change Authentication Type to Credentials
Given the user "<name>" has logged in
When he changes Authentication to User and Password
Then the Authentication Type is changed to Credentials

@tag4
Scenario Outline: Change Authentication Type to RFID
Given the user "<name>" has logged in
When he changes Authentication to RFID
Then the Authentication Type is changed to RFID

@tag5
Scenario Outline: Impersonation Option is disabled when there is no Email Server Added
Given the user "<name>" has logged in
When there is no Email Server Added
Then the Impersonation Option is disabled

@tag6
Scenario Outline: Impersonation Options are displayed in the Credentials Page
Given the user "<name>" has logged in
And he enables Impersonation
When he tries to create a new meeting
Then the Impersonation Options displayed in the Credentials Page

Examples:
    | name  				| password    |
    | SamuelSahonero| Control123! |
