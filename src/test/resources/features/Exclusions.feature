@Returns

Feature: Exclusions Feature
  @Accessibility
  Scenario: An excluded user is not able to start a return after their exclusion date
    Given the user accesses the authority wizard
    When a user with VRN 100000001 and IOSS Number IM9009999991 accesses the returns journey
#    can't add any dashboard checks until other implementation has been done
    Then the user manually navigates to their December 2023 return
    And the user is on the excluded-cannot-use-service page

  Scenario: A user who has reversed their exclusion is able to start a return for the next available period
    Given the user accesses the authority wizard
    When a user with VRN 100000001 and IOSS Number IM9009999992 accesses the returns journey
#    can't add any dashboard checks until other implementation has been done
    Then the user manually navigates to their December 2023 return
    And the user is on the 2023-M12/start page

#    Rejoin not implemented yet
#  Scenario: A user who has rejoined the scheme is able to start a return

  Scenario: A user who is excluded in the future sees the correct dashboard messages when they have a return due next month
    Given the user accesses the authority wizard
    When a user with VRN 100000001 and IOSS Number IM9009999995 accesses the returns journey
    And the user is redirected to their IOSS Account
    Then they are presented with the correct banner for trader with an exclusion date in the future with a return due
    And they are shown the correct returns message for one outstanding return
    And they are shown the correct returns message for one return due next month

    #User has requested to leave the service and has no returns due
#  not possible - can't have exclusion date in future and not have any returns due - awaiting design response

  Scenario: A user who has an exclusion date in the month before their last return sees the correct dashboard messages
    Given the user accesses the authority wizard
    When a user with VRN 100000001 and IOSS Number IM9029999994 accesses the returns journey
    And the user is redirected to their IOSS Account
    Then they are presented with the correct banner for trader with an exclusion date in the past with a return due
    And they are shown the correct returns message for one outstanding return
    When the user clicks on the Start your return link
    Then the user is on the 2024-M1/start page

#  dev - added the messages for that yesterday but need the calculate if the current return is final to show that scenario.
  Scenario: A user who has left the service and has no outstanding actions
    Given the user accesses the authority wizard
    When a user with VRN 100000001 and IOSS Number IM9039999994 accesses the returns journey
    And the user is redirected to their IOSS Account
    Then they are presented with the correct banner for trader with an exclusion date in the past and no outstanding actions
    And they are shown the correct returns message for no outstanding returns
    And the returns tile shows final return is completed


#We removed you - returns still to complete - no rejoin button
#We removed you - no more returns - rejoin button
#We excluded you - returns still to complete - date you can rejoin - no rejoin button
#We excluded you - no more returns - date you can rejoin - no rejoin button



