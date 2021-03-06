package csye7200.data

import org.apache.spark.sql.{DataFrame, SparkSession}
import org.scalatest.{FlatSpec, Matchers}

import scala.io.{Codec, Source}
import scala.util._

class CsvParserSpec extends FlatSpec with Matchers{

  behavior of "csv file parsing"

  it should "work for csv data" in {

    val spark = SparkSession
      .builder()
      .appName(this.getClass.getSimpleName)
      .master("local")
      .config("spark.mongodb.output.uri", "mongodb://root:!Csye7200@49.235.244.219:27017")
      .config("spark.mongodb.output.database", "csye7200")
      .getOrCreate()

    val parser = CsvParser("src/test/scala/csye7200/data/unit_test_parse.csv",spark)
    val dataFrame = parser.parse()
    dataFrame.show()
  }

}
