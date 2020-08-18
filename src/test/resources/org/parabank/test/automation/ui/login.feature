@login
Feature: Login and Logout
  As an customer
  I want to be able to log into the application
  so that I can access my bank account

  Scenario: Potential customer can sign up for an new bank account
    Given the customer is on the sign up page
    When they signs up with the following details
      | First Name | Caroline       |
      | Last Name  | Perez          |
      | Address    | 12 West Street |
      | City       | Lewis          |
      | State      | New York       |
      | Zip Code   | 43035          |
      | Phone      | 01836483721    |
      | SSN        | 078-05-1120    |
      | Username   | caroline103    |
      | Password   | abc123         |
      | Confirm    | abc123         |
    Then their accounts overview page should be displayed
    And the message 'Your account was created successfully. You are now logged in.' should be displayed

  Scenario: Customer can login with valid credentials
    Given the customer is on the customer login page
    When they sign in with the following details
      | username | john |
      | password | demo |
    Then their accounts overview page should be displayed

  Scenario Outline: Customer cannot login with invalid credentials
    Given the customer is on the customer login page
    When they sign in with the following details
      | username | <username> |
      | password | <password> |
    Then the customer login page should be displayed
    And the error title 'Error!' should be displayed
    And the error message '<message>' should be displayed

    Examples:
      | username | password | message                                          |
      | john     | invDemo  | The username and password could not be verified. |
      | invUser  | demo     | The username and password could not be verified. |
      | john     |          | Please enter a username and password.            |
      |          | demo     | Please enter a username and password.            |
      |          |          | Please enter a username and password.            |

  Scenario: Signed-in customer can logout
    Given the customer is on their account overview page
    When they log out
    Then the customer index page should be displayed