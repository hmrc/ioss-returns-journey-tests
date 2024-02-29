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
import uk.gov.hmrc.test.ui.pages.CommonPage.{clickContinue, getDoubleIndexString, selectLink, waitForElement}
import uk.gov.hmrc.test.ui.pages.{AuthPage, CommonPage}

class ReturnsStepDef extends BaseStepDef {

  val host: String         = TestConfiguration.url("ioss-returns-frontend")
  val paymentsHost: String = TestConfiguration.url("pay-frontend")

  Given("""the user accesses the authority wizard""") { () =>
    AuthPage.goToAuthStub()
  }

  Then("""^the user is redirected to their IOSS Account$""") { () =>
    driver.getCurrentUrl shouldBe s"$host/your-account"
  }

  Then("""^the user is redirected to the (Change Your Registration|Rejoin) page in IOSS Registration$""") {
    (page: String) =>
      if (page == "Rejoin") {
        CommonPage.checkRegistrationPage("rejoin-registration")
      } else {
        CommonPage.checkRegistrationPage("change-your-registration")
      }
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

  Then("""^the user clicks on the (.*) (link|button)$""") { (link: String, action: String) =>
    link match {
      case "Start your return"        =>
        driver.findElement(By.id("start-your-return")).click()
      case "Change your registration" =>
        driver.findElement(By.id("change-your-registration")).click()
      case "Back to your account"     =>
        driver.findElement(By.id("back-to-your-account")).click()
      case "Make a payment"           =>
        driver.findElement(By.id("make-a-payment")).click()
      case "View submitted returns"   =>
        driver.findElement(By.id("view-submitted-returns")).click()
      case "December 2023"            =>
        waitForElement(By.className("govuk-table__cell--numeric"))
        selectLink("past-returns\\/2023-M12")
      case "November 2023"            =>
        waitForElement(By.className("govuk-table__cell--numeric"))
        selectLink("past-returns\\/2023-M11")
      case "October 2023"             =>
        waitForElement(By.className("govuk-table__cell--numeric"))
        selectLink("past-returns\\/2023-M10")
      case "December 2022"            =>
        waitForElement(By.className("govuk-table__cell--numeric"))
        selectLink("past-returns\\/2022-M12")
      case "November 2022"            =>
        waitForElement(By.className("govuk-table__cell--numeric"))
        selectLink("past-returns\\/2022-M11")
      case "October 2022"             =>
        waitForElement(By.className("govuk-table__cell--numeric"))
        selectLink("past-returns\\/2022-M10")
      case "Pay Now"                  =>
        driver.findElement(By.id("pay-now")).click()
      case "Rejoin this service"      =>
        driver.findElement(By.id("rejoin-scheme")).click()
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
    """^the user chooses the country (.*) as their (first|second|third) correction within the (first|second|third) correction period$"""
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
    """^the user (adds|amends to) (.*) on the (first|second|third) (.*) page for the (first|second|third) correction period$"""
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
    """^the user selects the (CYA|change-return-period|correction period) change link for (first|second|third|page) (.*) from (.*)$"""
  ) { (route: String, index: String, toPage: String, fromPage: String) =>
    val changeIndex = index match {
      case "first"  => "1"
      case "second" => "2"
      case "third"  => "3"
      case _        => "no index required"
    }
    if (route == "change-return-period") {
      driver.findElement(By.id("change-return-period")).click()
    } else if (route == "correction period") {
      driver.findElement(By.id("change-correction-periods")).click()
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

  When("""^the return for (.*) is displayed to the user$""") { (monthYear: String) =>
    val htmlBody = driver.findElement(By.tagName("body")).getText
    Assert.assertTrue(htmlBody.contains(s"Submitted return for $monthYear"))
  }

  When("""^the correct sections are displayed on the previous return with sales to EU and corrections$""") {
    val htmlBody = driver.findElement(By.tagName("body")).getText
    Assert.assertTrue(htmlBody.contains("Sales to EU countries, Northern Ireland or both"))
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
    Assert.assertTrue(htmlBody.contains("Sales to EU countries, Northern Ireland or both"))
    Assert.assertFalse(htmlBody.contains("Corrections"))
    Assert.assertFalse(htmlBody.contains("VAT declared where no payment is due"))
    Assert.assertFalse(htmlBody.contains("VAT owed (including corrections)"))
    Assert.assertTrue(htmlBody.contains("VAT owed"))
  }

  When("""^the user manually navigates to the (.*) link$""") { (link: String) =>
    CommonPage.navigateToBtaLink(link)
  }

  Then("""^the user is directed to the Welsh transition page$""") { () =>
    driver.getCurrentUrl contains s"$host/no-welsh-service?redirectUrl"
  }

  When("""^the payments tile shows that the trader may still owe VAT$""") {
    val htmlBody = driver.findElement(By.tagName("body")).getText
    Assert.assertTrue(htmlBody.contains("You may still owe VAT"))
  }

  When(
    """^the user selects the (first|second) payment option on the (.*) page$"""
  ) { (option: String, url: String) =>
    CommonPage.checkUrl(url)
    CommonPage.selectPaymentOption(option)
  }

  When("""^the payments tile shows there are no outstanding payments$""") {
    val htmlBody = driver.findElement(By.tagName("body")).getText
    Assert.assertTrue(htmlBody.contains("You do not owe anything right now."))
  }

  When("""^the user manually navigates to the outstanding payments page$""") {
    CommonPage.navigateToOutstandingPayments
  }

  When("""^the user does not owe any VAT$""") {
    CommonPage.checkUrl("outstanding-payments")
    val htmlH1 = driver.findElement(By.tagName("h1")).getText
    Assert.assertTrue(htmlH1.contains("You do not owe any Import One Stop Shop VAT"))
  }

  When("""^the payments tile shows one payment due and one payment overdue$""") {
    val htmlBody = driver.findElement(By.tagName("body")).getText
    Assert.assertTrue(htmlBody.contains("Due Payments"))
    Assert.assertTrue(htmlBody.contains("Overdue Payments"))
  }

  When("""^the payments tile shows a single payment due$""") {
    val htmlBody = driver.findElement(By.tagName("body")).getText
    Assert.assertTrue(htmlBody.contains("Due Payments"))
  }

  Then("""^the user has been redirected to the payments service$""") { () =>
    driver.getCurrentUrl startsWith s"$paymentsHost/select-payment-amount?traceId="
  }

  Then("""^the user clicks the Show all sections accordion$""") { () =>
    waitForElement(By.className("govuk-accordion__show-all"))
    val htmlBody = driver.findElement(By.tagName("body")).getText
    if (htmlBody.contains("Show all sections")) {
      driver.findElement(By.className("govuk-accordion__show-all")).click()
    }
  }

  When("""^the user manually navigates to the returns service$""") {
    CommonPage.navigateToReturnsService
  }

  When("""^the user clicks the Pay now link for October 2023$""") {
    selectLink("make-payment\\/2023-M10\\/50000")
  }

  When("""^the correct sections are displayed for a nil return$""") {
    val htmlBody = driver.findElement(By.tagName("body")).getText
    Assert.assertFalse(htmlBody.contains("Sales to EU countries, Northern Ireland or both"))
    Assert.assertFalse(htmlBody.contains("Corrections"))
    Assert.assertFalse(htmlBody.contains("VAT declared where no payment is due"))
    Assert.assertFalse(htmlBody.contains("VAT owed (including corrections)"))
    Assert.assertFalse(htmlBody.contains("Pay now"))
  }

  When("""^the user manually navigates to their December 2023 return$""") {
    CommonPage.navigateToReturn
  }

  Then("""^the link to Rejoin this service is not displayed on the dashboard$""") { () =>
    val htmlBody = driver.findElement(By.tagName("body")).getText
    Assert.assertFalse(htmlBody.contains("Rejoin this service"))
  }

  Then(
    """^they are presented with the correct banner for trader with an exclusion date in the future with outstanding returns$"""
  ) { () =>
    val htmlBody = driver.findElement(By.tagName("body")).getText
    Assert.assertTrue(
      htmlBody.contains("You have requested to leave this service. You must complete and pay any outstanding returns.")
    )
  }

  Then(
    """^they are presented with the correct banner for trader with an exclusion date in the past with a return due$"""
  ) { () =>
    val htmlBody = driver.findElement(By.tagName("body")).getText
    Assert.assertTrue(
      htmlBody.contains("You have left this service. You must complete and pay any outstanding returns.")
    )
  }

  Then(
    """^they are presented with the correct banner for trader removed from service and has outstanding returns$"""
  ) { () =>
    val htmlBody = driver.findElement(By.tagName("body")).getText
    Assert.assertTrue(
      htmlBody.contains("We've removed you from this service, but you must complete and pay your final return.")
    )
  }

  Then(
    """^they are presented with the correct banner for trader removed from service and has no outstanding returns$"""
  ) { () =>
    val htmlBody = driver.findElement(By.tagName("body")).getText
    Assert.assertTrue(
      htmlBody.contains("We've removed you from this service.")
    )
  }

  Then(
    """^they are presented with the correct banner for trader with an exclusion date in the past and no outstanding actions$"""
  ) { () =>
    val htmlBody = driver.findElement(By.tagName("body")).getText
    Assert.assertTrue(
      htmlBody.contains("You have left this service.")
    )
  }

  Then(
    """^they are presented with the correct banner for a quarantined trader with no outstanding returns$"""
  ) { () =>
    val htmlBody = driver.findElement(By.tagName("body")).getText
    Assert.assertTrue(
      htmlBody.contains(
        "We've removed you from this service. You cannot rejoin until"
      )
    )
  }

  Then("""^they are shown the correct returns message for outstanding returns$""") { () =>
    val htmlBody = driver.findElement(By.tagName("body")).getText
    Assert.assertTrue(htmlBody.contains("You can correct a previous return when you submit your final one."))
  }

  Then("""^they are shown the correct returns message for no outstanding returns$""") { () =>
    val htmlBody = driver.findElement(By.tagName("body")).getText
    Assert.assertTrue(
      htmlBody.contains(
        "You can no longer use this service to correct previous returns. You must make any VAT corrections directly with the country where you made the sales."
      )
    )
  }

  Then("""^the returns tile shows final return is completed$""") { () =>
    val htmlBody = driver.findElement(By.tagName("body")).getText
    Assert.assertTrue(htmlBody.contains("You've completed your final return."))
  }

  Then("""^they are presented with the heading for their final return$""") { () =>
    val htmlH1 = driver.findElement(By.tagName("h1")).getText
    Assert.assertTrue(htmlH1.equals("Do you want to start your final return?"))
  }

  Then("""^they are presented with the regular heading for starting a return$""") { () =>
    val htmlH1 = driver.findElement(By.tagName("h1")).getText
    Assert.assertTrue(htmlH1.equals("Do you want to start your return for December 2023?"))
  }

  Then("""^they (are not|are) advised it is their last chance to correct a return$""") { (whichVersion: String) =>
    val htmlBody = driver.findElement(By.tagName("body")).getText
    if (whichVersion == "are not") {
      Assert.assertFalse(htmlBody.contains("This is your last chance to correct a previous return on this service."))
    } else {
      Assert.assertTrue(htmlBody.contains("This is your last chance to correct a previous return on this service."))
    }
  }

  Then("""^the user (is not|is) shown the corrections warning before submission$""") { (whichVersion: String) =>
    val htmlBody = driver.findElement(By.tagName("body")).getText
    if (whichVersion == "is not") {
      Assert.assertFalse(
        htmlBody.contains(
          "After you submit your return, you will not be able to make any corrections using the Import One Stop Shop service."
        )
      )
    } else {
      Assert.assertTrue(
        htmlBody.contains(
          "After you submit your return, you will not be able to make any corrections using the Import One Stop Shop service."
        )
      )
    }
  }

  Then("""^the previously declared text (is not|is) displayed above the amount box$""") { (whichVersion: String) =>
    val htmlBody = driver.findElement(By.tagName("body")).getText
    if (whichVersion == "is not") {
      Assert.assertFalse(
        htmlBody.contains("Enter a minus value if you declared too much in your previous return.")
      )
      Assert.assertFalse(
        htmlBody.contains("Your most recent declaration for this period is")
      )
    } else {
      Assert.assertTrue(
        htmlBody.contains("Enter a minus value if you declared too much in your previous return.")
      )
      Assert.assertTrue(
        htmlBody.contains("Your most recent declaration for this period is")
      )
    }
  }

  Then("""^the no payments due for minus corrections text (will not|will) be displayed$""") { (whichVersion: String) =>
    val htmlBody = driver.findElement(By.tagName("body")).getText
    if (whichVersion == "will not") {
      Assert.assertFalse(
        htmlBody.contains("VAT declared where no payment is due")
      )
      Assert.assertFalse(
        htmlBody.contains("The tax authorities in countries where you declared too much VAT are responsible for refunding any overpayments.")
      )
    } else {
      Assert.assertTrue(
        htmlBody.contains("VAT declared where no payment is due")
      )
      Assert.assertTrue(
        htmlBody.contains("The tax authorities in countries where you declared too much VAT are responsible for refunding any overpayments.")
      )
    }
  }
}
