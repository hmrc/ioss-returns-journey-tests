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

class IntermediarySaveForLaterSpec extends BaseSpec {

  private val dashboard    = Dashboard
  private val auth         = Auth
  private val fileUpload   = FileUpload
  private val intermediary = Intermediary
  private val save         = Save

  Feature("Intermediary Save For Later journeys") {

    Scenario("Intermediary with multiple saved returns can continue a previously saved return for a NETP") {

      Given("the user accesses a saved return in the IOSS Returns Service on behalf of a NETP")
      auth.goToAuthorityWizard()
      auth.loginAsIntermediary("IN9006655444", "IM9006655443", "savedReturn")

      And("the correct client name is displayed for IM9006655443")
      dashboard.checkJourneyUrl("IM9006655443/2025-M3/return-continue")
      intermediary.checkClientName("IM9006655443")

      And("the user answers yes on the IM9006655443/2025-M3/return-continue page")
      dashboard.clickLink("value_0")
      dashboard.continue()

      And("the user able to continue the NETP return from the last saved point IM9006655443/add-new-country/1/1")
      dashboard.checkJourneyUrl("IM9006655443/add-new-country/1/1")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9006655443/country-vat-correction-amount/1/1")
      dashboard.enterAnswer("1000")
      dashboard.checkJourneyUrl("IM9006655443/vat-payable-confirm/1/1")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9006655443/vat-payable-check/1/1")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9006655443/correction-list-countries/1")
      dashboard.answerRadioButton("no")
      dashboard.checkJourneyUrl("IM9006655443/2025-M3/vat-correction-months-add")
      dashboard.answerRadioButton("no")

      And("the user submits their return successfully via the check-your-answers page")
      dashboard.checkJourneyUrl("IM9006655443/check-your-answers")
      dashboard.submit()
      dashboard.checkJourneyUrl("IM9006655443/return-successfully-submitted")
    }

    Scenario("Intermediary with multiple saved returns can delete a previously saved return for a NETP") {

      Given("the user accesses a saved return in the IOSS Returns Service on behalf of a NETP")
      auth.goToAuthorityWizard()
      auth.loginAsIntermediary("IN9006655444", "IM9006655442", "savedReturn")

      And("the correct client name is displayed for IM9006655442")
      dashboard.checkJourneyUrl("IM9006655442/2025-M1/return-continue")
      intermediary.checkClientName("IM9006655442")

      And("the user answers No, delete this return on the IM9006655442/2025-M1/return-continue page")
      dashboard.clickLink("value_1")
      dashboard.continue()

      And("the user answers yes on the IM9006655442/2025-M1/return-delete-confirm page")
      dashboard.checkJourneyUrl("IM9006655442/2025-M1/return-delete-confirm")
      dashboard.answerRadioButton("yes")

      And("the user is redicrected to their intermediary dashboard")
      intermediary.dashboardUrlCheck("intermediary")
    }

    Scenario("Intermediary with a single saved return can continue a previously saved return for a NETP") {

      Given("the user accesses a saved return in the IOSS Returns Service on behalf of a NETP")
      auth.goToAuthorityWizard()
      auth.loginAsIntermediary("IN9006655555", "IM9006655551", "savedReturn")

      And("the correct client name is displayed for IM9006655551")
      dashboard.checkJourneyUrl("IM9006655551/2025-M1/return-continue")
      intermediary.checkClientName("IM9006655551")

      And("the user answers yes on the IM9006655551/2025-M1/return-continue page")
      dashboard.clickLink("value_0")
      dashboard.continue()

      And("the user able to continue the NETP return from the last saved point IM9006655551/sales-to-country/2/1")
      dashboard.checkJourneyUrl("IM9006655551/sales-to-country/2/1")
      dashboard.enterAnswer("1000")
      dashboard.checkJourneyUrl("IM9006655551/vat-on-sales/2/1")
      dashboard.clickLink("choice")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9006655551/sales-to-country/2/2")
      dashboard.enterAnswer("1000")
      dashboard.checkJourneyUrl("IM9006655551/vat-on-sales/2/2")
      dashboard.clickLink("choice")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9006655551/check-sales/2")
      dashboard.answerRadioButton("no")
      dashboard.checkJourneyUrl("IM9006655551/add-sales-country-list")
      dashboard.answerRadioButton("no")

      And("the user submits their return successfully via the check-your-answers page")
      dashboard.checkJourneyUrl("IM9006655551/check-your-answers")
      dashboard.submit()
      dashboard.checkJourneyUrl("IM9006655551/return-successfully-submitted")
    }

