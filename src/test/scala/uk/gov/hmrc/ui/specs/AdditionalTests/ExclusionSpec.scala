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

class ExclusionSpec extends BaseSpec {

  private val dashboard  = Dashboard
  private val auth       = Auth
  private val fileUpload = FileUpload
  private val exclusion  = Exclusion

  Feature("Exclusions journeys") {

    Scenario("A self excluded user is not able to start a return after their exclusion date") {

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "IM9009999991", "Organisation", "hasIOSSEnrolment", "dashboard")
      dashboard.checkJourneyUrl("your-account")

      When("the user manually navigates to their December 2023 return")
      dashboard.goToPage("IM9009999991/2023-M12/start-return")

      Then("the user is on the excluded-cannot-use-service page")
      dashboard.checkJourneyUrl("excluded-cannot-use-service")
    }

    Scenario("An HMRC excluded user is not able to start a return after their exclusion date") {

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "IM9009999911", "Organisation", "hasIOSSEnrolment", "dashboard")
      dashboard.checkJourneyUrl("your-account")

      When("the user manually navigates to their December 2023 return")
      dashboard.goToPage("IM9009999911/2023-M12/start-return")

      Then("the user is on the excluded-cannot-use-service page")
      dashboard.checkJourneyUrl("excluded-cannot-use-service")
    }

    Scenario("A user who has reversed their exclusion is able to start a return for the next available period") {

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "IM9009999992", "Organisation", "hasIOSSEnrolment", "dashboard")
      dashboard.checkJourneyUrl("your-account")

      When("the user clicks on the 'Start your return' link")
      dashboard.clickLink("start-your-return")

      Then("the user is on the start page for their return")
      dashboard.checkJourneyUrl("IM9009999992/2023-M11/start-return")
    }

    Scenario(
      "A user who is excluded in the future sees the correct dashboard messages when they have outstanding returns"
    ) {

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "IM9009999995", "Organisation", "hasIOSSEnrolment", "dashboard")
      dashboard.checkJourneyUrl("your-account")

      And(
        "the user is presented with the correct banner and message for trader with an exclusion date in the future with outstanding returns"
      )
      exclusion.excludedFutureOutstandingReturnsBanner()
      exclusion.excludedOutstandingReturnsReturnsMessage()

      When("the user clicks on the 'Start your return' link")
      dashboard.clickLink("start-your-return")

      Then("the user is on the start page for their return")
      dashboard.checkJourneyUrl("IM9009999995/2023-M12/start-return")
    }

    Scenario(
      "A user who has an exclusion date in the month before their last return sees the correct dashboard messages"
    ) {

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "IM9029999994", "Organisation", "hasIOSSEnrolment", "dashboard")
      dashboard.checkJourneyUrl("your-account")

      And(
        "the user is presented with the correct banner and message for trader with an exclusion date in the past with a return due"
      )
      exclusion.excludedPastOutstandingReturnsBanner()
      exclusion.excludedOutstandingReturnsReturnsMessage()

      When("the user clicks on the 'Start your return' link")
      dashboard.clickLink("start-your-return")

      Then("the user is on the start page for their return")
      dashboard.checkJourneyUrl("IM9029999994/2024-M1/start-return")
    }

    Scenario("A user who has left the service and has no outstanding actions") {

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "IM9039999994", "Organisation", "hasIOSSEnrolment", "dashboard")

      When("the user is on their dashboard")
      dashboard.checkJourneyUrl("your-account")

      Then(
        "the user is presented with the correct banner and message for a trader with an exclusion date in the past and no outstanding actions"
      )
      exclusion.excludedNoOutstandingReturnsBanner()
      exclusion.excludedNoOutstandingReturnsMessage()
      exclusion.excludedFinalReturnTile()
    }

    Scenario("A user who has been removed from the service and has outstanding returns") {

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "IM9049999994", "Organisation", "hasIOSSEnrolment", "dashboard")
      dashboard.checkJourneyUrl("your-account")

      And(
        "the user is presented with the correct banner and message for trader removed from service and has outstanding returns"
      )
      exclusion.removedOutstandingReturnsBanner()
      exclusion.excludedOutstandingReturnsReturnsMessage()

      When("the user clicks on the 'Start your return' link")
      dashboard.clickLink("start-your-return")

      Then("the user is on the start page for their return")
      dashboard.checkJourneyUrl("IM9049999994/2023-M12/start-return")
    }

    Scenario("A user who has been removed from the service and has no outstanding returns") {

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "IM9059999994", "Organisation", "hasIOSSEnrolment", "dashboard")
      dashboard.checkJourneyUrl("your-account")

      And(
        "the user is presented with the correct banner and message for trader removed from service and has no outstanding returns"
      )
      exclusion.removedNoOutstandingReturnsBanner()
      exclusion.excludedNoOutstandingReturnsMessage()
      exclusion.excludedFinalReturnTile()
    }

    Scenario("A user who has been quarantined and has outstanding returns") {

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "IM9069999994", "Organisation", "hasIOSSEnrolment", "dashboard")
      dashboard.checkJourneyUrl("your-account")

      And(
        "the user is presented with the correct banner and message for quarantined trader who has outstanding returns"
      )
      exclusion.removedOutstandingReturnsBanner()
      exclusion.excludedOutstandingReturnsReturnsMessage()

      When("the user clicks on the 'Start your return' link")
      dashboard.clickLink("start-your-return")

      Then("the user is on the start page for their return")
      dashboard.checkJourneyUrl("IM9069999994/2023-M12/start-return")
    }

    Scenario("A user who has been quarantined and has no outstanding returns") {

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "IM9003999993", "Organisation", "hasIOSSEnrolment", "dashboard")
      dashboard.checkJourneyUrl("your-account")

      And(
        "the user is presented with the correct banner and message for quarantined trader with no outstanding returns"
      )
      exclusion.quarantinedNoOutstandingReturnsBanner()
      exclusion.excludedNoOutstandingReturnsMessage()
      exclusion.excludedFinalReturnTile()
    }

    Scenario("A final return for an excluded user has the correct final return content") {

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "IM9049999994", "Organisation", "hasIOSSEnrolment", "dashboard")
      dashboard.checkJourneyUrl("your-account")

      When("the user clicks on the 'Start your return' link")
      dashboard.clickLink("start-your-return")

      Then("the user is on the start page for their return")
      dashboard.checkJourneyUrl("IM9049999994/2023-M12/start-return")

      And("the user is presented with the header for their final return")
      exclusion.finalReturnHeading(display = true, "trader")

      And("the user answers yes")
      dashboard.answerRadioButton("yes")

      And("the user answers no on the want-to-upload-file page")
      dashboard.checkJourneyUrl("IM9049999994/want-to-upload-file")
      fileUpload.selectFileUpload("No, enter them myself")

      And("the user answers no on the sold-goods page")
      dashboard.checkJourneyUrl("IM9049999994/sold-goods")
      dashboard.answerRadioButton("no")

      And("the user is advised it is their last chance to correct a return")
      dashboard.checkJourneyUrl("IM9049999994/correct-previous-return")
      exclusion.lastChanceForCorrection(display = true)

      And("the user answers no on the correct-previous-return page")
      dashboard.answerRadioButton("no")

      And("the user is shown the corrections warning before submission")
      dashboard.checkJourneyUrl("IM9049999994/check-your-answers")
      exclusion.correctionsWarningSubmission(display = true)

      And("the user successfully submits their return")
      dashboard.submit()
      dashboard.checkJourneyUrl("IM9049999994/return-successfully-submitted")
    }

    Scenario("An excluded user who has more than one return remaining does not have final return content") {

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "IM9009999995", "Organisation", "hasIOSSEnrolment", "dashboard")
      dashboard.checkJourneyUrl("your-account")

      When("the user clicks on the 'Start your return' link")
      dashboard.clickLink("start-your-return")

      Then("the user is on the start page for their return")
      dashboard.checkJourneyUrl("IM9009999995/2023-M12/start-return")

      And("the user is presented with the regular heading for starting a return")
      exclusion.finalReturnHeading(display = false, "trader")
      exclusion.regularHeadingStartReturn()

      And("the user answers yes")
      dashboard.answerRadioButton("yes")

      And("the user answers no on the want-to-upload-file page")
      dashboard.checkJourneyUrl("IM9009999995/want-to-upload-file")
      fileUpload.selectFileUpload("No, enter them myself")

      And("the user answers no on the sold-goods page")
      dashboard.checkJourneyUrl("IM9009999995/sold-goods")
      dashboard.answerRadioButton("no")

      And("the user is not advised it is their last chance to correct a return")
      dashboard.checkJourneyUrl("IM9009999995/correct-previous-return")
      exclusion.lastChanceForCorrection(display = false)

      And("the user answers no on the correct-previous-return page")
      dashboard.answerRadioButton("no")

      And("the user is not shown the corrections warning before submission")
      dashboard.checkJourneyUrl("IM9009999995/check-your-answers")
      exclusion.correctionsWarningSubmission(display = false)

      And("the user successfully submits their return")
      dashboard.submit()
      dashboard.checkJourneyUrl("IM9009999995/return-successfully-submitted")
    }

    Scenario(
      "An excluded user with outstanding returns due over 3 years ago is advised to report them directly to the countries where sales were made"
    ) {

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "IM9001239999", "Organisation", "hasIOSSEnrolment", "dashboard")

      When("the user is on the dashboard")
      dashboard.checkJourneyUrl("your-account")

      Then("the correct return message for returns due more than three years ago for excluded traders is displayed")
      exclusion.returnsOverThreeYearsMessage()
    }

    Scenario(
      "An excluded user with outstanding payments due over 3 years ago is advised to report them directly to the countries where sales were made"
    ) {

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "IM9001238999", "Organisation", "hasIOSSEnrolment", "dashboard")

      When("the user is on the dashboard")
      dashboard.checkJourneyUrl("your-account")

      Then("the correct payments message for payments due more than three years ago for excluded traders is displayed")
      exclusion.paymentsOverThreeYearsMessage()
    }
  }
}
