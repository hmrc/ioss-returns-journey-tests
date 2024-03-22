@Returns @Accessibility

Feature: Save For Later Feature

  Scenario: A user can save their progress and return to the last page they were on
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9001234566 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the Start your return link
    Then the user answers yes on the 2023-M12/start page
    And the user answers yes on the sold-goods page
    When the user clicks the Save and come back later button
    Then the user clicks on the continue to complete your return link
    And the user selects Austria on the first sold-to-country page
    And the user ticks the first checkbox on the first vat-rates-from-country page
    And the user ticks the second checkbox on the first vat-rates-from-country page
    And the user clicks the continue button
#    When the user clicks the Save and come back later button
#    Then the user clicks on the continue to complete your return link
#    Currently broken - sorry there is a problem page
    And the user enters first country total sales of 6000 for first selected VAT rate on the sales-to-country page
    And the user confirms the vat for the first EU country as the suggested amount for the first selected VAT rate on the vat-on-sales page
    And the user enters first country total sales of 1000.25 for second selected VAT rate on the sales-to-country page
    And the user confirms the vat for the first EU country as the suggested amount for the second selected VAT rate on the vat-on-sales page
    When the user clicks the Save and come back later button
    Then the user clicks on the continue to complete your return link
    And the user answers no on the check-sales/1 page
    When the user clicks the Save and come back later button
    Then the user clicks on the continue to complete your return link
    And the user answers no on the add-sales-country-list page
    And the user answers yes on the correct-previous-return page
    When the user clicks the Save and come back later button
    Then the user clicks on the continue to complete your return link
    When the user picks year 2023 on the correction-return-year/1 page
    When the user clicks the Save and come back later button
    Then the user clicks on the continue to complete your return link
    Then the user picks month October on the correction-return-period/1 page
    When the user clicks the Save and come back later button
    Then the user clicks on the continue to complete your return link
    And the user chooses the country Austria as their first correction within the first correction period
    When the user clicks the Save and come back later button
    Then the user clicks on the continue to complete your return link
    And the user answers yes on the add-new-country/1/1 page
    When the user clicks the Save and come back later button
    Then the user clicks on the continue to complete your return link
    And the user adds 1500 on the first country-vat-correction-amount page for the first correction period
    When the user clicks the Save and come back later button
    Then the user clicks on the continue to complete your return link
    And the user answers yes on the vat-payable-confirm/1/1 page
#    Button not currently implemented on this page
#    When the user clicks the Save and come back later button
#    Then the user clicks on the continue to complete your return link
    And the user answers no on the correction-list-countries/1 page
    When the user clicks the Save and come back later button
    Then the user clicks on the continue to complete your return link
    And the user answers no on the 2023-M12/vat-correction-periods-add page
    Then the user is on the check-your-answers page
    When the user clicks the Save and come back later button
    Then the user clicks on the continue to complete your return link
    Then the user is on the check-your-answers page
    And the user clicks the continue button
    Then the user is on the successfully-submitted page

#    Also want scenarios for:
#    Save and go back to your account, resume saved return
#    Save and log out, resume saved return
#    Delete in progress return and start again
#    Failure on submitting return and check save for later is implemented
#    What if a user starts a return, saves, then gets excluded and comes back

#  Manual to check:
#    Has save button been removed from period/start page
#    Has save button been added to /remove-vat-rate-sales-for-country page




