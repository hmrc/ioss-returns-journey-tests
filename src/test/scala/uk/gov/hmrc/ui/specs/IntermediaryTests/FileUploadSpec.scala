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

class FileUploadSpec extends BaseSpec {

  private val dashboard  = Dashboard
  private val auth       = Auth
  private val fileUpload = FileUpload

  Feature("File Upload journeys") {

    Scenario("Intermediary can submit a return for a NETP using file upload") {

      Given("the user accesses the IOSS Returns Service on behalf of a NETP")
      auth.goToAuthorityWizard()
      auth.loginAsIntermediary("IN9001234567", "IM9001144771", "returns")

      And("the user answers yes on the IM9001144771/2025-M3/start-return page")
      dashboard.checkJourneyUrl("IM9001144771/2025-M3/start-return")
      dashboard.answerRadioButton("yes")

      Then("the user answers yes on the IM9001144771/want-to-upload-file page")
      dashboard.checkJourneyUrl("IM9001144771/want-to-upload-file")
      fileUpload.selectFileUpload("Yes")

      And("the user uploads the file 'Test.csv'")
      dashboard.checkJourneyUrl("IM9001144771/file-upload")
      fileUpload.uploadFile("Test.csv")

      And("the user answers yes to the IM9001144771/file-uploaded page")
      fileUpload.fileUploadedUrlCheck("IM9001144771")
      fileUpload.selectFileUpload("Yes")

      And("the user answers no on the IM9001144771/correct-previous-return page")
      dashboard.checkJourneyUrl("IM9001144771/correct-previous-return")
      dashboard.answerRadioButton("no")

      And("the user submits their return successfully via the check-your-answers page")
      dashboard.checkJourneyUrl("IM9001144771/check-your-answers")
      dashboard.submit()
      dashboard.checkJourneyUrl("IM9001144771/return-successfully-submitted")
    }

    Scenario("A global IOSS user can submit a return using file upload") {

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "IM9009999888", "Organisation", "hasIOSSEnrolment", "dashboard")
      dashboard.checkJourneyUrl("your-account")

      When("the user clicks on the 'Start your return' link")
      dashboard.clickLink("start-your-return")

      Then("the user answers yes on the start page")
      dashboard.checkJourneyUrl("IM9009999888/2023-M12/start-return")
      dashboard.answerRadioButton("yes")

      And("the user answers no on the want-to-upload-file page")
      dashboard.checkJourneyUrl("IM9009999888/want-to-upload-file")
      fileUpload.selectFileUpload("Yes")

      And("the user uploads the file 'Test2.csv'")
      dashboard.checkJourneyUrl("IM9009999888/file-upload")
      fileUpload.uploadFile("Test2.csv")

      And("the user answers yes to the IM9009999888/file-uploaded page")
      fileUpload.fileUploadedUrlCheck("IM9009999888")
      fileUpload.selectFileUpload("Yes")

      And("the user submits their return successfully via the check-your-answers page")
      dashboard.checkJourneyUrl("IM9009999888/check-your-answers")
      dashboard.submit()
      dashboard.checkJourneyUrl("IM9009999888/return-successfully-submitted")
    }
  }
}
