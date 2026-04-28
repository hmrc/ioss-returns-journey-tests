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

  private val dashboard = Dashboard
  private val auth = Auth
  private val fileUpload = FileUpload
  private val intermediary = Intermediary
  private val correction = Correction

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
  }
}
