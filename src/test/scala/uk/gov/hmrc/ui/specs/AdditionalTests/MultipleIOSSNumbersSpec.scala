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

  private val dashboard    = Dashboard
  private val auth         = Auth
  private val multipleIOSS = MultipleIOSS
  private val pastReturn   = PastReturn

  Feature("Multiple IOSS Numbers journeys") {

    Scenario(
      "A user with one other IOSS number can view their current account and returns for their previous IOSS number"
    ) {

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()

      When("the user has one current and one previous IOSS registration")
      auth.loginUsingAuthorityWizard(
        "100000001",
        "IM9007230000",
        "Organisation",
        "onePreviousRegistration",
        "dashboard"
      )
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
      pastReturn.selectPastReturnLink(
        s"IM9007230000\\/past-returns\\/${MultipleIOSS.periodForMultipleRegistrations(3)}"
      )
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

      Then("their previous IOSS registration number is displayed on the page")
      multipleIOSS.correctIOSSNumber("IM9006230000")

      And("the user clicks the Show all sections accordion")
      pastReturn.showAllAccordion()

      Then("the user clicks on the first return month for the previous registration")
      pastReturn.selectPastReturnLink(
        s"past-returns\\/${MultipleIOSS.periodForMultipleRegistrations(6)}\\/IM9006230000"
      )
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

    Scenario(
      "A user with more than one other IOSS number can view their current account and returns for their previous IOSS number"
    ) {

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()

      When("the user has one current and two previous IOSS registrations")
      auth.loginUsingAuthorityWizard(
        "100000001",
        "IM9007230003",
        "Organisation",
        "multiplePreviousRegistrations",
        "dashboard"
      )
      dashboard.checkJourneyUrl("your-account")

      Then("their current IOSS registration number is displayed on the dashboard")
      multipleIOSS.correctIOSSNumber("IM9007230003")

      When("the user clicks on the 'View submitted returns' link")
      dashboard.clickLink("view-submitted-returns")

      Then("the user is on the past-returns page")
      dashboard.checkJourneyUrl("IM9007230003/past-returns")

      And("the correct link for one previous IOSS number is displayed")
      multipleIOSS.linkForPreviousRegistrations("moreThanOne")

      And("the user clicks the Show all sections accordion")
      pastReturn.showAllAccordion()

      When("the user clicks on the first return month for their current registration")
      pastReturn.selectPastReturnLink(
        s"IM9007230003\\/past-returns\\/${MultipleIOSS.periodForMultipleRegistrations(3)}"
      )
      dashboard.checkJourneyUrl(s"IM9007230003/past-returns/${MultipleIOSS.periodForMultipleRegistrations(3)}")

      And("the correct returns and payments references are shown for IM9007230003")
      pastReturn.returnsAndPaymentReferences("IM9007230003")

      Then("the user clicks back on the browser")
      dashboard.clickBackButton()

      And("the user is on the IM9007230003/past-returns page")
      dashboard.checkJourneyUrl("IM9007230003/past-returns")

      When("the user clicks on the view returns link for multiple previous registrations")
      dashboard.clickLink("return-registration-selection")

      And("the user is on the IM9007230003/return-registration-selection page")
      dashboard.checkJourneyUrl("IM9007230003/return-registration-selection")

      And("the correct previous IOSS numbers are displayed")
      multipleIOSS.previousIOSSNumbers()

      And("the user selects the returns for IOSS number IM9007230001")
      dashboard.clickLink("IM9007230001")
      dashboard.continue()

      And("the user is on the IM9007230003/view-returns-multiple-reg page")
      dashboard.checkJourneyUrl("IM9007230003/view-returns-multiple-reg")

      Then("the correct previous IOSS registration number IM9007230001 is displayed on the page")
      multipleIOSS.correctIOSSNumber("IM9007230001")

      And("the user clicks the Show all sections accordion")
      pastReturn.showAllAccordion()

      Then("the user clicks on the first return month for the previous registration")
      pastReturn.selectPastReturnLink(
        s"past-returns\\/${MultipleIOSS.periodForMultipleRegistrations(9)}\\/IM9007230001"
      )
      dashboard.checkJourneyUrl(s"past-returns/${MultipleIOSS.periodForMultipleRegistrations(9)}/IM9007230001")

      And("the correct returns and payments references are shown for IM9007230001")
      pastReturn.returnsAndPaymentReferences("IM9007230001")

      Then("the user clicks back on the browser")
      dashboard.clickBackButton()

      And("the user is on the IM9007230003/view-returns-multiple-reg page")
      dashboard.checkJourneyUrl("IM9007230003/view-returns-multiple-reg")

      When("the user clicks on the Return to your current registration link")
      dashboard.clickLink("submitted-returns-history")

      And("the user is on the IM9007230003/past-returns page")
      dashboard.checkJourneyUrl("IM9007230003/past-returns")

      When("the user clicks on the view returns link for multiple previous registrations")
      dashboard.clickLink("return-registration-selection")

      And("the user is on the IM9007230003/return-registration-selection page")
      dashboard.checkJourneyUrl("IM9007230003/return-registration-selection")

      And("the user selects the returns for IOSS number IM9007230002")
      dashboard.clickLink("IM9007230002")
      dashboard.continue()

      And("the user is on the IM9007230003/view-returns-multiple-reg page")
      dashboard.checkJourneyUrl("IM9007230003/view-returns-multiple-reg")

      Then("the correct previous IOSS registration number IM9007230002 is displayed on the page")
      multipleIOSS.correctIOSSNumber("IM9007230002")

      And("the user clicks the Show all sections accordion")
      pastReturn.showAllAccordion()

      Then("the user clicks on the first return month for the previous registration")
      pastReturn.selectPastReturnLink(
        s"past-returns\\/${MultipleIOSS.periodForMultipleRegistrations(6)}\\/IM9007230002"
      )
      dashboard.checkJourneyUrl(s"past-returns/${MultipleIOSS.periodForMultipleRegistrations(6)}/IM9007230002")

      And("the correct returns and payments references are shown for IM9007230002")
      pastReturn.returnsAndPaymentReferences("IM9007230002")
    }

    Scenario(
      "A user with one previous registration that has multiple outstanding payments"
    ) {

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()

      When("the user has one current and one previous IOSS registration")
      auth.loginUsingAuthorityWizard(
        "100000001",
        "IM9007230000",
        "Organisation",
        "onePreviousRegistration",
        "dashboard"
      )
      dashboard.checkJourneyUrl("your-account")

      Then(
        "the dashboard warning is displayed regarding multiple outstanding payments on their one previous registration IM9006230000"
      )
      multipleIOSS.dashboardWarningOutstandingPaymentsMultipleRegistrations(
        "multiplePaymentsOneRegistration",
        "IM9006230000"
      )

      When("the user clicks Pay for a previous registration link")
      dashboard.cssLink("which-previous-registration-to-pay")

      Then(
        "the user selects the first payment option on the IM9007230000/which-previous-registration-vat-month-to-pay page"
      )
      dashboard.checkJourneyUrl("IM9007230000/which-previous-registration-vat-month-to-pay")
      dashboard.clickLink("value_0")
      dashboard.continue()

      And("the user has been redirected to the payments service")
      dashboard.checkExternalServiceUrl("payments")
    }

    Scenario(
      "A user with one previous registration that has one outstanding payment"
    ) {

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()

      When("the user has one current and one previous IOSS registration")
      auth.loginUsingAuthorityWizard(
        "100000001",
        "IM9005230000",
        "Organisation",
        "onePreviousRegistration",
        "dashboard"
      )
      dashboard.checkJourneyUrl("your-account")

      Then(
        "the dashboard warning is displayed regarding one outstanding payment on their one previous registration IM9004230000"
      )
      multipleIOSS.dashboardWarningOutstandingPaymentsMultipleRegistrations(
        "onePaymentOneRegistration",
        "IM9004230000"
      )

      When("the user clicks Pay for a previous registration link")
      dashboard.cssLink("which-previous-registration-to-pay")

      And("the user has been redirected to the payments service")
      dashboard.checkExternalServiceUrl("payments")
    }

    Scenario(
      "A user with more than one previous registration that has multiple outstanding payments"
    ) {

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()

      When("the user has one current and two previous IOSS registrations")
      auth.loginUsingAuthorityWizard(
        "100000001",
        "IM9007230003",
        "Organisation",
        "multiplePreviousRegistrations",
        "dashboard"
      )
      dashboard.checkJourneyUrl("your-account")

      Then(
        "the dashboard warning is displayed regarding multiple outstanding payments on their previous registrations"
      )
      multipleIOSS.dashboardWarningOutstandingPaymentsMultipleRegistrations(
        "multiplePaymentsMultipleRegistrations",
        "IM9007230003"
      )

      When("the user clicks Pay for a previous registration link")
      dashboard.cssLink("which-previous-registration-to-pay")

      And("the correct multiple payment amounts are displayed for each previous registration")
      dashboard.checkJourneyUrl("IM9007230003/which-previous-registration-to-pay")
      multipleIOSS.outstandingPaymentAmounts("multiple")

      Then(
        "the user selects the first previous ioss number on the IM9007230003/which-previous-registration-to-pay page"
      )
      dashboard.clickLink("value_0")
      dashboard.continue()

      And(
        "the user selects the first payment option on the IM9007230003/which-previous-registration-vat-month-to-pay page"
      )
      dashboard.checkJourneyUrl("IM9007230003/which-previous-registration-vat-month-to-pay")
      dashboard.clickLink("value_0")
      dashboard.continue()

      And("the user has been redirected to the payments service")
      dashboard.checkExternalServiceUrl("payments")
    }

    Scenario(
      "A user with more than one previous registration that has single outstanding payments"
    ) {

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()

      When("the user has one current and two previous IOSS registrations")
      auth.loginUsingAuthorityWizard(
        "100000001",
        "IM9007230006",
        "Organisation",
        "multiplePreviousRegistrations",
        "dashboard"
      )
      dashboard.checkJourneyUrl("your-account")

      Then(
        "the dashboard warning is displayed regarding one outstanding payment on their previous registrations"
      )
      multipleIOSS.dashboardWarningOutstandingPaymentsMultipleRegistrations(
        "onePaymentMultipleRegistrations",
        "IM9007230006"
      )

      When("the user clicks Pay for a previous registration link")
      dashboard.cssLink("which-previous-registration-to-pay")

      And("the correct single payment amounts are displayed for each previous registration")
      dashboard.checkJourneyUrl("IM9007230006/which-previous-registration-to-pay")
      multipleIOSS.outstandingPaymentAmounts("single")

      Then(
        "the user selects the second previous ioss number on the IM9007230006/which-previous-registration-to-pay page"
      )
      dashboard.clickLink("value_1")
      dashboard.continue()

      And("the user has been redirected to the payments service")
      dashboard.checkExternalServiceUrl("payments")
    }
  }
}
