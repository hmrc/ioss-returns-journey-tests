@Returns @Accessibility

Feature: Returns Feature

  @ZAP
  Scenario: A user accesses the returns service
    Given the user accesses the service
    And the user signs into authority wizard as an Organisation Admin with VAT enrolment 100000002
    Then the user is directed back to the index page


