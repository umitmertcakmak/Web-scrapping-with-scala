package com.datadrivers.mongo

/**
  * Created by umitcakmak on 31/05/17.
  */

import scala.collection.immutable.IndexedSeq

import org.mongodb.scala._
import org.mongodb.scala.model.Aggregates._
import org.mongodb.scala.model.Filters._
import org.mongodb.scala.model.Projections._
import org.mongodb.scala.model.Sorts._
import org.mongodb.scala.model.Updates._
import org.mongodb.scala.model._

import Helpers._

object MongoDriver {

  def main(args: Array[String]): Unit = {
    // docs here: http://mongodb.github.io/mongo-scala-driver/1.0/getting-started/quick-tour/
    // To directly connect to the default server localhost on port 27017
    val mongoClient: MongoClient = MongoClient()

    // get handle to "mydb" database
    val database: MongoDatabase = mongoClient.getDatabase("mydb")

    // get a handle to the "test" collection
    val collection: MongoCollection[Document] = database.getCollection("test")

    collection.drop().results()

    // make a document and insert it
    val doc: Document = Document("_id" -> 0, "name" -> "MongoDB", "type" -> "database",
      "count" -> 1, "info" -> Document("x" -> 203, "y" -> 102))

    collection.insertOne(doc).results()

    // get it (since it's the only one in there since we dropped the rest earlier on)
    collection.find.first().printResults()

//
//    // now, lets add lots of little documents to the collection so we can explore queries and cursors
//    val documents: IndexedSeq[Document] = (1 to 100) map { i: Int => Document("i" -> i) }
//    val insertObservable = collection.insertMany(documents)
//
//    val insertAndCount = for {
//      insertResult <- insertObservable
//      countResult <- collection.count()
//    } yield countResult
//
//    println(s"total # of documents after inserting 100 small ones (should be 101):  ${insertAndCount.headResult()}")
//
//    collection.find().first().printHeadResult()
//
//    // Query Filters
//    // now use a query to get 1 document out
//    collection.find(equal("i", 71)).first().printHeadResult()
//
//    // now use a range query to get a larger subset
//    collection.find(gt("i", 50)).printResults()
//
//    // range query with multiple constraints
//    collection.find(and(gt("i", 50), lte("i", 100))).printResults()
//
//    // Sorting
//    collection.find(exists("i")).sort(descending("i")).first().printHeadResult()
//
//    // Projection
//    collection.find().projection(excludeId()).first().printHeadResult()
//
//    //Aggregation
//    collection.aggregate(Seq(
//      filter(gt("i", 0)),
//      project(Document("""{ITimes10: {$multiply: ["$i", 10]}}"""))
//    )).printResults()
//
//    // Update One
//    collection.updateOne(equal("i", 10), set("i", 110)).printHeadResult("Update Result: ")
//
//    // Update Many
//    collection.updateMany(lt("i", 100), inc("i", 100)).printHeadResult("Update Result: ")
//
//    // Delete One
//    collection.deleteOne(equal("i", 110)).printHeadResult("Delete Result: ")
//
//    // Delete Many
//    collection.deleteMany(gte("i", 100)).printHeadResult("Delete Result: ")
//
//    collection.drop().results()
//
//    // ordered bulk writes
//    val writes: List[WriteModel[_ <: Document]] = List(
//      InsertOneModel(Document("_id" -> 4)),
//      InsertOneModel(Document("_id" -> 5)),
//      InsertOneModel(Document("_id" -> 6)),
//      UpdateOneModel(Document("_id" -> 1), set("x", 2)),
//      DeleteOneModel(Document("_id" -> 2)),
//      ReplaceOneModel(Document("_id" -> 3), Document("_id" -> 3, "x" -> 4))
//    )
//
//    collection.bulkWrite(writes).printHeadResult("Bulk write results: ")
//
//    collection.drop().results()
//
//    collection.bulkWrite(writes, BulkWriteOptions().ordered(false)).printHeadResult("Bulk write results (unordered): ")
//
//    collection.find().printResults("Documents in collection: ")
//
//    // Clean up
//    collection.drop().results()
//
//    release resources

    mongoClient.close()
  }
}
