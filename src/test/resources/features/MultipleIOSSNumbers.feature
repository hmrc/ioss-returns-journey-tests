@Additional

Feature: Multiple IOSS Numbers Feature

  Scenario: A user with one other IOSS number can view their current account and returns for their previous IOSS number
    Given the user accesses the authority wizard
    And a user with current IOSS Number IM9007230000 and at least one previous IOSS number accesses the returns journey
    Then the user is redirected to their IOSS Account
    And the correct IOSS number IM9007230000 is displayed on the dashboard
    When the user clicks on the View submitted returns link
    Then the user is on the past-returns page
    And the correct link for one previous IOSS number is displayed
    And the user clicks the Show all sections accordion
    When the user clicks on the first return month for current registration
    Then the correct returns and payments references are shown for IM9007230000
    And the user clicks back on the browser
    When the user clicks on the view returns link for one previous registration
    Then the user is on the view-returns-multiple-reg page
    And the correct IOSS number IM9006230000 is displayed on the page
    And the user clicks the Show all sections accordion
    When the user clicks on the first return month for one previous registration
    Then the correct returns and payments references are shown for IM9006230000
    When the user clicks back on the browser
    Then the user is on the view-returns-multiple-reg page
    And the user clicks on the Return to your current registration link
    Then the user is on the past-returns page

  Scenario: A user with more than one other IOSS number can view their current account and returns for their previous IOSS number
    Given the user accesses the authority wizard
    And a user with current IOSS Number IM9007230003 and at least one previous IOSS number accesses the returns journey
    Then the user is redirected to their IOSS Account
    And the correct IOSS number IM9007230003 is displayed on the dashboard
    When the user clicks on the View submitted returns link
    Then the user is on the past-returns page
    Then the correct link for more than one previous IOSS number is displayed
    And the user clicks the Show all sections accordion
    When the user clicks on the first return month for current registration
    Then the correct returns and payments references are shown for IM9007230003
    Then the user clicks back on the browser
    When the user clicks on the view returns link for multiple previous registrations
    Then the user is on the return-registration-selection page
    And the correct previous IOSS numbers are displayed
    When the user selects the returns for IOSS number IM9007230001
    Then the correct IOSS number IM9007230001 is displayed on the page
    And the user clicks the Show all sections accordion
    When the user clicks on the first return month for first previous registration
    Then the correct returns and payments references are shown for IM9007230001
    When the user clicks back on the browser
    Then the user is on the view-returns-multiple-reg page
    And the user clicks on the Return to your current registration link
    Then the user is on the past-returns page
    When the user clicks on the view returns link for multiple previous registrations
    Then the user is on the return-registration-selection page
    When the user selects the returns for IOSS number IM9007230002
    Then the correct IOSS number IM9007230002 is displayed on the page
    And the user clicks the Show all sections accordion
    When the user clicks on the first return month for second previous registration
    Then the correct returns and payments references are shown for IM9007230002

  Scenario: A user with one previous registration that has multiple outstanding payments
    Given the user accesses the authority wizard
    And a user with current IOSS Number IM9007230000 and at least one previous IOSS number accesses the returns journey
    Then the user is redirected to their IOSS Account
    And the dashboard warning is displayed regarding multiple outstanding payments on their one previous registration IM9006230000
    When the user clicks on the Pay for a previous registration link
    And the user selects the first payment option on the which-previous-registration-vat-period-to-pay page
    Then the user has been redirected to the payments service

  Scenario: A user with one previous registration that has one outstanding payment
    Given the user accesses the authority wizard
    And a user with current IOSS Number IM9005230000 and at least one previous IOSS number accesses the returns journey
    Then the user is redirected to their IOSS Account
    And the dashboard warning is displayed regarding one outstanding payment on their one previous registration IM9004230000
    When the user clicks on the Pay for a previous registration link
    Then the user has been redirected to the payments service

  Scenario: A user with more than one previous registration that has multiple outstanding payments
    Given the user accesses the authority wizard
    And a user with current IOSS Number IM9007230003 and at least one previous IOSS number accesses the returns journey
    Then the user is redirected to their IOSS Account
    And the dashboard warning is displayed regarding multiple outstanding payments on their multiple previous registrations IM9007230003
    When the user clicks on the Pay for a previous registration link
    And the user is on the which-previous-registration-to-pay page
    Then the correct multiple outstanding payment amounts are displayed for each previous registration
    When the user picks the first previous IOSS Number
    And the user selects the first payment option on the which-previous-registration-vat-period-to-pay page
    Then the user has been redirected to the payments service

  Scenario: A user with more than one previous registration that has single outstanding payments
    Given the user accesses the authority wizard
    And a user with current IOSS Number IM9007230006 and at least one previous IOSS number accesses the returns journey
    Then the user is redirected to their IOSS Account
    And the dashboard warning is displayed regarding one outstanding payment on their multiple previous registrations IM9007230006
    When the user clicks on the Pay for a previous registration link
    And the user is on the which-previous-registration-to-pay page
    Then the correct single outstanding payment amounts are displayed for each previous registration
    When the user picks the second previous IOSS Number
    Then the user has been redirected to the payments service






