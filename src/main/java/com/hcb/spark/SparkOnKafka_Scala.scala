/*
package com.hcb.spark

/**
  * TODO 功能描述
  *
  * @author xu.zhang5
  * @date 2018/4/13 15:27
  * @since V1.0
  *
  */
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.streaming.kafka010._
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe


object SparkOnKafka_Scala {
  def main(args: Array[String]): Unit = {

    val conf=new SparkConf().setMaster("local[2]").setAppName("test")
    val sc=new SparkContext(conf)


    sc.textFile("")

    sc.setLogLevel("WARN")
    val streamingContext=new StreamingContext(sc,Seconds(5))


    val kafkaParams = Map[String, Object](
      "bootstrap.servers" -> "avatartest:9092",
      "key.deserializer" -> classOf[StringDeserializer],
      "value.deserializer" -> classOf[StringDeserializer],
      "group.id" -> "use_a_separate_group_id_for_each_stream",
      "auto.offset.reset" -> "latest",
      "enable.auto.commit" -> (false: java.lang.Boolean)
    )

    val topics = Array("zxtest")
    val stream = KafkaUtils.createDirectStream[String, String](
      streamingContext,
      PreferConsistent,
       Subscribe[String, String](topics, kafkaParams)
    )

    stream.map(record => (record.key, record.value)).print()

    streamingContext.start()
    streamingContext.awaitTermination()

    sc.stop()

  }
}
*/
