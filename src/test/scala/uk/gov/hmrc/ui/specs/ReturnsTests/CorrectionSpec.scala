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

package uk.gov.hmrc.ui.specs.ReturnsTests

import uk.gov.hmrc.ui.pages._
import uk.gov.hmrc.ui.specs.BaseSpec

import java.time.LocalDate

class CorrectionSpec extends BaseSpec {

  private val dashboard  = Dashboard
  private val auth       = Auth
  private val fileUpload = FileUpload
  private val correction = Correction

  private val lastYear    = LocalDate.now().minusYears(1).getYear
  private val twoYearsAgo = LocalDate.now().minusYears(2).getYear

  Feature("Corrections journeys") {

    Scenario("A simple corrections journey with previously undeclared countries added to a nil return") {

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "IM9008888888", "Organisation", "hasIOSSEnrolment", "dashboard")
      dashboard.checkJourneyUrl("your-account")

      When("the user clicks on the 'Start your return' link")
      dashboard.clickLink("start-your-return")

      Then("the user answers yes on the start page")
      dashboard.checkJourneyUrl("IM9008888888/2023-M12/start-return")
      dashboard.answerRadioButton("yes")

      And("the user answers no on the want-to-upload-file page")
      dashboard.checkJourneyUrl("IM9008888888/want-to-upload-file")
      fileUpload.selectFileUpload("No, enter them myself")

      And("the user answers no on the sold-goods page")
      dashboard.checkJourneyUrl("IM9008888888/sold-goods")
      dashboard.answerRadioButton("no")

      And("the user answers yes on the correct-previous-return page")
      dashboard.checkJourneyUrl("IM9008888888/correct-previous-return")
      dashboard.answerRadioButton("yes")

      And("the user answers yes on the correction-return-single-month page")
      dashboard.checkJourneyUrl("IM9008888888/correction-return-single-month/1")
      dashboard.answerRadioButton("yes")

      And("the user selects a country")
      dashboard.checkJourneyUrl("IM9008888888/correction-country/1/1")
      dashboard.selectCountry("Estonia")

      And("the user answers yes on the add-new-country page")
      dashboard.checkJourneyUrl("IM9008888888/add-new-country/1/1")
      dashboard.answerRadioButton("yes")

      And("the country is not showing the previously declared text")
      dashboard.checkJourneyUrl("IM9008888888/country-vat-correction-amount/1/1")
      correction.previouslyDeclaredText("trader", display = false)

      And("the user enters the correction amount")
      dashboard.enterAnswer("1500")

      And("the user answers yes on the vat-payable-confirm page")
      dashboard.checkJourneyUrl("IM9008888888/vat-payable-confirm/1/1")
      dashboard.answerRadioButton("yes")

      And("the user clicks continue on the vat-payable-check page")
      dashboard.checkJourneyUrl("IM9008888888/vat-payable-check/1/1")
      dashboard.continue()

      And("the user answers yes on the correction-list-countries page")
      dashboard.checkJourneyUrl("IM9008888888/correction-list-countries/1")
      dashboard.answerRadioButton("yes")

      And("the user selects a country")
      dashboard.checkJourneyUrl("IM9008888888/correction-country/1/2")
      dashboard.selectCountry("Portugal")

      And("the user answers yes on the add-new-country page")
      dashboard.checkJourneyUrl("IM9008888888/add-new-country/1/2")
      dashboard.answerRadioButton("yes")

      And("the country is not showing the previously declared text")
      dashboard.checkJourneyUrl("IM9008888888/country-vat-correction-amount/1/2")
      correction.previouslyDeclaredText("trader", display = false)

      And("the user enters the correction amount")
      dashboard.enterAnswer("160.36")

      And("the user answers yes on the vat-payable-confirm page")
      dashboard.checkJourneyUrl("IM9008888888/vat-payable-confirm/1/2")
      dashboard.answerRadioButton("yes")

      And("the user clicks continue on the vat-payable-check page")
      dashboard.checkJourneyUrl("IM9008888888/vat-payable-check/1/2")
      dashboard.continue()

      And("the user answers no on the correction-list-countries page")
      dashboard.checkJourneyUrl("IM9008888888/correction-list-countries/1")
      dashboard.answerRadioButton("no")

      And("the user clicks continue on the vat-correction-months page")
      dashboard.checkJourneyUrl("IM9008888888/2023-M12/vat-correction-months")
      dashboard.continue()

      And("the no payments due for minus corrections text is not displayed")
      dashboard.checkJourneyUrl("IM9008888888/check-your-answers")
      correction.noPaymentsDueForCorrectionsText(displayed = false)

      And("the user submits their return successfully via the check-your-answers page")
      dashboard.submit()
      dashboard.checkJourneyUrl("IM9008888888/return-successfully-submitted")
    }

    Scenario("A corrections journey with previously declared countries") {

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "IM9001234567", "Organisation", "hasIOSSEnrolment", "dashboard")
      dashboard.checkJourneyUrl("your-account")

      When("the user clicks on the 'Start your return' link")
      dashboard.clickLink("start-your-return")

      Then("the user answers yes on the start page")
      dashboard.checkJourneyUrl("IM9001234567/2023-M12/start-return")
      dashboard.answerRadioButton("yes")

      And("the user answers no on the want-to-upload-file page")
      dashboard.checkJourneyUrl("IM9001234567/want-to-upload-file")
      fileUpload.selectFileUpload("No, enter them myself")

      And("the user answers no on the sold-goods page")
      dashboard.checkJourneyUrl("IM9001234567/sold-goods")
      dashboard.answerRadioButton("no")

      And("the user answers yes on the correct-previous-return page")
      dashboard.checkJourneyUrl("IM9001234567/correct-previous-return")
      dashboard.answerRadioButton("yes")

      And("the user picks 2023 on the correction-return-year page")
      dashboard.checkJourneyUrl("IM9001234567/correction-return-year/1")
      dashboard.clickLink("value_2023")
      dashboard.continue()

