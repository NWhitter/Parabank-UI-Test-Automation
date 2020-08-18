Feature: Accounts

  Background:
    Given the base url is set to parabank's url
    And the base path is set to the customers endpoint

  Scenario: Get customer accounts
    And the Accept header is set as "application/json"
    When an GET request is sent with path parameters to the accounts endpoint
      | customerId | 12212 |

    Then the response's status is 200
    And the response's content type is "application/json"
    And the response's body includes the following in any order
      | id       | 12345    |
      | customer | 12212    |
      | type     | CHECKING |
      | balance  | -2200    |

  Scenario Outline: Get customer details
    And the Accept header is set as "application/json"
    When an GET request is sent with path parameters to the customers endpoint
      | customerId | 12212 |

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

    Examples:
      | content_type     |
      | application/json |
      | application/xml  |