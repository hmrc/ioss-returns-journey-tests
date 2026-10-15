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

import uk.gov.hmrc.ui.pages.*
import uk.gov.hmrc.ui.specs.BaseSpec

class NETPChangeDateSpec extends BaseSpec {

  private val dashboard    = Dashboard
  private val auth         = Auth
  private val netp         = NETP
  private val intermediary = Intermediary

  Feature("Intermediary change date journeys") {

    Scenario(
      "Intermediary can progress a return for a NETP where the NETP registration has not been updated for over six months"
    ) {

      Given("the intermediary accesses the IOSS Returns Service on behalf of a NETP")
      auth.goToAuthorityWizard()
      auth.loginAsIntermediary("IN9002221222", "IM9002221221", "returns")

      And("the intermediary answers yes on the IM9002221221/2025-M3/start-return page")
      dashboard.checkJourneyUrl("IM9002221221/2025-M3/start-return")
      dashboard.answerRadioButton("yes")

      Then("the intermediary can continue with the client's return")
      dashboard.checkJourneyUrl("IM9002221221/want-to-upload-file")
    }

    Scenario(
      "Intermediary is redirected to their global IOS registration when they have both IOSS global and intermediary enrolments and both change dates are over 6 months old"
    ) {

      Given("the intermediary accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()
      auth.loginAsIntermediary("IN9002221222", "notApplicable", "doubleEnrolmentChangeDate")

      And("the user is on the select-account page")
      dashboard.checkJourneyUrl("select-account")

      When("the user selects the dashboard for number IM9003232323")
      dashboard.clickLink("IM9003232323")
      dashboard.continue()

      Then("the user is redirected to their IOSS account")
      intermediary.dashboardUrlCheck("global")

      Then("the user is able to progress their IOSS return")
      dashboard.clickLink("start-your-return")
      dashboard.checkJourneyUrl("IM9003232323/2024-M1/start-return")
      dashboard.answerRadioButton("yes")

      And(
        "the user clicks the Review your registration details link and is redirected to the global IOSS registration service to view/amend"
      )
      dashboard.checkJourneyUrl("IM9003232323/review-registration")
      dashboard.cssLink("start-amend-journey")
      dashboard.checkRegistrationJourneyUrl("change-your-registration")
    }
  }
}
