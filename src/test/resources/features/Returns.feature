@Returns @Accessibility

Feature: Returns Feature

  @ZAP
  Scenario: A user adds sales for multiple countries to a return
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9001234567 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the Start your return link
    Then the user answers yes on the 2023-M10/start page
    And the user answers yes on the soldGoods page
    And the user enters France on the first soldToCountry page
    And the user ticks the first checkbox on the first vatRatesFromCountry page
    And the user clicks the continue button
    And the user enters 6000 on the first sales-to-country page
    And the user confirms the vat for the first EU country as the suggested amount for the first selected VAT rate on the vatOnSales page
    And the user answers yes on the add-sales-country-list page
    And the user enters Italy on the second soldToCountry page
    And the user ticks the first checkbox on the second vatRatesFromCountry page
    And the user clicks the continue button
    And the user enters 1234 on the second sales-to-country page
    And the user confirms the vat for the second EU country as the suggested amount for the first selected VAT rate on the vatOnSales page
    And the user answers no on the add-sales-country-list page
#   Temporarily adding Correction page until logic is in to check if it is the first return
    And the user answers no on the correct-previous-return page
    Then the user is on the check-your-answers page