      And("the user picks October on the correction-return-month page")
      dashboard.checkJourneyUrl("IM9001234567/correction-return-month/1")
      dashboard.clickLink("value_October")
      dashboard.continue()

      And("the user selects a country")
      dashboard.checkJourneyUrl("IM9001234567/correction-country/1/1")
      dashboard.selectCountry("Germany")

      And("the country is showing the previously declared text")
      dashboard.checkJourneyUrl("IM9001234567/country-vat-correction-amount/1/1")
      correction.previouslyDeclaredText("trader", display = true)

      And("the user enters the correction amount")
      dashboard.enterAnswer("-100.65")

      And("the user answers yes on the vat-payable-confirm page")
      dashboard.checkJourneyUrl("IM9001234567/vat-payable-confirm/1/1")
      dashboard.answerRadioButton("yes")

      And("the user clicks continue on the vat-payable-check page")
      dashboard.checkJourneyUrl("IM9001234567/vat-payable-check/1/1")
      dashboard.continue()

      And("the user answers yes on the correction-list-countries page")
      dashboard.checkJourneyUrl("IM9001234567/correction-list-countries/1")
      dashboard.answerRadioButton("yes")

      And("the user selects a country")
      dashboard.checkJourneyUrl("IM9001234567/correction-country/1/2")
      dashboard.selectCountry("France")

      And("the country is showing the previously declared text")
      dashboard.checkJourneyUrl("IM9001234567/country-vat-correction-amount/1/2")
      correction.previouslyDeclaredText("trader", display = true)

      And("the user enters the correction amount")
      dashboard.enterAnswer("1453")

      And("the user answers yes on the vat-payable-confirm page")
      dashboard.checkJourneyUrl("IM9001234567/vat-payable-confirm/1/2")
      dashboard.answerRadioButton("yes")

      And("the user clicks continue on the vat-payable-check page")
      dashboard.checkJourneyUrl("IM9001234567/vat-payable-check/1/2")
      dashboard.continue()

      And("the user answers no on the correction-list-countries page")
      dashboard.checkJourneyUrl("IM9001234567/correction-list-countries/1")
      dashboard.answerRadioButton("no")

      And("the user clicks yes on the vat-correction-months-add page")
      dashboard.checkJourneyUrl("IM9001234567/2023-M12/vat-correction-months-add")
      dashboard.answerRadioButton("yes")

      And("the user picks 2023 on the correction-return-year page")
      dashboard.checkJourneyUrl("IM9001234567/correction-return-year/2")
      dashboard.clickLink("value_2023")
      dashboard.continue()

      And("the user picks November on the correction-return-month page")
      dashboard.checkJourneyUrl("IM9001234567/correction-return-month/2")
      dashboard.clickLink("value_November")
      dashboard.continue()

      And("the user selects a country")
      dashboard.checkJourneyUrl("IM9001234567/correction-country/2/1")
      dashboard.selectCountry("France")

      And("the user enters the correction amount")
      dashboard.checkJourneyUrl("IM9001234567/country-vat-correction-amount/2/1")
      dashboard.enterAnswer("1500")

      And("the user answers yes on the vat-payable-confirm page")
      dashboard.checkJourneyUrl("IM9001234567/vat-payable-confirm/2/1")
      dashboard.answerRadioButton("yes")

      And("the user clicks continue on the vat-payable-check page")
      dashboard.checkJourneyUrl("IM9001234567/vat-payable-check/2/1")
      dashboard.continue()

      And("the user answers yes on the correction-list-countries page")
      dashboard.checkJourneyUrl("IM9001234567/correction-list-countries/2")
      dashboard.answerRadioButton("yes")

      And("the user selects a country")
      dashboard.checkJourneyUrl("IM9001234567/correction-country/2/2")
      dashboard.selectCountry("Germany")

      And("the user enters the correction amount")
      dashboard.checkJourneyUrl("IM9001234567/country-vat-correction-amount/2/2")
      dashboard.enterAnswer("160.36")

      And("the user answers yes on the vat-payable-confirm page")
      dashboard.checkJourneyUrl("IM9001234567/vat-payable-confirm/2/2")
      dashboard.answerRadioButton("yes")

      And("the user clicks continue on the vat-payable-check page")
      dashboard.checkJourneyUrl("IM9001234567/vat-payable-check/2/2")
      dashboard.continue()

      And("the user answers no on the correction-list-countries page")
      dashboard.checkJourneyUrl("IM9001234567/correction-list-countries/2")
      dashboard.answerRadioButton("no")

      And("the user clicks continue on the vat-correction-months page")
      dashboard.checkJourneyUrl("IM9001234567/2023-M12/vat-correction-months")
      dashboard.continue()

      And("the no payments due for minus corrections text is not displayed")
      dashboard.checkJourneyUrl("IM9001234567/check-your-answers")
      correction.noPaymentsDueForCorrectionsText(displayed = false)

