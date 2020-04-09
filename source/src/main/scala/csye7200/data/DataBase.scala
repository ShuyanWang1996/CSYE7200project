package csye7200.data

import org.mongodb.scala._

case class Database(host_name:String, db_name:String){
  val mongoClient: MongoClient = MongoClient(host_name)
  val database: MongoDatabase = mongoClient.getDatabase(db_name)
}
