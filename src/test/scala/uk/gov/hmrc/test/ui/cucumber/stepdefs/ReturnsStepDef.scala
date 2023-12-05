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

package uk.gov.hmrc.test.ui.cucumber.stepdefs

import org.openqa.selenium.By
import uk.gov.hmrc.test.ui.conf.TestConfiguration
import uk.gov.hmrc.test.ui.pages.CommonPage.clickContinue
import uk.gov.hmrc.test.ui.pages.{AuthPage, CommonPage}

class ReturnsStepDef extends BaseStepDef {

  val host: String = TestConfiguration.url("ioss-returns-frontend")

  Given("""the user accesses the authority wizard""") { () =>
    AuthPage.goToAuthStub()
  }

  Then("""^the user is redirected to their IOSS Account$""") { () =>
    driver.getCurrentUrl shouldBe s"$host/your-account"
  }

  Then("""^the user is redirected to the Change Your Registration page in IOSS Registration$""") { () =>
    CommonPage.checkChangeYourRegistration()
  }

  When("""^a user with VRN (.*) and IOSS Number (.*) accesses the returns journey""") {
    (vrn: String, iossNumber: String) =>
      AuthPage.loginUsingAuthorityWizard("with", "IOSS and VAT", vrn, iossNumber)
  }

  When("""^a user with VRN (.*) and no IOSS enrolment accesses the returns journey""") { (vrn: String) =>
    AuthPage.loginUsingAuthorityWizard("with", "VAT", vrn, "none")
  }

  Then("""^the user is on the (.*) page$""") { (url: String) =>
    CommonPage.checkUrl(url)
  }

  Then("""^the user clicks on the (.*) link$""") { (link: String) =>
    link match {
      case "Start your return"        =>
        driver.findElement(By.id("start-return")).click()
      case "Change your registration" =>
        driver.findElement(By.id("change-your-registration")).click()
      case "Back to your account"     =>
        driver.findElement(By.id("back-to-your-account")).click()
      case _                          =>
        throw new Exception("Link doesn't exist")
    }
  }
  When("""^the user answers (yes|no) on the (.*) page$""") { (data: String, url: String) =>
    CommonPage.checkUrl(url)
    CommonPage.selectAnswer(data)
  }

  When(
    """^the user enters (first|second|third) country total sales of (.*) for (first|second|third|fourth|fifth) selected VAT rate on the (.*) page$"""
  ) { (countryIndex: String, data: String, vatRateIndex: String, page: String) =>
    CommonPage.checkDoubleIndexURL(countryIndex, vatRateIndex, page)
    CommonPage.enterData("value", data)
    CommonPage.clickContinue()
  }

  When("""^the user ticks the (first|second|third|fourth|fifth) checkbox on the (first|second|third) (.*) page$""") {

    (checkbox: String, index: String, page: String) =>
      val pageIndex = index match {
        case "first"  => "1"
        case "second" => "2"
        case "third"  => "3"
        case "fourth" => "4"
        case "fifth"  => "5"
        case _        => throw new Exception("Index doesn't exist")
      }
      CommonPage.checkUrl(s"$page/$pageIndex")
      CommonPage.tickCheckbox(checkbox)
  }

  Then("""^the user clicks the continue button$""") { () =>
    clickContinue()
  }

  When(
    """^the user confirms the vat for the (first|second|third) EU country as the suggested amount for the (first|second|third|fourth|fifth) selected VAT rate on the (.*) page$"""
  ) { (countryIndex: String, vatRateIndex: String, page: String) =>
    val pageIndex = countryIndex match {
      case "first"  => "1"
      case "second" => "2"
      case "third"  => "3"
      case "fourth" => "4"
      case "fifth"  => "5"
      case _        => throw new Exception("Index doesn't exist")
    }
    CommonPage.checkUrl(s"$page/$pageIndex")
    driver.findElement(By.id("value_0")).click()
    clickContinue()
  }

  When(
    """^the user enters a different amount of VAT totalling (.*) for the (first|second|third) country and the (first|second) selected VAT rate on the (.*) page$"""
  ) { (newVatAmount: String, indexCountry: String, indexVatRate: String, page: String) =>
    CommonPage.checkDoubleIndexURL(indexCountry, indexVatRate, page)
    driver.findElement(By.id("value_1")).click()
    CommonPage.enterData("amount", newVatAmount)
    CommonPage.clickContinue()
  }

  When("""^the user selects (.*) on the (first|second|third|fourth|fifth) (.*) page$""") {
    (data: String, index: String, page: String) =>
      val pageIndex = index match {
        case "first"  => "1"
        case "second" => "2"
        case "third"  => "3"
        case "fourth" => "4"
        case "fifth"  => "5"
        case _        => throw new Exception("Index doesn't exist")
      }
      CommonPage.checkUrl(s"$page/$pageIndex")
      CommonPage.selectValueAutocomplete(data)
  }

  Then("""^the user selects the (change|remove) link for (.*)$""") { (linkType: String, link: String) =>
    CommonPage.selectLink(link)
  }

}
