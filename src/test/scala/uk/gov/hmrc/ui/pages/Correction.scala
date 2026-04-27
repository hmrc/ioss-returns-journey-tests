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

object Correction extends BasePage {

  def previouslyDeclaredText(traderType: String, display: Boolean): Unit = {
    val htmlBody = Driver.instance.findElement(By.tagName("body")).getText

    if (display) {
      if (traderType == "NETP") {
        Assert.assertTrue(
          htmlBody.contains("Enter a minus value if too much was declared in the previous return.")
        )
        Assert.assertTrue(
          htmlBody.contains("Their most recent declaration for this month is")
        )
      } else {
        Assert.assertTrue(
          htmlBody.contains("Enter a minus value if you declared too much in your previous return.")
        )
        Assert.assertTrue(
          htmlBody.contains("Your most recent declaration for this month is")
        )
      }
    } else {
      Assert.assertFalse(
        htmlBody.contains("Enter a minus value if you declared too much in your previous return.")
      )
      Assert.assertFalse(
        htmlBody.contains("Your most recent declaration for this month is")
      )
    }
  }

  def noPaymentsDueForCorrectionsText(displayed: Boolean): Unit = {
    val htmlBody = Driver.instance.findElement(By.tagName("body")).getText
    if (displayed) {
      Assert.assertTrue(
        htmlBody.contains("VAT declared where no payment is due")
      )
      Assert.assertTrue(
        htmlBody.contains(
          "The tax authorities in countries where you declared too much VAT are responsible for refunding any overpayments."
        )
      )
    } else {
      Assert.assertFalse(
        htmlBody.contains("VAT declared where no payment is due")
      )
      Assert.assertFalse(
        htmlBody.contains(
          "The tax authorities in countries where you declared too much VAT are responsible for refunding any overpayments."
        )
      )
    }
  }
  def removeCorrectionCountry(country: String): Unit = {
    val heading = Driver.instance.findElement(By.tagName("h1")).getText
    Assert.assertTrue(
      heading.equals(
        s"Are you sure you want to remove this correction for $country?"
      )
    )
  }
}
