package cn.paraview

class Para(var name: String, var location: String) {
  val staticName = "default"
}

class ParaView {
  var name: String = ""
  var location: String = ""
}

object Para {


  def main(args: Array[String]): Unit = {

    val para: Para = new Para(null, "张江")

    val paraView: ParaView = new ParaView

    para.name = "派拉para"
    paraView.name = "派拉paraview"

    println(para.name)
    println(paraView.name)

    println(method(func(1, 3), 1))
    println(func(method(1, 3), 1))

    val arr = Array(1, 2, 3, 4, 5)
    val amp = arr.map(2 * _)

    println(amp)

  }

  // 函数、方法的区别

  def method(x: Int, y: Int): Int = x + y

  val func = (x: Int, y: Int) => x + y

}