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

package uk.gov.hmrc.test.ui.cucumber.utils

import org.mongodb.scala.MongoClient
import org.mongodb.scala.model.Filters

import scala.concurrent.Await
import scala.concurrent.duration._

object MongoConnection {

  private val timeout: FiniteDuration = 10.seconds

  private val mongoClient: MongoClient = MongoClient()

  sys.addShutdownHook {
    mongoClient.close()
  }

  def dropRecord(db: String, collection: String, iossNumber: String): Unit =
    try Await.result(
      mongoClient
        .getDatabase(db)
        .getCollection(collection)
        .deleteMany(filter = Filters.equal("iossNumber", iossNumber))
        .head(),
      timeout
    )
    catch {
      case e: Exception => println("Error: " + e)
    }

  def dropSavedAnswers(): Unit = {
    dropRecord("ioss-returns", "saved-user-answers", "IM9001234566")
    dropRecord("ioss-returns", "saved-user-answers", "IM9007777778")
    dropRecord("ioss-returns", "saved-user-answers", "IM9005999997")
    dropRecord("ioss-returns", "saved-user-answers", "IM9009999888")
    dropRecord("ioss-returns", "saved-user-answers", "IM9001234567")
    dropRecord("ioss-returns", "saved-user-answers", "IM9001234569")
    dropRecord("ioss-returns", "saved-user-answers", "IM9001234568")
    dropRecord("ioss-returns", "saved-user-answers", "IM9005999997")
    dropRecord("ioss-returns", "saved-user-answers", "IM9001233211")
    dropRecord("ioss-returns", "saved-user-answers", "IM9005999977")
    dropRecord("ioss-returns", "saved-user-answers", "IM9009995555")
    dropRecord("ioss-returns", "saved-user-answers", "IM9009999555")
    dropRecord("ioss-returns", "saved-user-answers", "IM9049999994")
    dropRecord("ioss-returns", "saved-user-answers", "IM9009999995")
    dropRecord("ioss-returns", "saved-user-answers", "IM9007777777")

  }
}
