@Returns @Accessibility

Feature: Kickouts Feature

  Scenario: A user without an IOSS enrolment cannot access the returns service
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and no IOSS enrolment accesses the returns journey
    Then the user is on the cannot-use-not-registered page

  Scenario: A user answers no to starting their return
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9001234567 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the Start your return link
    Then the user answers no on the 2023-M12/start page
    And the user is on the no-other-periods-available page
    Then the user clicks on the Back to your account link
    And the user is on the your-account page

  Scenario: Failure page when there is an error submitting return due to the registration not being found on Core
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9007777777 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the Start your return link
    Then the user answers yes on the 2023-M12/start page
    And the user answers yes on the sold-goods page
    And the user selects Belgium on the first sold-to-country page
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
    And the user answers no on the correct-previous-return page
    Then the user is on the check-your-answers page
    And the user clicks the continue button
    Then the user is on the submission-failure page

  Scenario: Failure page when there is an error submitting the return to Core
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9007777778 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the Start your return link
    Then the user answers yes on the 2023-M12/start page
    And the user answers yes on the sold-goods page
    And the user selects Belgium on the first sold-to-country page
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
    And the user answers no on the correct-previous-return page
    Then the user is on the check-your-answers page
    And the user clicks the continue button
    Then the user is on the submission-failure page

  Scenario: A user has no available returns to start after clicking the start return link via secure messages
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9009999998 accesses the returns journey
    When the user accesses the start return link via secure messages
    Then the user is on the no-returns-due page

  Scenario: A user is not able to submit a return out of sequence
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9001234567 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user manually navigates to their January 2024 return
    Then the user is on the cannot-start-return page

  Scenario: A user is not able to submit a return for a period that has not yet ended
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9009999998 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user manually navigates to their current month return
#  currently going to cannot-start-return
    Then the user is on the no-other-periods-available page

  Scenario: An excluded trader is not able to submit a return for a period that is older than 3 years
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9001239999 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the Start your return link
#    currently going to cannot-start-return page in error
    Then the user is on the start return page for the first available return period within 3 years
    When the user manually navigates to their December 2020 return
#  not developed yet
    Then the user is on the new-page page




