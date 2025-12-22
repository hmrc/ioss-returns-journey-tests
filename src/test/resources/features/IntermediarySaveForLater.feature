@Intermediary

Feature: Intermediary Save For Later Feature

  Scenario: Intermediary with multiple saved returns can continue a previously saved return for a NETP
    Given the user accesses the authority wizard
    When intermediary IN9006655444 accesses the multiple saved returns journey for NETP IM9006655443
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

  Scenario: Intermediary with a single saved return can continue a previously saved return for a NETP
    Given the user accesses the authority wizard
    When intermediary IN9006655555 accesses the single saved return journey for NETP IM9006655551
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
