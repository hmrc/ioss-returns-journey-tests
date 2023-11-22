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
import uk.gov.hmrc.test.ui.pages.{AuthPage, CommonPage}

class ReturnsStepDef extends BaseStepDef {

  val host: String = TestConfiguration.url("ioss-returns-frontend")

  Given("""the user accesses the authority wizard""") { () =>
    AuthPage.goToAuthStub()
  }

  Then("""^the user is redirected to their IOSS Account$""") { () =>
    driver.getCurrentUrl shouldBe s"$host/your-account"
  }

  Then("""^the user clicks on the Change Your Registration link$""") { () =>
    driver.findElement(By.id("change-your-registration")).click()
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
}
