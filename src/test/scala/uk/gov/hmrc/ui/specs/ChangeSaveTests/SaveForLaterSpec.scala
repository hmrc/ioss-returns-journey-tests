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

class SaveForLaterSpec extends BaseSpec {

  private val dashboard  = Dashboard
  private val auth       = Auth
  private val fileUpload = FileUpload
  private val save       = Save

  Feature("Save For Later journeys") {

    Scenario("A user can save their progress and return to the last page they were on") {
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000123", "IM9001234566", "Organisation", "hasIOSSEnrolment", "dashboard")
      dashboard.checkJourneyUrl("your-account")

      When("the user clicks on the 'Start your return' link")
      dashboard.clickLink("start-your-return")

      Then("the user answers questions on their return")
      dashboard.checkJourneyUrl("IM9001234566/2023-M12/start-return")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234566/want-to-upload-file")
      fileUpload.selectFileUpload("No, enter them myself")
      dashboard.checkJourneyUrl("IM9001234566/sold-goods")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234566/sold-to-country/1")
      dashboard.selectCountry("Austria")
      dashboard.checkJourneyUrl("IM9001234566/vat-rates-from-country/1")
      dashboard.tickCheckbox("first")
      dashboard.tickCheckbox("second")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9001234566/sales-to-country/1/1")

      When("the user clicks the Save and come back later button")
      dashboard.clickLink("saveProgress")

      Then("the user is on the progress-saved page")
      save.checkProgressSaved("IM9001234566/2023-M12")

      When("the user clicks on the continue to complete your return link")
      dashboard.clickLink("continueToYourReturn")

      Then("the user can resume their return 1")
      dashboard.checkJourneyUrl("IM9001234566/sales-to-country/1/1")
      dashboard.enterAnswer("6000")
      dashboard.checkJourneyUrl("IM9001234566/vat-on-sales/1/1")
      dashboard.clickLink("choice")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9001234566/sales-to-country/1/2")
      dashboard.enterAnswer("1000.25")
      dashboard.checkJourneyUrl("IM9001234566/vat-on-sales/1/2")
      dashboard.clickLink("choice")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9001234566/check-sales/1")

      When("the user clicks the Save and come back later button")
      dashboard.clickLink("saveProgress")

      Then("the user is on the progress-saved page")
      save.checkProgressSaved("IM9001234566/2023-M12")

      When("the user clicks on the continue to complete your return link")
      dashboard.clickLink("continueToYourReturn")

      Then("the user can resume their return 2")
      dashboard.checkJourneyUrl("IM9001234566/check-sales/1")
      dashboard.answerRadioButton("no")
      dashboard.checkJourneyUrl("IM9001234566/add-sales-country-list")

      When("the user clicks the Save and come back later button")
      dashboard.clickLink("saveProgress")

      Then("the user is on the progress-saved page")
      save.checkProgressSaved("IM9001234566/2023-M12")

      When("the user clicks on the continue to complete your return link")
      dashboard.clickLink("continueToYourReturn")

      Then("the user can resume their return 3")
      dashboard.checkJourneyUrl("IM9001234566/add-sales-country-list")
      dashboard.answerRadioButton("no")
      dashboard.checkJourneyUrl("IM9001234566/correct-previous-return")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234566/correction-return-year/1")
      dashboard.clickLink("value_2023")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9001234566/correction-return-month/1")
      dashboard.clickLink("value_October")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9001234566/correction-country/1/1")

      When("the user clicks the Save and come back later button")
      dashboard.clickLink("saveProgress")

      Then("the user is on the progress-saved page")
      save.checkProgressSaved("IM9001234566/2023-M12")

      When("the user clicks on the continue to complete your return link")
      dashboard.clickLink("continueToYourReturn")

      Then("the user can resume their return 4")
      dashboard.checkJourneyUrl("IM9001234566/correction-country/1/1")
      dashboard.selectCountry("Austria")
      dashboard.checkJourneyUrl("IM9001234566/add-new-country/1/1")

      When("the user clicks the Save and come back later button")
      dashboard.clickLink("saveProgress")

      Then("the user is on the progress-saved page")
      save.checkProgressSaved("IM9001234566/2023-M12")

      When("the user clicks on the continue to complete your return link")
      dashboard.clickLink("continueToYourReturn")

      Then("the user can resume their return 5")
      dashboard.checkJourneyUrl("IM9001234566/add-new-country/1/1")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234566/country-vat-correction-amount/1/1")

      When("the user clicks the Save and come back later button")
      dashboard.clickLink("saveProgress")

      Then("the user is on the progress-saved page")
      save.checkProgressSaved("IM9001234566/2023-M12")

      When("the user clicks on the continue to complete your return link")
      dashboard.clickLink("continueToYourReturn")

      Then("the user can resume their return 6")
      dashboard.checkJourneyUrl("IM9001234566/country-vat-correction-amount/1/1")
      dashboard.enterAnswer("1500")
      dashboard.checkJourneyUrl("IM9001234566/vat-payable-confirm/1/1")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234566/vat-payable-check/1/1")

      When("the user clicks the Save and come back later button")
      dashboard.clickLink("saveProgress")

      Then("the user is on the progress-saved page")
      save.checkProgressSaved("IM9001234566/2023-M12")

      When("the user clicks on the continue to complete your return link")
      dashboard.clickLink("continueToYourReturn")

      Then("the user can resume their return 7")
      dashboard.checkJourneyUrl("IM9001234566/vat-payable-check/1/1")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9001234566/correction-list-countries/1")
      dashboard.answerRadioButton("no")
      dashboard.checkJourneyUrl("IM9001234566/2023-M12/vat-correction-months-add")
      dashboard.answerRadioButton("no")
      dashboard.checkJourneyUrl("IM9001234566/check-your-answers")

      When("the user clicks the Save and come back later button")
      dashboard.clickLink("saveProgress")

      Then("the user is on the progress-saved page")
      save.checkProgressSaved("IM9001234566/2023-M12")

      When("the user clicks on the continue to complete your return link")
      dashboard.clickLink("continueToYourReturn")

      And("the user submits their return successfully via the check-your-answers page")
      dashboard.checkJourneyUrl("IM9001234566/check-your-answers")
      dashboard.submit()
      dashboard.checkJourneyUrl("IM9001234566/return-successfully-submitted")
    }

    Scenario("A user can save their progress and return to their in progress return via your account") {
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000123", "IM9001234566", "Organisation", "hasIOSSEnrolment", "dashboard")
      dashboard.checkJourneyUrl("your-account")

      When("the user clicks on the 'Start your return' link")
      dashboard.clickLink("start-your-return")

      Then("the user answers questions on their return")
      dashboard.checkJourneyUrl("IM9001234566/2023-M12/start-return")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234566/want-to-upload-file")
      fileUpload.selectFileUpload("No, enter them myself")
      dashboard.checkJourneyUrl("IM9001234566/sold-goods")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234566/sold-to-country/1")
      dashboard.selectCountry("Belgium")
      dashboard.checkJourneyUrl("IM9001234566/vat-rates-from-country/1")
      dashboard.tickCheckbox("first")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9001234566/sales-to-country/1/1")
      dashboard.enterAnswer("1000")
      dashboard.checkJourneyUrl("IM9001234566/vat-on-sales/1/1")
      dashboard.clickLink("choice")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9001234566/check-sales/1")

      When("the user clicks the Save and come back later button")
      dashboard.clickLink("saveProgress")

      Then("the user is on the progress-saved page")
      save.checkProgressSaved("IM9001234566/2023-M12")

      When("the user clicks on the return to your account link")
      dashboard.clickLink("backToYourAccount")

      Then("the user clicks on the Continue your return link")
      dashboard.clickLink("continue-your-return")

      And("the user answers 'Continue my return' on the return-continue page")
      dashboard.checkJourneyUrl("IM9001234566/2023-M12/return-continue")
      dashboard.clickLink("value_0")
      dashboard.continue()

      Then("the user can resume their return 1")
      dashboard.checkJourneyUrl("IM9001234566/check-sales/1")
      dashboard.answerRadioButton("no")
      dashboard.checkJourneyUrl("IM9001234566/add-sales-country-list")
      dashboard.answerRadioButton("no")
      dashboard.checkJourneyUrl("IM9001234566/correct-previous-return")

      When("the user clicks the Save and come back later button")
      dashboard.clickLink("saveProgress")

      Then("the user is on the progress-saved page")
      save.checkProgressSaved("IM9001234566/2023-M12")

      When("the user clicks on the return to your account link")
      dashboard.clickLink("backToYourAccount")

      Then("the user clicks on the Continue your return link")
      dashboard.clickLink("continue-your-return")

      And("the user answers 'Continue my return' on the return-continue page")
      dashboard.checkJourneyUrl("IM9001234566/2023-M12/return-continue")
      dashboard.clickLink("value_0")
      dashboard.continue()

      Then("the user can resume their return 2")
      dashboard.checkJourneyUrl("IM9001234566/correct-previous-return")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234566/correction-return-year/1")
      dashboard.clickLink("value_2023")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9001234566/correction-return-month/1")
      dashboard.clickLink("value_October")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9001234566/correction-country/1/1")
      dashboard.selectCountry("Austria")
      dashboard.checkJourneyUrl("IM9001234566/add-new-country/1/1")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234566/country-vat-correction-amount/1/1")
      dashboard.enterAnswer("120.36")
      dashboard.checkJourneyUrl("IM9001234566/vat-payable-confirm/1/1")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234566/vat-payable-check/1/1")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9001234566/correction-list-countries/1")
      dashboard.answerRadioButton("no")
      dashboard.checkJourneyUrl("IM9001234566/2023-M12/vat-correction-months-add")
      dashboard.answerRadioButton("no")

      And("the user submits their return successfully via the check-your-answers page")
      dashboard.checkJourneyUrl("IM9001234566/check-your-answers")
      dashboard.submit()
      dashboard.checkJourneyUrl("IM9001234566/return-successfully-submitted")
    }

