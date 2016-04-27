Feature: Login

Scenario Outline: Sign In to Room Manager
Given Go to Room Manager Admin with an specific <browser>
  And I insert <username>
	And I insert <password>
When I did click on Sig In button
Then Room Manager displays a new page

Examples:
    | browser  | username | password    |
    | Firefox  | Lucero   | Control123  | 