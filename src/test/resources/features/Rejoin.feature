@Returns

Feature: Rejoin Feature

  Scenario: A user can access Rejoin this service via Your Account in the Returns service
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9019999998 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the Rejoin this service link
    Then the user is redirected to the Rejoin page in IOSS Registration

  Scenario: A user who has an exclusion effective date in the future does not have the rejoin this service link on the dashboard
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9009999997 accesses the returns journey
    Then the user is redirected to their IOSS Account
    And the link to Rejoin this service is not displayed on the dashboard

#  quarantined shouldn't have link



