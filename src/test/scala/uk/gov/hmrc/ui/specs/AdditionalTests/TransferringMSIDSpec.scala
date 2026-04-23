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

class TransferringMSIDSpec extends BaseSpec {

  private val dashboard        = Dashboard
  private val auth             = Auth
  private val fileUpload       = FileUpload
  private val transferringMsid = TransferringMSID
  private val exclusion        = Exclusion
  private val pastReturn       = PastReturn

  Feature("Transferring MSID journeys") {

    Scenario("A user who has transferred from another member state has a partial first return") {

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "IM9005999997", "Organisation", "hasIOSSEnrolment", "dashboard")
      dashboard.checkJourneyUrl("your-account")

      When("the user clicks on the 'Start your return' link")
      dashboard.clickLink("start-your-return")

      Then("the user transferring from another MSID is offered a partial return for the correct period")
      dashboard.checkJourneyUrl("IM9005999997/2024-M1/start-return")
      transferringMsid.transferringMsidText("trader", "from", "offered", "partial")
      dashboard.answerRadioButton("yes")

      And("the user answers no on the want-to-upload-file page")
      dashboard.checkJourneyUrl("IM9005999997/want-to-upload-file")
      fileUpload.selectFileUpload("No, enter them myself")

      And("the user answers no on the sold-goods page")
      dashboard.checkJourneyUrl("IM9005999997/sold-goods")
      dashboard.answerRadioButton("no")

      And("the user transferring from another MSID is submitting a partial return for the correct period")
      dashboard.checkJourneyUrl("IM9005999997/check-your-answers")
      transferringMsid.transferringMsidText("trader", "from", "submitting", "partial")

      And("the user submits their return successfully via the check-your-answers page")
      dashboard.submit()
      dashboard.checkJourneyUrl("IM9005999997/return-successfully-submitted")
    }

    Scenario("A user who has transferred from another member state has a full second return") {

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "IM9005999977", "Organisation", "hasIOSSEnrolment", "dashboard")
      dashboard.checkJourneyUrl("your-account")

      When("the user clicks on the 'Start your return' link")
      dashboard.clickLink("start-your-return")

      Then("the user transferring from another MSID is offered a full return for the correct period")
      dashboard.checkJourneyUrl("IM9005999977/2024-M2/start-return")
      transferringMsid.transferringMsidText("trader", "from", "offered", "full")
      dashboard.answerRadioButton("yes")

      And("the user answers no on the want-to-upload-file page")
      dashboard.checkJourneyUrl("IM9005999977/want-to-upload-file")
      fileUpload.selectFileUpload("No, enter them myself")

      And("the user answers no on the sold-goods page")
      dashboard.checkJourneyUrl("IM9005999977/sold-goods")
      dashboard.answerRadioButton("no")

      And("the user answers no on the correct-previous-return page")
      dashboard.checkJourneyUrl("IM9005999977/correct-previous-return")
      dashboard.answerRadioButton("no")

