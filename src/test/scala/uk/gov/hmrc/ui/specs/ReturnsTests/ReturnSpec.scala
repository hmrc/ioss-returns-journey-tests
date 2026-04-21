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

class ReturnSpec extends BaseSpec {

  private val dashboard = Dashboard
  private val auth      = Auth
  private val fileUpload = FileUpload

  Feature("Returns journeys") {

    Scenario("A user adds sales for multiple countries to a return") {

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000002", "IM9009999888", "Organisation", "hasIOSSEnrolment", "dashboard")
      dashboard.checkJourneyUrl("your-account")

      When("the user clicks on the 'Start your return' link")
      dashboard.clickLink("start-your-return")

      Then("the user answers yes on the start page")
      dashboard.checkJourneyUrl("IM9009999888/2023-M12/start-return")
      dashboard.answerRadioButton("yes")

      And("the user answers no on the want-to-upload-file page")
      dashboard.checkJourneyUrl("IM9009999888/want-to-upload-file")
      fileUpload.selectFileUpload("No, enter them myself")

      And("the user answers yes on the sold-goods page")
      dashboard.checkJourneyUrl("IM9009999888/sold-goods")
      dashboard.answerRadioButton("yes")

      And("the user selects a country on the sold-to-country page")
      dashboard.checkJourneyUrl("IM9009999888/sold-to-country/1")
      dashboard.selectCountry("France")

      And("the user selects the first and fifth checkbox on the vat-rates-from-country page")
      dashboard.checkJourneyUrl("IM9009999888/vat-rates-from-country/1")
      dashboard.tickCheckbox("first")
      dashboard.tickCheckbox("fifth")
      dashboard.continue()

      And("the user enters the first country total sales for first VAT rate")
      dashboard.checkJourneyUrl("IM9009999888/sales-to-country/1/1")
      dashboard.enterAnswer("6000")

      And("the user confirms the suggested amount of VAT")
      dashboard.checkJourneyUrl("IM9009999888/vat-on-sales/1/1")
      dashboard.clickLink("choice")
      dashboard.continue()

      And("the user enters the first country total sales for second VAT rate")
      dashboard.checkJourneyUrl("IM9009999888/sales-to-country/1/2")
      dashboard.enterAnswer("987654.32")

      And("the user confirms the suggested amount of VAT")
      dashboard.checkJourneyUrl("IM9009999888/vat-on-sales/1/2")
      dashboard.clickLink("choice")
      dashboard.continue()

      And("the user answers no on the check-sales page")
      dashboard.checkJourneyUrl("IM9009999888/check-sales/1")
      dashboard.answerRadioButton("no")

      And("the user answers yes on the add-sales-country-list page")
      dashboard.checkJourneyUrl("IM9009999888/add-sales-country-list")
      dashboard.answerRadioButton("yes")

      And("the user selects a country on the sold-to-country page")
      dashboard.checkJourneyUrl("IM9009999888/sold-to-country/2")
      dashboard.selectCountry("Italy")

      And("the user selects the first checkbox on the vat-rates-from-country page")
      dashboard.checkJourneyUrl("IM9009999888/vat-rates-from-country/2")
      dashboard.tickCheckbox("first")
      dashboard.continue()

      And("the user enters the second country total sales for first VAT rate")
      dashboard.checkJourneyUrl("IM9009999888/sales-to-country/2/1")
      dashboard.enterAnswer("1234")

      And("the user enters a different amount of VAT instead of the suggested amount of VAT")
      dashboard.checkJourneyUrl("IM9009999888/vat-on-sales/2/1")
      dashboard.enterAlternativeVatAmount("120.56")

      And("the user answers no on the check-sales page")
      dashboard.checkJourneyUrl("IM9009999888/check-sales/2")
      dashboard.answerRadioButton("no")

      And("the user answers yes on the add-sales-country-list page")
      dashboard.checkJourneyUrl("IM9009999888/add-sales-country-list")
      dashboard.answerRadioButton("yes")

      And("the user selects a country on the sold-to-country page")
      dashboard.checkJourneyUrl("IM9009999888/sold-to-country/3")
      dashboard.selectCountry("Portugal")

      And("the user selects the third and fifth checkbox on the vat-rates-from-country page")
      dashboard.checkJourneyUrl("IM9009999888/vat-rates-from-country/3")
      dashboard.tickCheckbox("third")
      dashboard.tickCheckbox("fifth")
      dashboard.continue()

      And("the user enters the third country total sales for first VAT rate")
      dashboard.checkJourneyUrl("IM9009999888/sales-to-country/3/1")
      dashboard.enterAnswer("123.87")

      And("the user confirms the suggested amount of VAT")
      dashboard.checkJourneyUrl("IM9009999888/vat-on-sales/3/1")
      dashboard.clickLink("choice")
      dashboard.continue()

      And("the user enters the third country total sales for second VAT rate")
      dashboard.checkJourneyUrl("IM9009999888/sales-to-country/3/2")
      dashboard.enterAnswer("6433")

      And("the user enters a different amount of VAT instead of the suggested amount of VAT")
      dashboard.checkJourneyUrl("IM9009999888/vat-on-sales/3/2")
      dashboard.enterAlternativeVatAmount("1500")

      And("the user answers no on the check-sales page")
      dashboard.checkJourneyUrl("IM9009999888/check-sales/3")
      dashboard.answerRadioButton("no")

      And("the user answers no on the add-sales-country-list page")
      dashboard.checkJourneyUrl("IM9009999888/add-sales-country-list")
      dashboard.answerRadioButton("no")

      And("the user submits their return successfully via the check-your-answers page")
      dashboard.checkJourneyUrl("IM9009999888/check-your-answers")
      dashboard.submit()
      dashboard.checkJourneyUrl("IM9009999888/return-successfully-submitted")
    }
  }
}