      And("the user submits their return successfully via the check-your-answers page")
      dashboard.submit()
      dashboard.checkJourneyUrl("IM9001234567/return-successfully-submitted")
    }

    Scenario("A corrections journey with previously declared countries that have been corrected multiple times") {

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "IM9001233211", "Organisation", "hasIOSSEnrolment", "dashboard")
      dashboard.checkJourneyUrl("your-account")

      When("the user clicks on the 'Start your return' link")
      dashboard.clickLink("start-your-return")

      Then("the user answers yes on the start page")
      dashboard.checkJourneyUrl("IM9001233211/2024-M1/start-return")
      dashboard.answerRadioButton("yes")

      And("the user answers no on the want-to-upload-file page")
      dashboard.checkJourneyUrl("IM9001233211/want-to-upload-file")
      fileUpload.selectFileUpload("No, enter them myself")

      And("the user answers no on the sold-goods page")
      dashboard.checkJourneyUrl("IM9001233211/sold-goods")
      dashboard.answerRadioButton("no")

      And("the user answers yes on the correct-previous-return page")
      dashboard.checkJourneyUrl("IM9001233211/correct-previous-return")
      dashboard.answerRadioButton("yes")

      And("the user picks 2023 on the correction-return-year page")
      dashboard.checkJourneyUrl("IM9001233211/correction-return-year/1")
      dashboard.clickLink("value_2023")
      dashboard.continue()

      And("the user picks October on the correction-return-month page")
      dashboard.checkJourneyUrl("IM9001233211/correction-return-month/1")
      dashboard.clickLink("value_October")
      dashboard.continue()

      And("the user selects a country")
      dashboard.checkJourneyUrl("IM9001233211/correction-country/1/1")
      dashboard.selectCountry("Germany")

      And("the country is showing the previously declared text")
      dashboard.checkJourneyUrl("IM9001233211/country-vat-correction-amount/1/1")
      correction.previouslyDeclaredText("trader", display = true)

      And("the user enters the correction amount")
      dashboard.enterAnswer("-2500")

      And("the user answers yes on the vat-payable-confirm page")
      dashboard.checkJourneyUrl("IM9001233211/vat-payable-confirm/1/1")
      dashboard.answerRadioButton("yes")

      And("the user clicks continue on the vat-payable-check page")
      dashboard.checkJourneyUrl("IM9001233211/vat-payable-check/1/1")
      dashboard.continue()

      And("the user answers yes on the correction-list-countries page")
      dashboard.checkJourneyUrl("IM9001233211/correction-list-countries/1")
      dashboard.answerRadioButton("yes")

      And("the user selects a country")
      dashboard.checkJourneyUrl("IM9001233211/correction-country/1/2")
      dashboard.selectCountry("France")

      And("the country is showing the previously declared text")
      dashboard.checkJourneyUrl("IM9001233211/country-vat-correction-amount/1/2")
      correction.previouslyDeclaredText("trader", display = true)

      And("the user enters the correction amount")
      dashboard.enterAnswer("2600")

      And("the user answers yes on the vat-payable-confirm page")
      dashboard.checkJourneyUrl("IM9001233211/vat-payable-confirm/1/2")
      dashboard.answerRadioButton("yes")

      And("the user clicks continue on the vat-payable-check page")
      dashboard.checkJourneyUrl("IM9001233211/vat-payable-check/1/2")
      dashboard.continue()

      And("the user answers no on the correction-list-countries page")
      dashboard.checkJourneyUrl("IM9001233211/correction-list-countries/1")
      dashboard.answerRadioButton("no")

      And("the user clicks no on the vat-correction-months-add page")
      dashboard.checkJourneyUrl("IM9001233211/2024-M1/vat-correction-months-add")
      dashboard.answerRadioButton("no")

      And("the no payments due for minus corrections text is displayed")
      dashboard.checkJourneyUrl("IM9001233211/check-your-answers")
      correction.noPaymentsDueForCorrectionsText(displayed = true)

      And("the user submits their return successfully via the check-your-answers page")
      dashboard.submit()
      dashboard.checkJourneyUrl("IM9001233211/return-successfully-submitted")
    }

    Scenario("A corrections journey with full minus correction to a country that also has sales in the new return") {

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "IM9001233211", "Organisation", "hasIOSSEnrolment", "dashboard")
      dashboard.checkJourneyUrl("your-account")

      When("the user clicks on the 'Start your return' link")
      dashboard.clickLink("start-your-return")

      Then("the user answers yes on the start page")
      dashboard.checkJourneyUrl("IM9001233211/2024-M1/start-return")
      dashboard.answerRadioButton("yes")

      And("the user answers no on the want-to-upload-file page")
      dashboard.checkJourneyUrl("IM9001233211/want-to-upload-file")
      fileUpload.selectFileUpload("No, enter them myself")

      And("the user answers yes on the sold-goods page")
      dashboard.checkJourneyUrl("IM9001233211/sold-goods")
      dashboard.answerRadioButton("yes")

      And("the user selects a country on the sold-to-country page")
      dashboard.checkJourneyUrl("IM9001233211/sold-to-country/1")
      dashboard.selectCountry("Germany")

      And("the user selects the first checkbox on the vat-rates-from-country page")
      dashboard.checkJourneyUrl("IM9001233211/vat-rates-from-country/1")
      dashboard.tickCheckbox("first")
      dashboard.continue()

      And("the user enters the first country total sales for first VAT rate")
      dashboard.checkJourneyUrl("IM9001233211/sales-to-country/1/1")
      dashboard.enterAnswer("6000")

      And("the user confirms the suggested amount of VAT")
      dashboard.checkJourneyUrl("IM9001233211/vat-on-sales/1/1")
      dashboard.clickLink("choice")
      dashboard.continue()

      And("the user answers no on the check-sales page")
      dashboard.checkJourneyUrl("IM9001233211/check-sales/1")
      dashboard.answerRadioButton("no")

      And("the user answers no on the add-sales-country-list page")
      dashboard.checkJourneyUrl("IM9001233211/add-sales-country-list")
      dashboard.answerRadioButton("no")

      And("the user answers yes on the correct-previous-return page")
      dashboard.checkJourneyUrl("IM9001233211/correct-previous-return")
      dashboard.answerRadioButton("yes")

      And("the user picks 2023 on the correction-return-year page")
      dashboard.checkJourneyUrl("IM9001233211/correction-return-year/1")
      dashboard.clickLink("value_2023")
      dashboard.continue()

      And("the user picks October on the correction-return-month page")
      dashboard.checkJourneyUrl("IM9001233211/correction-return-month/1")
      dashboard.clickLink("value_October")
      dashboard.continue()

      And("the user selects a country")
      dashboard.checkJourneyUrl("IM9001233211/correction-country/1/1")
      dashboard.selectCountry("Germany")

      And("the country is showing the previously declared text")
      dashboard.checkJourneyUrl("IM9001233211/country-vat-correction-amount/1/1")
      correction.previouslyDeclaredText("trader", display = true)

      And("the user enters the correction amount")
      dashboard.enterAnswer("-3500")

      And("the user answers yes on the vat-payable-confirm page")
      dashboard.checkJourneyUrl("IM9001233211/vat-payable-confirm/1/1")
      dashboard.answerRadioButton("yes")

      And("the user clicks continue on the vat-payable-check page")
      dashboard.checkJourneyUrl("IM9001233211/vat-payable-check/1/1")
      dashboard.continue()

      And("the user answers no on the correction-list-countries page")
      dashboard.checkJourneyUrl("IM9001233211/correction-list-countries/1")
      dashboard.answerRadioButton("no")

      And("the user clicks no on the vat-correction-months-add page")
      dashboard.checkJourneyUrl("IM9001233211/2024-M1/vat-correction-months-add")
      dashboard.answerRadioButton("no")

      And("the no payments due for minus corrections text is displayed")
      dashboard.checkJourneyUrl("IM9001233211/check-your-answers")
      correction.noPaymentsDueForCorrectionsText(displayed = true)

      And("the user submits their return successfully via the check-your-answers page")
      dashboard.submit()
      dashboard.checkJourneyUrl("IM9001233211/return-successfully-submitted")
    }

    Scenario("A corrections journey with only minus corrections") {

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "IM9001234567", "Organisation", "hasIOSSEnrolment", "dashboard")
      dashboard.checkJourneyUrl("your-account")

      When("the user clicks on the 'Start your return' link")
      dashboard.clickLink("start-your-return")

      Then("the user answers yes on the start page")
      dashboard.checkJourneyUrl("IM9001234567/2023-M12/start-return")
      dashboard.answerRadioButton("yes")

      And("the user answers no on the want-to-upload-file page")
      dashboard.checkJourneyUrl("IM9001234567/want-to-upload-file")
      fileUpload.selectFileUpload("No, enter them myself")

      And("the user answers no on the sold-goods page")
      dashboard.checkJourneyUrl("IM9001234567/sold-goods")
      dashboard.answerRadioButton("no")

      And("the user answers yes on the correct-previous-return page")
      dashboard.checkJourneyUrl("IM9001234567/correct-previous-return")
      dashboard.answerRadioButton("yes")

      And("the user picks 2023 on the correction-return-year page")
      dashboard.checkJourneyUrl("IM9001234567/correction-return-year/1")
      dashboard.clickLink("value_2023")
      dashboard.continue()

      And("the user picks October on the correction-return-month page")
      dashboard.checkJourneyUrl("IM9001234567/correction-return-month/1")
      dashboard.clickLink("value_October")
      dashboard.continue()

      And("the user selects a country")
      dashboard.checkJourneyUrl("IM9001234567/correction-country/1/1")
      dashboard.selectCountry("Germany")

      And("the country is showing the previously declared text")
      dashboard.checkJourneyUrl("IM9001234567/country-vat-correction-amount/1/1")
      correction.previouslyDeclaredText("trader", display = true)

      And("the user enters the correction amount")
      dashboard.enterAnswer("-100.65")

      And("the user answers yes on the vat-payable-confirm page")
      dashboard.checkJourneyUrl("IM9001234567/vat-payable-confirm/1/1")
      dashboard.answerRadioButton("yes")

      And("the user clicks continue on the vat-payable-check page")
      dashboard.checkJourneyUrl("IM9001234567/vat-payable-check/1/1")
      dashboard.continue()

      And("the user answers no on the correction-list-countries page")
      dashboard.checkJourneyUrl("IM9001234567/correction-list-countries/1")
      dashboard.answerRadioButton("no")

      And("the user clicks no on the vat-correction-months-add page")
      dashboard.checkJourneyUrl("IM9001234567/2023-M12/vat-correction-months-add")
      dashboard.answerRadioButton("no")

      And("the no payments due for minus corrections text is displayed")
      dashboard.checkJourneyUrl("IM9001234567/check-your-answers")
      correction.noPaymentsDueForCorrectionsText(displayed = true)

      And("the user submits their return successfully via the check-your-answers page")
      dashboard.submit()
      dashboard.checkJourneyUrl("IM9001234567/return-successfully-submitted")
    }

    Scenario("A user is offered a single period to correct and chooses No") {

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "IM9008888888", "Organisation", "hasIOSSEnrolment", "dashboard")
      dashboard.checkJourneyUrl("your-account")

      When("the user clicks on the 'Start your return' link")
      dashboard.clickLink("start-your-return")

      Then("the user answers yes on the start page")
      dashboard.checkJourneyUrl("IM9008888888/2023-M12/start-return")
      dashboard.answerRadioButton("yes")

      And("the user answers no on the want-to-upload-file page")
      dashboard.checkJourneyUrl("IM9008888888/want-to-upload-file")
      fileUpload.selectFileUpload("No, enter them myself")

      And("the user answers no on the sold-goods page")
      dashboard.checkJourneyUrl("IM9008888888/sold-goods")
      dashboard.answerRadioButton("no")

      And("the user answers yes on the correct-previous-return page")
      dashboard.checkJourneyUrl("IM9008888888/correct-previous-return")
      dashboard.answerRadioButton("yes")

      And("the user answers no on the correction-return-single-month page")
      dashboard.checkJourneyUrl("IM9008888888/correction-return-single-month/1")
      dashboard.answerRadioButton("no")

      And("the user presses continue on the no-correction-months-available page")
      dashboard.checkJourneyUrl("no-correction-months-available")
      dashboard.continue()

      And("the user submits their return successfully via the check-your-answers page")
      dashboard.checkJourneyUrl("IM9008888888/check-your-answers")
      dashboard.submit()
      dashboard.checkJourneyUrl("IM9008888888/return-successfully-submitted")
    }

    Scenario("A user has corrections available for multiple months within the same year") {

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "IM9001234568", "Organisation", "hasIOSSEnrolment", "dashboard")
      dashboard.checkJourneyUrl("your-account")

      When("the user clicks on the 'Start your return' link")
      dashboard.clickLink("start-your-return")

      Then("the user answers yes on the start page")
      dashboard.checkJourneyUrl("IM9001234568/2024-M1/start-return")
      dashboard.answerRadioButton("yes")

      And("the user answers no on the want-to-upload-file page")
      dashboard.checkJourneyUrl("IM9001234568/want-to-upload-file")
      fileUpload.selectFileUpload("No, enter them myself")

      And("the user answers no on the sold-goods page")
      dashboard.checkJourneyUrl("IM9001234568/sold-goods")
      dashboard.answerRadioButton("no")

      And("the user answers yes on the correct-previous-return page")
      dashboard.checkJourneyUrl("IM9001234568/correct-previous-return")
      dashboard.answerRadioButton("yes")

      And("the user picks 2023 on the correction-return-year page")
      dashboard.checkJourneyUrl("IM9001234568/correction-return-year/1")
      dashboard.clickLink("value_2023")
      dashboard.continue()

      And("the user picks October on the correction-return-month page")
      dashboard.checkJourneyUrl("IM9001234568/correction-return-month/1")
      dashboard.clickLink("value_October")
      dashboard.continue()

      And("the user selects a country")
      dashboard.checkJourneyUrl("IM9001234568/correction-country/1/1")
      dashboard.selectCountry("Bulgaria")

      And("the user answers yes on the add-new-country page")
      dashboard.checkJourneyUrl("IM9001234568/add-new-country/1/1")
      dashboard.answerRadioButton("yes")

      And("the user enters the correction amount")
      dashboard.checkJourneyUrl("IM9001234568/country-vat-correction-amount/1/1")
      dashboard.enterAnswer("1500")

      And("the user answers yes on the vat-payable-confirm page")
      dashboard.checkJourneyUrl("IM9001234568/vat-payable-confirm/1/1")
      dashboard.answerRadioButton("yes")

      And("the user clicks continue on the vat-payable-check page")
      dashboard.checkJourneyUrl("IM9001234568/vat-payable-check/1/1")
      dashboard.continue()

      And("the user answers yes on the correction-list-countries page")
      dashboard.checkJourneyUrl("IM9001234568/correction-list-countries/1")
      dashboard.answerRadioButton("yes")

      And("the user selects a country")
      dashboard.checkJourneyUrl("IM9001234568/correction-country/1/2")
      dashboard.selectCountry("Czech Republic")

      And("the user answers yes on the add-new-country page")
      dashboard.checkJourneyUrl("IM9001234568/add-new-country/1/2")
      dashboard.answerRadioButton("yes")

      And("the user enters the correction amount")
      dashboard.checkJourneyUrl("IM9001234568/country-vat-correction-amount/1/2")
      dashboard.enterAnswer("160.36")

      And("the user answers yes on the vat-payable-confirm page")
      dashboard.checkJourneyUrl("IM9001234568/vat-payable-confirm/1/2")
      dashboard.answerRadioButton("yes")

      And("the user clicks continue on the vat-payable-check page")
      dashboard.checkJourneyUrl("IM9001234568/vat-payable-check/1/2")
      dashboard.continue()

      And("the user answers no on the correction-list-countries page")
      dashboard.checkJourneyUrl("IM9001234568/correction-list-countries/1")
      dashboard.answerRadioButton("no")

      And("the user clicks yes on the vat-correction-months-add page")
      dashboard.checkJourneyUrl("IM9001234568/2024-M1/vat-correction-months-add")
      dashboard.answerRadioButton("yes")

      And("the user picks 2023 on the correction-return-year page")
      dashboard.checkJourneyUrl("IM9001234568/correction-return-year/2")
      dashboard.clickLink("value_2023")
      dashboard.continue()

      And("the user picks November on the correction-return-month page")
      dashboard.checkJourneyUrl("IM9001234568/correction-return-month/2")
      dashboard.clickLink("value_November")
      dashboard.continue()

      And("the user selects a country")
      dashboard.checkJourneyUrl("IM9001234568/correction-country/2/1")
      dashboard.selectCountry("Bulgaria")

      And("the user answers yes on the add-new-country page")
      dashboard.checkJourneyUrl("IM9001234568/add-new-country/2/1")
      dashboard.answerRadioButton("yes")

      And("the user enters the correction amount")
      dashboard.checkJourneyUrl("IM9001234568/country-vat-correction-amount/2/1")
      dashboard.enterAnswer("100.25")

      And("the user answers yes on the vat-payable-confirm page")
      dashboard.checkJourneyUrl("IM9001234568/vat-payable-confirm/2/1")
      dashboard.answerRadioButton("yes")

      And("the user clicks continue on the vat-payable-check page")
      dashboard.checkJourneyUrl("IM9001234568/vat-payable-check/2/1")
      dashboard.continue()

      And("the user answers yes on the correction-list-countries page")
      dashboard.checkJourneyUrl("IM9001234568/correction-list-countries/2")
      dashboard.answerRadioButton("yes")

      And("the user selects a country")
      dashboard.checkJourneyUrl("IM9001234568/correction-country/2/2")
      dashboard.selectCountry("Slovakia")

      And("the user answers yes on the add-new-country page")
      dashboard.checkJourneyUrl("IM9001234568/add-new-country/2/2")
      dashboard.answerRadioButton("yes")

      And("the user enters the correction amount")
      dashboard.checkJourneyUrl("IM9001234568/country-vat-correction-amount/2/2")
      dashboard.enterAnswer("1450")

      And("the user answers yes on the vat-payable-confirm page")
      dashboard.checkJourneyUrl("IM9001234568/vat-payable-confirm/2/2")
      dashboard.answerRadioButton("yes")

      And("the user clicks continue on the vat-payable-check page")
      dashboard.checkJourneyUrl("IM9001234568/vat-payable-check/2/2")
      dashboard.continue()

      And("the user answers no on the correction-list-countries page")
      dashboard.checkJourneyUrl("IM9001234568/correction-list-countries/2")
      dashboard.answerRadioButton("no")

      And("the user clicks yes on the vat-correction-months-add page")
      dashboard.checkJourneyUrl("IM9001234568/2024-M1/vat-correction-months-add")
      dashboard.answerRadioButton("yes")

      And("the user picks 2023 on the correction-return-year page")
      dashboard.checkJourneyUrl("IM9001234568/correction-return-year/3")
      dashboard.clickLink("value_2023")
      dashboard.continue()

      And("the user picks December on the correction-return-month page")
      dashboard.checkJourneyUrl("IM9001234568/correction-return-month/3")
      dashboard.clickLink("value_December")
      dashboard.continue()

      And("the user selects a country")
      dashboard.checkJourneyUrl("IM9001234568/correction-country/3/1")
      dashboard.selectCountry("Bulgaria")

      And("the user answers yes on the add-new-country page")
      dashboard.checkJourneyUrl("IM9001234568/add-new-country/3/1")
      dashboard.answerRadioButton("yes")

      And("the user enters the correction amount")
      dashboard.checkJourneyUrl("IM9001234568/country-vat-correction-amount/3/1")
      dashboard.enterAnswer("1234")

      And("the user answers yes on the vat-payable-confirm page")
      dashboard.checkJourneyUrl("IM9001234568/vat-payable-confirm/3/1")
      dashboard.answerRadioButton("yes")

      And("the user clicks continue on the vat-payable-check page")
      dashboard.checkJourneyUrl("IM9001234568/vat-payable-check/3/1")
      dashboard.continue()

      And("the user answers no on the correction-list-countries page")
      dashboard.checkJourneyUrl("IM9001234568/correction-list-countries/3")
      dashboard.answerRadioButton("no")

      And("the user presses continue on the vat-correction-months page")
      dashboard.checkJourneyUrl("IM9001234568/2024-M1/vat-correction-months")
      dashboard.continue()

      And("the user submits their return successfully via the check-your-answers page")
      dashboard.checkJourneyUrl("IM9001234568/check-your-answers")
      dashboard.submit()
      dashboard.checkJourneyUrl("IM9001234568/return-successfully-submitted")
    }

    Scenario("A user has corrections available for multiple years") {

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "IM9001234569", "Organisation", "hasIOSSEnrolment", "dashboard")
      dashboard.checkJourneyUrl("your-account")

      When("the user clicks on the 'Start your return' link")
      dashboard.clickLink("start-your-return")

      Then("the user answers yes on the start page")
      dashboard.checkJourneyUrl(s"IM9001234569/$lastYear-M12/start-return")
      dashboard.answerRadioButton("yes")

      And("the user answers no on the want-to-upload-file page")
      dashboard.checkJourneyUrl("IM9001234569/want-to-upload-file")
      fileUpload.selectFileUpload("No, enter them myself")

      And("the user answers no on the sold-goods page")
      dashboard.checkJourneyUrl("IM9001234569/sold-goods")
      dashboard.answerRadioButton("no")

      And("the user answers yes on the correct-previous-return page")
      dashboard.checkJourneyUrl("IM9001234569/correct-previous-return")
      dashboard.answerRadioButton("yes")

      And("the user picks two years ago on the correction-return-year page")
      dashboard.checkJourneyUrl("IM9001234569/correction-return-year/1")
      dashboard.clickLink(s"value_$twoYearsAgo")
      dashboard.continue()

      And("the user picks October on the correction-return-month page")
      dashboard.checkJourneyUrl("IM9001234569/correction-return-month/1")
      dashboard.clickLink("value_October")
      dashboard.continue()

      And("the user selects a country")
      dashboard.checkJourneyUrl("IM9001234569/correction-country/1/1")
      dashboard.selectCountry("Denmark")

      And("the user answers yes on the add-new-country page")
      dashboard.checkJourneyUrl("IM9001234569/add-new-country/1/1")
      dashboard.answerRadioButton("yes")

      And("the user enters the correction amount")
      dashboard.checkJourneyUrl("IM9001234569/country-vat-correction-amount/1/1")
      dashboard.enterAnswer("2222")

      And("the user answers yes on the vat-payable-confirm page")
      dashboard.checkJourneyUrl("IM9001234569/vat-payable-confirm/1/1")
      dashboard.answerRadioButton("yes")

      And("the user clicks continue on the vat-payable-check page")
      dashboard.checkJourneyUrl("IM9001234569/vat-payable-check/1/1")
      dashboard.continue()

      And("the user answers no on the correction-list-countries page")
      dashboard.checkJourneyUrl("IM9001234569/correction-list-countries/1")
      dashboard.answerRadioButton("no")

      And("the user clicks yes on the vat-correction-months-add page")
      dashboard.checkJourneyUrl(s"IM9001234569/$lastYear-M12/vat-correction-months-add")
      dashboard.answerRadioButton("yes")

      And("the user picks last year on the correction-return-year page")
      dashboard.checkJourneyUrl("IM9001234569/correction-return-year/2")
      dashboard.clickLink(s"value_$lastYear")
      dashboard.continue()

      And("the user picks November on the correction-return-month page")
      dashboard.checkJourneyUrl("IM9001234569/correction-return-month/2")
      dashboard.clickLink("value_November")
      dashboard.continue()

      And("the user selects a country")
      dashboard.checkJourneyUrl("IM9001234569/correction-country/2/1")
      dashboard.selectCountry("Denmark")

      And("the user answers yes on the add-new-country page")
      dashboard.checkJourneyUrl("IM9001234569/add-new-country/2/1")
      dashboard.answerRadioButton("yes")

      And("the user enters the correction amount")
      dashboard.checkJourneyUrl("IM9001234569/country-vat-correction-amount/2/1")
      dashboard.enterAnswer("1235.04")

      And("the user answers yes on the vat-payable-confirm page")
      dashboard.checkJourneyUrl("IM9001234569/vat-payable-confirm/2/1")
      dashboard.answerRadioButton("yes")

      And("the user clicks continue on the vat-payable-check page")
      dashboard.checkJourneyUrl("IM9001234569/vat-payable-check/2/1")
      dashboard.continue()

      And("the user answers yes on the correction-list-countries page")
      dashboard.checkJourneyUrl("IM9001234569/correction-list-countries/2")
      dashboard.answerRadioButton("yes")

      And("the user selects a country")
      dashboard.checkJourneyUrl("IM9001234569/correction-country/2/2")
      dashboard.selectCountry("Spain")

      And("the user answers yes on the add-new-country page")
      dashboard.checkJourneyUrl("IM9001234569/add-new-country/2/2")
      dashboard.answerRadioButton("yes")

      And("the user enters the correction amount")
      dashboard.checkJourneyUrl("IM9001234569/country-vat-correction-amount/2/2")
      dashboard.enterAnswer("111")

      And("the user answers yes on the vat-payable-confirm page")
      dashboard.checkJourneyUrl("IM9001234569/vat-payable-confirm/2/2")
      dashboard.answerRadioButton("yes")

      And("the user clicks continue on the vat-payable-check page")
      dashboard.checkJourneyUrl("IM9001234569/vat-payable-check/2/2")
      dashboard.continue()

      And("the user answers no on the correction-list-countries page")
      dashboard.checkJourneyUrl("IM9001234569/correction-list-countries/2")
      dashboard.answerRadioButton("no")

      And("the user clicks yes on the vat-correction-months-add page")
      dashboard.checkJourneyUrl(s"IM9001234569/$lastYear-M12/vat-correction-months-add")
      dashboard.answerRadioButton("yes")

      And("the user picks two years ago on the correction-return-year page")
      dashboard.checkJourneyUrl("IM9001234569/correction-return-year/3")
      dashboard.clickLink(s"value_$twoYearsAgo")
      dashboard.continue()

      And("the user picks December on the correction-return-month page")
      dashboard.checkJourneyUrl("IM9001234569/correction-return-month/3")
      dashboard.clickLink("value_December")
      dashboard.continue()

      And("the user selects a country")
      dashboard.checkJourneyUrl("IM9001234569/correction-country/3/1")
      dashboard.selectCountry("Spain")

      And("the user answers yes on the add-new-country page")
      dashboard.checkJourneyUrl("IM9001234569/add-new-country/3/1")
      dashboard.answerRadioButton("yes")

      And("the user enters the correction amount")
      dashboard.checkJourneyUrl("IM9001234569/country-vat-correction-amount/3/1")
      dashboard.enterAnswer("45210")

      And("the user answers yes on the vat-payable-confirm page")
      dashboard.checkJourneyUrl("IM9001234569/vat-payable-confirm/3/1")
      dashboard.answerRadioButton("yes")

      And("the user clicks continue on the vat-payable-check page")
      dashboard.checkJourneyUrl("IM9001234569/vat-payable-check/3/1")
      dashboard.continue()

      And("the user answers no on the correction-list-countries page")
      dashboard.checkJourneyUrl("IM9001234569/correction-list-countries/3")
      dashboard.answerRadioButton("no")

      And("the user answers no on the vat-correction-months-add page")
      dashboard.checkJourneyUrl(s"IM9001234569/$lastYear-M12/vat-correction-months-add")
      dashboard.answerRadioButton("no")

      And("the user submits their return successfully via the check-your-answers page")
      dashboard.checkJourneyUrl("IM9001234569/check-your-answers")
      dashboard.submit()
      dashboard.checkJourneyUrl("IM9001234569/return-successfully-submitted")
    }

    Scenario("A user adds return data with corrections for multiple years and periods") {

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "IM9001234569", "Organisation", "hasIOSSEnrolment", "dashboard")
      dashboard.checkJourneyUrl("your-account")

      When("the user clicks on the 'Start your return' link")
      dashboard.clickLink("start-your-return")

      Then("the user answers yes on the start page")
      dashboard.checkJourneyUrl(s"IM9001234569/$lastYear-M12/start-return")
      dashboard.answerRadioButton("yes")

      And("the user answers no on the want-to-upload-file page")
      dashboard.checkJourneyUrl("IM9001234569/want-to-upload-file")
      fileUpload.selectFileUpload("No, enter them myself")

      And("the user answers yes on the sold-goods page")
      dashboard.checkJourneyUrl("IM9001234569/sold-goods")
      dashboard.answerRadioButton("yes")

      And("the user selects a country on the sold-to-country page")
      dashboard.checkJourneyUrl("IM9001234569/sold-to-country/1")
      dashboard.selectCountry("France")

      And("the user selects the first and fifth checkbox on the vat-rates-from-country page")
      dashboard.checkJourneyUrl("IM9001234569/vat-rates-from-country/1")
      dashboard.tickCheckbox("first")
      dashboard.tickCheckbox("fifth")
      dashboard.continue()

      And("the user enters the first country total sales for first VAT rate")
      dashboard.checkJourneyUrl("IM9001234569/sales-to-country/1/1")
      dashboard.enterAnswer("6000")

      And("the user confirms the suggested amount of VAT")
      dashboard.checkJourneyUrl("IM9001234569/vat-on-sales/1/1")
      dashboard.clickLink("choice")
      dashboard.continue()

      And("the user enters the first country total sales for second VAT rate")
      dashboard.checkJourneyUrl("IM9001234569/sales-to-country/1/2")
      dashboard.enterAnswer("987654.32")

      And("the user confirms the suggested amount of VAT")
      dashboard.checkJourneyUrl("IM9001234569/vat-on-sales/1/2")
      dashboard.clickLink("choice")
      dashboard.continue()

      And("the user answers no on the check-sales page")
      dashboard.checkJourneyUrl("IM9001234569/check-sales/1")
      dashboard.answerRadioButton("no")

      And("the user answers yes on the add-sales-country-list page")
      dashboard.checkJourneyUrl("IM9001234569/add-sales-country-list")
      dashboard.answerRadioButton("yes")

      And("the user selects a country on the sold-to-country page")
      dashboard.checkJourneyUrl("IM9001234569/sold-to-country/2")
      dashboard.selectCountry("Italy")

      And("the user selects the first checkbox on the vat-rates-from-country page")
      dashboard.checkJourneyUrl("IM9001234569/vat-rates-from-country/2")
      dashboard.tickCheckbox("first")
      dashboard.continue()

      And("the user enters the second country total sales for first VAT rate")
      dashboard.checkJourneyUrl("IM9001234569/sales-to-country/2/1")
      dashboard.enterAnswer("1234")

      And("the user enters a different amount of VAT instead of the suggested amount of VAT")
      dashboard.checkJourneyUrl("IM9001234569/vat-on-sales/2/1")
      dashboard.enterAlternativeVatAmount("120.56")

      And("the user answers no on the check-sales page")
      dashboard.checkJourneyUrl("IM9001234569/check-sales/2")
      dashboard.answerRadioButton("no")

      And("the user answers no on the add-sales-country-list page")
      dashboard.checkJourneyUrl("IM9001234569/add-sales-country-list")
      dashboard.answerRadioButton("no")

      And("the user answers yes on the correct-previous-return page")
      dashboard.checkJourneyUrl("IM9001234569/correct-previous-return")
      dashboard.answerRadioButton("yes")

      And("the user picks two years ago on the correction-return-year page")
      dashboard.checkJourneyUrl("IM9001234569/correction-return-year/1")
      dashboard.clickLink(s"value_$twoYearsAgo")
      dashboard.continue()

      And("the user picks October on the correction-return-month page")
      dashboard.checkJourneyUrl("IM9001234569/correction-return-month/1")
      dashboard.clickLink("value_October")
      dashboard.continue()

      And("the user selects a country")
      dashboard.checkJourneyUrl("IM9001234569/correction-country/1/1")
      dashboard.selectCountry("Denmark")

      And("the user answers yes on the add-new-country page")
      dashboard.checkJourneyUrl("IM9001234569/add-new-country/1/1")
      dashboard.answerRadioButton("yes")

      And("the user enters the correction amount")
      dashboard.checkJourneyUrl("IM9001234569/country-vat-correction-amount/1/1")
      dashboard.enterAnswer("2222")

      And("the user answers yes on the vat-payable-confirm page")
      dashboard.checkJourneyUrl("IM9001234569/vat-payable-confirm/1/1")
      dashboard.answerRadioButton("yes")

      And("the user clicks continue on the vat-payable-check page")
      dashboard.checkJourneyUrl("IM9001234569/vat-payable-check/1/1")
      dashboard.continue()

      And("the user answers no on the correction-list-countries page")
      dashboard.checkJourneyUrl("IM9001234569/correction-list-countries/1")
      dashboard.answerRadioButton("no")

      And("the user clicks yes on the vat-correction-months-add page")
      dashboard.checkJourneyUrl(s"IM9001234569/$lastYear-M12/vat-correction-months-add")
      dashboard.answerRadioButton("yes")

      And("the user picks last year on the correction-return-year page")
      dashboard.checkJourneyUrl("IM9001234569/correction-return-year/2")
      dashboard.clickLink(s"value_$lastYear")
      dashboard.continue()

      And("the user picks November on the correction-return-month page")
      dashboard.checkJourneyUrl("IM9001234569/correction-return-month/2")
      dashboard.clickLink("value_November")
      dashboard.continue()

      And("the user selects a country")
      dashboard.checkJourneyUrl("IM9001234569/correction-country/2/1")
      dashboard.selectCountry("Slovakia")

      And("the user answers yes on the add-new-country page")
      dashboard.checkJourneyUrl("IM9001234569/add-new-country/2/1")
      dashboard.answerRadioButton("yes")

      And("the user enters the correction amount")
      dashboard.checkJourneyUrl("IM9001234569/country-vat-correction-amount/2/1")
      dashboard.enterAnswer("1234")

      And("the user answers yes on the vat-payable-confirm page")
      dashboard.checkJourneyUrl("IM9001234569/vat-payable-confirm/2/1")
      dashboard.answerRadioButton("yes")

      And("the user clicks continue on the vat-payable-check page")
      dashboard.checkJourneyUrl("IM9001234569/vat-payable-check/2/1")
      dashboard.continue()

      And("the user answers yes on the correction-list-countries page")
      dashboard.checkJourneyUrl("IM9001234569/correction-list-countries/2")
      dashboard.answerRadioButton("yes")

      And("the user selects a country")
      dashboard.checkJourneyUrl("IM9001234569/correction-country/2/2")
      dashboard.selectCountry("Germany")

      And("the user enters the correction amount")
      dashboard.checkJourneyUrl("IM9001234569/country-vat-correction-amount/2/2")
      dashboard.enterAnswer("1234")

      And("the user answers yes on the vat-payable-confirm page")
      dashboard.checkJourneyUrl("IM9001234569/vat-payable-confirm/2/2")
      dashboard.answerRadioButton("yes")

      And("the user clicks continue on the vat-payable-check page")
      dashboard.checkJourneyUrl("IM9001234569/vat-payable-check/2/2")
      dashboard.continue()

      And("the user answers no on the correction-list-countries page")
      dashboard.checkJourneyUrl("IM9001234569/correction-list-countries/2")
      dashboard.answerRadioButton("no")

      And("the user clicks no on the vat-correction-months-add page")
      dashboard.checkJourneyUrl(s"IM9001234569/$lastYear-M12/vat-correction-months-add")
      dashboard.answerRadioButton("no")

      And("the user submits their return successfully via the check-your-answers page")
      dashboard.checkJourneyUrl("IM9001234569/check-your-answers")
      dashboard.submit()
      dashboard.checkJourneyUrl("IM9001234569/return-successfully-submitted")
    }
  }
}
