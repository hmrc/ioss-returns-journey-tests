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

class ChangeRemoveSpec extends BaseSpec {

  private val dashboard = Dashboard
  private val auth = Auth
  private val fileUpload = FileUpload

  Feature("Change and Remove journeys") {

    Scenario("A user can remove sales to a country in their return") {

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

      And("the user answers yes on the sold-goods page")
      dashboard.checkJourneyUrl("IM9001234567/sold-goods")
      dashboard.answerRadioButton("yes")

      And("the user selects a country on the sold-to-country page")
      dashboard.checkJourneyUrl("IM9001234567/sold-to-country/1")
      dashboard.selectCountry("Poland")

      And("the user selects the first and second checkbox on the vat-rates-from-country page")
      dashboard.checkJourneyUrl("IM9001234567/vat-rates-from-country/1")
      dashboard.tickCheckbox("first")
      dashboard.tickCheckbox("second")
      dashboard.continue()

      And("the user enters the first country total sales for first VAT rate")
      dashboard.checkJourneyUrl("IM9001234567/sales-to-country/1/1")
      dashboard.enterAnswer("10000")

      And("the user confirms the suggested amount of VAT")
      dashboard.checkJourneyUrl("IM9001234567/vat-on-sales/1/1")
      dashboard.clickLink("choice")
      dashboard.continue()

      And("the user enters the first country total sales for second VAT rate")
      dashboard.checkJourneyUrl("IM9001234567/sales-to-country/1/2")
      dashboard.enterAnswer("12345")

      And("the user enters a different amount of VAT instead of the suggested amount of VAT")
      dashboard.checkJourneyUrl("IM9001234567/vat-on-sales/1/2")
      dashboard.enterAlternativeVatAmount("10500")

      And("the user answers no on the check-sales page")
      dashboard.checkJourneyUrl("IM9001234567/check-sales/1")
      dashboard.answerRadioButton("no")

      And("the user answers yes on the add-sales-country-list page")
      dashboard.checkJourneyUrl("IM9001234567/add-sales-country-list")
      dashboard.answerRadioButton("yes")

      And("the user selects a country on the sold-to-country page")
      dashboard.checkJourneyUrl("IM9001234567/sold-to-country/2")
      dashboard.selectCountry("Germany")

      And("the user selects the second checkbox on the vat-rates-from-country page")
      dashboard.checkJourneyUrl("IM9001234567/vat-rates-from-country/2")
      dashboard.tickCheckbox("second")
      dashboard.continue()

      And("the user enters the second country total sales for first VAT rate")
      dashboard.checkJourneyUrl("IM9001234567/sales-to-country/2/1")
      dashboard.enterAnswer("3210")

      And("the user confirms the suggested amount of VAT")
      dashboard.checkJourneyUrl("IM9001234567/vat-on-sales/2/1")
      dashboard.clickLink("choice")
      dashboard.continue()

      And("the user answers no on the check-sales page")
      dashboard.checkJourneyUrl("IM9001234567/check-sales/2")
      dashboard.answerRadioButton("no")

      When("the user selects the remove link for the first country")
      dashboard.checkJourneyUrl("IM9001234567/add-sales-country-list")
      dashboard.selectChangeOrRemoveLink("IM9001234567\\/remove-sales-country\\/1")

      Then("the user answers yes on the remove-sales-country page")
      dashboard.checkJourneyUrl("IM9001234567/remove-sales-country/1")
      dashboard.answerRadioButton("yes")

      And("the user answers yes on the add-sales-country-list page")
      dashboard.checkJourneyUrl("IM9001234567/add-sales-country-list")
      dashboard.answerRadioButton("yes")

      And("the user selects a country on the sold-to-country page")
      dashboard.checkJourneyUrl("IM9001234567/sold-to-country/2")
      dashboard.selectCountry("Spain")

      And("the user selects the first checkbox on the vat-rates-from-country page")
      dashboard.checkJourneyUrl("IM9001234567/vat-rates-from-country/2")
      dashboard.tickCheckbox("first")
      dashboard.continue()

      And("the user enters the second country total sales for first VAT rate")
      dashboard.checkJourneyUrl("IM9001234567/sales-to-country/2/1")
      dashboard.enterAnswer("6547")

      And("the user confirms the suggested amount of VAT")
      dashboard.checkJourneyUrl("IM9001234567/vat-on-sales/2/1")
      dashboard.clickLink("choice")
      dashboard.continue()

      And("the user answers no on the check-sales page")
      dashboard.checkJourneyUrl("IM9001234567/check-sales/2")
      dashboard.answerRadioButton("no")

      And("the user answers yes on the add-sales-country-list page")
      dashboard.checkJourneyUrl("IM9001234567/add-sales-country-list")
      dashboard.answerRadioButton("yes")

      And("the user selects a country on the sold-to-country page")
      dashboard.checkJourneyUrl("IM9001234567/sold-to-country/3")
      dashboard.selectCountry("Slovenia")

      And("the user selects the first checkbox on the vat-rates-from-country page")
      dashboard.checkJourneyUrl("IM9001234567/vat-rates-from-country/3")
      dashboard.tickCheckbox("first")
      dashboard.continue()

      And("the user enters the second country total sales for first VAT rate")
      dashboard.checkJourneyUrl("IM9001234567/sales-to-country/3/1")
      dashboard.enterAnswer("9999")

      And("the user confirms the suggested amount of VAT")
      dashboard.checkJourneyUrl("IM9001234567/vat-on-sales/3/1")
      dashboard.clickLink("choice")
      dashboard.continue()

      And("the user answers no on the check-sales page")
      dashboard.checkJourneyUrl("IM9001234567/check-sales/3")
      dashboard.answerRadioButton("no")

      And("the user answers no on the add-sales-country-list page")
      dashboard.checkJourneyUrl("IM9001234567/add-sales-country-list")
      dashboard.answerRadioButton("no")

      And("the user answers no on the correct-previous-return page")
      dashboard.checkJourneyUrl("IM9001234567/correct-previous-return")
      dashboard.answerRadioButton("no")

      And("the user submits their return successfully via the check-your-answers page")
      dashboard.checkJourneyUrl("IM9001234567/check-your-answers")
      dashboard.submit()
      dashboard.checkJourneyUrl("IM9001234567/return-successfully-submitted")
    }

    Scenario("A user can add some sales then remove them all in their return") {

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

      And("the user adds sales for two countries")
      dashboard.checkJourneyUrl("IM9001234567/sold-goods")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234567/sold-to-country/1")
      dashboard.selectCountry("Czech Republic")
      dashboard.checkJourneyUrl("IM9001234567/vat-rates-from-country/1")
      dashboard.tickCheckbox("first")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9001234567/sales-to-country/1/1")
      dashboard.enterAnswer("5544")
      dashboard.checkJourneyUrl("IM9001234567/vat-on-sales/1/1")
      dashboard.clickLink("choice")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9001234567/check-sales/1")
      dashboard.answerRadioButton("no")
      dashboard.checkJourneyUrl("IM9001234567/add-sales-country-list")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234567/sold-to-country/2")
      dashboard.selectCountry("Lithuania")
      dashboard.checkJourneyUrl("IM9001234567/vat-rates-from-country/2")
      dashboard.tickCheckbox("first")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9001234567/sales-to-country/2/1")
      dashboard.enterAnswer("11111")
      dashboard.checkJourneyUrl("IM9001234567/vat-on-sales/2/1")
      dashboard.clickLink("choice")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9001234567/check-sales/2")
      dashboard.answerRadioButton("no")

      When("the user is on the country list")
      dashboard.checkJourneyUrl("IM9001234567/add-sales-country-list")

      Then("the user can remove the sales data for the second country")
      dashboard.selectChangeOrRemoveLink("IM9001234567\\/remove-sales-country\\/2")
      dashboard.checkJourneyUrl("IM9001234567/remove-sales-country/2")
      dashboard.answerRadioButton("yes")

      When("the user is on the country list")
      dashboard.checkJourneyUrl("IM9001234567/add-sales-country-list")

      Then("the user can remove the sales data for the first country")
      dashboard.selectChangeOrRemoveLink("IM9001234567\\/remove-sales-country\\/1")
      dashboard.checkJourneyUrl("IM9001234567/remove-sales-country/1")
      dashboard.answerRadioButton("yes")

      And("the user answers no on the sold-goods page")
      dashboard.checkJourneyUrl("IM9001234567/sold-goods")
      dashboard.answerRadioButton("no")

      And("the user answers no on the correct-previous-return page")
      dashboard.checkJourneyUrl("IM9001234567/correct-previous-return")
      dashboard.answerRadioButton("no")

      And("the user submits their return successfully via the check-your-answers page")
      dashboard.checkJourneyUrl("IM9001234567/check-your-answers")
      dashboard.submit()
      dashboard.checkJourneyUrl("IM9001234567/return-successfully-submitted")
    }

    Scenario("A user can change and remove answers via the mini CYA for VAT rates") {

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

      And("the user adds sales for two countries")
      dashboard.checkJourneyUrl("IM9001234567/sold-goods")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234567/sold-to-country/1")
      dashboard.selectCountry("Denmark")
      dashboard.checkJourneyUrl("IM9001234567/sales-to-country/1/1")
      dashboard.enterAnswer("10000")
      dashboard.checkJourneyUrl("IM9001234567/vat-on-sales/1/1")
      dashboard.clickLink("choice")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9001234567/check-sales/1")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9001234567/add-sales-country-list")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234567/sold-to-country/2")
      dashboard.selectCountry("Croatia")
      dashboard.checkJourneyUrl("IM9001234567/vat-rates-from-country/2")
      dashboard.tickCheckbox("second")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9001234567/sales-to-country/2/1")
      dashboard.enterAnswer("6321")
      dashboard.checkJourneyUrl("IM9001234567/vat-on-sales/2/1")
      dashboard.enterAlternativeVatAmount("600")

      When("the user is on the check-sales page for Croatia")
      dashboard.checkJourneyUrl("IM9001234567/check-sales/2")

      Then("the user clicks change and amends the vat from a different amount to the suggested amount")
      dashboard.selectChangeOrRemoveLink("IM9001234567\\/vat-on-sales\\/2\\/1\\?waypoints\\=IM9001234567-change-check-sales-2")
      dashboard.checkJourneyUrl("IM9001234567/vat-on-sales/2/1?waypoints=IM9001234567-change-check-sales-2")
      dashboard.clickLink("choice")
      dashboard.continue()

      And("the user answers yes on the check-sales page for Croatia and adds another VAT rate")
      dashboard.checkJourneyUrl("IM9001234567/check-sales/2")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234567/vat-rates-from-country/2")
      dashboard.tickCheckbox("third")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9001234567/sales-to-country/2/2")
      dashboard.enterAnswer("123.11")
      dashboard.checkJourneyUrl("IM9001234567/vat-on-sales/2/2")
      dashboard.clickLink("choice")
      dashboard.continue()

      When("the user is on the check-sales page for Croatia")
      dashboard.checkJourneyUrl("IM9001234567/check-sales/2")

      Then("the user clicks change and amends the sales amount for the second VAT rate")
      dashboard.selectChangeOrRemoveLink("IM9001234567\\/sales-to-country\\/2\\/2\\?waypoints\\=IM9001234567-change-check-sales-2")
      dashboard.checkJourneyUrl("IM9001234567/sales-to-country/2/2?waypoints=IM9001234567-change-check-sales-2")
      dashboard.enterAnswer("641")
      dashboard.checkJourneyUrl("IM9001234567/vat-on-sales/2/2?waypoints=IM9001234567-change-check-sales-2")
      dashboard.clickLink("choice")
      dashboard.continue()

      And("the user answers yes on the check-sales page for Croatia to add the final VAT rate")
      dashboard.checkJourneyUrl("IM9001234567/check-sales/2")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234567/remaining-vat-rate-from-country/2/3")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234567/sales-to-country/2/3")
      dashboard.enterAnswer("1400")
      dashboard.checkJourneyUrl("IM9001234567/vat-on-sales/2/3")
      dashboard.clickLink("choice")
      dashboard.continue()

