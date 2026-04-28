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
import org.openqa.selenium.support.ui.{ExpectedConditions, FluentWait}
import uk.gov.hmrc.configuration.TestEnvironment
import uk.gov.hmrc.selenium.webdriver.Driver
import org.scalatest.matchers.should.Matchers._

import java.time.format.DateTimeFormatter
import java.time.{Clock, LocalDate}

object Dashboard extends BasePage {

  private val registrationUrl: String        =
    TestEnvironment.url("ioss-registration-frontend")
  private val registrationJourneyUrl: String = "/pay-vat-on-goods-sold-to-eu/register-for-import-one-stop-shop"

  private val dashboardUrl: String        =
    TestEnvironment.url("ioss-returns-frontend")
  private val dashboardJourneyUrl: String = "/pay-vat-on-goods-sold-to-eu/import-one-stop-shop-returns-payments"

  private val exclusionUrl: String        =
    TestEnvironment.url("ioss-exclusions-frontend")
  private val exclusionJourneyUrl: String = "/pay-vat-on-goods-sold-to-eu/leave-import-one-stop-shop"

  private val paymentUrl: String =
    TestEnvironment.url("pay-frontend")

  def goToDashboardJourney(): Unit =
    get(dashboardUrl + dashboardJourneyUrl)

  def checkJourneyUrl(page: String): Unit = {
    fluentWait.until(ExpectedConditions.urlContains(page))
    if (page == "add-sales-from-eu" || page == "check-add-sales-from-eu") {
      fluentWait.until(ExpectedConditions.urlMatches("^((?!add-sales-from-eu-to-eu).)*$"))
    }
    getCurrentUrl should startWith(s"$dashboardUrl$dashboardJourneyUrl")
    getCurrentUrl should endWith(page)
  }

  def checkRegistrationJourneyUrl(page: String): Unit = {
    fluentWait.until(ExpectedConditions.urlContains(page))
    getCurrentUrl should startWith(s"$registrationUrl$registrationJourneyUrl")
    getCurrentUrl should endWith(page)
  }

  def checkExclusionsJourneyUrl(): Unit = {
    fluentWait.until(ExpectedConditions.urlContains(s"$exclusionUrl$exclusionJourneyUrl"))
    getCurrentUrl should startWith(s"$exclusionUrl$exclusionJourneyUrl")
  }

  def checkExternalServiceUrl(page: String): Unit =
    if (page == "payments") {
      val paymentsUrlToCheck = s"$paymentUrl/pay/select-payment-amount?traceId="
      fluentWait.until(ExpectedConditions.urlContains(paymentsUrlToCheck))
      getCurrentUrl should startWith(paymentsUrlToCheck)
    } else if (page == "paymentsWithDate") {
      val paymentsUrlToCheck = s"$paymentUrl/pay/when-do-you-want-to-pay?traceId="
      fluentWait.until(ExpectedConditions.urlContains(paymentsUrlToCheck))
      getCurrentUrl should startWith(paymentsUrlToCheck)
    } else {
      fluentWait.until(ExpectedConditions.urlContains(page))
      if (page == "business-account") {
        getCurrentUrl should endWith(page)
      } else {
        getCurrentUrl.contains(page)
      }
    }

