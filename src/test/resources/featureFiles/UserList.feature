Feature: List down all the User


  Scenario Outline: List users from Page 2
    Given I have endpoint and query parameter for Page '<Page_number>'
    When I use GET http method
    Then All Users should be listed with a '<Status_Code>'

    Examples:
      | Status_Code | Page_number |
      | 200         | 2           |