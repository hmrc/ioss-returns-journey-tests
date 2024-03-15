@Returns

Feature: BTA Feature

  Scenario: A user enters the Your Account page via BTA, submits a nil return and has the correct link back to BTA
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9009999888 accesses the returns journey
    Then the user manually navigates to the your-account-from-bta link
    Then the user is redirected to their IOSS Account
    When the user clicks on the Start your return link
    Then the user answers yes on the 2023-M12/start page
    And the user answers no on the sold-goods page
    Then the user is on the check-your-answers page
    And the user clicks the continue button
    Then the user is on the successfully-submitted page
#    Need ID for back to your account back-to-your-account
#    Also currently goes back to your-account and not business-account
#    VEIOSS-493
#    Then the user clicks on the Back to your account link
#    And the user is on the business-account page

  Scenario: A user enters the Start Your Return page via BTA, submits a nil return and has the correct link back to BTA
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9009999888 accesses the returns journey
    Then the user manually navigates to the start-your-return-from-bta/2023-M12 link
    Then the user answers yes on the 2023-M12/start page
    And the user answers no on the sold-goods page
    Then the user is on the check-your-answers page
    And the user clicks the continue button
    Then the user is on the successfully-submitted page
#    Need ID for back to your account back-to-your-account
#    Also currently goes back to your-account and not business-account
#    VEIOSS-493
#    Then the user clicks on the Back to your account link
#    And the user is on the business-account page

  Scenario: A user with one outstanding payment clicks the BTA payment link and is directed to the payments service
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9009999888 accesses the returns journey
    Then the user manually navigates to the payments-from-bta link
#  Currently goes to outstanding-payments within returns service with no content on page
#Should this be implemented yet?
#  In OSS we had a single outstanding payment and multiple outstanding payments version of this test - which IOSS numbers can we use
#Also there should be a Welsh version

  @Accessibility
  Scenario: A Welsh user enters the Your Account page via BTA and sees the Welsh transition page before "Your Account"
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9009999888 accesses the returns journey
    Then the user manually navigates to the your-account-from-bta?lang=cy link
    And the user is directed to the Welsh transition page
    Then the user clicks the continue button
    And the user is on the your-account page

  Scenario: A Welsh user enters the Start Your Return page via BTA and sees the Welsh transition page before they start their return
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9009999888 accesses the returns journey
    Then the user manually navigates to the start-your-return-from-bta/2023-M12?lang=cy link
    And the user is directed to the Welsh transition page
    Then the user clicks the continue button
    Then the user is on the 2023-M12/start page

  Scenario: A user enters the Your Account page via BTA with the en specific parameter and is directed straight to "Your Account"
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9009999888 accesses the returns journey
    Then the user manually navigates to the your-account-from-bta?lang=en link
    Then the user is redirected to their IOSS Account

#  Future - returns-history-from-bta - not currently possible as past-returns not fully implemented yet
#  Same for Welsh user - returns-history-from-bta?lang=cy

#  Future - continue-return-from-bta - not currently possible as save and come back later not implemented
#  Same for Welsh user - continue-return-from-bta/2023-M10?lang=cy


