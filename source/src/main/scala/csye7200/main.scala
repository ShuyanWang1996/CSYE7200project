package csye7200

import java.io.File

import com.mongodb.spark.MongoSpark
import csye7200.data.CsvParser
import csye7200.controller.{DTStrategy, Strategy}
import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types._
import com.mongodb.spark.config._
import csye7200.data.Generator

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
      .config("spark.mongodb.output.uri", "mongodb://root:!Csye7200@49.235.244.219:27017")
      .config("spark.mongodb.output.database", "csye7200")
      .config("spark.mongodb.output.collection", "test")//daily_reports
      .getOrCreate()
    //import all .csv in loop
//    val path="../data/JHU/csse_covid_19_data/csse_covid_19_daily_reports/"
//    get_fileList(path).foreach(f=>{
//      println(f.getPath)
//      //get DF from .csv
//      val base_df = CsvParser(f.getPath, sparkSession).parse()
//      base_df.show()
//      //write to MongoDB
//      MongoSpark.save(base_df.write.mode("append"))
//    })
    //Read China data from mongoDB
    val df_China = sparkSession.read.format("mongo").option("uri", "mongodb://49.235.244.219/csye7200.train_data_China").load()
    DTStrategy().start()
    val df_US = sparkSession.read.format("mongo").option("uri", "mongodb://49.235.244.219/csye7200.test_data_US").load()
  }
}

object simulation_process{
  def start()={
    Generator.simulate()
  }
}

object main {
  def main(args: Array[String]): Unit = {
    println("Entry")
//    analyze_process.start()
    simulation_process.start()
  }
}
