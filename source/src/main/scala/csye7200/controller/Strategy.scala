package csye7200.controller

class Strategy {

}

case class DTStrategy(){
  def start(): Unit = {
    //spark do not support non-linear-regression
    val proc1 = Runtime.getRuntime().exec("python regression.py")
    proc1.waitFor()
  }
}