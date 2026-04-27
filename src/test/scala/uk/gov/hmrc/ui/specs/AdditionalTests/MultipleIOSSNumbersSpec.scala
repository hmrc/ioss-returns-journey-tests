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

class MultipleIOSSNumbersSpec extends BaseSpec {

  private val dashboard = Dashboard
  private val auth = Auth
  private val multipleIOSS = MultipleIOSS
  private val pastReturn = PastReturn

  Feature("Multiple IOSS Numbers journeys") {

    Scenario("A user who has transferred from another member state has a partial first return") {

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()

      When("the user has one current and one previous IOSS registration")
      auth.loginUsingAuthorityWizard("100000001", "IM9007230000", "Organisation", "onePreviousRegistration", "dashboard")
      dashboard.checkJourneyUrl("your-account")

      Then("their current IOSS registration number is displayed on the dashboard")
      multipleIOSS.correctIOSSNumber("IM9007230000")

      When("the user clicks on the 'View submitted returns' link")
      dashboard.clickLink("view-submitted-returns")

      Then("the user is on the past-returns page")
      dashboard.checkJourneyUrl("IM9007230000/past-returns")

      And("the correct link for one previous IOSS number is displayed")
      multipleIOSS.linkForPreviousRegistrations("one")

      And("the user clicks the Show all sections accordion")
      pastReturn.showAllAccordion()

      When("the user clicks on the first return month for their current registration")
      pastReturn.selectPastReturnLink(s"IM9007230000\\/past-returns\\/${MultipleIOSS.periodForMultipleRegistrations(3)}")
      dashboard.checkJourneyUrl(s"IM9007230000/past-returns/${MultipleIOSS.periodForMultipleRegistrations(3)}")

      And("the correct returns and payments references are shown for IM9007230000")
      pastReturn.returnsAndPaymentReferences("IM9007230000")

      Then("the user clicks back on the browser")
      dashboard.clickBackButton()

      And("the user is on the IM9007230000/past-returns page")
      dashboard.checkJourneyUrl("IM9007230000/past-returns")

      When("the user clicks on the view returns link for their previous registration")
      dashboard.clickLink("view-returns-one-reg")

      And("the user is on the IM9007230000/view-returns-multiple-reg page")
      dashboard.checkJourneyUrl("IM9007230000/view-returns-multiple-reg")

      Then("their previous IOSS registration number is displayed on the dashboard")
      multipleIOSS.correctIOSSNumber("IM9006230000")

      And("the user clicks the Show all sections accordion")
      pastReturn.showAllAccordion()

      Then("the user clicks on the first return month for the previous registration")
      pastReturn.selectPastReturnLink(s"past-returns\\/${MultipleIOSS.periodForMultipleRegistrations(6)}\\/IM9006230000")
      dashboard.checkJourneyUrl(s"past-returns/${MultipleIOSS.periodForMultipleRegistrations(6)}/IM9006230000")

      And("the correct returns and payments references are shown for IM9006230000")
      pastReturn.returnsAndPaymentReferences("IM9006230000")

      Then("the user clicks back on the browser")
      dashboard.clickBackButton()

      And("the user is on the IM9007230000/view-returns-multiple-reg page")
      dashboard.checkJourneyUrl("IM9007230000/view-returns-multiple-reg")

      When("the user clicks on the Return to your current registration link")
      dashboard.clickLink("submitted-returns-history")

      And("the user is on the IM9007230000/past-returns page")
      dashboard.checkJourneyUrl("IM9007230000/past-returns")
    }
  }
}
