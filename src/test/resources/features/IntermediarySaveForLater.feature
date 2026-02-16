@Intermediary

Feature: Intermediary Save For Later Feature

  Scenario: Intermediary with multiple saved returns can continue a previously saved return for a NETP
    Given the user accesses the authority wizard
    When intermediary IN9006655444 accesses the saved return journey for NETP IM9006655443
    And the user is on the 2025-M3/return-continue page
    And the correct client name is displayed for IM9006655443
    Then the user selects the Yes option
    Then the user is on the add-new-country/1/1 page
    And the user answers yes on the add-new-country/1/1 page
    And the user adds 1000 on the first country-vat-correction-amount page for the first correction period
    And the user answers yes on the vat-payable-confirm/1/1 page
    And the user is on the vat-payable-check/1/1 page
    And the user clicks the continue button
    Then the user is on the correction-list-countries/1 page
    And the user answers no on the correction-list-countries/1 page
    And the user answers no on the 2025-M3/vat-correction-months-add page
    Then the user is on the check-your-answers page
    And the user clicks the submit button
    Then the user is on the return-successfully-submitted page

  Scenario: Intermediary with multiple saved returns can delete a previously saved return for a NETP
    Given the user accesses the authority wizard
    When intermediary IN9006655444 accesses the saved return journey for NETP IM9006655442
    And the user is on the 2025-M1/return-continue page
    And the correct client name is displayed for IM9006655442
    Then the user selects the No, delete this return option
    And the user is on the 2025-M1/return-delete-confirm page
    And the intermediary selects yes to delete the return
    Then the user is redirected to their Intermediary Dashboard

  Scenario: Intermediary with a single saved return can continue a previously saved return for a NETP
    Given the user accesses the authority wizard
    When intermediary IN9006655555 accesses the saved return journey for NETP IM9006655551
    And the user is on the 2025-M1/return-continue page
    And the correct client name is displayed for IM9006655551
    Then the user selects the Yes option
    Then the user is on the sales-to-country/2/1 page
    And the user enters second country total sales of 1000 for first selected VAT rate on the sales-to-country page
    And the user confirms the vat for the second EU country as the suggested amount for the first selected VAT rate on the vat-on-sales page
    And the user enters second country total sales of 1000 for second selected VAT rate on the sales-to-country page
    And the user confirms the vat for the second EU country as the suggested amount for the second selected VAT rate on the vat-on-sales page
    And the user answers no on the check-sales/2 page
    And the user answers no on the add-sales-country-list page
    Then the user is on the check-your-answers page
    And the user clicks the submit button
    Then the user is on the return-successfully-submitted page

  Scenario: An intermediary can save the progress of a NETP return and return to the page they were on
    Given the user accesses the authority wizard
    When intermediary IN9006655555 accesses the returns journey for NETP IM9006655552
    Then the user answers yes on the 2025-M3/start-return page
    And the user is on the want-to-upload-file page
    Then the user selects No, enter them myself to upload a file
    And the user answers yes on the sold-goods page
    And the user is on the sold-to-country/1 page
    When the user clicks the Save and come back later button
    And the user is on the 2025-M3/progress-saved page
    Then the user clicks on the continue to complete your return link
    And the user selects Austria on the first sold-to-country page
    And the user ticks the first checkbox on the first vat-rates-from-country page
    And the user ticks the second checkbox on the first vat-rates-from-country page
    And the user clicks the continue button
    And the user is on the sales-to-country/1/1 page
    When the user clicks the Save and come back later button
    And the user is on the 2025-M3/progress-saved page
    Then the user clicks on the continue to complete your return link
    And the user enters first country total sales of 6000 for first selected VAT rate on the sales-to-country page
    And the user confirms the vat for the first EU country as the suggested amount for the first selected VAT rate on the vat-on-sales page
    And the user enters first country total sales of 1000.25 for second selected VAT rate on the sales-to-country page
    And the user confirms the vat for the first EU country as the suggested amount for the second selected VAT rate on the vat-on-sales page
    And the user is on the check-sales/1 page
    When the user clicks the Save and come back later button
    And the user is on the 2025-M3/progress-saved page
    Then the user clicks on the continue to complete your return link
    And the user answers no on the check-sales/1 page
    And the user is on the add-sales-country-list page
    When the user clicks the Save and come back later button
    And the user is on the 2025-M3/progress-saved page
    Then the user clicks on the continue to complete your return link
    And the user answers no on the add-sales-country-list page
    And the user answers yes on the correct-previous-return page
    And the user is on the correction-return-year/1 page
    When the user clicks the Save and come back later button
    And the user is on the 2025-M3/progress-saved page
    Then the user clicks on the continue to complete your return link
    When the user picks year 2025 on the correction-return-year/1 page
    And the user is on the correction-return-month/1 page
    When the user clicks the Save and come back later button
    And the user is on the 2025-M3/progress-saved page
    Then the user clicks on the continue to complete your return link
    Then the user picks month January on the correction-return-month/1 page
    And the user is on the correction-country/1/1 page
    When the user clicks the Save and come back later button
    And the user is on the 2025-M3/progress-saved page
    Then the user clicks on the continue to complete your return link
    And the user chooses the country Austria as their first correction within the first correction period
    And the user is on the add-new-country/1/1 page
    When the user clicks the Save and come back later button
    And the user is on the 2025-M3/progress-saved page
    Then the user clicks on the continue to complete your return link
    And the user answers yes on the add-new-country/1/1 page
    And the user is on the country-vat-correction-amount/1/1 page
    When the user clicks the Save and come back later button
    And the user is on the 2025-M3/progress-saved page
    Then the user clicks on the continue to complete your return link
    And the user adds 1500 on the first country-vat-correction-amount page for the first correction period
    And the user is on the vat-payable-confirm/1/1 page
    When the user clicks the Save and come back later button
    And the user is on the 2025-M3/progress-saved page
    Then the user clicks on the continue to complete your return link
    And the user answers yes on the vat-payable-confirm/1/1 page
    And the user is on the vat-payable-check/1/1 page
    When the user clicks the Save and come back later button
    And the user is on the 2025-M3/progress-saved page
    Then the user clicks on the continue to complete your return link
    And the user is on the vat-payable-check/1/1 page
    And the user clicks the continue button
    And the user answers no on the correction-list-countries/1 page
    And the user is on the 2025-M3/vat-correction-months-add page
    When the user clicks the Save and come back later button
    And the user is on the 2025-M3/progress-saved page
    Then the user clicks on the continue to complete your return link
    And the user answers no on the 2025-M3/vat-correction-months-add page
    Then the user is on the check-your-answers page
    And the user is on the check-your-answers page
    When the user clicks the Save and come back later button
    And the user is on the 2025-M3/progress-saved page
    Then the user clicks on the continue to complete your return link
    Then the user is on the check-your-answers page
    And the user clicks the submit button
    Then the user is on the return-successfully-submitted page

  Scenario: Intermediary cannot access saved return for a client who is not registered with them
    Given the user accesses the authority wizard
    When intermediary IN9001234567 accesses the saved return journey for NETP IM9006655443
    Then the user is on the cannot-use-not-registered page

