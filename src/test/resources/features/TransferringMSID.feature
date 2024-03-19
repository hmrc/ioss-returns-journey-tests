@Returns

Feature: Transferring MSID Feature

  Scenario: A user who has transferred from another member state has a partial first return
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9005999997 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the Start your return link
#    check partial return dates
    Then the user answers yes on the 2024-M2/start page
    And the user answers no on the sold-goods page
    Then the user is on the check-your-answers page
    And the user clicks the continue button
    Then the user is on the successfully-submitted page
#    checking with design if we need checks on cya and previously submitted returns for partial dates

  Scenario: A user who is transferring to another member state has a partial final return
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9009999555 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the Start your return link
#    check partial return dates
    Then the user answers yes on the 2024-M2/start page
    And the user answers no on the sold-goods page
    Then the user is on the check-your-answers page
    And the user clicks the continue button
    Then the user is on the successfully-submitted page
#    checking with design if we need checks on cya and previously submitted returns for partial dates

