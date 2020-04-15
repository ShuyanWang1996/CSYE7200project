package csye7200.data

import org.scalatest._

import scala.io._
import scala.util._

class GeneratorSpec extends FlatSpec{

  behavior of "generate simple sample"

  it should "generate ten rows data with the same phone" in{
    Generator.simulate(1,"unit_test_generator")
  }

}
