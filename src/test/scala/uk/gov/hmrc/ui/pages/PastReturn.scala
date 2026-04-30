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
import uk.gov.hmrc.selenium.webdriver.Driver

object PastReturn extends BasePage {

  def showAllAccordion(): Unit = {
    fluentWait.until(ExpectedConditions.presenceOfElementLocated(By.className("govuk-accordion__show-all")))

    val htmlBody = Driver.instance.findElement(By.tagName("body")).getText

    if (htmlBody.contains("Show all sections")) {
      click(By.className("govuk-accordion__show-all"))
    }
  }

  def selectPastReturnLink(link: String): Unit = {
    fluentWait.until(ExpectedConditions.presenceOfElementLocated(By.className("govuk-table__cell--numeric")))
    fluentWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(s"a[href*=$link]")))
    click(By.cssSelector(s"a[href*=$link]"))
  }

  def returnForMonth(monthYear: String): Unit = {
    val htmlBody = Driver.instance.findElement(By.tagName("body")).getText
    Assert.assertTrue(htmlBody.contains(s"Submitted return for $monthYear"))
  }

  def returnWithEUSalesAndCorrections(): Unit = {
    val htmlBody = Driver.instance.findElement(By.tagName("body")).getText
    Assert.assertTrue(htmlBody.contains("Sales to EU countries, Northern Ireland or both"))
    Assert.assertTrue(htmlBody.contains("Corrections"))
    Assert.assertTrue(htmlBody.contains("VAT declared where no payment is due"))
    Assert.assertTrue(htmlBody.contains("VAT owed (including corrections)"))
  }

  def returnWithNoCorrections(): Unit = {
    val htmlBody = Driver.instance.findElement(By.tagName("body")).getText
    Assert.assertTrue(htmlBody.contains("Sales to EU countries, Northern Ireland or both"))
    Assert.assertFalse(htmlBody.contains("VAT owed (including corrections)"))
    Assert.assertTrue(htmlBody.contains("VAT owed"))
  }

  def nilReturn(): Unit = {
    val htmlBody = Driver.instance.findElement(By.tagName("body")).getText
    Assert.assertFalse(htmlBody.contains("Sales to EU countries, Northern Ireland or both"))
    Assert.assertFalse(htmlBody.contains("Corrections"))
    Assert.assertFalse(htmlBody.contains("VAT declared where no payment is due"))
    Assert.assertFalse(htmlBody.contains("VAT owed (including corrections)"))
    Assert.assertFalse(htmlBody.contains("Pay now"))
  }

  def fullMonthHeading(): Unit = {
    val htmlH1 = Driver.instance.findElement(By.tagName("h1")).getText
    Assert.assertTrue(htmlH1.equals("Submitted return for December 2024"))
  }

  def returnsAndPaymentReferences(iossNumber: String): Unit = {
    val htmlBody = Driver.instance.findElement(By.tagName("body")).getText
    Assert.assertTrue(htmlBody.contains(s"XI/$iossNumber"))
  }

}
