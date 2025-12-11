@Returns

Feature: Corrections Feature

  Scenario: A simple corrections journey with previously undeclared countries added to a nil return
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9008888888 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the Start your return link
    Then the user answers yes on the 2023-M12/start-return page
    And the user answers no on the sold-goods page
    And the user answers yes on the correct-previous-return page
    Then the user answers yes on the correction-return-single-month/1 page
    And the user chooses the country Estonia as their first correction within the first correction period
    And the user answers yes on the add-new-country/1/1 page
    And the user is on the country-vat-correction-amount/1/1 page
    And the previously declared text is not displayed above the amount box
    And the user adds 1500 on the first country-vat-correction-amount page for the first correction period
    And the user answers yes on the vat-payable-confirm/1/1 page
    And the user is on the vat-payable-check/1/1 page
    And the user clicks the continue button
    Then the user is on the correction-list-countries/1 page
    And the user answers yes on the correction-list-countries/1 page
    And the user chooses the country Portugal as their second correction within the first correction period
    And the user answers yes on the add-new-country/1/2 page
    And the user is on the country-vat-correction-amount/1/2 page
    And the previously declared text is not displayed above the amount box
    And the user adds 160.36 on the second country-vat-correction-amount page for the first correction period
    And the user answers yes on the vat-payable-confirm/1/2 page
    And the user is on the vat-payable-check/1/2 page
    And the user clicks the continue button
    Then the user is on the correction-list-countries/1 page
    And the user answers no on the correction-list-countries/1 page
    And the user clicks the continue button
    Then the user is on the check-your-answers page
    And the no payments due for minus corrections text will not be displayed
    And the user clicks the submit button
    Then the user is on the return-successfully-submitted page

  Scenario: A corrections journey with previously declared countries
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9001234567 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the Start your return link
    Then the user answers yes on the 2023-M12/start-return page
    And the user answers no on the sold-goods page
    And the user answers yes on the correct-previous-return page
    When the user picks year 2023 on the correction-return-year/1 page
    Then the user picks month October on the correction-return-month/1 page
    And the user chooses the country Germany as their first correction within the first correction period
    And the user is on the country-vat-correction-amount/1/1 page
    And the previously declared text is displayed above the amount box
    And the user adds -100.65 on the first country-vat-correction-amount page for the first correction period
    And the user answers yes on the vat-payable-confirm/1/1 page
    And the user is on the vat-payable-check/1/1 page
    And the user clicks the continue button
    Then the user is on the correction-list-countries/1 page
    And the user answers yes on the correction-list-countries/1 page
    And the user chooses the country France as their second correction within the first correction period
    And the user is on the country-vat-correction-amount/1/2 page
    And the previously declared text is displayed above the amount box
    And the user adds 1453 on the second country-vat-correction-amount page for the first correction period
    And the user answers yes on the vat-payable-confirm/1/2 page
    And the user is on the vat-payable-check/1/2 page
    And the user clicks the continue button
    Then the user is on the correction-list-countries/1 page
    And the user answers no on the correction-list-countries/1 page
    When the user answers yes on the 2023-M12/vat-correction-months-add page
    Then the user picks year 2023 on the correction-return-year/2 page
    Then the user picks month November on the correction-return-month/2 page
    And the user chooses the country France as their first correction within the second correction period
    And the user adds 1500 on the first country-vat-correction-amount page for the second correction period
    And the user answers yes on the vat-payable-confirm/2/1 page
    And the user is on the vat-payable-check/2/1 page
    And the user clicks the continue button
    Then the user is on the correction-list-countries/2 page
    And the user answers yes on the correction-list-countries/2 page
    And the user chooses the country Germany as their second correction within the second correction period
    And the user adds 160.36 on the second country-vat-correction-amount page for the second correction period
    And the user answers yes on the vat-payable-confirm/2/2 page
    And the user is on the vat-payable-check/2/2 page
    And the user clicks the continue button
    Then the user is on the correction-list-countries/2 page
    And the user answers no on the correction-list-countries/2 page
    Then the user is on the 2023-M12/vat-correction-months page
    And the user clicks the continue button
    Then the user is on the check-your-answers page
    And the no payments due for minus corrections text will not be displayed
    And the user clicks the submit button
    Then the user is on the return-successfully-submitted page

  Scenario: A corrections journey with previously declared countries that have been corrected multiple times
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9001233211 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the Start your return link
    Then the user answers yes on the 2024-M1/start-return page
    And the user answers no on the sold-goods page
    And the user answers yes on the correct-previous-return page
    When the user picks year 2023 on the correction-return-year/1 page
    Then the user picks month October on the correction-return-month/1 page
    And the user chooses the country Germany as their first correction within the first correction period
    And the user is on the country-vat-correction-amount/1/1 page
    And the previously declared text is displayed above the amount box
    And the user adds -2500 on the first country-vat-correction-amount page for the first correction period
    And the user answers yes on the vat-payable-confirm/1/1 page
    And the user is on the vat-payable-check/1/1 page
    And the user clicks the continue button
    Then the user is on the correction-list-countries/1 page
    And the user answers yes on the correction-list-countries/1 page
    And the user chooses the country France as their second correction within the first correction period
    And the user is on the country-vat-correction-amount/1/2 page
    And the previously declared text is displayed above the amount box
    And the user adds 2600 on the second country-vat-correction-amount page for the first correction period
    And the user answers yes on the vat-payable-confirm/1/2 page
    And the user is on the vat-payable-check/1/2 page
    And the user clicks the continue button
    Then the user is on the correction-list-countries/1 page
    And the user answers no on the correction-list-countries/1 page
    When the user answers no on the 2024-M1/vat-correction-months-add page
    Then the user is on the check-your-answers page
    And the no payments due for minus corrections text will be displayed
    And the user clicks the submit button
    Then the user is on the return-successfully-submitted page

  Scenario: A corrections journey with full minus correction to a country that also has sales in the new return
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9001233211 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the Start your return link
    Then the user answers yes on the 2024-M1/start-return page
    And the user answers yes on the sold-goods page
    And the user selects Germany on the first sold-to-country page
    And the user ticks the first checkbox on the first vat-rates-from-country page
    And the user clicks the continue button
    And the user enters first country total sales of 950 for first selected VAT rate on the sales-to-country page
    And the user confirms the vat for the first EU country as the suggested amount for the second selected VAT rate on the vat-on-sales page
    And the user answers no on the check-sales/1 page
    And the user answers no on the add-sales-country-list page
    And the user answers yes on the correct-previous-return page
    When the user picks year 2023 on the correction-return-year/1 page
    Then the user picks month October on the correction-return-month/1 page
    And the user chooses the country Germany as their first correction within the first correction period
    And the user is on the country-vat-correction-amount/1/1 page
    And the previously declared text is displayed above the amount box
    And the user adds -3500 on the first country-vat-correction-amount page for the first correction period
    And the user answers yes on the vat-payable-confirm/1/1 page
    And the user is on the vat-payable-check/1/1 page
    And the user clicks the continue button
    Then the user is on the correction-list-countries/1 page
    And the user answers no on the correction-list-countries/1 page
    When the user answers no on the 2024-M1/vat-correction-months-add page
    Then the user is on the check-your-answers page
    And the no payments due for minus corrections text will be displayed
    And the user clicks the submit button
    Then the user is on the return-successfully-submitted page

  Scenario: A corrections journey with only minus corrections
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9001234567 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the Start your return link
    Then the user answers yes on the 2023-M12/start-return page
    And the user answers no on the sold-goods page
    And the user answers yes on the correct-previous-return page
    When the user picks year 2023 on the correction-return-year/1 page
    Then the user picks month October on the correction-return-month/1 page
    And the user chooses the country Germany as their first correction within the first correction period
    And the user is on the country-vat-correction-amount/1/1 page
    And the previously declared text is displayed above the amount box
    And the user adds -100.65 on the first country-vat-correction-amount page for the first correction period
    And the user answers yes on the vat-payable-confirm/1/1 page
    And the user is on the vat-payable-check/1/1 page
    And the user clicks the continue button
    Then the user is on the correction-list-countries/1 page
    And the user answers no on the correction-list-countries/1 page
    When the user answers no on the 2023-M12/vat-correction-months-add page
    Then the user is on the check-your-answers page
    And the no payments due for minus corrections text will be displayed
    And the user clicks the submit button
    Then the user is on the return-successfully-submitted page

  Scenario: A user is offered a single period to correct and chooses No
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9008888888 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the Start your return link
    Then the user answers yes on the 2023-M12/start-return page
    And the user answers no on the sold-goods page
    And the user answers yes on the correct-previous-return page
    When the user answers no on the correction-return-single-month/1 page
    Then the user is on the no-correction-months-available page
    And the user clicks the continue button
    Then the user is on the check-your-answers page
    And the user clicks the submit button
    Then the user is on the return-successfully-submitted page

  Scenario: A user has corrections available but selects no to adding any
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9001234567 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the Start your return link
    Then the user answers yes on the 2023-M12/start-return page
    And the user answers no on the sold-goods page
    And the user answers no on the correct-previous-return page
    Then the user is on the check-your-answers page
    And the user clicks the submit button
    Then the user is on the return-successfully-submitted page

  Scenario: A user has corrections available for multiple months within the same year
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9001234568 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the Start your return link
    Then the user answers yes on the 2024-M1/start-return page
    And the user answers no on the sold-goods page
    And the user answers yes on the correct-previous-return page
    When the user picks year 2023 on the correction-return-year/1 page
    When the user picks month October on the correction-return-month/1 page
    And the user chooses the country Bulgaria as their first correction within the first correction period
    And the user answers yes on the add-new-country/1/1 page
    And the user adds 1500 on the first country-vat-correction-amount page for the first correction period
    And the user answers yes on the vat-payable-confirm/1/1 page
    And the user is on the vat-payable-check/1/1 page
    And the user clicks the continue button
    Then the user is on the correction-list-countries/1 page
    And the user answers yes on the correction-list-countries/1 page
    And the user chooses the country Czech Republic as their second correction within the first correction period
    And the user answers yes on the add-new-country/1/2 page
    And the user adds 160.36 on the second country-vat-correction-amount page for the first correction period
    And the user answers yes on the vat-payable-confirm/1/2 page
    And the user is on the vat-payable-check/1/2 page
    And the user clicks the continue button
    And the user answers no on the correction-list-countries/1 page
    When the user answers yes on the 2024-M1/vat-correction-months-add page
    When the user picks year 2023 on the correction-return-year/2 page
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
    When the user answers yes on the 2024-M1/vat-correction-months-add page
    And the user picks year 2023 on the correction-return-year/3 page
    When the user picks month December on the correction-return-month/3 page
    And the user chooses the country Bulgaria as their first correction within the third correction period
    And the user answers yes on the add-new-country/3/1 page
    And the user adds 1234 on the first country-vat-correction-amount page for the third correction period
    And the user answers yes on the vat-payable-confirm/3/1 page
    And the user is on the vat-payable-check/3/1 page
    And the user clicks the continue button
    Then the user is on the correction-list-countries/3 page
    And the user answers no on the correction-list-countries/3 page
    Then the user is on the 2024-M1/vat-correction-months page
    And the user clicks the continue button
    Then the user is on the check-your-answers page
    And the user clicks the submit button
    Then the user is on the return-successfully-submitted page

  Scenario: A user has corrections available for multiple years
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
    When the user answers yes on the thisYear-M1/vat-correction-months-add page
    Then the user picks year last year on the correction-return-year/2 page
    When the user picks month October on the correction-return-month/2 page
    And the user chooses the country Denmark as their first correction within the second correction period
    And the user answers yes on the add-new-country/2/1 page
    And the user adds 1235.04 on the first country-vat-correction-amount page for the second correction period
    And the user answers yes on the vat-payable-confirm/2/1 page
    And the user is on the vat-payable-check/2/1 page
    And the user clicks the continue button
    Then the user is on the correction-list-countries/2 page
    And the user answers yes on the correction-list-countries/2 page
    And the user chooses the country Spain as their second correction within the second correction period
    And the user answers yes on the add-new-country/2/2 page
    And the user adds 111 on the second country-vat-correction-amount page for the second correction period
    And the user answers yes on the vat-payable-confirm/2/2 page
    And the user is on the vat-payable-check/2/2 page
    And the user clicks the continue button
    Then the user is on the correction-list-countries/2 page
    And the user answers no on the correction-list-countries/2 page
    And the user answers yes on the thisYear-M1/vat-correction-months-add page
    When the user picks year two years ago on the correction-return-year/3 page
    Then the user picks month December on the correction-return-month/3 page
    And the user chooses the country Spain as their first correction within the third correction period
    And the user answers yes on the add-new-country/3/1 page
    And the user adds 45210 on the first country-vat-correction-amount page for the third correction period
    And the user answers yes on the vat-payable-confirm/3/1 page
    And the user is on the vat-payable-check/3/1 page
    And the user clicks the continue button
    Then the user is on the correction-list-countries/3 page
    And the user answers no on the correction-list-countries/3 page
    And the user answers no on the thisYear-M1/vat-correction-months-add page
    Then the user is on the check-your-answers page
    And the user clicks the submit button
    Then the user is on the return-successfully-submitted page

  Scenario: A user has corrections available for a single period
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9008888888 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the Start your return link
    Then the user answers yes on the 2023-M12/start-return page
    And the user answers no on the sold-goods page
    And the user answers yes on the correct-previous-return page
    And the user answers yes on the correction-return-single-month/1 page
    And the user chooses the country Portugal as their first correction within the first correction period
    And the user answers yes on the add-new-country/1/1 page
    And the user adds 1500.24 on the first country-vat-correction-amount page for the first correction period
    And the user answers yes on the vat-payable-confirm/1/1 page
    And the user is on the vat-payable-check/1/1 page
    And the user clicks the continue button
    Then the user is on the correction-list-countries/1 page
    And the user answers yes on the correction-list-countries/1 page
    And the user chooses the country Romania as their second correction within the first correction period
    And the user answers yes on the add-new-country/1/2 page
    And the user adds 16000 on the second country-vat-correction-amount page for the first correction period
    And the user answers yes on the vat-payable-confirm/1/2 page
    And the user is on the vat-payable-check/1/2 page
    And the user clicks the continue button
    And the user answers no on the correction-list-countries/1 page
    And the user is on the 2023-M12/vat-correction-months page
    When the user clicks the continue button
    Then the user is on the check-your-answers page
    And the user clicks the submit button
    Then the user is on the return-successfully-submitted page

  Scenario: A user adds return data with corrections for multiple years and periods
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9001234569 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the Start your return link
    Then the user answers yes on the thisYear-M1/start-return page
    And the user answers yes on the sold-goods page
    And the user selects France on the first sold-to-country page
    And the user ticks the first checkbox on the first vat-rates-from-country page
    And the user ticks the fifth checkbox on the first vat-rates-from-country page
    And the user clicks the continue button
    And the user enters first country total sales of 6000 for first selected VAT rate on the sales-to-country page
    And the user confirms the vat for the first EU country as the suggested amount for the first selected VAT rate on the vat-on-sales page
    And the user enters first country total sales of 987654.32 for second selected VAT rate on the sales-to-country page
    And the user confirms the vat for the first EU country as the suggested amount for the second selected VAT rate on the vat-on-sales page
    And the user answers no on the check-sales/1 page
    And the user answers yes on the add-sales-country-list page
    And the user selects Italy on the second sold-to-country page
    And the user ticks the first checkbox on the second vat-rates-from-country page
    And the user clicks the continue button
    And the user enters second country total sales of 1234 for first selected VAT rate on the sales-to-country page
    And the user enters a different amount of VAT totalling 120.56 for the second country and the first selected VAT rate on the vat-on-sales page
    And the user answers no on the check-sales/2 page
    And the user answers no on the add-sales-country-list page
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
    And the user answers yes on the correction-list-countries/2 page
    And the user chooses the country Germany as their second correction within the second correction period
    And the user is on the country-vat-correction-amount/2/2 page
    And the previously declared text is displayed above the amount box
    And the user adds 1234 on the second country-vat-correction-amount page for the second correction period
    And the user answers yes on the vat-payable-confirm/2/2 page
    And the user is on the vat-payable-check/2/2 page
    And the user clicks the continue button
    Then the user is on the correction-list-countries/2 page
    And the user answers no on the correction-list-countries/2 page
    And the user answers no on the thisYear-M1/vat-correction-months-add page
    Then the user is on the check-your-answers page
    And the user clicks the submit button
    Then the user is on the return-successfully-submitted page

  Scenario: A user completing their first return has no corrections available
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9009999888 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the Start your return link
    Then the user answers yes on the 2023-M12/start-return page
    And the user answers no on the sold-goods page
    Then the user is on the check-your-answers page
    And the user clicks the submit button
    Then the user is on the return-successfully-submitted page

