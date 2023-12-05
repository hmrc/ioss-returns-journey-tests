@Returns @Accessibility

Feature: Change and Remove Feature

  Scenario: A user can remove sales to a country in their return
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9001234567 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the Start your return link
    Then the user answers yes on the 2023-M10/start page
    And the user answers yes on the soldGoods page
    And the user selects Poland on the first soldToCountry page
    And the user ticks the first checkbox on the first vatRatesFromCountry page
    And the user ticks the second checkbox on the first vatRatesFromCountry page
    And the user clicks the continue button
    And the user enters first country total sales of 10000 for first selected VAT rate on the sales-to-country page
    And the user confirms the vat for the first EU country as the suggested amount for the first selected VAT rate on the vatOnSales page
    And the user enters first country total sales of 12345 for second selected VAT rate on the sales-to-country page
    And the user enters a different amount of VAT totalling 10500 for the first country and the second selected VAT rate on the vatOnSales page
    And the user answers yes on the add-sales-country-list page
    And the user selects Germany on the second soldToCountry page
    And the user ticks the second checkbox on the second vatRatesFromCountry page
    And the user clicks the continue button
    And the user enters second country total sales of 3210 for first selected VAT rate on the sales-to-country page
    And the user confirms the vat for the second EU country as the suggested amount for the first selected VAT rate on the vatOnSales page
    Then the user selects the remove link for remove-sales-country\/1
    And the user answers yes on the remove-sales-country/1 page
    Then the user answers yes on the add-sales-country-list page
    And the user selects Spain on the second soldToCountry page
    And the user ticks the first checkbox on the second vatRatesFromCountry page
    And the user clicks the continue button
    And the user enters second country total sales of 6547 for first selected VAT rate on the sales-to-country page
    And the user confirms the vat for the second EU country as the suggested amount for the first selected VAT rate on the vatOnSales page
    And the user answers yes on the add-sales-country-list page
    And the user selects Slovenia on the third soldToCountry page
    And the user ticks the first checkbox on the third vatRatesFromCountry page
    And the user clicks the continue button
    And the user enters third country total sales of 9999 for first selected VAT rate on the sales-to-country page
    And the user confirms the vat for the third EU country as the suggested amount for the first selected VAT rate on the vatOnSales page
    And the user answers no on the add-sales-country-list page
    Then the user is on the check-your-answers page

  Scenario: A user can add some sales then remove them all in their return
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9001234567 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the Start your return link
    Then the user answers yes on the 2023-M10/start page
    And the user answers yes on the soldGoods page
    And the user selects Czech Republic on the first soldToCountry page
    And the user ticks the first checkbox on the first vatRatesFromCountry page
    And the user clicks the continue button
    And the user enters first country total sales of 5544 for first selected VAT rate on the sales-to-country page
    And the user confirms the vat for the first EU country as the suggested amount for the first selected VAT rate on the vatOnSales page
    And the user answers yes on the add-sales-country-list page
    And the user selects Lithuania on the second soldToCountry page
    And the user ticks the first checkbox on the second vatRatesFromCountry page
    And the user clicks the continue button
    And the user enters second country total sales of 11111 for first selected VAT rate on the sales-to-country page
    And the user confirms the vat for the second EU country as the suggested amount for the first selected VAT rate on the vatOnSales page
    Then the user selects the remove link for remove-sales-country\/2
    And the user answers yes on the remove-sales-country/2 page
    Then the user selects the remove link for remove-sales-country\/1
    And the user answers yes on the remove-sales-country/1 page
    Then the user answers no on the soldGoods page
    And the user is on the check-your-answers page




