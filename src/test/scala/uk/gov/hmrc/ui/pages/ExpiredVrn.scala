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

object ExpiredVrn extends BasePage {

  def excludedExpiredVrnPastOutstandingReturnsBanner(): Unit = {
    val htmlBody = Driver.instance.findElement(By.tagName("body")).getText
    Assert.assertTrue(
      htmlBody.contains(
        "You have left this service. You must complete and pay any outstanding returns.\nYou are no longer VAT registered. You must re-register for VAT to use the Import One Stop Shop service."
      )
    )
  }

  def excludedExpiredVrnPastNoOutstandingReturnsBanner(): Unit = {
    val htmlBody = Driver.instance.findElement(By.tagName("body")).getText
    Assert.assertTrue(
      htmlBody.contains(
        "You have left this service.\nYou are no longer VAT registered. You must re-register for VAT to use the Import One Stop Shop service."
      )
    )
  }

  def removedExpiredVrnPastNoOutstandingReturnsBanner(): Unit = {
    val htmlBody = Driver.instance.findElement(By.tagName("body")).getText
    Assert.assertTrue(
      htmlBody.contains(
        "We've removed you from this service.\nYou are no longer VAT registered. You must re-register for VAT to use the Import One Stop Shop service."
      )
    )
  }
}
