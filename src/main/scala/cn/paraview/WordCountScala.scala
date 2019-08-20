package cn.paraview

import java.io.PrintWriter

object WordCountScala {

  def main(args: Array[String]): Unit = {

    import scala.io.Source

    // 1、读取文件，获取文件对象
    val source = Source.fromFile("c://training", "UTF-8")

    // 2、获取行遍历器
    val lines: Iterator[String] = source.getLines()

    // 3、存储所有行到数组
    val arr: Array[String] = lines.toArray

    // 4、数组中每一个元素均为一行数据
    val res1: Array[String] = arr.flatMap(_.split(" "))

    // 5、对每一行进行切分，每行的每个单词都设置为1次，这种key-value 格式用tuple的数据结构存储
    val res2: Array[(String, Int)] = res1.map(x => (x, 1))

    // 6、按照规则分组，这里对每一个tuple元素(key-value) 按照 key进行分组，即单词相同的分到一组, 每个元素形如：hello , [(hello ,1),(hello ,1)]
    val res3: scala.collection.immutable.Map[String, Array[(String, Int)]] = res2.groupBy(x => x._1)

    // 7、每一组内的tuple元素(key,1)个数，即为出现的个数
    val res4: scala.collection.immutable.Map[String, Int] = res3.map(x => (x._1, x._2.size))

    // 8、将key-value 形式转换为list形式
    val res5 = res4.toList

    // 9、按照出现的次数进行排序(按照tuple的value进行排序),在按照tuple的key值进行排序(反向)
    val res6 = res5.sortBy(_._2).reverse

    // 10、结果输出到文件,Scala不提供文件写入功能组件,需要借助java 的方法，PrintWrite / File等方法均可
    val out = new PrintWriter("c://result.txt")
    for (x <- res6) {
      out.println(x._1 + "\t\t" + x._2)
    }
    out.close()
  }
}