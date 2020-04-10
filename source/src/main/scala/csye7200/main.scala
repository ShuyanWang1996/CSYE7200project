package csye7200

import csye7200.data.CsvParser
import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object main {
  def main(args: Array[String]): Unit = {
    println("Entry")
    //init spark
    val sparkConf = new SparkConf().setMaster("local").setAppName("csye7200")
    val sparkContext = new SparkContext(sparkConf)
    val path="../data/JHU/csse_covid_19_data/csse_covid_19_daily_reports/01-22-2020.csv"
    val sparkSession = SparkSession.builder
      .config(sparkConf)
      .appName("mycsye")
      .getOrCreate()
    //get DF from .csv
    val base_df = CsvParser(path, sparkSession).parse()
    base_df.show()
  }
}
