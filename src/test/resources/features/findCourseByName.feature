Feature: Open browser and check course name

  Scenario: find course by name
    Given I open browser Chrome
    When I open main page
    Then I find a course by name "DataOps Engineer"
    And I check that the expected name "DataOps Engineer" matches the actual "DataOps Engineer"
    Then I close the browser and end the session