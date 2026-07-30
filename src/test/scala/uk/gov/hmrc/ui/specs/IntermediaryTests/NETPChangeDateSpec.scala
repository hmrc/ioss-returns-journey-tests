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

  private val dashboard        = Dashboard
  private val auth             = Auth
  private val netp = NETP

  Feature("NETP Change Date over two years journeys") {

    Scenario("Intermediary starts a return for a NETP and the NETP registration has not been updated for over two years - review registration") {

      Given("the intermediary accesses the IOSS Returns Service on behalf of a NETP")
      auth.goToAuthorityWizard()
      auth.loginAsIntermediary("IN9002221223", "IM9002221223", "returns")

      And("the intermediary answers yes on the IM9001144771/2025-M3/start-return page")
      dashboard.checkJourneyUrl("IM9002221223/2025-M3/start-return")
      dashboard.answerRadioButton("yes")

      When("the intermediary is on the review-registration page")
      dashboard.checkJourneyUrl("IM9002221223/review-registration")
      netp.checkInterceptHeading()

      And("the intermediary selects the Review their registration details link")
      dashboard.cssLink("start-amend-journey\\/IM9002221223")

      Then("the intermediary is redirected to review the client's registration")
      netp.checkJourneyUrl("change-your-registration")
      netp.reviewRegistrationCheck()
    }

    Scenario("Intermediary starts a return for a NETP and the NETP registration has not been updated for over two years - skip for now") {

      Given("the intermediary accesses the IOSS Returns Service on behalf of a NETP")
      auth.goToAuthorityWizard()
      auth.loginAsIntermediary("IN9002221223", "IM9002221223", "returns")

      And("the intermediary answers yes on the IM9001144771/2025-M3/start-return page")
      dashboard.checkJourneyUrl("IM9002221223/2025-M3/start-return")
      dashboard.answerRadioButton("yes")

      When("the intermediary is on the review-registration page")
      dashboard.checkJourneyUrl("IM9002221223/review-registration")
      netp.checkInterceptHeading()

      And("the intermediary selects the Skip for now button")
      dashboard.clickLink("skip")

      Then("the intermediary can continue with the client's return")
      dashboard.checkJourneyUrl("IM9002221223/want-to-upload-file")
    }
  }
}
