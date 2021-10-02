Feature: Click course by name

  Scenario: find and click some course by part of course name
    Given I open browser Chrome
    When I open main page
    And I find a course by name filter "i"
    Then I close the browser and end the session

  Scenario: find and click some course by full course name
    Given I open browser Chrome
    When I open main page
    And I find a course by name filter "Highload Architect"
    Then I close the browser and end the session