@Returns @Accessibility

Feature: Corrections Feature

  @ZAP
  Scenario: A simple corrections journey with previously undeclared countries added to a nil return
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9001234567 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the Start your return link
    Then the user answers yes on the 2023-M10/start page
    And the user answers no on the soldGoods page
#   Logic to check if it is the first return does not exist yet
    And the user answers yes on the correct-previous-return page
    Then the user manually navigates to the first correction country
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
    And a user with VRN 100000001 and IOSS Number IM9001234567 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the Start your return link
    Then the user answers yes on the 2023-M10/start page
    And the user answers no on the soldGoods page
#   Logic to check if it is the first return does not exist yet
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
#   Logic to check if it is the first return does not exist yet
    And the user answers no on the correct-previous-return page
    Then the user is on the check-your-answers page
    And the user clicks the continue button
    Then the user is on the successfully-submitted page

#    Multiple periods available to correct
#    No periods to correct - first return

