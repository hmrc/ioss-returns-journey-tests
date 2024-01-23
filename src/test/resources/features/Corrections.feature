@Returns @Accessibility

Feature: Corrections Feature

  @ZAP
  Scenario: A simple corrections journey with previously undeclared countries added to a nil return
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9008888888 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the Start your return link
    Then the user answers yes on the 2023-M10/start page
    And the user answers no on the soldGoods page
    And the user answers yes on the correct-previous-return page
    Then the user answers yes on the correction-return-single-period/1 page
    And the user chooses the country Estonia as their first correction within the first correction period
    And the user answers yes on the add-new-country/1/1 page
    And the user adds 1500 on the first country-vat-correction-amount page for the first correction period
    And the user answers yes on the vat-payable-confirm/1/1 page
    Then the user is on the correction-list-countries/1 page
    And the user answers yes on the correction-list-countries/1 page
    And the user chooses the country Portugal as their second correction within the first correction period
    And the user answers yes on the add-new-country/1/2 page
    And the user adds 160.36 on the second country-vat-correction-amount page for the first correction period
    And the user answers yes on the vat-payable-confirm/1/2 page
    Then the user is on the correction-list-countries/1 page
    And the user answers no on the correction-list-countries/1 page
    Then the user is on the check-your-answers page
    And the user clicks the continue button
    Then the user is on the successfully-submitted page

  Scenario: A user is offered a single period to correct and chooses No
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9008888888 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the Start your return link
    Then the user answers yes on the 2023-M10/start page
    And the user answers no on the soldGoods page
    And the user answers yes on the correct-previous-return page
    When the user answers no on the correction-return-single-period/1 page
    Then the user is on the no-correction-periods-available page
    And the user clicks the continue button
    Then the user is on the check-your-answers page
    And the user clicks the continue button
    Then the user is on the successfully-submitted page

  Scenario: A user has corrections available but selects no to adding any
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9001234567 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the Start your return link
    Then the user answers yes on the 2023-M10/start page
    And the user answers no on the soldGoods page
    And the user answers no on the correct-previous-return page
    Then the user is on the check-your-answers page
    And the user clicks the continue button
    Then the user is on the successfully-submitted page

  Scenario: A user has corrections available for multiple months within the same year
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9001234568 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the Start your return link
    Then the user answers yes on the 2023-M10/start page
    And the user answers no on the soldGoods page
    And the user answers yes on the correct-previous-return page
    When the user picks year 2023 on the correction-return-year/1 page
    When the user picks month October on the correction-return-period/1 page
    And the user chooses the country Bulgaria as their first correction within the first correction period
    And the user answers yes on the add-new-country/1/1 page
    And the user adds 1500 on the first country-vat-correction-amount page for the first correction period
    And the user answers yes on the vat-payable-confirm/1/1 page
    Then the user is on the correction-list-countries/1 page
    And the user answers yes on the correction-list-countries/1 page
    And the user chooses the country Czech Republic as their second correction within the first correction period
    And the user answers yes on the add-new-country/1/2 page
    And the user adds 160.36 on the second country-vat-correction-amount page for the first correction period
    And the user answers yes on the vat-payable-confirm/1/2 page
    And the user answers no on the correction-list-countries/1 page
    When the user answers yes on the 2023-M10/vat-correction-periods-add page
    Then the user picks month November on the correction-return-period/2 page
    And the user chooses the country Bulgaria as their first correction within the second correction period
    And the user answers yes on the add-new-country/2/1 page
    And the user adds 100.25 on the first country-vat-correction-amount page for the second correction period
    And the user answers yes on the vat-payable-confirm/2/1 page
    Then the user is on the correction-list-countries/2 page
    And the user answers yes on the correction-list-countries/2 page
    And the user chooses the country Slovakia as their second correction within the second correction period
    And the user answers yes on the add-new-country/2/2 page
    And the user adds 1450 on the second country-vat-correction-amount page for the second correction period
    And the user answers yes on the vat-payable-confirm/2/2 page
    Then the user is on the correction-list-countries/2 page
    And the user answers no on the correction-list-countries/2 page
#    Got to /2023-M10/vat-correction-periods page and it's only giving me the option to continue but not add another period, however should have december
    Then the user clicks the continue button
    Then the user is on the check-your-answers page
    And the user clicks the continue button
    Then the user is on the successfully-submitted page

  Scenario: A user has corrections available for multiple years
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9001234569 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the Start your return link
    Then the user answers yes on the 2023-M10/start page
    And the user answers no on the soldGoods page
    And the user answers yes on the correct-previous-return page
    When the user picks year 2022 on the correction-return-year/1 page
    When the user picks month October on the correction-return-period/1 page
    And the user chooses the country Denmark as their first correction within the first correction period
    And the user answers yes on the add-new-country/1/1 page
    And the user adds 2222 on the first country-vat-correction-amount page for the first correction period
    And the user answers yes on the vat-payable-confirm/1/1 page
    Then the user is on the correction-list-countries/1 page
    And the user answers no on the correction-list-countries/1 page
#    Will need to go to corrections list to be able to add corrections for other years and months
    Then the user is on the check-your-answers page
    And the user clicks the continue button
    Then the user is on the successfully-submitted page

  Scenario: A user has corrections available for a single period
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9008888888 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the Start your return link
    Then the user answers yes on the 2023-M10/start page
    And the user answers no on the soldGoods page
    And the user answers yes on the correct-previous-return page
    And the user answers yes on the correction-return-single-period/1 page
    And the user chooses the country Portugal as their first correction within the first correction period
    And the user answers yes on the add-new-country/1/1 page
    And the user adds 1500.24 on the first country-vat-correction-amount page for the first correction period
    And the user answers yes on the vat-payable-confirm/1/1 page
    Then the user is on the correction-list-countries/1 page
    And the user answers yes on the correction-list-countries/1 page
    And the user chooses the country Romania as their second correction within the first correction period
    And the user answers yes on the add-new-country/1/2 page
    And the user adds 16000 on the second country-vat-correction-amount page for the first correction period
    And the user answers yes on the vat-payable-confirm/1/2 page
    And the user answers no on the correction-list-countries/1 page
#    Will need to go to corrections list to be able to add corrections for other years and months
    Then the user is on the check-your-answers page
    And the user clicks the continue button
    Then the user is on the successfully-submitted page

  Scenario: A user adds return data with corrections for multiple years and periods
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9001234569 accesses the returns journey
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
#    Will need to go to corrections list to be able to add corrections for other years and months
    Then the user is on the check-your-answers page
    And the user clicks the continue button
    Then the user is on the successfully-submitted page

  Scenario: A user completing their first return has no corrections available
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9009999888 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the Start your return link
    Then the user answers yes on the 2023-M10/start page
    And the user answers no on the soldGoods page
    Then the user is on the check-your-answers page
    And the user clicks the continue button
    Then the user is on the successfully-submitted page