    Scenario("A user can save their progress and return to their in progress return after logging out and back in") {
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000123", "IM9001234566", "Organisation", "hasIOSSEnrolment", "dashboard")
      dashboard.checkJourneyUrl("your-account")

      When("the user clicks on the 'Start your return' link")
      dashboard.clickLink("start-your-return")

      Then("the user answers questions on their return")
      dashboard.checkJourneyUrl("IM9001234566/2023-M12/start-return")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234566/want-to-upload-file")
      fileUpload.selectFileUpload("No, enter them myself")
      dashboard.checkJourneyUrl("IM9001234566/sold-goods")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234566/sold-to-country/1")

      When("the user clicks the Save and come back later button")
      dashboard.clickLink("saveProgress")

      Then("the user is on the progress-saved page")
      save.checkProgressSaved("IM9001234566/2023-M12")

      When("the user clicks on the continue to complete your return link")
      dashboard.clickLink("continueToYourReturn")

      Then("the user can resume their return 1")
      dashboard.checkJourneyUrl("IM9001234566/sold-to-country/1")
      dashboard.selectCountry("Bulgaria")
      dashboard.checkJourneyUrl("IM9001234566/vat-rates-from-country/1")
      dashboard.tickCheckbox("first")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9001234566/sales-to-country/1/1")
      dashboard.enterAnswer("1000")
      dashboard.checkJourneyUrl("IM9001234566/vat-on-sales/1/1")
      dashboard.clickLink("choice")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9001234566/check-sales/1")
      dashboard.answerRadioButton("no")
      dashboard.checkJourneyUrl("IM9001234566/add-sales-country-list")

      When("the user clicks the Save and come back later button")
      dashboard.clickLink("saveProgress")

      Then("the user is on the progress-saved page")
      save.checkProgressSaved("IM9001234566/2023-M12")

      When("the user clicks on the sign out and come back later link")
      dashboard.clickLink("signOut")

      Then("the user signs back into the service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000123", "IM9001234566", "Organisation", "hasIOSSEnrolment", "dashboard")

      When("the user clicks on the Continue your return link from the dashboard")
      dashboard.checkJourneyUrl("your-account")
      dashboard.clickLink("continue-your-return")

      And("the user answers 'Continue my return' on the return-continue page")
      dashboard.checkJourneyUrl("IM9001234566/2023-M12/return-continue")
      dashboard.clickLink("value_0")
      dashboard.continue()

      Then("the user can resume their return 2")
      dashboard.checkJourneyUrl("IM9001234566/add-sales-country-list")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234566/sold-to-country/2")
      dashboard.selectCountry("Denmark")
      dashboard.checkJourneyUrl("IM9001234566/sales-to-country/2/1")
      dashboard.enterAnswer("1000")
      dashboard.checkJourneyUrl("IM9001234566/vat-on-sales/2/1")
      dashboard.clickLink("choice")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9001234566/check-sales/2")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9001234566/add-sales-country-list")
      dashboard.answerRadioButton("no")
      dashboard.checkJourneyUrl("IM9001234566/correct-previous-return")
      dashboard.answerRadioButton("no")
      dashboard.checkJourneyUrl("IM9001234566/check-your-answers")

