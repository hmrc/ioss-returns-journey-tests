@Returns

Feature: Exclusions Feature
  @Accessibility
  Scenario: An excluded user is not able to start a return after their exclusion date
    Given the user accesses the authority wizard
    When a user with VRN 100000001 and IOSS Number IM9009999991 accesses the returns journey
    Then the user manually navigates to their December 2023 return
    And the user is on the excluded-cannot-use-service page

  Scenario: A user who has reversed their exclusion is able to start a return for the next available period
    Given the user accesses the authority wizard
    When a user with VRN 100000001 and IOSS Number IM9009999992 accesses the returns journey
    Then the user manually navigates to their December 2023 return
    And the user is on the 2023-M12/start page

  Scenario: A user who is excluded in the future sees the correct dashboard messages when they have outstanding returns
    Given the user accesses the authority wizard
    When a user with VRN 100000001 and IOSS Number IM9009999995 accesses the returns journey
    And the user is redirected to their IOSS Account
    Then they are presented with the correct banner for trader with an exclusion date in the future with outstanding returns
    And they are shown the correct returns message for outstanding returns
    When the user clicks on the Start your return link
    Then the user is on the 2023-M12/start page

  Scenario: A user who has an exclusion date in the month before their last return sees the correct dashboard messages
    Given the user accesses the authority wizard
    When a user with VRN 100000001 and IOSS Number IM9029999994 accesses the returns journey
    And the user is redirected to their IOSS Account
    Then they are presented with the correct banner for trader with an exclusion date in the past with a return due
    And they are shown the correct returns message for outstanding returns
    When the user clicks on the Start your return link
    Then the user is on the 2024-M1/start page

  Scenario: A user who has left the service and has no outstanding actions
    Given the user accesses the authority wizard
    When a user with VRN 100000001 and IOSS Number IM9039999994 accesses the returns journey
    And the user is redirected to their IOSS Account
    Then they are presented with the correct banner for trader with an exclusion date in the past and no outstanding actions
    And they are shown the correct returns message for no outstanding returns
    And the returns tile shows final return is completed

  Scenario: A user who has been removed from the service and has outstanding returns
    Given the user accesses the authority wizard
    When a user with VRN 100000001 and IOSS Number IM9049999994 accesses the returns journey
    And the user is redirected to their IOSS Account
    Then they are presented with the correct banner for trader removed from service and has outstanding returns
    And they are shown the correct returns message for outstanding returns
    When the user clicks on the Start your return link
    Then the user is on the 2023-M12/start page

  Scenario: A user who has been removed from the service and has no outstanding returns
    Given the user accesses the authority wizard
    When a user with VRN 100000001 and IOSS Number IM9059999994 accesses the returns journey
    And the user is redirected to their IOSS Account
    Then they are presented with the correct banner for trader removed from service and has no outstanding returns
    And they are shown the correct returns message for no outstanding returns
    And the returns tile shows final return is completed

  Scenario: A user who has been quarantined and has outstanding returns
    Given the user accesses the authority wizard
    When a user with VRN 100000001 and IOSS Number IM9069999994 accesses the returns journey
    And the user is redirected to their IOSS Account
    Then they are presented with the correct banner for trader removed from service and has outstanding returns
    And they are shown the correct returns message for outstanding returns
    When the user clicks on the Start your return link
    Then the user is on the 2023-M12/start page

  Scenario: A user who has been quarantined and has no outstanding returns
    Given the user accesses the authority wizard
    When a user with VRN 100000001 and IOSS Number IM9003999993 accesses the returns journey
    And the user is redirected to their IOSS Account
    Then they are presented with the correct banner for a quarantined trader with no outstanding returns
    And they are shown the correct returns message for no outstanding returns
    And the returns tile shows final return is completed



