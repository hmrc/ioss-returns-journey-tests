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

import uk.gov.hmrc.test.ui.pages.{AuthPage, IntermediaryPage}

class IntermediaryStepDef extends BaseStepDef {

  When("""^intermediary (.*) accesses the (.*) journey for NETP (.*)""") {
    (intermediaryNumber: String, journey: String, iossNumber: String) =>
      AuthPage.intermediaryLogin(intermediaryNumber, iossNumber, journey)
  }

  Then("""^the correct details are shown on the acknowledgement page for NETP (.*)""") { (iossNumber: String) =>
    IntermediaryPage.checkNetpReturn(iossNumber)
  }

  Then(
    """^the correct caption is displayed for (UK with VRN|UK with UTR|UK with NINO|Non-UK with VRN|Non-UK with FTR)"""
  ) { (scenario: String) =>
    IntermediaryPage.checkCaption(scenario)
  }

  Then(
    """^the correct details are displayed at the top of check-your-answers for (UK with VRN|UK with UTR|UK with NINO|Non-UK with VRN|Non-UK with FTR)"""
  ) { (scenario: String) =>
    IntermediaryPage.checkCYA(scenario)
  }

  Then(
    """^the correct submitted returns caption is displayed for (.*)"""
  ) { (scenario: String) =>
    IntermediaryPage.checkSubmittedReturnsCaption(scenario)
  }

  Then(
    """^the correct start return heading is displayed for a (UK with VRN|UK with UTR|UK with NINO|Non-UK with VRN|Non-UK with FTR) client"""
  ) { (scenario: String) =>
    IntermediaryPage.checkReturnStartHeading(scenario)
  }

}
