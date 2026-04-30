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
import uk.gov.hmrc.ui.pagesold.AuthPage.{convertToAnyShouldWrapper, endWith}

import java.time.LocalDate

object Intermediary extends BasePage {

  private val globalDashboard: String                 = TestConfiguration.url("ioss-returns-frontend")
  private val globalDashboardJourneyUrl: String       = "/pay-vat-on-goods-sold-to-eu/import-one-stop-shop-returns-payments"
  private val intermediaryDashboard: String           = TestConfiguration.url("ioss-intermediary-dashboard-frontend")
  private val intermediaryDashboardJourneyUrl: String =
    "/pay-clients-vat-on-eu-sales/manage-ioss-returns-payments-clients"

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
      case _              =>
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

  def checkNetpPayments(): Unit = {
    val h1       = Driver.instance.findElement(By.tagName("h1")).getText
    val htmlBody = Driver.instance.findElement(By.tagName("body")).getText

    Assert.assertTrue(h1.equals("Which month would you like to make a payment for?"))
    Assert.assertTrue(
      htmlBody.contains(
        "January 2025 - £1,397.30 owed\n" +
          "February 2025 - £397.30 owed"
      )
    )
  }

  def checkSubmittedReturnsCaption(scenario: String): Unit = {
    val caption = Driver.instance.findElement(By.tagName("body")).getText

    scenario match {
      case "UK with VRN"                 =>
        Assert.assertTrue(
          caption.contains(
            "NETP Return VRN"
          )
        )
      case "UK with NINO"                =>
        Assert.assertTrue(
          caption.contains(
            "NETP Return NINO"
          )
        )
      case "oldest registration client"  =>
        Assert.assertTrue(
          caption.contains(
            "Oldest Registration - Client One"
          )
        )
      case "middle registration client"  =>
        Assert.assertTrue(
          caption.contains(
            "Middle Registration - Client Three"
          )
        )
      case "current registration client" =>
        Assert.assertTrue(
          caption.contains(
            "Current Registration - Client Five"
          )
        )
      case _                             =>
        throw new Exception("Scenario doesn't exist")
    }
  }

  def checkReturnStartHeading(scenario: String): Unit = {
    val caption = Driver.instance.findElement(By.tagName("body")).getText

    scenario match {
      case "UK with VRN"     =>
        Assert.assertTrue(
          caption.contains(
            "Do you want to start the March 2025 return for NETP Return VRN?"
          )
        )
      case "UK with UTR"     =>
        Assert.assertTrue(
          caption.contains(
            "Do you want to start the January 2025 return for NETP Return UTR?"
          )
        )
      case "UK with NINO"    =>
        Assert.assertTrue(
          caption.contains(
            "Do you want to start the January 2025 return for NETP Return NINO?"
          )
        )
      case "Non-UK with VRN" =>
        Assert.assertTrue(
          caption.contains(
            "Do you want to start the January 2025 return for NETP Return Non-UK VRN?"
          )
        )
      case "Non-UK with FTR" =>
        Assert.assertTrue(
          caption.contains(
            "Do you want to start the March 2025 return for NETP Return FTR?"
          )
        )
      case _                 =>
        throw new Exception("Scenario doesn't exist")
    }
  }

  def checkCaption(scenario: String): Unit = {
    val caption = Driver.instance.findElement(By.tagName("body")).getText

    scenario match {
      case "UK with VRN"     =>
        Assert.assertTrue(
          caption.contains(
            "NETP Return VRN March 2025"
          )
        )
      case "UK with UTR"     =>
        Assert.assertTrue(
          caption.contains(
            "NETP Return UTR January 2025"
          )
        )
      case "UK with NINO"    =>
        Assert.assertTrue(
          caption.contains(
            "NETP Return NINO January 2025"
          )
        )
      case "Non-UK with VRN" =>
        Assert.assertTrue(
          caption.contains(
            "NETP Return Non-UK VRN January 2025"
          )
        )
      case "Non-UK with FTR" =>
        Assert.assertTrue(
          caption.contains(
            "NETP Return FTR March 2025"
          )
        )
      case _                 =>
        throw new Exception("Scenario doesn't exist")
    }
  }

  def checkCYA(scenario: String): Unit = {
    val htmlBody = Driver.instance.findElement(By.tagName("body")).getText

    scenario match {
      case "UK with VRN"     =>
        Assert.assertTrue(
          htmlBody.contains(
            "Business name NETP Return VRN\n" +
              "IOSS number IM9001144771\n" +
              "UK VAT registration number 159951159\n" +
              "Return month March 2025"
          )
        )
      case "UK with UTR"     =>
        Assert.assertTrue(
          htmlBody.contains(
            "Business name NETP Return UTR\n" +
              "IOSS number IM9001144773\n" +
              "Tax reference 1234567890\n" +
              "Return month January 2025"
          )
        )
      case "UK with NINO"    =>
        Assert.assertTrue(
          htmlBody.contains(
            "Business name NETP Return NINO\n" +
              "IOSS number IM9001144778\n" +
              "National Insurance number AA112233D\n" +
              "Return month January 2025"
          )
        )
      case "Non-UK with VRN" =>
        Assert.assertTrue(
          htmlBody.contains(
            "Business name NETP Return Non-UK VRN\n" +
              "IOSS number IM9001144775\n" +
              "UK VAT registration number 159951111\n" +
              "Return month January 2025"
          )
        )
      case "Non-UK with FTR" =>
        Assert.assertTrue(
          htmlBody.contains(
            "Business name NETP Return FTR\n" +
              "IOSS number IM9001144777\n" +
              "Tax reference CA123456\n" +
              "Return month March 2025"
          )
        )
      case _                 =>
        throw new Exception("Scenario doesn't exist")
    }
  }

  def checkOldestReturnUrl(iossNumber: String): Unit = {
    val oldestAvailableReturnMonth  = LocalDate.now().minusYears(3).minusMonths(1).getMonthValue
    val oldestAvailableReturnYear   = LocalDate.now().minusYears(3).minusMonths(1).getYear
    val oldestAvailablePeriodString = s"$oldestAvailableReturnYear-M$oldestAvailableReturnMonth"
    val url                         = s"$iossNumber/$oldestAvailablePeriodString/start-return"
    fluentWait.until(ExpectedConditions.urlContains(url))
    getCurrentUrl should endWith(url)
  }

  def checkOverdueHintText(): Unit = {
    val htmlBody = Driver.instance.findElement(By.tagName("body")).getText
    Assert.assertTrue(
      htmlBody.contains(
        "Your client has 4 overdue returns. You must complete their oldest return first."
      )
    )
  }

  def returnOverThreeYearsOverdue(): String = {
    val firstExpiredReturnMonth = LocalDate.now().minusYears(3).minusMonths(2).getMonthValue
    val firstExpiredReturnYear  = LocalDate.now().minusYears(3).minusMonths(2).getYear
    s"$firstExpiredReturnYear-M$firstExpiredReturnMonth"
  }

  def checkClientName(iossNumber: String): Unit = {
    val htmlBody = Driver.instance.findElement(By.tagName("body")).getText

    iossNumber match {
      case "IM9006655443" =>
        Assert.assertTrue(
          htmlBody.contains(
            "NETP Return NINO March 2025"
          )
        )
      case "IM9006655442" =>
        Assert.assertTrue(
          htmlBody.contains(
            "NETP Return UTR January 2025"
          )
        )
      case "IM9006655551" =>
        Assert.assertTrue(
          htmlBody.contains(
            "NETP Return FTR January 2025"
          )
        )
      case _              =>
        throw new Exception("IOSS number doesn't exist")
    }
  }
}
