@Returns @Accessibility

Feature: Past Returns Feature

  Scenario: A user can view a previously submitted return for November 2023
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9001234567 accesses the returns journey
    Then the user is redirected to their IOSS Account
#    Manual navigation until dashboard link is in
    When the user manually navigates to the previous submitted return for November 2023
    Then the user is on the past-returns/2023-M11 page
    And the return for November 2023 is displayed to the user
    And the correct sections are displayed on the previous return with sales to EU and corrections
#    Pay now button has not been implemented yet
    When the user clicks on the your-account breadcrumb
    Then the user is on the your-account page

  Scenario: A user can view a previously submitted nil return
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9003333333 accesses the returns journey
    Then the user is redirected to their IOSS Account
#    Manual navigation until dashboard link is in
    When the user manually navigates to the previous submitted return for November 2023
    Then the user is on the past-returns/2023-M11 page
    And the return for November 2023 is displayed to the user
#    Add checks to page once fixes for VEIOSS-491 goes in
    When the user clicks on the your-account breadcrumb
    Then the user is on the your-account page

  Scenario: A user can view a previously submitted return with no corrections
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9004444444 accesses the returns journey
    Then the user is redirected to their IOSS Account
#    Manual navigation until dashboard link is in
    When the user manually navigates to the previous submitted return for November 2023
    Then the user is on the past-returns/2023-M11 page
    And the return for November 2023 is displayed to the user
    And the correct sections are displayed on the previous return with no corrections
#    Pay now button has not been implemented yet
    When the user clicks on the your-account breadcrumb
    Then the user is on the your-account page




