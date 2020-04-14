package csye7200.data

import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, SparkSession}

case class CsvParser(filename: String, session:SparkSession){
  def parse(): DataFrame = {
    //The ETL Process
    val columnNames = Seq("Country" ,"Province", "Datetime", "Confirmed", "Deaths", "Recovered")
    val df:DataFrame = session.read.option("header", "true").csv(filename)
    df.withColumnRenamed( "Country/Region" , "Country")
      .withColumnRenamed( "Province/State" , "Province")
      .withColumnRenamed( "Last Update" , "Datetime")
      .withColumnRenamed( "Country_Region" , "Country")
      .withColumnRenamed( "Province_State" , "Province")
      .withColumnRenamed( "Last_Update" , "Datetime")
      .select(columnNames.head, columnNames.tail: _*)
    df
  }
}
