@Returns @Accessibility

Feature: Kickouts Feature

  Scenario: A user without an IOSS enrolment cannot access the returns service
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and no IOSS enrolment accesses the returns journey
    Then the user is on the cannot-use-not-registered page

  Scenario: A user answers no to starting their return
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9001234567 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the Start your return link
    Then the user answers no on the 2023-M10/start page
    And the user is on the no-other-periods-available page
    Then the user clicks on the Back to your account link
    And the user is on the your-account page


