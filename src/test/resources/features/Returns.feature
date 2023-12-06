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
    And the user selects France on the first soldToCountry page
    And the user ticks the first checkbox on the first vatRatesFromCountry page
    And the user ticks the fifth checkbox on the first vatRatesFromCountry page
    And the user clicks the continue button
    And the user enters first country total sales of 6000 for first selected VAT rate on the sales-to-country page
    And the user confirms the vat for the first EU country as the suggested amount for the first selected VAT rate on the vatOnSales page
    And the user enters first country total sales of 987654.32 for second selected VAT rate on the sales-to-country page
    And the user confirms the vat for the first EU country as the suggested amount for the second selected VAT rate on the vatOnSales page
    And the user answers yes on the add-sales-country-list page
    And the user selects Italy on the second soldToCountry page
    And the user ticks the first checkbox on the second vatRatesFromCountry page
    And the user clicks the continue button
    And the user enters second country total sales of 1234 for first selected VAT rate on the sales-to-country page
    And the user enters a different amount of VAT totalling 120.56 for the second country and the first selected VAT rate on the vatOnSales page
    And the user answers yes on the add-sales-country-list page
    And the user selects Portugal on the third soldToCountry page
    And the user ticks the third checkbox on the third vatRatesFromCountry page
    And the user ticks the fifth checkbox on the third vatRatesFromCountry page
    And the user clicks the continue button
    And the user enters third country total sales of 123.87 for first selected VAT rate on the sales-to-country page
    And the user confirms the vat for the third EU country as the suggested amount for the first selected VAT rate on the vatOnSales page
    And the user enters third country total sales of 6433 for second selected VAT rate on the sales-to-country page
    And the user enters a different amount of VAT totalling 1500 for the third country and the second selected VAT rate on the vatOnSales page
    And the user answers no on the add-sales-country-list page
#   Temporarily adding Correction page until logic is in to check if it is the first return
    And the user answers no on the correct-previous-return page
    Then the user is on the check-your-answers page



