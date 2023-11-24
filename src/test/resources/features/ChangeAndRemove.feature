@Returns @Accessibility

Feature: Change and Remove Feature

  Scenario: A user can remove sales to a country in their return
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9001234567 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the Start your return link
    Then the user answers yes on the 2023-M10/start page
    And the user answers yes on the soldGoods page
    And the user enters Poland on the first soldToCountry page
    And the user ticks the first checkbox on the first vatRatesFromCountry page
    And the user clicks the continue button
    And the user enters 10000 on the first salesToCountry page
    And the user confirms the vat for the first EU country as the suggested amount for the first selected VAT rate on the vatOnSales page
    And the user answers yes on the add-sales-country-list page
    And the user enters Germany on the second soldToCountry page
    And the user ticks the first checkbox on the second vatRatesFromCountry page
    And the user clicks the continue button
    And the user enters 3210 on the second salesToCountry page
    And the user confirms the vat for the second EU country as the suggested amount for the first selected VAT rate on the vatOnSales page
    Then the user selects the remove link for remove-sales-country\/1
    And the user answers yes on the remove-sales-country/1 page
    Then the user answers yes on the add-sales-country-list page
    And the user enters Spain on the second soldToCountry page
    And the user ticks the first checkbox on the second vatRatesFromCountry page
    And the user clicks the continue button
    And the user enters 6547 on the second salesToCountry page
    And the user confirms the vat for the second EU country as the suggested amount for the first selected VAT rate on the vatOnSales page
    And the user answers yes on the add-sales-country-list page
    And the user enters Slovenia on the third soldToCountry page
    And the user ticks the first checkbox on the third vatRatesFromCountry page
    And the user clicks the continue button
    And the user enters 9999 on the third salesToCountry page
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
    And the user enters Czech Republic on the first soldToCountry page
    And the user ticks the first checkbox on the first vatRatesFromCountry page
    And the user clicks the continue button
    And the user enters 5544 on the first salesToCountry page
    And the user confirms the vat for the first EU country as the suggested amount for the first selected VAT rate on the vatOnSales page
    And the user answers yes on the add-sales-country-list page
    And the user enters Lithuania on the second soldToCountry page
    And the user ticks the first checkbox on the second vatRatesFromCountry page
    And the user clicks the continue button
    And the user enters 11111 on the second salesToCountry page
    And the user confirms the vat for the second EU country as the suggested amount for the first selected VAT rate on the vatOnSales page
    Then the user selects the remove link for remove-sales-country\/2
    And the user answers yes on the remove-sales-country/2 page
    Then the user selects the remove link for remove-sales-country\/1
    And the user answers yes on the remove-sales-country/1 page
    Then the user answers no on the soldGoods page
    And the user is on the check-your-answers page




