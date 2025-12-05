/*
 * Copyright 2025 HM Revenue & Customs
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

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By
import org.openqa.selenium.support.ui.Select
import uk.gov.hmrc.test.ui.conf.TestConfiguration

object AuthPage extends BasePage {

  def loginUsingAuthorityWizard(
    user: String,
    withStatus: String,
    enrolment: String,
    vrn: String,
    iossNumber: String
  ): Unit = {

    val stubUrl: String = TestConfiguration.url("auth-login-stub") + "/gg-sign-in"
    driver.getCurrentUrl should startWith(stubUrl)

    driver.findElement(By.id("redirectionUrl")).sendKeys(TestConfiguration.url("ioss-returns-frontend"))

    val selectAffinityGroup = new Select(driver.findElement(By.id("affinityGroupSelect")))
    selectAffinityGroup.selectByValue("Organisation")

    if (user == "assistant") {
      val selectCredentialRole = new Select(driver.findElement(By.id("credential-role-select")))
      selectCredentialRole.selectByValue("Assistant")
    }

    if (withStatus == "with") {
      driver.findElement(By.id("enrolment[0].name")).sendKeys("HMRC-MTD-VAT")
      driver
        .findElement(By.id("input-0-0-name"))
        .sendKeys("VRN")
      driver
        .findElement(By.id("input-0-0-value"))
        .sendKeys(vrn)
      if (enrolment == "IOSS and VAT") {
        driver.findElement(By.id("enrolment[1].name")).sendKeys("HMRC-IOSS-ORG")
        driver
          .findElement(By.id("input-1-0-name"))
          .sendKeys("IOSSNumber")
        if (iossNumber == "default") {
          driver
            .findElement(By.id("input-1-0-value"))
            .sendKeys("IM9001234567")
        } else {
          driver
            .findElement(By.id("input-1-0-value"))
            .sendKeys(iossNumber)
          if (iossNumber == "IM9007230000") {
            driver.findElement(By.id("enrolment[2].name")).sendKeys("HMRC-IOSS-ORG")
            driver
              .findElement(By.id("input-2-0-value"))
              .sendKeys("IM9006230000")
          } else if (iossNumber == "IM9005230000") {
            driver.findElement(By.id("enrolment[2].name")).sendKeys("HMRC-IOSS-ORG")
            driver
              .findElement(By.id("input-2-0-value"))
              .sendKeys("IM9004230000")
          } else if (iossNumber == "IM9007230003") {
            driver.findElement(By.id("enrolment[2].name")).sendKeys("HMRC-IOSS-ORG")
            driver
              .findElement(By.id("input-2-0-value"))
              .sendKeys("IM9007230002")
            driver.findElement(By.id("enrolment[3].name")).sendKeys("HMRC-IOSS-ORG")
            driver
              .findElement(By.id("input-3-0-value"))
              .sendKeys("IM9007230001")
          } else if (iossNumber == "IM9007230006") {
            driver.findElement(By.id("enrolment[2].name")).sendKeys("HMRC-IOSS-ORG")
            driver
              .findElement(By.id("input-2-0-value"))
              .sendKeys("IM9007230005")
            driver.findElement(By.id("enrolment[3].name")).sendKeys("HMRC-IOSS-ORG")
            driver
              .findElement(By.id("input-3-0-value"))
              .sendKeys("IM9007230004")
          }
        }
      }
    }
    driver.findElement(By.cssSelector("Input[value='Submit']")).click()
  }

  def goToAuthStub(): Unit =
    driver.navigate().to("http://localhost:9949/auth-login-stub/gg-sign-in/")

  def intermediaryLogin(): Unit = {

    val stubUrl: String = TestConfiguration.url("auth-login-stub") + "/gg-sign-in"
    driver.getCurrentUrl should startWith(stubUrl)

    driver.findElement(By.id("redirectionUrl")).sendKeys(TestConfiguration.url("ioss-returns-frontend"))

    val selectAffinityGroup = new Select(driver.findElement(By.id("affinityGroupSelect")))
    selectAffinityGroup.selectByValue("Organisation")

    driver.findElement(By.id("enrolment[0].name")).sendKeys("HMRC-MTD-VAT")
    driver
      .findElement(By.id("input-0-0-name"))
      .sendKeys("VRN")
    driver
      .findElement(By.id("input-0-0-value"))
      .sendKeys("100000001")

    driver.findElement(By.id("enrolment[1].name")).sendKeys("HMRC-IOSS-INT")
    driver
      .findElement(By.id("input-1-0-name"))
      .sendKeys("IntNumber")
    driver
      .findElement(By.id("input-1-0-value"))
      .sendKeys("IN9001234567")

    driver.findElement(By.cssSelector("Input[value='Submit']")).click()

  }
}