  def checkProblemPage(): Unit = {
    fluentWait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("h1")))
    val h1 = Driver.instance.findElement(By.tagName("h1")).getText
    Assert.assertTrue(h1.equals("Sorry, there is a problem with the service"))
  }

  def insufficientEnrolmentPage(): Unit = {
    fluentWait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("h1")))
    val h1 = Driver.instance.findElement(By.tagName("h1")).getText
    Assert.assertTrue(h1.equals("You cannot use this service"))
  }

  def clickLink(link: String): Unit =
    click(By.id(link))

  def checkName(nameType: String): Unit = {
    val htmlBody = Driver.instance.findElement(By.tagName("body")).getText
    if (nameType == "organisation") {
      Assert.assertTrue(htmlBody.contains("Company Name"))
    } else {
      Assert.assertTrue(htmlBody.contains("first middle last"))
    }
  }

  def clickBackButton(): Unit =
    Driver.instance
      .navigate()
      .back()

  def goToPage(page: String): Unit =
    get(s"$dashboardUrl$dashboardJourneyUrl/$page")

  def answerRadioButton(answer: String): Unit = {

    answer match {
      case "yes" => click(By.id("value"))
      case "no"  => click(By.id("value-no"))
      case _     => throw new Exception("Option doesn't exist")
    }
    click(continueButton)
  }

  def waitForElement(by: By): Unit =
    new FluentWait(Driver.instance).until(ExpectedConditions.presenceOfElementLocated(by))

  def selectCountry(country: String): Unit = {
    val inputId = "value"
    sendKeys(By.id(inputId), country)
    waitForElement(By.id(inputId))
    click(By.cssSelector("li#value__option--0"))
    click(continueButton)
  }

  def tickCheckbox(checkbox: String): Unit =
    checkbox match {
      case "first"  => click(By.id("value_0"))
      case "second" => click(By.id("value_1"))
      case "third"  => click(By.id("value_2"))
      case "fourth" => click(By.id("value_3"))
      case "fifth"  => click(By.id("value_4"))
      case _        => throw new Exception("Checkbox doesn't exist")
    }

  def continue(): Unit =
    click(continueButton)

  def submit(): Unit =
    click(submitButton)

  def enterAnswer(answer: String): Unit = {
    sendKeys(By.id("value"), answer)
    click(continueButton)
  }

  def enterAlternativeVatAmount(amount: String): Unit = {
    click(By.id("choice_different"))
    waitForElement(By.id("amount"))
    sendKeys(By.id("amount"), amount)
    click(continueButton)
  }

  def navigateToSecureStartReturn(): Unit =
    get(s"$dashboardUrl$dashboardJourneyUrl/start-return")

  def navigateToReturnStartPage(period: String): Unit = {
    val lastYear = LocalDate.now().minusYears(1).getYear.toString

    val urlPeriod = if (period == "current") {
      currentPeriod()
    } else if (period == "excluded") {
      s"$lastYear-Q2"
    } else {
      period
    }

    get(s"$dashboardUrl$dashboardJourneyUrl/$urlPeriod/start")
  }

  private def nextQuarter: LocalDate = {
    val today                    = LocalDate.now(Clock.systemUTC())
    val endMonthOfQuarter        = (((today.getMonthValue - 1) / 3) + 1) * 3
    val dateInLastMonthOfQuarter = today.withMonth(endMonthOfQuarter)
    val lastDayOfCurrentQuarter  = dateInLastMonthOfQuarter.withDayOfMonth(dateInLastMonthOfQuarter.lengthOfMonth)

    lastDayOfCurrentQuarter.plusDays(1)
  }

  def generateNextAvailableReturn(): String = {
    val today               = LocalDate.now()
    val currentQuarterStart = nextQuarter.minusMonths(3)
    val currentQuarterEnd   = today.withMonth(currentQuarterStart.getMonthValue).plusMonths(2)

    s"You can complete your " +
      s"${currentQuarterStart.format(DateTimeFormatter.ofPattern("MMMM"))} to " +
      s"${currentQuarterEnd.format(DateTimeFormatter.ofPattern("MMMM yyyy"))} return from " +
      s"${nextQuarter.format(DateTimeFormatter.ofPattern("d MMMM yyyy"))}."
  }

  def nextAvailableReturnDueMessage(): Unit = {
    val htmlBody = Driver.instance.findElement(By.tagName("body")).getText
    Assert.assertTrue(htmlBody.contains(generateNextAvailableReturn()))
  }

  def getQuarter(month: Int): String =
    month match {
      case 1 | 2 | 3    => "Q1"
      case 4 | 5 | 6    => "Q2"
      case 7 | 8 | 9    => "Q3"
      case 10 | 11 | 12 => "Q4"
    }

  def currentPeriod(): String =
    s"${LocalDate.now().getYear}-${getQuarter(LocalDate.now().getMonthValue)}"

  def navigateToPreviouslySubmittedReturn(): Unit =
    get(s"$dashboardUrl$dashboardJourneyUrl/past-returns/2022-Q1")

  def navigateToPastReturnsHistory(): Unit =
    get(s"$dashboardUrl$dashboardJourneyUrl/past-returns")

  def selectReturnPeriod(period: String): Unit = {
    period match {
      case "first"  => click(By.id("value_0"))
      case "second" => click(By.id("value_1"))
      case _        => throw new Exception("Link doesn't exist")
    }
    continue()
  }

  def noPastReturns(): Unit = {
    val htmlBody = Driver.instance.findElement(By.tagName("body")).getText
    Assert.assertTrue(htmlBody.contains("You have not submitted any returns."))
  }

  def selectChangeOrRemoveLink(link: String): Unit =
    cssLink(link)

  def checkSavedReturn(): Unit = {
    val pageTitle = "We have saved your progress - Submit a One Stop Shop return and pay VAT - GOV.UK"
    fluentWait.until(
      ExpectedConditions.titleIs(pageTitle)
    )
    getTitle should be(pageTitle)
  }

  def cssLink(link: String): Unit =
    click(By.cssSelector(s"a[href*=$link]"))
}
