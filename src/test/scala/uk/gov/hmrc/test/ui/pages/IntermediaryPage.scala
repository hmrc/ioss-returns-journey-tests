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

}
