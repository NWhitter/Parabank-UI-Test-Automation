@login
Feature: Login and Logout
  As an customer
  I want to be able to log into the application
  so that I can access my bank account

  Scenario: Potential customer can sign up for an new bank account
    Given the customer is on the sign up page
    When they sign up with the following details
      | first name | Caroline       |
      | last name  | Perez          |
      | address    | 12 West Street |
      | city       | Lewis          |
      | state      | New York       |
      | zip code   | 43035          |
      | phone      | 01836483721    |
      | ssn        | 078-05-1120    |
      | username   | caroline103    |
      | password   | abc123         |
      | confirm    | abc123         |
    Then the account created page should be displayed
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
      | john     | [blank]  | Please enter a username and password.            |
      | [blank]  | demo     | Please enter a username and password.            |
      | [blank]  | [blank]  | Please enter a username and password.            |

  Scenario: Signed-in customer can logout
    Given the customer is on their account overview page
    When they log out
    Then the customer index page should be displayed