package csye7200.data

import spray.json._
import scala.util.Random
import java.io._

case class Generator ()

case class Trace (phone: Long, date: String, latitude: Double, longtitude: Double)

object Generator{

  val lat1:Double = 42.352352
  val lat2:Double = 42.229478
  val long1:Double = -71.056530
  val long2:Double = -71.188359

  object Protocol extends DefaultJsonProtocol{
    implicit val traceFormat = jsonFormat4(Trace.apply)
  }

  def simulate (N:Int=1000000) = {
    val phones = new Array[Long](N)
    val traces = new Array[Trace](N*10)
    val jsonFile = "simulation.json"
    import Protocol._
    for (i <- 0 to N-1){
      phones(i) = Random.nextInt(900000000)+100000000
      for (j <- 0 to 9){
        traces(i*10+j) = new Trace(phones(i),"2020-04-"+(j+10),lat1 + Random.nextDouble() * (lat2 - lat1),long1 + Random.nextDouble() * (long2 - long1) )
      }
    }
    val writer = new PrintWriter(jsonFile)
    for (elem <- traces){
      writer.write(elem.toJson.toString()+"\n")
    }
    writer.close()
    ExportHelper(jsonFile,"traces","json").exportTo()
  }

}
