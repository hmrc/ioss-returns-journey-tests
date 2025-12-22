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

package uk.gov.hmrc.test.ui.data

import java.time.LocalDate

object SavedReturns {
  val yesterday = LocalDate.now().minusDays(1)

  val data: List[String] =
    List(
      s"""
         |{
         |  "_id": {
         |    "$$oid": "6949260b7cf1becd5eee0573"
         |  },
         |  "iossNumber": "IM9006655441",
         |  "period": {
         |    "year": 2025,
         |    "month": "M1"
         |  },
         |  "data": "k3ySQgdC3SV/SFOVWxBX2KCTMTo97gSs75ssPEQW2W14lzJV88WEjMCBi/Q0h/6xqQD4fMrUUT5ai2Sw/h73nx6d5YpUOJt33Dy7NzsFAFnWzmCUxAXkGR1MfL/bLMRNRRpUnIqDaq6VCYXRwhkob7OQUJ4BhxWvLWxi+GSPrDZQ+s2k7E+9kqspKRZ+JCEKCw0RuDMf696vmXxh7aMACeTBtodczPBaUDo=",
         |  "lastUpdated": {
         |    "$$date": "${yesterday}T11:05:47.198Z"
         |  }
         |}
         |""".stripMargin,
      s"""
         |{
         |  "_id": {
         |    "$$oid": "694928797cf1becd5eee940b"
         |  },
         |  "iossNumber": "IM9006655442",
         |  "period": {
         |    "year": 2025,
         |    "month": "M1"
         |  },
         |  "data": "NVmDFkQFNZJecpgraA8uRGsqxaV9GG1IxKJLvMXq+n6PcVYGMRbT6DWFEcXnf4mPllHi5XJVhrmgWrmjJ+QCwtvtdFNM/9+AMd65DIDup38Hh/ZZsk7/X471ISyPOXpAimzrrze+vfm9A6zYX6bxBvjenxxi7LnYaRzedzmUxb0T3/1sRqXc2DSTwujJXx6u0ZpTaVXl8E2cAEvSdHEPJ4VYNDqxfl/CUyK6QIA10bZnYOs91vY3waa4pEz4ROKJ6vNZ5cB/6abxDITWQEj5sM0RUi3/qYNUgqbCzkgxSGWdD6MeekpgpNoVswcwQZbbLiMBGNHlMLt47ty22IjHK8tMVN5WXG6mJGf57OgAIuZ6Hq7eXHy6o9XpH+Ro2iuHIusns/uEDX9i9svnq4ZTmvBJ15m02kOVdLNvTcD2Mlc1LJtITL8ZeBKZjfH82XOxiv4pr2al2ewp4Ybgqk0TZekTzQ2cE0qta7MBhcUk5vbEdVH67yQSu97Gh9/FfVDiUdthiwQEq37cbXShQbLGJH2LXjZ9BOlhaShasO4TIrsglgv/wzp7HssxVQqWOT62c3qgsdPp9hjihDwhmUx0uqpDn03NkXcsiQqWLWgT1rneMyuDXSgGIoqkvf7IJ3+obn281DOVkB1zo3ackjyvtcZWcMZSKDuEJtRXDctqXLmj4ZoABwZwNT7uMcvy4U5bvpDUM19+NCpH5nfKEgk+FZCi3cE6OYBWpu6K20OIOBL29E2ncVs7zWbth8tXMUtxnhZHNXmEAu+1nWQvcDyeIBl2Qr31R5rjy93WXI/FHZC9pZs09uaM9IOT7nfs1JGcHx4GYG/P3qfW3l13zWKtSZ2C6aMRjO8KVm8JX6x5pDinPnTs9K4InSNXMo/rFUt7l3V5MkFkl3F5mbiHJItAxt+IzDHTKuYxUfkxKKu9dWaZelVk0+BgkxhICSYuTfseOeEbc6Pc6WXoDA==",
         |  "lastUpdated": {
         |    "$$date": "${yesterday}T11:16:09.439Z"
         |  }
         |}
         |""".stripMargin,
      s"""
         |{
         |  "_id": {
         |    "$$oid": "6949290c7cf1becd5eeeb6c3"
         |  },
         |  "iossNumber": "IM9006655443",
         |  "period": {
         |    "year": 2025,
         |    "month": "M3"
         |  },
         |  "data": "gtojCZrXTQzQZrb4IcPSYl/J/d9MuLvXEConFzc8DemwUu+n4jsFCAF5K19eVOuNoOkDciEJ2ZozwBkQSyX3xngxAjHc2gUxuZAtRXIMiZ73Lhf+ZXyG6ND97CY3d6s60vRXPVb58fwnv8h6sfwyuI3EyzFqXPsnHPYJETGa/t/AhrAAnYFTcTLI4+TOfJxgeg6ECdzLqn19dI95OXzbfoYlZHax/iCJlhQsRrCCfxzGJVxBJHEYivDWu+GnCSKc+MARDMcJfbDQNsx3cbzms7ShO3MdWsykOwKHovKt5MITDQURWJ4jkEbS8+7nTNIGBbrIrHGOOxqU9/IKK7IjljDS5nPYQpgaACWPaAeXd9jGwJF4DpRxYI6tigqGiBIefhfnTb9qVRcFinpZCDocyLsMcSiSQJ8TXSUnpa+VKHefq2HkUc78jBaUm4e1gIUM24ePAPZaRJFQUKrxomtQm4DhpFHW5u8CAqeUWxiZKLyMWBahBfW+rFjN1vanbRiaboxVPlz6k25aBEorn2i5mOidlwWxuFmC4pElf8YKfsjaRavbpJM7FFHsrtBt5dGxzeJxvC5z/8qry8MMavJ2nGR6dZPfx+aiXNfqWynISrWy",
         |  "lastUpdated": {
         |    "$$date": "${yesterday}T11:18:36.172Z"
         |  }
         |}
         |""".stripMargin,
      s"""
         |{
         |  "_id": {
         |    "$$oid": "69492b177cf1becd5eef3085"
         |  },
         |  "iossNumber": "IM9006655551",
         |  "period": {
         |    "year": 2025,
         |    "month": "M1"
         |  },
         |  "data": "77/ZoSPzDOGzXQskLH1pGvhp+tp3mJjDhdYjO0lmXnrnD1+osJ98EEqn2pya8Z7LcKU9/FFNsUlfrrgVx6MQ43eOYKOmV9Pp7Lv+fcNymSRb9bbhBCOWuGstVIwoQ/2PZoZQ/92ORGPpmQLptVxnfLReS6pJjefu8lL8mOuhoEqnFCexF/jfomkPS0dmzjDbOaNDhoZCcWSypLCux3hVMhyWp2Kz0esxQl+yBdf/iI/ETtKQ4iAWtOyIvhZLonp/v1ZGgq7fQh28PuYGMR6ddtks2SFAmin5y5m6tUjPz8FA489fyJfQY3vQoZoymX4aJeMfcNxABF4VtfJDejHQqovAjbK+pt5BX1YeYEcUmTbi/zldKq4MN/hS9Xz4k5LWgrL79DtbotceS/APARyJn7Ytc1QLDvdhPmp8SoIJNz10vNpwoHeOmud5rRfJOqOTGA+9Gqzxf9+Im9WMvc/RLThGwhaRDyLfYOaTVyaw9gyE4RD+djRLoWEwuvS/cruKpwcXoGAegr4CpcHZ4lGoRuBR54omUYJCl8CmhFC0wAo4UbDtE7J6i7Bqe10wIDCVE6izQhYE3Ht2ZB4wWtEaQ4pXrLophpHxpXxP6C1cS1/PJ+Oc2l5dEklK+J6W5zonrf+YcEflYlozssLsCQ4clfiUIP0DZEus50+mKvgQ3ghsAZGV5R2sh6Oigm7BZwlTVgkBKAsg5QOHcaa5edJoLNojkY5DojoZHzTkvONICeoY/Pz8/cL+MgMuUqTGeKPaonJjqKeMzr9Dx9hIf1qxhpXM20uZLTJhFklB6M7j+ptROsIstMucT5I44YcEmn9J/SfXLmpojc62elf1BDmEwHhGpDiTX3cLTMskCKTGL8vs",
         |  "lastUpdated": {
         |    "$$date": "${yesterday}T11:27:19.858Z"
         |  }
         |}
         |""".stripMargin
    )
}
