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

class PaymentSpec extends BaseSpec {

  private val dashboard = Dashboard
  private val auth = Auth
  private val payment = Payment

  Feature("Payment journeys") {

    Scenario("A user has multiple outstanding payments") {

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "IM9001234567", "Organisation", "hasIOSSEnrolment", "dashboard")
      dashboard.checkJourneyUrl("your-account")

      When("the user clicks on the Make a payment link")
      dashboard.clickLink("make-a-payment")

      Then("the user is on the outstanding-payments page")
      dashboard.checkJourneyUrl("IM9001234567/outstanding-payments")

      And("the user selects the second payment option")
      dashboard.clickLink("value_1")
      dashboard.continue()

      Then("the user has been redirected to the payments service")
      dashboard.checkExternalServiceUrl("payments")
    }

    Scenario("A user has a single outstanding payment") {

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "IM9008888888", "Organisation", "hasIOSSEnrolment", "dashboard")
      dashboard.checkJourneyUrl("your-account")

      When("the user clicks on the Make a payment link")
      dashboard.clickLink("make-a-payment")

      Then("the user has been redirected to the payments service")
      dashboard.checkExternalServiceUrl("payments")
    }

    Scenario("A user has no payments due on first return which has not been submitted yet") {

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "IM9009999888", "Organisation", "hasIOSSEnrolment", "dashboard")

      When("the user is on their dashboard")
      dashboard.checkJourneyUrl("your-account")

      Then("the payments tile shows no outstanding payments")
      payment.noPaymentsTile()

      When("the user manually navigates to the outstanding payments page")
      dashboard.goToPage("IM9009999888/outstanding-payments")

      Then("the user is shown that they do not owe any VAT")
      dashboard.checkJourneyUrl("IM9009999888/outstanding-payments")
      payment.noVatOwed()
    }

    Scenario("A user has no payments due as submitted returns are fully paid") {

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "IM9008888887", "Organisation", "hasIOSSEnrolment", "dashboard")

      When("the user is on their dashboard")
      dashboard.checkJourneyUrl("your-account")

      Then("the payments tile shows no outstanding payments")
      payment.noPaymentsTile()

      When("the user manually navigates to the outstanding payments page")
      dashboard.goToPage("IM9008888887/outstanding-payments")

      Then("the user is shown that they do not owe any VAT")
      dashboard.checkJourneyUrl("IM9008888887/outstanding-payments")
      payment.noVatOwed()
    }

    Scenario("A user has submitted three returns, one fully paid, one overdue and one due") {

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "IM9008888886", "Organisation", "hasIOSSEnrolment", "dashboard")
      dashboard.checkJourneyUrl("your-account")

      And("the payments tile shows one payment due and one payment overdue")
      payment.onePaymentDueOneOverdue()

      When("the user clicks on the Make a payment link")
      dashboard.clickLink("make-a-payment")

      Then("the user is on the outstanding-payments page")
      dashboard.checkJourneyUrl("IM9008888886/outstanding-payments")

      And("the user selects the second payment option")
      dashboard.clickLink("value_0")
      dashboard.continue()

      Then("the user has been redirected to the payments service")
      dashboard.checkExternalServiceUrl("payments")
    }

    Scenario("A user has submitted one return and the payment is due") {

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "IM9008888885", "Organisation", "hasIOSSEnrolment", "dashboard")
      dashboard.checkJourneyUrl("your-account")

      And("the payments tile shows a single payment due")
      payment.singlePaymentDue()

      When("the user clicks on the Make a payment link")
      dashboard.clickLink("make-a-payment")

      Then("the user has been redirected to the payments service")
      dashboard.checkExternalServiceUrl("paymentsWithDate")
    }

    Scenario("An error has occurred with the payments API and the dashboard text is amended appropriately") {

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "IM9001231231", "Organisation", "hasIOSSEnrolment", "dashboard")

      When("the user is on their dashboard")
      dashboard.checkJourneyUrl("your-account")

      Then("the payments tile shows that the trader may still owe VAT")
      payment.errorTile()
    }
  }
}