      And("the user submits their return successfully via the check-your-answers page")
      dashboard.checkJourneyUrl("IM9005999977/check-your-answers")
      dashboard.submit()
      dashboard.checkJourneyUrl("IM9005999977/return-successfully-submitted")
    }

    Scenario(
      "A user who is transferring to another member state has a full return to submit prior to their final partial return"
    ) {

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "IM9009995555", "Organisation", "hasIOSSEnrolment", "dashboard")
      dashboard.checkJourneyUrl("your-account")

      When("the user clicks on the 'Start your return' link")
      dashboard.clickLink("start-your-return")

      And("the user is not presented with the heading for their final return")
      exclusion.finalReturnHeading(display = false, "trader")

      Then("the user transferring to another MSID is offered a full return for the correct period")
      dashboard.checkJourneyUrl("IM9009995555/2024-M1/start-return")
      transferringMsid.transferringMsidText("trader", "to", "offered", "full")
      dashboard.answerRadioButton("yes")

      And("the user answers no on the want-to-upload-file page")
      dashboard.checkJourneyUrl("IM9009995555/want-to-upload-file")
      fileUpload.selectFileUpload("No, enter them myself")

      And("the user answers no on the sold-goods page")
      dashboard.checkJourneyUrl("IM9009995555/sold-goods")
      dashboard.answerRadioButton("no")

      And("the user answers no on the correct-previous-return page")
      dashboard.checkJourneyUrl("IM9009995555/correct-previous-return")
      dashboard.answerRadioButton("no")

      And("the user is not shown the corrections warning before submission")
      dashboard.checkJourneyUrl("IM9009995555/check-your-answers")
      exclusion.correctionsWarningSubmission(display = false)

      And("the user submits their return successfully via the check-your-answers page")
      dashboard.submit()
      dashboard.checkJourneyUrl("IM9009995555/return-successfully-submitted")
    }

    Scenario("A user who is transferring to another member state has a partial final return") {

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "IM9009999555", "Organisation", "hasIOSSEnrolment", "dashboard")
      dashboard.checkJourneyUrl("your-account")

      When("the user clicks on the 'Start your return' link")
      dashboard.clickLink("start-your-return")

      And("the user is presented with the heading for their final return")
      exclusion.finalReturnHeading(display = true, "trader")

      Then("the user transferring to another MSID is offered a partial return for the correct period")
      dashboard.checkJourneyUrl("IM9009999555/2024-M2/start-return")
      transferringMsid.transferringMsidText("trader", "to", "offered", "partial")
      dashboard.answerRadioButton("yes")

      And("the user answers no on the want-to-upload-file page")
      dashboard.checkJourneyUrl("IM9009999555/want-to-upload-file")
      fileUpload.selectFileUpload("No, enter them myself")

      And("the user answers no on the sold-goods page")
      dashboard.checkJourneyUrl("IM9009999555/sold-goods")
      dashboard.answerRadioButton("no")

      And("the user answers no on the correct-previous-return page")
      dashboard.checkJourneyUrl("IM9009999555/correct-previous-return")
      dashboard.answerRadioButton("no")

      And("the user is shown the corrections warning before submission")
      dashboard.checkJourneyUrl("IM9009999555/check-your-answers")
      exclusion.correctionsWarningSubmission(display = true)

      And("the user transferring to another MSID is submitting a partial return for the correct period")
      transferringMsid.transferringMsidText("trader", "to", "submitting", "partial")

      And("the user submits their return successfully via the check-your-answers page")
      dashboard.submit()
      dashboard.checkJourneyUrl("IM9009999555/return-successfully-submitted")
    }

    Scenario("A user who transferred from another member state has a first partial return in past returns") {

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "IM9005999777", "Organisation", "hasIOSSEnrolment", "dashboard")
      dashboard.checkJourneyUrl("your-account")

      When("the user clicks on the 'View submitted returns' link")
      dashboard.clickLink("view-submitted-returns")

      Then("the user is on the past-returns page")
      dashboard.checkJourneyUrl("IM9005999777/past-returns")

      And("the user clicks the Show all sections accordion")
      pastReturn.showAllAccordion()

      When("the user clicks on the January 2024 link")
      pastReturn.selectPastReturnLink("past-returns\\/2024-M1")

      Then("the user is shown the correct previously submitted return")
      dashboard.checkJourneyUrl("IM9005999777/past-returns/2024-M1")

      And("the user transferring from another MSID has the correct partial dates in the past return")
      transferringMsid.checkTransferringFromOtherMSIDPastReturn()
    }

    Scenario("A user who transferred to another member state has a final partial return in past returns") {

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "IM9009955555", "Organisation", "hasIOSSEnrolment", "dashboard")
      dashboard.checkJourneyUrl("your-account")

      When("the user clicks on the 'View submitted returns' link")
      dashboard.clickLink("view-submitted-returns")

      Then("the user is on the past-returns page")
      dashboard.checkJourneyUrl("IM9009955555/past-returns")

      And("the user clicks the Show all sections accordion")
      pastReturn.showAllAccordion()

      When("the user clicks on the February 2024 link")
      pastReturn.selectPastReturnLink("past-returns\\/2024-M2")

      Then("the user is shown the correct previously submitted return")
      dashboard.checkJourneyUrl("IM9009955555/past-returns/2024-M2")

      And("the user transferring from another MSID has the correct partial dates in the past return")
      transferringMsid.checkTransferringToOtherMSIDPastReturn()
    }
  }
}
