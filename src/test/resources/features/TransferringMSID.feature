@Returns

Feature: Transferring MSID Feature

  Scenario: A user who has transferred from another member state has a partial first return
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9005999997 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the Start your return link
    Then the user transferring from another MSID is offered a partial return for the correct period
    And the user answers yes on the 2024-M1/start page
    And the user answers no on the sold-goods page
    Then the user is on the check-your-answers page
    And the user clicks the continue button
    Then the user is on the successfully-submitted page
#    checking with design if we need checks on cya and previously submitted returns for partial dates

  Scenario: A user who has transferred from another member state has a full second return
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9005999977 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the Start your return link
    Then the user transferring from another MSID is offered a full return for the correct period
    And the user answers yes on the 2024-M2/start page
    And the user answers no on the sold-goods page
    And the user answers no on the correct-previous-return page
    Then the user is on the check-your-answers page
    And the user clicks the continue button
    Then the user is on the successfully-submitted page
#    checking with design if we need checks on cya and previously submitted returns for partial dates

  Scenario: A user who is transferring to another member state has a full return to submit prior to their final partial return
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9009995555 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the Start your return link
    Then they are not presented with the heading for their final return
    Then the user transferring to another MSID is offered a full return for the correct period
    And the user answers yes on the 2024-M1/start page
    And the user answers no on the sold-goods page
    And the user answers no on the correct-previous-return page
    And the user is on the check-your-answers page
    And the user clicks the continue button
    Then the user is on the successfully-submitted page
#    checking with design if we need checks on cya and previously submitted returns for partial dates

  Scenario: A user who is transferring to another member state has a partial final return
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9009999555 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the Start your return link
    Then they are presented with the heading for their final return
    And the user transferring to another MSID is offered a partial return for the correct period
    And the user answers yes on the 2024-M2/start page
    And the user answers no on the sold-goods page
    And the user answers no on the correct-previous-return page
    And the user is on the check-your-answers page
    And the user clicks the continue button
    Then the user is on the successfully-submitted page
#    checking with design if we need checks on cya and previously submitted returns for partial dates


