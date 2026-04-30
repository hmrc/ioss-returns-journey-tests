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

package uk.gov.hmrc.ui.pages

import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedConditions
import org.scalatest.matchers.should.Matchers._
import uk.gov.hmrc.configuration.TestEnvironment

object Auth extends BasePage {

  private val authUrl: String           = TestEnvironment.url("auth-login-stub") + "/auth-login-stub/gg-sign-in"
  private val returnsUrl: String        =
    TestEnvironment.url("ioss-returns-frontend")
  private val returnsJourneyUrl: String = "/pay-vat-on-goods-sold-to-eu/import-one-stop-shop-returns-payments"

  def goToAuthorityWizard(): Unit = {
    get(authUrl)
    fluentWait.until(ExpectedConditions.urlContains(authUrl))
  }

  def checkAuthUrl(): Unit =
    getCurrentUrl should startWith(authUrl)

  def loginUsingAuthorityWizard(
    vrn: String,
    iossNumber: String,
    affinityGroup: String,
    accountType: String,
    journey: String
  ): Unit = {

    getCurrentUrl should startWith(authUrl)

    sendKeys(By.name("redirectionUrl"), s"$returnsUrl$returnsJourneyUrl")

    selectByValue(By.id("affinityGroupSelect"), "Organisation")

    if (affinityGroup == "OrganisationAssistant") {
      selectByValue(By.id("credential-role-select"), "Assistant")
    }

    if (vrn != "None") {
      sendKeys(By.id("enrolment[0].name"), "HMRC-MTD-VAT")
      sendKeys(By.id("input-0-0-name"), "VRN")
      sendKeys(By.id("input-0-0-value"), vrn)
    }

    if (accountType != "vatOnly") {
      sendKeys(By.id("enrolment[1].name"), "HMRC-IOSS-ORG")
      sendKeys(By.id("input-1-0-name"), "IOSSNumber")

      if (iossNumber != "notApplicable") {
        sendKeys(By.id("input-1-0-value"), iossNumber)
      }

      if (accountType == "onePreviousRegistration") {
        sendKeys(By.id("enrolment[2].name"), "HMRC-IOSS-ORG")
        sendKeys(By.id("input-2-0-name"), "IOSSNumber")
        sendKeys(By.id("input-2-0-value"), "IM9006230000")
      }

      if (accountType == "multiplePreviousRegistrations") {
        sendKeys(By.id("enrolment[2].name"), "HMRC-IOSS-ORG")
        sendKeys(By.id("input-2-0-name"), "IOSSNumber")
        sendKeys(By.id("input-2-0-value"), "IM9007230002")

        sendKeys(By.id("enrolment[3].name"), "HMRC-IOSS-ORG")
        sendKeys(By.id("input-3-0-name"), "IOSSNumber")
        sendKeys(By.id("input-3-0-value"), "IM9007230001")
      }
    }

    click(By.cssSelector("Input[value='Submit']"))

  }

  def loginAsIntermediary(
    intermediaryNumber: String,
    iossNumber: String,
    journey: String
  ): Unit = {

    getCurrentUrl should startWith(authUrl)

    val endpoint = if (journey == "returns" || journey == "doubleEnrolmentNetpReturns" || journey == "savedReturn") {
      s"start-return-as-intermediary/$iossNumber"
    } else if (journey == "doubleEnrolmentGlobalReturns") {
      ""
    } else if (journey == "payments") {
      s"start-payment-as-intermediary/$iossNumber"
    } else {
      s"start-returns-history-as-intermediary/$iossNumber"
    }

    sendKeys(By.id("redirectionUrl"), s"$returnsUrl$returnsJourneyUrl/$endpoint")

    selectByValue(By.id("affinityGroupSelect"), "Organisation")

    sendKeys(By.id("enrolment[0].name"), "HMRC-MTD-VAT")
    sendKeys(By.id("input-0-0-name"), "VRN")
    sendKeys(By.id("input-0-0-value"), "100000001")

    sendKeys(By.id("enrolment[1].name"), "HMRC-IOSS-INT")
    sendKeys(By.id("input-1-0-name"), "IntNumber")
    sendKeys(By.id("input-1-0-value"), intermediaryNumber)

    if (intermediaryNumber == "IN9002230002") {
      sendKeys(By.id("enrolment[2].name"), "HMRC-IOSS-INT")
      sendKeys(By.id("input-2-0-name"), "IntNumber")
      sendKeys(By.id("input-2-0-value"), "IN9001230002")

      sendKeys(By.id("enrolment[3].name"), "HMRC-IOSS-INT")
      sendKeys(By.id("input-3-0-name"), "IntNumber")
      sendKeys(By.id("input-3-0-value"), "IN9000230002")
    }

    if (journey == "doubleEnrolmentNetpReturns" || journey == "doubleEnrolmentGlobalReturns") {
      sendKeys(By.id("enrolment[2].name"), "HMRC-IOSS-ORG")
      sendKeys(By.id("input-2-0-name"), "IOSSNumber")
      sendKeys(By.id("input-2-0-value"), "IM9001234567")
    }

    click(By.cssSelector("Input[value='Submit']"))
  }
}
