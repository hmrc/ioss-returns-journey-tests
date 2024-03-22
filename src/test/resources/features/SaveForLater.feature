@Returns @Accessibility

Feature: Save For Later Feature

  Scenario: A user can save their progress and return to the last page they were on
    Given the user accesses the authority wizard
    And a user with VRN 100000123 and IOSS Number IM9001234566 accesses the returns journey
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

  Scenario: A user can save their progress and return to their in progress return via your account
    Given the user accesses the authority wizard
    And a user with VRN 100000123 and IOSS Number IM9001234566 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the Start your return link
    Then the user answers yes on the 2023-M12/start page
    And the user answers yes on the sold-goods page
    When the user clicks the Save and come back later button
    Then the user clicks on the continue to complete your return link
    And the user selects Belgium on the first sold-to-country page
    And the user ticks the first checkbox on the first vat-rates-from-country page
    And the user clicks the continue button
    And the user enters first country total sales of 1000 for first selected VAT rate on the sales-to-country page
    And the user confirms the vat for the first EU country as the suggested amount for the first selected VAT rate on the vat-on-sales page
    When the user clicks the Save and come back later button
    Then the user clicks on the return to your account link
#    No continue return link
    When the user clicks on the Start your return link
#    Continue the return in progress/delete and start again page not showing
#  Will add rest of steps once this is implemented

  Scenario: A user can save their progress and return to their in progress return after logging out and back in
    Given the user accesses the authority wizard
    And a user with VRN 100000123 and IOSS Number IM9001234566 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the Start your return link
    Then the user answers yes on the 2023-M12/start page
    And the user answers yes on the sold-goods page
    When the user clicks the Save and come back later button
    Then the user clicks on the continue to complete your return link
    And the user selects Bulgaria on the first sold-to-country page
    And the user ticks the first checkbox on the first vat-rates-from-country page
    And the user clicks the continue button
    And the user enters first country total sales of 1000 for first selected VAT rate on the sales-to-country page
    And the user confirms the vat for the first EU country as the suggested amount for the first selected VAT rate on the vat-on-sales page
    And the user answers no on the check-sales/1 page
    And the user answers no on the add-sales-country-list page
    When the user clicks the Save and come back later button
    Then the user clicks on the sign out and come back later link
    Given the user accesses the authority wizard
    And a user with VRN 100000123 and IOSS Number IM9001234566 accesses the returns journey
    Then the user is redirected to their IOSS Account
    #    No continue return link
    When the user clicks on the Start your return link
#    Continue the return in progress/delete and start again page not showing
#  Will add rest of steps once this is implemented

  Scenario: A user can delete a saved in-progress return and start again
    Given the user accesses the authority wizard
    And a user with VRN 100000123 and IOSS Number IM9001234566 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the Start your return link
    Then the user answers yes on the 2023-M12/start page
    And the user answers yes on the sold-goods page
    When the user clicks the Save and come back later button
    Then the user clicks on the continue to complete your return link
    And the user selects Croatia on the first sold-to-country page
    And the user ticks the first checkbox on the first vat-rates-from-country page
    And the user clicks the continue button
    And the user enters first country total sales of 1000 for first selected VAT rate on the sales-to-country page
    And the user confirms the vat for the first EU country as the suggested amount for the first selected VAT rate on the vat-on-sales page
    And the user answers no on the check-sales/1 page
    And the user answers no on the add-sales-country-list page
    And the user answers yes on the correct-previous-return page
    When the user picks year 2023 on the correction-return-year/1 page
    When the user clicks the Save and come back later button
    Then the user clicks on the sign out and come back later link
    Given the user accesses the authority wizard
    And a user with VRN 100000123 and IOSS Number IM9001234566 accesses the returns journey
    Then the user is redirected to their IOSS Account
    #    No continue return link
    When the user clicks on the Start your return link
#    Continue the return in progress/delete and start again page not showing
#  Will add rest of steps once this is implemented

  Scenario: A failed submission of a return is saved for later
    Given the user accesses the authority wizard
    And a user with VRN 100000123 and IOSS Number IM9007777778 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the Start your return link
    Then the user answers yes on the 2023-M12/start page
    And the user answers no on the sold-goods page
    And the user answers no on the correct-previous-return page
    Then the user is on the check-your-answers page
    And the user clicks the continue button
    Then the user is on the submission-failure page
#  Return should be saved for later so user can return to it, however this is not currently implemented inc. content

#    Once I can access the saved return via your account:
#    What if a user starts a return, saves, then gets excluded and comes back

#  Manual to check:
#    Has save button been removed from period/start page
#    Has save button been added to /remove-vat-rate-sales-for-country page

#  Questions:
#    Is saving by VRN still correct for OSS or should it be IOSS Number




