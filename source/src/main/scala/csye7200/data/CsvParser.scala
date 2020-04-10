package csye7200.data

import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, SparkSession}

case class CsvParser(filename: String, session:SparkSession){
  def parse(): DataFrame = {
    session.read.option("header", "true").csv(filename)
  }
}
