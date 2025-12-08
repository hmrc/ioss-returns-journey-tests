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
import org.openqa.selenium.support.ui.{ExpectedConditions, FluentWait}
import uk.gov.hmrc.test.ui.conf.TestConfiguration

import java.time.LocalDate

object CommonPage extends BasePage {

  val host: String     = TestConfiguration.url("ioss-returns-frontend")
  val authHost: String = TestConfiguration.url("auth-login-stub")

  def checkRegistrationPage(page: String): Unit =
    driver.getCurrentUrl should startWith(
      TestConfiguration.url("ioss-registration-frontend") + s"/$page"
    )

  def checkUrl(url: String): Unit = {
    val urlToCheck = s"$host/$url"
    fluentWait.until(ExpectedConditions.urlContains(urlToCheck))
    driver.getCurrentUrl should startWith(urlToCheck)
  }

  def selectAnswer(data: String): Unit = {
    data match {
      case "yes" => driver.findElement(By.id("value")).click()
      case "no"  => driver.findElement(By.id("value-no")).click()
      case _     => throw new Exception("Option doesn't exist")
    }
    clickContinue()
  }
  def clickContinue(): Unit =
    driver.findElement(By.id("continue")).click()

  def clickSubmit(): Unit =
    driver.findElement(By.id("submit")).click()

  def enterData(inputId: String, data: String): Unit =
    driver.findElement(By.id(inputId)).sendKeys(data)

  def tickCheckbox(checkbox: String): Unit =
    checkbox match {
      case "first"  => driver.findElement(By.id("value_0")).click()
      case "second" => driver.findElement(By.id("value_1")).click()
      case "third"  => driver.findElement(By.id("value_2")).click()
      case "fourth" => driver.findElement(By.id("value_3")).click()
      case "fifth"  => driver.findElement(By.id("value_4")).click()
      case _        => throw new Exception("Checkbox doesn't exist")
    }

  def selectValueAutocomplete(data: String): Unit = {
    val inputId = "value"
    driver.findElement(By.id(inputId)).sendKeys(data)
    waitForElement(By.id(inputId))
    driver.findElement(By.cssSelector("li#value__option--0")).click()
    clickContinue()
  }

  def waitForElement(by: By) =
    new FluentWait(driver).until {
      ExpectedConditions.presenceOfElementLocated(by)
    }

  def selectLink(link: String): Unit =
    driver.findElement(By.cssSelector(s"a[href*=$link]")).click()

  def checkDoubleIndexURL(firstIndex: String, secondIndex: String, page: String, backslash: Boolean): Unit =
    CommonPage.checkUrl(page + getDoubleIndexString(firstIndex, secondIndex, backslash))

  def getDoubleIndexString(firstIndex: String, secondIndex: String, backslash: Boolean): String = {
    val extraString = if (backslash) {
      "\\"
    } else {
      ""
    }

    (firstIndex, secondIndex) match {
      case ("first", "first")   => s"/1$extraString/1"
      case ("first", "second")  => s"/1$extraString/2"
      case ("first", "third")   => s"/1$extraString/3"
      case ("second", "first")  => s"/2$extraString/1"
      case ("second", "second") => s"/2$extraString/2"
      case ("second", "third")  => s"/2$extraString/3"
      case ("third", "first")   => s"/3$extraString/1"
      case ("third", "second")  => s"/3$extraString/2"
      case _                    => throw new Exception("Index combination is invalid")
    }
  }

  def clearData(): Unit =
    driver.findElement(By.id("value")).clear()

  def navigateToFirstCorrectionCountry(): Unit =
    driver
      .navigate()
      .to(s"$host/correction-country/1/1")

  def selectYearRadioButton(year: String): Unit = {

    val twoYearsAgo = LocalDate.now().minusYears(2).getYear
    val lastYear    = LocalDate.now().minusYears(1).getYear

    year match {
      case "two years ago" => driver.findElement(By.id(s"value_$twoYearsAgo")).click()
      case "last year"     => driver.findElement(By.id(s"value_$lastYear")).click()
      case "2023"          => driver.findElement(By.id("value_2023")).click()
      case "2025"          => driver.findElement(By.id("value_2025")).click()
      case _               => throw new Exception("Option doesn't exist")
    }
    CommonPage.clickContinue()
  }

