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

import uk.gov.hmrc.ui.pages.*
import uk.gov.hmrc.ui.specs.BaseSpec

class ChangeDateSpec extends BaseSpec {

  private val dashboard = Dashboard
  private val auth      = Auth

  Feature("Change date over two years journeys") {

    Scenario("A user has not amended their registration for two years - updates registration") {

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "IM9003232323", "Organisation", "hasIOSSEnrolment", "dashboard")
      dashboard.checkJourneyUrl("your-account")

      When("the user clicks on the 'Start your return' link")
      dashboard.clickLink("start-your-return")

      And("the user answers yes on the start page")
      dashboard.checkJourneyUrl("IM9003232323/2024-M1/start-return")
      dashboard.answerRadioButton("yes")

      Then("the user is on the review-registration page")
      dashboard.checkJourneyUrl("IM9003232323/review-registration")

      And(
        "the user clicks the Review your registration details link and is redirected to the registration service to view/amend"
      )
      dashboard.cssLink("start-amend-journey")
      dashboard.checkRegistrationJourneyUrl("change-your-registration")
    }

    Scenario("A user has not amended their registration for two years - Skip for now") {

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "IM9003232323", "Organisation", "hasIOSSEnrolment", "dashboard")
      dashboard.checkJourneyUrl("your-account")

      When("the user clicks on the 'Start your return' link")
      dashboard.clickLink("start-your-return")

      And("the user answers yes on the start page")
      dashboard.checkJourneyUrl("IM9003232323/2024-M1/start-return")
      dashboard.answerRadioButton("yes")

      Then("the user is on the review-registration page")
      dashboard.checkJourneyUrl("IM9003232323/review-registration")

      And("the user clicks the Skip for now button")
      dashboard.clickLink("skip")

      And("the user can continue with their return")
      dashboard.checkJourneyUrl("IM9003232323/want-to-upload-file")
    }
  }
}
