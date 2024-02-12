@Returns

Feature: Check Your Answers Feature

  Scenario: A user completes a return then uses Check Your Answers to change "start your return" to no
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9001234567 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the Start your return link
    Then the user answers yes on the 2023-M12/start page
    And the user answers yes on the soldGoods page
    And the user selects France on the first soldToCountry page
    And the user ticks the first checkbox on the first vatRatesFromCountry page
    And the user clicks the continue button
    And the user enters first country total sales of 6000 for first selected VAT rate on the sales-to-country page
    And the user confirms the vat for the first EU country as the suggested amount for the first selected VAT rate on the vatOnSales page
    And the user answers no on the check-sales/1 page
    And the user answers no on the add-sales-country-list page
    And the user answers no on the correct-previous-return page
    Then the user is on the check-your-answers page
    When the user selects the change-return-period change link for page 2023-M12/start from check-your-answers
    Then the user answers no on the 2023-M12/start page
    And the user is on the no-other-periods-available page

  Scenario: A user adds details to a return then uses Check Your Answers to change "Sales made" to no
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9001234567 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the Start your return link
    Then the user answers yes on the 2023-M12/start page
    And the user answers yes on the soldGoods page
    And the user selects France on the first soldToCountry page
    And the user ticks the first checkbox on the first vatRatesFromCountry page
    And the user clicks the continue button
    And the user enters first country total sales of 6000 for first selected VAT rate on the sales-to-country page
    And the user confirms the vat for the first EU country as the suggested amount for the first selected VAT rate on the vatOnSales page
    And the user answers no on the check-sales/1 page
    And the user answers no on the add-sales-country-list page
    And the user answers no on the correct-previous-return page
    Then the user is on the check-your-answers page
    When the user selects the CYA change link for page soldGoods from check-your-answers
    Then the user answers no on the soldGoods page
    And the user is on the check-your-answers page
    And the user clicks the continue button
    Then the user is on the successfully-submitted page

  Scenario: A user completes a nil return then uses Check Your Answers to amend it
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9001234567 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the Start your return link
    Then the user answers yes on the 2023-M12/start page
    And the user answers no on the soldGoods page
    And the user answers no on the correct-previous-return page
    Then the user is on the check-your-answers page
    When the user selects the CYA change link for page soldGoods from check-your-answers
    Then the user answers yes on the soldGoods page
    And the user selects Croatia on the first soldToCountry page
    And the user ticks the first checkbox on the first vatRatesFromCountry page
    And the user clicks the continue button
    And the user enters first country total sales of 1500 for first selected VAT rate on the sales-to-country page
    And the user confirms the vat for the first EU country as the suggested amount for the first selected VAT rate on the vatOnSales page
    Then the user answers yes on the check-sales/1 page
    And the user ticks the second checkbox on the first vatRatesFromCountry page
    And the user clicks the continue button
    And the user enters first country total sales of 147.65 for second selected VAT rate on the sales-to-country page
    And the user enters a different amount of VAT totalling 100.21 for the first country and the second selected VAT rate on the vatOnSales page
    Then the user answers yes on the check-sales/1 page
    And the user answers yes on the remaining-vat-rate-from-country/1/3 page
    And the user enters first country total sales of 16001 for third selected VAT rate on the sales-to-country page
    And the user confirms the vat for the first EU country as the suggested amount for the third selected VAT rate on the vatOnSales page
    Then the user clicks the continue button
    And the user answers yes on the add-sales-country-list page
    And the user selects Germany on the second soldToCountry page
    And the user ticks the second checkbox on the second vatRatesFromCountry page
    And the user clicks the continue button
    And the user enters second country total sales of 3210 for first selected VAT rate on the sales-to-country page
    And the user confirms the vat for the second EU country as the suggested amount for the first selected VAT rate on the vatOnSales page
    And the user answers no on the check-sales/2 page
    And the user answers no on the add-sales-country-list page
    Then the user is on the check-your-answers page
    When the user selects the CYA change link for page correct-previous-return from check-your-answers
    Then the user answers yes on the correct-previous-return page
    When the user picks year 2023 on the correction-return-year/1 page
    When the user picks month October on the correction-return-period/1 page
    And the user chooses the country Bulgaria as their first correction within the first correction period
    And the user answers yes on the add-new-country/1/1 page
    And the user adds 1500 on the first country-vat-correction-amount page for the first correction period
    And the user answers yes on the vat-payable-confirm/1/1 page
    Then the user is on the correction-list-countries/1 page
    And the user answers no on the correction-list-countries/1 page
    When the user answers yes on the 2023-M12/vat-correction-periods-add page
    When the user picks year 2023 on the correction-return-year/2 page
    Then the user picks month November on the correction-return-period/2 page
    And the user chooses the country Bulgaria as their first correction within the second correction period
    And the user answers yes on the add-new-country/2/1 page
    And the user adds 100.25 on the first country-vat-correction-amount page for the second correction period
    And the user answers yes on the vat-payable-confirm/2/1 page
    Then the user is on the correction-list-countries/2 page
    And the user answers no on the correction-list-countries/2 page
    And the user is on the 2023-M12/vat-correction-periods page
    When the user clicks the continue button
    Then the user is on the check-your-answers page
    And the user clicks the continue button
    Then the user is on the successfully-submitted page

  Scenario: A user adds details to a return then uses Check Your Answers to amend it and add more
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9001234567 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the Start your return link
    Then the user answers yes on the 2023-M12/start page
    And the user answers yes on the soldGoods page
    And the user selects France on the first soldToCountry page
    And the user ticks the first checkbox on the first vatRatesFromCountry page
    And the user clicks the continue button
    And the user enters first country total sales of 6000 for first selected VAT rate on the sales-to-country page
    And the user confirms the vat for the first EU country as the suggested amount for the first selected VAT rate on the vatOnSales page
    And the user answers no on the check-sales/1 page
    And the user answers no on the add-sales-country-list page
    And the user answers no on the correct-previous-return page
    Then the user is on the check-your-answers page
    When the user selects the CYA change link for page add-sales-country-list from check-your-answers
    Then the user selects the list change link for first check-sales
    And the user answers yes on the check-sales/1?waypoints=change-add-sales-country-list page
    And the user ticks the first checkbox on the first vatRatesFromCountry page
    And the user clicks the continue button
    And the user enters first country total sales of 125.63 for second selected VAT rate on the sales-to-country page
    And the user confirms the vat for the first EU country as the suggested amount for the second selected VAT rate on the vatOnSales page
    And the user answers no on the check-sales/1 page
    Then the user answers yes on the add-sales-country-list page
    And the user selects Germany on the second soldToCountry page
    And the user ticks the first checkbox on the second vatRatesFromCountry page
    And the user clicks the continue button
    And the user enters second country total sales of 6000 for first selected VAT rate on the sales-to-country page
    And the user confirms the vat for the second EU country as the suggested amount for the first selected VAT rate on the vatOnSales page
    And the user answers no on the check-sales/2 page
    And the user answers no on the add-sales-country-list page
    Then the user is on the check-your-answers page
    And the user clicks the continue button
    Then the user is on the successfully-submitted page

  Scenario: A user adds return data with corrections for multiple years and periods then changes corrections to no on CYA page
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9001234569 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the Start your return link
    Then the user answers yes on the 2024-M1/start page
    And the user answers yes on the soldGoods page
    And the user selects France on the first soldToCountry page
    And the user ticks the first checkbox on the first vatRatesFromCountry page
    And the user ticks the fifth checkbox on the first vatRatesFromCountry page
    And the user clicks the continue button
    And the user enters first country total sales of 6000 for first selected VAT rate on the sales-to-country page
    And the user confirms the vat for the first EU country as the suggested amount for the first selected VAT rate on the vatOnSales page
    And the user enters first country total sales of 987654.32 for second selected VAT rate on the sales-to-country page
    And the user confirms the vat for the first EU country as the suggested amount for the second selected VAT rate on the vatOnSales page
    And the user answers no on the check-sales/1 page
    And the user answers yes on the add-sales-country-list page
    And the user selects Italy on the second soldToCountry page
    And the user ticks the first checkbox on the second vatRatesFromCountry page
    And the user clicks the continue button
    And the user enters second country total sales of 1234 for first selected VAT rate on the sales-to-country page
    And the user enters a different amount of VAT totalling 120.56 for the second country and the first selected VAT rate on the vatOnSales page
    And the user answers no on the check-sales/2 page
    And the user answers no on the add-sales-country-list page
    And the user answers yes on the correct-previous-return page
    When the user picks year 2022 on the correction-return-year/1 page
    When the user picks month October on the correction-return-period/1 page
    And the user chooses the country Denmark as their first correction within the first correction period
    And the user answers yes on the add-new-country/1/1 page
    And the user adds 2222 on the first country-vat-correction-amount page for the first correction period
    And the user answers yes on the vat-payable-confirm/1/1 page
    Then the user is on the correction-list-countries/1 page
    And the user answers no on the correction-list-countries/1 page
    And the user answers yes on the 2024-M1/vat-correction-periods-add page
    When the user picks year 2023 on the correction-return-year/2 page
    When the user picks month December on the correction-return-period/2 page
    And the user chooses the country Slovakia as their first correction within the second correction period
    And the user answers yes on the add-new-country/2/1 page
    And the user adds 1234 on the first country-vat-correction-amount page for the second correction period
    And the user answers yes on the vat-payable-confirm/2/1 page
    Then the user is on the correction-list-countries/2 page
    And the user answers no on the correction-list-countries/2 page
    And the user answers no on the 2024-M1/vat-correction-periods-add page
    Then the user is on the check-your-answers page
    When the user selects the CYA change link for page correct-previous-return from check-your-answers
    Then the user answers no on the correct-previous-return page
    Then the user is on the check-your-answers page
    And the user clicks the continue button
    Then the user is on the successfully-submitted page

  Scenario: A user adds return data with corrections for multiple years and periods then changes corrections via CYA
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9001234569 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the Start your return link
    Then the user answers yes on the 2024-M1/start page
    And the user answers yes on the soldGoods page
    And the user selects France on the first soldToCountry page
    And the user ticks the first checkbox on the first vatRatesFromCountry page
    And the user ticks the fifth checkbox on the first vatRatesFromCountry page
    And the user clicks the continue button
    And the user enters first country total sales of 6000 for first selected VAT rate on the sales-to-country page
    And the user confirms the vat for the first EU country as the suggested amount for the first selected VAT rate on the vatOnSales page
    And the user enters first country total sales of 987654.32 for second selected VAT rate on the sales-to-country page
    And the user confirms the vat for the first EU country as the suggested amount for the second selected VAT rate on the vatOnSales page
    And the user answers no on the check-sales/1 page
    And the user answers yes on the add-sales-country-list page
    And the user selects Italy on the second soldToCountry page
    And the user ticks the first checkbox on the second vatRatesFromCountry page
    And the user clicks the continue button
    And the user enters second country total sales of 1234 for first selected VAT rate on the sales-to-country page
    And the user enters a different amount of VAT totalling 120.56 for the second country and the first selected VAT rate on the vatOnSales page
    And the user answers no on the check-sales/2 page
    And the user answers no on the add-sales-country-list page
    And the user answers yes on the correct-previous-return page
    When the user picks year 2022 on the correction-return-year/1 page
    When the user picks month October on the correction-return-period/1 page
    And the user chooses the country Denmark as their first correction within the first correction period
    And the user answers yes on the add-new-country/1/1 page
    And the user adds 2222 on the first country-vat-correction-amount page for the first correction period
    And the user answers yes on the vat-payable-confirm/1/1 page
    Then the user is on the correction-list-countries/1 page
    And the user answers no on the correction-list-countries/1 page
    And the user answers yes on the 2024-M1/vat-correction-periods-add page
    When the user picks year 2023 on the correction-return-year/2 page
    When the user picks month December on the correction-return-period/2 page
    And the user chooses the country Slovakia as their first correction within the second correction period
    And the user answers yes on the add-new-country/2/1 page
    And the user adds 1234 on the first country-vat-correction-amount page for the second correction period
    And the user answers yes on the vat-payable-confirm/2/1 page
    Then the user is on the correction-list-countries/2 page
    And the user answers no on the correction-list-countries/2 page
    And the user answers no on the 2024-M1/vat-correction-periods-add page
    Then the user is on the check-your-answers page
    When the user selects the correction period change link for page 2024-M1/vat-correction-periods from check-your-answers
    And the user answers yes on the 2024-M1/vat-correction-periods-add page
    When the user picks year 2022 on the correction-return-year/3 page
    When the user picks month December on the correction-return-period/3 page
    And the user chooses the country Slovakia as their first correction within the third correction period
    And the user answers yes on the add-new-country/3/1 page
    And the user adds 1234 on the first country-vat-correction-amount page for the third correction period
    And the user answers yes on the vat-payable-confirm/3/1 page
    Then the user is on the correction-list-countries/3 page
    And the user answers no on the correction-list-countries/3 page
    And the user answers no on the 2024-M1/vat-correction-periods-add page
    Then the user is on the check-your-answers page
    And the user clicks the continue button
    Then the user is on the successfully-submitted page


