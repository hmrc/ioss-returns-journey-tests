@Returns @Accessibility

Feature: Returns Feature

  @ZAP
  Scenario: A user completes a basic returns journey
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9001234567 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the Start your return link
    Then the user answers yes on the startReturn/2023-M10 page
    And the user answers yes on the soldGoods/2023-M10 page
    And the user enters FR on the first soldToCountry/2023-M10 page
    And the user ticks the first checkbox on the first vatRatesFromCountry/2023-M10 page
    And the user clicks the continue button
    And the user enters 6000 on the first salesToCountry/2023-M10 page
    And the user confirms the vat for the first EU country as the suggested amount for the first selected VAT rate on the vatOnSales/2023-M10 page
    Then the user is on the check-your-answers page