      And("the user clicks continue on the check-sales page for Croatia")
      dashboard.checkJourneyUrl("IM9001234567/check-sales/2")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9001234567/add-sales-country-list")

      When("the user selects change on Croatia")
      dashboard.selectChangeOrRemoveLink("IM9001234567\\/check-sales\\/2\\?waypoints\\=IM9001234567-change-add-sales-country-list")
      dashboard.checkJourneyUrl("IM9001234567/check-sales/2?waypoints=IM9001234567-change-add-sales-country-list")

      Then("the user removes the second VAT rate")
      dashboard.selectChangeOrRemoveLink("IM9001234567\\/remove-vat-rate-sales-for-country\\/2\\/2\\?waypoints\\=IM9001234567-change-add-sales-country-list")
      dashboard.checkJourneyUrl("IM9001234567/remove-vat-rate-sales-for-country/2/2?waypoints=IM9001234567-change-add-sales-country-list")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234567/check-sales/2?waypoints=IM9001234567-change-add-sales-country-list")

      And("the user selects change on the second VAT rate to enter a different amount of VAT")
      dashboard.selectChangeOrRemoveLink("IM9001234567\\/vat-on-sales\\/2\\/2\\?waypoints\\=IM9001234567-change-check-sales-2\\%2CIM9001234567-change-add-sales-country-list")
      dashboard.checkJourneyUrl("IM9001234567/vat-on-sales/2/2?waypoints=IM9001234567-change-check-sales-2%2CIM9001234567-change-add-sales-country-list")
      dashboard.enterAlternativeVatAmount("250.11")
      dashboard.checkJourneyUrl("IM9001234567/check-sales/2?waypoints=IM9001234567-change-add-sales-country-list")
      dashboard.answerRadioButton("no")
      dashboard.checkJourneyUrl("IM9001234567/add-sales-country-list")
      dashboard.answerRadioButton("no")

      And("the user answers no on the correct-previous-return page")
      dashboard.checkJourneyUrl("IM9001234567/correct-previous-return")
      dashboard.answerRadioButton("no")

      And("the user submits their return successfully via the check-your-answers page")
      dashboard.checkJourneyUrl("IM9001234567/check-your-answers")
      dashboard.submit()
      dashboard.checkJourneyUrl("IM9001234567/return-successfully-submitted")
    }
  }
}
