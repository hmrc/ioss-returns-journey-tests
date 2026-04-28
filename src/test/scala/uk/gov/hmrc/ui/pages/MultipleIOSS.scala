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
import uk.gov.hmrc.selenium.webdriver.Driver

import java.time.LocalDate
import java.time.format.DateTimeFormatter

object MultipleIOSS extends BasePage {

  def correctIOSSNumber(iossNumber: String): Unit = {
    val htmlBody = Driver.instance.findElement(By.tagName("body")).getText
    Assert.assertTrue(htmlBody.contains("IOSS number: " + iossNumber))
  }

  def linkForPreviousRegistrations(variation: String): Unit = {
    val htmlBody           = Driver.instance.findElement(By.tagName("body")).getText
    val dateTimeFormatter  = DateTimeFormatter.ofPattern("MMMM yyyy")
    val startDate          = LocalDate.now().minusMonths(6)
    val startDateFormatted = dateTimeFormatter.format(startDate)
    val endDate            = LocalDate.now().minusMonths(4)
    val endDateFormatted   = dateTimeFormatter.format(endDate)

    if (variation == "one") {
      Assert.assertTrue(htmlBody.contains(s"View returns from $startDateFormatted to $endDateFormatted"))
    } else {
      Assert.assertTrue(htmlBody.contains("View returns from a previous registration"))
    }
  }

  def periodForMultipleRegistrations(months: Int): String = {
    val returnMonth  = LocalDate.now().minusMonths(months).getMonthValue
    val returnYear   = LocalDate.now().minusMonths(months).getYear
    val periodString = s"$returnYear-M$returnMonth"

    periodString
  }

  def previousIOSSNumbers(): Unit = {
    val htmlBody = Driver.instance.findElement(By.tagName("body")).getText
    Assert.assertTrue(htmlBody.contains("IM9007230001"))
    Assert.assertTrue(htmlBody.contains("IM9007230002"))
  }

  def dashboardWarningOutstandingPaymentsMultipleRegistrations(variation: String, iossNumber: String): Unit = {
    val htmlBody = Driver.instance.findElement(By.tagName("body")).getText
    val amount   = variation match {
      case "multiplePaymentsOneRegistration"       => "£1,500"
      case "multiplePaymentsMultipleRegistrations" => "£4,250"
      case "onePaymentMultipleRegistrations"       => "£3,750"
      case _                                       => "£2,000"
    }

    if (variation == "multiplePaymentsMultipleRegistrations" || variation == "onePaymentMultipleRegistrations") {
      Assert.assertTrue(htmlBody.contains(s"This account owes a total of $amount on previous registrations."))
    } else {
      Assert.assertTrue(
        htmlBody.contains(s"This account owes $amount on a previous registration with IOSS number $iossNumber.")
      )
    }
  }

  def outstandingPaymentAmounts(variation: String): Unit = {
    val htmlBody = Driver.instance.findElement(By.tagName("body")).getText
    if (variation == "single") {
      Assert.assertTrue(htmlBody.contains("You owe £1,750 for your registration with IOSS number\nIM9007230004"))
      Assert.assertTrue(htmlBody.contains("You owe £2,000 for your registration with IOSS number\nIM9007230005"))
    } else {
      Assert.assertTrue(htmlBody.contains("You owe £2,750 for your registration with IOSS number\nIM9007230001"))
      Assert.assertTrue(htmlBody.contains("You owe £1,500 for your registration with IOSS number\nIM9007230002"))
    }
  }
}
