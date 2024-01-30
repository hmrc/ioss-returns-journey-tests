@Returns

Feature: Rejoin Feature

  Scenario: A user can access Change Your Registration via Your Account in the Returns service
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9019999997 accesses the returns journey
    Then the user is redirected to their IOSS Account
    When the user clicks on the Rejoin registration link
    Then the user is redirected to the Rejoin Registration page in IOSS Registration


