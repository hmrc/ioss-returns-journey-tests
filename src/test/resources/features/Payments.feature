@Returns @Accessibility

Feature: Returns Feature

  @ZAP
  Scenario: A user has multiple outstanding payments
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9001234567 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the Make a payment link
    Then the user is on the outstanding-payments page
    When the user selects the second payment option on the outstanding-payments page
#    Check goes to payments service - requires fix in pay api

#  Scenario: A user has a single outstanding payment

  Scenario: An error has occured with the payments API and the dashboard text is amended appropriately
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9001231231 accesses the returns journey
    Then the user is redirected to their IOSS Account
    And the payments text shows that the trader may still owe VAT





