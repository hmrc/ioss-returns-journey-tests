/*
 * Copyright 2023 HM Revenue & Customs
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

import org.openqa.selenium.By
import org.openqa.selenium.support.ui.{ExpectedConditions, FluentWait}
import uk.gov.hmrc.test.ui.conf.TestConfiguration

object CommonPage extends BasePage {

  val host: String     = TestConfiguration.url("ioss-returns-frontend")
  val authHost: String = TestConfiguration.url("auth-login-stub")

  def checkChangeYourRegistration(): Unit =
    driver.getCurrentUrl should startWith(
      TestConfiguration.url("ioss-registration-frontend") + "/change-your-registration"
    )

  def checkUrl(url: String): Unit =
    driver.getCurrentUrl should startWith(s"$host/$url")

  def selectAnswer(data: String): Unit = {
    data match {
      case "yes" => driver.findElement(By.id("value")).click()
      case "no"  => driver.findElement(By.id("value-no")).click()
      case _     => throw new Exception("Option doesn't exist")
    }
    clickContinue()
  }
  def clickContinue(): Unit =
    driver.findElement(By.id("continue")).click()

  def enterData(inputId: String, data: String): Unit =
    driver.findElement(By.id(inputId)).sendKeys(data)

  def tickCheckbox(checkbox: String): Unit =
    checkbox match {
      case "first"  => driver.findElement(By.id("value_0")).click()
      case "second" => driver.findElement(By.id("value_1")).click()
      case "third"  => driver.findElement(By.id("value_2")).click()
      case "fourth" => driver.findElement(By.id("value_3")).click()
      case "fifth"  => driver.findElement(By.id("value_4")).click()
      case _        => throw new Exception("Checkbox doesn't exist")
    }

  def selectValueAutocomplete(data: String): Unit = {
    waitForElement(By.id("value"))
    enterData("value", data)
    clickContinue()
  }

  def waitForElement(by: By) =
    new FluentWait(driver).until {
      ExpectedConditions.presenceOfElementLocated(by)
    }

  def selectLink(link: String): Unit =
    driver.findElement(By.cssSelector(s"a[href*=$link]")).click()

  def checkDoubleIndexURL(firstIndex: String, secondIndex: String, page: String): Unit = {
    val indexesUrl = (firstIndex, secondIndex) match {
      case ("first", "first")   => "/1/1"
      case ("first", "second")  => "/1/2"
      case ("first", "third")   => "/1/3"
      case ("second", "first")  => "/2/1"
      case ("second", "second") => "/2/2"
      case ("second", "third")  => "/2/3"
      case ("third", "first")   => "/3/1"
      case ("third", "second")  => "/3/2"
      case _                    => throw new Exception("Index combination is invalid")
    }
    CommonPage.checkUrl(page + indexesUrl)
  }

  def clearData(): Unit =
    driver.findElement(By.id("value")).clear()

}
