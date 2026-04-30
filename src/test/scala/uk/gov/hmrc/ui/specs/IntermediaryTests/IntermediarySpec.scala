/*
 * Copyright 2026 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.ui.specs.IntermediaryTests

import uk.gov.hmrc.ui.pages._
import uk.gov.hmrc.ui.specs.BaseSpec

class IntermediarySpec extends BaseSpec {

  private val dashboard        = Dashboard
  private val auth             = Auth
  private val fileUpload       = FileUpload
  private val intermediary     = Intermediary
  private val correction       = Correction
  private val payment          = Payment
  private val pastReturn       = PastReturn
  private val transferringMsid = TransferringMSID
  private val exclusion        = Exclusion

  Feature("Intermediary journeys") {

    Scenario("Intermediary can submit a nil return for a NETP") {

      Given("the user accesses the IOSS Returns Service on behalf of a NETP")
      auth.goToAuthorityWizard()
      auth.loginAsIntermediary("IN9001234567", "IM9001144771", "returns")

      And("the user answers yes on the IM9001144771/2025-M3/start-return page")
      dashboard.checkJourneyUrl("IM9001144771/2025-M3/start-return")
      dashboard.answerRadioButton("yes")

      Then("the user answers no on the IM9001144771/want-to-upload-file page")
      dashboard.checkJourneyUrl("IM9001144771/want-to-upload-file")
      fileUpload.selectFileUpload("No, enter them myself")

      And("the user answers no on the sold-goods page")
      dashboard.checkJourneyUrl("IM9001144771/sold-goods")
      dashboard.answerRadioButton("no")

      And("the user answers no on the IM9001144771/correct-previous-return page")
      dashboard.checkJourneyUrl("IM9001144771/correct-previous-return")
      dashboard.answerRadioButton("no")

      And("the user submits their return successfully via the check-your-answers page")
      dashboard.checkJourneyUrl("IM9001144771/check-your-answers")
      dashboard.submit()
      dashboard.checkJourneyUrl("IM9001144771/return-successfully-submitted")

      And("the correct details are shown on the acknowledgement page for NETP IM9001144771")
      intermediary.checkNetpReturn("IM9001144771")

      When("the user clicks on the Back to your account link")
      dashboard.clickLink("back-to-your-account")

      Then("the user is redirected to the Intermediary Dashboard")
      intermediary.dashboardUrlCheck("intermediary")
    }

    Scenario("Intermediary can submit a return with sales to the EU for a NETP") {

      Given("the user accesses the IOSS Returns Service on behalf of a NETP")
      auth.goToAuthorityWizard()
      auth.loginAsIntermediary("IN9001234567", "IM9001144771", "returns")

      And("the user answers yes on the IM9001144771/2025-M3/start-return page")
      dashboard.checkJourneyUrl("IM9001144771/2025-M3/start-return")
      dashboard.answerRadioButton("yes")

      Then("the user answers no on the IM9001144771/want-to-upload-file page")
      dashboard.checkJourneyUrl("IM9001144771/want-to-upload-file")
      fileUpload.selectFileUpload("No, enter them myself")

      And("the user answers yes on the IM9001144771/sold-goods page")
      dashboard.checkJourneyUrl("IM9001144771/sold-goods")
      dashboard.answerRadioButton("yes")

      And("the user selects a country on the sold-to-country page")
      dashboard.checkJourneyUrl("IM9001144771/sold-to-country/1")
      dashboard.selectCountry("France")

      And("the user selects the first and fifth checkbox on the vat-rates-from-country page")
      dashboard.checkJourneyUrl("IM9001144771/vat-rates-from-country/1")
      dashboard.tickCheckbox("first")
      dashboard.tickCheckbox("fifth")
      dashboard.continue()

      And("the user enters the first country total sales for first VAT rate")
      dashboard.checkJourneyUrl("IM9001144771/sales-to-country/1/1")
      dashboard.enterAnswer("6000")

      And("the user confirms the suggested amount of VAT")
      dashboard.checkJourneyUrl("IM9001144771/vat-on-sales/1/1")
      dashboard.clickLink("choice")
      dashboard.continue()

      And("the user enters the first country total sales for second VAT rate")
      dashboard.checkJourneyUrl("IM9001144771/sales-to-country/1/2")
      dashboard.enterAnswer("987654.32")

      And("the user confirms the suggested amount of VAT")
      dashboard.checkJourneyUrl("IM9001144771/vat-on-sales/1/2")
      dashboard.clickLink("choice")
      dashboard.continue()

      And("the user answers no on the check-sales page")
      dashboard.checkJourneyUrl("IM9001144771/check-sales/1")
      dashboard.answerRadioButton("no")

      And("the user answers yes on the add-sales-country-list page")
      dashboard.checkJourneyUrl("IM9001144771/add-sales-country-list")
      dashboard.answerRadioButton("yes")

      And("the user selects a country on the sold-to-country page")
      dashboard.checkJourneyUrl("IM9001144771/sold-to-country/2")
      dashboard.selectCountry("Italy")

      And("the user selects the first checkbox on the vat-rates-from-country page")
      dashboard.checkJourneyUrl("IM9001144771/vat-rates-from-country/2")
      dashboard.tickCheckbox("first")
      dashboard.continue()

      And("the user enters the second country total sales for first VAT rate")
      dashboard.checkJourneyUrl("IM9001144771/sales-to-country/2/1")
      dashboard.enterAnswer("1234")

      And("the user enters a different amount of VAT instead of the suggested amount of VAT")
      dashboard.checkJourneyUrl("IM9001144771/vat-on-sales/2/1")
      dashboard.enterAlternativeVatAmount("120.56")

      And("the user answers no on the check-sales page")
      dashboard.checkJourneyUrl("IM9001144771/check-sales/2")
      dashboard.answerRadioButton("no")

      And("the user answers yes on the add-sales-country-list page")
      dashboard.checkJourneyUrl("IM9001144771/add-sales-country-list")
      dashboard.answerRadioButton("yes")

      And("the user selects a country on the sold-to-country page")
      dashboard.checkJourneyUrl("IM9001144771/sold-to-country/3")
      dashboard.selectCountry("Portugal")

      And("the user selects the third and fifth checkbox on the vat-rates-from-country page")
      dashboard.checkJourneyUrl("IM9001144771/vat-rates-from-country/3")
      dashboard.tickCheckbox("third")
      dashboard.tickCheckbox("fifth")
      dashboard.continue()

      And("the user enters the third country total sales for first VAT rate")
      dashboard.checkJourneyUrl("IM9001144771/sales-to-country/3/1")
      dashboard.enterAnswer("123.87")

      And("the user confirms the suggested amount of VAT")
      dashboard.checkJourneyUrl("IM9001144771/vat-on-sales/3/1")
      dashboard.clickLink("choice")
      dashboard.continue()

      And("the user enters the third country total sales for second VAT rate")
      dashboard.checkJourneyUrl("IM9001144771/sales-to-country/3/2")
      dashboard.enterAnswer("6433")

      And("the user enters a different amount of VAT instead of the suggested amount of VAT")
      dashboard.checkJourneyUrl("IM9001144771/vat-on-sales/3/2")
      dashboard.enterAlternativeVatAmount("1500")

      And("the user answers no on the check-sales page")
      dashboard.checkJourneyUrl("IM9001144771/check-sales/3")
      dashboard.answerRadioButton("no")

      And("the user answers no on the add-sales-country-list page")
      dashboard.checkJourneyUrl("IM9001144771/add-sales-country-list")
      dashboard.answerRadioButton("no")

      And("the user answers no on the IM9001144771/correct-previous-return page")
      dashboard.checkJourneyUrl("IM9001144771/correct-previous-return")
      dashboard.answerRadioButton("no")

      And("the user submits their return successfully via the check-your-answers page")
      dashboard.checkJourneyUrl("IM9001144771/check-your-answers")
      dashboard.submit()
      dashboard.checkJourneyUrl("IM9001144771/return-successfully-submitted")

      And("the correct details are shown on the acknowledgement page for NETP IM9001144771")
      intermediary.checkNetpReturn("IM9001144771")

      When("the user clicks on the Back to your account link")
      dashboard.clickLink("back-to-your-account")

      Then("the user is redirected to the Intermediary Dashboard")
      intermediary.dashboardUrlCheck("intermediary")
    }

    Scenario("Intermediary can submit a return for a NETP with corrections for multiple periods") {

      Given("the user accesses the IOSS Returns Service on behalf of a NETP")
      auth.goToAuthorityWizard()
      auth.loginAsIntermediary("IN9001234567", "IM9001144777", "returns")

      And("the user answers yes on the IM9001144777/2025-M3/start-return page")
      dashboard.checkJourneyUrl("IM9001144777/2025-M3/start-return")
      dashboard.answerRadioButton("yes")

      Then("the user answers no on the IM9001144777/want-to-upload-file page")
      dashboard.checkJourneyUrl("IM9001144777/want-to-upload-file")
      fileUpload.selectFileUpload("No, enter them myself")

      And("the user answers no on the sold-goods page")
      dashboard.checkJourneyUrl("IM9001144777/sold-goods")
      dashboard.answerRadioButton("no")

      And("the user answers no on the IM9001144777/correct-previous-return page")
      dashboard.checkJourneyUrl("IM9001144777/correct-previous-return")
      dashboard.answerRadioButton("yes")

      And("the user picks 2025 on the correction-return-year page")
      dashboard.checkJourneyUrl("IM9001144777/correction-return-year/1")
      dashboard.clickLink("value_2025")
      dashboard.continue()

      And("the user picks January on the correction-return-month page")
      dashboard.checkJourneyUrl("IM9001144777/correction-return-month/1")
      dashboard.clickLink("value_January")
      dashboard.continue()

      And("the user selects a country")
      dashboard.checkJourneyUrl("IM9001144777/correction-country/1/1")
      dashboard.selectCountry("Germany")

      And("the country is showing the previously declared text")
      dashboard.checkJourneyUrl("IM9001144777/country-vat-correction-amount/1/1")
      correction.previouslyDeclaredText("NETP", display = true)

      And("the user enters the correction amount")
      dashboard.enterAnswer("-500")

      And("the user answers yes on the vat-payable-confirm page")
      dashboard.checkJourneyUrl("IM9001144777/vat-payable-confirm/1/1")
      dashboard.answerRadioButton("yes")

      And("the user clicks continue on the vat-payable-check page")
      dashboard.checkJourneyUrl("IM9001144777/vat-payable-check/1/1")
      dashboard.continue()

      And("the user answers yes on the correction-list-countries page")
      dashboard.checkJourneyUrl("IM9001144777/correction-list-countries/1")
      dashboard.answerRadioButton("yes")

      And("the user selects a country")
      dashboard.checkJourneyUrl("IM9001144777/correction-country/1/2")
      dashboard.selectCountry("France")

      And("the user answers yes on the add-new-country page")
      dashboard.checkJourneyUrl("IM9001144777/add-new-country/1/2")
      dashboard.answerRadioButton("yes")

      And("the user enters the correction amount")
      dashboard.checkJourneyUrl("IM9001144777/country-vat-correction-amount/1/2")
      dashboard.enterAnswer("400")

      And("the user answers yes on the vat-payable-confirm page")
      dashboard.checkJourneyUrl("IM9001144777/vat-payable-confirm/1/2")
      dashboard.answerRadioButton("yes")

      And("the user clicks continue on the vat-payable-check page")
      dashboard.checkJourneyUrl("IM9001144777/vat-payable-check/1/2")
      dashboard.continue()

      And("the user answers no on the correction-list-countries page")
      dashboard.checkJourneyUrl("IM9001144777/correction-list-countries/1")
      dashboard.answerRadioButton("no")

      And("the user clicks yes on the vat-correction-months-add page")
      dashboard.checkJourneyUrl("IM9001144777/2025-M3/vat-correction-months-add")
      dashboard.answerRadioButton("yes")

      And("the user picks 2025 on the correction-return-year page")
      dashboard.checkJourneyUrl("IM9001144777/correction-return-year/2")
      dashboard.clickLink("value_2025")
      dashboard.continue()

      And("the user picks February on the correction-return-month page")
      dashboard.checkJourneyUrl("IM9001144777/correction-return-month/2")
      dashboard.clickLink("value_February")
      dashboard.continue()

      And("the user selects a country")
      dashboard.checkJourneyUrl("IM9001144777/correction-country/2/1")
      dashboard.selectCountry("Germany")

      And("the country is showing the previously declared text")
      dashboard.checkJourneyUrl("IM9001144777/country-vat-correction-amount/2/1")
      correction.previouslyDeclaredText("NETP", display = true)

      And("the user enters the correction amount")
      dashboard.enterAnswer("125.25")

      And("the user answers yes on the vat-payable-confirm page")
      dashboard.checkJourneyUrl("IM9001144777/vat-payable-confirm/2/1")
      dashboard.answerRadioButton("yes")

      And("the user clicks continue on the vat-payable-check page")
      dashboard.checkJourneyUrl("IM9001144777/vat-payable-check/2/1")
      dashboard.continue()

      And("the user answers no on the correction-list-countries page")
      dashboard.checkJourneyUrl("IM9001144777/correction-list-countries/2")
      dashboard.answerRadioButton("no")

      And("the user clicks continue on the vat-correction-months page")
      dashboard.checkJourneyUrl("IM9001144777/2025-M3/vat-correction-months")
      dashboard.continue()

      And("the user submits their return successfully via the check-your-answers page")
      dashboard.checkJourneyUrl("IM9001144777/check-your-answers")
      dashboard.submit()
      dashboard.checkJourneyUrl("IM9001144777/return-successfully-submitted")

      And("the correct details are shown on the acknowledgement page for NETP IM9001144777")
      intermediary.checkNetpReturn("IM9001144777")

      When("the user clicks on the Back to your account link")
      dashboard.clickLink("back-to-your-account")

      Then("the user is redirected to the Intermediary Dashboard")
      intermediary.dashboardUrlCheck("intermediary")
    }

    Scenario("Intermediary can submit a first return for a NETP") {

      Given("the user accesses the IOSS Returns Service on behalf of a NETP")
      auth.goToAuthorityWizard()
      auth.loginAsIntermediary("IN9001234567", "IM9001144773", "returns")

      And("the user answers yes on the IM9001144773/2025-M3/start-return page")
      dashboard.checkJourneyUrl("IM9001144773/2025-M1/start-return")
      dashboard.answerRadioButton("yes")

      And("the user answers no on the want-to-upload-file page")
      dashboard.checkJourneyUrl("IM9001144773/want-to-upload-file")
      fileUpload.selectFileUpload("No, enter them myself")

      And("the user answers no on the sold-goods page")
      dashboard.checkJourneyUrl("IM9001144773/sold-goods")
      dashboard.answerRadioButton("no")

      And("the user submits their return successfully via the check-your-answers page")
      dashboard.checkJourneyUrl("IM9001144773/check-your-answers")
      dashboard.submit()
      dashboard.checkJourneyUrl("IM9001144773/return-successfully-submitted")

      And("the correct details are shown on the acknowledgement page for NETP IM9001144773")
      intermediary.checkNetpReturn("IM9001144773")

      When("the user clicks on the Back to your account link")
      dashboard.clickLink("back-to-your-account")

      Then("the user is redirected to the Intermediary Dashboard")
      intermediary.dashboardUrlCheck("intermediary")
    }

    Scenario("Intermediary cannot access return for a client not registered to them") {

      Given("the user accesses the IOSS Returns Service on behalf of a NETP who is not registered to them")
      auth.goToAuthorityWizard()
      auth.loginAsIntermediary("IN9001234568", "IM9001144773", "returns")

      Then("the user is on the cannot-use-not-registered page")
      dashboard.checkJourneyUrl("cannot-use-not-registered")
    }

    Scenario(
      "Intermediary has multiple outstanding payments for a NETP"
    ) {

      Given("the user accesses payments in the IOSS Returns Service on behalf of a NETP")
      auth.goToAuthorityWizard()
      auth.loginAsIntermediary("IN9001234567", "IM9001144771", "payments")

      And("the correct payments are displayed for the NETP")
      dashboard.checkJourneyUrl("IM9001144771/outstanding-payments")
      intermediary.checkNetpPayments()

      When(
        "the user selects the first payment option on the IM9001144771/outstanding-payments page"
      )
      dashboard.clickLink("value_0")
      dashboard.continue()

      Then("the user has been redirected to the payments service")
      dashboard.checkExternalServiceUrl("payments")

      And("the user clicks back on the browser")
      dashboard.clickBackButton()

      When("the user is on the IM9001144771/outstanding-payments page")
      dashboard.checkJourneyUrl("IM9001144771/outstanding-payments")

      And(
        "the user selects the second payment option on the IM9001144771/outstanding-payments page"
      )
      dashboard.clickLink("value_1")
      dashboard.continue()

      Then("the user has been redirected to the payments service")
      dashboard.checkExternalServiceUrl("payments")
    }

    Scenario(
      "Intermediary has a single outstanding payment for a NETP"
    ) {

      Given("the user accesses payments in the IOSS Returns Service on behalf of a NETP")
      auth.goToAuthorityWizard()
      auth.loginAsIntermediary("IN9001234567", "IM9001144777", "payments")

      Then("the user is redirected to the payments service")
      dashboard.checkExternalServiceUrl("payments")
    }

    Scenario(
      "Intermediary has no outstanding payments for a NETP"
    ) {

      Given("the user accesses payments in the IOSS Returns Service on behalf of a NETP")
      auth.goToAuthorityWizard()
      auth.loginAsIntermediary("IN9001234567", "IM9001144773", "payments")

      When("the user is on the IM9001144773/outstanding-payments page")
      dashboard.checkJourneyUrl("IM9001144773/outstanding-payments")

      Then("the user is shown that the intermediary does not owe any VAT")
      payment.noVatOwed()
    }

    Scenario("Intermediary cannot access payments for a client not registered to them") {

      Given("the user accesses payments in the IOSS Returns Service on behalf of a NETP who is not registered to them")
      auth.goToAuthorityWizard()
      auth.loginAsIntermediary("IN9001234568", "IM9001144771", "payments")

      Then("the user is on the cannot-use-not-registered page")
      dashboard.checkJourneyUrl("cannot-use-not-registered")
    }

    Scenario("Intermediary views previous returns from the same year") {

      Given("the user accesses submitted returns in the IOSS Returns Service on behalf of a NETP")
      auth.goToAuthorityWizard()
      auth.loginAsIntermediary("IN9001234567", "IM9001144771", "submitted-returns")

      Then("the user is on the past-returns page")
      dashboard.checkJourneyUrl("IM9001144771/past-returns")

      And("the correct submitted returns caption is displayed")
      intermediary.checkSubmittedReturnsCaption("UK with VRN")

      And("the user clicks the Show all sections accordion")
      pastReturn.showAllAccordion()

      When("the user clicks on the January 2025 link")
      pastReturn.selectPastReturnLink("past-returns\\/2025-M1")

      Then("the user is shown the correct previously submitted return")
      dashboard.checkJourneyUrl("IM9001144771/past-returns/2025-M1")

      And("the correct submitted returns caption is displayed")
      intermediary.checkSubmittedReturnsCaption("UK with VRN")

      And("the return for January 2025 is displayed to the user")
      pastReturn.returnForMonth("January 2025")

      And("the correct sections are displayed on the previous return with no corrections")
      pastReturn.returnWithNoCorrections()

      When("the user clicks back on the browser")
      dashboard.clickBackButton()

      Then("the user is on the past-returns page")
      dashboard.checkJourneyUrl("IM9001144771/past-returns")

      And("the user clicks the Pay Now link for February 2025")
      dashboard.cssLink("make-payment\\/2025-M2")

      And("the user has been redirected to the payments service")
      dashboard.checkExternalServiceUrl("payments")
    }

    Scenario("Intermediary views previous returns from multiple years including a nil return") {

      Given("the user accesses submitted returns in the IOSS Returns Service on behalf of a NETP")
      auth.goToAuthorityWizard()
      auth.loginAsIntermediary("IN9001001001", "IM9001001001", "submitted-returns")

      Then("the user is on the past-returns page")
      dashboard.checkJourneyUrl("IM9001001001/past-returns")

      And("the correct submitted returns caption is displayed")
      intermediary.checkSubmittedReturnsCaption("UK with NINO")

      And("the user clicks the Show all sections accordion")
      pastReturn.showAllAccordion()

      When("the user clicks on the December 2024 link")
      pastReturn.selectPastReturnLink("past-returns\\/2024-M12")

      Then("the user is shown the correct previously submitted return")
      dashboard.checkJourneyUrl("IM9001001001/past-returns/2024-M12")

      And("the correct submitted returns caption is displayed")
      intermediary.checkSubmittedReturnsCaption("UK with NINO")

      And("the return for December 2024 is displayed to the user")
      pastReturn.returnForMonth("December 2024")

      And("the correct sections are displayed for a nil return")
      pastReturn.nilReturn()

      When("the user clicks back on the browser")
      dashboard.clickBackButton()

      Then("the user is on the past-returns page")
      dashboard.checkJourneyUrl("IM9001001001/past-returns")

      And("the user clicks the Pay Now link for January 2025")
      dashboard.cssLink("make-payment\\/2025-M1")

      And("the user has been redirected to the payments service")
      dashboard.checkExternalServiceUrl("payments")
    }

    Scenario("Intermediary cannot access previous returns for a client not registered to them") {

      Given(
        "the user accesses submitted returns in the IOSS Returns Service on behalf of a NETP who is not registered to them"
      )
      auth.goToAuthorityWizard()
      auth.loginAsIntermediary("IN9001001001", "IM9001144771", "submitted-returns")

      Then("the user is on the cannot-use-not-registered page")
      dashboard.checkJourneyUrl("cannot-use-not-registered")
    }

    Scenario("Intermediary views previous returns for excluded NETP") {

      Given("the user accesses submitted returns in the IOSS Returns Service on behalf of a NETP who is excluded")
      auth.goToAuthorityWizard()
      auth.loginAsIntermediary("IN9008888886", "IM9001144884", "submitted-returns")

      Then("the user is on the past-returns page")
      dashboard.checkJourneyUrl("IM9001144884/past-returns")

      And("the user clicks the Show all sections accordion")
      pastReturn.showAllAccordion()

      When("the user clicks on the January 2025 link")
      pastReturn.selectPastReturnLink("past-returns\\/2025-M1")

      Then("the user is shown the correct previously submitted return")
      dashboard.checkJourneyUrl("IM9001144884/past-returns/2025-M1")

      And("the return for January 2025 is displayed to the user")
      pastReturn.returnForMonth("January 2025")

      And("the correct sections are displayed on the previous return with no corrections")
      pastReturn.returnWithNoCorrections()

      When("the user clicks back on the browser")
      dashboard.clickBackButton()

      Then("the user is on the past-returns page")
      dashboard.checkJourneyUrl("IM9001144884/past-returns")

      And("the user clicks the Pay Now link for February 2025")
      dashboard.cssLink("make-payment\\/2025-M2")

      And("the user has been redirected to the payments service")
      dashboard.checkExternalServiceUrl("payments")
    }

    Scenario("A NETP who has transferred from another member state has a partial first return") {

      Given("the user accesses the IOSS Returns Service on behalf of a NETP")
      auth.goToAuthorityWizard()
      auth.loginAsIntermediary("IN9005999997", "IM9005555551", "returns")

      Then("the NETP transferring from another MSID is offered a partial return for the correct period")
      dashboard.checkJourneyUrl("IM9005555551/2024-M1/start-return")
      transferringMsid.transferringMsidText("netp", "from", "offered", "partial")
      dashboard.answerRadioButton("yes")

      And("the user answers no on the want-to-upload-file page")
      dashboard.checkJourneyUrl("IM9005555551/want-to-upload-file")
      fileUpload.selectFileUpload("No, enter them myself")

      And("the user answers no on the sold-goods page")
      dashboard.checkJourneyUrl("IM9005555551/sold-goods")
      dashboard.answerRadioButton("no")

      And("the netp transferring from another MSID is submitting a partial return for the correct period")
      dashboard.checkJourneyUrl("IM9005555551/check-your-answers")
      transferringMsid.transferringMsidText("netp", "from", "submitting", "partial")

      And("the user submits their return successfully via the check-your-answers page")
      dashboard.submit()
      dashboard.checkJourneyUrl("IM9005555551/return-successfully-submitted")
    }

    Scenario("A NETP who is transferring to another member state has a partial final return") {

      Given("the user accesses the IOSS Returns Service on behalf of a NETP")
      auth.goToAuthorityWizard()
      auth.loginAsIntermediary("IN9005999997", "IM9005555552", "returns")

      Then("the netp transferring to another MSID is offered a partial return for the correct period")
      dashboard.checkJourneyUrl("IM9005555552/2024-M2/start-return")
      exclusion.finalReturnHeading(display = true, "NETP")
      transferringMsid.transferringMsidText("netp", "to", "offered", "partial")
      dashboard.answerRadioButton("yes")

      And("the user answers no on the want-to-upload-file page")
      dashboard.checkJourneyUrl("IM9005555552/want-to-upload-file")
      fileUpload.selectFileUpload("No, enter them myself")

      And("the user answers no on the sold-goods page")
      dashboard.checkJourneyUrl("IM9005555552/sold-goods")
      dashboard.answerRadioButton("no")

      And("the user answers no on the correct-previous-return page")
      dashboard.checkJourneyUrl("IM9005555552/correct-previous-return")
      exclusion.lastChanceForCorrection(display = true)
      dashboard.answerRadioButton("no")

      And("the user is shown the corrections warning before submission")
      dashboard.checkJourneyUrl("IM9005555552/check-your-answers")
      exclusion.correctionsWarningSubmission(display = true)

      And("the netp transferring to another MSID is submitting a partial return for the correct period")
      transferringMsid.transferringMsidText("netp", "to", "submitting", "partial")

      And("the user submits their return successfully via the check-your-answers page")
      dashboard.submit()
      dashboard.checkJourneyUrl("IM9005555552/return-successfully-submitted")
    }

    Scenario("Correct company information displayed on a return for UK based NETP with VRN") {

      Given("the user accesses the IOSS Returns Service on behalf of a NETP")
      auth.goToAuthorityWizard()
      auth.loginAsIntermediary("IN9001234567", "IM9001144771", "returns")

      And("the correct start return heading is displayed for a UK with VRN client")
      dashboard.checkJourneyUrl("IM9001144771/2025-M3/start-return")
      intermediary.checkReturnStartHeading("UK with VRN")

      And("the user answers yes on the IM9001144771/2025-M3/start-return page")
      dashboard.answerRadioButton("yes")

      Then("the user answers no on the IM9001144771/want-to-upload-file page")
      dashboard.checkJourneyUrl("IM9001144771/want-to-upload-file")
      fileUpload.selectFileUpload("No, enter them myself")

      And("the correct caption is displayed for a UK with VRN client")
      dashboard.checkJourneyUrl("IM9001144771/sold-goods")
      intermediary.checkCaption("UK with VRN")

      And("the user answers no on the sold-goods page")
      dashboard.answerRadioButton("no")

      And("the user answers no on the IM9001144771/correct-previous-return page")
      dashboard.checkJourneyUrl("IM9001144771/correct-previous-return")
      dashboard.answerRadioButton("no")

      And("the correct details are displayed at the top of check-your-answers for a UK with VRN client")
      dashboard.checkJourneyUrl("IM9001144771/check-your-answers")
      intermediary.checkCYA("UK with VRN")

      And("the user submits their return successfully via the check-your-answers page")
      dashboard.submit()
      dashboard.checkJourneyUrl("IM9001144771/return-successfully-submitted")
    }

    Scenario("Correct company information displayed on a return for UK based NETP with UTR") {

      Given("the user accesses the IOSS Returns Service on behalf of a NETP")
      auth.goToAuthorityWizard()
      auth.loginAsIntermediary("IN9001234567", "IM9001144773", "returns")

      And("the correct start return heading is displayed for a UK with UTR client")
      dashboard.checkJourneyUrl("IM9001144773/2025-M1/start-return")
      intermediary.checkReturnStartHeading("UK with UTR")

      And("the user answers yes on the IM9001144773/2025-M1/start-return page")
      dashboard.answerRadioButton("yes")

      Then("the user answers no on the IM9001144773/want-to-upload-file page")
      dashboard.checkJourneyUrl("IM9001144773/want-to-upload-file")
      fileUpload.selectFileUpload("No, enter them myself")

      And("the correct caption is displayed for a UK with UTR client")
      dashboard.checkJourneyUrl("IM9001144773/sold-goods")
      intermediary.checkCaption("UK with UTR")

      And("the user answers no on the sold-goods page")
      dashboard.answerRadioButton("no")

      And("the correct details are displayed at the top of check-your-answers for a UK with UTR client")
      dashboard.checkJourneyUrl("IM9001144773/check-your-answers")
      intermediary.checkCYA("UK with UTR")

      And("the user submits their return successfully via the check-your-answers page")
      dashboard.submit()
      dashboard.checkJourneyUrl("IM9001144773/return-successfully-submitted")
    }

    Scenario("Correct company information displayed on a return for UK based NETP with NINO") {

      Given("the user accesses the IOSS Returns Service on behalf of a NETP")
      auth.goToAuthorityWizard()
      auth.loginAsIntermediary("IN9001234567", "IM9001144778", "returns")

      And("the correct start return heading is displayed for a UK with NINO client")
      dashboard.checkJourneyUrl("IM9001144778/2025-M1/start-return")
      intermediary.checkReturnStartHeading("UK with NINO")

      And("the user answers yes on the IM9001144778/2025-M1/start-return page")
      dashboard.answerRadioButton("yes")

      Then("the user answers no on the IM9001144778/want-to-upload-file page")
      dashboard.checkJourneyUrl("IM9001144778/want-to-upload-file")
      fileUpload.selectFileUpload("No, enter them myself")

      And("the correct caption is displayed for a UK with NINO client")
      dashboard.checkJourneyUrl("IM9001144778/sold-goods")
      intermediary.checkCaption("UK with NINO")

      And("the user answers no on the sold-goods page")
      dashboard.answerRadioButton("no")

      And("the correct details are displayed at the top of check-your-answers for a UK with NINO client")
      dashboard.checkJourneyUrl("IM9001144778/check-your-answers")
      intermediary.checkCYA("UK with NINO")

      And("the user submits their return successfully via the check-your-answers page")
      dashboard.submit()
      dashboard.checkJourneyUrl("IM9001144778/return-successfully-submitted")
    }

    Scenario("Correct company information displayed on a return for Non-UK based NETP with VRN") {

      Given("the user accesses the IOSS Returns Service on behalf of a NETP")
      auth.goToAuthorityWizard()
      auth.loginAsIntermediary("IN9001234567", "IM9001144775", "returns")

      And("the correct start return heading is displayed for a Non-UK with VRN client")
      dashboard.checkJourneyUrl("IM9001144775/2025-M1/start-return")
      intermediary.checkReturnStartHeading("Non-UK with VRN")

      And("the user answers yes on the IM9001144775/2025-M1/start-return page")
      dashboard.answerRadioButton("yes")

      Then("the user answers no on the IM9001144775/want-to-upload-file page")
      dashboard.checkJourneyUrl("IM9001144775/want-to-upload-file")
      fileUpload.selectFileUpload("No, enter them myself")

      And("the correct caption is displayed for a Non-UK with VRN client")
      dashboard.checkJourneyUrl("IM9001144775/sold-goods")
      intermediary.checkCaption("Non-UK with VRN")

      And("the user answers no on the sold-goods page")
      dashboard.answerRadioButton("no")

      And("the correct details are displayed at the top of check-your-answers for a Non-UK with VRN client")
      dashboard.checkJourneyUrl("IM9001144775/check-your-answers")
      intermediary.checkCYA("Non-UK with VRN")

      And("the user submits their return successfully via the check-your-answers page")
      dashboard.submit()
      dashboard.checkJourneyUrl("IM9001144775/return-successfully-submitted")
    }

    Scenario("Correct company information displayed on a return for Non-UK based NETP with FTR") {

      Given("the user accesses the IOSS Returns Service on behalf of a NETP")
      auth.goToAuthorityWizard()
      auth.loginAsIntermediary("IN9001234567", "IM9001144777", "returns")

      And("the correct start return heading is displayed for a Non-UK with FTR client")
      dashboard.checkJourneyUrl("IM9001144777/2025-M3/start-return")
      intermediary.checkReturnStartHeading("Non-UK with FTR")

      And("the user answers yes on the IM9001144777/2025-M3/start-return page")
      dashboard.answerRadioButton("yes")

      Then("the user answers no on the IM9001144777/want-to-upload-file page")
      dashboard.checkJourneyUrl("IM9001144777/want-to-upload-file")
      fileUpload.selectFileUpload("No, enter them myself")

      And("the correct caption is displayed for a UK with FTR client")
      dashboard.checkJourneyUrl("IM9001144777/sold-goods")
      intermediary.checkCaption("Non-UK with FTR")

      And("the user answers no on the sold-goods page")
      dashboard.answerRadioButton("no")

      And("the user answers no on the IM9001144777/correct-previous-return page")
      dashboard.checkJourneyUrl("IM9001144777/correct-previous-return")
      dashboard.answerRadioButton("no")

      And("the correct details are displayed at the top of check-your-answers for a Non-UK with FTR client")
      dashboard.checkJourneyUrl("IM9001144777/check-your-answers")
      intermediary.checkCYA("Non-UK with FTR")

      And("the user submits their return successfully via the check-your-answers page")
      dashboard.submit()
      dashboard.checkJourneyUrl("IM9001144777/return-successfully-submitted")
    }

    Scenario(
      "Intermediary can view submitted returns from a previous registration as well as their current registration"
    ) {

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()

      When("the user accesses submitted returns within the IOSS Returns Service on behalf of a NETP")
      auth.goToAuthorityWizard()
      auth.loginAsIntermediary("IN9002230002", "IM9001144667", "submitted-returns")

      Then("the user is on the past-returns page")
      dashboard.checkJourneyUrl("IM9001144667/past-returns")

      And("the correct submitted returns caption is displayed for IM9001144667")
      intermediary.checkSubmittedReturnsCaption("oldest registration client")

      And("the user clicks the Show all sections accordion")
      pastReturn.showAllAccordion()

      When("the user clicks on the first return month for IM9001144667")
      pastReturn.selectPastReturnLink(
        s"IM9001144667\\/past-returns\\/${MultipleIOSS.periodForMultipleRegistrations(9)}"
      )
      dashboard.checkJourneyUrl(s"IM9001144667/past-returns/${MultipleIOSS.periodForMultipleRegistrations(9)}")

      And("the correct submitted returns caption is displayed for IM9001144667")
      intermediary.checkSubmittedReturnsCaption("oldest registration client")

      And("the correct returns and payments references are shown for IM9001144667")
      pastReturn.returnsAndPaymentReferences("IM9001144667")

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()

      When("the user accesses submitted returns within the IOSS Returns Service on behalf of a NETP")
      auth.goToAuthorityWizard()
      auth.loginAsIntermediary("IN9002230002", "IM9001144669", "submitted-returns")

      Then("the user is on the past-returns page")
      dashboard.checkJourneyUrl("IM9001144669/past-returns")

      And("the correct submitted returns caption is displayed for IM9001144669")
      intermediary.checkSubmittedReturnsCaption("middle registration client")

      And("the user clicks the Show all sections accordion")
      pastReturn.showAllAccordion()

      When("the user clicks on the first return month for IM9001144669")
      pastReturn.selectPastReturnLink(
        s"IM9001144669\\/past-returns\\/${MultipleIOSS.periodForMultipleRegistrations(6)}"
      )
      dashboard.checkJourneyUrl(s"IM9001144669/past-returns/${MultipleIOSS.periodForMultipleRegistrations(6)}")

      And("the correct submitted returns caption is displayed for IM9001144669")
      intermediary.checkSubmittedReturnsCaption("middle registration client")

      And("the correct returns and payments references are shown for IM9001144669")
      pastReturn.returnsAndPaymentReferences("IM9001144669")

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()

      When("the user accesses submitted returns within the IOSS Returns Service on behalf of a NETP")
      auth.goToAuthorityWizard()
      auth.loginAsIntermediary("IN9002230002", "IM9001144671", "submitted-returns")

      Then("the user is on the past-returns page")
      dashboard.checkJourneyUrl("IM9001144671/past-returns")

      And("the correct submitted returns caption is displayed for IM9001144671")
      intermediary.checkSubmittedReturnsCaption("current registration client")

      And("the user clicks the Show all sections accordion")
      pastReturn.showAllAccordion()

      When("the user clicks on the first return month for IM9001144671")
      pastReturn.selectPastReturnLink(
        s"IM9001144671\\/past-returns\\/${MultipleIOSS.periodForMultipleRegistrations(3)}"
      )
      dashboard.checkJourneyUrl(s"IM9001144671/past-returns/${MultipleIOSS.periodForMultipleRegistrations(3)}")

      And("the correct submitted returns caption is displayed for IM9001144671")
      intermediary.checkSubmittedReturnsCaption("current registration client")

      And("the correct returns and payments references are shown for IM9001144671")
      pastReturn.returnsAndPaymentReferences("IM9001144671")
    }

    Scenario("Intermediary has excluded NETP with only open returns over 3 years overdue") {

      Given("the user accesses the IOSS Returns Service on behalf of a NETP")
      auth.goToAuthorityWizard()
      auth.loginAsIntermediary("IN9004004004", "IM9004004004", "returns")

      Then("the user is on the IM9004004004/no-returns-due page")
      dashboard.checkJourneyUrl("IM9004004004/no-returns-due")
    }

    Scenario("Intermediary has excluded NETP with only open returns that are both over and under 3 years overdue") {

      Given("the user accesses the IOSS Returns Service on behalf of a NETP")
      auth.goToAuthorityWizard()
      auth.loginAsIntermediary("IN9005005005", "IM9005005005", "returns")

      Then("the oldest available return period for IM9005005005 is shown to the Intermediary")
      intermediary.checkOldestReturnUrl("IM9005005005")

      And("the hint text showing the current number of overdue returns is displayed")
      intermediary.checkOverdueHintText()

      And("the user answers yes on the start-return page")
      dashboard.answerRadioButton("yes")

      Then("the user answers no on the IM9005005005/want-to-upload-file page")
      dashboard.checkJourneyUrl("IM9005005005/want-to-upload-file")
      fileUpload.selectFileUpload("No, enter them myself")

      And("the user answers no on the sold-goods page")
      dashboard.checkJourneyUrl("IM9005005005/sold-goods")
      dashboard.answerRadioButton("no")

      And("the user submits their return successfully via the check-your-answers page")
      dashboard.checkJourneyUrl("IM9005005005/check-your-answers")
      dashboard.submit()
      dashboard.checkJourneyUrl("IM9005005005/return-successfully-submitted")
    }

    Scenario("Intermediary cannot start a return that is over 3 years overdue") {

      Given("the user accesses the IOSS Returns Service on behalf of a NETP")
      auth.goToAuthorityWizard()
      auth.loginAsIntermediary("IN9005005005", "IM9005005005", "returns")

      And("the oldest available return period for IM9005005005 is shown to the Intermediary")
      intermediary.checkOldestReturnUrl("IM9005005005")

      When("the user navigates to a return more than three years overdue")
      dashboard.goToPage(s"IM9005005005/${Intermediary.returnOverThreeYearsOverdue()}/start-return")

      Then("the user is on the IM9005005005/cannot-start-excluded-return page")
      dashboard.checkJourneyUrl("IM9005005005/cannot-start-excluded-return")
    }

    Scenario("User with both Intermediary and global IOSS enrolments can access a return for a NETP") {

      Given(
        "the user with both intermediary and global IOSS enrolments accesses the IOSS Returns Service on behalf of a NETP"
      )
      auth.goToAuthorityWizard()
      auth.loginAsIntermediary("IN9001234567", "IM9001144771", "doubleEnrolmentNetpReturns")

      Then("the user is able to progress a return for a NETP")
      dashboard.checkJourneyUrl("IM9001144771/2025-M3/start-return")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001144771/want-to-upload-file")
      fileUpload.selectFileUpload("No, enter them myself")
      dashboard.checkJourneyUrl("IM9001144771/sold-goods")
      dashboard.answerRadioButton("no")
    }

    Scenario("User with both Intermediary and global IOSS enrolments can access a global IOSS return") {

      Given(
        "the user with both intermediary and global IOSS enrolments accesses the IOSS Returns Service to start a return for their own IOSS account"
      )
      auth.goToAuthorityWizard()
      auth.loginAsIntermediary("IN9001234567", "NotApplicable", "doubleEnrolmentGlobalReturns")

      And("the user is on the select-account page")
      dashboard.checkJourneyUrl("select-account")

      When("the user selects the dashboard for number IM9001234567")
      dashboard.clickLink("IM9001234567")
      dashboard.continue()

      Then("the user is redirected to their IOSS account")
      intermediary.dashboardUrlCheck("global")

      Then("the user is able to progress their IOSS return")
      dashboard.clickLink("start-your-return")
      dashboard.checkJourneyUrl("IM9001234567/2023-M12/start-return")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234567/want-to-upload-file")
      fileUpload.selectFileUpload("No, enter them myself")
      dashboard.checkJourneyUrl("IM9001234567/sold-goods")
      dashboard.answerRadioButton("yes")
    }
  }
}
