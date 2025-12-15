@Intermediary

Feature: Intermediary Returns Feature

  Scenario: Intermediary can submit a nil return for a NETP
    Given the user accesses the authority wizard
    And intermediary IN9001234567 accesses the returns journey for NETP IM9001144771
    Then the user answers yes on the 2025-M3/start-return page
    And the user answers no on the sold-goods page
    And the user answers no on the correct-previous-return page
    Then the user is on the check-your-answers page
    And the user clicks the submit button
    Then the user is on the return-successfully-submitted page
    And the correct details are shown on the acknowledgement page for NETP IM9001144771
    When the user clicks on the Back to your account link
    Then the user is redirected to their Intermediary Dashboard

  Scenario: Intermediary can submit a return with sales to the EU for a NETP
    Given the user accesses the authority wizard
    And intermediary IN9001234567 accesses the returns journey for NETP IM9001144771
    Then the user answers yes on the 2025-M3/start-return page
    And the user answers yes on the sold-goods page
    And the user selects France on the first sold-to-country page
    And the user ticks the first checkbox on the first vat-rates-from-country page
    And the user ticks the fifth checkbox on the first vat-rates-from-country page
    And the user clicks the continue button
    And the user enters first country total sales of 6000 for first selected VAT rate on the sales-to-country page
    And the user confirms the vat for the first EU country as the suggested amount for the first selected VAT rate on the vat-on-sales page
    And the user enters first country total sales of 987654.32 for second selected VAT rate on the sales-to-country page
    And the user confirms the vat for the first EU country as the suggested amount for the second selected VAT rate on the vat-on-sales page
    And the user answers no on the check-sales/1 page
    And the user answers yes on the add-sales-country-list page
    And the user selects Italy on the second sold-to-country page
    And the user ticks the first checkbox on the second vat-rates-from-country page
    And the user clicks the continue button
    And the user enters second country total sales of 1234 for first selected VAT rate on the sales-to-country page
    And the user enters a different amount of VAT totalling 120.56 for the second country and the first selected VAT rate on the vat-on-sales page
    And the user answers no on the check-sales/2 page
    And the user answers yes on the add-sales-country-list page
    And the user selects Portugal on the third sold-to-country page
    And the user ticks the third checkbox on the third vat-rates-from-country page
    And the user ticks the fifth checkbox on the third vat-rates-from-country page
    And the user clicks the continue button
    And the user enters third country total sales of 123.87 for first selected VAT rate on the sales-to-country page
    And the user confirms the vat for the third EU country as the suggested amount for the first selected VAT rate on the vat-on-sales page
    And the user enters third country total sales of 6433 for second selected VAT rate on the sales-to-country page
    And the user enters a different amount of VAT totalling 1500 for the third country and the second selected VAT rate on the vat-on-sales page
    And the user answers no on the check-sales/3 page
    And the user answers no on the add-sales-country-list page
    And the user answers no on the correct-previous-return page
    Then the user is on the check-your-answers page
    And the user clicks the submit button
    Then the user is on the return-successfully-submitted page
    And the correct details are shown on the acknowledgement page for NETP IM9001144771
    When the user clicks on the Back to your account link
    Then the user is redirected to their Intermediary Dashboard

  Scenario: Intermediary can submit a return for a NETP with corrections for multiple periods
    Given the user accesses the authority wizard
    And intermediary IN9001234567 accesses the returns journey for NETP IM9001144777
    Then the user answers yes on the 2025-M3/start-return page
    And the user answers no on the sold-goods page
    And the user answers yes on the correct-previous-return page
    When the user picks year 2025 on the correction-return-year/1 page
    Then the user picks month January on the correction-return-month/1 page
    And the user chooses the country Germany as their first correction within the first correction period
    And the user is on the country-vat-correction-amount/1/1 page
    And the previously declared text is displayed above the amount box
    And the user adds -500 on the first country-vat-correction-amount page for the first correction period
    And the user answers yes on the vat-payable-confirm/1/1 page
    And the user is on the vat-payable-check/1/1 page
    And the user clicks the continue button
    When the user answers yes on the correction-list-countries/1 page
    And the user chooses the country France as their second correction within the first correction period
    And the user answers yes on the add-new-country/1/2 page
    And the user adds 400 on the second country-vat-correction-amount page for the first correction period
    And the user answers yes on the vat-payable-confirm/1/2 page
    And the user is on the vat-payable-check/1/2 page
    And the user clicks the continue button
    And the user answers no on the correction-list-countries/1 page
    And the user answers yes on the 2025-M3/vat-correction-months-add page
    When the user picks year 2025 on the correction-return-year/2 page
    Then the user picks month February on the correction-return-month/2 page
    And the user chooses the country Germany as their first correction within the second correction period
    And the user is on the country-vat-correction-amount/2/1 page
    And the previously declared text is displayed above the amount box
    And the user adds 125.25 on the first country-vat-correction-amount page for the second correction period
    And the user answers yes on the vat-payable-confirm/2/1 page
    And the user is on the vat-payable-check/2/1 page
    And the user clicks the continue button
    And the user answers no on the correction-list-countries/2 page
    Then the user is on the 2025-M3/vat-correction-months page
    And the user clicks the continue button
    Then the user is on the check-your-answers page
    And the user clicks the submit button
    Then the user is on the return-successfully-submitted page
    And the correct details are shown on the acknowledgement page for NETP IM9001144777
    When the user clicks on the Back to your account link
    Then the user is redirected to their Intermediary Dashboard

  Scenario: Intermediary can submit a first return for a NETP
    Given the user accesses the authority wizard
    And intermediary IN9001234567 accesses the returns journey for NETP IM9001144773
    Then the user answers yes on the 2025-M1/start-return page
    And the user answers no on the sold-goods page
    Then the user is on the check-your-answers page
    And the user clicks the submit button
    Then the user is on the return-successfully-submitted page
    And the correct details are shown on the acknowledgement page for NETP IM9001144773
    When the user clicks on the Back to your account link
    Then the user is redirected to their Intermediary Dashboard

  Scenario: Intermediary cannot access return for a client not registered to them
    Given the user accesses the authority wizard
    And intermediary IN9001234568 accesses the returns journey for NETP IM9001144771
    Then the user is on the cannot-use-not-registered page

  Scenario: Intermediary has multiple outstanding payments for a NETP
    Given the user accesses the authority wizard
    And intermediary IN9001234567 accesses the payments journey for NETP IM9001144771
    When the user selects the first payment option on the outstanding-payments page
    Then the user has been redirected to the payments service
    And the user clicks back on the browser
    And the user is on the outstanding-payments page
    When the user selects the second payment option on the outstanding-payments page
    Then the user has been redirected to the payments service

  Scenario: Intermediary has a single outstanding payment for a NETP
    Given the user accesses the authority wizard
    And intermediary IN9001234567 accesses the payments journey for NETP IM9001144777
    Then the user has been redirected to the payments service

  Scenario: Intermediary has no outstanding payments for a NETP
    Given the user accesses the authority wizard
    When intermediary IN9001234567 accesses the payments journey for NETP IM9001144773
    And the user is on the outstanding-payments page
    Then the NETP does not owe any VAT

  Scenario: Intermediary cannot access payments for a client not registered to them
    Given the user accesses the authority wizard
    And intermediary IN9001234568 accesses the payments journey for NETP IM9001144771
    Then the user is on the cannot-use-not-registered page

  Scenario: Intermediary views previous returns from the same year
    Given the user accesses the authority wizard
    And intermediary IN9001234567 accesses the submitted-returns journey for NETP IM9001144771
    Then the user is on the past-returns page
    And the user clicks the Show all sections accordion
    When the user clicks on the January 2025 link
    Then the user is on the past-returns/2025-M1 page
    And the return for January 2025 is displayed to the user
    And the correct sections are displayed on the previous return with no corrections
    When the user clicks back on the browser
    And the user is on the past-returns page
    When the user clicks the Pay now link for February 2025
    Then the user has been redirected to the payments service

  Scenario: Intermediary views previous returns from multiple years including a nil return
    Given the user accesses the authority wizard
    And intermediary IN9001001001 accesses the submitted-returns journey for NETP IM9001001001
    Then the user is on the past-returns page
    And the user clicks the Show all sections accordion
    When the user clicks on the December 2024 link
    Then the user is on the past-returns/2024-M12 page
    And the return for December 2024 is displayed to the user
    Then the correct sections are displayed for a nil return
    When the user clicks back on the browser
    And the user is on the past-returns page
    When the user clicks the Pay now link for January 2025
    Then the user has been redirected to the payments service

  Scenario: Intermediary cannot access previous returns for a client not registered to them
    Given the user accesses the authority wizard
    And intermediary IN9001001001 accesses the submitted-returns journey for NETP IM9001144771
    Then the user is on the cannot-use-not-registered page

  Scenario: Intermediary views previous returns for excluded NETP
    Given the user accesses the authority wizard
    And intermediary IN9008888886 accesses the submitted-returns journey for NETP IM9001144884
    Then the user is on the past-returns page
    And the user clicks the Show all sections accordion
    When the user clicks on the January 2025 link
    Then the user is on the past-returns/2025-M1 page
    And the return for January 2025 is displayed to the user
    And the correct sections are displayed on the previous return with no corrections
    When the user clicks back on the browser
    And the user is on the past-returns page
    When the user clicks the Pay now link for February 2025
    Then the user has been redirected to the payments service

  Scenario: A NETP who has transferred from another member state has a partial first return
    Given the user accesses the authority wizard
    And intermediary IN9005999997 accesses the returns journey for NETP IM9005999997
    And the user is on the 2024-M1/start-return page
    Then the user transferring from another MSID is offered a partial return for the correct period
    And the user answers yes on the 2024-M1/start-return page
    And the user answers no on the sold-goods page
    Then the user is on the check-your-answers page
    And the user transferring from another MSID is submitting a partial return for the correct period
    And the user clicks the submit button
    Then the user is on the return-successfully-submitted page

  Scenario: A NETP who is transferring to another member state has a partial final return
    Given the user accesses the authority wizard
    And intermediary IN9005999997 accesses the returns journey for NETP IM9009999555
    And the user is on the 2024-M2/start-return page
    Then they are presented with the heading for their final return
    And the user transferring to another MSID is offered a partial return for the correct period
    And the user answers yes on the 2024-M2/start-return page
    And the user answers no on the sold-goods page
    Then the user is on the correct-previous-return page
    And they are advised it is their last chance to correct a return
    And the user answers no on the correct-previous-return page
    And the user is on the check-your-answers page
    Then the user is shown the corrections warning before submission
    And the user transferring to another MSID is submitting a partial return for the correct period
    And the user clicks the submit button
    Then the user is on the return-successfully-submitted page

  Scenario: Correct company information displayed on a return for UK based NETP with VRN
    Given the user accesses the authority wizard
    And intermediary IN9001234567 accesses the returns journey for NETP IM9001144771
    Then the user answers yes on the 2025-M3/start-return page
    And the user is on the sold-goods page
    And the correct caption is displayed for UK with VRN
    And the user answers no on the sold-goods page
    And the user answers no on the correct-previous-return page
    Then the user is on the check-your-answers page
    And the user clicks the submit button
    Then the user is on the return-successfully-submitted page
    And the correct details are shown on the acknowledgement page for NETP IM9001144771

  Scenario: Correct company information displayed on a return for UK based NETP with UTR
    Given the user accesses the authority wizard
    And intermediary IN9001234567 accesses the returns journey for NETP IM9001144773
    Then the user answers yes on the 2025-M1/start-return page
    And the user is on the sold-goods page
    And the correct caption is displayed for UK with UTR
    And the user answers no on the sold-goods page
    And the user answers no on the correct-previous-return page
    Then the user is on the check-your-answers page
    And the user clicks the submit button
    Then the user is on the return-successfully-submitted page

  Scenario: Correct company information displayed on a return for UK based NETP with NINO
    Given the user accesses the authority wizard
    And intermediary IN9001234567 accesses the returns journey for NETP IM9001144778
    Then the user answers yes on the 2025-M1/start-return page
    And the user is on the sold-goods page
    And the correct caption is displayed for UK with NINO
    And the user answers no on the sold-goods page
    And the user answers no on the correct-previous-return page
    Then the user is on the check-your-answers page
    And the user clicks the submit button
    Then the user is on the return-successfully-submitted page

  Scenario: Correct company information displayed on a return for Non-UK based NETP with VRN
    Given the user accesses the authority wizard
    And intermediary IN9001234567 accesses the returns journey for NETP IM9001144777
    Then the user answers yes on the 2025-M3/start-return page
    And the user is on the sold-goods page
    And the correct caption is displayed for Non-UK with VRN
    And the user answers no on the sold-goods page
    And the user answers no on the correct-previous-return page
    Then the user is on the check-your-answers page
    And the user clicks the submit button
    Then the user is on the return-successfully-submitted page

  Scenario: Correct company information displayed on a return for Non-UK based NETP with FTR
    Given the user accesses the authority wizard
    And intermediary IN9001234567 accesses the returns journey for NETP IM9001144775
    Then the user answers yes on the 2025-M1/start-return page
    And the user is on the sold-goods page
    And the correct caption is displayed for Non-UK with FTR
    And the user answers no on the sold-goods page
    And the user answers no on the correct-previous-return page
    Then the user is on the check-your-answers page
    And the user clicks the submit button
    Then the user is on the return-successfully-submitted page




