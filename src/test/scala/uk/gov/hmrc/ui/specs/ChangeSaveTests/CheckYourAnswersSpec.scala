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

package uk.gov.hmrc.ui.specs.ChangeSaveTests

import uk.gov.hmrc.ui.pages._
import uk.gov.hmrc.ui.specs.BaseSpec

import java.time.LocalDate

class CheckYourAnswersSpec extends BaseSpec {

  private val dashboard  = Dashboard
  private val auth       = Auth
  private val fileUpload = FileUpload

  private val lastYear    = LocalDate.now().minusYears(1).getYear
  private val twoYearsAgo = LocalDate.now().minusYears(2).getYear

  Feature("Check Your Answers journeys") {

    Scenario("A user adds details to a return then uses Check Your Answers to change 'Sales made' to no") {

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "IM9001234567", "Organisation", "hasIOSSEnrolment", "dashboard")
      dashboard.checkJourneyUrl("your-account")

      When("the user clicks on the 'Start your return' link")
      dashboard.clickLink("start-your-return")

      Then("the user adds sales for one country")
      dashboard.checkJourneyUrl("IM9001234567/2023-M12/start-return")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234567/want-to-upload-file")
      fileUpload.selectFileUpload("No, enter them myself")
      dashboard.checkJourneyUrl("IM9001234567/sold-goods")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234567/sold-to-country/1")
      dashboard.selectCountry("France")
      dashboard.checkJourneyUrl("IM9001234567/vat-rates-from-country/1")
      dashboard.tickCheckbox("first")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9001234567/sales-to-country/1/1")
      dashboard.enterAnswer("6000")
      dashboard.checkJourneyUrl("IM9001234567/vat-on-sales/1/1")
      dashboard.clickLink("choice")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9001234567/check-sales/1")
      dashboard.answerRadioButton("no")
      dashboard.checkJourneyUrl("IM9001234567/add-sales-country-list")
      dashboard.answerRadioButton("no")
      dashboard.checkJourneyUrl("IM9001234567/correct-previous-return")
      dashboard.answerRadioButton("no")

      And("the user clicks change for sold-goods on the check-your-answers page")
      dashboard.checkJourneyUrl("IM9001234567/check-your-answers")
      dashboard.selectChangeOrRemoveLink("IM9001234567\\/sold-goods\\?waypoints\\=IM9001234567-check-your-answers")
      dashboard.checkJourneyUrl("IM9001234567/sold-goods?waypoints=IM9001234567-check-your-answers")

      And("the user changes the answer to no")
      dashboard.answerRadioButton("no")

      And("the user submits their return successfully via the check-your-answers page")
      dashboard.checkJourneyUrl("IM9001234567/check-your-answers")
      dashboard.submit()
      dashboard.checkJourneyUrl("IM9001234567/return-successfully-submitted")
    }

    Scenario("A user completes a nil return then uses Check Your Answers to amend it") {

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "IM9001234567", "Organisation", "hasIOSSEnrolment", "dashboard")
      dashboard.checkJourneyUrl("your-account")

      When("the user clicks on the 'Start your return' link")
      dashboard.clickLink("start-your-return")

      Then("the user completes a nil return")
      dashboard.checkJourneyUrl("IM9001234567/2023-M12/start-return")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234567/want-to-upload-file")
      fileUpload.selectFileUpload("No, enter them myself")
      dashboard.checkJourneyUrl("IM9001234567/sold-goods")
      dashboard.answerRadioButton("no")
      dashboard.checkJourneyUrl("IM9001234567/correct-previous-return")
      dashboard.answerRadioButton("no")

      And("the user clicks change for sold-goods on the check-your-answers page")
      dashboard.checkJourneyUrl("IM9001234567/check-your-answers")
      dashboard.selectChangeOrRemoveLink("IM9001234567\\/sold-goods\\?waypoints\\=IM9001234567-check-your-answers")
      dashboard.checkJourneyUrl("IM9001234567/sold-goods?waypoints=IM9001234567-check-your-answers")

      And("the user changes the answer to yes")
      dashboard.answerRadioButton("yes")

