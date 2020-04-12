package csye7200.data

import spray.json._
import scala.util.Random
import java.util.Date

case class Generator (lat1: float, lat2: float, long1: float, long2: float) {

  // simulation
  val latDif = lat2 - lat1
  val longDif = long1 - long2
  val N = 1000000;
  val arrPhone = Seq[Long](N)
  val arrTrace = Seq[Trace](N*10)

  def simulate () = {
    for (i <- 1 to N-1){
      arrPhone(i) = Random.nextInt(999999999)
      for (j <- 1 to 10){
        arrTrace(i*10+j) = new Trace(arrPhone(i),new Date(2020,4,j),lat1 + Random.nextFloat() * latDif,long1 + Random.nextFloat() * longDif)
      }
    }

  }

}

case class Trace (phone: Long, date: Date, latitude: float, longtitude: float){
}
