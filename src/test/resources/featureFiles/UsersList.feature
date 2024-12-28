Feature: List down all the User


  Scenario Outline: List users from a specific page
    Given I have endpoint and query parameter for Page <Page_number>
    When I hit GET http method
    Then I should get a response with Status code <StatusCode>
    And All the Users from Page <Page_number> should be listed

    Examples:
      | StatusCode | Page_number |
      | 200        | 2           |
      | 200        | 4           |