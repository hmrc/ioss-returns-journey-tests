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

  private val dashboard  = Dashboard
  private val auth       = Auth
  private val fileUpload = FileUpload
  private val payment    = Payment
  private val save       = Save

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

    Scenario(
      "A user enters the Start Your Return page via BTA, submits a nil return and has the correct link back to BTA"
    ) {

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

    Scenario(
      "A user with one outstanding payment clicks the BTA payment link and is directed to the payments service"
    ) {

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "IM9008888888", "Organisation", "withIOSSEnrolment", "dashboard")
      dashboard.checkJourneyUrl("your-account")

      When("the user manually navigates to the payments-from-bta link")
      dashboard.goToPage("test-only/payments-from-bta")

      Then("the user has been redirected to the payments service")
      dashboard.checkExternalServiceUrl("payments")
    }

    Scenario(
      "A user with multiple outstanding payments clicks the BTA payment link and is directed to the outstanding payments page"
    ) {

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

    Scenario(
      "A user with no outstanding payments clicks the BTA payment link and is directed to no outstanding payments page"
    ) {

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "IM9009999888", "Organisation", "withIOSSEnrolment", "dashboard")
      dashboard.checkJourneyUrl("your-account")

      When("the user manually navigates to the payments-from-bta link")
      dashboard.goToPage("test-only/payments-from-bta")

      And("the user is on the outstanding-payments page")
      dashboard.checkJourneyUrl("IM9009999888/outstanding-payments")

      Then("the user is informed that they do not owe any VAT")
      payment.noVatOwed()
    }

    Scenario(
      "A user with one outstanding payment clicks the Welsh version of the BTA payment link and is directed to the no Welsh service page before payments"
    ) {

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "IM9008888888", "Organisation", "withIOSSEnrolment", "dashboard")
      dashboard.checkJourneyUrl("your-account")

      When("the user manually navigates to the payments-from-bta?lang=cy link")
      dashboard.goToPage("test-only/payments-from-bta?lang=cy")

      And("the user is on the Welsh transition page")
      dashboard.checkExternalServiceUrl("no-welsh-service?redirectUrl")

      When("the user clicks the continue button")
      dashboard.continue()

      Then("the user has been redirected to the payments service")
      dashboard.checkExternalServiceUrl("payments")
    }

    Scenario(
      "A Welsh user enters the Your Account page via BTA and sees the Welsh transition page before 'Your Account'"
    ) {

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "IM9009999888", "Organisation", "withIOSSEnrolment", "dashboard")
      dashboard.checkJourneyUrl("your-account")

      When("the user manually navigates to the your-account-from-bta?lang=cy link")
      dashboard.goToPage("test-only/your-account-from-bta?lang=cy")

      And("the user is on the Welsh transition page")
      dashboard.checkExternalServiceUrl("no-welsh-service?redirectUrl")

      When("the user clicks the continue button")
      dashboard.continue()

      Then("the user has been redirected to the returns dashboard")
      dashboard.checkJourneyUrl("your-account")
    }

    Scenario(
      "A Welsh user enters the Start Your Return page via BTA and sees the Welsh transition page before they start their return"
    ) {

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "IM9009999888", "Organisation", "withIOSSEnrolment", "dashboard")
      dashboard.checkJourneyUrl("your-account")

      When("the user manually navigates to the start-your-return-from-bta/2023-M12?lang=cy link")
      dashboard.goToPage("test-only/start-your-return-from-bta/2023-M12?lang=cy")

      And("the user is on the Welsh transition page")
      dashboard.checkExternalServiceUrl("no-welsh-service?redirectUrl")

      When("the user clicks the continue button")
      dashboard.continue()

      Then("the user has been redirected to the start page of their return")
      dashboard.checkJourneyUrl("IM9009999888/2023-M12/start-return")
    }

    Scenario(
      "A user enters the Your Account page via BTA with the en specific parameter and is directed straight to 'Your Account'"
    ) {

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "IM9009999888", "Organisation", "withIOSSEnrolment", "dashboard")
      dashboard.checkJourneyUrl("your-account")

      When("the user manually navigates to the your-account-from-bta?lang=en link")
      dashboard.goToPage("test-only/your-account-from-bta?lang=en")

      Then("the user has been redirected to the returns dashboard")
      dashboard.checkJourneyUrl("your-account")
    }

    Scenario(
      "A user enters their returns history via BTA"
    ) {

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "IM9001234567", "Organisation", "withIOSSEnrolment", "dashboard")
      dashboard.checkJourneyUrl("your-account")

      When("the user manually navigates to the returns-history-from-bta link")
      dashboard.goToPage("test-only/returns-history-from-bta")

      Then("the user has been redirected to their past returns")
      dashboard.checkJourneyUrl("IM9001234567/past-returns")
    }

    Scenario(
      "A Welsh user enters their returns history via BTA and sees the Welsh page first"
    ) {

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "IM9001234567", "Organisation", "withIOSSEnrolment", "dashboard")
      dashboard.checkJourneyUrl("your-account")

      When("the user manually navigates to the returns-history-from-bta?lang=cy link")
      dashboard.goToPage("test-only/returns-history-from-bta?lang=cy")

      And("the user is on the Welsh transition page")
      dashboard.checkExternalServiceUrl("no-welsh-service?redirectUrl")

      When("the user clicks the continue button")
      dashboard.continue()

      Then("the user has been redirected to their past returns")
      dashboard.checkJourneyUrl("IM9001234567/past-returns")
    }

    Scenario("A user accesses a saved return via BTA") {
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000444", "IM9001112222", "Organisation", "hasIOSSEnrolment", "dashboard")
      dashboard.checkJourneyUrl("your-account")

      When("the user clicks on the 'Start your return' link")
      dashboard.clickLink("start-your-return")

      Then("the user answers questions on their return")
      dashboard.checkJourneyUrl("IM9001112222/2023-M12/start-return")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001112222/want-to-upload-file")
      fileUpload.selectFileUpload("No, enter them myself")
      dashboard.checkJourneyUrl("IM9001112222/sold-goods")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001112222/sold-to-country/1")
      dashboard.selectCountry("Austria")
      dashboard.checkJourneyUrl("IM9001112222/vat-rates-from-country/1")

      When("the user clicks the Save and come back later button")
      dashboard.clickLink("saveProgress")

      Then("the user is on the progress-saved page")
      save.checkProgressSaved("IM9001112222/2023-M12")

      When("the user manually navigates to the continue-return-from-bta/2023-M12 link")
      dashboard.goToPage("test-only/continue-return-from-bta/2023-M12")

      Then("the user is on the return-continue page")
      dashboard.checkJourneyUrl("IM9001112222/2023-M12/return-continue")
    }

    Scenario("A Welsh user accesses a saved return via BTA") {
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000444", "IM9001112222", "Organisation", "hasIOSSEnrolment", "dashboard")
      dashboard.checkJourneyUrl("your-account")

      When("the user clicks on the 'Start your return' link")
      dashboard.clickLink("start-your-return")

      Then("the user answers questions on their return")
      dashboard.checkJourneyUrl("IM9001112222/2023-M12/start-return")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001112222/want-to-upload-file")
      fileUpload.selectFileUpload("No, enter them myself")
      dashboard.checkJourneyUrl("IM9001112222/sold-goods")
      dashboard.answerRadioButton("yes")
      dashboard.checkJourneyUrl("IM9001112222/sold-to-country/1")
      dashboard.selectCountry("Austria")
      dashboard.checkJourneyUrl("IM9001112222/vat-rates-from-country/1")

      When("the user clicks the Save and come back later button")
      dashboard.clickLink("saveProgress")

      Then("the user is on the progress-saved page")
      save.checkProgressSaved("IM9001112222/2023-M12")

      When("the user manually navigates to the continue-return-from-bta/2023-M12?lang=cy link")
      dashboard.goToPage("test-only/continue-return-from-bta/2023-M12?lang=cy")

      And("the user is on the Welsh transition page")
      dashboard.checkExternalServiceUrl("no-welsh-service?redirectUrl")

      When("the user clicks the continue button")
      dashboard.continue()

      Then("the user is on the return-continue page")
      dashboard.checkJourneyUrl("IM9001112222/2023-M12/return-continue")
    }
  }
}
