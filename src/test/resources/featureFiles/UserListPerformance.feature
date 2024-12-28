Feature: List users in 200ms

  Scenario Outline: List users in specific time
    Given I have endpoint and query parameter <delay_value>
    When I use GET http method
    Then I should get a response within <milliseconds>

    Examples:
      | milliseconds | delay_value |
      | 100          | 3           |