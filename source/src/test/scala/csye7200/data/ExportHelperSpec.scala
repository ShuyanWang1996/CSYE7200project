package csye7200.data

import org.apache.spark.sql.{DataFrame, SparkSession}
import org.scalatest.{FlatSpec, Matchers}

import scala.io.{Codec, Source}
import scala.util._

class ExportHelperSpec extends FlatSpec with Matchers{

//  behavior of "json file"

  it should "work for json file" in {

    val exportHelper = ExportHelper("src/test/scala/csye7200/data/JsonDemo.json","unit_test_io","json")
    exportHelper.exportTo();
    //    val dataFrame:DataFrame = parser.parse()
    //
    //    println("aaa")
    ////    println(dataFrame)
    ////    dataFrame should matchPattern{
    ////      case
    ////    }
  }
}
