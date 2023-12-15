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
    And the user answers no on the check-sales/1 page
    And the user answers yes on the add-sales-country-list page
    And the user selects Germany on the second soldToCountry page
    And the user ticks the second checkbox on the second vatRatesFromCountry page
    And the user clicks the continue button
    And the user enters second country total sales of 3210 for first selected VAT rate on the sales-to-country page
    And the user confirms the vat for the second EU country as the suggested amount for the first selected VAT rate on the vatOnSales page
    And the user answers no on the check-sales/2 page
    Then the user selects the remove link for remove-sales-country\/1
    And the user answers yes on the remove-sales-country/1 page
    Then the user answers yes on the add-sales-country-list page
    And the user selects Spain on the second soldToCountry page
    And the user ticks the first checkbox on the second vatRatesFromCountry page
    And the user clicks the continue button
    And the user enters second country total sales of 6547 for first selected VAT rate on the sales-to-country page
    And the user confirms the vat for the second EU country as the suggested amount for the first selected VAT rate on the vatOnSales page
    And the user answers no on the check-sales/2 page
    And the user answers yes on the add-sales-country-list page
    And the user selects Slovenia on the third soldToCountry page
    And the user ticks the first checkbox on the third vatRatesFromCountry page
    And the user clicks the continue button
    And the user enters third country total sales of 9999 for first selected VAT rate on the sales-to-country page
    And the user confirms the vat for the third EU country as the suggested amount for the first selected VAT rate on the vatOnSales page
    And the user answers no on the check-sales/3 page
    And the user answers no on the add-sales-country-list page
  #  Temporarily adding Correction page until logic is in to check if it is the first return
    And the user answers no on the correct-previous-return page
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
    And the user answers no on the check-sales/1 page
    And the user answers yes on the add-sales-country-list page
    And the user selects Lithuania on the second soldToCountry page
    And the user ticks the first checkbox on the second vatRatesFromCountry page
    And the user clicks the continue button
    And the user enters second country total sales of 11111 for first selected VAT rate on the sales-to-country page
    And the user confirms the vat for the second EU country as the suggested amount for the first selected VAT rate on the vatOnSales page
    And the user answers no on the check-sales/2 page
    Then the user selects the remove link for remove-sales-country\/2
    And the user answers yes on the remove-sales-country/2 page
    Then the user selects the remove link for remove-sales-country\/1
    And the user answers yes on the remove-sales-country/1 page
    Then the user answers no on the soldGoods page
#  Temporarily adding Correction page until logic is in to check if it is the first return
    And the user answers no on the correct-previous-return page
    And the user is on the check-your-answers page

  Scenario: A user can change and remove answers via the mini CYA for VAT rates
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9001234567 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the Start your return link
    Then the user answers yes on the 2023-M10/start page
    And the user answers yes on the soldGoods page
    And the user selects Denmark on the first soldToCountry page
    And the user enters first country total sales of 10000 for first selected VAT rate on the sales-to-country page
    And the user confirms the vat for the first EU country as the suggested amount for the first selected VAT rate on the vatOnSales page
    Then the user is on the check-sales/1 page
    And the user clicks the continue button
    Then the user answers yes on the add-sales-country-list page
    And the user selects Slovakia on the second soldToCountry page
    And the user ticks the second checkbox on the second vatRatesFromCountry page
    And the user clicks the continue button
    And the user enters second country total sales of 6321 for first selected VAT rate on the sales-to-country page
    And the user enters a different amount of VAT totalling 600 for the second country and the first selected VAT rate on the vatOnSales page
    Then the user is on the check-sales/2 page
    Then the user selects the mini CYA change link for first vatOnSales\/2
    And the user confirms the vat for the second EU country as the suggested amount for the first selected VAT rate on the vatOnSales page
    Then the user is on the check-sales/2 page
    And the user answers yes on the check-sales/2 page
    And the user ticks the second checkbox on the second vatRatesFromCountry page
    And the user clicks the continue button
    And the user enters second country total sales of 123.11 for second selected VAT rate on the sales-to-country page
    And the user confirms the vat for the second EU country as the suggested amount for the second selected VAT rate on the vatOnSales page
    Then the user selects the mini CYA change link for second sales-to-country\/2
    And the user changes second country total sales of 641 for second selected VAT rate on the sales-to-country page
    And the user confirms the vat for the second EU country as the suggested amount for the second selected VAT rate on the vatOnSales page
    Then the user is on the check-sales/2 page
    And the user answers yes on the check-sales/2 page
    And the user answers yes on the remaining-vat-rate-from-country/2/3 page
    And the user enters second country total sales of 1400 for third selected VAT rate on the sales-to-country page
    And the user confirms the vat for the second EU country as the suggested amount for the third selected VAT rate on the vatOnSales page
    Then the user clicks the continue button
    Then the user selects the list change link for second check-sales
    And the user selects the remove link for remove-vat-rate-sales-for-country\/2\/2
    And the user answers yes on the remove-vat-rate-sales-for-country/2/2 page
