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

object Exclusion extends BasePage {

  def excludedFutureOutstandingReturnsBanner(): Unit = {
    val htmlBody = Driver.instance.findElement(By.tagName("body")).getText
    Assert.assertTrue(
      htmlBody.contains("You have asked to leave this service. You must complete and pay any outstanding returns.")
    )
  }

  def excludedPastOutstandingReturnsBanner(): Unit = {
    val htmlBody = Driver.instance.findElement(By.tagName("body")).getText
    Assert.assertTrue(
      htmlBody.contains("You have left this service. You must complete and pay any outstanding returns.")
    )
  }

  def excludedOutstandingReturnsReturnsMessage(): Unit = {
    val htmlBody = Driver.instance.findElement(By.tagName("body")).getText
    Assert.assertTrue(htmlBody.contains("You can correct a previous return when you submit your final one."))
  }

  def excludedNoOutstandingReturnsBanner(): Unit = {
    val htmlBody = Driver.instance.findElement(By.tagName("body")).getText
    Assert.assertTrue(
      htmlBody.contains("You can no longer use this service to correct previous returns. You must make any VAT corrections directly with the country where you made the sales.")
    )
  }

  def excludedNoOutstandingReturnsMessage(): Unit = {
    val htmlBody = Driver.instance.findElement(By.tagName("body")).getText
    Assert.assertTrue(htmlBody.contains("You can no longer use this service to correct previous returns. You must make any VAT corrections directly with the country where you made the sales."))
  }

  def excludedFinalReturnTile(): Unit = {
    val htmlBody = Driver.instance.findElement(By.tagName("body")).getText
    Assert.assertTrue(htmlBody.contains("You've completed your final return."))
  }

  def removedOutstandingReturnsBanner(): Unit = {
    val htmlBody = Driver.instance.findElement(By.tagName("body")).getText
    Assert.assertTrue(
      htmlBody.contains("We've removed you from this service, but you must complete and pay your final return.")
    )
  }

  def removedNoOutstandingReturnsBanner(): Unit = {
    val htmlBody = Driver.instance.findElement(By.tagName("body")).getText
    Assert.assertTrue(
      htmlBody.contains("We've removed you from this service.")
    )
  }

  def quarantinedNoOutstandingReturnsBanner(): Unit = {
    val htmlBody = Driver.instance.findElement(By.tagName("body")).getText
    Assert.assertTrue(
      htmlBody.contains("We've removed you from this service. You cannot rejoin until")
    )
  }

  def finalReturnHeading(display: Boolean, traderType: String): Unit = {
    val htmlH1 = Driver.instance.findElement(By.tagName("h1")).getText

    if(display){
      if (traderType == "NETP") {
        Assert.assertTrue(htmlH1.equals("Do you want to start the final return for NETP Return VRN?"))
      } else {
        Assert.assertTrue(htmlH1.equals("Do you want to start your final return?"))
      }
    } else {
      Assert.assertFalse(htmlH1.equals("Do you want to start your final return?"))
    }
  }

  def lastChanceForCorrection(display: Boolean): Unit = {
    val htmlBody = Driver.instance.findElement(By.tagName("body")).getText
    val text = "This is your last chance to correct a previous return on this service."

    if(display){
        Assert.assertTrue(htmlBody.contains(text))
      } else {
      Assert.assertFalse(htmlBody.contains(text))
    }
  }

  def correctionsWarningSubmission(display: Boolean): Unit = {
    val htmlBody = Driver.instance.findElement(By.tagName("body")).getText
    val text = "After you submit your return, you will not be able to make any corrections using the Import One Stop Shop service."

    if(display){
      Assert.assertTrue(htmlBody.contains(text))
    } else {
      Assert.assertFalse(htmlBody.contains(text))
    }
  }

  def regularHeadingStartReturn(): Unit = {
    val htmlH1 = Driver.instance.findElement(By.tagName("h1")).getText
    Assert.assertTrue(htmlH1.equals("Do you want to start your return for December 2023?"))
  }

  def returnsOverThreeYearsMessage(): Unit = {
    val htmlBody = Driver.instance.findElement(By.tagName("body")).getText
    Assert.assertTrue(
      htmlBody.contains(
        "You must complete your July 2020 return with the countries where you made your sales."
      )
    )
    Assert.assertTrue(
      htmlBody.contains(
        "You must complete your August 2020 return with the countries where you made your sales."
      )
    )
    Assert.assertTrue(
      htmlBody.contains(
        "You must complete your September 2020 return with the countries where you made your sales."
      )
    )
    Assert.assertTrue(
      htmlBody.contains(
        "You must complete your October 2020 return with the countries where you made your sales."
      )
    )
    Assert.assertTrue(
      htmlBody.contains(
        "You must complete your November 2020 return with the countries where you made your sales."
      )
    )
    Assert.assertTrue(
      htmlBody.contains(
        "You must complete your December 2020 return with the countries where you made your sales."
      )
    )
  }

  def paymentsOverThreeYearsMessage(): Unit = {
    val htmlBody = Driver.instance.findElement(By.tagName("body")).getText
    Assert.assertTrue(
      htmlBody.contains(
        "You have an outstanding IOSS VAT payment for July 2020. You must contact the countries where you made your sales to pay the VAT due."
      )
    )
    Assert.assertTrue(
      htmlBody.contains(
        "You have an outstanding IOSS VAT payment for August 2020. You must contact the countries where you made your sales to pay the VAT due."
      )
    )
    Assert.assertTrue(
      htmlBody.contains(
        "You have an outstanding IOSS VAT payment for September 2020. You must contact the countries where you made your sales to pay the VAT due."
      )
    )
    Assert.assertTrue(
      htmlBody.contains(
        "You have an outstanding IOSS VAT payment for October 2020. You must contact the countries where you made your sales to pay the VAT due."
      )
    )
    Assert.assertTrue(
      htmlBody.contains(
        "You have an outstanding IOSS VAT payment for November 2020. You must contact the countries where you made your sales to pay the VAT due."
      )
    )
    Assert.assertTrue(
      htmlBody.contains(
        "You have an outstanding IOSS VAT payment for December 2020. You must contact the countries where you made your sales to pay the VAT due."
      )
    )
  }
}
