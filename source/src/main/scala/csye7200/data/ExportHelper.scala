package csye7200.data

import com.mongodb.DBObject
import com.mongodb.util.JSON
import com.mongodb.casbah._
import org.bson.Document
import com.mongodb.casbah.{MongoClient, MongoCollection}
import com.mongodb.util.JSON
import spray.json.JsValue;
import play.api.libs.json._
import com.mongodb.casbah.Implicits._
import com.mongodb.BasicDBList
import com.mongodb.DBObject
import com.mongodb.casbah.commons.MongoDBObject
import com.mongodb.casbah.commons.MongoDBList
import org.bson.types.ObjectId
import com.mongodb.casbah.Imports._
import java.text.DateFormat
import java.util.Date
import play.api.libs.json._
import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, SparkSession}

 case class ExportHelper(json:Array[JsValue]) {

  val spark = SparkSession
    .builder()
    .appName(this.getClass.getSimpleName).master("local")
    .config("spark.mongodb.output.uri", "mongodb://49.235.244.219:27017")
    .config("spark.mongodb.output.database", "csye7200")
    .config("spark.mongodb.output.collection", "traces")//daily_reports
    .getOrCreate()
//  val jsonRDD = spark.sparkContext.makeRDD(json)
  val jsonDataSet = spark.createDataFrame(json)


}
