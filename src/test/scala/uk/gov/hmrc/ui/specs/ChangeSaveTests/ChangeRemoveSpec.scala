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

class ChangeRemoveSpec extends BaseSpec {

  private val dashboard  = Dashboard
  private val auth       = Auth
  private val fileUpload = FileUpload
  private val correction = Correction

  private val lastYear    = LocalDate.now().minusYears(1).getYear
  private val twoYearsAgo = LocalDate.now().minusYears(2).getYear

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
      dashboard.selectChangeOrRemoveLink(
        "IM9001234567\\/vat-on-sales\\/2\\/1\\?waypoints\\=IM9001234567-change-check-sales-2"
      )
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
      dashboard.selectChangeOrRemoveLink(
        "IM9001234567\\/sales-to-country\\/2\\/2\\?waypoints\\=IM9001234567-change-check-sales-2"
      )
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
      dashboard.selectChangeOrRemoveLink(
        "IM9001234567\\/check-sales\\/2\\?waypoints\\=IM9001234567-change-add-sales-country-list"
      )
      dashboard.checkJourneyUrl("IM9001234567/check-sales/2?waypoints=IM9001234567-change-add-sales-country-list")

      Then("the user removes the second VAT rate")
      dashboard.selectChangeOrRemoveLink(
        "IM9001234567\\/remove-vat-rate-sales-for-country\\/2\\/2\\?waypoints\\=IM9001234567-change-add-sales-country-list"
      )
      dashboard.checkJourneyUrl(
        "IM9001234567/remove-vat-rate-sales-for-country/2/2?waypoints=IM9001234567-change-add-sales-country-list"
      )
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234567/check-sales/2?waypoints=IM9001234567-change-add-sales-country-list")

      And("the user selects change on the second VAT rate to enter a different amount of VAT")
      dashboard.selectChangeOrRemoveLink(
        "IM9001234567\\/vat-on-sales\\/2\\/2\\?waypoints\\=IM9001234567-change-check-sales-2\\%2CIM9001234567-change-add-sales-country-list"
      )
      dashboard.checkJourneyUrl(
        "IM9001234567/vat-on-sales/2/2?waypoints=IM9001234567-change-check-sales-2%2CIM9001234567-change-add-sales-country-list"
      )
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

