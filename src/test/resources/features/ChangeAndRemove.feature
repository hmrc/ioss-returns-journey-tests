@ChangeSave

Feature: Change and Remove Feature

  Scenario: A user can remove sales to a country in their return
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9001234567 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the Start your return link
    Then the user answers yes on the 2023-M12/start-return page
    And the user answers yes on the sold-goods page
    And the user selects Poland on the first sold-to-country page
    And the user ticks the first checkbox on the first vat-rates-from-country page
    And the user ticks the second checkbox on the first vat-rates-from-country page
    And the user clicks the continue button
    And the user enters first country total sales of 10000 for first selected VAT rate on the sales-to-country page
    And the user confirms the vat for the first EU country as the suggested amount for the first selected VAT rate on the vat-on-sales page
    And the user enters first country total sales of 12345 for second selected VAT rate on the sales-to-country page
    And the user enters a different amount of VAT totalling 10500 for the first country and the second selected VAT rate on the vat-on-sales page
    And the user answers no on the check-sales/1 page
    And the user answers yes on the add-sales-country-list page
    And the user selects Germany on the second sold-to-country page
    And the user ticks the second checkbox on the second vat-rates-from-country page
    And the user clicks the continue button
    And the user enters second country total sales of 3210 for first selected VAT rate on the sales-to-country page
    And the user confirms the vat for the second EU country as the suggested amount for the first selected VAT rate on the vat-on-sales page
    And the user answers no on the check-sales/2 page
    Then the user selects the remove link for remove-sales-country\/1
    And the user answers yes on the remove-sales-country/1 page
    Then the user answers yes on the add-sales-country-list page
    And the user selects Spain on the second sold-to-country page
    And the user ticks the first checkbox on the second vat-rates-from-country page
    And the user clicks the continue button
    And the user enters second country total sales of 6547 for first selected VAT rate on the sales-to-country page
    And the user confirms the vat for the second EU country as the suggested amount for the first selected VAT rate on the vat-on-sales page
    And the user answers no on the check-sales/2 page
    And the user answers yes on the add-sales-country-list page
    And the user selects Slovenia on the third sold-to-country page
    And the user ticks the first checkbox on the third vat-rates-from-country page
    And the user clicks the continue button
    And the user enters third country total sales of 9999 for first selected VAT rate on the sales-to-country page
    And the user confirms the vat for the third EU country as the suggested amount for the first selected VAT rate on the vat-on-sales page
    And the user answers no on the check-sales/3 page
    And the user answers no on the add-sales-country-list page
    And the user answers no on the correct-previous-return page
    Then the user is on the check-your-answers page
    And the user clicks the submit button
    Then the user is on the return-successfully-submitted page

  Scenario: A user can add some sales then remove them all in their return
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9001234567 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the Start your return link
    Then the user answers yes on the 2023-M12/start-return page
    And the user answers yes on the sold-goods page
    And the user selects Czech Republic on the first sold-to-country page
    And the user ticks the first checkbox on the first vat-rates-from-country page
    And the user clicks the continue button
    And the user enters first country total sales of 5544 for first selected VAT rate on the sales-to-country page
    And the user confirms the vat for the first EU country as the suggested amount for the first selected VAT rate on the vat-on-sales page
    And the user answers no on the check-sales/1 page
    And the user answers yes on the add-sales-country-list page
    And the user selects Lithuania on the second sold-to-country page
    And the user ticks the first checkbox on the second vat-rates-from-country page
    And the user clicks the continue button
    And the user enters second country total sales of 11111 for first selected VAT rate on the sales-to-country page
    And the user confirms the vat for the second EU country as the suggested amount for the first selected VAT rate on the vat-on-sales page
    And the user answers no on the check-sales/2 page
    Then the user selects the remove link for remove-sales-country\/2
    And the user answers yes on the remove-sales-country/2 page
    Then the user selects the remove link for remove-sales-country\/1
    And the user answers yes on the remove-sales-country/1 page
    Then the user answers no on the sold-goods page
    And the user answers no on the correct-previous-return page
    And the user is on the check-your-answers page
    And the user clicks the submit button
    Then the user is on the return-successfully-submitted page

  Scenario: A user can change and remove answers via the mini CYA for VAT rates
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9001234567 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the Start your return link
    Then the user answers yes on the 2023-M12/start-return page
    And the user answers yes on the sold-goods page
    And the user selects Denmark on the first sold-to-country page
    And the user enters first country total sales of 10000 for first selected VAT rate on the sales-to-country page
    And the user confirms the vat for the first EU country as the suggested amount for the first selected VAT rate on the vat-on-sales page
    Then the user is on the check-sales/1 page
    And the user clicks the continue button
    Then the user answers yes on the add-sales-country-list page
    And the user selects Croatia on the second sold-to-country page
    And the user ticks the second checkbox on the second vat-rates-from-country page
    And the user clicks the continue button
    And the user enters second country total sales of 6321 for first selected VAT rate on the sales-to-country page
    And the user enters a different amount of VAT totalling 600 for the second country and the first selected VAT rate on the vat-on-sales page
    Then the user is on the check-sales/2 page
    Then the user selects the mini CYA change link for first vat-on-sales\/2
    And the user confirms the vat for the second EU country as the suggested amount for the first selected VAT rate on the vat-on-sales page
    Then the user is on the check-sales/2 page
    And the user answers yes on the check-sales/2 page
    And the user ticks the second checkbox on the second vat-rates-from-country page
    And the user clicks the continue button
    And the user enters second country total sales of 123.11 for second selected VAT rate on the sales-to-country page
    And the user confirms the vat for the second EU country as the suggested amount for the second selected VAT rate on the vat-on-sales page
    Then the user selects the second mini CYA change link for second sales-to-country\/2
    And the user changes second country total sales of 641 for second selected VAT rate on the sales-to-country page
    And the user confirms the vat for the second EU country as the suggested amount for the second selected VAT rate on the vat-on-sales page
    Then the user is on the check-sales/2 page
    And the user answers yes on the check-sales/2 page
    And the user answers yes on the remaining-vat-rate-from-country/2/3 page
    And the user enters second country total sales of 1400 for third selected VAT rate on the sales-to-country page
    And the user confirms the vat for the second EU country as the suggested amount for the third selected VAT rate on the vat-on-sales page
    Then the user clicks the continue button
    Then the user selects the list change link for second check-sales
    And the user selects the remove link for remove-vat-rate-sales-for-country\/2\/2
    And the user answers yes on the remove-vat-rate-sales-for-country/2/2 page
    Then the user selects the second mini CYA change link for second vat-on-sales\/2
    And the user enters a different amount of VAT totalling 250.11 for the second country and the second selected VAT rate on the vat-on-sales page
    Then the user answers no on the check-sales/2 page
    And the user answers no on the add-sales-country-list page
    And the user answers no on the correct-previous-return page
    And the user is on the check-your-answers page
    And the user clicks the submit button
    Then the user is on the return-successfully-submitted page

  Scenario: A user can remove all answers via the mini CYA for VAT rates
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9001234567 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the Start your return link
    Then the user answers yes on the 2023-M12/start-return page
    And the user answers yes on the sold-goods page
    And the user selects Croatia on the first sold-to-country page
    And the user ticks the first checkbox on the first vat-rates-from-country page
    And the user ticks the second checkbox on the first vat-rates-from-country page
    And the user ticks the third checkbox on the first vat-rates-from-country page
    And the user clicks the continue button
    And the user enters first country total sales of 10000 for first selected VAT rate on the sales-to-country page
    And the user confirms the vat for the first EU country as the suggested amount for the first selected VAT rate on the vat-on-sales page
    And the user enters first country total sales of 12345.23 for second selected VAT rate on the sales-to-country page
    And the user enters a different amount of VAT totalling 10500.21 for the first country and the second selected VAT rate on the vat-on-sales page
    And the user enters first country total sales of 98765 for third selected VAT rate on the sales-to-country page
    And the user confirms the vat for the first EU country as the suggested amount for the third selected VAT rate on the vat-on-sales page
    Then the user is on the check-sales/1 page
    When the user selects the remove link for remove-vat-rate-sales-for-country\/1\/2
    Then the user answers yes on the remove-vat-rate-sales-for-country/1/2 page
    And the user selects the remove link for remove-vat-rate-sales-for-country\/1\/1
    And the user answers yes on the remove-vat-rate-sales-for-country/1/1 page
    And the user selects the remove link for remove-vat-rate-sales-for-country\/1\/1
    And the user answers yes on the remove-vat-rate-sales-for-country/1/1 page
    Then the user is on the vat-rates-from-country/1 page
    And the user ticks the third checkbox on the first vat-rates-from-country page
    And the user clicks the continue button
    And the user enters first country total sales of 2000 for first selected VAT rate on the sales-to-country page
    And the user confirms the vat for the first EU country as the suggested amount for the first selected VAT rate on the vat-on-sales page
    And the user answers no on the check-sales/1 page
    And the user answers no on the add-sales-country-list page
    And the user answers no on the correct-previous-return page
    Then the user is on the check-your-answers page
    And the user clicks the submit button
    Then the user is on the return-successfully-submitted page

  Scenario: A user can change answers via the country list in their return
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9001234567 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the Start your return link
    Then the user answers yes on the 2023-M12/start-return page
    And the user answers yes on the sold-goods page
    And the user selects Finland on the first sold-to-country page
    And the user ticks the second checkbox on the first vat-rates-from-country page
    And the user clicks the continue button
    And the user enters first country total sales of 1234 for first selected VAT rate on the sales-to-country page
    And the user confirms the vat for the first EU country as the suggested amount for the first selected VAT rate on the vat-on-sales page
    And the user answers no on the check-sales/1 page
    Then the user selects the list change link for first check-sales
    And the user answers yes on the check-sales/1 page
    And the user ticks the first checkbox on the first vat-rates-from-country page
    And the user clicks the continue button
    And the user enters first country total sales of 2222.33 for second selected VAT rate on the sales-to-country page
    And the user confirms the vat for the first EU country as the suggested amount for the second selected VAT rate on the vat-on-sales page
    And the user answers no on the check-sales/1?waypoints=change-add-sales-country-list page
    Then the user answers yes on the add-sales-country-list page
    And the user selects Malta on the second sold-to-country page
    And the user ticks the second checkbox on the second vat-rates-from-country page
    And the user ticks the third checkbox on the second vat-rates-from-country page
    And the user clicks the continue button
    And the user enters second country total sales of 3333 for first selected VAT rate on the sales-to-country page
    And the user confirms the vat for the second EU country as the suggested amount for the first selected VAT rate on the vat-on-sales page
    And the user enters second country total sales of 654 for second selected VAT rate on the sales-to-country page
    And the user enters a different amount of VAT totalling 10500 for the second country and the second selected VAT rate on the vat-on-sales page
    And the user answers no on the check-sales/2 page
    Then the user answers no on the add-sales-country-list page
    And the user answers no on the correct-previous-return page
    Then the user is on the check-your-answers page
    And the user clicks the submit button
    Then the user is on the return-successfully-submitted page

  Scenario: A user can change answers via the correction country list - previously undeclared
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9001234567 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the Start your return link
    Then the user answers yes on the 2023-M12/start-return page
    And the user answers no on the sold-goods page
    And the user answers yes on the correct-previous-return page
    When the user picks year 2023 on the correction-return-year/1 page
    Then the user picks month November on the correction-return-month/1 page
    And the user chooses the country Spain as their first correction within the first correction period
    And the user answers yes on the add-new-country/1/1 page
    And the user adds 11111 on the first country-vat-correction-amount page for the first correction period
    And the user answers yes on the vat-payable-confirm/1/1 page
    And the user is on the vat-payable-check/1/1 page
    And the user clicks the continue button
    Then the user is on the correction-list-countries/1 page
    And the user answers yes on the correction-list-countries/1 page
    And the user chooses the country Slovakia as their second correction within the first correction period
    And the user answers yes on the add-new-country/1/2 page
    And the user adds 1234.56 on the second country-vat-correction-amount page for the first correction period
    And the user answers yes on the vat-payable-confirm/1/2 page
    And the user is on the vat-payable-check/1/2 page
    And the user clicks the continue button
    Then the user is on the correction-list-countries/1 page
    When the user selects the correction countries list change link for the first country on the first correction period
    And the user amends to 1111 on the first country-vat-correction-amount page for the first correction period
    Then the user answers yes on the vat-payable-confirm/1/1?waypoints=add-correction-list-countries-1 page
    And the user is on the vat-payable-check/1/1 page
    And the user clicks the continue button
    Then the user answers yes on the correction-list-countries/1 page
    And the user chooses the country Denmark as their third correction within the first correction period
    And the user answers yes on the add-new-country/1/3 page
    And the user adds 1254.01 on the third country-vat-correction-amount page for the first correction period
    And the user answers yes on the vat-payable-confirm/1/3 page
    And the user is on the vat-payable-check/1/3 page
    And the user clicks the continue button
    Then the user is on the correction-list-countries/1 page
    When the user selects the correction countries list change link for the third country on the first correction period
    And the user amends to 1254.10 on the third country-vat-correction-amount page for the first correction period
    Then the user answers yes on the vat-payable-confirm/1/3?waypoints=add-correction-list-countries-1 page
    And the user is on the vat-payable-check/1/3 page
    And the user clicks the continue button
    And the user answers no on the correction-list-countries/1 page
    When the user answers no on the 2023-M12/vat-correction-months-add page
    Then the user is on the check-your-answers page
    And the user clicks the submit button
    Then the user is on the return-successfully-submitted page

  Scenario: A user can remove all answers via the correction country list - previously undeclared
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9001234567 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the Start your return link
    Then the user answers yes on the 2023-M12/start-return page
    And the user answers no on the sold-goods page
    And the user answers yes on the correct-previous-return page
    When the user picks year 2023 on the correction-return-year/1 page
    Then the user picks month October on the correction-return-month/1 page
    And the user chooses the country Czech Republic as their first correction within the first correction period
    And the user answers yes on the add-new-country/1/1 page
    And the user adds 12345 on the first country-vat-correction-amount page for the first correction period
    And the user answers yes on the vat-payable-confirm/1/1 page
    And the user is on the vat-payable-check/1/1 page
    And the user clicks the continue button
    Then the user is on the correction-list-countries/1 page
    And the user answers yes on the correction-list-countries/1 page
    And the user chooses the country Cyprus as their second correction within the first correction period
    And the user answers yes on the add-new-country/1/2 page
    And the user adds 6543.21 on the second country-vat-correction-amount page for the first correction period
    And the user answers yes on the vat-payable-confirm/1/2 page
    And the user is on the vat-payable-check/1/2 page
    And the user clicks the continue button
    Then the user is on the correction-list-countries/1 page
    When the user selects the remove link for remove-country-correction\/1\/2
    And the remove country correction page is displayed for Cyprus
    Then the user answers yes on the remove-country-correction/1/2 page
    And the user is on the correction-list-countries/1 page
    When the user selects the remove link for remove-country-correction\/1\/1
    And the remove country correction page is displayed for Czech Republic
    Then the user answers yes on the remove-country-correction/1/1 page
    And the user answers no on the correct-previous-return page
    Then the user is on the check-your-answers page
    And the user clicks the submit button
    Then the user is on the return-successfully-submitted page

  Scenario: A user can remove all answers via the correction periods list for same year
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9001234568 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the Start your return link
    Then the user answers yes on the 2024-M1/start-return page
    And the user answers no on the sold-goods page
    And the user answers yes on the correct-previous-return page
    When the user picks year 2023 on the correction-return-year/1 page
    Then the user picks month October on the correction-return-month/1 page
    And the user chooses the country Czech Republic as their first correction within the first correction period
    And the user answers yes on the add-new-country/1/1 page
    And the user adds 12345 on the first country-vat-correction-amount page for the first correction period
    And the user answers yes on the vat-payable-confirm/1/1 page
    And the user is on the vat-payable-check/1/1 page
    And the user clicks the continue button
    Then the user is on the correction-list-countries/1 page
    And the user answers yes on the correction-list-countries/1 page
    And the user chooses the country Cyprus as their second correction within the first correction period
    And the user answers yes on the add-new-country/1/2 page
    And the user adds 6543.21 on the second country-vat-correction-amount page for the first correction period
    And the user answers yes on the vat-payable-confirm/1/2 page
    And the user is on the vat-payable-check/1/2 page
    And the user clicks the continue button
    Then the user answers no on the correction-list-countries/1 page
    When the user answers yes on the 2024-M1/vat-correction-months-add page
    Then the user picks year 2023 on the correction-return-year/2 page
    Then the user picks month November on the correction-return-month/2 page
    And the user chooses the country Bulgaria as their first correction within the second correction period
    And the user answers yes on the add-new-country/2/1 page
    And the user adds 100.25 on the first country-vat-correction-amount page for the second correction period
    And the user answers yes on the vat-payable-confirm/2/1 page
    And the user is on the vat-payable-check/2/1 page
    And the user clicks the continue button
    Then the user is on the correction-list-countries/2 page
    And the user answers yes on the correction-list-countries/2 page
    And the user chooses the country Slovakia as their second correction within the second correction period
    And the user answers yes on the add-new-country/2/2 page
    And the user adds 1450 on the second country-vat-correction-amount page for the second correction period
    And the user answers yes on the vat-payable-confirm/2/2 page
    And the user is on the vat-payable-check/2/2 page
    And the user clicks the continue button
    Then the user is on the correction-list-countries/2 page
    And the user answers no on the correction-list-countries/2 page
    When the user is on the 2024-M1/vat-correction-months page
    Then the user selects the remove link for remove-month-correction\/2
    And the remove page is displayed for the November 2023 correction
    And the user answers yes on the remove-month-correction/2 page
    And the corrections list is showing one correction for October 2023
    Then the user selects the remove link for remove-month-correction\/1
    And the remove page is displayed for the October 2023 correction
    And the user answers yes on the remove-month-correction/1 page
    Then the user answers no on the correct-previous-return page
    Then the user is on the check-your-answers page
    And the user clicks the submit button
    Then the user is on the return-successfully-submitted page

  Scenario: A user can change and remove answers via the correction periods list for same year
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9001234568 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the Start your return link
    Then the user answers yes on the 2024-M1/start-return page
    And the user answers no on the sold-goods page
    And the user answers yes on the correct-previous-return page
    When the user picks year 2023 on the correction-return-year/1 page
    Then the user picks month October on the correction-return-month/1 page
    And the user chooses the country Czech Republic as their first correction within the first correction period
    And the user answers yes on the add-new-country/1/1 page
    And the user adds 12345 on the first country-vat-correction-amount page for the first correction period
    And the user answers yes on the vat-payable-confirm/1/1 page
    And the user is on the vat-payable-check/1/1 page
    And the user clicks the continue button
    Then the user is on the correction-list-countries/1 page
    And the user answers yes on the correction-list-countries/1 page
    And the user chooses the country Cyprus as their second correction within the first correction period
    And the user answers yes on the add-new-country/1/2 page
    And the user adds 6543.21 on the second country-vat-correction-amount page for the first correction period
    And the user answers yes on the vat-payable-confirm/1/2 page
    And the user is on the vat-payable-check/1/2 page
    And the user clicks the continue button
    Then the user answers no on the correction-list-countries/1 page
    When the user answers yes on the 2024-M1/vat-correction-months-add page
    Then the user picks year 2023 on the correction-return-year/2 page
    Then the user picks month November on the correction-return-month/2 page
    And the user chooses the country Bulgaria as their first correction within the second correction period
    And the user answers yes on the add-new-country/2/1 page
    And the user adds 100.25 on the first country-vat-correction-amount page for the second correction period
    And the user answers yes on the vat-payable-confirm/2/1 page
    And the user is on the vat-payable-check/2/1 page
    And the user clicks the continue button
    Then the user is on the correction-list-countries/2 page
    And the user answers yes on the correction-list-countries/2 page
    And the user chooses the country Slovakia as their second correction within the second correction period
    And the user answers yes on the add-new-country/2/2 page
    And the user adds 1450 on the second country-vat-correction-amount page for the second correction period
    And the user answers yes on the vat-payable-confirm/2/2 page
    And the user is on the vat-payable-check/2/2 page
    And the user clicks the continue button
    Then the user is on the correction-list-countries/2 page
    And the user answers no on the correction-list-countries/2 page
    When the user is on the 2024-M1/vat-correction-months page
    Then the user selects the remove link for remove-month-correction\/2
    And the remove page is displayed for the November 2023 correction
    And the user answers yes on the remove-month-correction/2 page
    And the corrections list is showing one correction for October 2023
    Then the user answers yes on the 2024-M1/vat-correction-months-add page
    Then the user picks year 2023 on the correction-return-year/2 page
    Then the user picks month December on the correction-return-month/2 page
    And the user chooses the country Poland as their first correction within the second correction period
    And the user answers yes on the add-new-country/2/1 page
    And the user adds 12345.67 on the first country-vat-correction-amount page for the second correction period
    And the user answers yes on the vat-payable-confirm/2/1 page
    And the user is on the vat-payable-check/2/1 page
    And the user clicks the continue button
    Then the user is on the correction-list-countries/2 page
    And the user answers yes on the correction-list-countries/2 page
    And the user chooses the country Portugal as their second correction within the second correction period
    And the user answers yes on the add-new-country/2/2 page
    And the user adds 1000 on the second country-vat-correction-amount page for the second correction period
    And the user answers yes on the vat-payable-confirm/2/2 page
    And the user is on the vat-payable-check/2/2 page
    And the user clicks the continue button
    Then the user is on the correction-list-countries/2 page
    And the user answers no on the correction-list-countries/2 page
    When the user is on the 2024-M1/vat-correction-months page
    Then the user selects the change link for correction-list-countries\/1
    And the user answers yes on the correction-list-countries/1 page
    And the user chooses the country Spain as their third correction within the first correction period
    And the user answers yes on the add-new-country/1/3 page
    And the user adds 1500.01 on the third country-vat-correction-amount page for the first correction period
    And the user answers yes on the vat-payable-confirm/1/3 page
    And the user is on the vat-payable-check/1/3 page
    And the user clicks the continue button
    Then the user is on the correction-list-countries/1 page
    And the user answers no on the correction-list-countries/1 page
    Then the user answers no on the 2024-M1/vat-correction-months-add page
    Then the user is on the check-your-answers page
    And the user clicks the submit button
    Then the user is on the return-successfully-submitted page

  Scenario: A user can change and remove answers via the correction periods list for multiple years
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9001234569 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the Start your return link
    Then the user answers yes on the thisYear-M1/start-return page
    And the user answers no on the sold-goods page
    And the user answers yes on the correct-previous-return page
    When the user picks year two years ago on the correction-return-year/1 page
    When the user picks month October on the correction-return-month/1 page
    And the user chooses the country Denmark as their first correction within the first correction period
    And the user answers yes on the add-new-country/1/1 page
    And the user adds 2222 on the first country-vat-correction-amount page for the first correction period
    And the user answers yes on the vat-payable-confirm/1/1 page
    And the user is on the vat-payable-check/1/1 page
    And the user clicks the continue button
    Then the user is on the correction-list-countries/1 page
    And the user answers no on the correction-list-countries/1 page
    And the user answers yes on the thisYear-M1/vat-correction-months-add page
    When the user picks year last year on the correction-return-year/2 page
    When the user picks month December on the correction-return-month/2 page
    And the user chooses the country Slovakia as their first correction within the second correction period
    And the user answers yes on the add-new-country/2/1 page
    And the user adds 1234 on the first country-vat-correction-amount page for the second correction period
    And the user answers yes on the vat-payable-confirm/2/1 page
    And the user is on the vat-payable-check/2/1 page
    And the user clicks the continue button
    Then the user is on the correction-list-countries/2 page
    And the user answers no on the correction-list-countries/2 page
    Then the user selects the change link for correction-list-countries\/2
    And the user is on the correction-list-countries/2 page
    And the country list page is displayed for the December last year correction
    And the user answers yes on the correction-list-countries/2 page
    And the user chooses the country Belgium as their second correction within the second correction period
    And the user answers yes on the add-new-country/2/2 page
    And the user adds 1258.41 on the second country-vat-correction-amount page for the second correction period
    And the user answers yes on the vat-payable-confirm/2/2 page
    And the user is on the vat-payable-check/2/2 page
    And the user clicks the continue button
    Then the user is on the correction-list-countries/2 page
    And the user answers no on the correction-list-countries/2 page
    When the user answers yes on the thisYear-M1/vat-correction-months-add page
    When the user picks year two years ago on the correction-return-year/3 page
    When the user picks month December on the correction-return-month/3 page
    And the user chooses the country France as their first correction within the third correction period
    And the user is on the country-vat-correction-amount/3/1 page
    And the previously declared text is displayed above the amount box
    And the user adds 1234 on the first country-vat-correction-amount page for the third correction period
    And the user answers yes on the vat-payable-confirm/3/1 page
    And the user is on the vat-payable-check/3/1 page
    And the user clicks the continue button
    Then the user is on the correction-list-countries/3 page
    And the user answers no on the correction-list-countries/3 page
    When the user selects the remove link for remove-month-correction\/1
    And the remove page is displayed for the October correction from two years ago
    Then the user answers yes on the remove-month-correction/1 page
    And the user is on the thisYear-M1/vat-correction-months-add page
    And the corrections list is showing 2 corrections for December two years ago and December last year
    Then the user answers no on the thisYear-M1/vat-correction-months-add page
    Then the user is on the check-your-answers page
    And the user clicks the submit button
    Then the user is on the return-successfully-submitted page

  Scenario: A user can change answers via the correction mini CYA
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9001234567 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the Start your return link
    Then the user answers yes on the 2023-M12/start-return page
    And the user answers no on the sold-goods page
    And the user answers yes on the correct-previous-return page
    When the user picks year 2023 on the correction-return-year/1 page
    When the user picks month October on the correction-return-month/1 page
    And the user chooses the country Belgium as their first correction within the first correction period
    And the user answers yes on the add-new-country/1/1 page
    And the user adds 1000 on the first country-vat-correction-amount page for the first correction period
    And the user answers yes on the vat-payable-confirm/1/1 page
    And the user is on the vat-payable-check/1/1 page
    When the user selects the change link for country-vat-correction-amount\/1\/1
    Then the user is on the country-vat-correction-amount/1/1 page
    And the user amends to 1111.11 on the first country-vat-correction-amount page for the first correction period
    And the user answers yes on the vat-payable-confirm/1/1 page
    And the user is on the vat-payable-check/1/1 page
    And the user clicks the continue button
    Then the user is on the correction-list-countries/1 page
    And the user answers no on the correction-list-countries/1 page
    Then the user answers no on the 2023-M12/vat-correction-months-add page
    Then the user is on the check-your-answers page
    And the user clicks the submit button
    Then the user is on the return-successfully-submitted page

  Scenario: A user can add corrections for all available periods then remove via the correction periods list for same year
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9001234567 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the Start your return link
    Then the user answers yes on the 2023-M12/start-return page
    And the user answers no on the sold-goods page
    And the user answers yes on the correct-previous-return page
    When the user picks year 2023 on the correction-return-year/1 page
    Then the user picks month November on the correction-return-month/1 page
    And the user chooses the country Austria as their first correction within the first correction period
    And the user answers yes on the add-new-country/1/1 page
    And the user adds 1000 on the first country-vat-correction-amount page for the first correction period
    And the user answers yes on the vat-payable-confirm/1/1 page
    And the user is on the vat-payable-check/1/1 page
    And the user clicks the continue button
    Then the user is on the correction-list-countries/1 page
    And the user answers no on the correction-list-countries/1 page
    When the user answers yes on the 2023-M12/vat-correction-months-add page
    Then the user picks year 2023 on the correction-return-year/2 page
    Then the user picks month October on the correction-return-month/2 page
    And the user chooses the country Belgium as their first correction within the second correction period
    And the user answers yes on the add-new-country/2/1 page
    And the user adds 2000 on the first country-vat-correction-amount page for the second correction period
    And the user answers yes on the vat-payable-confirm/2/1 page
    And the user is on the vat-payable-check/2/1 page
    And the user clicks the continue button
    And the user answers no on the correction-list-countries/2 page
    When the user is on the 2023-M12/vat-correction-months page
    Then the user selects the remove link for remove-month-correction\/2
    And the remove page is displayed for the October 2023 correction
    And the user answers yes on the remove-month-correction/2 page
    And the corrections list is showing one correction for November 2023
    Then the user selects the remove link for remove-month-correction\/1
    And the remove page is displayed for the November 2023 correction
    And the user answers yes on the remove-month-correction/1 page
    Then the user answers no on the correct-previous-return page
    Then the user is on the check-your-answers page
    And the user clicks the submit button
    Then the user is on the return-successfully-submitted page


