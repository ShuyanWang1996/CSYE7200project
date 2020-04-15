package csye7200.data

import org.apache.spark.sql.{DataFrame, SparkSession}
import org.scalatest.{FlatSpec, Matchers}

import scala.io.{Codec, Source}
import scala.util._

class ExportImportHelperSpec extends FlatSpec with Matchers{

  behavior of "json file I/O"

  it should "work for export" in {
    val exportHelper = ExportHelper("src/test/scala/csye7200/data/unit_test_io.json","unit_test_io","json")
    exportHelper.exportTo();
  }

  it should "work for import" in {
    val importHelper = ImportHelper("unit_test_io")
    importHelper.importFrom()
  }

  it should "work for both export and import" in {
    val collection = "unit_test_io"
    println("DataFrame from ExportHelper")
    val dataFrame1 = ExportHelper("src/test/scala/csye7200/data/unit_test_io.json",collection,"json").exportTo()
    println("DataFrame from ImportHelper")
    val dataFrame2 = ImportHelper("unit_test_io").importFrom()
  }
}
