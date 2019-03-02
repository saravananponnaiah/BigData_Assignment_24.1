package com.company.stream

import org.apache.spark._
import org.apache.spark.streaming._
import org.apache.spark.streaming.api.java._
import org.apache.spark.api.java.function._

object WordCounter {
  def main(args : Array[String]) : Unit = {
      val conf = new SparkConf().setMaster("local[6]").setAppName("Word Counter")
      val ssc = new StreamingContext(conf, Seconds(20))
      val lines = ssc.textFileStream("E:\\Hadoop\\StreamData")
      val words = lines.flatMap(line => line.split(","))
      val pairs = words.map(word => (word, 1))
      val wordCount = pairs.reduceByKey(_ + _)
      wordCount.print()
      
      ssc.start()
      ssc.awaitTermination()
  }
}