      And("the user adds sales for Croatia")
      dashboard.checkJourneyUrl("IM9001234567/sold-to-country/1?waypoints=IM9001234567-check-your-answers")
      dashboard.selectCountry("Croatia")
      dashboard.checkJourneyUrl("IM9001234567/vat-rates-from-country/1?waypoints=IM9001234567-check-your-answers")
      dashboard.tickCheckbox("first")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9001234567/sales-to-country/1/1?waypoints=IM9001234567-check-your-answers")
      dashboard.enterAnswer("1500")
      dashboard.checkJourneyUrl("IM9001234567/vat-on-sales/1/1?waypoints=IM9001234567-check-your-answers")
      dashboard.clickLink("choice")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9001234567/check-sales/1?waypoints=IM9001234567-check-your-answers")
      dashboard.answerRadioButton("yes")

      And("the user adds another VAT rate for Croatia")
      dashboard.checkJourneyUrl("IM9001234567/vat-rates-from-country/1?waypoints=IM9001234567-check-your-answers")
      dashboard.tickCheckbox("second")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9001234567/sales-to-country/1/2?waypoints=IM9001234567-check-your-answers")
      dashboard.enterAnswer("147.65")
      dashboard.checkJourneyUrl("IM9001234567/vat-on-sales/1/2?waypoints=IM9001234567-check-your-answers")
      dashboard.clickLink("choice")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9001234567/check-sales/1?waypoints=IM9001234567-check-your-answers")
      dashboard.answerRadioButton("yes")

      And("the user adds the final VAT rate for Croatia")
      dashboard.checkJourneyUrl(
        "IM9001234567/remaining-vat-rate-from-country/1/3?waypoints=IM9001234567-check-your-answers"
      )
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234567/sales-to-country/1/3?waypoints=IM9001234567-check-your-answers")
      dashboard.enterAnswer("16001")
      dashboard.checkJourneyUrl("IM9001234567/vat-on-sales/1/3?waypoints=IM9001234567-check-your-answers")
      dashboard.clickLink("choice")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9001234567/check-sales/1?waypoints=IM9001234567-check-your-answers")
      dashboard.continue()

      And("the user adds sales for Germany")
      dashboard.checkJourneyUrl("IM9001234567/add-sales-country-list?waypoints=IM9001234567-check-your-answers")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234567/sold-to-country/2?waypoints=IM9001234567-check-your-answers")
      dashboard.selectCountry("Germany")
      dashboard.checkJourneyUrl("IM9001234567/vat-rates-from-country/2?waypoints=IM9001234567-check-your-answers")
      dashboard.tickCheckbox("second")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9001234567/sales-to-country/2/1?waypoints=IM9001234567-check-your-answers")
      dashboard.enterAnswer("3210")
      dashboard.checkJourneyUrl("IM9001234567/vat-on-sales/2/1?waypoints=IM9001234567-check-your-answers")
      dashboard.clickLink("choice")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9001234567/check-sales/2?waypoints=IM9001234567-check-your-answers")
      dashboard.answerRadioButton("no")
      dashboard.checkJourneyUrl("IM9001234567/add-sales-country-list?waypoints=IM9001234567-check-your-answers")
      dashboard.answerRadioButton("no")

      When("the user is back on the check-your-answers page")
      dashboard.checkJourneyUrl("IM9001234567/check-your-answers")

      Then("the user clicks change for correct-previous-return")
      dashboard.selectChangeOrRemoveLink(
        "IM9001234567\\/correct-previous-return\\?waypoints\\=IM9001234567-check-your-answers"
      )
      dashboard.checkJourneyUrl("IM9001234567/correct-previous-return?waypoints=IM9001234567-check-your-answers")

      And("the user changes the answer to yes")
      dashboard.answerRadioButton("yes")

