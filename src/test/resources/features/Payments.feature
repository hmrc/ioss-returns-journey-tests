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

  Scenario: A user has a single outstanding payment
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9008888888 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the Make a payment link
    #    Check goes to payments service - requires fix in pay api

  Scenario: A user has no payments due
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9009999888 accesses the returns journey
    Then the user is redirected to their IOSS Account
    And the payments tile shows there are no outstanding payments
    When the user manually navigates to the outstanding payments page
    Then the user does not owe any VAT

    #  Manual - combinations of payments due
#     - overdue/payment deadline not passed yet?/multiple/one etc

#  Manual - payments on past returns

  Scenario: An error has occured with the payments API and the dashboard text is amended appropriately
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9001231231 accesses the returns journey
    Then the user is redirected to their IOSS Account
    And the payments tile shows that the trader may still owe VAT



