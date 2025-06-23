@Returns

Feature: Returns Feature

  Scenario: A user adds sales for multiple countries to a return
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9009999888 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the Start your return link
    Then the user answers yes on the 2023-M12/start-return page
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
    And the user answers yes on the add-sales-country-list page
    And the user selects Portugal on the third sold-to-country page
    And the user ticks the third checkbox on the third vat-rates-from-country page
    And the user ticks the fifth checkbox on the third vat-rates-from-country page
    And the user clicks the continue button
    And the user enters third country total sales of 123.87 for first selected VAT rate on the sales-to-country page
    And the user confirms the vat for the third EU country as the suggested amount for the first selected VAT rate on the vat-on-sales page
    And the user enters third country total sales of 6433 for second selected VAT rate on the sales-to-country page
    And the user enters a different amount of VAT totalling 1500 for the third country and the second selected VAT rate on the vat-on-sales page
    And the user answers no on the check-sales/3 page
    And the user answers no on the add-sales-country-list page
    Then the user is on the check-your-answers page
    And the user clicks the submit button
    Then the user is on the return-successfully-submitted page
    When the user clicks on the Pay Now button
    Then the user has been redirected to the payments service

  Scenario: A user adds sales with all possible VAT rates for a country via VAT rates CYA page
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9009999888 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the Start your return link
    Then the user answers yes on the 2023-M12/start-return page
    And the user answers yes on the sold-goods page
    And the user selects Croatia on the first sold-to-country page
    And the user ticks the first checkbox on the first vat-rates-from-country page
    And the user clicks the continue button
    And the user enters first country total sales of 1500 for first selected VAT rate on the sales-to-country page
    And the user confirms the vat for the first EU country as the suggested amount for the first selected VAT rate on the vat-on-sales page
    Then the user answers yes on the check-sales/1 page
    And the user ticks the second checkbox on the first vat-rates-from-country page
    And the user clicks the continue button
    And the user enters first country total sales of 147.65 for second selected VAT rate on the sales-to-country page
    And the user enters a different amount of VAT totalling 100.21 for the first country and the second selected VAT rate on the vat-on-sales page
    Then the user answers yes on the check-sales/1 page
    And the user answers yes on the remaining-vat-rate-from-country/1/3 page
    And the user enters first country total sales of 16001 for third selected VAT rate on the sales-to-country page
    And the user confirms the vat for the first EU country as the suggested amount for the third selected VAT rate on the vat-on-sales page
    Then the user clicks the continue button
    And the user answers no on the add-sales-country-list page
    Then the user is on the check-your-answers page
    And the user clicks the submit button
    Then the user is on the return-successfully-submitted page

  Scenario: A user can submit a nil return
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9009999888 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the Start your return link
    Then the user answers yes on the 2023-M12/start-return page
    And the user answers no on the sold-goods page
    Then the user is on the check-your-answers page
    And the user clicks the submit button
    Then the user is on the return-successfully-submitted page

  Scenario: A user can access their next available return via the secure messages link
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9001234567 accesses the returns journey
    When the user accesses the start return link via secure messages
    Then the user is on the 2023-M12/start-return page

  Scenario: An assistant user can access the returns service
    Given the user accesses the authority wizard
    And a assistant with VRN 100000001 and IOSS Number IM9001234567 accesses the returns journey
    Then the user is redirected to their IOSS Account




