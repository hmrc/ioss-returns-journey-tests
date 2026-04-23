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

object Payment extends BasePage {

  def noVatOwed(): Unit = {
    val htmlH1 = Driver.instance.findElement(By.tagName("h1")).getText
    Assert.assertTrue(htmlH1.contains("You do not owe any Import One Stop Shop VAT"))
  }

  def noPaymentsTile(): Unit = {
    val htmlBody = Driver.instance.findElement(By.tagName("body")).getText
    Assert.assertTrue(htmlBody.contains("You do not owe anything right now."))
  }

  def onePaymentDueOneOverdue(): Unit = {
    val htmlBody = Driver.instance.findElement(By.tagName("body")).getText
    Assert.assertTrue(htmlBody.contains("Due Payments"))
    Assert.assertTrue(htmlBody.contains("Overdue Payments"))
  }

  def singlePaymentDue(): Unit = {
    val htmlBody = Driver.instance.findElement(By.tagName("body")).getText
    Assert.assertTrue(htmlBody.contains("Due Payments"))
    Assert.assertFalse(htmlBody.contains("Overdue Payments"))
  }

  def errorTile(): Unit = {
    val htmlBody = Driver.instance.findElement(By.tagName("body")).getText
    Assert.assertTrue(htmlBody.contains("You may still owe VAT"))
  }
}
