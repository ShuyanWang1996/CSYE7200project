package csye7200.data

import com.mongodb.spark.MongoSpark
import org.apache.spark.sql.{DataFrame, SparkSession}

 case class ExportHelper(jsonFile:String, collection: String) {

  val spark = SparkSession
    .builder()
    .appName(this.getClass.getSimpleName)
    .master("local")
    .config("spark.mongodb.output.uri", "mongodb://49.235.244.219:27017")
    .config("spark.mongodb.output.database", "csye7200")
    .config("spark.mongodb.output.collection", collection)//daily_reports
    .getOrCreate()

  val dataFrame: DataFrame = spark.read.json(jsonFile)
  dataFrame.show()
  MongoSpark.save(dataFrame)

}
