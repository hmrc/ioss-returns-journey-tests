@Additional

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
    And the user clicks the submit button
    Then the user is on the successfully-submitted page
    When the user clicks on the Back to your account link
    Then the user has been redirected to BTA

  Scenario: A user enters the Start Your Return page via BTA, submits a nil return and has the correct link back to BTA
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9009999888 accesses the returns journey
    Then the user manually navigates to the start-your-return-from-bta/2023-M12 link
    Then the user answers yes on the 2023-M12/start page
    And the user answers no on the sold-goods page
    Then the user is on the check-your-answers page
    And the user clicks the submit button
    Then the user is on the successfully-submitted page
    When the user clicks on the Back to your account link
    Then the user has been redirected to BTA

  Scenario: A user with one outstanding payment clicks the BTA payment link and is directed to the payments service
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9008888888 accesses the returns journey
    Then the user manually navigates to the payments-from-bta link
    And the user has been redirected to the payments service

  Scenario: A user with multiple outstanding payments clicks the BTA payment link and is directed to the outstanding payments page
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9001234567 accesses the returns journey
    Then the user manually navigates to the payments-from-bta link
    And the user is on the outstanding-payments page
    When the user selects the second payment option on the outstanding-payments page
    Then the user has been redirected to the payments service

  Scenario: A user with no outstanding payments clicks the BTA payment link and is directed to no outstanding payments page
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9009999888 accesses the returns journey
    Then the user manually navigates to the payments-from-bta link
    And the user is on the outstanding-payments page
    And the user does not owe any VAT

  Scenario: A user with one outstanding payment clicks the Welsh version of the BTA payment link and is directed to the no Welsh service page before payments
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9008888888 accesses the returns journey
    Then the user manually navigates to the payments-from-bta?lang=cy link
    And the user is directed to the Welsh transition page
    Then the user clicks the continue button
    Then the user has been redirected to the payments service

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

  Scenario: A user enters their returns history via BTA
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9001234567 accesses the returns journey
    Then the user manually navigates to the returns-history-from-bta link
    And the user is on the past-returns page

  Scenario: A Welsh user enters their returns history via BTA and sees the Welsh page first
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9001234567 accesses the returns journey
    Then the user manually navigates to the returns-history-from-bta?lang=cy link
    And the user is directed to the Welsh transition page
    Then the user clicks the continue button
    And the user is on the past-returns page

  Scenario: A user accesses a saved return via BTA
    Given the user accesses the authority wizard
    And a user with VRN 100000444 and IOSS Number IM9001112222 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the Start your return link
    Then the user answers yes on the 2023-M12/start page
    And the user answers yes on the sold-goods page
    And the user selects Austria on the first sold-to-country page
    When the user clicks the Save and come back later button
    Then the user manually navigates to the continue-return-from-bta/2023-M12 link
    And the user is on the 2023-M12/return-continue page

  Scenario: A Welsh user accesses a saved return via BTA
    Given the user accesses the authority wizard
    And a user with VRN 100000444 and IOSS Number IM9001112222 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the Start your return link
    Then the user answers yes on the 2023-M12/start page
    And the user answers yes on the sold-goods page
    And the user selects Austria on the first sold-to-country page
    When the user clicks the Save and come back later button
    Then the user manually navigates to the continue-return-from-bta/2023-M12?lang=cy link
    And the user is directed to the Welsh transition page
    Then the user clicks the continue button
    And the user is on the 2023-M12/return-continue page