    Scenario("A user can remove all answers via the mini CYA for VAT rates") {

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

      And("the user adds sales across multiple VAT rates for Croatia")
      dashboard.checkJourneyUrl("IM9001234567/sold-goods")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234567/sold-to-country/1")
      dashboard.selectCountry("Croatia")
      dashboard.checkJourneyUrl("IM9001234567/vat-rates-from-country/1")
      dashboard.tickCheckbox("first")
      dashboard.tickCheckbox("second")
      dashboard.tickCheckbox("third")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9001234567/sales-to-country/1/1")
      dashboard.enterAnswer("10000")
      dashboard.checkJourneyUrl("IM9001234567/vat-on-sales/1/1")
      dashboard.clickLink("choice")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9001234567/sales-to-country/1/2")
      dashboard.enterAnswer("12345.23")
      dashboard.checkJourneyUrl("IM9001234567/vat-on-sales/1/2")
      dashboard.enterAlternativeVatAmount("10500.21")
      dashboard.checkJourneyUrl("IM9001234567/sales-to-country/1/3")
      dashboard.enterAnswer("98765")
      dashboard.checkJourneyUrl("IM9001234567/vat-on-sales/1/3")
      dashboard.clickLink("choice")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9001234567/check-sales/1")

      Then("the user removes the second VAT rate")
      dashboard.selectChangeOrRemoveLink("IM9001234567\\/remove-vat-rate-sales-for-country\\/1\\/2")
      dashboard.checkJourneyUrl("IM9001234567/remove-vat-rate-sales-for-country/1/2")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234567/check-sales/1")

      Then("the user removes the first VAT rate")
      dashboard.selectChangeOrRemoveLink("IM9001234567\\/remove-vat-rate-sales-for-country\\/1\\/1")
      dashboard.checkJourneyUrl("IM9001234567/remove-vat-rate-sales-for-country/1/1")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234567/check-sales/1")

      Then("the user removes the final VAT rate")
      dashboard.selectChangeOrRemoveLink("IM9001234567\\/remove-vat-rate-sales-for-country\\/1\\/1")
      dashboard.checkJourneyUrl("IM9001234567/remove-vat-rate-sales-for-country/1/1")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234567/vat-rates-from-country/1")

      And("the user selects the third vat rate on the vat-rates-from-country page")
      dashboard.tickCheckbox("third")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9001234567/sales-to-country/1/1")
      dashboard.enterAnswer("2000")
      dashboard.checkJourneyUrl("IM9001234567/vat-on-sales/1/1")
      dashboard.clickLink("choice")
      dashboard.continue()

      And("the user selects no on the check-sales page")
      dashboard.checkJourneyUrl("IM9001234567/check-sales/1")
      dashboard.answerRadioButton("no")

      And("the user selects no on the add-sales-country-list page")
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

    Scenario("A user can change answers via the country list in their return") {

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

      And("the user adds sales for Finland")
      dashboard.checkJourneyUrl("IM9001234567/sold-goods")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234567/sold-to-country/1")
      dashboard.selectCountry("Finland")
      dashboard.checkJourneyUrl("IM9001234567/vat-rates-from-country/1")
      dashboard.tickCheckbox("second")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9001234567/sales-to-country/1/1")
      dashboard.enterAnswer("1234")
      dashboard.checkJourneyUrl("IM9001234567/vat-on-sales/1/1")
      dashboard.clickLink("choice")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9001234567/check-sales/1")
      dashboard.answerRadioButton("no")
      dashboard.checkJourneyUrl("IM9001234567/add-sales-country-list")

      Then("the user clicks change on Finland to go back and add another VAT rate")
      dashboard.selectChangeOrRemoveLink(
        "IM9001234567\\/check-sales\\/1\\?waypoints\\=IM9001234567-change-add-sales-country-list"
      )
      dashboard.checkJourneyUrl("IM9001234567/check-sales/1?waypoints=IM9001234567-change-add-sales-country-list")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl(
        "IM9001234567/vat-rates-from-country/1?waypoints=IM9001234567-change-add-sales-country-list"
      )
      dashboard.tickCheckbox("first")
      dashboard.continue()
      dashboard.checkJourneyUrl(
        "IM9001234567/sales-to-country/1/2?waypoints=IM9001234567-change-add-sales-country-list"
      )
      dashboard.enterAnswer("2222.33")
      dashboard.checkJourneyUrl("IM9001234567/vat-on-sales/1/2?waypoints=IM9001234567-change-add-sales-country-list")
      dashboard.clickLink("choice")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9001234567/check-sales/1?waypoints=IM9001234567-change-add-sales-country-list")
      dashboard.answerRadioButton("no")
      dashboard.checkJourneyUrl("IM9001234567/add-sales-country-list")

      And("the user adds sales for Malta")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234567/sold-to-country/2")
      dashboard.selectCountry("Malta")
      dashboard.checkJourneyUrl("IM9001234567/vat-rates-from-country/2")
      dashboard.tickCheckbox("second")
      dashboard.tickCheckbox("third")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9001234567/sales-to-country/2/1")
      dashboard.enterAnswer("3333")
      dashboard.checkJourneyUrl("IM9001234567/vat-on-sales/2/1")
      dashboard.clickLink("choice")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9001234567/sales-to-country/2/2")
      dashboard.enterAnswer("65400")
      dashboard.checkJourneyUrl("IM9001234567/vat-on-sales/2/2")
      dashboard.enterAlternativeVatAmount("10500")
      dashboard.checkJourneyUrl("IM9001234567/check-sales/2")
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

    Scenario("A user can change answers via the correction country list - previously undeclared") {

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

      And("the user picks November on the correction-return-month page")
      dashboard.checkJourneyUrl("IM9001234567/correction-return-month/1")
      dashboard.clickLink("value_November")
      dashboard.continue()

      And("the user selects a country")
      dashboard.checkJourneyUrl("IM9001234567/correction-country/1/1")
      dashboard.selectCountry("Spain")

      And("the user answers yes on the add-new-country page")
      dashboard.checkJourneyUrl("IM9001234567/add-new-country/1/1")
      dashboard.answerRadioButton("yes")

      And("the user enters the correction amount")
      dashboard.checkJourneyUrl("IM9001234567/country-vat-correction-amount/1/1")
      dashboard.enterAnswer("11111")

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
      dashboard.selectCountry("Slovakia")

      And("the user answers yes on the add-new-country page")
      dashboard.checkJourneyUrl("IM9001234567/add-new-country/1/2")
      dashboard.answerRadioButton("yes")

      And("the user enters the correction amount")
      dashboard.checkJourneyUrl("IM9001234567/country-vat-correction-amount/1/2")
      dashboard.enterAnswer("1234.56")

      And("the user answers yes on the vat-payable-confirm page")
      dashboard.checkJourneyUrl("IM9001234567/vat-payable-confirm/1/2")
      dashboard.answerRadioButton("yes")

      And("the user clicks continue on the vat-payable-check page")
      dashboard.checkJourneyUrl("IM9001234567/vat-payable-check/1/2")
      dashboard.continue()

      Then("the user changes the correction amount for Spain")
      dashboard.checkJourneyUrl("IM9001234567/correction-list-countries/1")
      dashboard.selectChangeOrRemoveLink(
        "IM9001234567\\/country-vat-correction-amount\\/1\\/1\\?waypoints\\=IM9001234567-change-add-correction-list-countries-1"
      )
      dashboard.checkJourneyUrl(
        "IM9001234567/country-vat-correction-amount/1/1?waypoints=IM9001234567-change-add-correction-list-countries-1"
      )
      dashboard.enterAnswer("1111")
      dashboard.checkJourneyUrl(
        "IM9001234567/vat-payable-confirm/1/1?waypoints=IM9001234567-add-correction-list-countries-1"
      )
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl(
        "IM9001234567/vat-payable-check/1/1?waypoints=IM9001234567-add-correction-list-countries-1"
      )
      dashboard.continue()
      dashboard.checkJourneyUrl(
        "IM9001234567/correction-list-countries/1?waypoints=IM9001234567-add-correction-list-countries-1"
      )

      And("the user adds a third country")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl(
        "IM9001234567/correction-country/1/3?waypoints=IM9001234567-add-correction-list-countries-1"
      )
      dashboard.selectCountry("Denmark")
      dashboard.checkJourneyUrl(
        "IM9001234567/add-new-country/1/3?waypoints=IM9001234567-add-correction-list-countries-1"
      )
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl(
        "IM9001234567/country-vat-correction-amount/1/3?waypoints=IM9001234567-add-correction-list-countries-1"
      )
      dashboard.enterAnswer("1254.01")
      dashboard.checkJourneyUrl(
        "IM9001234567/vat-payable-confirm/1/3?waypoints=IM9001234567-add-correction-list-countries-1"
      )
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl(
        "IM9001234567/vat-payable-check/1/3?waypoints=IM9001234567-add-correction-list-countries-1"
      )
      dashboard.continue()

      And("the user answers no on the correction-list-countries page")
      dashboard.checkJourneyUrl(
        "IM9001234567/correction-list-countries/1?waypoints=IM9001234567-add-correction-list-countries-1"
      )
      dashboard.answerRadioButton("no")

      And("the user clicks no on the vat-correction-months-add page")
      dashboard.checkJourneyUrl(
        "IM9001234567/2023-M12/vat-correction-months-add?waypoints=IM9001234567-add-correction-list-countries-1"
      )
      dashboard.answerRadioButton("no")

      And("the user submits their return successfully via the check-your-answers page")
      dashboard.checkJourneyUrl(
        "IM9001234567/check-your-answers?waypoints=IM9001234567-add-correction-list-countries-1"
      )
      dashboard.submit()
      dashboard.checkJourneyUrl("IM9001234567/return-successfully-submitted")
    }

    Scenario("A user can remove all answers via the correction country list - previously undeclared") {

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
      dashboard.selectCountry("Czech Republic")

      And("the user answers yes on the add-new-country page")
      dashboard.checkJourneyUrl("IM9001234567/add-new-country/1/1")
      dashboard.answerRadioButton("yes")

      And("the user enters the correction amount")
      dashboard.checkJourneyUrl("IM9001234567/country-vat-correction-amount/1/1")
      dashboard.enterAnswer("12345")

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
      dashboard.selectCountry("Cyprus")

      And("the user answers yes on the add-new-country page")
      dashboard.checkJourneyUrl("IM9001234567/add-new-country/1/2")
      dashboard.answerRadioButton("yes")

      And("the user enters the correction amount")
      dashboard.checkJourneyUrl("IM9001234567/country-vat-correction-amount/1/2")
      dashboard.enterAnswer("6543.21")

      And("the user answers yes on the vat-payable-confirm page")
      dashboard.checkJourneyUrl("IM9001234567/vat-payable-confirm/1/2")
      dashboard.answerRadioButton("yes")

      And("the user clicks continue on the vat-payable-check page")
      dashboard.checkJourneyUrl("IM9001234567/vat-payable-check/1/2")
      dashboard.continue()

      Then("the user removes the correction for Cyprus")
      dashboard.checkJourneyUrl("IM9001234567/correction-list-countries/1")
      dashboard.selectChangeOrRemoveLink(
        "IM9001234567\\/remove-country-correction\\/1\\/2"
      )
      dashboard.checkJourneyUrl(
        "IM9001234567/remove-country-correction/1/2"
      )
      correction.removeCorrectionCountry("Cyprus")
      dashboard.answerRadioButton("yes")

      Then("the user removes the correction for Czech Republic")
      dashboard.checkJourneyUrl("IM9001234567/correction-list-countries/1")
      dashboard.selectChangeOrRemoveLink(
        "IM9001234567\\/remove-country-correction\\/1\\/1"
      )
      dashboard.checkJourneyUrl(
        "IM9001234567/remove-country-correction/1/1"
      )
      correction.removeCorrectionCountry("Czech Republic")
      dashboard.answerRadioButton("yes")

      And("the user answers no on the correct-previous-return page")
      dashboard.checkJourneyUrl("IM9001234567/correct-previous-return")
      dashboard.answerRadioButton("no")

      And("the user submits their return successfully via the check-your-answers page")
      dashboard.checkJourneyUrl(
        "IM9001234567/check-your-answers"
      )
      dashboard.submit()
      dashboard.checkJourneyUrl("IM9001234567/return-successfully-submitted")
    }

    Scenario("A user can remove all answers via the correction periods list for same year") {

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

      And("the user adds a correction for Czech Republic")
      dashboard.checkJourneyUrl("IM9001234568/correction-country/1/1")
      dashboard.selectCountry("Czech Republic")
      dashboard.checkJourneyUrl("IM9001234568/add-new-country/1/1")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234568/country-vat-correction-amount/1/1")
      dashboard.enterAnswer("12345")
      dashboard.checkJourneyUrl("IM9001234568/vat-payable-confirm/1/1")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234568/vat-payable-check/1/1")
      dashboard.continue()

      And("the user answers yes on the correction-list-countries page")
      dashboard.checkJourneyUrl("IM9001234568/correction-list-countries/1")
      dashboard.answerRadioButton("yes")

      And("the user adds a correction for Cyprus")
      dashboard.checkJourneyUrl("IM9001234568/correction-country/1/2")
      dashboard.selectCountry("Cyprus")
      dashboard.checkJourneyUrl("IM9001234568/add-new-country/1/2")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234568/country-vat-correction-amount/1/2")
      dashboard.enterAnswer("6543.21")
      dashboard.checkJourneyUrl("IM9001234568/vat-payable-confirm/1/2")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234568/vat-payable-check/1/2")
      dashboard.continue()

      And("the user answers no on the correction-list-countries page")
      dashboard.checkJourneyUrl("IM9001234568/correction-list-countries/1")
      dashboard.answerRadioButton("no")

      And("the user answers yes on the vat-correction-months-add page")
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

      And("the user adds a correction for Bulgaria")
      dashboard.checkJourneyUrl("IM9001234568/correction-country/2/1")
      dashboard.selectCountry("Bulgaria")
      dashboard.checkJourneyUrl("IM9001234568/add-new-country/2/1")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234568/country-vat-correction-amount/2/1")
      dashboard.enterAnswer("100.25")
      dashboard.checkJourneyUrl("IM9001234568/vat-payable-confirm/2/1")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234568/vat-payable-check/2/1")
      dashboard.continue()

      And("the user answers yes on the correction-list-countries page")
      dashboard.checkJourneyUrl("IM9001234568/correction-list-countries/2")
      dashboard.answerRadioButton("yes")

      And("the user adds a correction for Slovakia")
      dashboard.checkJourneyUrl("IM9001234568/correction-country/2/2")
      dashboard.selectCountry("Slovakia")
      dashboard.checkJourneyUrl("IM9001234568/add-new-country/2/2")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234568/country-vat-correction-amount/2/2")
      dashboard.enterAnswer("1450")
      dashboard.checkJourneyUrl("IM9001234568/vat-payable-confirm/2/2")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234568/vat-payable-check/2/2")
      dashboard.continue()

      And("the user answers no on the correction-list-countries page")
      dashboard.checkJourneyUrl("IM9001234568/correction-list-countries/2")
      dashboard.answerRadioButton("no")

      Then("the user clicks remove on the November corrections")
      dashboard.checkJourneyUrl("IM9001234568/2024-M1/vat-correction-months-add")
      dashboard.selectChangeOrRemoveLink("IM9001234568\\/remove-month-correction\\/2")
      dashboard.checkJourneyUrl("IM9001234568/remove-month-correction/2")
      correction.removeCorrectionPeriod("November", "2023")
      dashboard.answerRadioButton("yes")

      Then("the user clicks remove on the October corrections")
      dashboard.checkJourneyUrl("IM9001234568/2024-M1/vat-correction-months-add")
      correction.oneCorrectionMonth("October 2023")
      dashboard.selectChangeOrRemoveLink("IM9001234568\\/remove-month-correction\\/1")
      dashboard.checkJourneyUrl("IM9001234568/remove-month-correction/1")
      correction.removeCorrectionPeriod("October", "2023")
      dashboard.answerRadioButton("yes")

      And("the user answers no on the correct-previous-return page")
      dashboard.checkJourneyUrl("IM9001234568/correct-previous-return")
      dashboard.answerRadioButton("no")

      And("the user submits their return successfully via the check-your-answers page")
      dashboard.checkJourneyUrl(
        "IM9001234568/check-your-answers"
      )
      dashboard.submit()
      dashboard.checkJourneyUrl("IM9001234568/return-successfully-submitted")
    }

    Scenario("A user can change and remove answers via the correction periods list for same year") {

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

      And("the user adds a correction for Czech Republic")
      dashboard.checkJourneyUrl("IM9001234568/correction-country/1/1")
      dashboard.selectCountry("Czech Republic")
      dashboard.checkJourneyUrl("IM9001234568/add-new-country/1/1")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234568/country-vat-correction-amount/1/1")
      dashboard.enterAnswer("12345")
      dashboard.checkJourneyUrl("IM9001234568/vat-payable-confirm/1/1")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234568/vat-payable-check/1/1")
      dashboard.continue()

      And("the user answers yes on the correction-list-countries page")
      dashboard.checkJourneyUrl("IM9001234568/correction-list-countries/1")
      dashboard.answerRadioButton("yes")

      And("the user adds a correction for Cyprus")
      dashboard.checkJourneyUrl("IM9001234568/correction-country/1/2")
      dashboard.selectCountry("Cyprus")
      dashboard.checkJourneyUrl("IM9001234568/add-new-country/1/2")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234568/country-vat-correction-amount/1/2")
      dashboard.enterAnswer("6543.21")
      dashboard.checkJourneyUrl("IM9001234568/vat-payable-confirm/1/2")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234568/vat-payable-check/1/2")
      dashboard.continue()

      And("the user answers no on the correction-list-countries page")
      dashboard.checkJourneyUrl("IM9001234568/correction-list-countries/1")
      dashboard.answerRadioButton("no")

      And("the user answers yes on the vat-correction-months-add page")
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

      And("the user adds a correction for Bulgaria")
      dashboard.checkJourneyUrl("IM9001234568/correction-country/2/1")
      dashboard.selectCountry("Bulgaria")
      dashboard.checkJourneyUrl("IM9001234568/add-new-country/2/1")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234568/country-vat-correction-amount/2/1")
      dashboard.enterAnswer("100.25")
      dashboard.checkJourneyUrl("IM9001234568/vat-payable-confirm/2/1")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234568/vat-payable-check/2/1")
      dashboard.continue()

      And("the user answers yes on the correction-list-countries page")
      dashboard.checkJourneyUrl("IM9001234568/correction-list-countries/2")
      dashboard.answerRadioButton("yes")

      And("the user adds a correction for Slovakia")
      dashboard.checkJourneyUrl("IM9001234568/correction-country/2/2")
      dashboard.selectCountry("Slovakia")
      dashboard.checkJourneyUrl("IM9001234568/add-new-country/2/2")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234568/country-vat-correction-amount/2/2")
      dashboard.enterAnswer("1450")
      dashboard.checkJourneyUrl("IM9001234568/vat-payable-confirm/2/2")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234568/vat-payable-check/2/2")
      dashboard.continue()

      And("the user answers no on the correction-list-countries page")
      dashboard.checkJourneyUrl("IM9001234568/correction-list-countries/2")
      dashboard.answerRadioButton("no")

      Then("the user clicks remove on the November corrections")
      dashboard.checkJourneyUrl("IM9001234568/2024-M1/vat-correction-months-add")
      dashboard.selectChangeOrRemoveLink("IM9001234568\\/remove-month-correction\\/2")
      dashboard.checkJourneyUrl("IM9001234568/remove-month-correction/2")
      correction.removeCorrectionPeriod("November", "2023")
      dashboard.answerRadioButton("yes")

      Then("there is only one period with corrections")
      dashboard.checkJourneyUrl("IM9001234568/2024-M1/vat-correction-months-add")
      correction.oneCorrectionMonth("October 2023")

      And("the user answers yes on the vat-corrections-months-add page")
      dashboard.answerRadioButton("yes")

      And("the user picks 2023 on the correction-return-year page")
      dashboard.checkJourneyUrl("IM9001234568/correction-return-year/2")
      dashboard.clickLink("value_2023")
      dashboard.continue()

      And("the user picks December on the correction-return-month page")
      dashboard.checkJourneyUrl("IM9001234568/correction-return-month/2")
      dashboard.clickLink("value_December")
      dashboard.continue()

      And("the user adds a correction for Poland")
      dashboard.checkJourneyUrl("IM9001234568/correction-country/2/1")
      dashboard.selectCountry("Poland")
      dashboard.checkJourneyUrl("IM9001234568/add-new-country/2/1")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234568/country-vat-correction-amount/2/1")
      dashboard.enterAnswer("12345.67")
      dashboard.checkJourneyUrl("IM9001234568/vat-payable-confirm/2/1")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234568/vat-payable-check/2/1")
      dashboard.continue()

      And("the user answers yes on the correction-list-countries page")
      dashboard.checkJourneyUrl("IM9001234568/correction-list-countries/2")
      dashboard.answerRadioButton("yes")

      And("the user adds a correction for Portugal")
      dashboard.checkJourneyUrl("IM9001234568/correction-country/2/2")
      dashboard.selectCountry("Portugal")
      dashboard.checkJourneyUrl("IM9001234568/add-new-country/2/2")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234568/country-vat-correction-amount/2/2")
      dashboard.enterAnswer("1000")
      dashboard.checkJourneyUrl("IM9001234568/vat-payable-confirm/2/2")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234568/vat-payable-check/2/2")
      dashboard.continue()

      And("the user answers no on the correction-list-countries page")
      dashboard.checkJourneyUrl("IM9001234568/correction-list-countries/2")
      dashboard.answerRadioButton("no")

      Then("the user adds another country to the first period with corrections")
      dashboard.checkJourneyUrl("IM9001234568/2024-M1/vat-correction-months-add")
      dashboard.selectChangeOrRemoveLink("IM9001234568\\/correction-list-countries\\/1")
      dashboard.checkJourneyUrl("IM9001234568/correction-list-countries/1")
      dashboard.answerRadioButton("yes")

      And("the user adds a correction for Spain")
      dashboard.checkJourneyUrl("IM9001234568/correction-country/1/3")
      dashboard.selectCountry("Spain")
      dashboard.checkJourneyUrl("IM9001234568/add-new-country/1/3")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234568/country-vat-correction-amount/1/3")
      dashboard.enterAnswer("1500.01")
      dashboard.checkJourneyUrl("IM9001234568/vat-payable-confirm/1/3")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234568/vat-payable-check/1/3")
      dashboard.continue()

      And("the user answers no on the correction-list-countries page")
      dashboard.checkJourneyUrl("IM9001234568/correction-list-countries/1")
      dashboard.answerRadioButton("no")

      And("the user answers no on the vat-correction-months-add page")
      dashboard.checkJourneyUrl("IM9001234568/2024-M1/vat-correction-months-add")
      dashboard.answerRadioButton("no")

      And("the user submits their return successfully via the check-your-answers page")
      dashboard.checkJourneyUrl(
        "IM9001234568/check-your-answers"
      )
      dashboard.submit()
      dashboard.checkJourneyUrl("IM9001234568/return-successfully-submitted")
    }

    Scenario("A user can change and remove answers via the correction periods list for multiple years") {

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

      And("the user adds a correction for October two years ago")
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

      And("the user answers no on the correction-list-countries page")
      dashboard.checkJourneyUrl("IM9001234569/correction-list-countries/1")
      dashboard.answerRadioButton("no")

      And("the user clicks yes on the vat-correction-months-add page")
      dashboard.checkJourneyUrl(s"IM9001234569/$lastYear-M12/vat-correction-months-add")
      dashboard.answerRadioButton("yes")

      And("the user adds a correction for November last year")
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

      And("the user answers no on the correction-list-countries page")
      dashboard.checkJourneyUrl("IM9001234569/correction-list-countries/2")
      dashboard.answerRadioButton("no")

      Then("the user adds another correction to the second correction period")
      dashboard.checkJourneyUrl(s"IM9001234569/$lastYear-M12/vat-correction-months-add")
      dashboard.selectChangeOrRemoveLink("IM9001234569\\/correction-list-countries\\/2")
      dashboard.checkJourneyUrl("IM9001234569/correction-list-countries/2")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234569/correction-country/2/2")
      dashboard.selectCountry("Belgium")
      dashboard.checkJourneyUrl("IM9001234569/add-new-country/2/2")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234569/country-vat-correction-amount/2/2")
      dashboard.enterAnswer("1258.41")
      dashboard.checkJourneyUrl("IM9001234569/vat-payable-confirm/2/2")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234569/vat-payable-check/2/2")
      dashboard.continue()

      And("the user answers no on the correction-list-countries page")
      dashboard.checkJourneyUrl("IM9001234569/correction-list-countries/2")
      dashboard.answerRadioButton("no")

      And("the user answers yes on the vat-correction-months-add page")
      dashboard.checkJourneyUrl(s"IM9001234569/$lastYear-M12/vat-correction-months-add")
      dashboard.answerRadioButton("yes")

      And("the user adds a correction for December two years ago")
      dashboard.checkJourneyUrl("IM9001234569/correction-return-year/3")
      dashboard.clickLink(s"value_$twoYearsAgo")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9001234569/correction-return-month/3")
      dashboard.clickLink("value_December")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9001234569/correction-country/3/1")
      dashboard.selectCountry("France")
      dashboard.checkJourneyUrl("IM9001234569/country-vat-correction-amount/3/1")
      dashboard.enterAnswer("1234")
      dashboard.checkJourneyUrl("IM9001234569/vat-payable-confirm/3/1")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234569/vat-payable-check/3/1")
      dashboard.continue()

      And("the user answers no on the correction-list-countries page")
      dashboard.checkJourneyUrl("IM9001234569/correction-list-countries/3")
      dashboard.answerRadioButton("no")

      Then("the user removes the correction for the first month")
      dashboard.checkJourneyUrl(s"IM9001234569/$lastYear-M12/vat-correction-months-add")
      dashboard.selectChangeOrRemoveLink("IM9001234569\\/remove-month-correction\\/1")
      dashboard.checkJourneyUrl("IM9001234569/remove-month-correction/1")
      correction.removeCorrectionPeriod("October", "twoYearsAgo")
      dashboard.answerRadioButton("yes")

      And("the user answers no on the vat-correction-months-add page")
      dashboard.checkJourneyUrl(s"IM9001234569/$lastYear-M12/vat-correction-months-add")
      correction.twoCorrectionMonths()
      dashboard.answerRadioButton("no")

      And("the user submits their return successfully via the check-your-answers page")
      dashboard.checkJourneyUrl("IM9001234569/check-your-answers")
      dashboard.submit()
      dashboard.checkJourneyUrl("IM9001234569/return-successfully-submitted")
    }
  }
}
