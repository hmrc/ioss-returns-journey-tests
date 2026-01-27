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

import org.junit.Assert
import org.openqa.selenium.By
import uk.gov.hmrc.test.ui.pages.CommonPage.checkUrl

import java.time.LocalDate

object IntermediaryPage extends BasePage {

  def checkNetpReturn(iossNumber: String): Unit = {
    val htmlBody = driver.findElement(By.tagName("body")).getText

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

  def checkCaption(scenario: String): Unit = {
    val caption = driver.findElement(By.tagName("h1")).getText

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
    val htmlBody = driver.findElement(By.tagName("body")).getText

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

  def checkSubmittedReturnsCaption(scenario: String): Unit = {
    val caption = driver.findElement(By.tagName("body")).getText

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
    val caption = driver.findElement(By.tagName("h1")).getText

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

  def checkOldestReturnUrl(): Unit = {
    val oldestAvailableReturnMonth  = LocalDate.now().minusYears(3).minusMonths(1).getMonthValue
    val oldestAvailableReturnYear   = LocalDate.now().minusYears(3).minusMonths(1).getYear
    val oldestAvailablePeriodString = s"$oldestAvailableReturnYear-M$oldestAvailableReturnMonth"
    checkUrl(s"$oldestAvailablePeriodString/start-return")
  }

  def checkOverdueHintText(): Unit = {
    val htmlBody = driver.findElement(By.tagName("body")).getText
    Assert.assertTrue(
      htmlBody.contains(
        "Your client has 4 overdue returns. You must complete their oldest return first."
      )
    )
  }

}
