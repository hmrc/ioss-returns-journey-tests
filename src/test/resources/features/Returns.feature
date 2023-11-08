@Returns @Accessibility

Feature: Returns Feature

  @ZAP
  Scenario: A user accesses the returns service
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9001234567 accesses the returns journey
    Then the user is redirected to their IOSS Account


