package cn.paraview

import org.apache.spark.{SparkConf, SparkContext}

object WordCountSpark {

  /** spark-submit --class com.test.WorldCount --master spark://192.168.18.151:7077 sparktest.jar /test/data.txt /test/test-01 */
  def main(args: Array[String]) {

    test()
    val dataFile = args(0)
    val output = args(1)
    val sparkConf = new SparkConf().setAppName("WorldCount")
    val sparkContext = new SparkContext(sparkConf)
    val lines = sparkContext.textFile(dataFile)
    val counts = lines.flatMap(_.split(",")).map(s => (s, 1)).reduceByKey((a, b) => a + b)
    counts.saveAsTextFile(output)
    sparkContext.stop()

  }

  /** 测试 */
  def test(): Unit = {
    val sparkConf = new SparkConf().setMaster("local").setAppName("test")
    val sparkContext = new SparkContext(sparkConf)
    println("Hello World!")
  }
}