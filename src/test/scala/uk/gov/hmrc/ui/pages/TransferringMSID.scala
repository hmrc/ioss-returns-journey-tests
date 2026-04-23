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

object TransferringMSID extends BasePage {

  def transferringMsidText(traderType: String, transferDirection: String, returnStage: String, returnType: String): Unit = {
    val htmlBody = Driver.instance.findElement(By.tagName("body")).getText

    (traderType, transferDirection, returnStage, returnType) match {

      case ("trader", "to", "offered", "partial") =>
        Assert.assertTrue(htmlBody.contains("Only include sales up to 11 February 2024."))
      case ("netp", "to", "offered", "partial") =>
        Assert.assertTrue(htmlBody.contains("Only include their sales up to 11 February 2024."))
      case (_, "to", "submitting", "partial") =>
        Assert.assertTrue(htmlBody.contains("1 to 11 February 2024"))
      case ("trader", "from", "offered", "partial") =>
        Assert.assertTrue(htmlBody.contains("Only include sales from 15 January 2024."))
      case ("netp", "from", "offered", "partial") =>
        Assert.assertTrue(htmlBody.contains("Only include their sales from 15 January 2024."))
      case (_, "from", "submitting", "partial") =>
        Assert.assertTrue(htmlBody.contains("15 to 31 January 2024"))
      case _ =>
        Assert.assertFalse(htmlBody.contains("Only include sales from"))
    }
  }

  def checkTransferringFromOtherMSIDPastReturn(): Unit = {
    val htmlH1 = Driver.instance.findElement(By.tagName("h1")).getText
    Assert.assertTrue(htmlH1.equals("Submitted return for 15 to 31 January 2024"))
  }

  def checkTransferringToOtherMSIDPastReturn(): Unit = {
    val htmlH1 = Driver.instance.findElement(By.tagName("h1")).getText
    Assert.assertTrue(htmlH1.equals("Submitted return for 1 to 11 February 2024"))
  }

}
