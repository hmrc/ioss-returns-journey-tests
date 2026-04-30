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

import org.openqa.selenium.support.ui.ExpectedConditions
import org.scalatest.matchers.should.Matchers._
import uk.gov.hmrc.configuration.TestEnvironment

object Save extends BasePage {

  private val dashboardUrl: String        =
    TestEnvironment.url("ioss-returns-frontend")
  private val dashboardJourneyUrl: String = "/pay-vat-on-goods-sold-to-eu/import-one-stop-shop-returns-payments"

  def checkProgressSaved(iossNumberPeriod: String): Unit = {
    val url = s"$dashboardUrl$dashboardJourneyUrl/$iossNumberPeriod/progress-saved"
    fluentWait.until(ExpectedConditions.urlContains(url))
    getCurrentUrl should startWith(url)
  }
}
