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
    When the user clicks the Save and come back later button
    Then the user clicks on the continue to complete your return link
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






