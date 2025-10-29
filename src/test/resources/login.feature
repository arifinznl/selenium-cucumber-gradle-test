@login
Feature: Login

  @valid-login
  Scenario: Login using valid email and password
    Given user is on login page
    And user input username with "standard_user"
    And user input password with "secret_sauce"
    When user click login button
    Then user is on homepage

  @invalid-login
  Scenario: Login fails with error message
    Given user is on login page
    And user input username with "standard_user"
    And user input password with "invalid"
    When user click login button
    Then user able to see error message "Epic sadface: Username and password do not match any user in this service"

  @empty-username
  Scenario: Login with empty username
    Given user is on login page
    And user input username with ""
    And user input password with "secret_sauce"
    When user click login button
    Then user able to see error message "Epic sadface: Username is required"

  @empty-password
  Scenario: Login with empty password
    Given user is on login page
    And user input username with "standard_user"
    And user input password with ""
    When user click login button
    Then user able to see error message "Epic sadface: Password is required"

  @case-sensitive
  Scenario: Login with uppercase username
    Given user is on login page
    And user input username with "STANDARD_USER"
    And user input password with "secret_sauce"
    When user click login button
    Then user able to see error message "Epic sadface: Username and password do not match any user in this service"