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
import uk.gov.hmrc.ui.pages.Auth.{authUrl, fluentWait}
import uk.gov.hmrc.ui.pages.Dashboard.fluentWait

object PastReturn extends BasePage {

  def showAllAccordion(): Unit = {
    fluentWait.until(ExpectedConditions.presenceOfElementLocated(By.className("govuk-accordion__show-all")))

    val htmlBody = Driver.instance.findElement(By.tagName("body")).getText

    if (htmlBody.contains("Show all sections")) {
      click(By.className("govuk-accordion__show-all"))
    }
  }

  def selectPastReturn(link: String): Unit = {
    fluentWait.until(ExpectedConditions.presenceOfElementLocated(By.className("govuk-table__cell--numeric")))
    click(By.cssSelector(s"a[href*=$link]"))
  }

}
