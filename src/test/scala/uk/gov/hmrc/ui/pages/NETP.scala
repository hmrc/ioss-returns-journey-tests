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

import org.junit.Assert
import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedConditions
import org.scalatest.matchers.should.Matchers.*
import uk.gov.hmrc.configuration.TestEnvironment
import uk.gov.hmrc.selenium.webdriver.Driver

object NETP extends BasePage {

  private val netpRegistrationUrl: String        =
    TestEnvironment.url("ioss-netp-registration-frontend")
  private val netpRegistrationJourneyUrl: String = "/pay-clients-vat-on-eu-sales/register-new-ioss-client"

  def checkJourneyUrl(page: String): Unit = {
    val url = s"$netpRegistrationUrl$netpRegistrationJourneyUrl/$page"
    fluentWait.until(ExpectedConditions.urlContains(url))
    getCurrentUrl should startWith(url)
  }

  def checkInterceptHeading(): Unit = {
    val heading = Driver.instance.findElement(By.tagName("h1")).getText
    Assert.assertTrue(heading.equals("Check the registration for Change date over two years is up to date"))
  }

  def reviewRegistrationCheck(): Unit = {
    val htmlBody = Driver.instance.findElement(By.tagName("body")).getText
    Assert.assertTrue(htmlBody.contains("IOSS number: IM9002221223"))
  }
}
