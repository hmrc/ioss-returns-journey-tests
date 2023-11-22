@Returns @Accessibility

Feature: Kickouts Feature

  Scenario: A user without an IOSS enrolment cannot access the returns service
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and no IOSS enrolment accesses the returns journey
    Then the user is on the cannot-use-not-registered page


