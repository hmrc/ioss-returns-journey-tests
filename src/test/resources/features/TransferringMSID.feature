@Additional

Feature: Transferring MSID Feature

  Scenario: A user who has transferred from another member state has a partial first return
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9005999997 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the Start your return link
    And the user is on the 2024-M1/start-return page
    Then the user transferring from another MSID is offered a partial return for the correct period
    And the user answers yes on the 2024-M1/start-return page
    And the user is on the want-to-upload-file page
    Then the user selects No, enter them myself to upload a file
    And the user answers no on the sold-goods page
    Then the user is on the check-your-answers page
    And the user transferring from another MSID is submitting a partial return for the correct period
    And the user clicks the submit button
    Then the user is on the return-successfully-submitted page

  Scenario: A user who has transferred from another member state has a full second return
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9005999977 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the Start your return link
    And the user is on the 2024-M2/start-return page
    Then the user transferring from another MSID is offered a full return for the correct period
    And the user answers yes on the 2024-M2/start-return page
    And the user is on the want-to-upload-file page
    Then the user selects No, enter them myself to upload a file
    And the user answers no on the sold-goods page
    And the user answers no on the correct-previous-return page
    Then the user is on the check-your-answers page
    And the user clicks the submit button
    Then the user is on the return-successfully-submitted page

  Scenario: A user who is transferring to another member state has a full return to submit prior to their final partial return
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9009995555 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the Start your return link
    And the user is on the 2024-M1/start-return page
    Then they are not presented with the heading for their final return
    Then the user transferring to another MSID is offered a full return for the correct period
    And the user answers yes on the 2024-M1/start-return page
    And the user is on the want-to-upload-file page
    Then the user selects No, enter them myself to upload a file
    And the user answers no on the sold-goods page
    Then the user is on the correct-previous-return page
    And they are not advised it is their last chance to correct a return
    And the user answers no on the correct-previous-return page
    And the user is on the check-your-answers page
    Then the user is not shown the corrections warning before submission
    And the user clicks the submit button
    Then the user is on the return-successfully-submitted page

  Scenario: A user who is transferring to another member state has a partial final return
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9009999555 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the Start your return link
    And the user is on the 2024-M2/start-return page
    Then they are presented with the heading for their final return
    And the user transferring to another MSID is offered a partial return for the correct period
    And the user answers yes on the 2024-M2/start-return page
    And the user is on the want-to-upload-file page
    Then the user selects No, enter them myself to upload a file
    And the user answers no on the sold-goods page
    Then the user is on the correct-previous-return page
    And they are advised it is their last chance to correct a return
    And the user answers no on the correct-previous-return page
    And the user is on the check-your-answers page
    Then the user is shown the corrections warning before submission
    And the user transferring to another MSID is submitting a partial return for the correct period
    And the user clicks the submit button
    Then the user is on the return-successfully-submitted page

  Scenario: A user who transferred from another member state has a first partial return in past returns
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9005999777 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the View submitted returns link
    Then the user is on the past-returns page
    And the user clicks the Show all sections accordion
    When the user clicks on the January 2024 link
    Then the user is on the past-returns/2024-M1 page
    And the user transferring from another MSID has the correct partial dates in the past return

  Scenario: A user who transferred to another member state has a final partial return in past returns
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9009955555 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the View submitted returns link
    Then the user is on the past-returns page
    And the user clicks the Show all sections accordion
    When the user clicks on the February 2024 link
    Then the user is on the past-returns/2024-M2 page
    And the user transferring to another MSID has the correct partial dates in the past return