  def selectMonthRadioButton(month: String): Unit = {
    month match {
      case "January"  => driver.findElement(By.id("value_January")).click()
      case "February" => driver.findElement(By.id("value_February")).click()
      case "October"  => driver.findElement(By.id("value_October")).click()
      case "November" => driver.findElement(By.id("value_November")).click()
      case "December" => driver.findElement(By.id("value_December")).click()
      case _          => throw new Exception("Option doesn't exist")
    }
    CommonPage.clickContinue()
  }

  def navigateToPreviousReturn(): Unit =
    driver
      .navigate()
      .to(s"$host/past-returns/2023-M11")

  def navigateToBtaLink(link: String): Unit =
    driver
      .navigate()
      .to(s"$host/test-only/$link")

  def selectPaymentOption(option: String): Unit = {
    option match {
      case "first"  => driver.findElement(By.id("value_0")).click()
      case "second" => driver.findElement(By.id("value_1")).click()
      case _        => throw new Exception("Option doesn't exist")
    }
    CommonPage.clickContinue()
  }

  def navigateToOutstandingPayments(): Unit =
    driver
      .navigate()
      .to(s"$host/outstanding-payments")

  def navigateToReturnsService(): Unit =
    driver
      .navigate()
      .to(s"$host")

  def navigateToReturn(returnPeriod: String): Unit = {

    val currentReturnMonth  = LocalDate.now().getMonthValue
    val currentReturnYear   = LocalDate.now().getYear
    val currentPeriodString = s"$currentReturnYear-M$currentReturnMonth"

    val periodUrl = returnPeriod match {
      case "January 2018"   => "past-returns/2018-M1"
      case "September 2018" => "past-returns/2018-M9"
      case "December 2024"  => "past-returns/2024-M12"
      case "December 2020"  => "2020-M12/start-return"
      case "November 2023"  => "2023-M11/start-return"
      case "December 2023"  => "2023-M12/start-return"
      case "January 2024"   => "2024-M1/start-return"
      case "current month"  => s"$currentPeriodString/start-return"
      case _                => "period doesn't exist"
    }
    driver
      .navigate()
      .to(s"$host/$periodUrl")
  }

  def navigateToSecureStartReturn(): Unit =
    driver
      .navigate()
      .to(s"$host/start-return")

  def clickBackButton(): Unit =
    driver
      .navigate()
      .back()

  def selectIOSSNumberRadioButton(iossNumber: String): Unit = {
    iossNumber match {
      case "IM9007230001" => driver.findElement(By.id("IM9007230001")).click()
      case "IM9007230002" => driver.findElement(By.id("IM9007230002")).click()
      case _              => throw new Exception("Option doesn't exist")
    }
    CommonPage.clickContinue()
  }

  def selectRadioButton(scheme: String): Unit = {
    scheme match {
      case "1" => driver.findElement(By.id("value_0")).click()
      case "2" => driver.findElement(By.id("value_1")).click()
      case _   => throw new Exception("Option doesn't exist")
    }
    CommonPage.clickContinue()
  }

  def checkTransferringToOtherMSIDPastReturn(): Unit = {
    val htmlH1 = driver.findElement(By.tagName("h1")).getText
    Assert.assertTrue(htmlH1.equals("Submitted return for 1 to 11 February 2024"))
  }

  def checkTransferringFromOtherMSIDPastReturn(): Unit = {
    val htmlH1 = driver.findElement(By.tagName("h1")).getText
    Assert.assertTrue(htmlH1.equals("Submitted return for 15 to 31 January 2024"))
  }

  def checkFullMonthPastReturn(): Unit = {
    val htmlH1 = driver.findElement(By.tagName("h1")).getText
    Assert.assertTrue(htmlH1.equals("Submitted return for December 2024"))
  }
}
