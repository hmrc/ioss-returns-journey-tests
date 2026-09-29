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

package uk.gov.hmrc.ui.specs.AdditionalTests

import uk.gov.hmrc.ui.pages.*
import uk.gov.hmrc.ui.specs.BaseSpec

class VatGroupYesSpec extends BaseSpec {

  private val dashboard = Dashboard
  private val auth      = Auth
  private val netp      = NETP

  Feature("VAT Group Yes journeys") {

    Scenario(
      "User has fixed establishments in their registration but is now a VAT Group - dashboard"
    ) {

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()

      When("the user is now a VAT Group and has fixed establishments in their registration")
      auth.loginUsingAuthorityWizard("777777779", "IM9001234567", "Organisation", "hasIOSSEnrolment", "dashboard")

      Then("the user is redirected to the delete-all-fixed-establishments-as-part-of-vat-group page within the registration service")
      dashboard.checkRegistrationJourneyUrl("delete-all-fixed-establishments-as-part-of-vat-group")
    }

    Scenario(
      "User has fixed establishments in their registration but is now a VAT Group - start a return"
    ) {

      Given("the user accesses the IOSS Returns Service")
      auth.goToAuthorityWizard()

      When("the user is now a VAT Group and has fixed establishments in their registration")
      auth.loginUsingAuthorityWizard("777777779", "IM9001234567", "Organisation", "hasIOSSEnrolment", "returnFixedEstablishment")

      Then("the user is redirected to the delete-all-fixed-establishments-as-part-of-vat-group page within the registration service")
      dashboard.checkRegistrationJourneyUrl("delete-all-fixed-establishments-as-part-of-vat-group")
    }
  }
}
