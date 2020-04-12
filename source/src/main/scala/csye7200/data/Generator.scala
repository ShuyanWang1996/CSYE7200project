//package csye7200.data

//import spray.json._
import scala.util.Random
import java.util.Date
import java.io._

case class Generator ()

case class Trace (phone: Long, date: Date, latitude: Double, longtitude: Double)

object Generator{

  // simulation
  val lat1:Double = 42.352352
  val lat2:Double = 42.229478
  val long1:Double = -71.056530
  val long2:Double = -71.188359
  val N = 20;
  val phones = new Array[Long](N)
  val traces = new Array[Trace](N*10)

  def simulate () = {
    for (i <- 0 to N-1){
      phones(i) = Random.nextInt(999999999)
      for (j <- 0 to 9){
        traces(i*10+j) = new Trace(phones(i),new Date(),lat1 + Random.nextDouble() * (lat2 - lat1),long1 + Random.nextDouble() * (long2 - long1) )
      }
    }
    val writer = new PrintWriter("simulation.json")
//  import DefaultJsonProtocol._
    for(elem <- traces){
      println(elem)
//      writer.write(elem.toString)
    }
    writer.close()
  }
  def main(): Unit = {
    println("Hello")
    simulate()
  }
}
