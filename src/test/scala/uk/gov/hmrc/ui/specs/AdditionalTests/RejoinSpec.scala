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

class RejoinSpec extends BaseSpec {

  private val dashboard = Dashboard
  private val auth = Auth
  private val exclusion = Exclusion

  Feature("Rejoin journeys") {

    Scenario("A user can access Rejoin this service via Your Account in the Returns service") {

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "IM9039999994", "Organisation", "withIOSSEnrolment", "dashboard")

      When("the user is on their dashboard")
      dashboard.checkJourneyUrl("your-account")

      And("the user clicks on the Rejoin this service link")
      dashboard.clickLink("rejoin-scheme")

      Then("the user is redirected to the rejoin page in the IOSS Registration service")
      dashboard.checkRegistrationJourneyUrl("rejoin-registration")
    }

    Scenario("A user who has an exclusion effective date in the future does not have the rejoin this service link on the dashboard") {

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "IM9009999997", "Organisation", "withIOSSEnrolment", "dashboard")

      When("the user is on their dashboard")
      dashboard.checkJourneyUrl("your-account")

      Then("the link to Rejoin this service is not displayed on the dashboard")
      exclusion.noRejoinLink()
    }

    Scenario("A user who has an active quarantine does not have the rejoin this service link on the dashboard") {

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "IM9009999993", "Organisation", "withIOSSEnrolment", "dashboard")

      When("the user is on their dashboard")
      dashboard.checkJourneyUrl("your-account")

      Then("the link to Rejoin this service is not displayed on the dashboard")
      exclusion.noRejoinLink()
    }

    Scenario("A user with over 3 years of outstanding returns cannot rejoin until 3 years of outstanding returns are complete") {

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "IM9001239999", "Organisation", "withIOSSEnrolment", "dashboard")

      When("the user is on their dashboard")
      dashboard.checkJourneyUrl("your-account")

      Then("the link to Rejoin this service is not displayed on the dashboard")
      exclusion.noRejoinLink()
    }

    Scenario("A user with 1 outstanding return cannot rejoin until the outstanding return is complete") {

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "IM9029999994", "Organisation", "withIOSSEnrolment", "dashboard")

      When("the user is on their dashboard")
      dashboard.checkJourneyUrl("your-account")

      Then("the link to Rejoin this service is not displayed on the dashboard")
      exclusion.noRejoinLink()
    }
  }
}
