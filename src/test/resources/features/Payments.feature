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
    Then the user has been redirected to the payments service

  Scenario: A user has a single outstanding payment
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9008888888 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the Make a payment link
    Then the user has been redirected to the payments service

  Scenario: A user has no payments due on first return which has not been submitted yet
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9009999888 accesses the returns journey
    Then the user is redirected to their IOSS Account
    And the payments tile shows there are no outstanding payments
    When the user manually navigates to the outstanding payments page
    Then the user does not owe any VAT

  Scenario: A user has no payments due as submitted returns are fully paid
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9008888887 accesses the returns journey
    Then the user is redirected to their IOSS Account
    And the payments tile shows there are no outstanding payments
    When the user manually navigates to the outstanding payments page
    Then the user does not owe any VAT

  Scenario: A user has submitted three returns, one fully paid, one overdue and one due
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9008888886 accesses the returns journey
    Then the user is redirected to their IOSS Account
    And the payments tile shows one payment due and one payment overdue
    When the user clicks on the Make a payment link
    Then the user is on the outstanding-payments page
    When the user selects the first payment option on the outstanding-payments page
    Then the user has been redirected to the payments service

  Scenario: A user has submitted one return and the payment is due
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9008888885 accesses the returns journey
    Then the user is redirected to their IOSS Account
    And the payments tile shows a single payment due
    When the user clicks on the Make a payment link
    Then the user has been redirected to the payments service

  Scenario: An error has occurred with the payments API and the dashboard text is amended appropriately
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9001231231 accesses the returns journey
    Then the user is redirected to their IOSS Account
    And the payments tile shows that the trader may still owe VAT



