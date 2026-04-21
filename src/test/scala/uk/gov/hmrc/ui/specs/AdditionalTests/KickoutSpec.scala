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

package uk.gov.hmrc.ui.specs.AdditionalTests

import uk.gov.hmrc.ui.pages._
import uk.gov.hmrc.ui.specs.BaseSpec

import java.time.LocalDate

class KickoutSpec extends BaseSpec {

  private val dashboard  = Dashboard
  private val auth       = Auth
  private val fileUpload = FileUpload

  private val currentReturnMonth  = LocalDate.now().getMonthValue
  private val currentReturnYear   = LocalDate.now().getYear
  private val currentPeriodString = s"$currentReturnYear-M$currentReturnMonth"

  private val firstAvailableReturnMonth  = LocalDate.now().minusMonths(37).getMonthValue
  private val firstAvailableReturnYear   = LocalDate.now().minusMonths(37).getYear
  private val firstAvailablePeriodString = s"$firstAvailableReturnYear-M$firstAvailableReturnMonth"

  Feature("Kickout journeys") {

    Scenario("A user without an IOSS enrolment cannot access the returns service") {

      Given("the user accesses the IOSS Returns Service with no IOSS Enrolment")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "notApplicable", "Organisation", "vatOnly", "dashboard")

      Then("the user is on the cannot-use-not-registered page")
      dashboard.checkJourneyUrl("cannot-use-not-registered")
    }

    Scenario("A user answers no to starting their return") {

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "IM9001234567", "Organisation", "hasIOSSEnrolment", "dashboard")
      dashboard.checkJourneyUrl("your-account")

      When("the user clicks on the 'Start your return' link")
      dashboard.clickLink("start-your-return")

      And("the user answers no on the start page")
      dashboard.checkJourneyUrl("IM9001234567/2023-M12/start-return")
      dashboard.answerRadioButton("no")

      Then("the user is on the no-other-months-available page")
      dashboard.checkJourneyUrl("IM9001234567/no-other-months-available")

      When("the user clicks on the Back to your account link")
      dashboard.clickLink("back-to-your-account")

      Then("the user is back on their account dashboard")
      dashboard.checkJourneyUrl("your-account")
    }

    Scenario("Failure page when there is an error submitting return due to the registration not being found on Core") {

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "IM9007777777", "Organisation", "hasIOSSEnrolment", "dashboard")
      dashboard.checkJourneyUrl("your-account")

      When("the user clicks on the 'Start your return' link")
      dashboard.clickLink("start-your-return")

      Then("the user answers yes on the start page")
      dashboard.checkJourneyUrl("IM9007777777/2023-M12/start-return")
      dashboard.answerRadioButton("yes")

      And("the user answers no on the want-to-upload-file page")
      dashboard.checkJourneyUrl("IM9007777777/want-to-upload-file")
      fileUpload.selectFileUpload("No, enter them myself")

      And("the user answers no on the sold-goods page")
      dashboard.checkJourneyUrl("IM9007777777/sold-goods")
      dashboard.answerRadioButton("no")

      And("the user answers no on the correct-previous-return page")
      dashboard.checkJourneyUrl("IM9007777777/correct-previous-return")
      dashboard.answerRadioButton("no")

      And("the user submits their return via the check-your-answers page")
      dashboard.checkJourneyUrl("IM9007777777/check-your-answers")
      dashboard.submit()

      Then("the user is on the submission-failure page")
      dashboard.checkJourneyUrl("IM9007777777/submission-failure")
    }

    Scenario("Failure page when there is an error submitting the return to Core") {

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "IM9007777778", "Organisation", "hasIOSSEnrolment", "dashboard")
      dashboard.checkJourneyUrl("your-account")

      When("the user clicks on the 'Start your return' link")
      dashboard.clickLink("start-your-return")

      Then("the user answers yes on the start page")
      dashboard.checkJourneyUrl("IM9007777778/2023-M12/start-return")
      dashboard.answerRadioButton("yes")

      And("the user answers no on the want-to-upload-file page")
      dashboard.checkJourneyUrl("IM9007777778/want-to-upload-file")
      fileUpload.selectFileUpload("No, enter them myself")

      And("the user answers no on the sold-goods page")
      dashboard.checkJourneyUrl("IM9007777778/sold-goods")
      dashboard.answerRadioButton("no")

      And("the user answers no on the correct-previous-return page")
      dashboard.checkJourneyUrl("IM9007777778/correct-previous-return")
      dashboard.answerRadioButton("no")

      And("the user submits their return via the check-your-answers page")
      dashboard.checkJourneyUrl("IM9007777778/check-your-answers")
      dashboard.submit()

      Then("the user is on the submission-failure page")
      dashboard.checkJourneyUrl("IM9007777778/submission-failure")
    }

    Scenario("A user has no available returns to start after clicking the start return link via secure messages") {

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "IM9009999998", "Organisation", "hasIOSSEnrolment", "dashboard")
      dashboard.checkJourneyUrl("your-account")

      When("the user accesses the start return link via secure messages")
      dashboard.goToPage("IM9009999998/start-return")

      Then("the is on the no-returns-due page")
      dashboard.checkJourneyUrl("IM9009999998/no-returns-due")
    }

    Scenario("A user is not able to submit a return out of sequence") {

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "IM9001234567", "Organisation", "hasIOSSEnrolment", "dashboard")
      dashboard.checkJourneyUrl("your-account")

      When("the user manually navigates to a return for January 2024")
      dashboard.goToPage("IM9001234567/2024-M1/start-return")

      Then("the is on the cannot-start-return page")
      dashboard.checkJourneyUrl("cannot-start-return")
    }

    Scenario("A user is not able to submit a return for a period that has not yet ended") {

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "IM9009999998", "Organisation", "hasIOSSEnrolment", "dashboard")
      dashboard.checkJourneyUrl("your-account")

      When("the user manually navigates to a return for the current month")
      dashboard.goToPage(s"IM9009999998/$currentPeriodString/start-return")

      Then("the is on the no-other-months-available page")
      dashboard.checkJourneyUrl("IM9009999998/no-other-months-available")
    }

    Scenario("An excluded trader is not able to submit a return for a period that is older than 3 years") {

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "IM9001239999", "Organisation", "hasIOSSEnrolment", "dashboard")
      dashboard.checkJourneyUrl("your-account")

      When("the user clicks on the 'Start your return' link")
      dashboard.clickLink("start-your-return")

      Then("the user is on the start return page for the first available period within 3 years")
      dashboard.checkJourneyUrl(s"IM9001239999/$firstAvailablePeriodString/start-return")

      When("the user manually navigates to a return for December 2020")
      dashboard.goToPage("IM9001239999/2020-M12/start-return")

      Then("the user is on the cannot-start-excluded-return page")
      dashboard.checkJourneyUrl("IM9001239999/cannot-start-excluded-return")
    }
  }
}
