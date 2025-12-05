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

package uk.gov.hmrc.test.ui.cucumber.stepdefs

import org.junit.Assert
import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedConditions
import uk.gov.hmrc.test.ui.conf.TestConfiguration
import uk.gov.hmrc.test.ui.pages.CommonPage.{checkFullMonthPastReturn, checkTransferringFromOtherMSIDPastReturn, checkTransferringToOtherMSIDPastReturn, checkUrl, clickBackButton, clickContinue, clickSubmit, fluentWait, getDoubleIndexString, selectIOSSNumberRadioButton, selectLink, selectRadioButton, waitForElement}
import uk.gov.hmrc.test.ui.pages.{AuthPage, CommonPage}

import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ReturnsStepDef extends BaseStepDef {

  val host: String         = TestConfiguration.url("ioss-returns-frontend")
  val paymentsHost: String = TestConfiguration.url("pay-frontend")

  Given("""the user accesses the authority wizard""") { () =>
    AuthPage.goToAuthStub()
  }

  Then("""^the user is redirected to their IOSS Account$""") { () =>
    val url = s"$host/your-account"
    fluentWait.until(ExpectedConditions.urlContains(url))
    driver.getCurrentUrl shouldBe url
  }

  Then("""^the user is redirected to the (Change Your Registration|Rejoin) page in IOSS Registration$""") {
    (page: String) =>
      if (page == "Rejoin") {
        CommonPage.checkRegistrationPage("rejoin-registration")
      } else {
        CommonPage.checkRegistrationPage("change-your-registration")
      }
  }

  When("""^a (user|assistant) with VRN (.*) and IOSS Number (.*) accesses the returns journey""") {
    (user: String, vrn: String, iossNumber: String) =>
      AuthPage.loginUsingAuthorityWizard(user, "with", "IOSS and VAT", vrn, iossNumber)
  }

  When("""^a user with current IOSS Number (.*) and at least one previous IOSS number accesses the returns journey""") {
    (iossNumber: String) =>
      AuthPage.loginUsingAuthorityWizard("user", "with", "IOSS and VAT", "100000001", iossNumber)
  }

  When("""^a user with VRN (.*) and no IOSS enrolment accesses the returns journey""") { (vrn: String) =>
    AuthPage.loginUsingAuthorityWizard("user", "with", "VAT", vrn, "none")
  }

  Then("""^the user is on the (.*) page$""") { (url: String) =>
    CommonPage.checkUrl(url)
    if (url == "return-successfully-submitted") {
      val htmlBody = driver.findElement(By.tagName("body")).getText
      Assert.assertTrue(htmlBody.contains("Your return reference is"))
    }
  }

  Then("""^the user clicks on the (.*) (link|button)$""") { (link: String, action: String) =>
    link match {
      case "Start your return"                   =>
        driver.findElement(By.id("start-your-return")).click()
      case "Continue your return"                =>
        driver.findElement(By.id("continue-your-return")).click()
      case "Change your registration"            =>
        driver.findElement(By.id("change-your-registration")).click()
      case "Back to your account"                =>
        driver.findElement(By.id("back-to-your-account")).click()
      case "Make a payment"                      =>
        driver.findElement(By.id("make-a-payment")).click()
      case "View submitted returns"              =>
        driver.findElement(By.id("view-submitted-returns")).click()
      case "December 2023"                       =>
        waitForElement(By.className("govuk-table__cell--numeric"))
        selectLink("past-returns\\/2023-M12")
      case "November 2023"                       =>
        waitForElement(By.className("govuk-table__cell--numeric"))
        selectLink("past-returns\\/2023-M11")
      case "October 2023"                        =>
        waitForElement(By.className("govuk-table__cell--numeric"))
        selectLink("past-returns\\/2023-M10")
      case "December 2022"                       =>
        waitForElement(By.className("govuk-table__cell--numeric"))
        selectLink("past-returns\\/2022-M12")
      case "November 2022"                       =>
        waitForElement(By.className("govuk-table__cell--numeric"))
        selectLink("past-returns\\/2022-M11")
      case "October 2022"                        =>
        waitForElement(By.className("govuk-table__cell--numeric"))
        selectLink("past-returns\\/2022-M10")
      case "January 2024"                        =>
        waitForElement(By.className("govuk-table__cell--numeric"))
        selectLink("past-returns\\/2024-M1")
      case "February 2024"                       =>
        waitForElement(By.className("govuk-table__cell--numeric"))
        selectLink("past-returns\\/2024-M2")
      case "Pay Now"                             =>
        driver.findElement(By.id("pay-now")).click()
      case "Rejoin this service"                 =>
        driver.findElement(By.id("rejoin-scheme")).click()
      case "Return to your current registration" =>
        driver.findElement(By.id("submitted-returns-history")).click()
      case "continue to complete your return"    =>
        driver.findElement(By.id("continueToYourReturn")).click()
      case "return to your account"              =>
        driver.findElement(By.id("backToYourAccount")).click()
      case "sign out and come back later"        =>
        driver.findElement(By.id("signOut")).click()
      case "Pay for a previous registration"     =>
        CommonPage.selectLink("which-previous-registration-to-pay")
      case _                                     =>
        throw new Exception("Link doesn't exist")
    }
  }
  When("""^the user answers (yes|no) on the (.*) page$""") { (data: String, url: String) =>
    if (url startsWith "thisYear-") {

      val thisYear    = LocalDate.now().getYear
      val newUrl      = url.substring(8)
      val thisYearUrl = s"$thisYear$newUrl"

      CommonPage.checkUrl(thisYearUrl)
    } else {
      CommonPage.checkUrl(url)
    }
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

  Then("""^the user clicks the (continue|submit) button$""") { (button: String) =>
    if (button == "submit") {
      clickSubmit()
    } else {
      clickContinue()
    }
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
    """^the user selects the (mini CYA|second mini CYA|list) change link for (first|second|third|page) (.*)$"""
  ) { (route: String, index: String, toPage: String) =>
    val changeIndex = index match {
      case "first"  => "1"
      case "second" => "2"
      case "third"  => "3"
      case _        => "no index required"
    }
    if (route == "mini CYA") {
      CommonPage.selectLink(s"$toPage\\/$changeIndex\\?waypoints\\=change-check-sales")
    } else if (route == "second mini CYA") {
      CommonPage.selectLink(s"$toPage\\/$changeIndex\\?waypoints\\=change-check-sales-2")
    } else {
      CommonPage.selectLink(s"$toPage\\/$changeIndex\\?waypoints\\=change-add-sales-country-list")
    }
  }

  When("""^the user manually navigates to the first correction country$""") {
    CommonPage.navigateToFirstCorrectionCountry()
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
    """^the user selects the (CYA|correction period) change link for (first|second|third|page) (.*) from (.*)$"""
  ) { (route: String, index: String, toPage: String, fromPage: String) =>
    if (route == "correction period") {
      driver.findElement(By.id("change-correction-periods")).click()
    } else if (route == "CYA") {
      CommonPage.selectLink(s"$toPage\\?waypoints\\=$fromPage")
    }
  }

  When(
    """^the user picks year (two years ago|last year|2023) on the (.*) page$"""
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
    CommonPage.navigateToPreviousReturn()
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

  When("""^the correct sections are displayed on the previous return with no corrections""") {
    val htmlBody = driver.findElement(By.tagName("body")).getText
    Assert.assertTrue(htmlBody.contains("Sales to EU countries, Northern Ireland or both"))
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
    CommonPage.navigateToOutstandingPayments()
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
    CommonPage.navigateToReturnsService()
  }

  When("""^the user clicks the Pay now link for October 2023$""") {
    selectLink("make-payment\\/2023-M10")
  }

  When("""^the correct sections are displayed for a nil return$""") {
    val htmlBody = driver.findElement(By.tagName("body")).getText
    Assert.assertFalse(htmlBody.contains("Sales to EU countries, Northern Ireland or both"))
    Assert.assertFalse(htmlBody.contains("Corrections"))
    Assert.assertFalse(htmlBody.contains("VAT declared where no payment is due"))
    Assert.assertFalse(htmlBody.contains("VAT owed (including corrections)"))
    Assert.assertFalse(htmlBody.contains("Pay now"))
  }

  When(
    """^the user manually navigates to their (.*) return$"""
  ) { (returnPeriod: String) =>
    CommonPage.navigateToReturn(returnPeriod)
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
      htmlBody.contains("You have asked to leave this service. You must complete and pay any outstanding returns.")
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

  Then("""^they (are not|are) presented with the heading for their final return$""") { (finalReturn: String) =>
    val htmlH1 = driver.findElement(By.tagName("h1")).getText
    if (finalReturn == "are not") {
      Assert.assertFalse(htmlH1.equals("Do you want to start your final return?"))
    } else {
      Assert.assertTrue(htmlH1.equals("Do you want to start your final return?"))
    }
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
        htmlBody.contains("Your most recent declaration for this month is")
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
        htmlBody.contains(
          "The tax authorities in countries where you declared too much VAT are responsible for refunding any overpayments."
        )
      )
    } else {
      Assert.assertTrue(
        htmlBody.contains("VAT declared where no payment is due")
      )
      Assert.assertTrue(
        htmlBody.contains(
          "The tax authorities in countries where you declared too much VAT are responsible for refunding any overpayments."
        )
      )
    }
  }

  When("""^the user accesses the start return link via secure messages$""") { () =>
    CommonPage.navigateToSecureStartReturn()
  }

  Then("""^the correct IOSS number (.*) is displayed on the (dashboard|page)$""") {
    (iossNumber: String, page: String) =>
      val htmlBody = driver.findElement(By.tagName("body")).getText
      Assert.assertTrue(htmlBody.contains("IOSS number: " + iossNumber))
  }

  Then("""^the correct link for (one|more than one) previous IOSS number is displayed$""") { (variation: String) =>
    val htmlBody = driver.findElement(By.tagName("body")).getText

    val dateTimeFormatter  = DateTimeFormatter.ofPattern("MMMM yyyy")
    val startDate          = LocalDate.now().minusMonths(6)
    val startDateFormatted = dateTimeFormatter.format(startDate)
    val endDate            = LocalDate.now().minusMonths(4)
    val endDateFormatted   = dateTimeFormatter.format(endDate)

    if (variation == "one") {
      Assert.assertTrue(htmlBody.contains(s"View returns from $startDateFormatted to $endDateFormatted"))
    } else {
      Assert.assertTrue(htmlBody.contains("View returns from a previous registration"))
    }

  }

  Then(
    """^the user clicks on the view returns link for (one previous registration|multiple previous registrations)$"""
  ) { (previousRegistrations: String) =>
    previousRegistrations match {
      case "one previous registration"       =>
        driver.findElement(By.id("view-returns-one-reg")).click()
      case "multiple previous registrations" =>
        driver.findElement(By.id("return-registration-selection")).click()
      case _                                 =>
        throw new Exception("Link doesn't exist")
    }
  }

  Then(
    """^the user clicks on the first return month for (one previous|first previous|second previous|current) registration$"""
  ) { (registration: String) =>
    val month = registration match {
      case "one previous" | "second previous" => "6".toInt
      case "first previous"                   => "9".toInt
      case "current"                          => "3".toInt
      case _                                  =>
        throw new Exception("Registration doesn't exist")
    }

    val returnMonth  = LocalDate.now().minusMonths(month).getMonthValue
    val returnYear   = LocalDate.now().minusMonths(month).getYear
    val periodString = s"$returnYear-M$returnMonth"

    waitForElement(By.className("govuk-table__cell--numeric"))
    selectLink(s"past-returns\\/$periodString")

    checkUrl(s"past-returns/$periodString")
  }

  Then("""^the user clicks back on the browser$""") { () =>
    clickBackButton()
  }

  Then("""^the correct previous IOSS numbers are displayed$""") { () =>
    val htmlBody = driver.findElement(By.tagName("body")).getText
    Assert.assertTrue(htmlBody.contains("IM9007230001"))
    Assert.assertTrue(htmlBody.contains("IM9007230002"))
  }

  Then("""^the user selects the returns for IOSS number (IM9007230001|IM9007230002)$""") { (iossNumber: String) =>
    selectIOSSNumberRadioButton(iossNumber)
  }

  Then("""^the correct returns and payments references are shown for (.*)$""") { (iossNumber: String) =>
    val htmlBody = driver.findElement(By.tagName("body")).getText
    Assert.assertTrue(htmlBody.contains(s"XI/$iossNumber"))
  }

  Then(
    """^the user transferring (from|to) another MSID is (offered|submitting) a (partial|full) return for the correct period$"""
  ) { (transferDirection: String, returnStage: String, returnType: String) =>
    val htmlBody = driver.findElement(By.tagName("body")).getText
    if (transferDirection == "to" && returnStage == "offered" && returnType == "partial") {
      Assert.assertTrue(htmlBody.contains("Only include sales up to 11 February 2024."))
    } else if (transferDirection == "to" && returnStage == "submitted" && returnType == "partial") {
      Assert.assertTrue(htmlBody.contains("1 to 11 February 2024"))
    } else if (transferDirection == "from" && returnStage == "offered" && returnType == "partial") {
      Assert.assertTrue(htmlBody.contains("Only include sales from 15 January 2024."))
    } else if (transferDirection == "from" && returnStage == "submitted" && returnType == "partial") {
      Assert.assertTrue(htmlBody.contains("15 to 31 January 2024"))
    } else {
      Assert.assertFalse(htmlBody.contains("Only include sales from"))
    }
  }

  When("""^the user is on the start return page for the first available return period within 3 years$""") { () =>
    val firstAvailableReturnMonth  = LocalDate.now().minusMonths(37).getMonthValue
    val firstAvailableReturnYear   = LocalDate.now().minusMonths(37).getYear
    val firstAvailablePeriodString = s"$firstAvailableReturnYear-M$firstAvailableReturnMonth"

    checkUrl(s"$firstAvailablePeriodString/start-return")
  }

  Then("""^the user clicks the Save and come back later button$""") { () =>
    driver.findElement(By.id("saveProgress")).click()
  }

  Then("""^the user selects the (.*) option""") { (option: String) =>
    option match {
      case "Continue my return"               => driver.findElement(By.id("value_0")).click()
      case "Delete my return and start again" => driver.findElement(By.id("value_1")).click()
      case _                                  => throw new Exception("Link doesn't exist")
    }
    clickContinue()
  }

  Then(
    """^the dashboard warning is displayed regarding (multiple outstanding payments|one outstanding payment) on their (one previous registration|multiple previous registrations) (.*)$"""
  ) { (outstandingPayments: String, registrations: String, iossNumber: String) =>
    val htmlBody = driver.findElement(By.tagName("body")).getText

    val amount =
      if (outstandingPayments == "multiple outstanding payments" && registrations == "one previous registration") {
        "£1,500"
      } else if (
        outstandingPayments == "multiple outstanding payments" && registrations == "multiple previous registrations"
      ) {
        "£4,250"
      } else if (
        outstandingPayments == "one outstanding payment" && registrations == "multiple previous registrations"
      ) {
        "£3,750"
      } else {
        "£2,000"
      }

    if (registrations == "multiple previous registrations") {
      Assert.assertTrue(htmlBody.contains(s"This account owes a total of $amount on previous registrations."))
    } else {
      Assert.assertTrue(
        htmlBody.contains(s"This account owes $amount on a previous registration with IOSS number $iossNumber.")
      )
    }
  }

  Then("""^the correct (single|multiple) outstanding payment amounts are displayed for each previous registration$""") {
    (outstanding: String) =>
      val htmlBody = driver.findElement(By.tagName("body")).getText
      if (outstanding == "single") {
        Assert.assertTrue(htmlBody.contains("You owe £1,750 for your registration with IOSS number\nIM9007230004"))
        Assert.assertTrue(htmlBody.contains("You owe £2,000 for your registration with IOSS number\nIM9007230005"))
      } else {
        Assert.assertTrue(htmlBody.contains("You owe £2,750 for your registration with IOSS number\nIM9007230001"))
        Assert.assertTrue(htmlBody.contains("You owe £1,500 for your registration with IOSS number\nIM9007230002"))
      }

  }

  When(
    """^the user picks the (first|second) previous IOSS Number$"""
  ) { (answer: String) =>
    val radioButtonToSelect = answer match {
      case "first"  => "1"
      case "second" => "2"
      case _        => throw new Exception("Selection doesn't exist")
    }
    selectRadioButton(radioButtonToSelect)
  }

  Then("""^the user has been redirected to BTA$""") { () =>
    driver.getCurrentUrl endsWith "/business-account"
  }

  Then(
    """^the user transferring (to|from) another MSID has the correct partial dates in the past return$"""
  ) { (direction: String) =>
    if (direction == "to") {
      checkTransferringToOtherMSIDPastReturn()
    } else {
      checkTransferringFromOtherMSIDPastReturn()
    }

  }

  Then("""^the correct return message for returns due more than three years ago for excluded traders is displayed$""") {
    () =>
      val htmlBody = driver.findElement(By.tagName("body")).getText
      Assert.assertTrue(
        htmlBody.contains(
          "You must complete your July 2020 return with the countries where you made your sales."
        )
      )
      Assert.assertTrue(
        htmlBody.contains(
          "You must complete your August 2020 return with the countries where you made your sales."
        )
      )
      Assert.assertTrue(
        htmlBody.contains(
          "You must complete your September 2020 return with the countries where you made your sales."
        )
      )
      Assert.assertTrue(
        htmlBody.contains(
          "You must complete your October 2020 return with the countries where you made your sales."
        )
      )
      Assert.assertTrue(
        htmlBody.contains(
          "You must complete your November 2020 return with the countries where you made your sales."
        )
      )
      Assert.assertTrue(
        htmlBody.contains(
          "You must complete your December 2020 return with the countries where you made your sales."
        )
      )
  }

  Then(
    """^the correct payment message for payments due more than three years ago for excluded traders is displayed$"""
  ) { () =>
    val htmlBody = driver.findElement(By.tagName("body")).getText
    Assert.assertTrue(
      htmlBody.contains(
        "You have an outstanding IOSS VAT payment for July 2020. You must contact the countries where you made your sales to pay the VAT due."
      )
    )
    Assert.assertTrue(
      htmlBody.contains(
        "You have an outstanding IOSS VAT payment for August 2020. You must contact the countries where you made your sales to pay the VAT due."
      )
    )
    Assert.assertTrue(
      htmlBody.contains(
        "You have an outstanding IOSS VAT payment for September 2020. You must contact the countries where you made your sales to pay the VAT due."
      )
    )
    Assert.assertTrue(
      htmlBody.contains(
        "You have an outstanding IOSS VAT payment for October 2020. You must contact the countries where you made your sales to pay the VAT due."
      )
    )
    Assert.assertTrue(
      htmlBody.contains(
        "You have an outstanding IOSS VAT payment for November 2020. You must contact the countries where you made your sales to pay the VAT due."
      )
    )
    Assert.assertTrue(
      htmlBody.contains(
        "You have an outstanding IOSS VAT payment for December 2020. You must contact the countries where you made your sales to pay the VAT due."
      )
    )
  }

  Then(
    """^they are presented with the correct banner for expired VRN trader who has left the service and has outstanding returns$"""
  ) { () =>
    val htmlBody = driver.findElement(By.tagName("body")).getText
    Assert.assertTrue(
      htmlBody.contains(
        "You have left this service. You must complete and pay any outstanding returns.\nYou are no longer VAT registered. You must re-register for VAT to use the Import One Stop Shop service."
      )
    )
  }

  Then(
    """^they are presented with the correct banner for expired VRN trader who has left the service and has no outstanding returns$"""
  ) { () =>
    val htmlBody = driver.findElement(By.tagName("body")).getText
    Assert.assertTrue(
      htmlBody.contains(
        "You have left this service.\nYou are no longer VAT registered. You must re-register for VAT to use the Import One Stop Shop service."
      )
    )
  }

  Then(
    """^they are presented with the correct banner for expired VRN trader removed from service and has outstanding returns$"""
  ) { () =>
    val htmlBody = driver.findElement(By.tagName("body")).getText
    Assert.assertTrue(
      htmlBody.contains(
        "We've removed you from this service, but you must complete and pay your final return.\nYou are no longer VAT registered. You must re-register for VAT to use the Import One Stop Shop service."
      )
    )
  }

  Then(
    """^they are presented with the correct banner for expired VRN trader removed from service and has no outstanding returns$"""
  ) { () =>
    val htmlBody = driver.findElement(By.tagName("body")).getText
    Assert.assertTrue(
      htmlBody.contains(
        "We've removed you from this service.\nYou are no longer VAT registered. You must re-register for VAT to use the Import One Stop Shop service."
      )
    )
  }

  Then(
    """^a dashboard message is displayed for a return outstanding for more than 3 years$"""
  ) { () =>
    val htmlBody = driver.findElement(By.tagName("body")).getText
    Assert.assertTrue(
      htmlBody.contains("You must complete your July 2020 return with the countries where you made your sales.")
    )
    Assert.assertTrue(
      htmlBody.contains("You must complete your May 2021 return with the countries where you made your sales.")
    )
  }

  Then(
    """^a dashboard message is displayed for a payment outstanding for more than 3 years$"""
  ) { () =>
    val htmlBody = driver.findElement(By.tagName("body")).getText
    Assert.assertTrue(
      htmlBody.contains(
        "You must pay any outstanding IOSS VAT for July 2020 to the countries where you made your sales."
      )
    )
  }

  Then(
    """^the remove page is displayed for the (.*) correction$"""
  ) { (period: String) =>
    val heading = driver.findElement(By.tagName("h1")).getText
    Assert.assertTrue(
      heading.equals(
        s"Are you sure you want to remove this correction for $period?"
      )
    )
  }

  Then(
    """^the corrections list is showing one correction for (.*)$"""
  ) { (period: String) =>
    val heading  = driver.findElement(By.tagName("h1")).getText
    val htmlBody = driver.findElement(By.tagName("body")).getText
    Assert.assertTrue(
      heading.equals(
        s"You have corrected the VAT amount for one return month"
      )
    )
    Assert.assertTrue(
      htmlBody.contains(period)
    )
  }

  Then(
    """^the corrections list is showing 2 corrections for (.*) and (.*)$"""
  ) { (period1: String, period2: String) =>
    val heading  = driver.findElement(By.tagName("h1")).getText
    val htmlBody = driver.findElement(By.tagName("body")).getText
    Assert.assertTrue(
      heading.equals(
        s"You have corrected the VAT amount for 2 return months"
      )
    )
    Assert.assertTrue(
      htmlBody.contains(period1)
    )
    Assert.assertTrue(
      htmlBody.contains(period2)
    )
  }

  Then(
    """^the remove country correction page is displayed for (.*)$"""
  ) { (country: String) =>
    val heading = driver.findElement(By.tagName("h1")).getText
    Assert.assertTrue(
      heading.equals(
        s"Are you sure you want to remove this correction for $country?"
      )
    )
  }

  Then(
    """^the country list page is displayed for the (.*) correction$"""
  ) { (period: String) =>
    val htmlBody = driver.findElement(By.tagName("body")).getText
    Assert.assertTrue(
      htmlBody.contains(
        s"Correction month: $period"
      )
    )
  }

  Then(
    """^the past return shows the full month version of the heading$"""
  ) { () =>
    checkFullMonthPastReturn()

  }

  When("""^an intermediary accesses the returns journey""") { () =>
    AuthPage.intermediaryLogin()
  }
}
