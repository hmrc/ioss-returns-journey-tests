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

package uk.gov.hmrc.ui.specs.ReturnsTests

import uk.gov.hmrc.ui.pages._
import uk.gov.hmrc.ui.specs.BaseSpec

class PastReturnSpec extends BaseSpec {

  private val dashboard  = Dashboard
  private val auth       = Auth
  private val pastReturn = PastReturn

  Feature("Past Returns journeys") {

    Scenario("A user can view previously submitted returns for October and November 2023") {

      Given("the user accesses the IOSS Returns Service with no IOSS Enrolment")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "IM9001234567", "Organisation", "hasIOSSEnrolment", "dashboard")
      dashboard.checkJourneyUrl("your-account")

      When("the user clicks on the 'View submitted returns' link")
      dashboard.clickLink("view-submitted-returns")

      Then("the user is on the past-returns page")
      dashboard.checkJourneyUrl("IM9001234567/past-returns")

      And("the user clicks the Show all sections accordion")
      pastReturn.showAllAccordion()

      When("the user clicks on the November 2023 link")
      pastReturn.selectPastReturnLink("past-returns\\/2023-M11")

      Then("the user is shown the correct previously submitted return")
      dashboard.checkJourneyUrl("IM9001234567/past-returns/2023-M11")

      And("the return for November 2023 is displayed to the user")
      pastReturn.returnForMonth("November 2023")

      And("the correction sections are displayed on the previous return with sales to EU and corrections")
      pastReturn.returnWithEUSalesAndCorrections()

      And("the user clicks the Pay Now button")
      dashboard.clickLink("pay-now")

      And("the user has been redirected to the payments service")
      dashboard.checkExternalServiceUrl("payments")

      When("the user manually navigates back to the returns service")
      dashboard.goToDashboardJourney()

      When("the user clicks on the 'View submitted returns' link")
      dashboard.clickLink("view-submitted-returns")

      Then("the user is on the past-returns page")
      dashboard.checkJourneyUrl("IM9001234567/past-returns")

      And("the user clicks the Show all sections accordion")
      pastReturn.showAllAccordion()

      When("the user clicks on the October 2023 link")
      pastReturn.selectPastReturnLink("past-returns\\/2023-M10")

      Then("the user is shown the correct previously submitted return")
      dashboard.checkJourneyUrl("IM9001234567/past-returns/2023-M10")

      And("the return for October 2023 is displayed to the user")
      pastReturn.returnForMonth("October 2023")

      And("the correction sections are displayed on the previous return with no corrections")
      pastReturn.returnWithNoCorrections()

      When("the user manually navigates back to the returns service")
      dashboard.goToDashboardJourney()

      When("the user clicks on the 'View submitted returns' link")
      dashboard.clickLink("view-submitted-returns")

      Then("the user is on the past-returns page")
      dashboard.checkJourneyUrl("IM9001234567/past-returns")

      And("the user clicks the Show all sections accordion")
      pastReturn.showAllAccordion()

      When("the user clicks the Pay now link for October 2023")
      pastReturn.selectPastReturnLink("make-payment\\/2023-M10")

      Then("the user has been redirected to the payments service")
      dashboard.checkExternalServiceUrl("payments")
    }

    Scenario("A user can view previously submitted returns over multiple years") {

      Given("the user accesses the IOSS Returns Service with no IOSS Enrolment")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "IM9008888882", "Organisation", "hasIOSSEnrolment", "dashboard")
      dashboard.checkJourneyUrl("your-account")

      When("the user clicks on the 'View submitted returns' link")
      dashboard.clickLink("view-submitted-returns")

      Then("the user is on the past-returns page")
      dashboard.checkJourneyUrl("IM9008888882/past-returns")

      And("the user clicks the Show all sections accordion")
      pastReturn.showAllAccordion()

      When("the user clicks on the November 2022 link")
      pastReturn.selectPastReturnLink("past-returns\\/2022-M11")

      Then("the user is shown the correct previously submitted return")
      dashboard.checkJourneyUrl("IM9008888882/past-returns/2022-M11")

      And("the return for November 2022 is displayed to the user")
      pastReturn.returnForMonth("November 2022")

      And("the correction sections are displayed on the previous return with sales to EU and corrections")
      pastReturn.returnWithEUSalesAndCorrections()

      When("the user manually navigates back to the returns service")
      dashboard.goToDashboardJourney()

      When("the user clicks on the 'View submitted returns' link")
      dashboard.clickLink("view-submitted-returns")

      Then("the user is on the past-returns page")
      dashboard.checkJourneyUrl("IM9008888882/past-returns")

      And("the user clicks the Show all sections accordion")
      pastReturn.showAllAccordion()

      When("the user clicks on the December 2022 link")
      pastReturn.selectPastReturnLink("past-returns\\/2022-M12")

      Then("the user is shown the correct previously submitted return")
      dashboard.checkJourneyUrl("IM9008888882/past-returns/2022-M12")

      And("the return for December 2022 is displayed to the user")
      pastReturn.returnForMonth("December 2022")

      And("the correction sections are displayed on the previous return with sales to EU and corrections")
      pastReturn.returnWithEUSalesAndCorrections()

