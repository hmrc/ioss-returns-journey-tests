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

import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedConditions
import org.scalatest.concurrent.Eventually
import org.scalatest.matchers.should.Matchers._
import org.scalatest.time._
import uk.gov.hmrc.configuration.TestEnvironment
import uk.gov.hmrc.selenium.webdriver.Driver

import java.io.File

object FileUpload extends BasePage with Eventually {

  private val dashboardUrl: String        =
    TestEnvironment.url("ioss-returns-frontend")
  private val dashboardJourneyUrl: String = "/pay-vat-on-goods-sold-to-eu/import-one-stop-shop-returns-payments"

  def selectFileUpload(selectButton: String): Unit = {
    eventually(timeout(Span(1, Seconds)), interval(Span(200, Millis))) {
      selectButton match {
        case "Yes"                                                             => click(By.id("value"))
        case "No, enter them myself" | "No, I want to upload a different file" =>
          click(By.id("value-2"))
        case _                                                                 => throw new Exception("Option doesn't exist")
      }
    }
    click(continueButton)
  }

  def uploadFile(file: String): Unit = {
    val pathNameString: String = System.getProperty("user.dir") + s"/src/test/scala/uk/gov/hmrc/ui/data/$file"
    val filePath               = new File(pathNameString)

    //Cannot use clear function in PageObject sendKeys
    Driver.instance.findElement(By.id("file-input")).sendKeys(filePath.getAbsolutePath)

    click(By.id("file-upload-button"))
  }

  def fileUploadedUrlCheck(iossNumber: String): Unit = {
    val fileUploadedUrlStart = s"$dashboardUrl$dashboardJourneyUrl/$iossNumber/file-uploaded"
    fluentWait.until(ExpectedConditions.urlContains(fileUploadedUrlStart))
    getCurrentUrl should startWith(fileUploadedUrlStart)
  }
}
