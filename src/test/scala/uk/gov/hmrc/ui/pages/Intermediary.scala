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
import org.scalatest.matchers.should.Matchers.startWith
import uk.gov.hmrc.selenium.webdriver.Driver
import uk.gov.hmrc.ui.conf.TestConfiguration
import uk.gov.hmrc.ui.pagesold.AuthPage.convertToAnyShouldWrapper

object Intermediary extends BasePage {

  private val globalDashboard: String                  = TestConfiguration.url("ioss-returns-frontend")
  private val globalDashboardJourneyUrl: String = "/pay-vat-on-goods-sold-to-eu/import-one-stop-shop-returns-payments"
  private val intermediaryDashboard: String = TestConfiguration.url("ioss-intermediary-dashboard-frontend")
  private val intermediaryDashboardJourneyUrl: String   = "/pay-clients-vat-on-eu-sales/manage-ioss-returns-payments-clients"


  def checkNetpReturn(iossNumber: String): Unit = {
    val htmlBody = Driver.instance.findElement(By.tagName("body")).getText

    iossNumber match {
      case "IM9001144771" =>
        Assert.assertTrue(
          htmlBody.contains(
            "Return submitted for First Client\n" +
              "Your return reference is XI/IM9001144771/M03.2025"
          )
        )
      case "IM9001144773" =>
        Assert.assertTrue(
          htmlBody.contains(
            "Return submitted for Third Client\n" +
              "Your return reference is XI/IM9001144773/M01.2025"
          )
        )
      case "IM9001144777" =>
        Assert.assertTrue(
          htmlBody.contains(
            "Return submitted for Seventh Client\n" +
              "Your return reference is XI/IM9001144777/M03.2025"
          )
        )
      case _ =>
        throw new Exception("IOSS number doesn't exist")
    }
  }

  def dashboardUrlCheck(dashboardVersion: String): Unit = {
    val url = if (dashboardVersion == "global") {
      s"$globalDashboard$globalDashboardJourneyUrl"
    } else {
      s"$intermediaryDashboard$intermediaryDashboardJourneyUrl"
    }
    fluentWait.until(ExpectedConditions.urlContains(s"$url/your-account"))
    getCurrentUrl should startWith(s"$url/your-account")
  }
}
