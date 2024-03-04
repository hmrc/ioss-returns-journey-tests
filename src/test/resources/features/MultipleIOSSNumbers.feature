@Return

Feature: Multiple IOSS Numbers Feature

  Scenario: A user with one other IOSS number can view their current account and returns for their previous IOSS number
    Given the user accesses the authority wizard
    And a user with current IOSS Number IM9007230000 and at least one previous IOSS number accesses the returns journey
    Then the user is redirected to their IOSS Account
    And the correct IOSS number IM9007230000 is displayed on the dashboard
    When the user clicks on the View submitted returns link
    Then the user is on the past-returns page
    And the correct link for one previous IOSS number is displayed
#   Click link
#   Check IOSS number
#  And the correct IOSS number IM9006230000 is displayed on the page
#  Click on a return
#  Go back to current registration

  Scenario: A user with more than one other IOSS number can view their current account and returns for their previous IOSS number
    Given the user accesses the authority wizard
    And a user with current IOSS Number IM9007230003 and at least one previous IOSS number accesses the returns journey
    Then the user is redirected to their IOSS Account
    And the correct IOSS number IM9007230003 is displayed on the dashboard
    When the user clicks on the View submitted returns link
    Then the user is on the past-returns page
    And the correct link for more than one previous IOSS number is displayed
#   Click link
#  Select IOSS number
#   Check IOSS number
#    And the correct IOSS number IM9007230003 is displayed on the page
#  Click on a return
#  Go back to current registration
#  Try another IOSS number


