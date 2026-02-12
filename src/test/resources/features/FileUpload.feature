@Intermediary

Feature: File Upload Feature

  Scenario: Intermediary can submit a return for a NETP using file upload
    Given the user accesses the authority wizard
    And intermediary IN9001234567 accesses the returns journey for NETP IM9001144771
    Then the user answers yes on the 2025-M3/start-return page
    And the user is on the want-to-upload-file page
    When the user selects Yes to upload a file
    Then the user is on the file-upload page
    And the user uploads the file Test.csv
    And the user is on the file-uploaded page
    And the user selects Yes to upload a file
    And the user answers no on the correct-previous-return page
    Then the user is on the check-your-answers page

  Scenario: A global IOSS user can submit a return using file upload
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9009999888 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the Start your return link
    Then the user answers yes on the 2023-M12/start-return page
    And the user is on the want-to-upload-file page
    When the user selects Yes to upload a file
    Then the user is on the file-upload page
    And the user uploads the file Test.csv
    And the user is on the file-uploaded page
    And the user selects Yes to upload a file
    Then the user is on the check-your-answers page



