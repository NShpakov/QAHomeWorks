Feature: Open browser and check course name

  Scenario: find course by name
    Given I open browser Chrome
    When I open main page
    Then I find a course by name "Highload Architect"
    And I check that the expected name "Highload Architect" matches the actual "Highload Architect"
    Then I close the browser and end the session