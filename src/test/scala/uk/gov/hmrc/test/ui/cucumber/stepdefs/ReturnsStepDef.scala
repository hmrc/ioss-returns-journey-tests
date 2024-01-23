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

import org.junit.Assert
import org.openqa.selenium.By
import uk.gov.hmrc.test.ui.conf.TestConfiguration
import uk.gov.hmrc.test.ui.pages.CommonPage.{clickContinue, getDoubleIndexString}
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
    if (url == "successfully-submitted") {
      val htmlBody = driver.findElement(By.tagName("body")).getText
      Assert.assertTrue(htmlBody.contains("Your return reference is"))
    }
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
    """^the user (enters|changes) (first|second|third) country total sales of (.*) for (first|second|third|fourth|fifth) selected VAT rate on the (.*) page$"""
  ) { (mode: String, countryIndex: String, data: String, vatRateIndex: String, page: String) =>
    CommonPage.checkDoubleIndexURL(countryIndex, vatRateIndex, page, false)
    if (mode == "changes") {
      CommonPage.clearData()
    }
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
    driver.findElement(By.id("choice")).click()
    clickContinue()
  }

  When(
    """^the user enters a different amount of VAT totalling (.*) for the (first|second|third) country and the (first|second) selected VAT rate on the (.*) page$"""
  ) { (newVatAmount: String, indexCountry: String, indexVatRate: String, page: String) =>
    CommonPage.checkDoubleIndexURL(indexCountry, indexVatRate, page, false)
    driver.findElement(By.id("choice_different")).click()
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

  When(
    """^the user chooses the country (.*) as their (first|second|third) correction within the (first|second) correction period$"""
  ) { (data: String, countryIndex: String, periodIndex: String) =>
    CommonPage.checkDoubleIndexURL(periodIndex, countryIndex, "correction-country", false)
    CommonPage.selectValueAutocomplete(data)
  }

  Then("""^the user selects the (change|remove) link for (.*)$""") { (linkType: String, link: String) =>
    CommonPage.selectLink(link)
  }

  Then(
    """^the user selects the (mini CYA|list) change link for (first|second|third|page) (.*)$"""
  ) { (route: String, index: String, toPage: String) =>
    val changeIndex = index match {
      case "first"  => "1"
      case "second" => "2"
      case "third"  => "3"
      case _        => "no index required"
    }
    if (route == "mini CYA") {
      CommonPage.selectLink(s"$toPage\\/$changeIndex\\?waypoints\\=change-check-sales")
    } else {
      CommonPage.selectLink(s"$toPage\\/$changeIndex\\?waypoints\\=change-add-sales-country-list")
    }
  }

  When("""^the user manually navigates to the first correction country$""") {
    CommonPage.navigateToFirstCorrectionCountry
  }

  When(
    """^the user (adds|amends to) (.*) on the (first|second|third) (.*) page for the (first|second) correction period$"""
  ) { (mode: String, data: String, countryIndex: String, url: String, periodIndex: String) =>
    CommonPage.checkDoubleIndexURL(periodIndex, countryIndex, url, false)
    if (mode == "amends to") {
      CommonPage.clearData()
    }
    CommonPage.enterData("value", data)
    CommonPage.clickContinue()
  }

  Then(
    """^the user selects the correction countries list change link for the (first|second|third) country on the (first|second) correction period$"""
  ) { (countryIndex: String, correctionIndex: String) =>
    val indexString = getDoubleIndexString(correctionIndex, countryIndex, true)
    CommonPage.selectLink(
      s"country-vat-correction-amount\\$indexString\\?waypoints\\=change-add-correction-list-countries-1"
    )
  }

  Then(
    """^the user selects the (CYA|change-return-period) change link for (first|second|third|page) (.*) from (.*)$"""
  ) { (route: String, index: String, toPage: String, fromPage: String) =>
    val changeIndex = index match {
      case "first"  => "1"
      case "second" => "2"
      case "third"  => "3"
      case _        => "no index required"
    }
    if (route == "change-return-period") {
      driver.findElement(By.id("change-return-period")).click()
    } else if (route == "CYA") {
      CommonPage.selectLink(s"$toPage\\?waypoints\\=$fromPage")
    }
  }

  When(
    """^the user picks year (2022|2023) on the (.*) page$"""
  ) { (answer: String, url: String) =>
    CommonPage.checkUrl(url)
    CommonPage.selectYearRadioButton(answer)
  }

  When(
    """^the user picks month (October|November|December) on the (.*) page$"""
  ) { (answer: String, url: String) =>
    CommonPage.checkUrl(url)
    CommonPage.selectMonthRadioButton(answer)
  }

  When("""^the user manually navigates to the previous submitted return for November 2023$""") {
    CommonPage.navigateToPreviousReturn
  }

  When("""^the return for November 2023 is displayed to the user$""") {
    val htmlBody = driver.findElement(By.tagName("body")).getText
    Assert.assertTrue(htmlBody.contains("Submitted return for November 2023"))
  }

  When("""^the correct sections are displayed on the previous return with sales to EU and corrections$""") {
    val htmlBody = driver.findElement(By.tagName("body")).getText
    Assert.assertTrue(htmlBody.contains("Sales to EU countries and Northern Ireland"))
    Assert.assertTrue(htmlBody.contains("Corrections"))
    Assert.assertTrue(htmlBody.contains("VAT declared where no payment is due"))
    Assert.assertTrue(htmlBody.contains("VAT owed (including corrections)"))
  }

  Then("""^the user clicks on the (.*) breadcrumb""") { (id: String) =>
    driver
      .findElement(By.id(id))
      .click()
  }

  When("""^the correct sections are displayed on the previous return with no corrections""") {
    val htmlBody = driver.findElement(By.tagName("body")).getText
    Assert.assertTrue(htmlBody.contains("Sales to EU countries and Northern Ireland"))
    Assert.assertFalse(htmlBody.contains("Corrections"))
//    Should be false - for VAT declared where no payment is due VEIOSS-492
    Assert.assertTrue(htmlBody.contains("VAT declared where no payment is due"))
    Assert.assertFalse(htmlBody.contains("VAT owed (including corrections)"))
    Assert.assertTrue(htmlBody.contains("VAT owed"))
  }

}
