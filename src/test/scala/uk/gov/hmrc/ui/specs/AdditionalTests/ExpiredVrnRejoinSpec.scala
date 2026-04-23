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

class ExpiredVrnRejoinSpec extends BaseSpec {

  private val dashboard = Dashboard
  private val auth = Auth
  private val exclusion = Exclusion
  private val expiredVrn = ExpiredVrn

  Feature("Expired VRN rejoin journeys") {

    Scenario("A trader who has left the service with outstanding returns and now has an expired VRN") {

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("600000001", "IM9029999994", "Organisation", "withIOSSEnrolment", "dashboard")

      When("the user is on their dashboard")
      dashboard.checkJourneyUrl("your-account")

      Then("they are presented with the correct banner for expired VRN trader who has left the service and has outstanding returns")
      expiredVrn.excludedExpiredVrnPastOutstandingReturnsBanner()

      And("the link to Rejoin this service is not displayed on the dashboard")
      exclusion.noRejoinLink()
    }

    Scenario("A trader who has left the service with no outstanding returns and now has an expired VRN") {

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("600000001", "IM9039999994", "Organisation", "withIOSSEnrolment", "dashboard")

      When("the user is on their dashboard")
      dashboard.checkJourneyUrl("your-account")

      Then("they are presented with the correct banner for expired VRN trader who has left the service and has no outstanding returns")
      expiredVrn.excludedExpiredVrnPastNoOutstandingReturnsBanner()

      And("the link to Rejoin this service is not displayed on the dashboard")
      exclusion.noRejoinLink()
    }

    Scenario("A trader who has been removed from the service with no outstanding returns and now has an expired VRN") {

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("600000001", "IM9059999994", "Organisation", "withIOSSEnrolment", "dashboard")

      When("the user is on their dashboard")
      dashboard.checkJourneyUrl("your-account")

      Then("they are presented with the correct banner for expired VRN trader who has been removed from the service and has no outstanding returns")
      expiredVrn.removedExpiredVrnPastNoOutstandingReturnsBanner()

      And("the link to Rejoin this service is not displayed on the dashboard")
      exclusion.noRejoinLink()
    }

    Scenario("A trader who has left the service with outstanding returns older than 3 years and now has an expired VRN") {

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("600000001", "IM9001239999", "Organisation", "withIOSSEnrolment", "dashboard")

      When("the user is on their dashboard")
      dashboard.checkJourneyUrl("your-account")

      Then("they are presented with the correct banner for expired VRN trader who has left the service and has outstanding returns")
      expiredVrn.excludedExpiredVrnPastOutstandingReturnsBanner()

      And("a dashboard message is displayed for a return outstanding for more than 3 years")
      exclusion.returnsOverThreeYearsMessage()

      And("the link to Rejoin this service is not displayed on the dashboard")
      exclusion.noRejoinLink()
    }

    Scenario("A trader who has left the service with outstanding payments older than 3 years and now has an expired VRN") {

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("600000001", "IM9001238999", "Organisation", "withIOSSEnrolment", "dashboard")

      When("the user is on their dashboard")
      dashboard.checkJourneyUrl("your-account")

      Then("a dashboard message is displayed for a payment outstanding for more than 3 years")
      exclusion.paymentsOverThreeYearsMessage()

      And("the link to Rejoin this service is not displayed on the dashboard")
      exclusion.noRejoinLink()
    }
  }
}
