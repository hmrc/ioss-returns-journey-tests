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

package uk.gov.hmrc.ui.data

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
         |  "data" : "tJDvbN3jDrlfkNyrMu+lRAycn1Q2ah8tKcfI98LqXbEzbWbZM38weZwx1UbwEvMOC5DW0yXMYnRpb6o0+HU2Ov3M2JQjdyY1JdEe3rGBJtgK4hXNbZir3ZxynWyAm55PRRYniZTVsP6mVa6uw/Dw1+xJsvDhUh+BQYR8G/C+Oi5mA+uuvcTTNt+ZTHo+rsIhqFtCSV/ANLlQxQuZyaqMXM8/tq28FqbpX0bxgSPZD5TDeNaPEx6jjkGT+PLp+lFcPc9BpiavxsZmBmm5l7+fwYtajfLUyq+AtvjBCb7pbmHRbjRjgvmZdaR7elrsZsIUHnGy80SeV1hHPfE0imtEx3CXsnKlSIE01eh5kbeiQ8Vvf26Ubkj+uo1JBGjP+uXOu7o/z+KJgjjpEcUDdHn8r0PyU6Z0IrnJaAo3o1k4fjsgrd/2uSy8F87wKYi8xGlUr84bV/vOoebMAAiQQAnzf/UoEf4uAYyU6EOik3AkF7cuEttsuIUxajRETLv5RNIOHa/KJ0d+sWWp673vAbPXKXyKwA/yud4KVT8/stsBUuNWnGza/KY74YkFe9LnYkaCp/8INBT2DQzWVirvumUkpQsxq8NteM2f1mYNoFQ0V5NoLKivgF6jJJLu3kzFYw==",
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
         |  "data" : "CwCoRhfMqXfqvc7K5pBchxtIVp+1BkfUvE9jqpcCn5rkHJ/5/KXNaxg+prayqFC7IcilcYLOT/kc6wwNXnudoyQrCZLACwz/5REXBxYpchVNfOlSctOLyeeLk/3Mr65w1oCLtB+supeavShK6zC7oKy2PChq7fGUYzAeuEmlc6RfG9dDCdw71khgicbbdvLzrX3S/1XK2SgfnSTVceQq7Q1OnM4pZOYmPeH43N5venh5ir/XB1Oh9157I4+RVoNROJjMGJPdA+eEooyDCSCOpb8a9ZsI9geiv8uNPb+VwdJ9gIuydxPpqdsErRRvb9+wv+nk/eL3giHwKRMs1pjE3Nluwcga//BKtu/ah+r4T7ONmKIwfVMFOw5nlciTMlyQd0ce4oty3PksAb58xUflEnIu6KRJ6Qlb/1BDRIanMgxznF8iaBieqQ7wKVSNr0Q1hkyll4E1QdKEy0zxfnyLjr86cWv/QPj2I6acbnquLB87hqC84dhmybMrNZOs7GxXvX5MhcNErSmy0SB02NMn4vSU1tmP1n/mlTKXRWm673sXLhcNtZFs458CpYcnGJEdhRLP917AnTUhnMmKPV+eN4v1lQO0Kl2ou0q6f4gb9mjfdkeYkkHOCarRgjO4gdLZUBJxzyOFZWu5lP0M8xyngDDGlEmBgsdkl/rikvge63iyIeN63NBzOsltSotaaWWDZBDm+g4n4xDQIw5QcwmHImqZXbHiW0ewHsvohe444NQD6bs/aGAfyfxrt0rq2NnL2b7o0L8llSAocyelQEZ9wRWzvUXc/d2+fsNOAmT7unmFlGLg4lTRZXx1FQtplv80SHHcBuQFlDw+HAD27hs5r4uU+rId6I6RqVH42yvP39hMbZvGKoIavJ8QWSxNFQ==",
         |  "lastUpdated": {
         |    "$$date": "${yesterday}T11:27:19.858Z"
         |  }
         |}
         |""".stripMargin
    )
}
