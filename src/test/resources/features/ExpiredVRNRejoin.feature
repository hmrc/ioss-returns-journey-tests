@Additional

Feature: Expired VRN Rejoin Feature

  Scenario: A trader who has left the service with outstanding returns and now has an expired VRN
    Given the user accesses the authority wizard
    And a user with VRN 600000001 and IOSS Number IM9029999994 accesses the returns journey
    Then the user is redirected to their IOSS Account
    And they are presented with the correct banner for expired VRN trader who has left the service and has outstanding returns
    And the link to Rejoin this service is not displayed on the dashboard

  Scenario: A trader who has left the service with no outstanding returns and now has an expired VRN
    Given the user accesses the authority wizard
    And a user with VRN 600000001 and IOSS Number IM9039999994 accesses the returns journey
    Then the user is redirected to their IOSS Account
    And they are presented with the correct banner for expired VRN trader who has left the service and has no outstanding returns
    And the link to Rejoin this service is not displayed on the dashboard

#  Not showing the VAT part of the banner?
  Scenario: A trader who has been removed from the service with outstanding returns and now has an expired VRN
    Given the user accesses the authority wizard
    And a user with VRN 600000001 and IOSS Number IM9049999994 accesses the returns journey
    Then the user is redirected to their IOSS Account
    And they are presented with the correct banner for expired VRN trader removed from service and has outstanding returns
    And the link to Rejoin this service is not displayed on the dashboard

    #  Not showing the VAT part of the banner?
  Scenario: A trader who has been removed from the service with no outstanding returns and now has an expired VRN
    Given the user accesses the authority wizard
    And a user with VRN 600000001 and IOSS Number IM9059999994 accesses the returns journey
    Then the user is redirected to their IOSS Account
    And they are presented with the correct banner for expired VRN trader removed from service and has no outstanding returns
    And the link to Rejoin this service is not displayed on the dashboard

  Scenario: A trader who has left the service with outstanding returns older than 3 years and now has an expired VRN
    Given the user accesses the authority wizard
    And a user with VRN 600000001 and IOSS Number IM9001239999 accesses the returns journey
    Then the user is redirected to their IOSS Account
    Then they are presented with the correct banner for expired VRN trader who has left the service and has outstanding returns
    And a dashboard message is displayed for a return outstanding for more than 3 years
    And the link to Rejoin this service is not displayed on the dashboard

  Scenario: A trader who has left the service with outstanding payments older than 3 years and now has an expired VRN
    Given the user accesses the authority wizard
    And a user with VRN 600000001 and IOSS Number IM9001238999 accesses the returns journey
    Then the user is redirected to their IOSS Account
    And the correct payment message for payments due more than three years ago for excluded traders is displayed
    And the link to Rejoin this service is not displayed on the dashboard


#    Kickout page on reg service?


