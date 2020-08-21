@NewAccount
Feature: Open new account

  Scenario: Customer can open a new account
    Given the customer is on the open new account page
    When they open a new account with the following details
      | account type | transfer account |
      | Checking     | 13344            |
    Then their account should be opened
    And the new account should have an 'funds transfer received' transaction
    And the transfer account should have an 'funds transfer' transaction

    Scenario: Customer can view account details
      Given the customer has an account
      And they are on the accounts overview page
      When they view the account's details
      Then the account details