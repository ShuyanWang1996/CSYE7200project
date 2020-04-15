package csye7200.data

import org.apache.spark.sql.SparkSession
import com.mongodb.spark.MongoSpark
import org.apache.spark.sql.{DataFrame, SparkSession}

case class ImportHelper (collection: String) {

  def importFrom():DataFrame= {
    val spark = SparkSession
      .builder()
      .appName(this.getClass.getSimpleName)
      .master("local")
      .config("spark.mongodb.input.uri", "mongodb://root:!Csye7200@49.235.244.219:27017")
      .config("spark.mongodb.input.database", "csye7200")
      .config("spark.mongodb.input.collection", collection) //daily_reports
      .getOrCreate()
    val dataFrame: DataFrame = spark.read.format("mongo").load()
    dataFrame
  }
}

//case class ImportDailyReport(filename:String) extends ImportHelper{
//
//}
//
//case class ImportTimeSeries(filename: String) extends ImportHelper{
//
//}