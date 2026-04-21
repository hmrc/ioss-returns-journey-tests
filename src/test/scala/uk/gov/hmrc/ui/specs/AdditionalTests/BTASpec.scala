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

class BTASpec extends BaseSpec {

  private val dashboard = Dashboard
  private val auth = Auth
  private val fileUpload = FileUpload

  Feature("BTA journeys") {

    Scenario("A user enters the Your Account page via BTA, submits a nil return and has the correct link back to BTA") {

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "IM9009999888", "Organisation", "withIOSSEnrolment", "dashboard")
      dashboard.checkJourneyUrl("your-account")

      When("the user manually navigates to the your-account-from-bta link")
      dashboard.goToPage("test-only/your-account-from-bta")

      And("the user is redirected to their account")
      dashboard.checkJourneyUrl("your-account")

      And("the user submits a return")
      dashboard.clickLink("start-your-return")
      dashboard.checkJourneyUrl("IM9009999888/2023-M12/start-return")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9009999888/want-to-upload-file")
      fileUpload.selectFileUpload("No, enter them myself")
      dashboard.checkJourneyUrl("IM9009999888/sold-goods")
      dashboard.answerRadioButton("no")
      dashboard.checkJourneyUrl("IM9009999888/check-your-answers")
      dashboard.submit()
      dashboard.checkJourneyUrl("IM9009999888/return-successfully-submitted")

      When("the user clicks on the Back to your account link")
      dashboard.clickLink("back-to-your-account")

//      Going back to dashboard instead of BTA - VEIOSS-859
//      Then("the user is redirected to BTA")
//      dashboard.checkExternalServiceUrl("business-account")
    }

    Scenario("A user enters the Start Your Return page via BTA, submits a nil return and has the correct link back to BTA") {

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "IM9009999888", "Organisation", "withIOSSEnrolment", "dashboard")
      dashboard.checkJourneyUrl("your-account")

      When("the user manually navigates to the start-your-return-from-bta/2023-M12 link")
      dashboard.goToPage("test-only/start-your-return-from-bta/2023-M12")

      And("the user submits a return")
      dashboard.checkJourneyUrl("IM9009999888/2023-M12/start-return")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9009999888/want-to-upload-file")
      fileUpload.selectFileUpload("No, enter them myself")
      dashboard.checkJourneyUrl("IM9009999888/sold-goods")
      dashboard.answerRadioButton("no")
      dashboard.checkJourneyUrl("IM9009999888/check-your-answers")
      dashboard.submit()
      dashboard.checkJourneyUrl("IM9009999888/return-successfully-submitted")

      When("the user clicks on the Back to your account link")
      dashboard.clickLink("back-to-your-account")

      //      Going back to dashboard instead of BTA - VEIOSS-859
      //      Then("the user is redirected to BTA")
      //      dashboard.checkExternalServiceUrl("business-account")
    }

    Scenario("A user with one outstanding payment clicks the BTA payment link and is directed to the payments service") {

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "IM9008888888", "Organisation", "withIOSSEnrolment", "dashboard")
      dashboard.checkJourneyUrl("your-account")

      When("the user manually navigates to the payments-from-bta link")
      dashboard.goToPage("test-only/payments-from-bta")

      Then("the user has been redirected to the payments service")
      dashboard.checkExternalServiceUrl("payments")
    }

    Scenario("A user with multiple outstanding payments clicks the BTA payment link and is directed to the outstanding payments page") {

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "IM9001234567", "Organisation", "withIOSSEnrolment", "dashboard")
      dashboard.checkJourneyUrl("your-account")

      When("the user manually navigates to the payments-from-bta link")
      dashboard.goToPage("test-only/payments-from-bta")

      And("the user is on the outstanding-payments page")
      dashboard.checkJourneyUrl("IM9001234567/outstanding-payments")

      Then("the user selects the second payment option")
      dashboard.clickLink("value_1")
      dashboard.continue()

      Then("the user has been redirected to the payments service")
      dashboard.checkExternalServiceUrl("payments")
    }
  }
}
