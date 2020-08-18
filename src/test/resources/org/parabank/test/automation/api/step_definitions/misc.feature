Feature: Miscellaneous
  As an customer
  I want to be able to log into the application
  so that I can access my bank account

  Background:
    Given the base url is set to parabank's url

  Scenario: Login with valid credentials
    And the Accept header is set as "application/json"
    When an GET request is sent with path parameters to the login endpoint
      | username | john |
      | password | demo |

    Then the response's status is 200
    And the response's content type is "application/json"
    And the response's body matches the expected body
      | id              | 12212         |
      | firstName       | John          |
      | lastName        | Smith         |
      | address.street  | 1431 Main St  |
      | address.city    | Beverly Hills |
      | address.state   | CA            |
      | address.zipCode | 90210         |
      | phoneNumber     | 310-447-4121  |
      | ssn             | 622-11-9999   |

  Scenario Outline: Login with invalid credentials
    And the Accept header is set as "application/json"
    When an GET request is sent with path parameters to the login endpoint
      | username | <username> |
      | password | <password> |

    Then the response's status is 400
    And the response's content type is "text/plain"
    And the response's body contains message "Invalid username and/or password"

    Examples:
      | username | password |
      | john     | invDemo  |
      | invUser  | demo     |
      | john     |          |
      |          | demo     |
      |          |          |