      When("the user manually navigates back to the returns service")
      dashboard.goToDashboardJourney()

      When("the user clicks on the 'View submitted returns' link")
      dashboard.clickLink("view-submitted-returns")

      Then("the user is on the past-returns page")
      dashboard.checkJourneyUrl("IM9008888882/past-returns")

      And("the user clicks the Show all sections accordion")
      pastReturn.showAllAccordion()

      When("the user clicks on the December 2023 link")
      pastReturn.selectPastReturnLink("past-returns\\/2023-M12")

      Then("the user is shown the correct previously submitted return")
      dashboard.checkJourneyUrl("IM9008888882/past-returns/2023-M12")

      And("the return for December 2023 is displayed to the user")
      pastReturn.returnForMonth("December 2023")

      And("the correction sections are displayed on the previous return with sales to EU and corrections")
      pastReturn.returnWithEUSalesAndCorrections()
    }

    Scenario("A user can view a previously submitted nil return") {

      Given("the user accesses the IOSS Returns Service with no IOSS Enrolment")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "IM9003333333", "Organisation", "hasIOSSEnrolment", "dashboard")
      dashboard.checkJourneyUrl("your-account")

      When("the user clicks on the 'View submitted returns' link")
      dashboard.clickLink("view-submitted-returns")

      Then("the user is on the past-returns page")
      dashboard.checkJourneyUrl("IM9003333333/past-returns")

      And("the user clicks the Show all sections accordion")
      pastReturn.showAllAccordion()

      When("the user clicks on the December 2023 link")
      pastReturn.selectPastReturnLink("past-returns\\/2023-M12")

      Then("the user is shown the correct previously submitted return")
      dashboard.checkJourneyUrl("IM9003333333/past-returns/2023-M12")

      And("the return for December 2023 is displayed to the user")
      pastReturn.returnForMonth("December 2023")

      And("the correction sections are displayed on the previous return for a nil return")
      pastReturn.nilReturn()
    }

    Scenario("A user can view a previously submitted return with no corrections") {

      Given("the user accesses the IOSS Returns Service with no IOSS Enrolment")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "IM9004444444", "Organisation", "hasIOSSEnrolment", "dashboard")
      dashboard.checkJourneyUrl("your-account")

      When("the user clicks on the 'View submitted returns' link")
      dashboard.clickLink("view-submitted-returns")

      Then("the user is on the past-returns page")
      dashboard.checkJourneyUrl("IM9004444444/past-returns")

      And("the user clicks the Show all sections accordion")
      pastReturn.showAllAccordion()

      When("the user clicks on the December 2023 link")
      pastReturn.selectPastReturnLink("past-returns\\/2023-M12")

      Then("the user is shown the correct previously submitted return")
      dashboard.checkJourneyUrl("IM9004444444/past-returns/2023-M12")

      And("the return for December 2023 is displayed to the user")
      pastReturn.returnForMonth("December 2023")

      And("the correction sections are displayed on the previous return with no corrections")
      pastReturn.returnWithNoCorrections()
    }

    Scenario("A user is not able to submit a duplicate return") {

      Given("the user accesses the IOSS Returns Service with no IOSS Enrolment")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "IM9001234567", "Organisation", "hasIOSSEnrolment", "dashboard")
      dashboard.checkJourneyUrl("your-account")

      When("the user manually navigates to the start page for their November 2023 return")
      dashboard.goToPage("IM9001234567/2023-M11/start-return")

      Then("the user is shown the previously submitted return")
      dashboard.checkJourneyUrl("IM9001234567/past-returns/2023-M11")
    }

    Scenario("A user is not able view a submitted return for a month over 6 years ago") {

      Given("the user accesses the IOSS Returns Service with no IOSS Enrolment")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "IM9001236666", "Organisation", "hasIOSSEnrolment", "dashboard")
      dashboard.checkJourneyUrl("your-account")

      When("the user manually navigates to the start page for their January 2018 return")
      dashboard.goToPage("IM9001236666/2018-M1/start-return")

      Then("the user is shown the no-longer-able-to-view-return page")
      dashboard.checkJourneyUrl("IM9001236666/no-longer-able-to-view-return")

      When("the user manually navigates to the start page for their September 2018 return")
      dashboard.goToPage("IM9001236666/2018-M9/start-return")

      Then("the user is shown the no-longer-able-to-view-return page")
      dashboard.checkJourneyUrl("IM9001236666/no-longer-able-to-view-return")
    }

    Scenario("A user with a full return period does not show partial return dates") {

      Given("the user accesses the IOSS Returns Service with no IOSS Enrolment")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "IM9001236667", "Organisation", "hasIOSSEnrolment", "dashboard")
      dashboard.checkJourneyUrl("your-account")

      When("the user manually navigates to the start page for their December 2024 return")
      dashboard.goToPage("IM9001236667/2024-M12/start-return")

      Then("the user is shown the previously submitted return")
      dashboard.checkJourneyUrl("IM9001236667/past-returns/2024-M12")

      And("the past return shows the full month version of the heading")
      pastReturn.fullMonthHeading()
    }
  }
}
