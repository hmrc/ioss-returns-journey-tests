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

import uk.gov.hmrc.test.ui.conf.TestConfiguration
import uk.gov.hmrc.test.ui.pages.{AuthPage, CommonPage}

class ReturnsStepDef extends BaseStepDef {

  val host: String = TestConfiguration.url("ioss-returns-frontend")

  Given("^the user accesses the service$") { () =>
    CommonPage.goToStartOfJourney()
  }

  Given("^the user signs into authority wizard as an Organisation Admin with VAT enrolment (.*)$") { (vrn: String) =>
    AuthPage.loginUsingAuthorityWizard(vrn)
  }

  Then("""^the user is directed back to the index page$""") { () =>
    driver.getCurrentUrl shouldBe host
  }

}
