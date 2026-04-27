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
    val htmlBody = Driver.instance.findElement(By.tagName("body")).getText
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
}
