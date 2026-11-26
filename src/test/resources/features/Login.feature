Feature: OrangeHRM Login

  As a valid OrangeHRM user
  I want to log in to the application
  So that I can access the Dashboard

  @sanity @Login
  Scenario Outline: Successful login with valid credentials

    Given User launches the OrangeHRM application
    When User enters username "<username>"
    And User enters password "<password>"
    And User clicks on Login button
    Then User should be navigated to the Dashboard page
    
    Examples:
    	|username | password|
    	|Admin    |admin123 |
    	
    
  @Regression @Login
   Scenario Outline: Unsuccessful login with invalid credentials

    Given User launches the OrangeHRM application
    When User enters username "<username>"
    And User enters password "<password>"
    And User clicks on Login button
    Then User should see an error message

    Examples:
      | username | password   |
      | Admin    | admin1234  |
      | Admin1   | wrong123   |