      And("the user adds a correction to Bulgaria")
      dashboard.checkJourneyUrl("IM9001234567/correction-return-year/1?waypoints=IM9001234567-check-your-answers")
      dashboard.clickLink("value_2023")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9001234567/correction-return-month/1?waypoints=IM9001234567-check-your-answers")
      dashboard.clickLink("value_October")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9001234567/correction-country/1/1?waypoints=IM9001234567-check-your-answers")
      dashboard.selectCountry("Bulgaria")
      dashboard.checkJourneyUrl("IM9001234567/add-new-country/1/1?waypoints=IM9001234567-check-your-answers")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl(
        "IM9001234567/country-vat-correction-amount/1/1?waypoints=IM9001234567-check-your-answers"
      )
      dashboard.enterAnswer("1500")
      dashboard.checkJourneyUrl("IM9001234567/vat-payable-confirm/1/1?waypoints=IM9001234567-check-your-answers")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234567/vat-payable-check/1/1?waypoints=IM9001234567-check-your-answers")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9001234567/correction-list-countries/1?waypoints=IM9001234567-check-your-answers")
      dashboard.answerRadioButton("no")

      And("the user adds another correction to Bulgaria for a different month")
      dashboard.checkJourneyUrl(
        "IM9001234567/2023-M12/vat-correction-months-add?waypoints=IM9001234567-check-your-answers"
      )
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234567/correction-return-year/2?waypoints=IM9001234567-check-your-answers")
      dashboard.clickLink("value_2023")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9001234567/correction-return-month/2?waypoints=IM9001234567-check-your-answers")
      dashboard.clickLink("value_November")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9001234567/correction-country/2/1?waypoints=IM9001234567-check-your-answers")
      dashboard.selectCountry("Bulgaria")
      dashboard.checkJourneyUrl("IM9001234567/add-new-country/2/1?waypoints=IM9001234567-check-your-answers")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl(
        "IM9001234567/country-vat-correction-amount/2/1?waypoints=IM9001234567-check-your-answers"
      )
      dashboard.enterAnswer("100.25")
      dashboard.checkJourneyUrl("IM9001234567/vat-payable-confirm/2/1?waypoints=IM9001234567-check-your-answers")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234567/vat-payable-check/2/1?waypoints=IM9001234567-check-your-answers")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9001234567/correction-list-countries/2?waypoints=IM9001234567-check-your-answers")
      dashboard.answerRadioButton("no")
      dashboard.checkJourneyUrl("IM9001234567/2023-M12/vat-correction-months?waypoints=IM9001234567-check-your-answers")
      dashboard.continue()

      And("the user submits their return successfully via the check-your-answers page")
      dashboard.checkJourneyUrl("IM9001234567/check-your-answers")
      dashboard.submit()
      dashboard.checkJourneyUrl("IM9001234567/return-successfully-submitted")
    }

    Scenario("A user adds details to a return then uses Check Your Answers to amend it and add more") {

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "IM9001234567", "Organisation", "hasIOSSEnrolment", "dashboard")
      dashboard.checkJourneyUrl("your-account")

      When("the user clicks on the 'Start your return' link")
      dashboard.clickLink("start-your-return")

      Then("the user adds sales for one country")
      dashboard.checkJourneyUrl("IM9001234567/2023-M12/start-return")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234567/want-to-upload-file")
      fileUpload.selectFileUpload("No, enter them myself")
      dashboard.checkJourneyUrl("IM9001234567/sold-goods")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234567/sold-to-country/1")
      dashboard.selectCountry("France")
      dashboard.checkJourneyUrl("IM9001234567/vat-rates-from-country/1")
      dashboard.tickCheckbox("first")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9001234567/sales-to-country/1/1")
      dashboard.enterAnswer("6000")
      dashboard.checkJourneyUrl("IM9001234567/vat-on-sales/1/1")
      dashboard.clickLink("choice")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9001234567/check-sales/1")
      dashboard.answerRadioButton("no")
      dashboard.checkJourneyUrl("IM9001234567/add-sales-country-list")
      dashboard.answerRadioButton("no")
      dashboard.checkJourneyUrl("IM9001234567/correct-previous-return")
      dashboard.answerRadioButton("no")

      And("the user clicks change for add-sales-country-list on the check-your-answers page")
      dashboard.checkJourneyUrl("IM9001234567/check-your-answers")
      dashboard.selectChangeOrRemoveLink(
        "IM9001234567\\/add-sales-country-list\\?waypoints\\=IM9001234567-check-your-answers"
      )
      dashboard.checkJourneyUrl("IM9001234567/add-sales-country-list?waypoints=IM9001234567-check-your-answers")

      And("the user clicks change for check-sales/1")
      dashboard.selectChangeOrRemoveLink(
        "IM9001234567\\/check-sales\\/1\\?waypoints\\=IM9001234567-change-add-sales-country-list\\%2CIM9001234567-check-your-answers"
      )
      dashboard.checkJourneyUrl(
        "IM9001234567/check-sales/1?waypoints=IM9001234567-change-add-sales-country-list%2CIM9001234567-check-your-answers"
      )

      And("the user answers yes to add another VAT rate")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl(
        "IM9001234567/vat-rates-from-country/1?waypoints=IM9001234567-change-add-sales-country-list%2CIM9001234567-check-your-answers"
      )
      dashboard.tickCheckbox("second")
      dashboard.continue()
      dashboard.checkJourneyUrl(
        "IM9001234567/sales-to-country/1/2?waypoints=IM9001234567-change-add-sales-country-list%2CIM9001234567-check-your-answers"
      )
      dashboard.enterAnswer("125.63")
      dashboard.checkJourneyUrl(
        "IM9001234567/vat-on-sales/1/2?waypoints=IM9001234567-change-add-sales-country-list%2CIM9001234567-check-your-answers"
      )
      dashboard.clickLink("choice")
      dashboard.continue()
      dashboard.checkJourneyUrl(
        "IM9001234567/check-sales/1?waypoints=IM9001234567-change-add-sales-country-list%2CIM9001234567-check-your-answers"
      )
      dashboard.answerRadioButton("no")

      And("the user answers adds sales for Germany")
      dashboard.checkJourneyUrl("IM9001234567/add-sales-country-list?waypoints=IM9001234567-check-your-answers")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234567/sold-to-country/2?waypoints=IM9001234567-check-your-answers")
      dashboard.selectCountry("Germany")
      dashboard.checkJourneyUrl("IM9001234567/vat-rates-from-country/2?waypoints=IM9001234567-check-your-answers")
      dashboard.tickCheckbox("first")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9001234567/sales-to-country/2/1?waypoints=IM9001234567-check-your-answers")
      dashboard.enterAnswer("6000")
      dashboard.checkJourneyUrl("IM9001234567/vat-on-sales/2/1?waypoints=IM9001234567-check-your-answers")
      dashboard.clickLink("choice")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9001234567/check-sales/2?waypoints=IM9001234567-check-your-answers")
      dashboard.answerRadioButton("no")
      dashboard.checkJourneyUrl("IM9001234567/add-sales-country-list?waypoints=IM9001234567-check-your-answers")
      dashboard.answerRadioButton("no")

      And("the user submits their return successfully via the check-your-answers page")
      dashboard.checkJourneyUrl("IM9001234567/check-your-answers")
      dashboard.submit()
      dashboard.checkJourneyUrl("IM9001234567/return-successfully-submitted")
    }

    Scenario(
      "A user adds return data with corrections for multiple years and periods then changes corrections to no on CYA page"
    ) {

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "IM9001234569", "Organisation", "hasIOSSEnrolment", "dashboard")
      dashboard.checkJourneyUrl("your-account")

      When("the user clicks on the 'Start your return' link")
      dashboard.clickLink("start-your-return")

      Then("the user answers yes on the start page")
      dashboard.checkJourneyUrl(s"IM9001234569/$lastYear-M12/start-return")
      dashboard.answerRadioButton("yes")

      And("the user adds sales data to their return")
      dashboard.checkJourneyUrl("IM9001234569/want-to-upload-file")
      fileUpload.selectFileUpload("No, enter them myself")
      dashboard.checkJourneyUrl("IM9001234569/sold-goods")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234569/sold-to-country/1")
      dashboard.selectCountry("France")
      dashboard.checkJourneyUrl("IM9001234569/vat-rates-from-country/1")
      dashboard.tickCheckbox("first")
      dashboard.tickCheckbox("fifth")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9001234569/sales-to-country/1/1")
      dashboard.enterAnswer("6000")
      dashboard.checkJourneyUrl("IM9001234569/vat-on-sales/1/1")
      dashboard.clickLink("choice")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9001234569/sales-to-country/1/2")
      dashboard.enterAnswer("987654.32")
      dashboard.checkJourneyUrl("IM9001234569/vat-on-sales/1/2")
      dashboard.clickLink("choice")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9001234569/check-sales/1")
      dashboard.answerRadioButton("no")
      dashboard.checkJourneyUrl("IM9001234569/add-sales-country-list")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234569/sold-to-country/2")
      dashboard.selectCountry("Italy")
      dashboard.checkJourneyUrl("IM9001234569/vat-rates-from-country/2")
      dashboard.tickCheckbox("first")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9001234569/sales-to-country/2/1")
      dashboard.enterAnswer("1234")
      dashboard.checkJourneyUrl("IM9001234569/vat-on-sales/2/1")
      dashboard.enterAlternativeVatAmount("120.56")
      dashboard.checkJourneyUrl("IM9001234569/check-sales/2")
      dashboard.answerRadioButton("no")
      dashboard.checkJourneyUrl("IM9001234569/add-sales-country-list")
      dashboard.answerRadioButton("no")

      And("the user adds correction data to their return")
      dashboard.checkJourneyUrl("IM9001234569/correct-previous-return")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234569/correction-return-year/1")
      dashboard.clickLink(s"value_$twoYearsAgo")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9001234569/correction-return-month/1")
      dashboard.clickLink("value_October")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9001234569/correction-country/1/1")
      dashboard.selectCountry("Denmark")
      dashboard.checkJourneyUrl("IM9001234569/add-new-country/1/1")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234569/country-vat-correction-amount/1/1")
      dashboard.enterAnswer("2222")
      dashboard.checkJourneyUrl("IM9001234569/vat-payable-confirm/1/1")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234569/vat-payable-check/1/1")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9001234569/correction-list-countries/1")
      dashboard.answerRadioButton("no")
      dashboard.checkJourneyUrl(s"IM9001234569/$lastYear-M12/vat-correction-months-add")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234569/correction-return-year/2")
      dashboard.clickLink(s"value_$lastYear")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9001234569/correction-return-month/2")
      dashboard.clickLink("value_November")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9001234569/correction-country/2/1")
      dashboard.selectCountry("Slovakia")
      dashboard.checkJourneyUrl("IM9001234569/add-new-country/2/1")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234569/country-vat-correction-amount/2/1")
      dashboard.enterAnswer("1234")
      dashboard.checkJourneyUrl("IM9001234569/vat-payable-confirm/2/1")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234569/vat-payable-check/2/1")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9001234569/correction-list-countries/2")
      dashboard.answerRadioButton("no")
      dashboard.checkJourneyUrl(s"IM9001234569/$lastYear-M12/vat-correction-months-add")
      dashboard.answerRadioButton("no")

      When("the user is on check-your-answers at the end of the return journey")
      dashboard.checkJourneyUrl("IM9001234569/check-your-answers")

      And("the user clicks change for correct-previous-return")
      dashboard.selectChangeOrRemoveLink(
        "IM9001234569\\/correct-previous-return\\?waypoints\\=IM9001234569-check-your-answers"
      )

      Then("the user changes the answer to no to remove all corrections from the return")
      dashboard.checkJourneyUrl("IM9001234569/correct-previous-return?waypoints=IM9001234569-check-your-answers")
      dashboard.answerRadioButton("no")

      And("the user submits their return successfully via the check-your-answers page")
      dashboard.checkJourneyUrl("IM9001234569/check-your-answers")
      dashboard.submit()
      dashboard.checkJourneyUrl("IM9001234569/return-successfully-submitted")
    }

    Scenario(
      "A user adds return data with corrections for multiple years and periods then changes corrections via CYA"
    ) {

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "IM9001234569", "Organisation", "hasIOSSEnrolment", "dashboard")
      dashboard.checkJourneyUrl("your-account")

      When("the user clicks on the 'Start your return' link")
      dashboard.clickLink("start-your-return")

      Then("the user answers yes on the start page")
      dashboard.checkJourneyUrl(s"IM9001234569/$lastYear-M12/start-return")
      dashboard.answerRadioButton("yes")

      And("the user adds sales data to their return")
      dashboard.checkJourneyUrl("IM9001234569/want-to-upload-file")
      fileUpload.selectFileUpload("No, enter them myself")
      dashboard.checkJourneyUrl("IM9001234569/sold-goods")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234569/sold-to-country/1")
      dashboard.selectCountry("France")
      dashboard.checkJourneyUrl("IM9001234569/vat-rates-from-country/1")
      dashboard.tickCheckbox("first")
      dashboard.tickCheckbox("fifth")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9001234569/sales-to-country/1/1")
      dashboard.enterAnswer("6000")
      dashboard.checkJourneyUrl("IM9001234569/vat-on-sales/1/1")
      dashboard.clickLink("choice")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9001234569/sales-to-country/1/2")
      dashboard.enterAnswer("987654.32")
      dashboard.checkJourneyUrl("IM9001234569/vat-on-sales/1/2")
      dashboard.clickLink("choice")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9001234569/check-sales/1")
      dashboard.answerRadioButton("no")
      dashboard.checkJourneyUrl("IM9001234569/add-sales-country-list")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234569/sold-to-country/2")
      dashboard.selectCountry("Italy")
      dashboard.checkJourneyUrl("IM9001234569/vat-rates-from-country/2")
      dashboard.tickCheckbox("first")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9001234569/sales-to-country/2/1")
      dashboard.enterAnswer("1234")
      dashboard.checkJourneyUrl("IM9001234569/vat-on-sales/2/1")
      dashboard.enterAlternativeVatAmount("120.56")
      dashboard.checkJourneyUrl("IM9001234569/check-sales/2")
      dashboard.answerRadioButton("no")
      dashboard.checkJourneyUrl("IM9001234569/add-sales-country-list")
      dashboard.answerRadioButton("no")

      And("the user adds correction data to their return")
      dashboard.checkJourneyUrl("IM9001234569/correct-previous-return")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234569/correction-return-year/1")
      dashboard.clickLink(s"value_$twoYearsAgo")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9001234569/correction-return-month/1")
      dashboard.clickLink("value_October")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9001234569/correction-country/1/1")
      dashboard.selectCountry("Denmark")
      dashboard.checkJourneyUrl("IM9001234569/add-new-country/1/1")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234569/country-vat-correction-amount/1/1")
      dashboard.enterAnswer("2222")
      dashboard.checkJourneyUrl("IM9001234569/vat-payable-confirm/1/1")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234569/vat-payable-check/1/1")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9001234569/correction-list-countries/1")
      dashboard.answerRadioButton("no")
      dashboard.checkJourneyUrl(s"IM9001234569/$lastYear-M12/vat-correction-months-add")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234569/correction-return-year/2")
      dashboard.clickLink(s"value_$lastYear")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9001234569/correction-return-month/2")
      dashboard.clickLink("value_November")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9001234569/correction-country/2/1")
      dashboard.selectCountry("Slovakia")
      dashboard.checkJourneyUrl("IM9001234569/add-new-country/2/1")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234569/country-vat-correction-amount/2/1")
      dashboard.enterAnswer("1234")
      dashboard.checkJourneyUrl("IM9001234569/vat-payable-confirm/2/1")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234569/vat-payable-check/2/1")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9001234569/correction-list-countries/2")
      dashboard.answerRadioButton("no")
      dashboard.checkJourneyUrl(s"IM9001234569/$lastYear-M12/vat-correction-months-add")
      dashboard.answerRadioButton("no")

      When("the user is on check-your-answers at the end of the return journey")
      dashboard.checkJourneyUrl("IM9001234569/check-your-answers")

      And("the user clicks change for vat-correction-months-add")
      dashboard.selectChangeOrRemoveLink(
        s"IM9001234569\\/$lastYear-M12\\/vat-correction-months-add\\?waypoints\\=IM9001234569-check-your-answers"
      )

      Then("the user answers yes and adds another correction")
      dashboard.checkJourneyUrl(
        s"IM9001234569/$lastYear-M12/vat-correction-months-add?waypoints=IM9001234569-check-your-answers"
      )
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234569/correction-return-year/3?waypoints=IM9001234569-check-your-answers")
      dashboard.clickLink(s"value_$twoYearsAgo")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9001234569/correction-return-month/3?waypoints=IM9001234569-check-your-answers")
      dashboard.clickLink("value_December")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9001234569/correction-country/3/1?waypoints=IM9001234569-check-your-answers")
      dashboard.selectCountry("Slovakia")
      dashboard.checkJourneyUrl("IM9001234569/add-new-country/3/1?waypoints=IM9001234569-check-your-answers")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl(
        "IM9001234569/country-vat-correction-amount/3/1?waypoints=IM9001234569-check-your-answers"
      )
      dashboard.enterAnswer("1234")
      dashboard.checkJourneyUrl("IM9001234569/vat-payable-confirm/3/1?waypoints=IM9001234569-check-your-answers")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234569/vat-payable-check/3/1?waypoints=IM9001234569-check-your-answers")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9001234569/correction-list-countries/3?waypoints=IM9001234569-check-your-answers")
      dashboard.answerRadioButton("no")
      dashboard.checkJourneyUrl(
        s"IM9001234569/$lastYear-M12/vat-correction-months-add?waypoints=IM9001234569-check-your-answers"
      )
      dashboard.answerRadioButton("no")

      And("the user submits their return successfully via the check-your-answers page")
      dashboard.checkJourneyUrl("IM9001234569/check-your-answers")
      dashboard.submit()
      dashboard.checkJourneyUrl("IM9001234569/return-successfully-submitted")
    }
  }
}
