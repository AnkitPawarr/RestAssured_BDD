Feature: Creating a new User

  Scenario Outline: Admin should be able to create a new user
    Given I have endpoint
    When I hit POST http method
    Then The Status code should be <Status_code>
    And I should get '<Username>' and '<Job>' details in response

    Examples:
      | Status_code | Username | Job  |
      | 201         | ANKIT    | SDET |