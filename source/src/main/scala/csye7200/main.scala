package csye7200

import java.io.File

import com.mongodb.spark.MongoSpark
import csye7200.data.CsvParser
import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import com.mongodb.spark.config._


object analyze_process{
  def get_fileList(path:String):List[File]={
    val f = new File(path).listFiles.filter(f=>{f.isFile && f.getName.contains("csv")}).toList
    f
  }

  def start()={
    //init spark
    val sparkConf = new SparkConf().setMaster("local").setAppName("csye7200")
    val sparkSession = SparkSession.builder
      .config(sparkConf)
      .appName("csye_mongo")
      // Configuration for writing in a Mongo collection
      .config("spark.mongodb.output.uri", "mongodb://49.235.244.219:27017")
      .config("spark.mongodb.output.database", "csye7200")
      .config("spark.mongodb.output.collection", "daily_reports")
      // Configuration for reading a Mongo collection
      .config("spark.mongodb.input.uri", "mongodb://49.235.244.219:27017")
      .config("spark.mongodb.input.database", "csye7200")
      .config("spark.mongodb.input.collection", "daily_reports")
      .getOrCreate()
    //import all .csv in loop
    val path="../data/JHU/csse_covid_19_data/csse_covid_19_daily_reports/"
    get_fileList(path).foreach(f=>{
        println(f.getPath)
        //get DF from .csv
        val base_df = CsvParser(f.getPath, sparkSession).parse()
        base_df.show()
        //write to MongoDB
        MongoSpark.save(base_df.write.mode("append"))
    })
  }
}

object simulation_process{
  def start()={

  }
}

object main {
  def main(args: Array[String]): Unit = {
    println("Entry")
    analyze_process.start()
  }
}