    Scenario("An intermediary can save the progress of a NETP return and return to the page they were on") {

      Given("the user accesses the IOSS Returns Service on behalf of a NETP")
      auth.goToAuthorityWizard()
      auth.loginAsIntermediary("IN9006655555", "IM9006655552", "returns")

      And("the user starts a return for the NETP")
      dashboard.checkJourneyUrl("IM9006655552/2025-M3/start-return")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9006655552/want-to-upload-file")
      fileUpload.selectFileUpload("No, enter them myself")
      dashboard.checkJourneyUrl("IM9006655552/sold-goods")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9006655552/sold-to-country/1")

      When("the user clicks the Save and come back later button")
      dashboard.clickLink("saveProgress")

      Then("the user is on the progress-saved page")
      save.checkProgressSaved("IM9006655552/2025-M3")

      When("the user clicks on the continue to complete your return link")
      dashboard.clickLink("continueToYourReturn")

      And("the user can continue progressing their saved return 1")
      dashboard.checkJourneyUrl("IM9006655552/sold-to-country/1")
      dashboard.selectCountry("Austria")
      dashboard.checkJourneyUrl("IM9006655552/vat-rates-from-country/1")
      dashboard.tickCheckbox("first")
      dashboard.tickCheckbox("second")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9006655552/sales-to-country/1/1")

      When("the user clicks the Save and come back later button")
      dashboard.clickLink("saveProgress")

      Then("the user is on the progress-saved page")
      save.checkProgressSaved("IM9006655552/2025-M3")

      When("the user clicks on the continue to complete your return link")
      dashboard.clickLink("continueToYourReturn")

      And("the user can continue progressing their saved return 2")
      dashboard.checkJourneyUrl("IM9006655552/sales-to-country/1/1")
      dashboard.enterAnswer("6000")
      dashboard.checkJourneyUrl("IM9006655552/vat-on-sales/1/1")
      dashboard.clickLink("choice")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9006655552/sales-to-country/1/2")
      dashboard.enterAnswer("1000.25")
      dashboard.checkJourneyUrl("IM9006655552/vat-on-sales/1/2")
      dashboard.clickLink("choice")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9006655552/check-sales/1")

      When("the user clicks the Save and come back later button")
      dashboard.clickLink("saveProgress")

      Then("the user is on the progress-saved page")
      save.checkProgressSaved("IM9006655552/2025-M3")

      When("the user clicks on the continue to complete your return link")
      dashboard.clickLink("continueToYourReturn")

      And("the user can continue progressing their saved return 3")
      dashboard.checkJourneyUrl("IM9006655552/check-sales/1")
      dashboard.answerRadioButton("no")
      dashboard.checkJourneyUrl("IM9006655552/add-sales-country-list")

      When("the user clicks the Save and come back later button")
      dashboard.clickLink("saveProgress")

      Then("the user is on the progress-saved page")
      save.checkProgressSaved("IM9006655552/2025-M3")

      When("the user clicks on the continue to complete your return link")
      dashboard.clickLink("continueToYourReturn")

      And("the user can continue progressing their saved return 4")
      dashboard.checkJourneyUrl("IM9006655552/add-sales-country-list")
      dashboard.answerRadioButton("no")
      dashboard.checkJourneyUrl("IM9006655552/correct-previous-return")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9006655552/correction-return-year/1")

      When("the user clicks the Save and come back later button")
      dashboard.clickLink("saveProgress")

      Then("the user is on the progress-saved page")
      save.checkProgressSaved("IM9006655552/2025-M3")

      When("the user clicks on the continue to complete your return link")
      dashboard.clickLink("continueToYourReturn")

      And("the user can continue progressing their saved return 5")
      dashboard.checkJourneyUrl("IM9006655552/correction-return-year/1")
      dashboard.clickLink("value_2025")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9006655552/correction-return-month/1")
      dashboard.clickLink("value_January")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9006655552/correction-country/1/1")
      dashboard.selectCountry("Austria")
      dashboard.checkJourneyUrl("IM9006655552/add-new-country/1/1")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9006655552/country-vat-correction-amount/1/1")
      dashboard.enterAnswer("1500")
      dashboard.checkJourneyUrl("IM9006655552/vat-payable-confirm/1/1")

      When("the user clicks the Save and come back later button")
      dashboard.clickLink("saveProgress")

      Then("the user is on the progress-saved page")
      save.checkProgressSaved("IM9006655552/2025-M3")

      When("the user clicks on the continue to complete your return link")
      dashboard.clickLink("continueToYourReturn")

      And("the user can continue progressing their saved return 6")
      dashboard.checkJourneyUrl("IM9006655552/vat-payable-confirm/1/1")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9006655552/vat-payable-check/1/1")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9006655552/correction-list-countries/1")
      dashboard.answerRadioButton("no")
      dashboard.checkJourneyUrl("IM9006655552/2025-M3/vat-correction-months-add")
      dashboard.answerRadioButton("no")
      dashboard.checkJourneyUrl("IM9006655552/check-your-answers")

      When("the user clicks the Save and come back later button")
      dashboard.clickLink("saveProgress")

      Then("the user is on the progress-saved page")
      save.checkProgressSaved("IM9006655552/2025-M3")

      When("the user clicks on the continue to complete your return link")
      dashboard.clickLink("continueToYourReturn")

      And("the user submits their return successfully via the check-your-answers page")
      dashboard.checkJourneyUrl("IM9006655552/check-your-answers")
      dashboard.submit()
      dashboard.checkJourneyUrl("IM9006655552/return-successfully-submitted")
    }

    Scenario("Intermediary cannot access saved return for a client who is not registered with them") {

      Given("the user accesses the IOSS Returns Service on behalf of a NETP who is not registered to them as a client")
      auth.goToAuthorityWizard()
      auth.loginAsIntermediary("IN9001234567", "IM9006655443", "returns")

      Then("the user is on the cannot-use-not-registered page")
      dashboard.checkJourneyUrl("cannot-use-not-registered")
    }
  }
}
