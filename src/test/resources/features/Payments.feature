@Returns @Accessibility

Feature: Returns Feature

  @ZAP
  Scenario: A user has multiple outstanding payments
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9001234567 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the Make a payment link
    Then the user is on the outstanding-payments page
#    Click option and check goes to payments service

  Scenario: An error has occured with the payments API and the appropriate warning is displayed
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9001231231 accesses the returns journey
    Then the user is redirected to their IOSS Account
    And the payments text shows that the trader may still owe VAT
#    Warning banner displayed - question with design





