Feature: Search courses by date

  Scenario: find and click some course by start date
    Given I open browser Chrome
    When I open main page
    Then I go to page prepared courses
    Then I check that page url equal to value "https://otus.ru/online/"
    And  Maximum price for a course is 15000 rub
    Then Minimum price for a course is 3000 rub
    And I display the name and price of the course in the console
    Then I close the browser and end the session