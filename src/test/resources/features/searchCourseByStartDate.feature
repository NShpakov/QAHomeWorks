Feature: Search courses by date

  Scenario: find and click some course by start date
    Given I open browser Chrome
    When I open main page
    And I find a course by start date "С 30 сентября" and check courseName "Highload Architect"
    Then I close the browser and end the session

  Scenario Outline: find and check courses after input date
    Given I open browser Chrome
    When I open main page
    And I check course <courseId> after start date "<dateAfter>" then check courseName "<courseName>" and courseDate "<startDate>"
    Then I close the browser and end the session
    Examples:
      | courseId | dateAfter     | courseName         | startDate                    |
      | 1        | C 29 сентября | DataOps Engineer   | Wed Sep 29 00:00:00 MSK 2021 |
      | 2        | C 30 сентября | Highload Architect | Thu Sep 30 00:00:00 MSK 2021 |


