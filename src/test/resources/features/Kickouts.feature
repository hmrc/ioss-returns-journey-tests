@Returns @Accessibility

Feature: Returns Feature

  Scenario: A user accesses the returns service
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and no IOSS enrolment accesses the returns journey
    Then the user is on the cannot-use-not-registered page


