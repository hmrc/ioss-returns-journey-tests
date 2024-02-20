@Returns

Feature: Rejoin Feature

  Scenario: A user can access Rejoin this service via Your Account in the Returns service
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9039999994 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the Rejoin this service link
    Then the user is redirected to the Rejoin page in IOSS Registration

  Scenario: A user who has an exclusion effective date in the future does not have the rejoin this service link on the dashboard
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9009999997 accesses the returns journey
    Then the user is redirected to their IOSS Account
    And the link to Rejoin this service is not displayed on the dashboard

  Scenario: A user who has an active quarantine does not have the rejoin this service link on the dashboard
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9009999993 accesses the returns journey
    Then the user is redirected to their IOSS Account
    And the link to Rejoin this service is not displayed on the dashboard

  Scenario: A user with over 3 years of outstanding returns cannot rejoin until 3 years of outstanding returns are complete
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9001239999 accesses the returns journey
    Then the user is redirected to their IOSS Account
    And the link to Rejoin this service is not displayed on the dashboard

  Scenario: A user with 1 outstanding return cannot rejoin until the outstanding return is complete
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9029999994 accesses the returns journey
    Then the user is redirected to their IOSS Account
    And the link to Rejoin this service is not displayed on the dashboard