#    ug VEIOSS-423 for going to add-sales-country-list instead of check-sales/2
#   Then the user answers yes on the check-sales/2 page
#   temporary navigation step
    Then the user selects the list change link for second check-sales
    Then the user selects the mini CYA change link for second vatOnSales\/2
    And the user enters a different amount of VAT totalling 250.11 for the second country and the second selected VAT rate on the vatOnSales page
    Then the user answers no on the check-sales/2 page
    And the user answers no on the add-sales-country-list page
    #  Temporarily adding Correction page until logic is in to check if it is the first return
    And the user answers no on the correct-previous-return page
    And the user is on the check-your-answers page

  Scenario: A user can remove all answers via the mini CYA for VAT rates
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9001234567 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the Start your return link
    Then the user answers yes on the 2023-M10/start page
    And the user answers yes on the soldGoods page
    And the user selects Croatia on the first soldToCountry page
    And the user ticks the first checkbox on the first vatRatesFromCountry page
    And the user ticks the second checkbox on the first vatRatesFromCountry page
    And the user ticks the third checkbox on the first vatRatesFromCountry page
    And the user clicks the continue button
    And the user enters first country total sales of 10000 for first selected VAT rate on the sales-to-country page
    And the user confirms the vat for the first EU country as the suggested amount for the first selected VAT rate on the vatOnSales page
    And the user enters first country total sales of 12345.23 for second selected VAT rate on the sales-to-country page
    And the user enters a different amount of VAT totalling 10500.21 for the first country and the second selected VAT rate on the vatOnSales page
    And the user enters first country total sales of 98765 for third selected VAT rate on the sales-to-country page
    And the user confirms the vat for the first EU country as the suggested amount for the third selected VAT rate on the vatOnSales page
    Then the user is on the check-sales/1 page
    When the user selects the remove link for remove-vat-rate-sales-for-country\/1\/2
    Then the user answers yes on the remove-vat-rate-sales-for-country/1/2 page
    And the user selects the remove link for remove-vat-rate-sales-for-country\/1\/1
    And the user answers yes on the remove-vat-rate-sales-for-country/1/1 page
    And the user selects the remove link for remove-vat-rate-sales-for-country\/1\/1
    And the user answers yes on the remove-vat-rate-sales-for-country/1/1 page
    Then the user is on the vatRatesFromCountry/1 page
    And the user ticks the third checkbox on the first vatRatesFromCountry page
    And the user clicks the continue button
    And the user enters first country total sales of 2000 for first selected VAT rate on the sales-to-country page
    And the user confirms the vat for the first EU country as the suggested amount for the first selected VAT rate on the vatOnSales page
    And the user answers no on the check-sales/1 page
    And the user answers no on the add-sales-country-list page
    #   Temporarily adding Correction page until logic is in to check if it is the first return
    And the user answers no on the correct-previous-return page
    Then the user is on the check-your-answers page

  Scenario: A user can change answers via the country list in their return
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9001234567 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the Start your return link
    Then the user answers yes on the 2023-M10/start page
    And the user answers yes on the soldGoods page
    And the user selects Finland on the first soldToCountry page
    And the user ticks the second checkbox on the first vatRatesFromCountry page
    And the user clicks the continue button
    And the user enters first country total sales of 1234 for first selected VAT rate on the sales-to-country page
    And the user confirms the vat for the first EU country as the suggested amount for the first selected VAT rate on the vatOnSales page
    And the user answers no on the check-sales/1 page
    Then the user selects the list change link for first check-sales
    And the user answers yes on the check-sales/1 page
    And the user ticks the first checkbox on the first vatRatesFromCountry page
    And the user clicks the continue button
    And the user enters first country total sales of 2222.33 for second selected VAT rate on the sales-to-country page
    And the user confirms the vat for the first EU country as the suggested amount for the second selected VAT rate on the vatOnSales page
    Then the user answers yes on the add-sales-country-list page
    And the user selects Malta on the second soldToCountry page
    And the user ticks the second checkbox on the second vatRatesFromCountry page
    And the user ticks the third checkbox on the second vatRatesFromCountry page
    And the user clicks the continue button
    And the user enters second country total sales of 3333 for first selected VAT rate on the sales-to-country page
    And the user confirms the vat for the second EU country as the suggested amount for the first selected VAT rate on the vatOnSales page
    And the user enters second country total sales of 654 for second selected VAT rate on the sales-to-country page
    And the user enters a different amount of VAT totalling 10500 for the second country and the second selected VAT rate on the vatOnSales page
    And the user answers no on the check-sales/2 page
    Then the user answers no on the add-sales-country-list page
      #   Temporarily adding Correction page until logic is in to check if it is the first return
    And the user answers no on the correct-previous-return page
    Then the user is on the check-your-answers page
@wip
  Scenario: A user can change answers via the correction country list - previously undeclared
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9001234567 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the Start your return link
    Then the user answers yes on the 2023-M10/start page
    And the user answers no on the soldGoods page
#   Logic to check if it is the first return does not exist yet
    And the user answers yes on the correct-previous-return page
    Then the user manually navigates to the first correction country
    And the user chooses the country Spain as their first correction within the first correction period
    And the user answers yes on the add-new-country/1/1 page
    And the user adds 11111 on the first country-vat-correction-amount page for the first correction period
    And the user answers yes on the vat-payable-confirm/1/1 page
    Then the user is on the correction-list-countries/1 page
    And the user answers yes on the correction-list-countries/1 page
    And the user chooses the country Germany as their second correction within the first correction period
    And the user answers yes on the add-new-country/1/2 page
    And the user adds 1234.56 on the second country-vat-correction-amount page for the first correction period
    And the user answers yes on the vat-payable-confirm/1/2 page
    Then the user is on the correction-list-countries/1 page
#    Change answers and go round loop to add another correction

    And the user answers no on the correction-list-countries/1 page
    Then the user is on the check-your-answers page

  Scenario: A user can remove answers via the correction country list - previously undeclared


