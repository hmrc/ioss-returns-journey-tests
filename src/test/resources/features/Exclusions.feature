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

