@Returns @Accessibility

Feature: Past Returns Feature

  Scenario: A user can view previously submitted returns for October and November 2023
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9001234567 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the View submitted returns link
    Then the user is on the past-returns page
    And the user clicks the Show all sections accordion
    When the user clicks on the November 2023 link
    Then the user is on the past-returns/2023-M11 page
    And the return for November 2023 is displayed to the user
    And the correct sections are displayed on the previous return with sales to EU and corrections
    When the user clicks on the Pay Now button
    Then the user has been redirected to the payments service
    When the user manually navigates to the returns service
    Then the user clicks on the View submitted returns link
    And the user is on the past-returns page
    And the user clicks the Show all sections accordion
    When the user clicks on the October 2023 link
    Then the user is on the past-returns/2023-M10 page
    And the return for October 2023 is displayed to the user
    And the correct sections are displayed on the previous return with sales to EU and corrections
    When the user manually navigates to the returns service
    Then the user clicks on the View submitted returns link
    And the user is on the past-returns page
    And the user clicks the Show all sections accordion
    When the user clicks the Pay now link for October 2023
    Then the user has been redirected to the payments service

  Scenario: A user can view previously submitted returns over multiple years
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9008888882 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the View submitted returns link
    Then the user is on the past-returns page
    And the user clicks the Show all sections accordion
    When the user clicks on the November 2022 link
    Then the user is on the past-returns/2022-M11 page
    And the return for November 2022 is displayed to the user
    And the correct sections are displayed on the previous return with sales to EU and corrections
    When the user manually navigates to the returns service
    Then the user clicks on the View submitted returns link
    And the user is on the past-returns page
    And the user clicks the Show all sections accordion
    When the user clicks on the December 2022 link
    Then the user is on the past-returns/2022-M12 page
    And the return for December 2022 is displayed to the user
    And the correct sections are displayed on the previous return with sales to EU and corrections
    When the user manually navigates to the returns service
    Then the user clicks on the View submitted returns link
    And the user is on the past-returns page
    And the user clicks the Show all sections accordion
    When the user clicks on the December 2023 link
    Then the user is on the past-returns/2023-M12 page
    And the return for December 2023 is displayed to the user
    And the correct sections are displayed on the previous return with sales to EU and corrections

  Scenario: A user can view a previously submitted nil return
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9003333333 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the View submitted returns link
    Then the user is on the past-returns page
    And the user clicks the Show all sections accordion
    When the user clicks on the November 2023 link
    Then the user is on the past-returns/2023-M11 page
    And the return for November 2023 is displayed to the user
    Then the correct sections are displayed for a nil return

  Scenario: A user can view a previously submitted return with no corrections
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9004444444 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the View submitted returns link
    Then the user is on the past-returns page
    And the user clicks the Show all sections accordion
    When the user clicks on the November 2023 link
    Then the user is on the past-returns/2023-M11 page
    And the return for November 2023 is displayed to the user
    And the correct sections are displayed on the previous return with no corrections

  Scenario: A user is not able to submit a duplicate return
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9001234567 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user manually navigates to their November 2023 return
#  now incorrectly letting me start the return
    Then the user is on the past-returns/2023-M11 page