      When("the user clicks the Save and come back later button")
      dashboard.clickLink("saveProgress")

      Then("the user is on the progress-saved page")
      save.checkProgressSaved("IM9001234566/2023-M12")

      When("the user clicks on the sign out and come back later link")
      dashboard.clickLink("signOut")

      Then("the user signs back into the service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000123", "IM9001234566", "Organisation", "hasIOSSEnrolment", "dashboard")

      When("the user clicks on the Continue your return link from the dashboard")
      dashboard.checkJourneyUrl("your-account")
      dashboard.clickLink("continue-your-return")

      And("the user answers 'Continue my return' on the return-continue page")
      dashboard.checkJourneyUrl("IM9001234566/2023-M12/return-continue")
      dashboard.clickLink("value_0")
      dashboard.continue()

      And("the user submits their return successfully via the check-your-answers page")
      dashboard.checkJourneyUrl("IM9001234566/check-your-answers")
      dashboard.submit()
      dashboard.checkJourneyUrl("IM9001234566/return-successfully-submitted")
    }

    Scenario("A user can delete a saved in-progress return and start again") {
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000123", "IM9001234566", "Organisation", "hasIOSSEnrolment", "dashboard")
      dashboard.checkJourneyUrl("your-account")

      When("the user clicks on the 'Start your return' link")
      dashboard.clickLink("start-your-return")

      Then("the user answers questions on their return")
      dashboard.checkJourneyUrl("IM9001234566/2023-M12/start-return")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234566/want-to-upload-file")
      fileUpload.selectFileUpload("No, enter them myself")
      dashboard.checkJourneyUrl("IM9001234566/sold-goods")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234566/sold-to-country/1")
      dashboard.selectCountry("Croatia")
      dashboard.checkJourneyUrl("IM9001234566/vat-rates-from-country/1")
      dashboard.tickCheckbox("first")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9001234566/sales-to-country/1/1")
      dashboard.enterAnswer("1000")
      dashboard.checkJourneyUrl("IM9001234566/vat-on-sales/1/1")
      dashboard.clickLink("choice")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9001234566/check-sales/1")
      dashboard.answerRadioButton("no")
      dashboard.checkJourneyUrl("IM9001234566/add-sales-country-list")
      dashboard.answerRadioButton("no")
      dashboard.checkJourneyUrl("IM9001234566/correct-previous-return")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001234566/correction-return-year/1")
      dashboard.clickLink("value_2023")
      dashboard.continue()
      dashboard.checkJourneyUrl("IM9001234566/correction-return-month/1")

      When("the user clicks the Save and come back later button")
      dashboard.clickLink("saveProgress")

      Then("the user is on the progress-saved page")
      save.checkProgressSaved("IM9001234566/2023-M12")

      When("the user clicks on the sign out and come back later link")
      dashboard.clickLink("signOut")

      Then("the user signs back into the service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000123", "IM9001234566", "Organisation", "hasIOSSEnrolment", "dashboard")

      When("the user clicks on the Continue your return link from the dashboard")
      dashboard.checkJourneyUrl("your-account")
      dashboard.clickLink("continue-your-return")

      And("the user answers 'Delete my return and start again' on the return-continue page")
      dashboard.checkJourneyUrl("IM9001234566/2023-M12/return-continue")
      dashboard.clickLink("value_1")
      dashboard.continue()

      And("the user answers yes on the return-delete-confirm page")
      dashboard.checkJourneyUrl("IM9001234566/2023-M12/return-delete-confirm")
      dashboard.answerRadioButton("yes")

      And("the user is redirected to their account")
      dashboard.checkJourneyUrl("your-account")

      When("the user clicks on the 'Start your return' link")
      dashboard.clickLink("start-your-return")

      Then("the user answers yes on the start page")
      dashboard.checkJourneyUrl("IM9001234566/2023-M12/start-return")
      dashboard.answerRadioButton("yes")

      And("the user answers no on the want-to-upload-file page")
      dashboard.checkJourneyUrl("IM9001234566/want-to-upload-file")
      fileUpload.selectFileUpload("No, enter them myself")

      And("the user answers no on the sold-goods page")
      dashboard.checkJourneyUrl("IM9001234566/sold-goods")
      dashboard.answerRadioButton("no")

      And("the user answers no on the correct-previous-return page")
      dashboard.checkJourneyUrl("IM9001234566/correct-previous-return")
      dashboard.answerRadioButton("no")

      And("the user submits their return successfully via the check-your-answers page")
      dashboard.checkJourneyUrl("IM9001234566/check-your-answers")
      dashboard.submit()
      dashboard.checkJourneyUrl("IM9001234566/return-successfully-submitted")
    }

    Scenario("A failed submission of a return is saved for later") {
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000123", "IM9007777778", "Organisation", "hasIOSSEnrolment", "dashboard")
      dashboard.checkJourneyUrl("your-account")

      When("the user clicks on the 'Start your return' link")
      dashboard.clickLink("start-your-return")

      Then("the user completes a nil return")
      dashboard.checkJourneyUrl("IM9007777778/2023-M12/start-return")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9007777778/want-to-upload-file")
      fileUpload.selectFileUpload("No, enter them myself")
      dashboard.checkJourneyUrl("IM9007777778/sold-goods")
      dashboard.answerRadioButton("no")
      dashboard.checkJourneyUrl("IM9007777778/correct-previous-return")
      dashboard.answerRadioButton("no")

      And("the user submits their return")
      dashboard.checkJourneyUrl("IM9007777778/check-your-answers")
      dashboard.submit()

      And("there is an error and the user is shown the submission-failure page")
      dashboard.checkJourneyUrl("IM9007777778/submission-failure")

      When("the user clicks on the Back to your account link")
      dashboard.clickLink("back-to-your-account")
      dashboard.checkJourneyUrl("your-account")

      Then("the user clicks on the Continue your return link")
      dashboard.clickLink("continue-your-return")

      And("the user answers 'Continue my return' on the return-continue page")
      dashboard.checkJourneyUrl("IM9007777778/2023-M12/return-continue")
      dashboard.clickLink("value_0")
      dashboard.continue()

      Then("the user is on the check-your-answers page")
      dashboard.checkJourneyUrl("IM9007777778/check-your-answers")
    }
  }
}
